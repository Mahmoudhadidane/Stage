package com.cqrs.cqrs.zkteco4j.Packet;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class GetDeviceSerialNumber extends ZKPacket {
    public GetDeviceSerialNumber(short sessionId, short replyNumber) {
        super((short) 11, sessionId, replyNumber, "~SerialNumber".getBytes());
        //super((short) 11, sessionId, replyNumber, new byte[0]);
    }
}


