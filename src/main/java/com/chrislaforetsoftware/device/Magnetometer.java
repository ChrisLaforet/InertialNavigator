package com.chrislaforetsoftware.device;

public class Magnetometer extends I2CBuilder {

    public Magnetometer(int address) {
        super(address);
    }

    public String getDeviceName() {
        return "Magnetometer";
    }
}
