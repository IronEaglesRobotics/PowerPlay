package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;

@Config
@TeleOp
public class BlueTeleOp extends OpMode {
    SampleMecanumDrive drive;
    DcMotor arm;
    DcMotor slide;
    DcMotor slide2;
    Servo clawLeft;
    Servo clawRight;
    double clawLeftOpen = 0;
    double clawLeftClosed = 1;
    double clawRightOpen = 0;
    double clawRightClosed = 1;
    boolean clawClosed = true;
    int slidePosition = 0;
    public static int slideMin = 0;
    public static int slideMax = 500;
    public static int slideSpeed = 2;

    public static double slideP = 0.002;
    public static double slideI = 0;
    public static double slideD = 0;
    PIDController slideController = new PIDController(0,0,0);
    public static double armP = 0.002;
    public static double armI = 0;
    public static double armD = 0;
    PIDController armController = new PIDController(0,0,0);
    int armPosition = 0;
    public static int armMin = 0;
    public static int armMax = 500;
    public static int armSpeed = 2;

    @Override
    public void init() {
//        drive = new SampleMecanumDrive(hardwareMap);
//        drive.setPoseEstimate(PoseStorage.currentPose);
        arm = hardwareMap.get(DcMotor.class,"arm");
        slide = hardwareMap.get(DcMotor.class,"slide");
        slide2 = hardwareMap.get(DcMotor.class,"slide2");
        slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slide2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        clawLeft = hardwareMap.get(Servo.class,"clawLeft");
//        clawRight = hardwareMap.get(Servo.class,"clawRight");
    }

    @Override
    public void loop() {
        double x = -gamepad1.left_stick_y;
        double y = -gamepad1.left_stick_x;
        double z = -gamepad1.right_stick_x;

//        Pose2d poseEstimate = drive.getPoseEstimate();
////        Vector2d input = new Vector2d(x,y).rotated(-poseEstimate.getHeading());
////        drive.setWeightedDrivePower(new Pose2d(input.getX(), input.getY(), z/2.0));
//        drive.setWeightedDrivePower(new Pose2d(x, y, z));
//        drive.update();

        boolean high = gamepad2.y;
        boolean middle = gamepad2.b;
        boolean low = gamepad2.a;
        boolean ground = gamepad2.x;
        boolean claw = gamepad2.left_bumper;

//        if (claw) {
//            if (clawClosed) {
//                clawLeft.setPosition(clawLeftOpen);
//                clawRight.setPosition(clawRightOpen);
//                clawClosed = false;
//            } else {
//                clawLeft.setPosition(clawLeftClosed);
//                clawRight.setPosition(clawRightClosed);
//                clawClosed = true;
//            }
//        }
//        if (high) {
//
//        }
        double leftStick = -gamepad2.left_stick_y;
        slidePosition += leftStick * slideSpeed;
        slidePosition = Math.min(Math.max(slidePosition, slideMin), slideMax);
        slideController.setPID(slideP, slideI, slideD);
        slideController.setSetPoint(slidePosition);
        slide.setPower(slideController.calculate(slide.getCurrentPosition()));
        slide2.setPower(-slideController.calculate(slide.getCurrentPosition()));

        double rightStick = -gamepad2.right_stick_y;
        armPosition += rightStick * armSpeed;
        armPosition = Math.min(Math.max(armPosition, armMin), armMax);
        armController.setPID(armP, armI, armD);
        armController.setSetPoint(armPosition);
        arm.setPower(armController.calculate(arm.getCurrentPosition()));


//        telemetry.addData("x", poseEstimate.getX());
//        telemetry.addData("y", poseEstimate.getY());
//        telemetry.addData("heading", poseEstimate.getHeading());
        telemetry.addData("Position", slide.getCurrentPosition());
        telemetry.addData("Power", slide.getPower());
        telemetry.addData("ARM Position", arm.getCurrentPosition());
        telemetry.addData("ARM Power", arm.getPower());
        telemetry.addData("ARM PID", armController.getPositionError());

        telemetry.update();
    }
}
