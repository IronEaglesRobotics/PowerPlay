package org.firstinspires.ftc.teamcode.hardware;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Config
public class Slides { // TODO fix the math for different slide motors and less slides (5 --> 3)
    private DcMotor slide;
    private DcMotor slide2;

    public static double p = 0.001;
    public static double i = 0.01;
    public static double d = 0;
    public static double f = 0;
    public static double pTolerance = 20;
    public static PIDController controller = new PIDController(p, i, d);
    public static double downMultiplier = 0.5;

    public static int[] heights = {0, (int)(200/4.0), 2*(int)(200/4.0), 3*(int)(200/4.0), 4*(int)(200/4)};

    public static int heightOffset = 0;
    public static int targetMin = 0;
    public static int targetMax = 770;
    public static int highPos = 770 + heightOffset; // ALSO DEFINED IN UPDATE SLIDES
    public static int midPos = 300 + heightOffset; // ALSO DEFINED IN UPDATE SLIDES
    public static int lowPos = 0 + heightOffset; // ALSO DEFINED IN UPDATE SLIDES
    private int target = 0;

    public int decrementAmount = 100;

    public static int manualSpeed = 50;
    public static int zeroPower = 5;

    public enum Position { HIGH, MEDIUM, LOW, DOWN }

    public Slides(HardwareMap hardwareMap) {
        slide = hardwareMap.get(DcMotor.class, "slide");
        slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        slide.setDirection(DcMotorSimple.Direction.REVERSE);

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

    public void increaseTarget(double increase) {
        target += (int) (increase * manualSpeed);
        target = Math.min(targetMax, Math.max(targetMin, target));
    }

    public int getTarget() {
        return target;
    }

    public boolean atTarget() {
        return controller.atSetPoint();
    }

    public void cancel() {
        target = slide.getCurrentPosition();
    }

    public void targetReset() {
        target = targetMin;
    }

    public void update(double runTime) {
//        highPos = 850 + heightOffset;
//        midPos = 300 + heightOffset;
//        lowPos = 0 + heightOffset;

//        if (target == 0) {
//            slide.setPower(0);
//            slide2.setPower(0);
//        } else {
//            if (target < 5) {
//                slide.setPower(0);
//                slide2.setPower(0);
//            } else {
                double pid, ff;
                controller.setPID(p, i, d);
                controller.setTolerance(pTolerance);

                pid = controller.calculate(slide.getCurrentPosition(), target);
                ff = f;
                slide.setPower(pid + ff);

                pid = controller.calculate(slide2.getCurrentPosition(), target);
                ff = f;
                slide2.setPower(pid + ff);
//            }
//        }


//        controller.setPID(p, i, d);
//        controller.setTolerance(pTolerance);
//
//        double pid = controller.calculate(slide.getCurrentPosition(), actualTarget);
//        double ff = f;
//
////            if (slide.getCurrentPosition() > target) {
////                slide.setPower(pid*downMultiplier+ff);
////            } else {
//            slide.setPower(pid + ff);
////            }
//
//        pid = controller.calculate(slide2.getCurrentPosition(), actualTarget2);
//        ff = f;
//
////            if (slide2.getCurrentPosition() > target) {
////                slide2.setPower(pid*downMultiplier+ff);
////            } else {
//            slide2.setPower(pid + ff);
////            }
    }

    public String getTelemetry() {
        return String.format("Position: %s %s\nTarget: %s %s\nPower: %s %s\nHeightOffset: %s", slide.getCurrentPosition(), slide2.getCurrentPosition(), target, target, slide.getPower(), slide2.getPower(), heightOffset);
    }
}
