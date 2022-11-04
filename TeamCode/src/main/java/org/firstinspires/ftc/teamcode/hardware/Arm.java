package org.firstinspires.ftc.teamcode.hardware;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Config
public class Arm {
    private DcMotor arm;

    public static double p = 0.0025;
    public static double i = 0;
    public static double d = 0;
    public static double f = 0.04;
    public static PIDController controller = new PIDController(p, i, d);

    public static int targetMin = 0;
    public static int targetMax = 776;
    private int target = 0;
    private double ticksInDegrees = ((537.7*2)*(260/360.0)) / 260.0;

    public static int manualSpeed = 8;

    public Arm(HardwareMap hardwareMap) {
        arm = hardwareMap.get(DcMotor.class, "arm");
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        arm.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void setTarget(int pos) {
        target = Math.min(Math.max(target, targetMin), targetMax);
    }

    public void increaseTarget(double increase) {
        target += (int) (increase * manualSpeed);
        target += increase;
    }

    public void update() {
        controller.setPID(p, i, d);

        int currentPos = arm.getCurrentPosition();
        double pid = controller.calculate(currentPos, target);
        double ff = Math.cos(Math.toRadians((currentPos/ticksInDegrees)-40))*f;
//        double ff = Math.cos(Math.toRadians(target / ticksInDegrees)) * f;

        arm.setPower(pid + ff);
    }

    public String getTelemetry() {
        return String.format("Position: %s\nTarget: %s\nPower: %s", arm.getCurrentPosition(), target, arm.getPower());
    }
}
