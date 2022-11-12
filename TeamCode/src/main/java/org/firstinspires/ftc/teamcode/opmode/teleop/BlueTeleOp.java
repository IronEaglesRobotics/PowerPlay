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

    public static int targetDecrement = -1;
    public static double clawWait = 0.2;
    public static double hslideWait = 0.4;

    public static double drivebaseThrottle = 0.4;

    @Override
    public void init() {
        robot =  new Robot(hardwareMap);
        driver1 = new Controller(gamepad1);
        driver2 = new Controller(gamepad2);
    }

    public void extendMacro(Slides.Position pos) {
        switch(macroState) {
            case(0):
                driver2.rumble(20);
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
                if (robot.slides.atTarget()) {
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
                driver2.rumble(20);
                macroStartTime = getRuntime();
                robot.claw.open();
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
                macroState++;
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
                robot.slides.setTarget(0);
//                robot.slides.increaseTarget(targetDecrement);
//                if (robot.slides.getTarget() < 200) {
//                    robot.claw.open();
//                }
                if (robot.slides.getPosition() < 200) {
                    robot.claw.open();
                }
                if (robot.slides.getPosition() < 50) {
                    macroState = 0;
//                    robot.slides.targetReset();
                    runningMacro = 0;
                    lastMacro = 0;
                }
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
                if (Math.abs(driver2.getRightStick().getY()) > 0.05) { // close claw if anything is moved
                    robot.claw.close();
                }
                // retract all the time
                if (driver2.getLeftStickButton().isJustPressed()) {
                    runningMacro = 4;
                }

                // to cancel macro and allow manual movement
                if (driver2.getLeftStick().getY() > 0.05 || driver2.getRightStick().getY() > 0.05) {
                    runningMacro = 0;
                    lastMacro = 0;
                }

                if (driver2.getX().isJustPressed()) { // high position [closed, bring up, bring out]
                    runningMacro = 3;
                } else if (driver2.getY().isJustPressed()) { // middle position [middle goal level]
                    runningMacro = 2;
                } else if (driver2.getB().isJustPressed() && !driver2.getStart().isJustPressed()) { // low position [low goal level]
                    runningMacro = 1;
                } else if (driver2.getA().isJustPressed()) {
                    if (lastMacro == 0) { // if not running any macros
                        robot.claw.toggle(true);
                    } else { // otherwise, I need to undo a macro
                        runningMacro = 4;
                    }
                } else if (driver2.getDRight().isJustPressed()) {
                    robot.claw.toggle(false);
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
                break;
        }

        // cancel the macros
        if (driver2.getRightStickButton().isJustPressed()) {
            runningMacro = 0;
            lastMacro = 0;
            macroState = 0;
            robot.slides.cancel();
        }


        // update and telemetry
        robot.update(getRuntime());

        telemetry.addLine(robot.getTelemetry());
        telemetry.addLine(String.format("Last Macro: %s\nRunning Macro: %s\nMacroState: %s", lastMacro, runningMacro, macroState));
        telemetry.update();

    }
}
