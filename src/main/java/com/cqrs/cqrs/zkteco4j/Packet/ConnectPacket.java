package com.cqrs.cqrs.zkteco4j.Packet;

public class ConnectPacket extends ZKPacket{
    public ConnectPacket() {
        super((short) 1000, (short) 0, (short) 0, new byte[0]);
    }
}


