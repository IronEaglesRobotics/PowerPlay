package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.controller.Controller;
import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.hardware.Slides;


/**
 * TODO
 * - A shoudl open clsoe but also reset macro if active
 * - Pressing down on either joystick cancel macros
 */


@Config
@TeleOp
public class BlueTeleOp extends OpMode {
    private Robot robot;
    Controller driver1;
    Controller driver2;

    // wait times
    private double macroStartTime = getRuntime();
    private int macroState = 0;
    public static double clawWait = 0.2;
    public static double vslideWait = 0.5;

    @Override
    public void init() {
        robot =  new Robot(hardwareMap);
        driver1 = new Controller(gamepad1);
        driver2 = new Controller(gamepad2);
    }

    public void extendMacro(Slides.Position pos) {
        switch(macroState) {
            case(0):
                if (driver2.getX().isJustPressed()) {
                    macroStartTime = getRuntime();
                    robot.claw.close();
                    macroState ++;
                }
                break;
            case(1):
                if (getRuntime() > macroStartTime + clawWait) {
                    macroState ++;
                }
                break;
            case(2):
                robot.slides.setTarget(pos);
                macroState ++;
                break;
            case(3):
                if (getRuntime() > macroStartTime + vslideWait) {
                    macroState ++;
                }
                break;
            case(4):
                robot.hSlides.goToScore();
                macroState = 0;
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

        robot.drive.setWeightedDrivePower(new Pose2d(x, y, z));


        robot.slides.increaseTarget(driver2.getLeftStick().getY());
        robot.hSlides.increaseTarget(driver2.getRightStick().getY());
//        robot.arm.increaseTarget(driver2.getRightStick().getY());

        if (driver2.getA().isJustPressed()) {
            robot.claw.toggle();
        }

        // High position [closed, bring up, bring out]
        if (driver2.getX().isJustPressed()) {
            extendMacro(Slides.Position.HIGH);
        }

        // Middle position [middle goal level]
        if (driver2.getY().isJustPressed()) {
            extendMacro(Slides.Position.MEDIUM);
        }

        // Low position [low goal level]
        if (driver2.getB().isJustPressed()) {
            extendMacro(Slides.Position.LOW);
        }

        robot.update();

        telemetry.addLine(robot.getTelemetry());
        telemetry.update();
    }
}
