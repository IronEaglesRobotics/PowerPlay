package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.teamcode.hardware.Robot.Vision.AIMING;
import static org.firstinspires.ftc.teamcode.hardware.Robot.Vision.AUTO;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.util.Color;
import org.opencv.core.Point;

@Config
@Autonomous(name = "TestCam", group = "Competition")
public class TestCam extends LinearOpMode {
    FtcDashboard dashboard = FtcDashboard.getInstance();
    Telemetry dashboardTelemetry = dashboard.getTelemetry();

    private Robot robot;
    @Override
    public void runOpMode() throws InterruptedException {
        // Do
        this.robot = new Robot().init(hardwareMap, AIMING);
        waitForStart();
        while (isStarted()) {
            Point topOfJunction = this.robot.getAimingCamera().getTopOfJunction();
            Color centerColor = this.robot.getAimingCamera().getCenterColor();

            dashboardTelemetry.addData("CenterColor", centerColor);
            dashboardTelemetry.addData("JunctionT Top", topOfJunction.toString());
            dashboardTelemetry.update();
        }
        robot.getAimingCamera().stopAimingCamera();
    }
}
