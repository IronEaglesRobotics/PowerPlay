package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;

@Autonomous
public class challangeTest3 extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d start = new Pose2d(12,-60,Math.toRadians(90));
        Pose2d point1 = new Pose2d(12,-36,Math.toRadians(90));
        Pose2d point2 = new Pose2d(-12,-36,Math.toRadians(90));
        Pose2d point3 = new Pose2d(-12,-12,Math.toRadians(90));
        Pose2d point4 = new Pose2d(36,-12,Math.toRadians(90));
        Pose2d point5 = new Pose2d(36,-60,Math.toRadians(90));
        Pose2d point6 = new Pose2d(60,-60,Math.toRadians(90));
        Pose2d point7 = new Pose2d(60,12,Math.toRadians(90));
        Pose2d point8 = new Pose2d(-36,12,Math.toRadians(90));
        Pose2d point9 = new Pose2d(-36,-60,Math.toRadians(90));
        // turret paths
        Trajectory t1 = drive.trajectoryBuilder(start)
                .lineToLinearHeading(point1)
                .build();
        Trajectory t2 = drive.trajectoryBuilder(point1)
                .lineToLinearHeading(point2)
                .build();
        Trajectory t3 = drive.trajectoryBuilder(point2)
                .lineToLinearHeading(point3)
                .build();
        Trajectory t4 = drive.trajectoryBuilder(point3)
                .lineToLinearHeading(point4)
                .build();
        Trajectory t5 = drive.trajectoryBuilder(point4)
                .lineToLinearHeading(point5)
                .build();
        Trajectory t6 = drive.trajectoryBuilder(point5)
                .lineToLinearHeading(point6)
                .build();
        Trajectory t7 = drive.trajectoryBuilder(point6)
                .lineToLinearHeading(point7)
                .build();
        Trajectory t8 = drive.trajectoryBuilder(point7)
                .lineToLinearHeading(point8)
                .build();
        Trajectory t9 = drive.trajectoryBuilder(point8)
                .lineToLinearHeading(point9)
                .build();
        Trajectory t10 = drive.trajectoryBuilder(point9)
                .lineToLinearHeading(start)
                .build();
        drive.setPoseEstimate(start);//12.5x12  11.25

        waitForStart();
        if(isStopRequested()) return;

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

    }
}
