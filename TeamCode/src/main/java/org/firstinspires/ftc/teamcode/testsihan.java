package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

// starter mecanum base opmode
// (the name and group arguments are optional, they organize how the opmodes are shown on the driver station)
@TeleOp(name = "Basic Bot Sihan", group = "Development")
public class testsihan extends OpMode {
    // instantiate motor variables
    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;
    private Servo servoclawturn = null;
    private Servo servoclawgrip = null;
    private DcMotor slide = null;
    private DcMotor armswing = null;
    private DcMotor slide2 = null;
    private ColorRangeSensor coloursensor = null;

    // robot initialization
    @Override
    public void init() {

        //servo code
//        servo_test = hardwareMap.servo.get("servo");
        servoclawgrip = hardwareMap.get(Servo.class, "servo");
        servoclawturn = hardwareMap.get(Servo.class, "servoturn");

        // initialize motor variables
        // (the names are what is set through the driver station)
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        slide = hardwareMap.get(DcMotor.class, "slide");
        armswing = hardwareMap.get(DcMotor.class, "armswing");
        slide2 = hardwareMap.get(DcMotor.class, "slide2");
        slide2.setDirection(DcMotor.Direction.REVERSE);
        //sensor code
        coloursensor = hardwareMap.get(ColorRangeSensor.class, "coloursensor");


        // set the default direction for the motors
        // (some are reversed because they face opposite directions)
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        // set the motors to run without encoders
        // (encoders make the motor speeds more accurate, but require extra wires)
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armswing.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

    // main loop
    public void servo_move() {
        if (gamepad1.x) {
            servoclawgrip.setPosition(0.275);
        } else {
            servoclawgrip.setPosition(0.5);
        }


        if (gamepad1.y) {
            servoclawturn.setPosition(0.79);
        } else {
            servoclawturn.setPosition(0.085);
        }


        servoclawturn.setPosition(gamepad1.left_trigger);
        servoclawgrip.setPosition(Math.min(0.6, Math.max(0.2, gamepad1.right_trigger)));
    }


    @Override
    public void loop() {

        // get input from the gamepad
        // (up on the gamepad sticks is considered negative 1, so that's why you have to add a negative to the y)
        double x = gamepad1.left_stick_x / 1.75;
        double y = -gamepad1.left_stick_y / 1.75;
        double z = gamepad1.right_stick_x / 1.25;
        boolean pressup = gamepad1.dpad_up;
        boolean pressdown = gamepad1.dpad_down;
        boolean pressright = gamepad1.dpad_right;
        boolean pressleft = gamepad1.dpad_left;

        //armswing
        if (pressright) {
            armswing.setPower(0.3);
        } else if (pressleft) {
            armswing.setPower(-0.3);
        } else {
            armswing.setPower(0);
        }

        telemetry.addData("armswing", armswing.getCurrentPosition());

        //slide up and down
        if (pressup) {
            slide.setPower(0.3);
            slide2.setPower(0.3);
        } else if (pressdown) {
            slide.setPower(-0.3);
            slide2.setPower(-0.3);
        } else {
            slide.setPower(0);
            slide2.setPower(0);
        }

        // set motor power based on the gamepad input
        setWheels(x, y, z);
        servo_move();

        telemetry.addData("slide", slide.getCurrentPosition());
        telemetry.addData("coloursensor", coloursensor.getDistance(DistanceUnit.MM));
        telemetry.addData("servoclawturn", servoclawturn.getPosition());
        telemetry.addData("servoclawgrip", servoclawturn.getPosition());
        telemetry.update();
    }

    // method to set wheel powers
    public void setWheels(double x, double y, double z) {
        // instantiate motor power variables
        double flPower, frPower, blPower, brPower;

        // compute motor powers~/Library/Android/sdk/platform-tools/adb
        // (see gm0.org/en/latest/docs/software/mecanum-drive.html for more info)
        flPower = x + y + z;
        frPower = -x + y - z;
        blPower = -x + y + z;
        brPower = x + y - z;

        // make sure that the motor powers don't exceed 1 or -1
        // (a value greater than 1 or -1 will just be truncated down so it's ok, however it will throw off the ratio with the other motors)
        double max = Math.max(Math.max(flPower, frPower), Math.max(blPower, brPower));
        if (max > 1) {
            flPower /= max;
            frPower /= max;
            blPower /= max;
            brPower /= max;
        }

        // actually set the motor powers
        frontLeft.setPower(flPower);
        frontRight.setPower(frPower);
        backLeft.setPower(blPower);
        backRight.setPower(brPower);
    }
}