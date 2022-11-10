package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.hardware.Robot;

@Config
@Autonomous(name = "TestCam", group = "Competition")
public class TestCam extends LinearOpMode {
    public int parkPosition = 1;
    private Robot robot;
    @Override
    public void runOpMode() throws InterruptedException {
        // Do
        this.robot = new Robot().init(hardwareMap, true);
        waitForStart();
        while (isStarted()) {
            this.parkPosition = robot.getAutoCamera().getMarkerId();


            telemetry.addData("parkPosition", (parkPosition));
            telemetry.update();
        }
        robot.getAutoCamera().stopBarcodeWebcam();
    }
}
