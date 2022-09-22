package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Config
@TeleOp
public class SpeedyMaybev2 extends OpMode {

    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor back;
    //    DcMotor backRight;
    DcMotor pivot;

    PIDController controller;

    double y;
    double x;
    double z;
    int pivotPosition; // -2000 to 2000

    double d1;//mid 0.61
    double d2;

    public static double PIVOT_SPEED = 2;
    public static double RETURN_SPEED = 1;
    public static int PIVOT_LEFT_BOUNDARY = -50;
    public static int PIVOT_RIGHT_BOUNDARY = 50;
    public static double FRONT_SPEED = 1.0;
    public static double BACK_SPEED = 0.5;
    public static double BACK_DIRECTION = -1.0;
    public static double PIVOT_P = 0.03;
    public static double PIVOT_I = 0;
    public static double PIVOT_D = 0;

    @Override
    public void init() {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        back = hardwareMap.get(DcMotor.class, "back");
        pivot = hardwareMap.get(DcMotor.class, "pivot");

        // reverse direction of motors as needed
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        pivot.setDirection(DcMotorSimple.Direction.REVERSE);
        pivotPosition = 0;

        pivot.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        back.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        controller = new PIDController(PIVOT_P, PIVOT_I, PIVOT_D);
    }

    @Override
    public void loop() {
        // update variables
        y = -gamepad1.left_stick_y;
        x = gamepad1.right_stick_x;


        // turn based on input
        pivotPosition += x * PIVOT_SPEED;
        if (pivotPosition > 0) {
            pivotPosition -= RETURN_SPEED;
        } else {
            pivotPosition += RETURN_SPEED;
        }

        pivotPosition = Math.min(Math.max(PIVOT_LEFT_BOUNDARY, pivotPosition), PIVOT_RIGHT_BOUNDARY);

        // go to position
        controller.setPID(PIVOT_P, PIVOT_I, PIVOT_D);
        controller.setSetPoint(pivotPosition);
        pivot.setPower(controller.calculate(pivot.getCurrentPosition()));
//        if (pivot.getCurrentPosition() < pivotPosition) {
//            pivot.setPower(0.2);
//        } else {
//            pivot.setPower(-0.2);
//        }

        frontLeft.setPower(y*FRONT_SPEED);
        frontRight.setPower(y*FRONT_SPEED);
//        back.setPower(x*BACK_SPEED*BACK_DIRECTION);
        back.setPower((pivotPosition/50.0)*BACK_SPEED*BACK_DIRECTION);
//        backRight.setPower(x*BACK_SPEED*BACK_DIRECTION);


        telemetry.addLine("Pivot: "+ pivotPosition + " HI: " + pivot.getCurrentPosition());
        telemetry.update();
    }
}
