package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.controller.Controller;
import org.firstinspires.ftc.teamcode.hardware.Robot;

@Disabled
@TeleOp
public class AquilaIsAwesome extends OpMode {
    private Robot robot;

    Controller driver1;
    Controller driver2;
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;

    public static double turboForward = 1;
    public static double turboTurn = 0.8;
    public static double slowForward = 0.5;
    public static double slowTurn = 0.4;

    @Override
    public void init() {
        driver1 = new Controller(gamepad1);
        driver2 = new Controller(gamepad2);

        frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
        backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
        backRight = hardwareMap.get(DcMotorEx.class, "backRight");

//        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
//        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    @Override
    public void loop() {
        driver1.update();
        driver2.update();

        double x = -driver1.getLeftStick().getY();
//        double y = driver1.getLeftStick().getX();
        double z = driver1.getRightStick().getX();

        if (driver1.getY().isPressed()) {
            x *= turboForward;
            z *= turboTurn;
        } else {
            x *= slowForward;
            z *= slowTurn;
        }

        frontLeft.setPower(x-z);
        frontRight.setPower(x+z);
        backLeft.setPower(x-z);
        backRight.setPower(x+z);

    }
}
