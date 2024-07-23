package com.cqrs.cqrs.zkteco4j.commands;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class GetReadSizesReply {
    public int users;
    public int fingers;
    public int records;
    public int dummy;
    public int cards;
    public int fingersCap;
    public int usersCap;
    public int recCap;
    public int fingersAv;
    public int usersAv;
    public int recAv;
    public int faces;
    public int facesCap;

    public GetReadSizesReply(byte[] data) {
        //System.out.println("Raw data: " + Arrays.toString(data));

        ByteBuffer buffer = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);

        if (data.length >= 80) {
            users = buffer.getInt(4 * 4);       // 4th int
            fingers = buffer.getInt(6 * 4);     // 6th int
            records = buffer.getInt(8 * 4);     // 8th int
            dummy = buffer.getInt(10 * 4);      // 10th int
            cards = buffer.getInt(12 * 4);      // 12th int
            fingersCap = buffer.getInt(14 * 4); // 14th int
            usersCap = buffer.getInt(15 * 4);   // 15th int
            recCap = buffer.getInt(16 * 4);     // 16th int
            fingersAv = buffer.getInt(17 * 4);  // 17th int
            usersAv = buffer.getInt(18 * 4);    // 18th int
            recAv = buffer.getInt(19 * 4);      // 19th int
        }

        if (data.length >= 92) {
            buffer.position(80);
            faces = buffer.getInt();
            facesCap = buffer.getInt(2 * 4);    // 2nd int from this position
        }
    }
}
