package org.firstinspires.ftc.teamcode.hardware;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class DualServoClaw {
    private Servo left;
    private Servo right;

    public static double leftOpen = 0.6;
    public static double leftClosed = 0.4;
    public static double strongLeftClosed = 0.35;
    public static double rightOpen = 0.3;
    public static double rightClosed = 0.5;
    public static double strongRightClosed = 0.55;

    private boolean isOpen = false;
    private boolean isStrong = false; // by default not strong close

    public DualServoClaw(HardwareMap hardwareMap) {
        left = hardwareMap.get(Servo.class, "leftClaw");
        right = hardwareMap.get(Servo.class, "rightClaw");

        strongClose();
    }

    public void toggle(boolean strong) {
        isOpen = !isOpen;
        if (strong) {
            isStrong = true;
        } else {
            isStrong = false;
        }
    }

    public void update() {
        if (isOpen) {
//            isStrong = false;
            open();
        } else {
            if (isStrong) {
                strongClose();
            } else {
                close();
            }
        }
    }

    public void close() {
        left.setPosition(leftClosed);
        right.setPosition(rightClosed);
        isOpen = false;
    }

    public void strongClose() {
        left.setPosition(strongLeftClosed);
        right.setPosition(strongRightClosed);
        isStrong = true;
        isOpen = false;
    }

    public void open() {
        left.setPosition(leftOpen);
        right.setPosition(rightOpen);
        isOpen = true;
    }

    public String getTelemetry() {
        return "Left: "+left.getPosition()+" Right: "+right.getPosition();
    }
}
