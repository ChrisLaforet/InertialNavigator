package com.chrislaforetsoftware.control;

import com.chrislaforetsoftware.device.Gps;
import com.chrislaforetsoftware.device.I2CBuilder;
import com.chrislaforetsoftware.device.I2CSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GpsController {

    private Gps gps;

    public GpsController(Gps gps) {
        this.gps = gps;
    }

    public I2CBuilder getI2CBuilder() {
        return gps;
    }

    public String[] read() {
        try {
            final StringBuilder sb = new StringBuilder();

            while (true) {
                byte b = gps.readByte();
                if (b == '\n') {
                    break;
                } else if (b == (byte)255) {
                    return new String[0];
                } else {
                    sb.append((char)b);
                }
            }
            return I2CSupport.extractGPSComponents(sb.toString());
        } catch (Exception e) {
            // what to do on error?
        }

        return new String[0];
    }
}
