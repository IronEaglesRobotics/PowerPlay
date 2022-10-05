package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;

@Autonomous
public class StraightTesting extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d start = new Pose2d(-12,66,Math.toRadians(-90));
        Pose2d back = new Pose2d(-12,55,Math.toRadians(-90));
        Pose2d turretJ1 = new Pose2d(-12,30,Math.toRadians(-90));
        Pose2d turretJ2 = new Pose2d(-12,6,Math.toRadians(-90));
        Pose2d turretJ3 = new Pose2d(-12,-18,Math.toRadians(-90));
        Pose2d simpleJ1 = new Pose2d(-12,30,Math.toRadians(-45));
        Pose2d simpleJ2 = new Pose2d(-12,6,Math.toRadians(45));
        Pose2d simpleJ3 = new Pose2d(-12,-18,Math.toRadians(-45));

        // turret paths
        Trajectory t1 = drive.trajectoryBuilder(start).lineToLinearHeading(turretJ1).build();
        Trajectory t2 = drive.trajectoryBuilder(turretJ1).lineToLinearHeading(back).build();
        Trajectory t3 = drive.trajectoryBuilder(back).lineToLinearHeading(turretJ2).build();
        Trajectory t4 = drive.trajectoryBuilder(turretJ2).lineToLinearHeading(back).build();
        Trajectory t5 = drive.trajectoryBuilder(back).lineToLinearHeading(turretJ3).build();
        Trajectory t6 = drive.trajectoryBuilder(turretJ3).lineToLinearHeading(back).build();

        Trajectory t7 = drive.trajectoryBuilder(back).lineToLinearHeading(turretJ1).build();
        Trajectory t8 = drive.trajectoryBuilder(turretJ1).lineToLinearHeading(back).build();
        Trajectory t9 = drive.trajectoryBuilder(back).lineToLinearHeading(turretJ2).build();
        Trajectory t10 = drive.trajectoryBuilder(turretJ2).lineToLinearHeading(back).build();
        Trajectory t11 = drive.trajectoryBuilder(back).lineToLinearHeading(turretJ3).build();
        Trajectory t12 = drive.trajectoryBuilder(turretJ3).lineToLinearHeading(back).build();

        // simple paths
//        Trajectory t1 = drive.trajectoryBuilder(start).splineTo(new Vector2d(simpleJ1.getX(), simpleJ1.getY()), simpleJ1.getHeading()).build();
//        Trajectory t2 = drive.trajectoryBuilder(simpleJ1).splineTo(new Vector2d(back.getX(), back.getY()), back.getHeading()).build();
//        Trajectory t3 = drive.trajectoryBuilder(back).splineTo(new Vector2d(simpleJ2.getX(), simpleJ2.getY()), simpleJ2.getHeading()).build();
//        Trajectory t4 = drive.trajectoryBuilder(simpleJ2).splineTo(new Vector2d(back.getX(), back.getY()), back.getHeading()).build();
//        Trajectory t5 = drive.trajectoryBuilder(back).splineTo(new Vector2d(simpleJ3.getX(), simpleJ3.getY()), simpleJ3.getHeading()).build();
//        Trajectory t6 = drive.trajectoryBuilder(simpleJ3).splineTo(new Vector2d(back.getX(), back.getY()), back.getHeading()).build();
//
//        Trajectory t7 = drive.trajectoryBuilder(back).splineTo(new Vector2d(simpleJ1.getX(), simpleJ1.getY()), simpleJ1.getHeading()).build();
//        Trajectory t8 = drive.trajectoryBuilder(simpleJ1).splineTo(new Vector2d(back.getX(), back.getY()), back.getHeading()).build();
//        Trajectory t9 = drive.trajectoryBuilder(back).splineTo(new Vector2d(simpleJ2.getX(), simpleJ2.getY()), simpleJ2.getHeading()).build();
//        Trajectory t10 = drive.trajectoryBuilder(simpleJ2).splineTo(new Vector2d(back.getX(), back.getY()), back.getHeading()).build();
//        Trajectory t11 = drive.trajectoryBuilder(back).splineTo(new Vector2d(simpleJ3.getX(), simpleJ3.getY()), simpleJ3.getHeading()).build();
//        Trajectory t12 = drive.trajectoryBuilder(simpleJ3).splineTo(new Vector2d(back.getX(), back.getY()), back.getHeading()).build();

        drive.setPoseEstimate(start);//12.5x12  11.25

        waitForStart();

//        if(isStopRequested()) return;

        drive.followTrajectory(t1);
        drive.followTrajectory(t2);
        drive.followTrajectory(t3);
        drive.followTrajectory(t4);
        drive.followTrajectory(t5);
        drive.followTrajectory(t6);
        drive.followTrajectory(t7);
        drive.followTrajectory(t8);
        drive.followTrajectory(t9);
        drive.followTrajectory(t10);
        drive.followTrajectory(t11);
        drive.followTrajectory(t12);

    }
}
