package com.cqrs.cqrs.zkteco4j.cnn;


import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.channels.DatagramChannel;

public class Connector {
    public static final long TIMEOUT_MILLIS = 30000;
    private String hostname;
    private int port;
    private ConnectorType type;
    private SocketChannel tcpChannel;
    private DatagramChannel udpChannel;

    public Connector(String hostname, int port, ConnectorType type) {
        this.hostname = hostname;
        this.port = port;
        this.type = type;
        initConnection();
    }

    private void initConnection() {
        try {
            switch (type) {
                case TCP:
                    tcpChannel = SocketChannel.open();
                    tcpChannel.connect(new InetSocketAddress(hostname, port));
                    break;
                case UDP:
                    udpChannel = DatagramChannel.open();
                    udpChannel.connect(new InetSocketAddress(hostname, port));
                    break;
                default:
                    tcpChannel = SocketChannel.open();
                    tcpChannel.connect(new InetSocketAddress(hostname, port));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean connect() {
        switch (type) {
            case TCP:
                return tcpChannel.isOpen() && tcpChannel.isConnected();
            case UDP:
                return udpChannel.isOpen() && udpChannel.isConnected();
            default:
                return false;
        }
    }

    public void disconnect() {
        try {
            switch (type) {
                case TCP:
                    if (tcpChannel != null) {
                        tcpChannel.close();
                    }
                    break;
                case UDP:
                    if (udpChannel != null) {
                        udpChannel.close();
                    }
                    break;
                default:
                    if (tcpChannel != null) {
                        tcpChannel.close();
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(byte[] data) throws IOException {
        ByteBuffer buffer = ByteBuffer.wrap(data);
        switch (type) {
            case TCP:
                while (buffer.hasRemaining()) {
                    tcpChannel.write(buffer);
                }
                break;
            case UDP:
                udpChannel.send(ByteBuffer.wrap(data), new InetSocketAddress(hostname, port));
                break;
            default:
                throw new IllegalStateException("Invalid connector type");
        }
    }

    public int[] readResponse() throws IOException {
        switch (type) {
            case TCP:
                return readTCPResponse();
            case UDP:
                return readUDPResponse();
            default:
                throw new IllegalArgumentException("Unsupported connector type");
        }
    }

    public int[] readTCPResponse() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int bytesRead = tcpChannel.read(buffer);
        if (bytesRead == -1) {
            throw new IOException("End of stream reached");
        }
        buffer.flip();
        int[] response = new int[buffer.remaining()];
        for (int i = 0; buffer.hasRemaining(); i++) {
            response[i] = buffer.get() & 0xFF;
        }
        return response;
    }

    private int[] readUDPResponse() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        InetSocketAddress address = (InetSocketAddress) udpChannel.receive(buffer);
        buffer.flip();
        int[] response = new int[buffer.remaining()];
        for (int i = 0; buffer.hasRemaining(); i++) {
            response[i] = buffer.get() & 0xFF;
        }
        return response;
    }

    public static enum ConnectorType {
        TCP, UDP
    }

    public int getPort() {
        switch (type) {
            case TCP:
                return tcpChannel.socket().getPort();
            case UDP:
                return udpChannel.socket().getLocalPort();
            default:
                return -1;
        }
    }

    public InetAddress getAddress() {
        switch (type) {
            case TCP:
                return ((InetSocketAddress) tcpChannel.socket().getLocalSocketAddress()).getAddress();
            case UDP:
                return ((InetSocketAddress) udpChannel.socket().getLocalSocketAddress()).getAddress();
            default:
                return null;
        }
    }

    public SocketChannel getTCPSocket() {
        if (type == ConnectorType.TCP) {
            return tcpChannel;
        } else {
            return null;
        }
    }

    public DatagramChannel getUDPSocket() {
        if (type == ConnectorType.UDP) {
            return udpChannel;
        } else {
            return null;
        }
    }
}