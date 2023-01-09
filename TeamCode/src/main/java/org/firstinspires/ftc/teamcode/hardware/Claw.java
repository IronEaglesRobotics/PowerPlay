package org.firstinspires.ftc.teamcode.hardware;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Config
public class Claw { // TODO done in theory, but need to get the actual servo positions &&&& do the Sensor stuff
    private Servo pincher;
    private Servo wrist;
    private RevColorSensorV3 sensor;

    public static double pincherOpen = 0.6;
    public static double pincherClosed = 0.4;
    public static double wristUpright = 0.35;
    public static double wristFlipped = 0.35;
    public static double triggerDistance = 20; // mm

    private boolean isOpen = false;
    private boolean isUpright = true;

    public Claw(HardwareMap hardwareMap) {
        pincher = hardwareMap.get(Servo.class, "claw");
        wrist = hardwareMap.get(Servo.class, "wrist");
        sensor = hardwareMap.get(RevColorSensorV3.class, "trigger");
        close();
    }

    public double getTriggerDistance() {
        return sensor.getDistance(DistanceUnit.MM);
    }

    public void toggle() {
        isOpen = !isOpen;
    }

    public void flip() {
        isUpright = !isUpright;
    }

    public void update() {
        if (isUpright) {
            upright();
        } else {
            flipped();
        }

        if (getTriggerDistance() < triggerDistance && isOpen) {
            close();
        }

        if (isOpen) {
            open();
        } else {
            close();
        }
    }

    public void close() {
        pincher.setPosition(pincherClosed);
        isOpen = false;
    }

    public void open() {
        pincher.setPosition(pincherOpen);
        isOpen = true;
    }

    public void upright() {
        wrist.setPosition(wristUpright);
        isUpright = true;
    }

    public void flipped() {
        wrist.setPosition(wristFlipped);
        isUpright = false;
    }

    public String getTelemetry() {
        return "Pincher: "+pincher.getPosition()+"\nRotator: "+wrist.getPosition()+"\nProximitySensor: "+getTriggerDistance();
    }
}
