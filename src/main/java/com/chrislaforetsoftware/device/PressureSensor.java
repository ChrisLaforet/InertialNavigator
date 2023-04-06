package com.chrislaforetsoftware.device;

public class PressureSensor extends I2CBuilder {

    public static final int I2C_ADD_BMP388_AD0_LOW = 0x76;
    public static final int I2C_ADD_BMP388_AD0_HIGH = 0x77;
    public static final int I2C_ADD_BMP388 = I2C_ADD_BMP388_AD0_HIGH;

    public static final int BMP388_REG_ADD_WIA = 0x00;
    public static final int BMP388_REG_VAL_WIA = 0x50;

    public static final int BMP388_REG_ADD_ERR = 0x02;
    public static final int BMP388_REG_VAL_FATAL_ERR = 0x01;
    public static final int BMP388_REG_VAL_CMD_ERR = 0x02;
    public static final int BMP388_REG_VAL_CONF_ERR = 0x04;

    public static final int BMP388_REG_ADD_STATUS = 0x03;
    public static final int BMP388_REG_VAL_CMD_RDY = 0x10;
    public static final int BMP388_REG_VAL_DRDY_PRESS = 0x20;
    public static final int BMP388_REG_VAL_DRDY_TEMP = 0x40;

    public static final int BMP388_REG_ADD_CMD = 0x7E;
    public static final int BMP388_REG_VAL_EXTMODE_EN = 0x34;
    public static final int BMP388_REG_VAL_FIFI_FLUSH = 0xB0;
    public static final int BMP388_REG_VAL_SOFT_RESET = 0xB6;

    public static final int BMP388_REG_ADD_PWR_CTRL = 0x1B;
    public static final int BMP388_REG_VAL_PRESS_EN = 0x01;
    public static final int BMP388_REG_VAL_TEMP_EN = 0x02;
    public static final int BMP388_REG_VAL_NORMAL_MODE = 0x30;

    public static final int BMP388_REG_ADD_PRESS_XLSB = 0x04;
    public static final int BMP388_REG_ADD_PRESS_LSB = 0x05;
    public static final int BMP388_REG_ADD_PRESS_MSB = 0x06;
    public static final int BMP388_REG_ADD_TEMP_XLSB = 0x07;
    public static final int BMP388_REG_ADD_TEMP_LSB = 0x08;
    public static final int BMP388_REG_ADD_TEMP_MSB = 0x09;

    public static final int BMP388_REG_ADD_T1_LSB = 0x31;
    public static final int BMP388_REG_ADD_T1_MSB = 0x32;
    public static final int BMP388_REG_ADD_T2_LSB = 0x33;
    public static final int BMP388_REG_ADD_T2_MSB = 0x34;
    public static final int BMP388_REG_ADD_T3 = 0x35;
    public static final int BMP388_REG_ADD_P1_LSB = 0x36;
    public static final int BMP388_REG_ADD_P1_MSB = 0x37;
    public static final int BMP388_REG_ADD_P2_LSB = 0x38;
    public static final int BMP388_REG_ADD_P2_MSB = 0x39;
    public static final int BMP388_REG_ADD_P3 = 0x3A;
    public static final int BMP388_REG_ADD_P4 = 0x3B;
    public static final int BMP388_REG_ADD_P5_LSB = 0x3C;
    public static final int BMP388_REG_ADD_P5_MSB = 0x3D;
    public static final int BMP388_REG_ADD_P6_LSB = 0x3E;
    public static final int BMP388_REG_ADD_P6_MSB = 0x3F;
    public static final int BMP388_REG_ADD_P7 = 0x40;
    public static final int BMP388_REG_ADD_P8 = 0x41;
    public static final int BMP388_REG_ADD_P9_LSB = 0x42;
    public static final int BMP388_REG_ADD_P9_MSB = 0x43;
    public static final int BMP388_REG_ADD_P10 = 0x44;
    public static final int BMP388_REG_ADD_P11 = 0x45;

    private boolean isBMP388 = false;

    public PressureSensor(int busNumber, int address) {
        super(busNumber, address);
        calibrate();
    }

    @Override
    public String getDeviceName() {
        return "PressureSensor";
    }

    public void calibrate() {
        if (readByteFrom(BMP388_REG_ADD_WIA) == BMP388_REG_VAL_WIA) {
            isBMP388 = true;

            if ((readByteFrom(BMP388_REG_ADD_STATUS) & BMP388_REG_VAL_CMD_RDY) != 0) {
                writeByteTo((byte) BMP388_REG_VAL_SOFT_RESET, BMP388_REG_ADD_CMD);
            }
            sleepFor(10);
        } else {
            writeByteTo((byte)(BMP388_REG_VAL_PRESS_EN | BMP388_REG_VAL_TEMP_EN | BMP388_REG_VAL_NORMAL_MODE), BMP388_REG_ADD_PWR_CTRL);
        }

        loadCalibration();
    }

    public void loadCalibration() {
        var t1 = readUnsignedInt16From(BMP388_REG_ADD_T1_LSB);
        var t2 = readUnsignedInt16From(BMP388_REG_ADD_T2_LSB);
        var t3 = readByteFrom(BMP388_REG_ADD_T3);
        var p1 = readSignedInt16From(BMP388_REG_ADD_P1_LSB);
        var p2 = readSignedInt16From(BMP388_REG_ADD_P2_LSB);
        var p3 = readByteFrom(BMP388_REG_ADD_P3);
        var p4 = readByteFrom(BMP388_REG_ADD_P4);
        var p5 = readUnsignedInt16From(BMP388_REG_ADD_P5_LSB);
        var p6 = readUnsignedInt16From(BMP388_REG_ADD_P6_LSB);
        var p7 = readByteFrom(BMP388_REG_ADD_P7);
        var p8 = readByteFrom(BMP388_REG_ADD_P8);
        var p9 = readSignedInt16From(BMP388_REG_ADD_P9_LSB);
        var p10 = readByteFrom(BMP388_REG_ADD_P10);
        var p11 = readByteFrom(BMP388_REG_ADD_P11);
    }
}
