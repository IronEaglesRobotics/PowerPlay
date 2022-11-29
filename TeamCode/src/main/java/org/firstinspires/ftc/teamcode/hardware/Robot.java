package org.firstinspires.ftc.teamcode.hardware;

import static org.firstinspires.ftc.teamcode.util.Configurables.ARM_D;
import static org.firstinspires.ftc.teamcode.util.Configurables.ARM_I;
import static org.firstinspires.ftc.teamcode.util.Configurables.ARM_LEFT;
import static org.firstinspires.ftc.teamcode.util.Configurables.ARM_P;
import static org.firstinspires.ftc.teamcode.util.Configurables.ARM_POWER;
import static org.firstinspires.ftc.teamcode.util.Configurables.ARM_RIGHT;
import static org.firstinspires.ftc.teamcode.util.Configurables.AUTO;
import static org.firstinspires.ftc.teamcode.util.Configurables.CLAW_AUTO;
import static org.firstinspires.ftc.teamcode.util.Configurables.CLAW_CLOSED;
import static org.firstinspires.ftc.teamcode.util.Configurables.CLAW_DOWN;
import static org.firstinspires.ftc.teamcode.util.Configurables.CLAW_OPEN;
import static org.firstinspires.ftc.teamcode.util.Configurables.CLAW_UP;
import static org.firstinspires.ftc.teamcode.util.Configurables.SLIDE_LOW;
import static org.firstinspires.ftc.teamcode.util.Configurables.SLIDE_MAX;
import static org.firstinspires.ftc.teamcode.util.Configurables.SLIDE_MED;
import static org.firstinspires.ftc.teamcode.util.Configurables.SLIDE_POWER_UP;
import static org.firstinspires.ftc.teamcode.util.Configurables.STOP;
import static org.firstinspires.ftc.teamcode.util.Configurables.dunk;
import static org.firstinspires.ftc.teamcode.util.Constants.ARM;
import static org.firstinspires.ftc.teamcode.util.Constants.GRIP;
import static org.firstinspires.ftc.teamcode.util.Constants.LIFT;
import static org.firstinspires.ftc.teamcode.util.Constants.SLIDE;
import static org.firstinspires.ftc.teamcode.util.Constants.WHEEL_BACK_LEFT;
import static org.firstinspires.ftc.teamcode.util.Constants.WHEEL_BACK_RIGHT;
import static org.firstinspires.ftc.teamcode.util.Constants.WHEEL_FRONT_LEFT;
import static org.firstinspires.ftc.teamcode.util.Constants.WHEEL_FRONT_RIGHT;
import static org.firstinspires.ftc.teamcode.util.Constants.WRIST;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Robot {
    private Drive drive;
    private Arm arm;
    private Claw claw;
    private Lift lift;

    private AprilTagCamera autoCamera = null;
    private AimingCamera aimingCamera = null;

    public Robot init(HardwareMap hardwareMap) {
        return this.init(hardwareMap, null);
    }

    public enum Vision {
        AUTO, AIMING
    }

    public Robot init(HardwareMap hardwareMap, Vision vision) {
        if (vision == Vision.AUTO) {
            this.autoCamera = new AprilTagCamera().init(hardwareMap);
        } else if (vision == Vision.AIMING) {
            this.aimingCamera = new AimingCamera().init(hardwareMap);
        }
        this.drive = new Drive().init(hardwareMap);
        this.claw = new Claw().init(hardwareMap);
        this.lift = new Lift().init(hardwareMap);
        this.arm = new Arm().init(hardwareMap);

        return this;
    }

    public AimingCamera getAimingCamera() {
        return this.aimingCamera;
    }

    public AprilTagCamera getAutoCamera() {
        return this.autoCamera;
    }

    public Drive getDrive() {
        return this.drive;
    }

    public Lift getLift() {
        return this.lift;
    }

    public Arm getArm() {
        return this.arm;
    }

    public Claw getClaw() {
        return this.claw;
    }

    public static class Drive {
        private DcMotor frontLeft = null;
        private DcMotor frontRight = null;
        private DcMotor backLeft = null;
        private DcMotor backRight = null;

        public Drive init(HardwareMap hardwareMap) {
            // Drive
            this.frontLeft = hardwareMap.get(DcMotor.class, WHEEL_FRONT_LEFT);
            this.frontRight = hardwareMap.get(DcMotor.class, WHEEL_FRONT_RIGHT);
            this.backLeft = hardwareMap.get(DcMotor.class, WHEEL_BACK_LEFT);
            this.backRight = hardwareMap.get(DcMotor.class, WHEEL_BACK_RIGHT);
            this.frontLeft.setDirection(DcMotor.Direction.REVERSE);
            this.frontRight.setDirection(DcMotor.Direction.FORWARD);
            this.backLeft.setDirection(DcMotor.Direction.REVERSE);
            this.backRight.setDirection(DcMotor.Direction.FORWARD);
            this.frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            this.frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            this.backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            this.backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            return this;
        }

        public void setInput(Gamepad gamepad1, Gamepad gamepad2) {
            double x = gamepad1.left_stick_x / 1.4;
            double y = -gamepad1.left_stick_y / 1.4;
            double z = gamepad1.right_stick_x / 1.25;

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

    public static class Arm {
        private DcMotor arm = null;

        public Arm init(HardwareMap hardwareMap) {
            this.arm = hardwareMap.get(DcMotor.class, ARM);
            this.arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            this.arm.setTargetPosition(0);
            this.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

            return this;
        }

        public void moveLeft() {
            this.arm.setPower(ARM_POWER);
            this.arm.setTargetPosition(ARM_LEFT);
        }

        public void moveRight() {
            this.arm.setPower(ARM_POWER);
            this.arm.setTargetPosition(ARM_RIGHT);
        }

        public void drop() {
            this.arm.setPower(0);
        }

        public int getCurrentPosition() {
            return this.arm.getCurrentPosition();
        }
    }

    public static class Claw {
        private Servo clawTurn = null;
        private Servo clawGrip = null;

        public Claw init(HardwareMap hardwareMap) {
            this.clawGrip = hardwareMap.get(Servo.class, GRIP);
            this.clawTurn = hardwareMap.get(Servo.class, WRIST);

            return this;
        }

        public void open() {
            this.clawGrip.setPosition(CLAW_OPEN);
        }

        public void autoOpen() {
            this.clawGrip.setPosition(CLAW_AUTO);
        }

        public void close() {
            this.clawGrip.setPosition(CLAW_CLOSED);
        }

        public void auto() {
            this.clawGrip.setPosition(AUTO);
        }

        public void twistUp() {
            this.clawTurn.setPosition(CLAW_UP);
        }

        public void twistDown() {
            this.clawTurn.setPosition(CLAW_DOWN);
        }
    }

    public static class Lift {

//        public int getPosition() {
//            return slide.getCurrentPosition();
//        }

        public DcMotor slide = null;
        public DcMotor slide2 = null;


        public Lift init(HardwareMap hardwareMap) {
            this.slide = hardwareMap.get(DcMotor.class, LIFT);
            this.slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            this.slide.setTargetPosition(0);
            this.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            this.slide.setDirection(DcMotorSimple.Direction.REVERSE);

            this.slide2 = hardwareMap.get(DcMotor.class, SLIDE);
            this.slide2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            this.slide2.setTargetPosition(0);
            this.slide2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.slide2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            this.slide2.setDirection(DcMotorSimple.Direction.FORWARD);

            return this;
        }

        public void slideUp() {
            this.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            this.slide.setTargetPosition(SLIDE_MED);
            this.slide.setPower(SLIDE_POWER_UP);
            this.slide2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.slide2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            this.slide2.setTargetPosition(SLIDE_MED);
            this.slide2.setPower(SLIDE_POWER_UP);
        }

        public void slideUpFree() {
            this.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            this.slide.setTargetPosition(SLIDE_MAX);
            this.slide.setPower(SLIDE_POWER_UP);
            this.slide2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.slide2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            this.slide2.setTargetPosition(SLIDE_MAX);
            this.slide2.setPower(SLIDE_POWER_UP);
        }

        public void dunk() {
            this.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            this.slide.setTargetPosition(dunk);
            this.slide.setPower(SLIDE_POWER_UP);
            this.slide2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.slide2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            this.slide2.setTargetPosition(dunk);
            this.slide2.setPower(SLIDE_POWER_UP);
        }

        public void slideLow() {
            this.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            this.slide.setTargetPosition(SLIDE_LOW);
            this.slide.setPower(SLIDE_POWER_UP);
            this.slide2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.slide2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            this.slide2.setTargetPosition(SLIDE_LOW);
            this.slide2.setPower(SLIDE_POWER_UP);

        }
        public void slideMed() {
            this.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            this.slide.setTargetPosition(SLIDE_MAX);
            this.slide.setPower(SLIDE_POWER_UP);
            this.slide2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.slide2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            this.slide2.setTargetPosition(SLIDE_MAX);
            this.slide2.setPower(SLIDE_POWER_UP);
        }

        public void slideDown() {
            this.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            this.slide.setPower(this.slide.getCurrentPosition() < STOP ? 1.0 : 0.0);
            this.slide.setTargetPosition(0);
            this.slide2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.slide2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            this.slide2.setPower(this.slide.getCurrentPosition() < STOP ? 1.0 : 0.0);
            this.slide2.setTargetPosition(0);
        }

        public void slideStop() {
            this.slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            this.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.slide.setTargetPosition(slide.getCurrentPosition());
            this.slide.setPower(1.0);
            this.slide2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            this.slide2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.slide2.setTargetPosition(slide2.getCurrentPosition());
            this.slide2.setPower(1.0);
        }

        public String getTelemetry() {
            return String.format("position: %s", slide.getCurrentPosition());
        }
    }
}
