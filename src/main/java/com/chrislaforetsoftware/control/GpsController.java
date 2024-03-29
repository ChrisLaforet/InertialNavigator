package com.chrislaforetsoftware.control;

import com.chrislaforetsoftware.device.Gps;
import com.chrislaforetsoftware.device.I2CBuilder;
import com.chrislaforetsoftware.device.I2CSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Read about DDC in https://ozzmaker.com/wp-content/uploads/2019/09/u-blox8-M8_ReceiverDescrProtSpec_UBX-13003221_Public.pdf
//
//
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

public class GpsController implements IController {

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

            int bytesAvailable = gps.readUnsignedInt16From(Gps.DDC_NUMBER_BYTES_AVAILABLE_MSB);
System.err.println("GPS Available = " + bytesAvailable);
            while (true) {
                byte value = gps.readByteFrom(Gps.DDC_DATASTREAM);
 System.err.println("GPS READ=" + ((int)value & 0xff) + " (" + (char)(value & 0xff) + ")");
 // i2cget -y 1 0x42 0xff
                if (value == '\n') {
                    break;
                } else if (value == Gps.NOTHING_TO_READ) {
                    return Optional.empty();
                } else {
                    sb.append((char)value);
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
