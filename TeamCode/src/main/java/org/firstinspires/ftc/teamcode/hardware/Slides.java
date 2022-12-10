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
    public static double f = 0;
    public static double pTolerance = 20;
    public static PIDController controller = new PIDController(p, i, d);
    public static double downMultiplier = 0.5;

    public static int[] heights = {0, (int)(280/4.0), 2*(int)(280/4.0), 3*(int)(280/4.0), 4*(int)(280/4)};

    public static int heightOffset = 0;
    public static int targetMin = 0;
    public static int targetMax = 2000;
    public static int highPos = 1830 + heightOffset; // ALSO DEFINED IN UPDATE SLIDES
    public static int midPos = 1250 + heightOffset; // ALSO DEFINED IN UPDATE SLIDES
    public static int lowPos = 800 + heightOffset; // ALSO DEFINED IN UPDATE SLIDES
    private int target = 0;
    private int actualTarget = 0;
    private int actualTarget2 = 0;

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
        actualTarget = slide.getCurrentPosition();
        actualTarget2 = slide2.getCurrentPosition();
    }
    public int getTarget() {
        return target;
    }
    public void setTarget(Position pos) {
        if (pos == Position.HIGH) {
            target = Math.min(Math.max(highPos, targetMin), targetMax);
        } else if (pos == Position.MEDIUM) {
            target = Math.min(Math.max(midPos, targetMin), targetMax);
        } else if (pos == Position.LOW) {
            target = Math.min(Math.max(lowPos, targetMin), targetMax);
        }
        actualTarget = slide.getCurrentPosition();
        actualTarget2 = slide2.getCurrentPosition();
    }
    public int getPosition() {
        return slide.getCurrentPosition();
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

    public void update(double runTime) {
        highPos = 1830 + heightOffset;
        midPos = 1250 + heightOffset;
        lowPos = 800 + heightOffset;
        if (target < 5) {
            slide.setPower(0);
            slide2.setPower(0);
        } else {
            double pid, ff;
            controller.setPID(p, i, d);
            controller.setTolerance(pTolerance);

            pid = controller.calculate(slide.getCurrentPosition(), target);
            ff = f;
            slide.setPower(pid + ff);

            pid = controller.calculate(slide2.getCurrentPosition(), target);
            ff = f;
            slide2.setPower(pid + ff);
        }

//        if (target > 0) {
//            double actualTarget = slide.getCurrentPosition();


        if (actualTarget > target) {
            actualTarget -= decrementAmount;
        } else {
            actualTarget = target;
        }

        if (actualTarget2 > target) {
            actualTarget2 -= decrementAmount;
        } else {
            actualTarget2 = target;
        }
//            if (slide.getCurrentPosition() > target) {
//                actualTarget -= decrementAmount;
//            } else {
//                actualTarget = target;
//            }
            controller.setPID(p, i, d);
            controller.setTolerance(pTolerance);

            double pid = controller.calculate(slide.getCurrentPosition(), actualTarget);
            double ff = f;

//            if (slide.getCurrentPosition() > target) {
//                slide.setPower(pid*downMultiplier+ff);
//            } else {
                slide.setPower(pid + ff);
//            }

            pid = controller.calculate(slide2.getCurrentPosition(), actualTarget2);
            ff = f;

//            if (slide2.getCurrentPosition() > target) {
//                slide2.setPower(pid*downMultiplier+ff);
//            } else {
                slide2.setPower(pid + ff);
//            }
//        } else {
//            slide.setPower(0);
//            slide2.setPower(0);
//        }

//        if (target < 5 && slide.getCurrentPosition() < 5) {
//            if (startOfTighten == -1) {
//                startOfTighten = runTime;
//                slide.setPower(0.2);
//                slide2.setPower(0.2);
//            }
//            if (runTime > startOfTighten + tightenTime) {
//                slide.setPower(0);
//                slide2.setPower(0);
//                startOfTighten = -2;
//            }
//        } else {
//            startOfTighten = -1;
//        }
    }

    public boolean atTarget() {
        return controller.atSetPoint();
    }

    public String getTelemetry() {
        return String.format("Position: %s %s\nTarget: %s %s\nPower: %s %s\nHeightOffset: %s", slide.getCurrentPosition(), slide2.getCurrentPosition(), target, target, slide.getPower(), slide2.getPower(), heightOffset);
    }
}
