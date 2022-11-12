package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.PoseStorage;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.util.CameraPosition;

public class Robot {
    public SampleMecanumDrive drive;
    public Slides slides;
    public Claw claw;
    public HorizontalSlides hSlides;
    public Camera camera;
    private boolean camEnabled = false;

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
