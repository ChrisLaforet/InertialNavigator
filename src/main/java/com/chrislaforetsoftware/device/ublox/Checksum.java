package com.chrislaforetsoftware.device.ublox;

public class Checksum {
    private int ck_a;
    private int ck_b;

    private Checksum(int ck_a, int ck_b) {
        this.ck_a = ck_a & 0xff;
        this.ck_b = ck_b & 0xff;
    }

    public int getCkA() {
        return ck_a;
    }

    public int getCkB() {
        return ck_b;
    }

    public static Checksum calculate(byte[] message, int startOffset, int length) {
        int ck_a = 0;
        int ck_b = 0;

        for (int offset = 0; offset < length; offset++) {
            ck_a += message[startOffset + offset];
            ck_a &= 0xff;

            ck_b += ck_a;
            ck_b &= 0xff;
        }
    }
}
