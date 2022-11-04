package org.firstinspires.ftc.teamcode.opmode.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;

@Autonomous
public class challangeTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d start = new Pose2d(-12,65.5,Math.toRadians(-90));
        Pose2d point1 = new Pose2d(-12,36,Math.toRadians(-90));
        Pose2d point2 = new Pose2d(-36,36,Math.toRadians(-90));
        Pose2d point3 = new Pose2d(-36,12,Math.toRadians(-90));
        Pose2d point4 = new Pose2d(-12,12,Math.toRadians(-90));

        // turret paths
        Trajectory forward = drive.trajectoryBuilder(start)
                .lineToLinearHeading(point1)
                .build();
        Trajectory backward = drive.trajectoryBuilder(point4)
                .lineToLinearHeading(start)
                .build();
        Trajectory right = drive.trajectoryBuilder(point1)
                .lineToLinearHeading(point2)
                .build();
        Trajectory forwardPartTwo = drive.trajectoryBuilder(point2)
                .lineToLinearHeading(point3)
                .build();
        Trajectory left = drive.trajectoryBuilder(point3)
                .lineToLinearHeading(point4)
                .build();

        drive.setPoseEstimate(start);//12.5x12  11.25

        waitForStart();
        if(isStopRequested()) return;

        drive.followTrajectory(forward);
        drive.followTrajectory(right);
        drive.followTrajectory(forwardPartTwo);
        drive.followTrajectory(left);
        drive.followTrajectory(backward);
    }
}
