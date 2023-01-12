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

@Autonomous(name = "RightSideAuto", group = "Competition", preselectTeleOp = "MainTeleOp")
public class RightSideAuto extends LinearOpMode {
    public static int parkPosition = 1;
    private Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {

        this.robot = new Robot().init(hardwareMap);

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startOP = new Pose2d(34, -60, Math.toRadians(90));

        drive.setPoseEstimate(startOP);

        this.robot.getClaw().close();

        //Trajectories
        Trajectory push = drive.trajectoryBuilder(startOP)
                .forward(60)
                .build();

        Trajectory pushback = drive.trajectoryBuilder(push.end())
                .back(6)
                .build();

        Trajectory score = drive.trajectoryBuilder(pushback.end(), true)
                .lineToSplineHeading(new Pose2d(36, -12, Math.toRadians(-45)))
                .build();

        Trajectory prepareForDunk = drive.trajectoryBuilder(score.end(), true)
                .back(3)
                .build();

        Trajectory getCone = drive.trajectoryBuilder(prepareForDunk.end())
                .splineTo(new Vector2d(36.00001, -13), Math.toRadians(0))
                .splineTo(new Vector2d(51.5, -13), Math.toRadians(6))
                .build();

        Trajectory scoreAgain = drive.trajectoryBuilder(getCone.end(), true)
                .lineToSplineHeading(new Pose2d(40, -13, Math.toRadians(0)))
                .lineToSplineHeading(new Pose2d(35, -13, Math.toRadians(-45)))
                .build();

        Trajectory scoreAgain2 = drive.trajectoryBuilder(getCone.end(), true)
                .lineToSplineHeading(new Pose2d(40, -13, Math.toRadians(0)))
                .lineToSplineHeading(new Pose2d(35, -13, Math.toRadians(-45)))
                .build();

        Trajectory getAgain2 = drive.trajectoryBuilder(scoreAgain.end())
                .splineTo(new Vector2d(36.00001, -13), Math.toRadians(0))
                .splineTo(new Vector2d(51.2, -13), Math.toRadians(6))
                .build();

        Trajectory park3 = drive.trajectoryBuilder(scoreAgain.end())
                .splineTo(new Vector2d(37.00001, -12), Math.toRadians(0))
                .splineTo(new Vector2d(56, -13), Math.toRadians(0))
                .build();

        Trajectory park2 = drive.trajectoryBuilder(scoreAgain.end())
                .splineTo(new Vector2d(36.00001, -13), Math.toRadians(0))
                .build();

        Trajectory park1 = drive.trajectoryBuilder(park2.end())
                .back(25)
                .build();

        this.robot.useAutoCamera();

        while (!isStarted()) {
            parkPosition = robot.getAutoCamera().getMarkerId();
            telemetry.addData("parkPosition", (parkPosition));
            telemetry.addData("armPosition", (this.robot.getArm().getCurrentPosition()));
            telemetry.update();
        }

        // Score Preload
        robot.useAimingCamera();
        robot.getClaw().close();
        robot.getArm().moveMid();
        drive.followTrajectory(push);
        robot.getLift().dunk();
        robot.getArm().moveLeft();
        this.robot.getClaw().twistDown();
        drive.followTrajectory(pushback);
        drive.followTrajectory(score);
        sleep(1000);
        this.robot.aimSync();
        robot.getLift().slideLow();
        drive.followTrajectory(prepareForDunk);
        sleep(200);
        robot.getLift().dunk();
        sleep(200);
        robot.getClaw().open();
        robot.getArm().moveRight();
        robot.getLift().autoTop();
        robot.getClaw().twistUp();

        //Stack One
        drive.followTrajectory(getAgain2);
        robot.getClaw().close();
        sleep(250);
        robot.getArm().moveLeft();
        sleep(300);
        robot.getLift().dunk();
        this.robot.getClaw().twistDown();
        drive.followTrajectory(scoreAgain);
        this.robot.aimSync();
        robot.getLift().slideLow();
        drive.followTrajectory(prepareForDunk);
        sleep(200);
        robot.getLift().dunk();
        sleep(200);
        robot.getClaw().open();
        robot.getArm().moveRight();
        robot.getLift().autoTop2();
        robot.getClaw().twistUp();

        //Stack Two
        drive.followTrajectory(getAgain2);
        robot.getClaw().close();
        sleep(250);
        robot.getArm().moveLeft();
        sleep(300);
        robot.getLift().dunk();
        this.robot.getClaw().twistDown();
        drive.followTrajectory(scoreAgain2);
        this.robot.aimSync();
        robot.getLift().slideLow();
        drive.followTrajectory(prepareForDunk);
        sleep(200);
        robot.getLift().dunk();
        sleep(200);
        robot.getClaw().open();
        robot.getArm().moveMid();
        robot.getClaw().twistUp();
        robot.getLift().slideDown();
        robot.getArm().moveMid();

        //Park
        switch (parkPosition) {
            case 1:
                drive.followTrajectory(park3);
                robot.getArm().moveMid();
                break;
            case 2:
                drive.followTrajectory(park2);
                robot.getArm().moveMid();
                break;
            case 3:
                drive.followTrajectory(park2);
                drive.followTrajectory(park1);
                robot.getArm().moveMid();
                break;
            default:
                drive.followTrajectory(park2);
                robot.getArm().moveMid();
                break;
            // AHHH!!!!!
        }
    }
}
