package org.firstinspires.ftc.teamcode.opmode.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.hardware.Slides;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;

@Autonomous
public class BlueRightAuto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        Robot robot = new Robot(hardwareMap);
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
        Trajectory fromStartToScore = robot.drive.trajectoryBuilder(start)
                .lineToSplineHeading(new Pose2d(-36, 24, Math.toRadians(180)))
                .splineToSplineHeading(new Pose2d(-32, 8, Math.toRadians(135)), Math.toRadians(-45))
                .addTemporalMarker(0,() -> robot.slides.setTarget(Slides.Position.HIGH))
                .addTemporalMarker(2.5,() -> robot.hSlides.goToScore())
                .build();
        Trajectory fromScoreToLoad = robot.drive.trajectoryBuilder(score)
                .splineToSplineHeading(new Pose2d(-48,12,Math.toRadians(180)),Math.toRadians(180))
                .lineToSplineHeading(new Pose2d(-60,12,Math.toRadians(180)))
                .addTemporalMarker(.5,() -> robot.slides.setTarget(Slides.Position.FIVE))
                .addTemporalMarker(0,() -> robot.hSlides.goToIntake())
                .addTemporalMarker(1,() -> robot.claw.open())
                .build();
        Trajectory fromLoadToScore = robot.drive.trajectoryBuilder(load)
                .lineToSplineHeading(new Pose2d(-48,12,Math.toRadians(180)))
                .splineToSplineHeading(new Pose2d(-32,8,Math.toRadians(135)),Math.toRadians(-45))
                .addTemporalMarker(2.5,() -> robot.hSlides.goToScore())
                .build();
        Trajectory fromScoreToMiddleEnd1 = robot.drive.trajectoryBuilder(score)
                .splineToSplineHeading(new Pose2d(-36,24,Math.toRadians(90)),Math.toRadians(90))
                .lineToSplineHeading(new Pose2d(-36,36,Math.toRadians(90)))
                .addTemporalMarker(.5,() -> robot.slides.setTarget(Slides.Position.FIVE))
                .addTemporalMarker(0,() -> robot.hSlides.goToIntake())
                .addTemporalMarker(1,() -> robot.claw.open())
                //.addTemporalMarker(2, robot.extendMacro())
                .build();

        robot.drive.setPoseEstimate(start);//12.5x12  11.25

        waitForStart();
        if(isStopRequested()) return;

        robot.drive.followTrajectory(fromStartToScore);
        //extend
        robot.claw.open();
        sleep(200);
        robot.claw.close();
        sleep(200);
        //retract

        robot.drive.followTrajectory(fromScoreToLoad);
        robot.claw.close();
        sleep(200);
        robot.slides.setTarget(Slides.Position.HIGH);
        robot.drive.followTrajectory(fromLoadToScore);
        robot.claw.open();
        sleep(200);
        robot.claw.close();
        sleep(200);


        robot.drive.followTrajectory(fromScoreToMiddleEnd1);
    }
}
