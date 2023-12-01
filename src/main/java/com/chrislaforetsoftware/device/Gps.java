package com.chrislaforetsoftware.device;

import com.chrislaforetsoftware.device.ublox.Checksum;

public class Gps extends I2CBuilder {

    public static final int DDC_NUMBER_BYTES_AVAILABLE_MSB = 0xfd;
    public static final int DDC_NUMBER_BYTES_AVAILABLE_LSV = 0xfe;
    public static final int DDC_DATASTREAM = 0xff;

    public static final int DDC_WRITE_ADDRESS = 0xff;

    public static final byte PORT_CONFIG_BYTE_0 = (byte)0xb5;
    public static final byte PORT_CONFIG_BYTE_1 = (byte)0x62;
    public static final byte PORT_CONFIG_CLASS = (byte)0x6;
    public static final byte PORT_CONFIG_ID = 0;
    private static final int PORT_CONFIG_MESSAGE_LENGTH = 1;
    private static final int PORT_CONFIG_TOTAL_MESSAGE_LENGTH = PORT_CONFIG_MESSAGE_LENGTH + 8;

    private static final long HALF_SECOND = 500;

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
        sleepFor(HALF_SECOND);

        // send configuration
        byte[] messageBuffer = new byte[PORT_CONFIG_TOTAL_MESSAGE_LENGTH];
        messageBuffer[0] = PORT_CONFIG_BYTE_0;
        messageBuffer[1] = PORT_CONFIG_BYTE_1;
        messageBuffer[2] = PORT_CONFIG_CLASS;
        messageBuffer[3] = PORT_CONFIG_ID;
        messageBuffer[4] = (byte)PORT_CONFIG_MESSAGE_LENGTH & 0xff;
        messageBuffer[5] = (byte)(PORT_CONFIG_MESSAGE_LENGTH >> 8) & 0xff
        messageBuffer[6] = 0;
        final Checksum checksum = Checksum.calculate(messageBuffer, 2, 5);
        messageBuffer[7] = (byte)checksum.getCkA();
        messageBuffer[8] = (byte)checksum.getCkB();

        writeBytesTo(messageBuffer, messageBuffer.length, );

        // send configuration save
    }

    // Checksum is defined on page 135/136 of u-blox8 manual
}
