package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.sun.tools.javac.tree.DCTree;

import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;

@Config
@TeleOp
public class XtremeDrive extends OpMode {
    SampleMecanumDrive drive;
    Servo leftClaw;
    Servo rightClaw;
    DcMotor slide;
    public static double leftOpen;
    public static double leftClosed;
    public static double rightOpen;
    public static double rightClosed;
    public static int slideTop;
    public static int slideBottom;
    PIDController controller;
    public static double P = 0.002;
    public static double I = 0;
    public static double D = 0;

    @Override
    public void init() {
        drive = new SampleMecanumDrive(hardwareMap);
        drive.setPoseEstimate(PoseStorage.currentPose);
        leftClaw = hardwareMap.get(Servo.class,"leftClaw");
        rightClaw = hardwareMap.get(Servo.class,"rightClaw");
        slide = hardwareMap.get(DcMotor.class, "slides");
        slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        slide.setDirection(DcMotorSimple.Direction.REVERSE);
        controller = new PIDController(P, I, D);
        leftOpen = 0.16;
        leftClosed = 0.02;
        rightOpen = 0.15;
        rightClosed = 0.35;
        slideBottom = 0;
        slideTop = 3000;
    }

    @Override
    public void loop() {
        double x = -gamepad1.left_stick_y;
        double y = -gamepad1.left_stick_x;
        double z = -gamepad1.right_stick_x;
        double rightTrig = gamepad1.right_trigger;
        boolean rightBump = gamepad1.right_bumper;

        Pose2d poseEstimate = drive.getPoseEstimate();
        Vector2d input = new Vector2d(x,y).rotated(-poseEstimate.getHeading());
//        drive.setWeightedDrivePower(new Pose2d(input.getX(), input.getY(), z/2.0));
        drive.setWeightedDrivePower(new Pose2d(x, y, z/2.0));
        drive.update();

        if (rightBump) {
            leftClaw.setPosition(leftClosed);
            rightClaw.setPosition(rightClosed);
        } else {
            leftClaw.setPosition(leftOpen);
            rightClaw.setPosition(rightOpen);
        }

        int position = (int) (slideBottom + (int)(rightTrig * (slideTop - slideBottom)));
        controller.setPID(P, I, D);
        controller.setSetPoint(position);
        slide.setPower(controller.calculate(slide.getCurrentPosition())); // TODO change to using PID so that there is better thingies

        telemetry.addData("x", poseEstimate.getX());
        telemetry.addData("y", poseEstimate.getY());
        telemetry.addData("heading", poseEstimate.getHeading());
        telemetry.addData("Slide Power: ", rightTrig);
        telemetry.addData("Slide.getPower: ", slide.getPower());
        telemetry.addData("Slide Set Position: ", position);
        telemetry.addData("Slide.getPosition: ", slide.getCurrentPosition());
        telemetry.update();
    }
}
