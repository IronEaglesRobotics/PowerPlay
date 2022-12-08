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

@Autonomous(name = "TestAuto", group = "Competition")
public class testauto extends LinearOpMode {
    public static int parkPosition = 1;
    private Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        this.robot = new Robot().init(hardwareMap, Robot.Vision.AUTO);

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startOP = new Pose2d(34, -60, Math.toRadians(90));

        drive.setPoseEstimate(startOP);

        this.robot.getClaw().open();

        Trajectory  push = drive.trajectoryBuilder(startOP)
                .forward(60)
                .build();

        Trajectory pushBack = drive.trajectoryBuilder(push.end())
                .back(14)
                .build();

//        Trajectory score = drive.trajectoryBuilder(new Pose2d(pushBack.end().getX(), pushBack.end().getY(), Math.toRadians(-45)))
//                .back(6)
//                .build();

        Trajectory getConeOne = drive.trajectoryBuilder(new Pose2d(pushBack.end().getX(), pushBack.end().getY(), Math.toRadians(0)), true)
                .lineToSplineHeading(new Pose2d(54, -9, Math.toRadians(0)))
                .build();

        Trajectory goScore = drive.trajectoryBuilder(new Pose2d(getConeOne.end().getX(), getConeOne.end().getY(), Math.toRadians(0)))
                .lineToSplineHeading(new Pose2d(27, -14, Math.toRadians(45)))
                .build();

        Trajectory getCone = drive.trajectoryBuilder(new Pose2d(goScore.end().getX(), goScore.end().getY(), Math.toRadians(45)))
                .lineToSplineHeading(new Pose2d(56, -9, Math.toRadians(0)))
                .build();
        Trajectory Go = drive.trajectoryBuilder(new Pose2d(goScore.end().getX(), goScore.end().getY(), Math.toRadians(45)))
                .lineToSplineHeading(new Pose2d(57, -12, Math.toRadians(22)))
                .build();
        Trajectory Go2 = drive.trajectoryBuilder(new Pose2d(getConeOne.end().getX(), getConeOne.end().getY(), Math.toRadians(45)))
                .lineToSplineHeading(new Pose2d(57, -12, Math.toRadians(22)))
                .build();
//
//        Trajectory park = drive.trajectoryBuilder(getCone.end())
//                .lineToSplineHeading(new Pose2d(36, -12, Math.toRadians(180)))
//                .lineToSplineHeading(new Pose2d(12, -12, Math.toRadians(180)))
//                .build();

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

        robot.getArm().moveMid();
        drive.followTrajectory(push);
        robot.getArm().moveMid();
//        robot.getLift().slideUp();
        robot.getArm().moveMid();
        drive.followTrajectory(pushBack);
        robot.getArm().moveMid();
        drive.turn(Math.toRadians(-45));
        robot.getArm().moveMid();
//        robot.getArm().moveRight();
//        drive.followTrajectory(score);
//        robot.getLift().dunk();
//        robot.getClaw().close();
        drive.followTrajectory(getConeOne);
        drive.followTrajectory(Go2);
        drive.followTrajectory(goScore);
        drive.followTrajectory(Go);
//        robot.aimSync();
        drive.followTrajectory(getCone);
        drive.followTrajectory(Go2);
        drive.followTrajectory(goScore);
        drive.followTrajectory(Go);
////        robot.aimSync();
        drive.followTrajectory(getCone);
        drive.followTrajectory(Go2);
        drive.followTrajectory(goScore);
        drive.followTrajectory(Go);
////        robot.aimSync();
        drive.followTrajectory(getCone);
        drive.followTrajectory(Go2);
        drive.followTrajectory(goScore);
        drive.followTrajectory(Go);
////        robot.aimSync();
        drive.followTrajectory(getCone);
        drive.followTrajectory(Go2);
        drive.followTrajectory(goScore);
        drive.followTrajectory(Go);

//        switch (parkPosition) {
//            case 1:
//                drive.followTrajectory(park1);
//                // Park on the outside edge of the field
//                break;
//            case 2:
//                drive.followTrajectory(park2);
//                break;
//            case 3:
//                drive.followTrajectory(park3);
//                // Park in the middle of the field
//                break;
//            default:
//                break;
//            // AHHH!!!!!
//
//        }


    }
}
