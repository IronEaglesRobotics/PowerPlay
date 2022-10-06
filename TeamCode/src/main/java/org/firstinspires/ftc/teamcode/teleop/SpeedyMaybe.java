package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
public class SpeedyMaybe extends OpMode {

    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;

    double y;
    double x;
    double z;
    double d1;
    double d2;

    boolean startIsPressed = false;
    boolean mecanumMode = true;

    @Override
    public void init() {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        // reverse direction of motors as needed
//        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
//        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
//        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
//        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {
        // update variables
        y = -gamepad1.left_stick_y;
        x = gamepad1.left_stick_x * 1.1;
        z = gamepad1.right_stick_x;
        d1 = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(z), 1);
        d2 = Math.max(Math.abs(y) + Math.abs(z), 1);

        // set power to the wheels
        if (mecanumMode) {
            frontLeft.setPower((y + x + z)/d1);
            frontRight.setPower((y - x + z)/d1);
            backLeft.setPower(x);
            backRight.setPower(x);
            telemetry.addLine("Mecanum Mode Activated");
        } else {
            frontLeft.setPower((y + z)/d2);
            frontRight.setPower((y - z)/d2);
            backLeft.setPower(x);
            backRight.setPower(x);
            telemetry.addLine("Stealth Wheel Mode Activated");
        }
        telemetry.update();

        // switch between mecanum mode and stealth wheel mode
        if (!startIsPressed && gamepad1.start) {
            mecanumMode = !mecanumMode;
        }
        startIsPressed = gamepad1.start;
    }
}
