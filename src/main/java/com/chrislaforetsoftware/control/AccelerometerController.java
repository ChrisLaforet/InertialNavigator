package com.chrislaforetsoftware.control;

import com.chrislaforetsoftware.device.GyroAccelerometer;
import com.chrislaforetsoftware.device.I2CBuilder;

public class AccelerometerController implements IController {

    // https://ozzmaker.com/wp-content/uploads/2020/08/lsm6dsl-datasheet.pdf

    private GyroAccelerometer accelerometer;

    public AccelerometerController(GyroAccelerometer accelerometer) {
        this.accelerometer = accelerometer;
    }

    public I2CBuilder getI2CBuilder() {
        return accelerometer;
    }
}
