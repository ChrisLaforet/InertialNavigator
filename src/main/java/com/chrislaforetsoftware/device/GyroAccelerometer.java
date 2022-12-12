package com.chrislaforetsoftware.device;

public class GyroAccelerometer extends I2CBuilder {

    public GyroAccelerometer(int address) {
        super(address);
    }

    public String getDeviceName() {
        return "Gyro+Accelerometer";
    }
}
