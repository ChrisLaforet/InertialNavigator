package com.chrislaforetsoftware.device;

public class Gps extends I2CBuilder {

    public Gps(int address) {
        super(address);
    }

    public String getDeviceName() {
        return "GPS";
    }
}
