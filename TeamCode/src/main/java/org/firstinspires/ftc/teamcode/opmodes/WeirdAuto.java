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

@Autonomous(name = "WeirdAuto", group = "Competition", preselectTeleOp = "MainTeleOp")
public class WeirdAuto extends LinearOpMode {
    public static int parkPosition = 1;
    private Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {

        this.robot = new Robot().init(hardwareMap);

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startOP = new Pose2d(-32.5, -60, Math.toRadians(90));

        drive.setPoseEstimate(startOP);

        this.robot.getClaw().close();

        // Score the pre-loaded cone
        Trajectory scorePreload = drive.trajectoryBuilder(startOP)
                .lineToLinearHeading(new Pose2d(-34.0, -11.0, Math.toRadians(138)))
                .addTemporalMarker(1.5, () -> {
                    robot.getArm().moveLeft();
                })
                .build();

        Trajectory getStackCone = drive.trajectoryBuilder(scorePreload.end())
                .splineTo(new Vector2d(-54.3, -10.2), Math.toRadians(180))
                .addTemporalMarker(0.2, () -> {
                    robot.getArm().moveRight();
                    sleep(10);
                    robot.getClaw().twistUp();
                })
                .build();

        Trajectory scoreStackCone = drive.trajectoryBuilder(getStackCone.end())
                .lineToSplineHeading(new Pose2d(-34,-11,Math.toRadians(138)))
                .addTemporalMarker(0.5, () -> {
                    robot.getArm().moveLeft();
                    robot.getClaw().twistDown();
                })
                .build();

        waitForStart();

        // Score the preloaded cone
        robot.getLift().slideMed();
        drive.followTrajectory(scorePreload);
        robot.getLift().autoTop(); // Lower the slide to the height of the top cone
        sleep(100);
        robot.getClaw().open();
        sleep(500);

        // Score the first stack cone
        drive.followTrajectory(getStackCone);
        sleep(50); // Wait before scoring
        robot.getClaw().close();
        robot.getLift().slideMed();
        sleep(200);
        drive.followTrajectory(scoreStackCone);
        robot.getLift().autoTop2(); // Lower the slide to the height of the second cone
        sleep(100);
        robot.getClaw().open();
        sleep(500);

        // Score the second stack cone
        drive.followTrajectory(getStackCone);
        sleep(50); // Wait before scoring
        robot.getClaw().close();
        robot.getLift().slideMed();
        sleep(200);
        drive.followTrajectory(scoreStackCone);
        robot.getLift().autoTop3(); // Lower the slide to the height of the second cone
        sleep(100);

        robot.getClaw().open();
        sleep(500);

        // Score the third stack cone
        drive.followTrajectory(getStackCone);
        sleep(50); // Wait before scoring
        robot.getClaw().close();
        robot.getLift().slideMed();
        sleep(200);
        drive.followTrajectory(scoreStackCone);
        robot.getLift().autoTop4(); // Lower the slide to the height of the second cone
        sleep(100);
        robot.getClaw().open();
        sleep(500);

        // Score the fourth stack cone
        drive.followTrajectory(getStackCone);
        sleep(50); // Wait before scoring
        robot.getClaw().close();
        robot.getLift().slideMed();
        sleep(200);
        drive.followTrajectory(scoreStackCone);
        robot.getLift().autoTop5(); // Lower the slide to the height of the second cone
        sleep(100);
        robot.getClaw().open();
        sleep(500);

        // Score the fifth stack cone
        drive.followTrajectory(getStackCone);
        sleep(50); // Wait before scoring
        robot.getClaw().close();
        robot.getLift().slideMed();
        sleep(200);
        drive.followTrajectory(scoreStackCone);
        robot.getLift().slideDown(); // Lower the slide to the height of the second cone
        sleep(100);
        robot.getClaw().open();
        sleep(500);

        robot.getArm().moveMid();

        switch (parkPosition) {
            case 1:

                break;
            case 2:
                break;
            case 3:

                // Park in the middle of the field
                break;
            default:
                break;
            // AHHH!!!!!

        }


    }
}
