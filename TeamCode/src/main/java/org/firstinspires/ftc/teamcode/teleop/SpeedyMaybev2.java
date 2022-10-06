package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Disabled
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
    int pivotCounter;

    double d1;//mid 0.61
    double d2;

    public static double PIVOT_DELAY = 5;
    public static double TURN_SPEED = 4;
    public static double RETURN_SPEED = 2;
    public static int PIVOT_BOUNDARY_LEFT = -85;
    public static int PIVOT_BOUNDARY_RIGHT = 85;
    public static double FRONT_SPEED = 0.1;
    public static double BACK_SPEED = 0.5;
    public static double BACK_DIRECTION = -1.0;
    public static double PIVOT_P = 0.04;
    public static double PIVOT_I = 0;
    public static double PIVOT_D = 0.0001;
    public static double OFFSET = 0;
    public static double DEADZONE = 5;
    public static double DIFFERENTIAL = 0.5;

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
        pivotCounter = 0;

        pivot.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


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
        pivotCounter++;
        if (pivotCounter >= PIVOT_DELAY) {
            pivotPosition += x * TURN_SPEED;
            if (pivotPosition > DEADZONE) {
//                pivotPosition -= ((pivotPosition+OFFSET)/PIVOT_BOUNDARY_RIGHT) * RETURN_SPEED;
                pivotPosition -= RETURN_SPEED;
            } else if (pivotPosition < -DEADZONE){
//                pivotPosition += ((pivotPosition+OFFSET)/PIVOT_BOUNDARY_RIGHT) * RETURN_SPEED;
                pivotPosition += RETURN_SPEED;
            }
            pivotCounter = 0;
        }

        pivotPosition = Math.min(Math.max(PIVOT_BOUNDARY_LEFT, pivotPosition), PIVOT_BOUNDARY_RIGHT);

        // go to position
        controller.setPID(PIVOT_P, PIVOT_I, PIVOT_D);
        controller.setSetPoint(pivotPosition+OFFSET);
        pivot.setPower(controller.calculate(pivot.getCurrentPosition()));
//        if (pivot.getCurrentPosition() < pivotPosition) {
//            pivot.setPower(0.2);
//        } else {
//            pivot.setPower(-0.2);
//        }


//        frontLeft.setPower(Math.copySign(Math.abs(y*FRONT_SPEED)+y*DIFFERENTIAL*((pivotPosition+OFFSET)/PIVOT_BOUNDARY_RIGHT), y));
//        frontRight.setPower(Math.copySign(Math.abs(y*FRONT_SPEED)-y*DIFFERENTIAL*((pivotPosition+OFFSET)/PIVOT_BOUNDARY_RIGHT), y));
        frontLeft.setPower(y*FRONT_SPEED);
        frontRight.setPower(y*FRONT_SPEED);
//        back.setPower(x*BACK_SPEED*BACK_DIRECTION);
        back.setPower(((pivotPosition+OFFSET)/PIVOT_BOUNDARY_RIGHT)*BACK_SPEED*BACK_DIRECTION);
//        backRight.setPower(x*BACK_SPEED*BACK_DIRECTION);


        telemetry.addLine("Pivot: "+ pivotPosition + " " + OFFSET + " " + pivot.getCurrentPosition());
        telemetry.update();
    }
}
