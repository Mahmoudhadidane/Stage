package com.cqrs.cqrs.zkteco4j.Packet;

public class GetReadSizes extends ZKPacket {
    private static final int CMD_GET_FREE_SIZES = 50;

    public GetReadSizes(short sessionId, short replyNumber) {
        super((short) CMD_GET_FREE_SIZES, sessionId, replyNumber, new byte[0]);
    }
}








//    private static final int CMD_GET_FREE_SIZES = 50;
//
//    private short sessionId;
//    private short replyId;
//
//    public GetReadSizes(short sessionId, short replyId) {
//        this.sessionId = sessionId;
//        this.replyId = replyId;
//    }
//
//    public byte[] getRawPacket(boolean withHeader) {
//        byte[] packet = new byte[8];
//
//        // Set session ID and reply ID
//        packet[0] = (byte) (sessionId & 0xFF);
//        packet[1] = (byte) ((sessionId >> 8) & 0xFF);
//        packet[2] = (byte) (replyId & 0xFF);
//        packet[3] = (byte) ((replyId >> 8) & 0xFF);
//
//        // Set command code
//        packet[4] = (byte) (CMD_GET_FREE_SIZES & 0xFF);
//        packet[5] = (byte) ((CMD_GET_FREE_SIZES >> 8) & 0xFF);
//
//        // Additional packet construction based on protocol (if required)
//        packet[6] = 0;
//        packet[7] = 0;
//
//        // Optionally prepend with header if needed
//        if (withHeader) {
//            byte[] header = new byte[12];
//            System.arraycopy(packet, 0, header, 0, 8);
//            System.arraycopy(packet, 0, header, 8, 4);
//            return header;
//        }
//
//        return packet;
//    }
//}
