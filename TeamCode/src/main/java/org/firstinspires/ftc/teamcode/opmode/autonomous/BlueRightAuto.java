package org.firstinspires.ftc.teamcode.opmode.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;

@Autonomous
public class BlueRightAuto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d start = new Pose2d(-36,60,Math.toRadians(180));
        Pose2d score = new Pose2d(-32,8,Math.toRadians(135));
        Pose2d load = new Pose2d(-60,12,Math.toRadians(180));
        Pose2d topEnd1 = new Pose2d(-12,36,Math.toRadians(90));
        Pose2d topEnd2 = new Pose2d(-12,12,Math.toRadians(90));
        Pose2d middleEnd1 = new Pose2d(-36,36,Math.toRadians(90));
        Pose2d middleEnd2 = new Pose2d(-36,12,Math.toRadians(90));
        Pose2d bottomEnd1 = new Pose2d(-60,36,Math.toRadians(90));
        Pose2d bottomEnd2 = new Pose2d(-60,12,Math.toRadians(90));

        // turret paths
        Trajectory fromStartToScore = drive.trajectoryBuilder(start)
                .lineToSplineHeading(new Pose2d(-36, 24, Math.toRadians(180)))
                .splineToSplineHeading(new Pose2d(-32, 8, Math.toRadians(135)), Math.toRadians(-45))
                .build();
        Trajectory fromScoreToLoad = drive.trajectoryBuilder(score)
                .splineToSplineHeading(new Pose2d(-48,12,Math.toRadians(180)),Math.toRadians(180))
                .lineToSplineHeading(new Pose2d(-60,12,Math.toRadians(180)))
                .build();
        Trajectory fromLoadToScore = drive.trajectoryBuilder(load)
                .lineToSplineHeading(new Pose2d(-48,12,Math.toRadians(180)))
                .splineToSplineHeading(new Pose2d(-32,8,Math.toRadians(135)),Math.toRadians(-45))
                .build();
        Trajectory fromScoreToMiddleEnd1 = drive.trajectoryBuilder(score)
                .splineToSplineHeading(new Pose2d(-36,24,Math.toRadians(90)),Math.toRadians(90))
                .lineToSplineHeading(new Pose2d(-36,36,Math.toRadians(90)))
                .build();

        drive.setPoseEstimate(start);//12.5x12  11.25

        waitForStart();
        if(isStopRequested()) return;

        drive.followTrajectory(fromStartToScore);
        drive.followTrajectory(fromScoreToLoad);
        drive.followTrajectory(fromLoadToScore);
        drive.followTrajectory(fromScoreToLoad);
        drive.followTrajectory(fromLoadToScore);
        drive.followTrajectory(fromScoreToLoad);
        drive.followTrajectory(fromLoadToScore);
        drive.followTrajectory(fromScoreToLoad);
        drive.followTrajectory(fromLoadToScore);
        drive.followTrajectory(fromScoreToLoad);
        drive.followTrajectory(fromLoadToScore);
        drive.followTrajectory(fromScoreToMiddleEnd1);
    }
}
