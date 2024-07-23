package com.cqrs.cqrs.zkteco4j.Packet;

public interface IRequestPacket {

    byte[] getRawPacket(boolean disableStartCode);

    Object getId();

    void setChecksum(int checksum);

}
