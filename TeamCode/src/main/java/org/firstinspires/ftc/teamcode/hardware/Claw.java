package org.firstinspires.ftc.teamcode.hardware;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class Claw {
    private Servo pincher;
    private Servo wrist;
    private RevColorSensorV3 sensor;

    public static double pincherOpen = 0.6;
    public static double pincherClosed = 0.4;
    public static double wristUpright = 0.35;
    public static double wristFlipped = 0.35;
    public static double triggerDistance = 0.01;

    private boolean isOpen = false;
    private boolean isUpright = true;

    public Claw(HardwareMap hardwareMap) {
        pincher = hardwareMap.get(Servo.class, "Claw");
        wrist = hardwareMap.get(Servo.class, "Wrist");
        close();
    }

    public void toggle() {
        isOpen = !isOpen;
    }

    public void flip() {
        isUpright = !isUpright;
    }

    public void update() {
        if (isOpen) {
            open();
        } else {
            close();
        }

        if (isUpright) {
            upright();
        } else {
            flipped();
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
        return "Pincher: "+pincher.getPosition()+"\nRotator: "+wrist.getPosition();
    }
}
