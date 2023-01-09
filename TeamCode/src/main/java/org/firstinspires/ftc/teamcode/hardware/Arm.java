package org.firstinspires.ftc.teamcode.hardware;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class Arm { // TODO make sure the correct arm is mirrored (check todo comment below somewhere ;)
    private Servo pivotRight;
    private Servo pivotLeft;

    public static double maxPos = 0.8;
    public static double minPos = 0.05;
    public static double intake = 0.75; // up on joystick
    public static double score = 0.1; // down on joystick
    public static double manualSpeed = 0.025;
    private double target = 0.1;

    public Arm(HardwareMap hardwareMap) {
        pivotRight = hardwareMap.get(Servo.class, "pivotRight");
        pivotLeft = hardwareMap.get(Servo.class, "pivotLeft");

        goToIntake();
    }

    public void increaseTarget(double increase) {
        target -= (increase * manualSpeed);
        target = Math.min(maxPos, Math.max(minPos, target));
    }

    public void goToScore() {
        target = score;
    }

    public void goToIntake() {
        target = intake;
    }

    public void update() {
        // TODO Figure out which one (left or right) needs to be mirrored
        pivotRight.setPosition(target);
        pivotLeft.setPosition(1-target);
    }

    public String getTelemetry() {
        return "";
    }
}
