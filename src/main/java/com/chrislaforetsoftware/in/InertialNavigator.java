package com.chrislaforetsoftware.in;


import com.chrislaforetsoftware.device.Accelerometer;
import com.chrislaforetsoftware.device.Gyro;

public class InertialNavigator {

    public static final int GYRO_ADDRESS = 0x6a;
    public static final int ACCELEROMETER_ADDRESS = 0x1c;

    public static void main(String [] args) {
        System.out.println("Hello World");

        final Gyro gyro = new Gyro(GYRO_ADDRESS);
        final Accelerometer accelerometer = new Accelerometer(ACCELEROMETER_ADDRESS);
        System.out.println("GYRO\r\n");
        System.out.println(gyro.getDetails());
        System.out.println("ACCELEROMETER\r\n");
        System.out.println(accelerometer.getDetails());
    }
}
