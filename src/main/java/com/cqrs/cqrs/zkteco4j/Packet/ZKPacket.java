package com.cqrs.cqrs.zkteco4j.Packet;

import org.apache.commons.lang3.StringUtils;
import com.cqrs.cqrs.zkteco4j.utils.ArrayUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ZKPacket implements IRequestPacket {

    private byte[] startCode = {0x50, 0x50, (byte) 0x82, 0x7d}; // 4
    private byte[] command; // 2
    private byte[] checksum;  // 2
    private byte[] sessionId; // 2

    private byte[] replyNumber; //2

    private int[] payload = new int[0]; // Variable


    public ZKPacket (short cmd , short sessionId, short replyNumber, byte[] data) {
        this.command = ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN).putShort(cmd).array();
        this.sessionId  = ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN).putShort(sessionId).array();
        this.replyNumber  = ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN).putShort(replyNumber).array();
        if (data != null) {
            this.payload = ArrayUtils.convertByteArrayToIntArray(data);
        }
    }
    public ZKPacket (short cmd , short sessionId, short replyNumber, int[] data) {
        this.command = ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN).putShort(cmd).array();
        this.sessionId  = ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN).putShort(sessionId).array();
        this.replyNumber  = ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN).putShort(replyNumber).array();
        if (data != null) {
            this.payload = data;
        }
    }

    public byte[] getRawPacket(boolean disableStartCode) {
        ByteBuffer buffer = ByteBuffer.allocate(8+ payload.length);
        if (!disableStartCode) {
            buffer = ByteBuffer.allocate(16+ payload.length);
            buffer =buffer.put(startCode,0,4);

        }
        int totalPacketSize = payload.length + 8;
        buffer.putInt(Integer.reverseBytes(totalPacketSize));
        buffer =buffer.put(command,0,2);
        fillChecksumTCP();
        buffer = buffer.put(checksum,0,2);
        buffer =buffer.put(sessionId,0,2);
        buffer = buffer.put(replyNumber,0,2);
        for (int i=0;i<payload.length;i++) {
            buffer.put((byte) (payload[i]));
        }
        return buffer.array();

    }
    private void fillChecksumTCP() {
        int cmd = ByteBuffer.wrap(command).order(ByteOrder.LITTLE_ENDIAN).getShort();
        int session = ByteBuffer.wrap(sessionId).order(ByteOrder.LITTLE_ENDIAN).getShort();
        int reply = ByteBuffer.wrap(replyNumber).order(ByteOrder.LITTLE_ENDIAN).getShort();
        byte[] tmp = new byte[8 + payload.length];
        ByteBuffer buffer = ByteBuffer.wrap(tmp);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.putShort((short) cmd);
        buffer.putShort((short) 0);
        buffer.putShort((short) session);
        buffer.putShort((short) reply);
        for (int value : payload) {
            buffer.put((byte) value);
        }
        checksum = calculateChecksumTCP(tmp);
    }
    private void fillChecksumUDP() {
        int cmd = ByteBuffer.wrap(command).order(ByteOrder.LITTLE_ENDIAN).getShort();
        int session = ByteBuffer.wrap(sessionId).order(ByteOrder.LITTLE_ENDIAN).getShort();
        int reply = ByteBuffer.wrap(replyNumber).order(ByteOrder.LITTLE_ENDIAN).getShort();
        int[] chk = new int[]{cmd, 0, session, reply};
        int[] tmp = new int[chk.length+ payload.length] ;
        int j=0;
        for (int i: payload) {
            tmp[chk.length+j] = i;
            j++;
        }
        tmp[0] = cmd;
        tmp[1] = 0;
        tmp[2] = session;
        tmp[3] = reply;

        int i = calculateChecksumUDP(tmp);

        checksum = ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN).putShort((short) i).array();
    }

    public static byte[] calculateChecksumTCP(byte[] inputPayload) {
        int chk32b = 0;
        int j = 1;

        int[] payload;
        if (inputPayload.length % 2 == 1) {
            payload = new int[inputPayload.length + 1];
            for (int i = 0; i < inputPayload.length; i++) {
                payload[i] = inputPayload[i] & 0xFF;
            }
            payload[payload.length - 1] = 0;
        } else {
            payload = new int[inputPayload.length];
            for (int i = 0; i < inputPayload.length; i++) {
                payload[i] = inputPayload[i] & 0xFF;
            }
        }

        while (j < payload.length) {
            int num_16b = payload[j - 1] + (payload[j] << 8);
            chk32b = chk32b + num_16b;
            j += 2;
        }

        chk32b = (chk32b & 0xffff) + ((chk32b & 0xffff0000) >> 16);

        int chk_16b = chk32b ^ 0xFFFF;

        return ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN).putShort((short) chk_16b).array();
    }
    public static int calculateChecksumUDP(int[] inputPayload) {
        int[] payload;
        int chk32b = 0;
        int j = 1;

        if (inputPayload.length % 2 == 1) {
            payload = new int[inputPayload.length + 1];
            System.arraycopy(inputPayload, 0, payload, 0, inputPayload.length);
            payload[payload.length - 1] = 0;
        } else {
            payload = inputPayload;
        }

        while (j < payload.length) {
            int num_16b = payload[j - 1] + (payload[j] << 8);
            chk32b = chk32b + num_16b;
            j += 2;
        }

        chk32b = (chk32b & 0xffff) + ((chk32b & 0xffff0000) >> 16);

        int chk_16b = chk32b ^ 0xFFFF;

        return chk_16b;
    }

    public static long makeKey(int key, int sessionId) {
        int k;
        int i;

        short swp;

        k = 0;
        for (i = 0; i < 32; i++) {
            if ((key & (1 << i)) != 0) {
                k = (k << 1 | 1);
            } else {
                k = k << 1;
            }
        }

        k += sessionId;

        String hex = StringUtils.leftPad(Integer.toHexString(k), 8, "0");

        int[] response = new int[4];
        int index = 3;

        while (hex.length() > 0) {
            response[index] = (int) Long.parseLong(hex.substring(0, 2), 16);
            index--;

            hex = hex.substring(2);
        }

        response[0] ^= 'Z';
        response[1] ^= 'K';
        response[2] ^= 'S';
        response[3] ^= 'O';

        long finalKey = response[0] + (response[1] * 0x100) + (response[2] * 0x10000) + (response[3] * 0x1000000);

        swp = (short) (finalKey >> 16);
        finalKey = (finalKey << 16) + swp;

        return finalKey;
    }

    @Override
    public Object getId() {
        return null;
    }

    @Override
    public void setChecksum(int checksum) {

    }



}
