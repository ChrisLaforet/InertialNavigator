package com.chrislaforetsoftware.control;

import com.chrislaforetsoftware.device.I2CBuilder;
import com.chrislaforetsoftware.device.PressureSensor;

public class PressureSensorController implements IController {

    private PressureSensor pressureSensor;

    public PressureSensorController(PressureSensor pressureSensor) {
        this.pressureSensor = pressureSensor;
    }

    @Override
    public I2CBuilder getI2CBuilder() {
        return pressureSensor;
    }
}
