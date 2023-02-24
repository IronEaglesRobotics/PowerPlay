package org.firstinspires.ftc.teamcode.hardware;

import static org.firstinspires.ftc.teamcode.hardware.Arm.Position.INTAKE;
import static org.firstinspires.ftc.teamcode.hardware.Claw.Position.UPRIGHT;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.PoseStorage;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.util.CameraPosition;

@Config
public class Robot {
    public SampleMecanumDrive drive;
    public Slides slides;
    public Arm arm;
    public Claw claw;
    public Camera camera;
    private boolean camEnabled = false;

    public double macroStartTime = 0;
    public int macroState = 0;
    public int runningMacro = 0; // 0 = no macro | 1 = low macro | 2 = mid macro | 3 = high macro
    public int lastMacro = 0;

    public static double clawWait = 0.2;

    public Robot(HardwareMap hardwareMap) {
        drive = new SampleMecanumDrive(hardwareMap);
        if (!PoseStorage.AutoJustEnded) {
            PoseStorage.currentPose = new Pose2d(0, 0, Math.toRadians(0));
        }
        drive.setPoseEstimate(PoseStorage.currentPose);
        slides = new Slides(hardwareMap);
        claw = new Claw(hardwareMap, UPRIGHT);
        arm = new Arm(hardwareMap, INTAKE);
        camEnabled = false;
    }

    public Robot(HardwareMap hardwareMap, Arm.Position armPos, Claw.Position clawPos, CameraPosition cameraPosition) {
        drive = new SampleMecanumDrive(hardwareMap);
        drive.setPoseEstimate(PoseStorage.currentPose);
        slides = new Slides(hardwareMap);
        claw = new Claw(hardwareMap, clawPos);
        arm = new Arm(hardwareMap, armPos);
        camera = new Camera(cameraPosition);
        camera.init(hardwareMap);
        camEnabled = true;
    }

    public void extendMacro(Slides.Position pos, double runTime) {
        switch(macroState) {
            case(0):
                macroStartTime = runTime;
                claw.close();
                macroState ++;
                break;
            case(1):
                if (runTime > macroStartTime + clawWait) {
                    macroState ++;
                }
                break;
            case(2):
                macroStartTime = runTime;
                slides.setTarget(pos);
                arm.goToScore();
                claw.flipped();
                macroState = 0;
                lastMacro = runningMacro;
                runningMacro = 0;
                break;
        }
    }

    public void resetMacro(int pos, double runTime) {
        switch(macroState) {
            case(0):
                macroStartTime = runTime;
                claw.open();
                macroState++;
                break;
            case(1):
                if (runTime > macroStartTime + clawWait) {
                    macroState ++;
                }
                break;
            case(2):
                macroStartTime = runTime;
                slides.setTarget(pos);
                arm.goToIntake();
                claw.upright();
                macroState = 0;
                runningMacro = 0;
                lastMacro = 0;
        }
    }

    public void resetMacroEnd(int pos, double runTime) {
        switch(macroState) {
            case(0):
                macroStartTime = runTime;
                claw.strongOpen();
                macroState++;
                break;
            case(1):
                if (runTime > macroStartTime + clawWait) {
                    macroState ++;
                }
                break;
            case(2):
                macroStartTime = runTime;
                slides.setTarget(pos);
                arm.goToIntake();
                claw.upright();
                macroState = 0;
                runningMacro = 0;
                lastMacro = 0;
        }
    }

    public void update(double runTime) {
        drive.update();
        slides.update(runTime);
        claw.update();
        arm.update();
    }

    public String getTelemetry() {
//        if (camEnabled) {
//            return String.format("Slides: %s\nClaw: %s\nCamera: %s", slides.getTelemetry(), claw.getTelemetry(), camera.getTelemetry());
//        } else {
//            return String.format("Slides: %s\nClaw: %s", claw.getTelemetry());
//        }
        return String.format("%s \n%s \nArm: %s \nSlides: %s", drive.getTelemetry(), claw.getTelemetry(), arm.getTelemetry(), slides.getTelemetry());
    }
}
