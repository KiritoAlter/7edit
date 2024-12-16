package com.hl7soft.sevenedit.util.licapi.util;

public class Crc8Util {
    private static final byte[] CRC8_TABLE = new byte[] {
            0, 7, 14, 9, 28, 27, 18, 21, 56, 63,
            54, 49, 36, 35, 42, 45, 112, 119, 126, 121,
            108, 107, 98, 101, 72, 79, 70, 65, 84, 83,
            90, 93, -32, -25, -18, -23, -4, -5, -14, -11,
            -40, -33, -42, -47, -60, -61, -54, -51, -112, -105,
            -98, -103, -116, -117, -126, -123, -88, -81, -90, -95,
            -76, -77, -70, -67, -57, -64, -55, -50, -37, -36,
            -43, -46, -1, -8, -15, -10, -29, -28, -19, -22,
            -73, -80, -71, -66, -85, -84, -91, -94, -113, -120,
            -127, -122, -109, -108, -99, -102, 39, 32, 41, 46,
            59, 60, 53, 50, 31, 24, 17, 22, 3, 4,
            13, 10, 87, 80, 89, 94, 75, 76, 69, 66,
            111, 104, 97, 102, 115, 116, 125, 122, -119, -114,
            -121, Byte.MIN_VALUE, -107, -110, -101, -100, -79, -74, -65, -72,
            -83, -86, -93, -92, -7, -2, -9, -16, -27, -30,
            -21, -20, -63, -58, -49, -56, -35, -38, -45, -44,
            105, 110, 103, 96, 117, 114, 123, 124, 81, 86,
            95, 88, 77, 74, 67, 68, 25, 30, 23, 16,
            5, 2, 11, 12, 33, 38, 47, 40, 61, 58,
            51, 52, 78, 73, 64, 71, 82, 85, 92, 91,
            118, 113, 120, Byte.MAX_VALUE, 106, 109, 100, 99, 62, 57,
            48, 55, 34, 37, 44, 43, 6, 1, 8, 15,
            26, 29, 20, 19, -82, -87, -96, -89, -78, -75,
            -68, -69, -106, -111, -104, -97, -118, -115, -124, -125,
            -34, -39, -48, -41, -62, -59, -52, -53, -26, -31,
            -24, -17, -6, -3, -12, -13 };

    public static byte update(byte data, byte crc) {
        return CRC8_TABLE[crc ^ data];
    }

    public static byte updateBlock(byte[] data, int len, byte crc) {
        for (int i = 0; i < len; i++)
            crc = CRC8_TABLE[crc ^ data[i]];
        return crc;
    }

    public static byte calculate(byte[] data, int len) {
        byte crc = 0;
        for (int i = 0; i < len; i++)
            crc = CRC8_TABLE[(crc ^ data[i]) & 0xFF];
        return crc;
    }

    public static byte calculate(byte[] data) {
        byte crc = 0;
        for (int i = 0; i < data.length; i++)
            crc = CRC8_TABLE[(crc ^ data[i]) & 0xFF];
        return crc;
    }

    public static void main(String[] args) {
        try {
            String str = "This is sample text!";
            byte crc = calculate(str.getBytes());
            System.out.println("CRC: " + crc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
