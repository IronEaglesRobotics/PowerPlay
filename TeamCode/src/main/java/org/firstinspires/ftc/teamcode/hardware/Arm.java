package org.firstinspires.ftc.teamcode.hardware;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class Arm { // TODO make sure the correct arm is mirrored (check todo comment below somewhere ;)
    private Servo pivotRight;
    private Servo pivotLeft;

    public static double maxPos = 0.92;
    public static double minPos = 0.08;
    public static double intake = 0.1; // up on joystick
    public static double score = 0.9; // down on joystick
    public static double manualSpeed = 0.001;
    public static double armOffset = -0.006;

    private double target = intake;
    private double currentTarget = target;

    private static double speed = 0.001;

    public Arm(HardwareMap hardwareMap) {
        pivotRight = hardwareMap.get(Servo.class, "pivotRight");
        pivotLeft = hardwareMap.get(Servo.class, "pivotLeft");

        goToIntake();
    }

    public void increaseTarget(double increase) {
        target += (increase * manualSpeed);
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

        // current target slowly moves to target so the servos move slower
        if (currentTarget < target) {
            currentTarget += speed;
        } else if (currentTarget > target) {
            currentTarget -= speed;
        }

        pivotRight.setPosition(currentTarget);
        pivotLeft.setPosition(1+armOffset-currentTarget);
    }

    public String getTelemetry() {
        return "";
    }
}
