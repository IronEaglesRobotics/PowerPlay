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
@Disabled

@Config

@Autonomous(name = "IsThisRight", group = "Competition", preselectTeleOp = "MainTeleOp")
public class IsThisRight extends LinearOpMode {
    public static int parkPosition = 1;
    private Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {

        this.robot = new Robot().init(hardwareMap);

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startOP = new Pose2d(34, -60, Math.toRadians(90));

        drive.setPoseEstimate(startOP);

        this.robot.getClaw().close();

        Trajectory push = drive.trajectoryBuilder(startOP)
                .forward(57)
                .build();

        Trajectory pushback = drive.trajectoryBuilder(push.end())
                .back(3)
                .build();

        Trajectory score = drive.trajectoryBuilder(pushback.end(), true)
                .lineToSplineHeading(new Pose2d(34, -13, Math.toRadians(-140)))
                .build();

//        Trajectory ahhhhh = drive.trajectoryBuilder(score.end(), true)
//                .back(1)
//                .build();

        Trajectory getCone = drive.trajectoryBuilder(score.end())
                .splineTo(new Vector2d(34.00001, -13), Math.toRadians(0))
                .splineTo(new Vector2d(50.5, -13), Math.toRadians(0))
                .build();

        Trajectory scoreAgain = drive.trajectoryBuilder(getCone.end(), true)
                .lineToSplineHeading(new Pose2d(40, -13, Math.toRadians(0)))
                .lineToSplineHeading(new Pose2d(34, -13, Math.toRadians(-140)))
                .build();

//        Trajectory ahhhhha = drive.trajectoryBuilder(scoreAgain.end(), true)
//                .back(1)
//                .build();

        Trajectory getAgain = drive.trajectoryBuilder(scoreAgain.end())
                .lineToSplineHeading(new Pose2d(55, -10, Math.toRadians(0)))
                .build();

        Trajectory scoreAgain2 = drive.trajectoryBuilder(getCone.end(), true)
                .lineToSplineHeading(new Pose2d(40, -13, Math.toRadians(0)))
                .lineToSplineHeading(new Pose2d(34, -13, Math.toRadians(-145)))
                .build();

        Trajectory getAgain2 = drive.trajectoryBuilder(scoreAgain2.end())
                .splineTo(new Vector2d(34.00001, -13), Math.toRadians(0))
                .splineTo(new Vector2d(50.5, -14), Math.toRadians(0))
                .build();

        Trajectory getAgain3 = drive.trajectoryBuilder(scoreAgain2.end())
                .splineTo(new Vector2d(34.00001, -13), Math.toRadians(0))
                .splineTo(new Vector2d(50.5, -14), Math.toRadians(0))
                .build();

        Trajectory park3 = drive.trajectoryBuilder(scoreAgain2.end())
                .splineTo(new Vector2d(34.00001, -13), Math.toRadians(0))
                .splineTo(new Vector2d(55, -14), Math.toRadians(0))
                .build();

        Trajectory park2 = drive.trajectoryBuilder(scoreAgain2.end())
                .lineToSplineHeading(new Pose2d(-35,-14, Math.toRadians(0)))
                .build();

        Trajectory park1 = drive.trajectoryBuilder(park2.end())
                .back(24)
                .build();


//        Trajectory goAgain = drive.trajectoryBuilder(scoreAgain.end())
//                .splineTo(new Vector2d(40,-12), Math.toRadians(0))
//                .build();



        while (!isStarted()) {
            parkPosition = robot.getAutoCamera().getMarkerId();
            telemetry.addData("parkPosition", (parkPosition));
            telemetry.update();
        }
        // Do stuff
        robot.getClaw().close();
        robot.getArm().moveMid();
        drive.followTrajectory(push);
        robot.getLift().slideLow();
//        robot.getLift().dunk();
        robot.getArm().moveLeft();
        drive.followTrajectory(pushback);
        this.robot.getClaw().twistDown();
        drive.followTrajectory(score);
//        sleep(500);
//        robot.getLift().slideLow();
//        drive.followTrajectory(ahhhhh);
//        sleep(100);
        robot.getLift().dunk();
        sleep(150);
        robot.getClaw().open();
        robot.getArm().moveRight();
        robot.getLift().autoTop();
        sleep(100);
        robot.getClaw().twistUp();

        drive.followTrajectory(getAgain2);
        robot.getClaw().close();
        sleep(100);
        robot.getArm().moveLeft();
        sleep(100);
        robot.getLift().slideLow();
//        robot.getLift().dunk();
        this.robot.getClaw().twistDown();
        drive.followTrajectory(scoreAgain);
//        sleep(500);
//        drive.followTrajectory(ahhhhha);
//        sleep(100);
        robot.getLift().dunk();
        sleep(150);
        robot.getClaw().open();
        robot.getArm().moveRight();
        robot.getLift().autoTop2();
        sleep(100);
        robot.getClaw().twistUp();
//
        drive.followTrajectory(getAgain2);
        robot.getClaw().close();
        sleep(100);
        robot.getArm().moveLeft();
        sleep(250);
        robot.getLift().slideLow();
        this.robot.getClaw().twistDown();
        drive.followTrajectory(scoreAgain2);
//        drive.followTrajectory(ahhhhha);
//        sleep(250);
        robot.getLift().dunk();
        sleep(150);
        robot.getClaw().open();
        robot.getArm().moveRight();
        robot.getLift().autoTop3();

        sleep(100);
        robot.getClaw().twistUp();
        drive.followTrajectory(getAgain3);
        robot.getClaw().close();
        sleep(100);
        robot.getArm().moveLeft();
        sleep(250);
//        robot.getLift().dunk();
        robot.getLift().slideLow();
        this.robot.getClaw().twistDown();
        drive.followTrajectory(scoreAgain2);
//        drive.followTrajectory(ahhhhha);
//        sleep(100);
//        sleep(1000);
        robot.getLift().dunk();
        sleep(150);
        robot.getClaw().open();
        sleep(100);
        robot.getArm().moveMid();
        robot.getClaw().close();
        robot.getLift().slideDown();




        switch (parkPosition) {
            case 1:
                robot.getLift().slideDown();
                drive.followTrajectory(park3);
                break;
            case 2:
                robot.getLift().slideDown();
                drive.followTrajectory(park2);
                break;
            case 3:
                // Park in the middle of the field
                robot.getLift().slideDown();
                drive.followTrajectory(park2);
                drive.followTrajectory(park1);
                break;
            default:
                robot.getLift().slideDown();
                drive.followTrajectory(park2);
                break;
            // AHHH!!!!!

        }


    }
}
