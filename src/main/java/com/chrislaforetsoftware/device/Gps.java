package com.chrislaforetsoftware.device;

public class Gps extends I2CBuilder {

    public Gps(int busNumber, int address) {
        super(busNumber, address);
    }

    public String getDeviceName() {
        return "GPS";
    }
}
