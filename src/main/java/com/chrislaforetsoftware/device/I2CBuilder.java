package com.chrislaforetsoftware.device;

import com.diozero.api.I2CDevice;

public abstract class I2CBuilder {

    private I2CDevice device;

    public I2CBuilder(int address) {
        I2CDevice.Builder builder = I2CDevice.builder(address);
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
        sb.append("Controller = ");
        sb.append(device.getController() + "\r\n");
        sb.append("Byte order = ");
        sb.append(device.getByteOrder().toString() + "\r\n");
        return sb.toString();
    }

}
