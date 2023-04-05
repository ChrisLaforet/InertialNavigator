package com.chrislaforetsoftware.device;

public class GyroAccelerometer extends I2CBuilder {

    public static final int LSM6DSL_WHO_AM_I = 0x0F;

    public static final int LSM6DSL_RAM_ACCESS = 0x01;
    public static final int LSM6DSL_CTRL1_XL = 0x10;
    public static final int LSM6DSL_CTRL8_XL = 0x17;
    public static final int LSM6DSL_CTRL2_G = 0x11;
    public static final int LSM6DSL_CTRL10_C = 0x19;
    public static final int LSM6DSL_TAP_CFG1 = 0x58;
    public static final int LSM6DSL_INT1_CTRL = 0x0D;
    public static final int LSM6DSL_CTRL3_C = 0x12;
    public static final int LSM6DSL_CTRL4_C = 0x13;

    public static final int LSM6DSL_STEP_COUNTER_L = 0x4B;
    public static final int LSM6DSL_STEP_COUNTER_H = 0x4C;

    public static final int LSM6DSL_OUTX_L_XL = 0x28;
    public static final int LSM6DSL_OUTX_H_XL = 0x29;
    public static final int LSM6DSL_OUTY_L_XL = 0x2A;
    public static final int LSM6DSL_OUTY_H_XL = 0x2B;
    public static final int LSM6DSL_OUTZ_L_XL = 0x2C;
    public static final int LSM6DSL_OUTZ_H_XL = 0x2D;

    public static final int LSM6DSL_OUT_L_TEMP = 0x20;
    public static final int LSM6DSL_OUT_H_TEMP = 0x21;

    public static final int LSM6DSL_OUTX_L_G = 0x22;
    public static final int LSM6DSL_OUTX_H_G = 0x23;
    public static final int LSM6DSL_OUTY_L_G = 0x24;
    public static final int LSM6DSL_OUTY_H_G = 0x25;
    public static final int LSM6DSL_OUTZ_L_G = 0x26;
    public static final int LSM6DSL_OUTZ_H_G = 0x27;

    public static final int LSM6DSL_TAP_CFG = 0x58;
    public static final int LSM6DSL_WAKE_UP_SRC = 0x1B;
    public static final int LSM6DSL_WAKE_UP_DUR = 0x5C;
    public static final int LSM6DSL_FREE_FALL = 0x5D;
    public static final int LSM6DSL_MD1_CFG = 0x5E;
    public static final int LSM6DSL_MD2_CFG = 0x5F;
    public static final int LSM6DSL_TAP_THS_6D = 0x59;
    public static final int LSM6DSL_INT_DUR2 = 0x5A;
    public static final int LSM6DSL_WAKE_UP_THS = 0x5B;
    public static final int LSM6DSL_FUNC_SRC1 = 0x53;
    
    public GyroAccelerometer(int busNumber, int address) {
        super(busNumber, address);
    }

    public String getDeviceName() {
        return "Gyro+Accelerometer";
    }

    public void enableAccelerometer() {

    }

    public void enableGyro() {

    }
}
