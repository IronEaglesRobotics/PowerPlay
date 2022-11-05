package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.hardware.Camera;
import org.firstinspires.ftc.teamcode.util.CameraPosition;

@Config
@TeleOp(name = "Tele-OP Sihan", group = "Development")
public class MainTeleOp extends OpMode {
    // instantiate motor variables
    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;
    private Servo servoclawturn = null;
    private Servo servoclawgrip = null;
    private DcMotor slide = null;
    private DcMotor slide2 = null;
    private ColorRangeSensor coloursensor = null;
    private DcMotor armswing = null;
    private Camera camera = null;
    public static double servoclawopen = 0.496;
    public static double servoclawclose = 0.25;
    private int slidePosition = 0;
    public static double slidePowerUp = 0.8;
    public static double slidePowerDown = 0.5;
    public static int maxSlide = 4080;
    private int targetArmPosition;
    private static double armPower = 0.;
    public static double backArmPower = 0.45;
    public static double positiveCushion = 175;
    public static double negativeCushion = -74;
    public static double maxArmPower = 0.2;
    private boolean prevUpPressed = false;
    private boolean prevDownPressed = false;

    // robot initialization
    @Override
    public void init() {
        camera = new Camera(hardwareMap, CameraPosition.RIGHT);
        camera.initBarcodeWebcam();
        servoclawgrip = hardwareMap.get(Servo.class, "servo");
        servoclawturn = hardwareMap.get(Servo.class, "servoturn");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        slide = hardwareMap.get(DcMotor.class, "slide");
        armswing = hardwareMap.get(DcMotor.class, "armswing");
        slide2 = hardwareMap.get(DcMotor.class, "slide2");
        coloursensor = hardwareMap.get(ColorRangeSensor.class, "coloursensor");

//      Mecanum drive
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

//      slide and arm
        slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slide2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slide.setTargetPosition(slidePosition);
        slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slide.setDirection(DcMotorSimple.Direction.REVERSE);
        slide2.setTargetPosition(slidePosition);
        slide2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slide2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slide.setPower(slidePowerUp);
        slide2.setPower(slidePowerUp);
        armswing.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armswing.setTargetPosition(0);
        armswing.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armswing.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    // main loop
    public void servo_move() {
        if (gamepad2.a || gamepad1.b) {
            servoclawgrip.setPosition(servoclawclose);
        } else {
            servoclawgrip.setPosition(servoclawopen);
        }
    }

    @Override
    public void loop() {

        // get input from the gamepad
        // (up on the gamepad sticks is considered negative 1, so that's why you have to add a negative to the y)
        double x = gamepad1.left_stick_x / 1.75;
        double y = -gamepad1.left_stick_y / 1.75;
        double z = gamepad1.right_stick_x / 1.25;
        boolean upPressed = gamepad2.y || gamepad1.y;
        boolean downPressed = gamepad2.x || gamepad1.x;
        boolean pressright = gamepad2.dpad_right;
        boolean pressleft = gamepad2.dpad_left;
        double currentArmPosition = armswing.getCurrentPosition();

        if (pressright && currentArmPosition < positiveCushion) {
            armPower = currentArmPosition > 42 ? -backArmPower : maxArmPower;
        } else if (pressleft && currentArmPosition > negativeCushion) {
            armPower = currentArmPosition < 42 ? backArmPower : -maxArmPower;
        } else {
            armPower = 0;
        }
        this.armswing.setPower(armPower);

        // -5 - Starting
        // 42 - Upright
        // -91 - Forward 185
        // 173 - Backward -84

        if (currentArmPosition < 42) {
            servoclawturn.setPosition(0.675);
        } else {
            servoclawturn.setPosition(0.02);
        }

        if ((upPressed && !prevUpPressed) && slidePosition + 100 <= maxSlide) {
            slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            slide.setPower(slidePowerUp);

            slidePosition = maxSlide;
            slide.setTargetPosition(slidePosition);
        } else if ((downPressed && !prevDownPressed) && slidePosition - 100 >= -3) {
            slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

            slide.setPower(0);
        } else if ((prevUpPressed != upPressed) || (prevDownPressed != downPressed)) {
            slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            slidePosition = slide.getCurrentPosition();
            slide.setTargetPosition(slidePosition);
            slide.setPower(1.0);
        }

        prevUpPressed = upPressed;
        prevDownPressed = downPressed;

        setWheels(x, y, z);
        servo_move();

        telemetry.addData("armswing", armswing.getCurrentPosition());
        telemetry.addData("slide", slide.getCurrentPosition());
        telemetry.addData("coloursensor", coloursensor.getDistance(DistanceUnit.MM));
        telemetry.addData("servoclawturn", servoclawturn.getPosition());
        telemetry.addData("servoclawgrip", servoclawgrip.getPosition());
        telemetry.update();
    }

    public void setWheels(double x, double y, double z) {
        // instantiate motor power variables
        double flPower, frPower, blPower, brPower;

        flPower = x + y + z;
        frPower = -x + y - z;
        blPower = -x + y + z;
        brPower = x + y - z;

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