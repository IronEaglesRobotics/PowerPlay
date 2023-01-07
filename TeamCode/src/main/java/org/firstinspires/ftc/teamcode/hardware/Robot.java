package org.firstinspires.ftc.teamcode.hardware;

import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.AIMING_KD;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.AIMING_KI;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.AIMING_KP;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.AIMING_TOLERANCE;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.ARM_LEFT;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.ARM_LEFT_TELE;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.ARM_POWER;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.ARM_RIGHT;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.ARM_RIGHT_TELE;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.ARM_UPRIGHT;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.ARM_UPRIGHT_TELE;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.AUTO;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.AUTO_TOP;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.AUTO_TOP2;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.AUTO_TOP3;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.CLAW_AUTO;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.CLAW_CLOSED;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.CLAW_DOWN;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.CLAW_OPEN;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.CLAW_UP;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.GO_SLOW;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.LOW_JUNC;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.SLIDE_LOW;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.SLIDE_MAX;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.SLIDE_POWER_UP;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.STOP;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.WHY_TURN;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.dunk;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Constants.ARM;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Constants.GRIP;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Constants.INVALID_POINT;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Constants.LIFT;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Constants.SLIDE;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Constants.WHEEL_BACK_LEFT;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Constants.WHEEL_BACK_RIGHT;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Constants.WHEEL_FRONT_LEFT;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Constants.WHEEL_FRONT_RIGHT;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Constants.WRIST;

import com.arcrobotics.ftclib.controller.PController;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.opencv.core.Point;

public class Robot {
    private Drive drive;
    private Arm arm;
    private Claw claw;
    private Lift lift;

    private AprilTagCamera autoCamera = null;
    private AimingCamera aimingCamera = null;

    private HardwareMap hardwareMap;

    public Robot init(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;

        this.drive = new Drive().init(hardwareMap);
        this.claw = new Claw().init(hardwareMap);
        this.lift = new Lift().init(hardwareMap);
        this.arm = new Arm().init(hardwareMap);

        return this;
    }

    public void useAimingCamera() {
        if (this.autoCamera != null) {
            this.autoCamera.stopBarcodeWebcam();
            this.autoCamera = null;
        }

        this.aimingCamera = new AimingCamera().init(this.hardwareMap);
    }

    public void useAutoCamera() {
        if (this.aimingCamera != null) {
            this.aimingCamera.stopAimingCamera();
            this.aimingCamera = null;
        }

        this.autoCamera = new AprilTagCamera().init(this.hardwareMap);
    }

    public void aimSync() {
        long startTime = System.currentTimeMillis();

        PIDController pController = new PIDController(AIMING_KP, AIMING_KI, AIMING_KD);
        pController.setSetPoint(320);
        pController.setTolerance(AIMING_TOLERANCE);

        Point topOfJunction;
        while(!pController.atSetPoint() && System.currentTimeMillis() < (startTime + 1500)) {
            topOfJunction = this.getAimingCamera().getTopOfJunction();
            if (topOfJunction == null || topOfJunction == INVALID_POINT) {
                continue;
            }
            double output = pController.calculate(topOfJunction.x) * -1;
            this.getDrive().setInput(0, 0, output);
        }

        this.getDrive().setInput(0, 0, 0);
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
            double y = -gamepad1.left_stick_y / GO_SLOW;
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

            return this;
        }

        public void moveLeft() {
            this.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.arm.setPower(ARM_POWER);
            this.arm.setTargetPosition(ARM_LEFT);
        }

        public void moveMid() {
            this.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.arm.setTargetPosition(ARM_UPRIGHT);
            this.arm.setPower(ARM_POWER);
        }

        public void moveRight() {
            this.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.arm.setPower(ARM_POWER);
            this.arm.setTargetPosition(ARM_RIGHT);
        }

        public void moveLeftTele() {
            this.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.arm.setPower(ARM_POWER);
            this.arm.setTargetPosition(ARM_LEFT_TELE);
        }

        public void moveMidTele() {
            this.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.arm.setTargetPosition(ARM_UPRIGHT_TELE);
            this.arm.setPower(ARM_POWER);
        }

        public void moveRightTele() {
            this.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.arm.setPower(ARM_POWER);
            this.arm.setTargetPosition(ARM_RIGHT_TELE);
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
        private boolean isOpen = false;

        public Claw init(HardwareMap hardwareMap) {
            this.clawGrip = hardwareMap.get(Servo.class, GRIP);
            this.clawTurn = hardwareMap.get(Servo.class, WRIST);

            return this;
        }

        public void open() {
            this.clawGrip.setPosition(CLAW_OPEN);
            this.isOpen = true;
        }

        public void autoOpen() {
            this.clawGrip.setPosition(CLAW_AUTO);
        }

        public void close() {
            this.clawGrip.setPosition(CLAW_CLOSED);
            this.isOpen = false;
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

        public boolean isOpen() {
            return this.isOpen;
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
            this.slide.setTargetPosition(SLIDE_MAX);
            this.slide.setPower(SLIDE_POWER_UP);
            this.slide2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.slide2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            this.slide2.setTargetPosition(SLIDE_MAX);
            this.slide2.setPower(SLIDE_POWER_UP);
        }

        public void lowJunc() {
            this.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            this.slide.setTargetPosition(LOW_JUNC);
            this.slide.setPower(SLIDE_POWER_UP);
            this.slide2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.slide2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            this.slide2.setTargetPosition(LOW_JUNC);
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
            this.slide.setTargetPosition(SLIDE_LOW);
            this.slide.setPower(SLIDE_POWER_UP);
            this.slide2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.slide2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            this.slide2.setTargetPosition(SLIDE_LOW);
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

        public void autoTop() {
            this.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            this.slide.setTargetPosition(AUTO_TOP);
            this.slide.setPower(SLIDE_POWER_UP);
            this.slide2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.slide2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            this.slide2.setTargetPosition(AUTO_TOP);
            this.slide2.setPower(SLIDE_POWER_UP);
        }

        public void autoTop2() {
            this.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            this.slide.setTargetPosition(AUTO_TOP2);
            this.slide.setPower(SLIDE_POWER_UP);
            this.slide2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.slide2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            this.slide2.setTargetPosition(AUTO_TOP2);
            this.slide2.setPower(SLIDE_POWER_UP);
        }

        public void autoTop3() {
            this.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            this.slide.setTargetPosition(AUTO_TOP3);
            this.slide.setPower(SLIDE_POWER_UP);
            this.slide2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.slide2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            this.slide2.setTargetPosition(AUTO_TOP3);
            this.slide2.setPower(SLIDE_POWER_UP);
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
