package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.controller.Controller;
import org.firstinspires.ftc.teamcode.hardware.Robot;

@TeleOp
public class BlueTeleOp extends OpMode {
    private Robot robot;
    Controller driver1;
    Controller driver2;

    @Override
    public void init() {
        robot =  new Robot(hardwareMap);
        driver1 = new Controller(gamepad1);
        driver2 = new Controller(gamepad2);
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
//        robot.arm.increaseTarget(driver2.getRightStick().getY());

        if (driver2.getB().isJustPressed()) {
            robot.hSlides.toggle();
        }

        if (driver2.getA().isJustPressed()) {
            robot.claw.toggle();
        }

        robot.update();

        telemetry.addLine(robot.getTelemetry());
        telemetry.update();
    }
}
