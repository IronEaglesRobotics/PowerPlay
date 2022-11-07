package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.PoseStorage;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;

public class Robot {
    public SampleMecanumDrive drive;
    public Slides slides;
    public Claw claw;
    public HorizontalSlides hSlides;

    public Robot(HardwareMap hardwareMap) {
        drive = new SampleMecanumDrive(hardwareMap);
        drive.setPoseEstimate(PoseStorage.currentPose);
        slides = new Slides(hardwareMap);
        claw = new Claw(hardwareMap);

//        arm = new Arm(hardwareMap);
        hSlides = new HorizontalSlides(hardwareMap);
    }

    public void update(double runTime) {
        drive.update();
        slides.update(runTime);
//        arm.update();
        claw.update();
        hSlides.update();
    }

    public String getTelemetry() {
        return String.format("Slides: %s\nClaw: %s\nArm: %s", slides.getTelemetry(), claw.getTelemetry(), hSlides.getTelemetry());
    }
}
