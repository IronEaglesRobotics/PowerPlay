package org.firstinspires.ftc.teamcode.hardware;

import static org.firstinspires.ftc.teamcode.hardware.Arm.Position.AUTO;
import static org.firstinspires.ftc.teamcode.hardware.Arm.Position.INTAKE;
import static org.firstinspires.ftc.teamcode.hardware.Arm.Position.PICKUP;
import static org.firstinspires.ftc.teamcode.hardware.Arm.Position.SCORE;
import static org.firstinspires.ftc.teamcode.hardware.Claw.Position.UPRIGHT;
import static org.firstinspires.ftc.teamcode.hardware.Rad.Position.CLOSED;
import static org.firstinspires.ftc.teamcode.hardware.Rad.Position.OPEN;

import android.transition.Slide;

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
    public Rad rad;
    public Camera camera;
    private boolean camEnabled = false;

    public double macroStartTime = 0;
    public int macroState = 0;
    public int runningMacro = 0; // 0 = no macro | 1 = low macro | 2 = mid macro | 3 = high macro | 4 = pickup macro
    public int lastMacro = 0;

    public static double clawWait = 0.2;

    public Robot(HardwareMap hardwareMap) {
        drive = new SampleMecanumDrive(hardwareMap);
//        if (!PoseStorage.AutoJustEnded) {
//            PoseStorage.currentPose = new Pose2d(0, 0, Math.toRadians(0));
//        }
//        drive.setPoseEstimate(PoseStorage.currentPose);
        slides = new Slides(hardwareMap);
        arm = new Arm(hardwareMap, INTAKE);
        claw = new Claw(hardwareMap, UPRIGHT);
        rad = new Rad(hardwareMap, CLOSED);
        camEnabled = false;
    }

    public Robot(HardwareMap hardwareMap, Arm.Position armPos, Claw.Position clawPos, CameraPosition cameraPosition) {
        drive = new SampleMecanumDrive(hardwareMap);
//        drive.setPoseEstimate(PoseStorage.currentPose);
        slides = new Slides(hardwareMap);
        claw = new Claw(hardwareMap, clawPos);
        arm = new Arm(hardwareMap, armPos);
        if (armPos == PICKUP || armPos == AUTO) {
            rad = new Rad(hardwareMap, OPEN);
        } else {
            rad = new Rad(hardwareMap, CLOSED);
        }
        camera = new Camera(cameraPosition);
        camera.init(hardwareMap);
        camEnabled = true;
    }

    public void extendMacro(Slides.Position slidePos, Arm.Position armPos, double runTime) {
        switch(macroState) {
            case(0):
                macroStartTime = runTime;
                if (armPos == PICKUP) {
                    rad.open();
                } else {
                    claw.close();
                }
                macroState ++;
                break;
            case(1):
                if (runTime > macroStartTime + clawWait) {
                    macroState ++;
                }
                break;
            case(2):
                if (armPos != PICKUP) {
                    rad.close();
                } else {
                    claw.open();
                }
                macroStartTime = runTime;
                slides.setTarget(slidePos);
                if (slidePos == Slides.Position.LOW && armPos == SCORE) {
                    arm.setTarget(0.61);
                }
                arm.setTarget(armPos);
                claw.flipped();
                macroState = 0;
                lastMacro = runningMacro;
                runningMacro = 0;
                break;
        }
    }

    public void resetMacroNoDunk(int pos, double runTime) {
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
                rad.open();
                macroState = 0;
                runningMacro = 0;
                lastMacro = 0;
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
                rad.open();
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
                rad.open();
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
        rad.update();
    }

    public String getTelemetry() {
//        if (camEnabled) {
//            return String.format("Slides: %s\nClaw: %s\nCamera: %s", slides.getTelemetry(), claw.getTelemetry(), camera.getTelemetry());
//        } else {
//            return String.format("Slides: %s\nClaw: %s", claw.getTelemetry());
//        }
        return "";
//        return String.format("%s \n%s \nArm: %s \nSlides: %s", drive.getTelemetry(), claw.getTelemetry(), arm.getTelemetry(), slides.getTelemetry());
    }
}
