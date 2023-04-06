package com.chrislaforetsoftware.device;

import com.diozero.api.I2CDevice;

public abstract class I2CBuilder {

    private I2CDevice device;
    private int address;

    public I2CBuilder(int busNumber, int address) {
        this.address = address;
        I2CDevice.Builder builder = I2CDevice.builder(address).setController(busNumber);
        device = builder.build();
    }

    public abstract String getDeviceName();

    public String getDetails() {
        final StringBuffer sb = new StringBuffer();
        sb.append("Device type=")
            .append(getDeviceName() + "\r\n")
            .append("AddressSize = ")
            .append(device.getAddressSize() + "\r\n")
            .append("Address = ")
            .append(device.getAddress())
            .append(" (0x" + Integer.toHexString(device.getAddress()) + ")\r\n")
            .append("Controller = ")
            .append(device.getController() + "\r\n")
            .append("Byte order = ")
            .append(device.getByteOrder().toString() + "\r\n");
        return sb.toString();
    }

    public void sleepFor(long msec) {
        try {
            Thread.sleep(msec);
        } catch (InterruptedException ie) {
            // do nothing for now
        }
    }

    public byte readByte() {
        return device.readByteData(address);
    }

    public byte readByteFrom(int register) {
        return device.readByteData(register);
    }
    public void writeByte(byte value) {
        device.writeByte(value);
    }

    public void writeByteTo(byte value, int register) {
        device.writeByteData(value, register);
    }

    public int readUnsignedInt16From(int register) {
        byte lsb = readByteFrom(register);
        byte msb = readByteFrom(register + 0x1);
        return (msb << 0x08) | (lsb & 0xff);
    }

    public int readSignedInt16From(int register) {
        int value = readUnsignedInt16From(register);
        if (value > 0x7fff) {
            return value - 0xffff;
        }
        return value;
    }
}
