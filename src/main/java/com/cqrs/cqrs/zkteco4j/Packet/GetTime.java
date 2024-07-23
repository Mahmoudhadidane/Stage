package com.cqrs.cqrs.zkteco4j.Packet;

import com.cqrs.cqrs.zkteco4j.commands.CommandCode;

public class GetTime extends ZKPacket {
    public GetTime (int[] key){
        super((short) 201, (short) 0, (short) 0, key);
    }
    public GetTime(short sessionId, short replyNumber) {
        super((short) CommandCode.CMD_GET_TIME.getCode(), sessionId, replyNumber, new byte[0]);
    }
}


