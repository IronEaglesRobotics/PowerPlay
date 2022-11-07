package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.controller.Controller;
import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.hardware.Slides;

@Config
@TeleOp
public class BlueTeleOp extends OpMode {
    private Robot robot;
    Controller driver1;
    Controller driver2;

    // wait times
    private double macroStartTime = getRuntime();
    private int macroState = 0;
    private int runningMacro = 0; // 0 = no macro | 1 = low macro | 2 = mid macro | 3 = high macro
    private int lastMacro = 0;

    public static double clawWait = 0.5;
    public static double[] vslideWaits = new double[]{0.5, 1, 1.5}; // slide waits for low, med, high
    public static double hslideWait = 1;

    public static double drivebaseThrottle = 0.4;

    @Override
    public void init() {
        robot =  new Robot(hardwareMap);
        driver1 = new Controller(gamepad1);
        driver2 = new Controller(gamepad2);
    }

    public int posToNum (Slides.Position pos) {
        /*
        HIGH - 3
        MEDIUM - 2
        LOW - 1
        ELSE - -1
         */
        if (pos == Slides.Position.HIGH) {
            return 3;
        } else if (pos == Slides.Position.MEDIUM) {
            return 2;
        } else if (pos ==Slides.Position.LOW) {
            return 1;
        }
        return -1;
    }

    public Slides.Position numToPos (int num) {
        /*
        3 - High
        2 - Medium
        1 = Low
        */
        if (num == 1) {
            return Slides.Position.LOW;
        } else if (num == 2) {
            return Slides.Position.MEDIUM;
        } else {
            return Slides.Position.HIGH;
        }
    }

    public void extendMacro(Slides.Position pos) {
        switch(macroState) {
            case(0):
                macroStartTime = getRuntime();
                robot.claw.close();
                macroState ++;
                break;
            case(1):
                if (getRuntime() > macroStartTime + clawWait) {
                    macroState ++;
                }
                break;
            case(2):
                macroStartTime = getRuntime();
                robot.slides.setTarget(pos);
                macroState ++;
                break;
            case(3):
                if (getRuntime() > macroStartTime + vslideWaits[posToNum(pos) - 1]) {
                    macroState ++;
                }
                break;
            case(4):
                robot.hSlides.goToScore();
                macroState = 0;
                lastMacro = runningMacro;
                runningMacro = 0;
                break;
        }
    }

    public void resetMacro() {
        switch(macroState) {
            case(0):
                macroStartTime = getRuntime();
                macroState++;
                break;
            case(1):
                if (getRuntime() > macroStartTime + clawWait) {
                    macroState ++;
                }
                break;
            case(2):
                macroStartTime = getRuntime();
                robot.claw.close();
                break;
            case(3):
                if (getRuntime() > macroStartTime + clawWait) {
                    macroState ++;
                }
                break;
            case(4):
                macroStartTime = getRuntime();
                robot.hSlides.goToIntake();
                macroState ++;
                break;
            case(5):
                if (getRuntime() > macroStartTime + hslideWait) {
                    macroState ++;
                }
                break;
            case(6):
                macroState = 0;
                robot.slides.targetReset();
                runningMacro = 0;
                lastMacro = 0;
                break;
        }
    }

    @Override
    public void loop() {
        driver1.update();
        driver2.update();
        double x = driver1.getLeftStick().getY();
        double y = -driver1.getLeftStick().getX();
        double z = -driver1.getRightStick().getX();

        if (driver1.getLeftBumper().isPressed() || driver1.getRightBumper().isPressed()) { // TURBO
            robot.drive.setWeightedDrivePower(new Pose2d(x, y, z));
            driver1.rumble();
            driver2.rumble();
        } else {
            robot.drive.setWeightedDrivePower(new Pose2d(x * drivebaseThrottle, y * drivebaseThrottle, z * drivebaseThrottle));
        }


        switch (runningMacro) {
            case(0): // manual mode
                robot.slides.increaseTarget(driver2.getLeftStick().getY());
                robot.hSlides.increaseTarget(driver2.getRightStick().getY());
                if (Math.abs(driver2.getRightStick().getY()) > 0.05) {
                    robot.claw.close();
                }
                if (driver2.getA().isJustPressed()) {
                    if (lastMacro == 0) { // if not running any macros
                        robot.claw.toggle();
                        runningMacro = 0;
                    } else { // otherwise, I need to undo a macro
                        robot.claw.open();
                        runningMacro = 4;
                    }
                }
                // high position [closed, bring up, bring out]
                if (driver2.getX().isJustPressed()) {
                    runningMacro = 3;
                }

                // middle position [middle goal level]
                if (driver2.getY().isJustPressed()) {
                    runningMacro = 2;
                }

                // low position [low goal level]
                if (driver2.getB().isJustPressed()) {
                    runningMacro = 1;
                }
                break;
            case(1):
                extendMacro(Slides.Position.LOW);
                break;
            case(2):
                extendMacro(Slides.Position.MEDIUM);
                break;
            case(3):
                extendMacro(Slides.Position.HIGH);
                break;
            case (4): // macro reset
                resetMacro();
        }

        // cancel the macros
        if (driver2.getLeftStickButton().isJustPressed() || driver2.getRightStickButton().isJustPressed()) {
            runningMacro = 0;
            macroState = 0;
        }

        // update and telemetry
        robot.update();

        telemetry.addLine(robot.getTelemetry());
        telemetry.update();
    }
}
