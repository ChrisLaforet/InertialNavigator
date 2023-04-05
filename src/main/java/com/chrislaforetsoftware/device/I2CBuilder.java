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
        sb.append("Device type=");
        sb.append(getDeviceName() + "\r\n");
        sb.append("AddressSize = ");
        sb.append(device.getAddressSize() + "\r\n");
        sb.append("Address = ");
        sb.append(device.getAddress() + "\r\n");
        sb.append(" (0x" + Integer.toHexString(device.getAddress()) + ")");
        sb.append("Controller = ");
        sb.append(device.getController() + "\r\n");
        sb.append("Byte order = ");
        sb.append(device.getByteOrder().toString() + "\r\n");
        return sb.toString();
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
}
