package org.firstinspires.ftc.teamcode.hardware;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Config
public class Rad { // TODO done in theory, but need to get the actual servo positions &&&& do the Sensor stuff
    private Servo rad;

    public enum Position {
        OPEN, CLOSED
    }

    public static double open = 0.49;
    public static double closed = 0.551;
    public boolean isOpen = false;

    public Rad(HardwareMap hardwareMap, Position pos) {
        rad = hardwareMap.get(Servo.class, "RAD");

        if (pos == Position.OPEN) {
            open();
        } else {
            close();
        }
        update();
    }

    public void toggle() {
        // I don't use isOpen != isOpen because I want the open method to be called to start the timeout for the sensor
        if (isOpen) {
            close();
        } else {
            open();
        }
    }

    public void update() {

        if (isOpen) {
            rad.setPosition(open);
        } else {
            rad.setPosition(closed);
        }
    }

    public void close() {
        isOpen = false;
    }

    public void open() {
        isOpen = true;
    }

    public String getTelemetry() {
        return "Rad: "+rad.getPosition();
    }
}
