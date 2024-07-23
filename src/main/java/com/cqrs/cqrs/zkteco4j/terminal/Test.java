package com.cqrs.cqrs.zkteco4j.terminal;

import com.cqrs.cqrs.zkteco4j.cnn.Connector;

import java.io.IOException;

public class Test {

    public static void main(String[] args) {
        ZKTerminal terminal = new ZKTerminal("192.168.1.201", 4370, Connector.ConnectorType.TCP);
        boolean connectionEstablished = terminal.establishFullConnection(0);
        System.out.println("Connection established: " + connectionEstablished);

        if (connectionEstablished) {
            try {
                terminal.getTime();
                terminal.getDeviceSerialNumber();
                terminal.getReadSizes();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
