package com.cqrs.cqrs.zkteco4j.utils;

public class ArrayUtils {

    public static int[] convertByteArrayToIntArray(byte[] byteArray) {
        int[] intArray = new int[byteArray.length];
        for (int i = 0; i < byteArray.length; i++) {
            intArray[i] = byteArray[i] & 0xFF;
        }
        return intArray;
    }

    public static byte[] convertIntArrayToByteArray(int[] intArray) {
        byte[] byteArray = new byte[intArray.length];
        for (int i = 0; i < intArray.length; i++) {
            byteArray[i] = (byte) intArray[i];
        }
        return byteArray;
    }
}
