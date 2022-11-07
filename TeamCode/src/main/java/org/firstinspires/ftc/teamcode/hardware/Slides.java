package org.firstinspires.ftc.teamcode.hardware;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Config
public class Slides {
    private DcMotor slide;
    private DcMotor slide2;

    public static double p = 0.004;
    public static double i = 0;
    public static double d = 0;
    public static double f = 0.01;
    public static double pTolerance = 20;
    public static PIDController controller = new PIDController(p, i, d);

    public static int targetMin = 0;
    public static int targetMax = 1150;
    public static int highPos = 1150;
    public static int midPos = 575;
    public static int lowPos = 288;
    private int target = 0;

    public static int manualSpeed = 20;

    public enum Position { HIGH, MEDIUM, LOW }

    public Slides(HardwareMap hardwareMap) {
        slide = hardwareMap.get(DcMotor.class, "slide");
        slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slide.setDirection(DcMotorSimple.Direction.REVERSE);

        slide2 = hardwareMap.get(DcMotor.class, "slide2");
        slide2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slide2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slide2.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void setTarget(int pos) {
        target = Math.min(Math.max(pos, targetMin), targetMax);
    }
    public void setTarget(Position pos) {
        if (pos == Position.HIGH) {
            target = Math.min(Math.max(highPos, targetMin), targetMax);
        } else if (pos == Position.MEDIUM) {
            target = Math.min(Math.max(midPos, targetMin), targetMax);
        } else if (pos == Position.LOW) {
            target = Math.min(Math.max(lowPos, targetMin), targetMax);
        }
    }
    public void cancel() {
        target = slide.getCurrentPosition();
    }
    public void targetReset() {
        target = targetMin;
    }
    public void increaseTarget(double increase) {
        target += (int) (increase * manualSpeed);
        target = Math.min(targetMax, Math.max(targetMin, target));
    }

    public void update() {
        controller.setPID(p, i, d);
        controller.setTolerance(pTolerance);

        double pid = controller.calculate(slide.getCurrentPosition(), target);
        double ff = f;

        slide.setPower(pid + ff);

        pid = controller.calculate(slide2.getCurrentPosition(), target);
        ff = f;

        slide2.setPower(pid + ff);
    }

    public boolean atTarget() {
        return controller.atSetPoint();
    }

    public String getTelemetry() {
        return String.format("Position: %s %s\nTarget: %s %s\nPower: %s %s", slide.getCurrentPosition(), slide2.getCurrentPosition(), target, target, slide.getPower(), slide2.getPower());
    }
}
