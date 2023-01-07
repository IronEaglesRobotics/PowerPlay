package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.hardware.Robot;
@Config

@Autonomous(name = "WeirdAutoCam", group = "Competition", preselectTeleOp = "MainTeleOp")
public class WeirdAutoCam extends LinearOpMode {
    public static int parkPosition = 1;
    private Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {

        this.robot = new Robot().init(hardwareMap);
        this.robot.useAutoCamera();

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startOP = new Pose2d(-34, -60, Math.toRadians(90));

        drive.setPoseEstimate(startOP);

        this.robot.getClaw().open();

        Trajectory push = drive.trajectoryBuilder(startOP)
                .forward(54)
                .build();

        Trajectory pushback = drive.trajectoryBuilder(push.end())
                .back(6)
                .build();

        Trajectory score = drive.trajectoryBuilder(pushback.end(), true)
                .lineToSplineHeading(new Pose2d(-34, -12, Math.toRadians(135)))
                .build();

        Trajectory ahhhhh = drive.trajectoryBuilder(score.end(), true)
                .back(2.5)
                .build();
        Trajectory getCone = drive.trajectoryBuilder(ahhhhh.end())
                .splineTo(new Vector2d(-34.00001, -13), Math.toRadians(180))
                .splineTo(new Vector2d(-50.5, -13), Math.toRadians(180))
                .build();

        Trajectory scoreAgain = drive.trajectoryBuilder(getCone.end(), true)
                .lineToSplineHeading(new Pose2d(-40, -13, Math.toRadians(180)))
                .lineToSplineHeading(new Pose2d(-34, -13, Math.toRadians(135)))
                .build();

        Trajectory ahhhhha = drive.trajectoryBuilder(scoreAgain.end(), true)
                .back(1)
                .build();

        Trajectory getAgain = drive.trajectoryBuilder(ahhhhha.end())
                .lineToSplineHeading(new Pose2d(55, -10, Math.toRadians(0)))
                .build();

        Trajectory scoreAgain2 = drive.trajectoryBuilder(getCone.end(), true)
                .lineToSplineHeading(new Pose2d(-40, -13, Math.toRadians(180)))
                .lineToSplineHeading(new Pose2d(-34, -13, Math.toRadians(132)))
                .build();

        Trajectory getAgain2 = drive.trajectoryBuilder(scoreAgain2.end())
                .splineTo(new Vector2d(-34.00001, -13), Math.toRadians(180))
                .splineTo(new Vector2d(-51.5, -14), Math.toRadians(180))
                .build();

        Trajectory getAgain3 = drive.trajectoryBuilder(ahhhhha.end())
                .splineTo(new Vector2d(-34.00001, -13), Math.toRadians(180))
                .splineTo(new Vector2d(-50.5, -14), Math.toRadians(180))
                .build();

        Trajectory park3 = drive.trajectoryBuilder(ahhhhha.end())
                .splineTo(new Vector2d(-34.00001, -13), Math.toRadians(180))
                .splineTo(new Vector2d(-55, -14), Math.toRadians(180))
                .build();

        Trajectory park2 = drive.trajectoryBuilder(ahhhhh.end())
                .lineToSplineHeading(new Pose2d(-35,-14, Math.toRadians(180)))
                .build();

        Trajectory park1 = drive.trajectoryBuilder(park2.end())
                .back(25)
                .build();


        robot.useAimingCamera();

        while (!isStarted()) {
            parkPosition = robot.getAutoCamera().getMarkerId();
            telemetry.addData("parkPosition", (parkPosition));
            telemetry.update();
        }


        // Do stuff
        robot.getClaw().open();
        robot.getArm().moveMid();
        this.robot.getClaw().twistDown();
        robot.getLift().dunk();
        drive.followTrajectory(push);
//        robot.getLift().slideLow();
        robot.getArm().moveLeft();
        drive.followTrajectory(score);
        sleep(1000);
        this.robot.aimSync();
        robot.getLift().slideLow();
        drive.followTrajectory(ahhhhh);
        sleep(200);
        robot.getLift().dunk();
        sleep(150);
        robot.getClaw().close();
        robot.getArm().moveRight();
        robot.getLift().autoTop();
        sleep(100);
        robot.getClaw().twistUp();

        drive.followTrajectory(getAgain2);
        robot.getClaw().open();
        sleep(250);
        robot.getArm().moveLeft();
        sleep(250);
        robot.getLift().dunk();
        this.robot.getClaw().twistDown();
        drive.followTrajectory(scoreAgain);
        sleep(1000);
        this.robot.aimSync();
        robot.getLift().slideLow();
        drive.followTrajectory(ahhhhh);
        sleep(250);
        robot.getLift().dunk();
        sleep(150);
        robot.getClaw().close();
        robot.getArm().moveRight();
        robot.getLift().autoTop2();
        sleep(100);
        robot.getClaw().twistUp();

        drive.followTrajectory(getAgain2);
        robot.getClaw().open();
        sleep(250);
        robot.getArm().moveLeft();
        sleep(250);
        robot.getLift().dunk();
        this.robot.getClaw().twistDown();
        drive.followTrajectory(scoreAgain);
        sleep(1000);
        this.robot.aimSync();
        robot.getLift().slideLow();
        drive.followTrajectory(ahhhhh);
        sleep(250);
        robot.getLift().dunk();
        sleep(150);
        robot.getClaw().close();
        robot.getArm().moveMid();
        robot.getClaw().twistUp();
        robot.getLift().slideDown();
        robot.getArm().moveMid();
        sleep(100);
        robot.getArm().moveMid();






        switch (parkPosition) {
            case 1:
                drive.followTrajectory(park2);
                drive.followTrajectory(park1);
                break;
            case 2:
                drive.followTrajectory(park2);
                break;
            case 3:
                drive.followTrajectory(getAgain3);
                // Park in the middle of the field
                break;
            default:
                drive.followTrajectory(park2);
                break;
            // AHHH!!!!!

        }


    }
}
