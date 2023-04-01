package com.chrislaforetsoftware.control;

import com.chrislaforetsoftware.device.Gps;
import com.chrislaforetsoftware.device.I2CBuilder;
import com.chrislaforetsoftware.device.I2CSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GpsController {

    private Gps gps;

    public GpsController(Gps gps) {
        this.gps = gps;
    }

    public I2CBuilder getI2CBuilder() {
        return gps;
    }

    public Optional<String> readLine() {
        try {
            final StringBuilder sb = new StringBuilder();

            while (true) {
                byte b = gps.readByte();
                if (b == '\n') {
                    break;
                } else if (b == (byte)255) {
                    return Optional.empty();
                } else {
                    sb.append((char)b);
                }
            }
            return I2CSupport.validateNMEALine(sb.toString());
        } catch (Exception e) {
            // what to do on error?
        }

        return Optional.empty();
    }

    public String[] readComponents() {
        final Optional<String> line = readLine();
        if (line.isPresent()) {
            return I2CSupport.extractGPSComponents(line.get());
        }
        return new String[0];
    }
}
