package org.firstinspires.ftc.teamcode.opmodes;

import android.annotation.SuppressLint;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.hardware.Robot;

@Config
@Autonomous(name = "WALE", group = "Competition", preselectTeleOp = "MainTeleOp")
public class WaleTest extends LinearOpMode {
    Robot robot;
    public static double targetDistance = 4.5;
    public static double tolerance = 0.1;
    public static double speed = 0.2;

    public static double backoff = 0.25;

    @SuppressLint("DefaultLocale")
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        this.robot = new Robot().init(hardwareMap);
        this.robot.getClaw().openWide();

        while(!isStarted()) {
            this.robot.getDrive().update();

            Pose2d poseEstimate = this.robot.getDrive().getPoseEstimate();
            telemetry.addData("x", poseEstimate.getX());
            telemetry.addData("y", poseEstimate.getY());
            telemetry.addData("heading", poseEstimate.getHeading());
            telemetry.addData("distance", this.robot.getWale().getDistance(DistanceUnit.INCH));
            telemetry.addData("touch", this.robot.getWale().isPressed());
            telemetry.update();
        }

        waitForStart();

        while(!this.robot.getWale().isPressed() && opModeIsActive()) {
            this.robot.getDrive().setWeightedDrivePower(new Pose2d(speed ,0, 0));
        }
        double runningTotal = 0;
        int i = 0;
        int samples = 10;
        while (i < samples) {
            double d = this.robot.getWale().getDistance(DistanceUnit.INCH);
            if (d > 20.0) {
                continue;
            }
            runningTotal += this.robot.getWale().getDistance(DistanceUnit.INCH);
            i++;
        }
        double currentDistance = runningTotal / samples;
        double error = Math.abs(targetDistance - currentDistance);

        this.robot.getDrive().update();
        this.robot.getDrive().followTrajectory(this.robot.getDrive().trajectoryBuilder(this.robot.getDrive().getPoseEstimate()).back(backoff).build());

        telemetry.addLine(String.format("PoseBeforeMove: %s", this.robot.getDrive().getPoseEstimate()));
        if (currentDistance > targetDistance + tolerance) {
            telemetry.addLine(String.format("Moving Left %f inches", error));
            telemetry.addData("currentDistance", currentDistance);
            this.robot.getDrive().update();
            this.robot.getDrive().followTrajectory(this.robot.getDrive().trajectoryBuilder(this.robot.getDrive().getPoseEstimate()).strafeLeft(error).build());
        } else if (currentDistance < targetDistance - tolerance) {
            telemetry.addLine(String.format("Moving Right %f inches", error));
            telemetry.addData("currentDistance", currentDistance);
            this.robot.getDrive().update();
            this.robot.getDrive().followTrajectory(this.robot.getDrive().trajectoryBuilder(this.robot.getDrive().getPoseEstimate()).strafeRight(error).build());
        }
        telemetry.addLine(String.format("PoseAfterMove: %s", this.robot.getDrive().getPoseEstimate()));
        telemetry.update();
    }
}