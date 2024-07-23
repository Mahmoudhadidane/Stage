package com.cqrs.cqrs.zkteco4j.commands;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class GetDeviceSerialNumberReply extends ZKCommandReply {
    private final String deviceSerialNumber;

    public GetDeviceSerialNumberReply(CommandReplyCode code, int sessionId, int replyId, int[] payload) {
        super(code, sessionId, replyId, payload);

        if (code == CommandReplyCode.CMD_ACK_OK) {
            // Convert int[] to byte[]
            byte[] bytePayload = intArrayToByteArray(payload);

            //System.out.println("Raw byte payload: " + Arrays.toString(bytePayload));

            // Extract the serial number from the payload
            String parameterValue = new String(bytePayload, StandardCharsets.UTF_8);

            //System.out.println("Decoded parameter value: " + parameterValue);

            String[] parts = parameterValue.split("=");
            if (parts.length > 1) {
                this.deviceSerialNumber = parts[1].trim();
            } else {
                this.deviceSerialNumber = "Unknown";
            }
        } else {
            throw new RuntimeException("Unexpected command reply code: " + code);
        }
    }

    private byte[] intArrayToByteArray(int[] intArray) {
        byte[] byteArray = new byte[intArray.length];
        for (int i = 0; i < intArray.length; i++) {
            byteArray[i] = (byte) intArray[i];
        }
        return byteArray;
    }

    public String getDeviceSerialNumber() {
        return deviceSerialNumber;
    }
}
