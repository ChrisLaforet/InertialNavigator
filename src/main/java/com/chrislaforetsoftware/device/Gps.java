package com.chrislaforetsoftware.device;

public class Gps extends I2CBuilder {

    public static final int DDC_NUMBER_BYTES_AVAILABLE_MSB = 0xfd;
    public static final int DDC_NUMBER_BYTES_AVAILABLE_LSV = 0xfe;
    public static final int DDC_DATASTREAM = 0xff;

    public static final int DDC_WRITE_ADDRESS = 0xff;

    public static final byte PORT_CONFIG_BYTE_0 = (byte)0xB5;
    public static final byte PORT_CONFIG_BYTE_1 = (byte)0x62;
    public static final byte PORT_CONFIG_CLASS = (byte)0x6;
    public static final byte PORT_CONFIG_ID = 0;


    public static final byte NOTHING_TO_READ = (byte)0xff;

    public Gps(int busNumber, int address) {
        super(busNumber, address);
        configure();
    }

    public String getDeviceName() {
        return "GPS";
    }

    public void configure() {
        // send 0xff to wake up
        writeByteTo((byte)0xff, DDC_WRITE_ADDRESS);

        // wait 1/2 sec

        // send configuration

        // send configuration save
    }

    // Checksum is defined on page 135/136 of u-blox8 manual
}
