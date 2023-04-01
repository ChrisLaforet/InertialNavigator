package com.chrislaforetsoftware.control;

import com.chrislaforetsoftware.device.Gps;
import com.chrislaforetsoftware.device.I2CBuilder;
import com.chrislaforetsoftware.device.I2CSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//$GPVTG	Vector track and Speed over the Ground
//$GPGGA	GGA - essential fix data which provide 3D location and accuracy data.
//$GPGLL	GLL - Geographic Latitude and Longitude
//$GPGSA	GSA - details on the nature of the fix. It includes the numbers of the satellites
//$GPGSV	Detailed satelite data
//$GPRMC	RMC - The recommended minimum
//
//# GUIDE
//        # http://ava.upuaut.net/?p=768
//
//        GPSDAT = {
//        'strType': None,
//        'fixTime': None,
//        'lat': None,
//        'latDir': None,
//        'lon': None,
//        'lonDir': None,
//        'fixQual': None,
//        'numSat': None,
//        'horDil': None,
//        'alt': None,
//        'altUnit': None,
//        'galt': None,
//        'galtUnit': None,
//        'DPGS_updt': None,
//        'DPGS_ID': None
//        }

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
