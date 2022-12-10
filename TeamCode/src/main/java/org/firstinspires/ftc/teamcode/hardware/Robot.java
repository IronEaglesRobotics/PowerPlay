package org.firstinspires.ftc.teamcode.hardware;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.PoseStorage;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.util.CameraPosition;

@Config
public class Robot {
    public SampleMecanumDrive drive;
    public Slides slides;
    public Claw claw;
    public HorizontalSlides hSlides;
    public Camera camera;
    private boolean camEnabled = false;

    public double macroStartTime = 0;
    public int macroState = 0;
    public int runningMacro = 0; // 0 = no macro | 1 = low macro | 2 = mid macro | 3 = high macro
    public int lastMacro = 0;

    public static double clawWait = 0.2;
    public static double hslideWait = 0.4;


    public Robot(HardwareMap hardwareMap) {
        drive = new SampleMecanumDrive(hardwareMap);
        drive.setPoseEstimate(PoseStorage.currentPose);
        slides = new Slides(hardwareMap);
        claw = new Claw(hardwareMap);
        hSlides = new HorizontalSlides(hardwareMap);
        camEnabled = false;
    }

    public Robot(HardwareMap hardwareMap, CameraPosition cameraPosition) {
        drive = new SampleMecanumDrive(hardwareMap);
        drive.setPoseEstimate(PoseStorage.currentPose);
        slides = new Slides(hardwareMap);
        claw = new Claw(hardwareMap);
        hSlides = new HorizontalSlides(hardwareMap);
        camera = new Camera(cameraPosition);
        camera.init(hardwareMap);
        camEnabled = true;
    }

    public void extendMacro(Slides.Position pos, double runTime) {
        switch(macroState) {
            case(0):
//                driver2.rumble(20);
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
                macroState ++;
                break;
            case(3):
                if (slides.atTarget()) {
                    macroState ++;
                }
                break;
            case(4):
                hSlides.goToScoreWithOffset();
                macroState = 0;
                lastMacro = runningMacro;
                runningMacro = 0;
                break;
        }
    }

    public void resetMacro(int pos, double runTime) {
        switch(macroState) {
            case(0):
//                driver2.rumble(20);
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
                claw.close();
                macroState++;
                break;
            case(3):
                if (runTime > macroStartTime + clawWait) {
                    macroState ++;
                }
                break;
            case(4):
                macroStartTime = runTime;
                hSlides.goToIntake();
                macroState ++;
                break;
            case(5):
                if (runTime > macroStartTime + hslideWait) {
                    macroState ++;
                }
                break;
            case(6):
                slides.setTarget(pos);
                if (slides.getPosition() < 500) {
                    claw.open();
                }
//                robot.slides.increaseTarget(targetDecrement);
                if (slides.atTarget()) {
                    claw.open();
                    macroState = 0;
//                    robot.slides.targetReset();
                    runningMacro = 0;
                    lastMacro = 0;
                }
                break;
        }
    }

    public void update(double runTime) {
        drive.update();
        slides.update(runTime);
        claw.update();
        hSlides.update();
    }

    public String getTelemetry() {
        if (camEnabled) {
            return String.format("Slides: %s\nClaw: %s\nCamera: %s", slides.getTelemetry(), claw.getTelemetry(), camera.getTelemetry());
        } else {
            return String.format("Slides: %s\nClaw: %s", slides.getTelemetry(), claw.getTelemetry());
        }
    }
}
