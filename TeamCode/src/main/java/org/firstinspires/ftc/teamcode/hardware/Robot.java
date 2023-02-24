package org.firstinspires.ftc.teamcode.hardware;

import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.ARM_LEFT;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.ARM_LEFT_TELE;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.ARM_POWER;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.ARM_RIGHT;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.ARM_RIGHT_TELE;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.ARM_SCORE;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.ARM_UPRIGHT;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.ARM_UPRIGHT_TELE;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.AUTO_TOP;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.AUTO_TOP2;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.AUTO_TOP3;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.AUTO_TOP4;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.AUTO_TOP5;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.CLAW_CLOSED;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.CLAW_DOWN;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.CLAW_OPEN;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.CLAW_OPENWIDE;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.CLAW_UP;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.GO_SLOW;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.LOW_DUNK;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.LOW_JUNC;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.SLIDE_LOW;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.SLIDE_MAX;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.SLIDE_POWER_UP;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.STOP;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.WHY_TURN;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Constants.ARM;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Constants.GRIP;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Constants.LIFT;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Constants.SLIDE;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Constants.WHEEL_BACK_LEFT;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Constants.WHEEL_BACK_RIGHT;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Constants.WHEEL_FRONT_LEFT;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Constants.WHEEL_FRONT_RIGHT;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Constants.WRIST;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.opencv.engine.OpenCVEngineInterface;

public class Robot {
    private Drive drive;
    private Arm arm;
    private Claw claw;
    private Lift lift;
    private AprilTagCamera autoCamera;
    private HardwareMap hardwareMap;

    public Robot init(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;

        this.drive = new Drive().init(hardwareMap);
        this.claw = new Claw().init(hardwareMap);
        this.lift = new Lift().init(hardwareMap);
        this.arm = new Arm().init(hardwareMap);

        return this;
    }

    public void useAutoCamera() {
        this.autoCamera = new AprilTagCamera().init(this.hardwareMap);
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
            this.frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            this.frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            this.backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            this.backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            return this;
        }

        public void setInput(double x, double y, double z) {
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

        public void setInput(Gamepad gamepad1, Gamepad gamepad2) {
            double x = gamepad1.left_stick_x / GO_SLOW;
            if (Math.abs(x) < 0.225) {
                x = 0;
            }
            double y = -gamepad1.left_stick_y / GO_SLOW;
            if (Math.abs(y) < 0.225) {
                y = 0;
            }
            double z = gamepad1.right_stick_x / WHY_TURN;

            setInput(x, y, z);
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

            this.hold();

            return this;
        }

        public void hold() {
            this.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.arm.setTargetPosition(this.arm.getCurrentPosition());
            this.arm.setPower(ARM_POWER);
        }

        private void move(int position) {
            this.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.arm.setPower(ARM_POWER);
            this.arm.setTargetPosition(position);
        }

        public void moveLeft() {
            this.move(ARM_LEFT);
        }

        public void moveMid() {
            this.move(ARM_UPRIGHT);
        }

        public void moveRight() {
            this.move(ARM_RIGHT);
        }

        public void moveLeftTele() {
            this.move(ARM_LEFT_TELE);
        }

        public void moveMidTele() {
            this.move(ARM_UPRIGHT_TELE);
        }

        public void moveRightTele() {
            this.move(ARM_RIGHT_TELE);
        }

        public void moveScore() {this.move(ARM_SCORE);}

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
            this.clawGrip.scaleRange(0.01, 1);

            this.close();

            return this;
        }

        public double getCurrentPosition() {
            return this.clawGrip.getPosition();
        }

        public void close() {
            this.clawGrip.setPosition(CLAW_CLOSED);
        }

        public void open() {
            this.clawGrip.setPosition(CLAW_OPEN);
        }

        public void openWide() {
            this.clawGrip.setPosition(CLAW_OPENWIDE);
        }

        public void twistUp() {
            this.clawTurn.setPosition(CLAW_UP);
        }

        public void twistDown() {
            this.clawTurn.setPosition(CLAW_DOWN);
        }

    }

    public static class Lift {
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

        private void slideTo(int position, double power) {
            this.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            this.slide.setTargetPosition(position);
            this.slide.setPower(power);

            this.slide2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.slide2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            this.slide2.setTargetPosition(position);
            this.slide2.setPower(power);
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

        public void slideMax() {
            this.slideTo(SLIDE_MAX, SLIDE_POWER_UP);
        }

        public void lowJunction() {
            this.slideTo(LOW_JUNC, SLIDE_POWER_UP);
        }

        public void slideLow() {
            this.slideTo(SLIDE_LOW, SLIDE_POWER_UP);
        }

        public void lowDunk() {
            this.slideTo(LOW_DUNK, SLIDE_POWER_UP);
        }

        public void slideMed() {
            this.slideTo(SLIDE_LOW, SLIDE_POWER_UP);
        }

        public void autoTop() {
            this.slideTo(AUTO_TOP, SLIDE_POWER_UP);
        }

        public void autoTop2() {
            this.slideTo(AUTO_TOP2, SLIDE_POWER_UP);
        }

        public void autoTop3() {
            this.slideTo(AUTO_TOP3, SLIDE_POWER_UP);
        }

        public void autoTop4() {
            this.slideTo(AUTO_TOP4, SLIDE_POWER_UP);
        }

        public void autoTop5() {
            this.slideTo(AUTO_TOP5, SLIDE_POWER_UP);
        }

        public void slideStop() {
            this.slideTo(slide.getCurrentPosition(), 1.0);
        }

        public String getTelemetry() {
            return String.format("position: %s", slide.getCurrentPosition());
        }

        public void move(int height) {
            this.slideTo(height, SLIDE_POWER_UP);
        }
    }
}
