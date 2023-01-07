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
public abstract class ClawTeleOp extends OpMode {
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

        if (driver2.getA().isJustPressed()) {
            robot.claw.toggle();
        }

        // update and telemetry
        robot.update(getRuntime());

        telemetry.addLine(robot.getTelemetry());
        telemetry.update();
    }
}
