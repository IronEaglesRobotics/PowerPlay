package org.firstinspires.ftc.teamcode.hardware;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class Arm {

    public enum Position {
        INTAKE, SCORE, PICKUP, AUTO
    }

    private Servo pivotRight;
    private Servo pivotLeft;

    public static double maxPos = 0.99;
    public static double minPos = 0.055;
    public static double intake = 0.065; // up on joystick
    public static double score = 0.61; // down on joystick
    public static double pickup = 0.9;
    public static double auto = 0.87;

    public final double leftMin = 0.0255;
    public final double leftMax = 0.99;
    public final double rightMin = 0.01;
    public final double rightMax = 0.973;

    public static double[] presets = new double[]{0.92, 0.9, 0.85};
    public int presetIdx = 0;
    public boolean usePresets = false;

    public static double manualSpeed = 0.008;
    public static double armOffset = 0;

    private double target = intake;
    private double currentTarget = target;

    public static double speed = 0.03;

    public static double p = 0.13;
    public static double i = 0.00;
    public static double d = 0.0;
    public static double f = 0;
    public static double pTolerance = 0.01;
    public static PIDController controller = new PIDController(p, i, d);

    public Arm(HardwareMap hardwareMap, Position pos) {
        pivotLeft = hardwareMap.get(Servo.class, "pivotLeft");
        pivotRight = hardwareMap.get(Servo.class, "pivotRight");
//        pivotRight.setDirection(Servo.Direction.REVERSE);

        pivotLeft.scaleRange(leftMin, leftMax);
        pivotRight.scaleRange(rightMin, rightMax);

        if (pos == Position.SCORE) {
            goToScore();
        } else if (pos == Position.PICKUP) {
            goToPickup();
        } else if (pos == Position.AUTO) {
            goToAuto();
        } else if (pos == Position.INTAKE) {
            goToIntake();
        }
        currentTarget = target; // necessary to make sure arm doesn't move violently during init
//        pivotLeft.setPosition(currentTarget);
//        pivotRight.setPosition(currentTarget);
//        update();
    }

    public void increaseTarget(double increase) {
        target += (increase * manualSpeed);
        target = Math.min(maxPos, Math.max(minPos, target));
    }

    public void setTarget(Position pos) {
        if (pos == Arm.Position.INTAKE) {
            target = intake;
        } else if (pos == Arm.Position.SCORE) {
            target = score;
        } else if (pos == Arm.Position.PICKUP) {
            target = pickup;
        } else if (pos == Arm.Position.AUTO) {
            target = auto;
        }
    }

    public void goToScore() {
        target = (usePresets ? presets[presetIdx] : score);
    }

    public void toggleUsePreset() {
        usePresets = !usePresets;
    }
    public void cyclePreset() {
        presetIdx++;
        if (presetIdx >= presets.length) {
            presetIdx = 0;
        }
    }

    public void goToIntake() {
        target = intake;
    }

    public void goToPickup() {
        target = pickup;
    }
    public void goToAuto() {
        target = auto;
    }

    public void update() {
        pivotLeft.scaleRange(leftMin, leftMax);
        pivotRight.scaleRange(rightMin, rightMax);

        double pid;
        controller.setPID(p, i, d);
        controller.setTolerance(pTolerance);

        pid = controller.calculate(currentTarget, target);

        // current target slowly moves to target so the servos move slower
        // if close to set point, go there exactly
        if (controller.atSetPoint()) {
            currentTarget = target;
        } else {
            currentTarget += pid;
        }
//        if (currentTarget < target) {
//            currentTarget += pid;
//        } else if (currentTarget > target) {
//            currentTarget -= pid;
//        }

//        pivotLeft.setPosition(target);
//        pivotRight.setPosition(target);
        pivotLeft.setPosition(currentTarget);
        pivotRight.setPosition(currentTarget);

//        pivotLeft.setPosition(1+armOffset);
//        pivotRight.setPosition(currentTarget);
//        pivotLeft.setPosition(1+armOffset-currentTarget);
    }

    public String getTelemetry() {
        return ""+pivotRight.getPosition()+" "+pivotLeft.getPosition();
    }
}
