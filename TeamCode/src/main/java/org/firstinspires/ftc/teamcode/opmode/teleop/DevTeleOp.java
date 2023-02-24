package org.firstinspires.ftc.teamcode.opmode.teleop;

import static org.firstinspires.ftc.teamcode.hardware.Arm.Position.INTAKE;
import static org.firstinspires.ftc.teamcode.hardware.Claw.Position.UPRIGHT;
import static org.firstinspires.ftc.teamcode.opmode.Alliance.BLUE;
import static org.firstinspires.ftc.teamcode.opmode.Alliance.RED;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.controller.Controller;
import org.firstinspires.ftc.teamcode.hardware.Arm;
import org.firstinspires.ftc.teamcode.hardware.Claw;
import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.hardware.Slides;
import org.firstinspires.ftc.teamcode.opmode.Alliance;
import org.firstinspires.ftc.teamcode.util.CameraPosition;

@Disabled
@TeleOp
public class DevTeleOp extends OpMode {
    private Robot robot;
    Controller driver1;
    Controller driver2;

    public static double armWait = 0.2;
    private int delayState = 0; // for arm
    private double delayStart = 0;
    private boolean doArmDelay = false; // for arm
    private boolean isAutoClose = true;
    private double timeSinceOpened = 0; // for claw

    @Override
    public void init() {
        robot =  new Robot(hardwareMap, INTAKE, UPRIGHT, CameraPosition.LEFT);
        driver1 = new Controller(gamepad1);
        driver2 = new Controller(gamepad2);
    }

    @Override
    public void loop() {
        driver1.update();
        driver2.update();
        double x = driver1.getLeftStick().getY()*0.5;
        double y = -driver1.getLeftStick().getX()*0.5;
        double z = -driver1.getRightStick().getX()*0.25;

//        double x = driver1.getLeftStick().getY();
//        double y = -driver1.getLeftStick().getX();
//        double z = -driver1.getRightStick().getX();


        robot.drive.setWeightedDrivePower(new Pose2d(x, y, z));

        switch (robot.runningMacro) {
            case(0): // manual mode

                if (driver1.getX().isJustPressed()) { // high position
                    robot.runningMacro = 3;
                } else if (driver1.getY().isJustPressed()) { // middle position
                    robot.runningMacro = 2;
                } else if (driver1.getB().isJustPressed() && !driver1.getStart().isJustPressed()) { // low position
                    robot.runningMacro = 1;
                } else if (driver1.getA().isJustPressed() && !driver1.getStart().isJustPressed()) { // retract
                    if (robot.lastMacro == 0) { // if not running any macros
                        robot.claw.toggle();
                    } else { // otherwise, I need to undo a macro
                        robot.runningMacro = 4;
                    }
                }

                // arm auto movement
                if (robot.claw.justOpened) {
                    timeSinceOpened = getRuntime();
                    robot.claw.justOpened = false;
                }

                if (getRuntime() - timeSinceOpened > 0.5) { // means I am ready to go again
                    if (isAutoClose && robot.claw.isOpen && robot.claw.getTriggerDistance() < Claw.triggerDistance) {
                        robot.claw.close();
                        delayState = 0;
                        doArmDelay = true;
                    }
                    if (!isAutoClose && !robot.claw.isOpen) {
                        delayState = 0;
                        doArmDelay = true;
                    }
                }

                if (doArmDelay) {
                    switch (delayState) {
                        case (0):
                            delayStart = getRuntime();
                            delayState++;
                            break;
                        case (1):
                            if (getRuntime() > delayStart + armWait) {
                                delayState ++;
                            }
                            break;
                        case(2):
                            doArmDelay = false;
                            delayStart = getRuntime();
                            delayState = 3;
                            robot.arm.goToMiddle();
                            break;
                        case(3):
                            doArmDelay = false;
                            break;
                    }
                }

                break;
            case(1):
                robot.extendMacro(Slides.Position.LOW, getRuntime());
                break;
            case(2):
                robot.extendMacro(Slides.Position.MEDIUM, getRuntime());
                break;
            case(3):
                robot.extendMacro(Slides.Position.HIGH, getRuntime());
                break;
            case (4): // macro reset
                robot.resetMacro(0, getRuntime());
                break;
        }

        // update and telemetry
        robot.update(getRuntime());

        telemetry.addLine(robot.getTelemetry());
        telemetry.update();
    }
}
