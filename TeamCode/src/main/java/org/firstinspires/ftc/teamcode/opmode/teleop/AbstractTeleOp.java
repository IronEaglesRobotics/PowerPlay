package org.firstinspires.ftc.teamcode.opmode.teleop;

import static org.firstinspires.ftc.teamcode.opmode.Alliance.BLUE;
import static org.firstinspires.ftc.teamcode.opmode.Alliance.RED;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.controller.Controller;
import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.hardware.Slides;
import org.firstinspires.ftc.teamcode.opmode.Alliance;

@Config
public abstract class AbstractTeleOp extends OpMode {
    private Robot robot;
    public Alliance alliance;

    Controller driver1;
    Controller driver2;

    public static double drivebaseThrottle = 0.4;

    public static int heightIncrement = 20;

    public static double robot_width = 12;
    public static double robot_length = 12;
    public static double robot_radius = 6;

    public static double groundJuncRadius = 6;
    public static double coneRadius = 4;

    Pose2d robot_pos;
    double robot_x, robot_y, robot_heading;

    @Override
    public void init() {
        robot =  new Robot(hardwareMap);
        driver1 = new Controller(gamepad1);
        driver2 = new Controller(gamepad2);
        if (alliance == RED) {
            driver1.setColor(255, 0, 0);
            driver2.setColor(255, 0, 0);
        } else if (alliance == BLUE) {
            driver1.setColor(0, 0, 255);
            driver2.setColor(0, 0, 255);
        }

        robot.slides.decrementAmount = 90;
    }

    public double[] getJuncPos(int juncNum) {
        int x_ref = Integer.parseInt(String.valueOf(String.valueOf(juncNum).charAt(0)));
        int y_ref = Integer.parseInt(String.valueOf(String.valueOf(juncNum).charAt(1)));

        double x_pos = (y_ref * -23.5) + 70.5;
        double y_pos = (x_ref * -23.5) + 70.5;

        return new double[] {x_pos, y_pos};
    }

    public boolean isGroundJunc(int juncNum) {
        int x_ref = Integer.parseInt(String.valueOf(String.valueOf(juncNum).charAt(0)));
        int y_ref = Integer.parseInt(String.valueOf(String.valueOf(juncNum).charAt(1)));

        return x_ref % 2 != 0 && y_ref % 2 != 0;
    }

    public int nearestJunc(double posX, double posY) { // robot position
        int diffJuncX = (int) ((Math.abs(posX) + 11.75) / 23.5);
        int juncY = (posX < 0? 3 + diffJuncX : 3 - diffJuncX);

        int diffJuncY = (int) ((Math.abs(posY) + 11.75) / 23.5);
        int juncX = (posY < 0? 3 + diffJuncY : 3 - diffJuncY);

        return Integer.parseInt(String.valueOf(juncX) + String.valueOf(juncY));
    }

    @Override
    public void loop() {
        driver1.update();
        driver2.update();
        double x = driver1.getLeftStick().getY();
        double y = -driver1.getLeftStick().getX();
        double z = -driver1.getRightStick().getX();

//        robot_pos = robot.drive.getPoseEstimate();
//        robot_x = robot_pos.getX();
//        robot_y = robot_pos.getY();
//        robot_heading = robot_pos.getHeading(); // in radians
//
//
//        // check if overlapping
//        int nearJunc = nearestJunc(robot_x, robot_y);
//        double juncPosX = getJuncPos(nearJunc)[0];
//        double juncPosY = getJuncPos(nearJunc)[1];
//        double d = Math.sqrt((robot_x - juncPosX) * (robot_x - juncPosX) + (robot_y - juncPosY) * (robot_y - juncPosY));
//        double juncRadius = (isGroundJunc(nearJunc)? groundJuncRadius : coneRadius);
////        double juncAngle = Math.acos(robot_radius / d); // absolute angle of the junction from robot in radians
//        double juncAngle = Math.atan2(juncPosY - robot_y, juncPosX - robot_x); // absolute angle of the junction from robot in radians
//
//        if (d <= robot_radius + juncRadius) {
//            // overlapping or touching
//            double radianDiff = juncAngle - robot_heading;
//
//            if (radianDiff < Math.toRadians(180) && radianDiff > Math.toRadians(0)) {
//                x = Math.max(x, 0);
//            } else if (radianDiff > Math.toRadians(-180) && radianDiff < Math.toRadians(0)) {
//                x = Math.min(x, 0);
//            }
//
//            if (radianDiff < Math.toRadians(90) && radianDiff > Math.toRadians(-90)) {
//                y = Math.min(y, 0);
//            } else if (radianDiff > Math.toRadians(90) || radianDiff < Math.toRadians(-90)) {
//                y = Math.max(y, 0);
//            }
//        }

        if (driver1.getLeftBumper().isPressed() || driver1.getRightBumper().isPressed()) { // TURBO
            robot.drive.setWeightedDrivePower(new Pose2d(x, y, z));
        } else {
            robot.drive.setWeightedDrivePower(new Pose2d(x * drivebaseThrottle, y * drivebaseThrottle, z * drivebaseThrottle));
        }

        // increment heights
        if (driver2.getDUp().isJustPressed()) {
            Slides.heightOffset += heightIncrement;
            robot.slides.setTarget(robot.slides.getTarget() + heightIncrement);
        } else if (driver2.getDDown().isJustPressed()) {
            Slides.heightOffset -= heightIncrement;
            robot.slides.setTarget(robot.slides.getTarget() - heightIncrement);
        }

        switch (robot.runningMacro) {
            case(0): // manual mode
                robot.slides.increaseTarget(driver2.getLeftStick().getY());
                robot.hSlides.increaseTarget(driver2.getRightStick().getY());
                if (Math.abs(driver2.getRightStick().getY()) > 0.05) { // close claw if anything is moved
                    robot.claw.close();
                }
                // retract all the time
                if (driver2.getLeftStickButton().isJustPressed()) {
                    robot.runningMacro = 4;
                }

                // to cancel macro and allow manual movement
                if (driver2.getLeftStick().getY() > 0.05 || driver2.getRightStick().getY() > 0.05) {
                    robot.runningMacro = 0;
                    robot.lastMacro = 0;
                }

                if (driver2.getX().isJustPressed()) { // high position [closed, bring up, bring out]
                    robot.runningMacro = 3;
                } else if (driver2.getY().isJustPressed()) { // middle position [middle goal level]
                    robot.runningMacro = 2;
                } else if (driver2.getB().isJustPressed() && !driver2.getStart().isJustPressed()) { // low position [low goal level]
                    robot.runningMacro = 1;
                } else if (driver2.getA().isJustPressed()) {
                    if (robot.lastMacro == 0) { // if not running any macros
                        robot.claw.toggle(true);
                    } else { // otherwise, I need to undo a macro
                        robot.runningMacro = 4;
                    }
                } else if (driver2.getDRight().isJustPressed()) {
                    robot.claw.toggle(false);
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

        // cancel the macros
        if (driver2.getRightStickButton().isJustPressed()) {
            robot.runningMacro = 0;
            robot.lastMacro = 0;
            robot.macroState = 0;
            robot.slides.cancel();
        }

        // update and telemetry
        robot.update(getRuntime());

        telemetry.addLine(robot.getTelemetry());
        telemetry.addLine(String.format("Last Macro: %s\nRunning Macro: %s\nMacroState: %s", robot.lastMacro, robot.runningMacro, robot.macroState));
        telemetry.update();
    }
}
