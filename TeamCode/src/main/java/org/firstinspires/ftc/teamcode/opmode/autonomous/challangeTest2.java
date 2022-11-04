package org.firstinspires.ftc.teamcode.opmode.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;

@Autonomous
public class challangeTest2 extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d start = new Pose2d(12,65.5,Math.toRadians(-90));
        Pose2d point1 = new Pose2d(12,12,Math.toRadians(-90));
        Pose2d point2 = new Pose2d(36,12,Math.toRadians(-90));
        Pose2d point3 = new Pose2d(36,36,Math.toRadians(-90));
        Pose2d point4 = new Pose2d(-12,36,Math.toRadians(-90));
        Pose2d point5 = new Pose2d(-12,60,Math.toRadians(-90));
        Pose2d point6 = new Pose2d(-36,60,Math.toRadians(-90));
//        Pose2d point7 = new Pose2d(-36,60,Math.toRadians(-180));
        // turret paths
        Trajectory t1 = drive.trajectoryBuilder(start)
                .lineToLinearHeading(point1)
                .build();
        Trajectory t2 = drive.trajectoryBuilder(t1.end())
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
//        Trajectory t7 = drive.trajectoryBuilder(t6.end().plus(new Pose2d(0,0,Math.toRadians(-90))))
//                .lineToLinearHeading(point7)
//                .build();
        drive.setPoseEstimate(start);//12.5x12  11.25

        waitForStart();
        if(isStopRequested()) return;

        drive.followTrajectory(t1);
        drive.followTrajectory(t2);
        drive.followTrajectory(t3);
        drive.followTrajectory(t4);
        drive.followTrajectory(t5);
        drive.followTrajectory(t6);
        drive.turn(Math.toRadians(-90));
//        drive.followTrajectory(t7);
    }
}
