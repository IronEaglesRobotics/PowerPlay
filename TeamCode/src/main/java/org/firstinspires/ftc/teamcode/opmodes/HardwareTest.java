package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.teamcode.drive.opmode.util.Constants.WHEEL_BACK_LEFT;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Constants.WHEEL_BACK_RIGHT;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Constants.WHEEL_FRONT_LEFT;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Constants.WHEEL_FRONT_RIGHT;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
@Disabled

@Autonomous(name = "HardwareTest", group = "Development")
public class HardwareTest extends LinearOpMode {
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;

    @Override
    public void runOpMode() throws InterruptedException {
        this.frontLeft = hardwareMap.get(DcMotor.class, WHEEL_FRONT_LEFT);
        this.frontRight = hardwareMap.get(DcMotor.class, WHEEL_FRONT_RIGHT);
        this.backLeft = hardwareMap.get(DcMotor.class, WHEEL_BACK_LEFT);
        this.backRight = hardwareMap.get(DcMotor.class, WHEEL_BACK_RIGHT);

        stopMotors();
        waitForStart();

        frontLeft.setPower(0.1);
        sleep(1000);
        stopMotors();

        frontRight.setPower(0.1);
        sleep(1000);
        stopMotors();

        backLeft.setPower(0.1);
        sleep(1000);
        stopMotors();

        backRight.setPower(0.1);
        sleep(1000);
        stopMotors();
    }

    private void stopMotors() {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
}
