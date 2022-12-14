package com.chrislaforetsoftware.in;


import com.chrislaforetsoftware.device.GyroAccelerometer;
import com.chrislaforetsoftware.device.Magnetometer;

public class InertialNavigator {

    public static final int GYRO_ACCELEROMETER_ADDRESS = 0x6a;
    public static final int MAGNETOMETER_ADDRESS = 0x1c;
    public static final int PRESSURE_SENSOR_ADDRESS = 0x77;
    public static final int GPS_ADDRESS = 0x42;     // not showing on bus yets

    public static void main(String [] args) {
        //System.out.println("Hello World");

        final Magnetometer magnetometer = new Magnetometer(MAGNETOMETER_ADDRESS);
        final GyroAccelerometer accelerometer = new GyroAccelerometer(GYRO_ACCELEROMETER_ADDRESS);
        System.out.println(magnetometer.getDetails());
        System.out.println("\r\n");
        System.out.println(accelerometer.getDetails());
    }
}
