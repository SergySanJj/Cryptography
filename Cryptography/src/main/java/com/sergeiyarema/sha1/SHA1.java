package com.sergeiyarema.sha1;

import java.nio.charset.StandardCharsets;

public class SHA1 {

    public static String encode(String data) {
        return new String(new SHA1().encode(data.getBytes()), StandardCharsets.UTF_8);
    }

    private synchronized byte[] encode(byte[] data) {
        int h0 = 0x67452301;
        int h1 = 0xEFCDAB89;
        int h2 = 0x98BADCFE;
        int h3 = 0x10325476;
        int h4 = 0xC3D2E1F0;

        int origMsgLen = data.length;
        int origBitCount = origMsgLen * 8;

        byte[] appendOneMsg = new byte[origMsgLen + 1];
        System.arraycopy(data, 0, appendOneMsg, 0, origMsgLen);
        appendOneMsg[appendOneMsg.length - 1] = (byte) 0x80;
        int apdOneMsgLen = appendOneMsg.length * 8;

        while (apdOneMsgLen % 512 != 448) {
            apdOneMsgLen += 8;
        }

        byte[] output = fillToNeededLen(origBitCount, appendOneMsg, apdOneMsgLen);

        int size = output.length;
        int numChunks = size * 8 / 512;


        for (int i = 0; i < numChunks; i++) {
            int[] w = getChunks(output, i);
            extendWordCount(w);

            int a = h0;
            int b = h1;
            int c = h2;
            int d = h3;
            int e = h4;
            int f = 0;
            int k = 0;

            for (int j = 0; j < 80; j++) {
                if (j <= 19) {
                    f = (b & c) | ((~b) & d);
                    k = 0x5A827999;
                } else if (j <= 39) {
                    f = b ^ c ^ d;
                    k = 0x6ED9EBA1;
                } else if (j <= 59) {
                    f = (b & c) | (b & d) | (c & d);
                    k = 0x8F1BBCDC;
                } else {
                    f = b ^ c ^ d;
                    k = 0xCA62C1D6;
                }

                int temp = rotateLeft(a, 5) + f + e + k + w[j];
                e = d;
                d = c;
                c = rotateLeft(b, 30);
                b = a;
                a = temp;
            }

            h0 = h0 + a;
            h1 = h1 + b;
            h2 = h2 + c;
            h3 = h3 + d;
            h4 = h4 + e;
        }

        byte[] hash = new byte[20];

        for (int j = 0; j < 4; j++) {
            hash[j] = (byte) ((h0 >>> 24 - j * 8) & 0xFF);
        }

        for (int j = 0; j < 4; j++) {
            hash[j + 4] = (byte) ((h1 >>> 24 - j * 8) & 0xFF);
        }

        for (int j = 0; j < 4; j++) {
            hash[j + 8] = (byte) ((h2 >>> 24 - j * 8) & 0xFF);
        }

        for (int j = 0; j < 4; j++) {
            hash[j + 12] = (byte) ((h3 >>> 24 - j * 8) & 0xFF);
        }

        for (int j = 0; j < 4; j++) {
            hash[j + 16] = (byte) ((h4 >>> 24 - j * 8) & 0xFF);
        }

        return hash;
    }

    private void extendWordCount(int[] w) {
        // extend 16 words into 80 words
        for (int j = 16; j < 80; j++) {
            w[j] = rotateLeft(w[j - 3] ^ w[j - 8] ^ w[j - 14] ^ w[j - 16], 1);
        }
    }

    private int[] getChunks(byte[] output, int i) {
        int[] w = new int[80];
        // divide into 16 32 bit words
        for (int j = 0; j < 16; j++) {
            w[j] = ((output[i * 512 / 8 + 4 * j] << 24) & 0xFF000000) |
                    ((output[i * 512 / 8 + 4 * j + 1] << 16) & 0x00FF0000);
            w[j] |= ((output[i * 512 / 8 + 4 * j + 2] << 8) & 0xFF00) |
                    (output[i * 512 / 8 + 4 * j + 3] & 0xFF);
        }
        return w;
    }

    private static byte[] fillToNeededLen(long origBitCount, byte[] appendOneMsg, int apdOneMsgLen) {
        byte[] appendZerosMsg = new byte[apdOneMsgLen / 8];
        System.arraycopy(appendOneMsg, 0, appendZerosMsg, 0, appendOneMsg.length);

        byte[] output = new byte[appendZerosMsg.length + 8];

        for (int i = 0; i < 8; i++) {
            output[output.length - 1 - i] = (byte) ((origBitCount >>> (8 * i)) & 0xFF);
        }
        System.arraycopy(appendZerosMsg, 0, output, 0, appendZerosMsg.length);
        return output;
    }

    private static int rotateLeft(int n, int d) {
        return (n << d) | (n >>> (32 - d));
    }
}
