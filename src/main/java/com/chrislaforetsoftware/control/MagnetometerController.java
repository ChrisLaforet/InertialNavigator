package com.chrislaforetsoftware.control;

import com.chrislaforetsoftware.device.I2CBuilder;
import com.chrislaforetsoftware.device.Magnetometer;

import java.util.ArrayList;
import java.util.List;

public class MagnetometerController {

    public enum ScaleRange {
        Range4Gauss,
        Range8Gauss,
        Range12Gauss,
        Range16Gauss
    };

    public enum DataRate {
        Data5Hz,
        Data10Hz,
        Data20Hz,
        Data40Hz,
        Data80Hz
    };

    public enum Status {
        DataOverrun,
        ZDataOverrun,
        YDataOverrun,
        XDataOverrun,
        DataAvailable,
        ZDataAvailable,
        YDataAvailable,
        XDataAvailable
    }

    //https://ozzmaker.com/wp-content/uploads/2020/08/lis3mdl.pdf
    private Magnetometer magnetometer;

    public MagnetometerController(Magnetometer magnetometer) {
        this.magnetometer = magnetometer;
        enableLowPowerAndSensitivity();
    }

    public I2CBuilder getI2CBuilder() {
        return magnetometer;
    }

    public void setScaleRangeTo(ScaleRange scaleRange) {
        magnetometer.writeByteTo((byte)(scaleRange.ordinal() << 5), Magnetometer.LIS3MDL_CTRL_REG2);
    }

    public void enableLowPowerAndSensitivity() {
        magnetometer.writeByteTo((byte)0x80, Magnetometer.LIS3MDL_CTRL_REG1);
        magnetometer.writeByteTo((byte)0, Magnetometer.LIS3MDL_CTRL_REG4);

        setDataRate(DataRate.Data5Hz);
    }

    public void enableMediumPowerAndSensitivity() {
        magnetometer.writeByteTo((byte)0xB4, Magnetometer.LIS3MDL_CTRL_REG1);
        magnetometer.writeByteTo((byte)0x4, Magnetometer.LIS3MDL_CTRL_REG4);

        setDataRate(DataRate.Data20Hz);
    }

    public void enableHighPowerAndSensitivity() {
        magnetometer.writeByteTo((byte)0xDC, Magnetometer.LIS3MDL_CTRL_REG1);
        magnetometer.writeByteTo((byte)0x4, Magnetometer.LIS3MDL_CTRL_REG4);

        setDataRate(DataRate.Data80Hz);
    }

    public void setDataRate(DataRate dataRate) {
        byte value = (byte)(magnetometer.readByteFrom(Magnetometer.LIS3MDL_CTRL_REG1) & 0xe3);
        switch (dataRate) {
            case Data5Hz:
                value |= 0xc;
                break;
            case Data10Hz:
                value |= 0X10;
                break;
            case Data20Hz:
                value |= 0x14;
                break;
            case Data40Hz:
                value |= 0x18;
                break;
            case Data80Hz:
                value |= 0x1c;
                break;
        }
        magnetometer.writeByteTo(value, Magnetometer.LIS3MDL_CTRL_REG1);
    }

    public List<Status> getStatus() {
        final List<Status> statuses = new ArrayList<>();
        byte status = magnetometer.readByteFrom(Magnetometer.LIS3MDL_STATUS_REG);
        if ((status & 0x1) != 0) {
            statuses.add(Status.XDataAvailable);
        } else if ((status & 0x2) != 0) {
            statuses.add(Status.YDataAvailable);
        } else if ((status & 0x4) != 0) {
            statuses.add(Status.ZDataAvailable);
        } else if ((status & 0x8) != 0) {
            statuses.add(Status.DataAvailable);
        } else if ((status & 0x10) != 0) {
            statuses.add(Status.XDataOverrun);
        } else if ((status & 0x20) != 0) {
            statuses.add(Status.YDataOverrun);
        } else if ((status & 0x40) != 0) {
            statuses.add(Status.ZDataOverrun);
        } else if ((status & 0x80) != 0) {
            statuses.add(Status.DataOverrun);
        }
        return statuses;
    }

    public int getTemperature() {
        byte tempLow = magnetometer.readByteFrom(Magnetometer.LIS3MDL_TEMP_OUT_L);
        byte tempHigh = magnetometer.readByteFrom(Magnetometer.LIS3MDL_TEMP_OUT_H);
        return tempHigh << 8 | (tempLow & 0xff);
    }
}
