package org.firstinspires.ftc.teamcode.hardware;

import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.ARM_AUTO;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.ARM_LEFT;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.ARM_LEFT_TELE;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.ARM_POWER;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.ARM_RIGHT;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.ARM_RIGHT_TELE;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.ARM_SCORE;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.ARM_SCORE_TELE;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.ARM_TILT;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.ARM_UPRIGHT;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.ARM_UPRIGHT_TELE;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.AUTOSCORE;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.AUTO_TOP1;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.CLAW_CLOSED;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.CLAW_DOWN;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.CLAW_OPEN;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.CLAW_OPENWIDE;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.CLAW_UP;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.DEPLOY;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.LOW_JUNC;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.SLIDE_HIGH_AUTO;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.SLIDE_LOW_AUTO;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.SLIDE_MAX;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.SLIDE_MID;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.SLIDE_MID_AUTO;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.SLIDE_MID_TELE;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.SLIDE_POWER_DOWN;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.SLIDE_POWER_UP;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.STOW;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Constants.ARM;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Constants.GRIP;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Constants.LIFT;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Constants.SLIDE;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Constants.WRIST;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

public class Robot {
    private SampleMecanumDrive drive;
    private Arm arm;
    private Claw claw;
    private Lift lift;
    private WALE wale;
    private AprilTagCamera autoCamera;
    private HardwareMap hardwareMap;

    public Robot init(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;

        this.drive = new SampleMecanumDrive(hardwareMap);
        this.claw = new Claw().init(hardwareMap);
        this.lift = new Lift().init(hardwareMap);
        this.arm = new Arm().init(hardwareMap);
        this.wale = new WALE().init(hardwareMap);

        return this;
    }

    public void useAutoCamera() {
        this.autoCamera = new AprilTagCamera().init(this.hardwareMap);
    }

    public AprilTagCamera getAutoCamera() {
        return this.autoCamera;
    }

    public SampleMecanumDrive getDrive() {
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

    public WALE getWale() { return this.wale; }

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

        public void move(int position) {
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

        public void moveScoreTele() {
            this.move(ARM_SCORE_TELE);
        }

        public void moveMidTele() {
            this.move(ARM_UPRIGHT_TELE);
        }

        public void moveRightTele() {
            this.move(ARM_RIGHT_TELE);
        }

        public void moveScore() {
            this.move(ARM_SCORE);
        }

        public void moveAuto() {
            this.move(ARM_AUTO);
        }

        public void moveTilt() {
            this.move(ARM_TILT);
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

        public void slideTo(int position, double power) {
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
            this.slide.setPower(0);
            this.slide.setTargetPosition(0);
            this.slide2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.slide2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            this.slide2.setPower(0);
            this.slide2.setTargetPosition(0);
        }

        public int getTargetPosition() {
            return this.slide.getTargetPosition();
        }

        public int getCurrentPosition() {
            return this.slide.getCurrentPosition();
        }

        public void slideMax() {
            this.slideTo(SLIDE_MAX, SLIDE_POWER_UP);
        }

        public void slideScorAuto() {this.slideTo(AUTOSCORE, SLIDE_POWER_UP);}

        public void lowJunction() {
            this.slideTo(LOW_JUNC, SLIDE_POWER_UP);
        }

        public void slideMedTele() {
            this.slideTo(SLIDE_MID_TELE, SLIDE_POWER_UP);
        }

        public void slideMed() {
            this.slideTo(SLIDE_MID, SLIDE_POWER_UP);
        }

        public void slideHighAuto() {
            this.slideTo(SLIDE_HIGH_AUTO, SLIDE_POWER_UP);
        }

        public void slideMidAuto() {
            this.slideTo(SLIDE_MID_AUTO, SLIDE_POWER_UP);
        }

        public void slideLowAuto() {
            this.slideTo(SLIDE_LOW_AUTO, SLIDE_POWER_UP);
        }


        public void autoTop() {
            this.slideTo(AUTO_TOP1,SLIDE_POWER_DOWN);
        }

        public void slideStop() {this.slideTo(slide.getCurrentPosition(), 1.0);}

        public String getTelemetry() {
            return String.format("position: %s", slide.getCurrentPosition());
        }

        public void slideToCone(int height) {this.slideTo(height,SLIDE_POWER_DOWN);}
        public void slideToHeight(int slideHeight) {this.slideTo(slideHeight,SLIDE_POWER_UP);}

    }

    public static class WALE {
        private RevTouchSensor touch;
        private Rev2mDistanceSensor distance;

        private Servo servo;

        public double getDistance(DistanceUnit distnaceUnit) {
            return this.distance.getDistance(distnaceUnit);
        }

        public boolean isPressed() {
            return this.touch.isPressed();
        }

        public WALE init(HardwareMap hardwareMap) {
            this.touch = hardwareMap.get(RevTouchSensor.class, "touch");
            this.distance = hardwareMap.get(Rev2mDistanceSensor.class, "distance");
            this.servo = hardwareMap.get(Servo.class, "wale");

            return this;
        }

        public void deploy() {
            this.servo.setPosition(DEPLOY);
        }

        public void stow() {
            this.servo.setPosition(STOW);
        }
    }
}
