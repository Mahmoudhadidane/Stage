package com.cqrs.cqrs.zkteco4j.Packet;


public class ConnectAuthPacket extends ZKPacket{
    public ConnectAuthPacket(int[] key) {
        super((short) 1102, (short) 0, (short) 0, key);
    }
    public ConnectAuthPacket(int sessionId, int replyId, byte[] key) {
        super((short) 1102, (short) sessionId, (short) replyId, key);
    }
}
