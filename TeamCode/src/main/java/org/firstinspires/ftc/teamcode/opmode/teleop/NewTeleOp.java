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
import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.hardware.Slides;
import org.firstinspires.ftc.teamcode.opmode.Alliance;
import org.firstinspires.ftc.teamcode.util.CameraPosition;

@TeleOp(name = "New TeleOp")
public class NewTeleOp extends OpMode {
    private int teamElementLocation = 2;

    private Robot robot;
    Controller driver1;
    Controller driver2;

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

        // driver 1
        double x = -driver1.getLeftStick().getY();
        double y = driver1.getLeftStick().getX();
        double z = -driver1.getRightStick().getX();
        robot.drive.setWeightedDrivePower(new Pose2d(x, y, z));

        // driver 2
        robot.slides.increaseTarget(driver2.getLeftStick().getY());

//        if (driver2.getA().isJustPressed()) {
//            robot.claw.toggle();
//        }
//        if (driver2.getB().isJustPressed()) {
//            robot.claw.flip();
//        }

        if (driver2.getA().isJustPressed()) {
            robot.arm.goToIntake();
        }

        if (driver2.getY().isJustPressed()) {
            robot.arm.goToScore();
        }

        if (driver2.getB().isJustPressed()) {
            robot.arm.goToPickup();
        }

        // update and telemetry
        robot.update(getRuntime());

        int newLocation = robot.camera.getMarkerId();
        if (newLocation != -1) {
            teamElementLocation = newLocation;
        }
        telemetry.addLine("Randomization: "+teamElementLocation);
        telemetry.addLine(robot.getTelemetry());
        telemetry.update();
    }
}
