package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.PoseStorage;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;

@Config
@TeleOp
public class TeleTesting extends OpMode {
    SampleMecanumDrive drive;

    @Override
    public void init() {
        drive = new SampleMecanumDrive(hardwareMap);
        drive.setPoseEstimate(PoseStorage.currentPose);
    }

    @Override
    public void loop() {
        double x = -gamepad1.left_stick_y;
        double y = -gamepad1.left_stick_x;
        double z = -gamepad1.right_stick_x;

        Pose2d poseEstimate = drive.getPoseEstimate();
//        Vector2d input = new Vector2d(x,y).rotated(-poseEstimate.getHeading());
//        drive.setWeightedDrivePower(new Pose2d(input.getX(), input.getY(), z/2.0));
        drive.setWeightedDrivePower(new Pose2d(x, y, z));
        drive.update();

        telemetry.addData("x", poseEstimate.getX());
        telemetry.addData("y", poseEstimate.getY());
        telemetry.addData("heading", poseEstimate.getHeading());
        telemetry.update();
    }
}
