package org.firstinspires.ftc.teamcode.hardware;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class Arm {
    private Servo pivotRight;
    private Servo pivotLeft;

    public static double maxPos = 0.98;
    public static double minPos = 0.04;
    public static double intake = 0.04; // up on joystick
    public static double score = 0.9; // down on joystick
    public static double manualSpeed = 0.06;
    public static double armOffset = 0;

    private double target = intake;
    private double currentTarget = target;

    public static double speed = 0.03;

    public static double p = 0.08;
    public static double i = 0.05;
    public static double d = 0.001;
    public static double f = 0;
    public static double pTolerance = 0.001;
    public static PIDController controller = new PIDController(p, i, d);


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
        double pid;
        controller.setPID(p, i, d);
        controller.setTolerance(pTolerance);

        pid = controller.calculate(currentTarget, target);

        // current target slowly moves to target so the servos move slower
        currentTarget += pid;
//        if (currentTarget < target) {
//            currentTarget += pid;
//        } else if (currentTarget > target) {
//            currentTarget -= pid;
//        }

        pivotRight.setPosition(currentTarget);
        pivotLeft.setPosition(1+armOffset-currentTarget);
    }

    public String getTelemetry() {
        return ""+pivotRight.getPosition()+" "+pivotLeft.getPosition();
    }
}
