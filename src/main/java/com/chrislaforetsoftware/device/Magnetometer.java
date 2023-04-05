package com.chrislaforetsoftware.device;

public class Magnetometer extends I2CBuilder {

    public static final int  LIS3MDL_WHO_AM_I =  0x0F;

    public static final int LIS3MDL_CTRL_REG1 = 0x20;
    public static final int LIS3MDL_CTRL_REG2 = 0x21;
    public static final int LIS3MDL_CTRL_REG3 = 0x22;
    public static final int LIS3MDL_CTRL_REG4 = 0x23;
    public static final int LIS3MDL_CTRL_REG5 = 0x24;

    public static final int LIS3MDL_STATUS_REG = 0x27;

    public static final int LIS3MDL_OUT_X_L = 0x28;
    public static final int LIS3MDL_OUT_X_H = 0x29;
    public static final int LIS3MDL_OUT_Y_L = 0x2A;
    public static final int LIS3MDL_OUT_Y_H = 0x2B;
    public static final int LIS3MDL_OUT_Z_L = 0x2C;
    public static final int LIS3MDL_OUT_Z_H = 0x2D;

    public static final int LIS3MDL_TEMP_OUT_L = 0x2E;
    public static final int LIS3MDL_TEMP_OUT_H = 0x2F;

    public static final int LIS3MDL_INT_CFG = 0x30;
    public static final int LIS3MDL_INT_SRC = 0x31;
    public static final int LIS3MDL_INT_THS_L = 0x32;
    public static final int LIS3MDL_INT_THS_H = 0x33;

    public Magnetometer(int busNumber, int address) {
        super(busNumber, address);
    }

    public String getDeviceName() {
        return "Magnetometer";
    }
}
