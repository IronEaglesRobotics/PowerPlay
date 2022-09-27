package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;

@Config
@TeleOp
public class XtremeDrive extends OpMode {
    SampleMecanumDrive drive;
    Servo leftClaw;
    Servo rightClaw;
    public static double leftOpen;
    public static double leftClosed;
    public static double rightOpen;
    public static double rightClosed;

    @Override
    public void init() {
        drive = new SampleMecanumDrive(hardwareMap);
        drive.setPoseEstimate(PoseStorage.currentPose);
        leftClaw = hardwareMap.get(Servo.class,"leftClaw");
        rightClaw = hardwareMap.get(Servo.class,"rightClaw");
        leftOpen = 0;
        leftClosed = 1;
        rightOpen = 0;
        rightClosed = 1;
    }

    @Override
    public void loop() {
        double x = -gamepad1.left_stick_y;
        double y = -gamepad1.left_stick_x;
        double z = -gamepad1.right_stick_x;
        boolean a = gamepad1.a;

        Pose2d poseEstimate = drive.getPoseEstimate();
        Vector2d input = new Vector2d(x,y).rotated(-poseEstimate.getHeading());
        drive.setWeightedDrivePower(new Pose2d(input.getX(), input.getY(), z/2.0));
//        drive.setWeightedDrivePower(new Pose2d(x, y, z));
        drive.update();

        if (a) {
            leftClaw.setPosition(leftClosed);
            rightClaw.setPosition(rightClosed);
        } else {
            leftClaw.setPosition(leftOpen);
            rightClaw.setPosition(rightOpen);
        }

        telemetry.addData("x", poseEstimate.getX());
        telemetry.addData("y", poseEstimate.getY());
        telemetry.addData("heading", poseEstimate.getHeading());
        telemetry.update();
    }
}
