package org.firstinspires.ftc.teamcode.opmode.autonomous;

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

        Pose2d start = new Pose2d(-12,65.5,Math.toRadians(-90));
        Pose2d back = new Pose2d(-12,62,Math.toRadians(-90));
        Pose2d turretJ1 = new Pose2d(-12,12,Math.toRadians(-90));
//        Pose2d turretJ2 = new Pose2d(-12,6,Math.toRadians(-90));
//        Pose2d turretJ3 = new Pose2d(-12,-18,Math.toRadians(-90));

        // turret paths
        Trajectory forwardFromStart = drive.trajectoryBuilder(start)
                .lineToLinearHeading(turretJ1)
                .build();
        Trajectory forwardFromBack = drive.trajectoryBuilder(back)
                .lineToLinearHeading(turretJ1)
                .build();
        Trajectory backFromForward = drive.trajectoryBuilder(turretJ1)
                .lineToLinearHeading(back)
                .build();

        drive.setPoseEstimate(start);//12.5x12  11.25

        waitForStart();
        if(isStopRequested()) return;

        drive.followTrajectory(forwardFromStart);
        drive.followTrajectory(backFromForward);

        drive.followTrajectory(forwardFromBack);
        drive.followTrajectory(backFromForward);
        drive.followTrajectory(forwardFromBack);
        drive.followTrajectory(backFromForward);
        drive.followTrajectory(forwardFromBack);
        drive.followTrajectory(backFromForward);
        drive.followTrajectory(forwardFromBack);
        drive.followTrajectory(backFromForward);
        drive.followTrajectory(forwardFromBack);
        drive.followTrajectory(backFromForward);
    }
}
