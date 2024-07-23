package com.cqrs.cqrs.zkteco4j.terminal;

import com.cqrs.cqrs.zkteco4j.Packet.*;
import com.cqrs.cqrs.zkteco4j.commands.*;
import com.cqrs.cqrs.zkteco4j.utils.HexUtils;
import com.cqrs.cqrs.zkteco4j.utils.SecurityUtils;
import com.cqrs.cqrs.zkteco4j.cnn.Connector;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;



import static java.util.Arrays.*;

public class ZKTerminal {
    private final Connector connector;
    private int sessionId;
    private short replyId;
    private short replyNo;

    public ZKTerminal(String hostname, int port, Connector.ConnectorType type) {
        this.connector = new Connector(hostname, port, type);
    }

    public ZKCommandReply connect() throws IOException {
        sessionId = 0;
        replyNo = 0;

        byte[] toSend = new ConnectPacket().getRawPacket(false);

        connector.send(toSend);

        replyNo++;

        int[] response = connector.readResponse();

        CommandReplyCode replyCode = CommandReplyCode.decode(response[8] + (response[9] * 0x100));

        sessionId = (response[12] + (response[13] * 0x100));
        int replyId = response[14] + (response[15] * 0x100);

        int[] payloads = new int[response.length - 16];

        System.arraycopy(response, 16, payloads, 0, payloads.length);

        return new ZKCommandReply(replyCode, sessionId, replyId, payloads);
    }

    public ZKCommandReply connectAuth(int comKey) throws IOException {
        byte[] key = SecurityUtils.authKey(comKey, sessionId, 50);//supp50
        byte[] toSend = new ConnectAuthPacket(sessionId, replyNo, key).getRawPacket(false);

        connector.send(toSend);

        replyNo++;

        int[] response = connector.readResponse();

        CommandReplyCode replyCode = CommandReplyCode.decode(response[8] + (response[9] * 0x100));

        sessionId = (response[12] + (response[13] * 0x100));
        int replyId = response[14] + (response[15] * 0x100);

        int[] payloads = new int[response.length - 16];

        System.arraycopy(response, 16, payloads, 0, payloads.length);

        return new ZKCommandReply(replyCode, sessionId, replyId, payloads);
    }


    public boolean establishFullConnection(int comKey) {
        if (connector.connect()) {
            try {
                connect();
                connectAuth(comKey);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public void getTime() throws IOException {
        if (establishFullConnection(0)) {
            GetTime getTimePacket = new GetTime((short) sessionId, (short) replyNo);
            byte[] toSend = getTimePacket.getRawPacket(false);

            connector.send(toSend);
            replyNo++;

            int[] response = connector.readResponse();

            //System.out.println("Response received: " + Arrays.toString(response));

            try {
                GetTimeReply getTimeReply = new GetTimeReply(CommandReplyCode.decode(response[8] + (response[9] * 0x100)), sessionId, replyNo, copyOfRange(response, 16, response.length));
                Date deviceDate = getTimeReply.getDeviceDate();

                // Format DATE
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedDate = dateFormat.format(deviceDate);

                System.out.println("Date and Time on Device: " + formattedDate);
            } catch (ParseException e) {
                e.printStackTrace();
                System.out.println("Failed to parse the device date.");
            }
        } else {
            System.out.println("Error establishing connection.");
        }
    }

    public void getDeviceSerialNumber() throws IOException {
        if (establishFullConnection(0)) {
            GetDeviceSerialNumber getDeviceSerialNumberPacket = new GetDeviceSerialNumber((short) sessionId, (short) replyNo);
            byte[] toSend = getDeviceSerialNumberPacket.getRawPacket(false);

            connector.send(toSend);
            replyNo++;

            int[] byteResponse = connector.readResponse();


            if (byteResponse != null && byteResponse.length >= 16) {
                //System.out.println("Hexadecimal Response: " + HexUtils.byteArrayToHex(byteResponse));

                CommandReplyCode replyCode = CommandReplyCode.decode(byteResponse[8] + (byteResponse[9] * 0x100));
                GetDeviceSerialNumberReply getDeviceSerialNumberReply = new GetDeviceSerialNumberReply(
                        replyCode, sessionId, replyNo, copyOfRange(byteResponse, 16, byteResponse.length));
                String deviceSerialNumber = getDeviceSerialNumberReply.getDeviceSerialNumber();

                System.out.println("Device Serial Number: " + deviceSerialNumber);
            } else {
                System.out.println("Invalid response received.");
            }
        } else {
            System.out.println("Error establishing connection.");
        }
    }

    public void getReadSizes() throws IOException {
        GetReadSizes command = new GetReadSizes((short) sessionId, replyNo);
        byte[] response = sendCommand(command);

        if (response != null) {
            GetReadSizesReply reply = new GetReadSizesReply(response);
            System.out.println("Users: " + reply.users);
            System.out.println("Fingers: " + reply.fingers);
            System.out.println("Records: " + reply.records);
            System.out.println("Faces: " + reply.faces);
        } else {
            throw new IOException("Failed to read sizes");
        }
    }

    private byte[] sendCommand(ZKPacket command) throws IOException {
        // Convertir la commande en bytes et l'envoyer à l'appareil
        byte[] toSend = command.getRawPacket(false);
        connector.send(toSend);
        replyNo++;

        int[] response = connector.readResponse();
        // Vérifier la réponse et retourner les bytes utiles
        if (response != null && response.length >= 16) {
            byte[] payload = new byte[response.length - 16];
            for (int i = 0; i < payload.length; i++) {
                payload[i] = (byte) response[16 + i];
            }
            return payload;
        } else {
            System.out.println("Invalid response received: " + Arrays.toString(response));
            return null;
        }
    }


}


