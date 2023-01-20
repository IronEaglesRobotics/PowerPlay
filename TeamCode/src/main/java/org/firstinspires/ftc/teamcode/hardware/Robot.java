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
    public Arm arm;
    public Claw claw;
    public Camera camera;
    private boolean camEnabled = false;

    public double macroStartTime = 0;
    public int macroState = 0;
    public int runningMacro = 0; // 0 = no macro | 1 = low macro | 2 = mid macro | 3 = high macro
    public int lastMacro = 0;

    public static double clawWait = 0.2;
    public static double hslideWait = 0.4;


    public Robot(HardwareMap hardwareMap, Arm.Position armPos) {
        drive = new SampleMecanumDrive(hardwareMap);
        drive.setPoseEstimate(PoseStorage.currentPose);
        slides = new Slides(hardwareMap);
        claw = new Claw(hardwareMap);
        arm = new Arm(hardwareMap, armPos);
        camEnabled = false;
    }

    public Robot(HardwareMap hardwareMap, Arm.Position armPos, CameraPosition cameraPosition) {
        drive = new SampleMecanumDrive(hardwareMap);
        drive.setPoseEstimate(PoseStorage.currentPose);
        slides = new Slides(hardwareMap);
        claw = new Claw(hardwareMap);
        arm = new Arm(hardwareMap, armPos);
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
                arm.goToScore();
                claw.flipped();
//                macroState ++;
                macroState = 0;
                lastMacro = runningMacro;
                runningMacro = 0;
                break;
//            case(3):
//                if (slides.atTarget()) {
//                    macroState ++;
//                }
//                break;
//            case(4):
////                hSlides.goToScoreWithOffset();
//                arm.goToScore();
//                macroState = 0;
//                lastMacro = runningMacro;
//                runningMacro = 0;
//                break;
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
//                claw.close();
                slides.setTarget(pos);
                arm.goToIntake();
                claw.upright();
                macroState = 0;
                runningMacro = 0;
                lastMacro = 0;

//                macroState++;
//                break;
//            case(3):
//                if (runTime > macroStartTime + clawWait) {
//                    macroState ++;
//                }
//                break;
//            case(4):
//                macroStartTime = runTime;
////                hSlides.goToIntake();
//                arm.goToIntake();
//                macroState ++;
//                break;
//            case(5):
//                if (runTime > macroStartTime + hslideWait) {
//                    macroState ++;
//                    claw.open();
//                }
//                break;
//            case(6):
//                slides.setTarget(pos);
//                if (slides.atTarget()) {
////                    claw.open();
//                    macroState = 0;
////                    robot.slides.targetReset();
//                    runningMacro = 0;
//                    lastMacro = 0;
//                }
//                break;
        }
    }

    public void update(double runTime) {
        drive.update();
        slides.update(runTime);
        claw.update(runTime);
//        hSlides.update();
        arm.update();
    }

    public String getTelemetry() {
//        if (camEnabled) {
//            return String.format("Slides: %s\nClaw: %s\nCamera: %s", slides.getTelemetry(), claw.getTelemetry(), camera.getTelemetry());
//        } else {
//            return String.format("Slides: %s\nClaw: %s", claw.getTelemetry());
//        }
        return String.format("Claw: %s \nArm: %s \nSlides: %s", claw.getTelemetry(), arm.getTelemetry(), slides.getTelemetry());
    }
}
