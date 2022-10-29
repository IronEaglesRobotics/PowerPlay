package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

// starter mecanum base opmode
// (the name and group arguments are optional, they organize how the opmodes are shown on the driver station)
@Autonomous(name = "Basic Bot", group = "Development")
public class gabeAuto extends LinearOpMode {
    // instantiate motor variables
    private DcMotor frontLeftWheel = null;
    private DcMotor frontRightWheel = null;
    private DcMotor backLeftWheel = null;
    private DcMotor backRightWheel = null;

    @Override
    public void runOpMode() throws InterruptedException {
        frontLeftWheel = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRightWheel = hardwareMap.get(DcMotor.class, "frontRight");
        backLeftWheel = hardwareMap.get(DcMotor.class, "backLeft");
        backRightWheel = hardwareMap.get(DcMotor.class, "backRight");

        // set the default direction for the motors
        // (some are reversed because they face opposite directions)
        frontLeftWheel.setDirection(DcMotor.Direction.REVERSE);
        frontRightWheel.setDirection(DcMotor.Direction.FORWARD);
        backLeftWheel.setDirection(DcMotor.Direction.REVERSE);
        backRightWheel.setDirection(DcMotor.Direction.FORWARD);

        // set the motors to run without encoders
        // (encoders make the motor speeds more accurate, but require extra wires)
        frontLeftWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRightWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeftWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRightWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    // robot initialization

    // main loop


    // method to set wheel powers
    public void setWheels(double x, double y, double z) {
        // instantiate motor power variables
        double FLP, FRP, BLP, BRP;

        // compute motor powers
        // check out https://bit.ly/3S9XPIb for more information
        FLP =  x + y + z;
        FRP = -x + y - z;
        BLP = -x + y + z;
        BRP =  x + y - z;

        // make sure that the motor powers don't exceed 1 or -1
        // (a value greater than 1 or -1 will just be truncated down so it's ok, however it will throw off the ratio with the other motors)
        double max = Math.max(Math.max(BLP, FRP), Math.max(BLP, BRP));
        if (max > 1) {
            FLP /= max; FRP /= max;
            BLP /= max; BRP /= max;
        }

        // actually set the motor powers
        frontLeftWheel.setPower(FLP); frontRightWheel.setPower(FRP);
        backLeftWheel.setPower(BLP);  backRightWheel.setPower(BRP);
    }
}
