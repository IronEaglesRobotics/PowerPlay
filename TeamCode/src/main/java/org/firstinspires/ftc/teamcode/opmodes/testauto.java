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
@Disabled

@Autonomous(name = "TestAuto", group = "Competition")
public class testauto extends LinearOpMode {
    public static int parkPosition = 1;
    private Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        this.robot = new Robot().init(hardwareMap, Robot.Vision.AUTO);

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startOP = new Pose2d(-34, -60, Math.toRadians(90));

        drive.setPoseEstimate(startOP);

        this.robot.getClaw().open();

        Trajectory push = drive.trajectoryBuilder(startOP)
                .forward(60)
                .build();

        Trajectory pushBack = drive.trajectoryBuilder(push.end())
                .back(12)
                .build();

        Trajectory score = drive.trajectoryBuilder(new Pose2d(pushBack.end().getX(), pushBack.end().getY(), Math.toRadians(0)))
                .forward(6)
                .build();
        Trajectory back = drive.trajectoryBuilder(score.end())
                .back(6)
                .build();

        Trajectory getCone = drive.trajectoryBuilder(new Pose2d(back.end().getX(), back.end().getY(), Math.toRadians(0)))
                .forward(23)
                .build();

        Trajectory toScore = drive.trajectoryBuilder(getCone.end())
                .back(23)
                .build();

        Trajectory scoreAgain = drive.trajectoryBuilder(toScore.end())
                .forward(6)
                .build();

        Trajectory backAgain = drive.trajectoryBuilder(toScore.end())
                .back(6)
                .build();

        Trajectory getMoreCone = drive.trajectoryBuilder(new Pose2d(back.end().getX(), back.end().getY(), Math.toRadians(0)))
                .forward(23)
                .build();

        //Trajectories
        Trajectory park1 = drive.trajectoryBuilder(pushBack.end())
                .forward(6)
                .splineTo(new Vector2d(-57,-36),Math.toRadians(180))
                .build();

        Trajectory park2 = drive.trajectoryBuilder(pushBack.end())
                .forward(14)
                .build();

        Trajectory park3 = drive.trajectoryBuilder(pushBack.end())
                .forward(6)
                .splineTo(new Vector2d(-8,-32),Math.toRadians(0))
                .build();

        while (!isStarted()) {
            parkPosition = robot.getAutoCamera().getMarkerId();
            telemetry.addData("parkPosition", (parkPosition));
            telemetry.update();
        }
        // Do stuff

//        drive.followTrajectory(push);
//        drive.followTrajectory(pushBack);
//        drive.turn(Math.toRadians(45));
//        this.robot.getLift().slideUp();
//        sleep(2000);
//        drive.followTrajectory(score);
//        this.robot.getClaw().autoOpen();
//        sleep(200);
//        drive.followTrajectory(back);
//        this.robot.getLift().slideDown();
//        sleep(2000);
//        drive.turn(Math.toRadians(-135));
////        this.robot.getLift().oneCone();
//        this.robot.getClaw().close();
//        drive.followTrajectory(getCone);
//        this.robot.getClaw().open();
//        this.robot.getLift().clear();
//        drive.followTrajectory(toScore);
//        drive.turn(Math.toRadians(135));
//        this.robot.getLift().slideUp();
//        sleep(2000);
//        drive.followTrajectory(scoreAgain);
//        this.robot.getClaw().autoOpen();
//        sleep(200);
//        drive.followTrajectory(backAgain);
//        this.robot.getClaw().close();
//        this.robot.getLift().twoCone();
//        drive.followTrajectory(getMoreCone);
//        this.robot.getClaw().open();
//        this.robot.getLift().clear();
//        drive.followTrajectory(toScore);
//        drive.turn(Math.toRadians(135));
//        this.robot.getLift().slideUp();
//        sleep(2000);
//        drive.followTrajectory(scoreAgain);
//        this.robot.getClaw().autoOpen();
//        sleep(200);
//        drive.followTrajectory(backAgain);
//        this.robot.getClaw().close();
//        this.robot.getLift().threeCone();
//        drive.followTrajectory(getMoreCone);
//        this.robot.getClaw().open();
//        this.robot.getLift().clear();
//        drive.followTrajectory(toScore);
//        drive.turn(Math.toRadians(135));
//        this.robot.getLift().slideUp();
//        sleep(2000);
//        drive.followTrajectory(scoreAgain);
//        this.robot.getClaw().autoOpen();
//        sleep(200);
//        drive.followTrajectory(backAgain);
//        this.robot.getClaw().close();
//        this.robot.getLift().threeCone();
//        drive.followTrajectory(getMoreCone);
//        this.robot.getClaw().open();
//        this.robot.getLift().clear();

        switch (parkPosition) {
            case 1:
                drive.followTrajectory(park1);
                // Park on the outside edge of the field
                break;
            case 2:
                drive.followTrajectory(park2);
                break;
            case 3:
                drive.followTrajectory(park3);
                // Park in the middle of the field
                break;
            default:
                break;
            // AHHH!!!!!

        }


    }
}
