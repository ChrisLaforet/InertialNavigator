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

        // split twice to get $ line and checksum
        String[] parts = line.split("\\*", 2);

        int checkSum = 0;
        for (char ch : parts[0].substring(1).toCharArray()) {       // remove $ and checksum
            checkSum ^= (int)ch;
        }

        if (checkSum != Integer.parseInt(parts[1], 16)) {
            return Optional.empty();
        }
        return Optional.of(line);
    }
}
