package com.chrislaforetsoftware.device;

import java.util.Optional;

public class I2CSupport {

    private I2CSupport() {
        // maintains this class as a static class
    }

    private static int countOccurrencesIn(String line, char ch) {
        return line.length() - line.replace("$", "").length();
    }

    public static Optional<String> validateNMEALine(String line) {
        // checks for I2C clock stretching bug in NMEA line
        if (line.length() < 1 ||
                !line.startsWith("$") ||
                countOccurrencesIn(line, '$') != 1 ||
                line.length() >= 84) {
            return Optional.empty();
        }

        for (char ch : line.toCharArray()) {
            int value = (int)ch;
            if (value != 0xd && (value < 32 || value > 122)) {
                return Optional.empty();
            }
        }

        if (line.contains("txbuf")) {
            return Optional.empty();
        }

        // split twice
        String[] parts = line.split("\\*", 2);

        int checkSum = 0;
        for (char ch : parts[0].substring(1).toCharArray()) {       // remove $ and checksum
            checkSum ^= (int)ch;
        }
        int x = Integer.parseInt(parts[1], 16);
        if (checkSum != Integer.parseInt(parts[1], 16)) {
            return Optional.empty();
        }
        return Optional.of(line);
//            gpsChars = ''.join(chr(c) for c in gpsLine)
//        if (gpsChars.find('txbuf') == -1):          # Check #4, skip txbuff allocation error
//                gpsStr, chkSum = gpsChars.split('*',2)  # Check #5 only split twice to avoid unpack error
//                gpsComponents = gpsStr.split(',')
//        chkVal = 0
//        for ch in gpsStr[1:]: # Remove the $ and do a manual checksum on the rest of the NMEA sentence
//        chkVal ^= ord(ch)
//        if (chkVal == int(chkSum, 16)): # Compare the calculated checksum with the one in the NMEA sentence
//        print gpsChars
    }
}
