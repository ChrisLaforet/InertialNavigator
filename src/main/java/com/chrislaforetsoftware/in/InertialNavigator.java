package com.chrislaforetsoftware.in;


import com.chrislaforetsoftware.control.GpsController;
import com.chrislaforetsoftware.control.MagnetometerController;
import com.chrislaforetsoftware.device.Gps;
import com.chrislaforetsoftware.device.GyroAccelerometer;
import com.chrislaforetsoftware.device.Magnetometer;

import java.util.Optional;

public class InertialNavigator {

    public static final int GYRO_ACCELEROMETER_ADDRESS = 0x6a;
    public static final int MAGNETOMETER_ADDRESS = 0x1c;
    public static final int PRESSURE_SENSOR_ADDRESS = 0x77;
    public static final int GPS_ADDRESS = 0x42;     // not showing on bus yet

    public static final int BUS_CONTROLLER_ZERO = 0;
    public static final int BUS_CONTROLLER_ONE = 1;

    public static void main(String [] args) {
        //System.out.println("Hello World");

        // TODO: Permit selection of the BUS in args

        final Magnetometer magnetometer = new Magnetometer(BUS_CONTROLLER_ONE, MAGNETOMETER_ADDRESS);
        final GyroAccelerometer accelerometer = new GyroAccelerometer(BUS_CONTROLLER_ONE, GYRO_ACCELEROMETER_ADDRESS);
        final Gps gps = new Gps(BUS_CONTROLLER_ONE, GPS_ADDRESS);
        System.out.println(magnetometer.getDetails());
        System.out.println("\r\n");
        System.out.println(accelerometer.getDetails());
        System.out.println("\r\n");
        System.out.println(gps.getDetails());

        final MagnetometerController magnetometerController = new MagnetometerController(magnetometer);
        System.out.println("Magnetometer status: " + magnetometerController.getStatus());
        System.out.println("Magnetometer temp: " + magnetometerController.getTemperature());

        final GpsController gpsController = new GpsController(gps);
        for (int count = 0; count < 24; count++) {
            final Optional<String> gpsString = gpsController.readLine();
            if (gpsString.isPresent()) {
                System.out.println("GPS data: " + gpsString.get());
            }
        }
    }
}
