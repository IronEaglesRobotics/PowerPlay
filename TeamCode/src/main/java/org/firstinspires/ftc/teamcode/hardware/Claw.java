package org.firstinspires.ftc.teamcode.hardware;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class Claw {
    private Servo left;
    private Servo right;

    public static double leftOpen = 0.6;
    public static double leftClosed = 0.4;
    public static double rightOpen = 0.3;
    public static double rightClosed = 0.5;

    private boolean isOpen = false;

    public Claw(HardwareMap hardwareMap) {
        left = hardwareMap.get(Servo.class, "leftClaw");
        right = hardwareMap.get(Servo.class, "rightClaw");

        close();
    }

    public void toggle() {
        if (isOpen) {
            close();
        } else {
            open();
        }
        isOpen = !isOpen;
    }

    public void update() {
        if (isOpen) {
            open();
        } else {
            close();
        }
    }

    public void close() {
        left.setPosition(leftClosed);
        right.setPosition(rightClosed);
        isOpen = false;
    }

    public void open() {
        left.setPosition(leftOpen);
        right.setPosition(rightOpen);
        isOpen = true;
    }

    public String getTelemetry() {
        return "";
    }
}
