package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables;
import org.firstinspires.ftc.teamcode.hardware.Robot;

@Config

@Autonomous(name = "WeirdAuto", group = "Competition", preselectTeleOp = "MainTeleOp")
public class WeirdAuto extends LinearOpMode {
    private Robot robot;
    private SampleMecanumDrive drive;
    private Trajectory getStackCone;
    private Trajectory scoreStackCone;
    @Override
    public void runOpMode() throws InterruptedException {

        this.robot = new Robot().init(hardwareMap);
        this.drive = new SampleMecanumDrive(hardwareMap);

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

        this.getStackCone = drive.trajectoryBuilder(scorePreload.end())
                .splineTo(new Vector2d(-54.3, -10.2), Math.toRadians(180))
                .addTemporalMarker(0.05, () -> {
                    robot.getArm().moveRight();
                    robot.getClaw().twistUp();
                })
                .build();

        this.scoreStackCone = drive.trajectoryBuilder(getStackCone.end())
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

        getAndScoreStackCone(Configurables.AUTO_TOP);
        getAndScoreStackCone(Configurables.AUTO_TOP2);
        getAndScoreStackCone(Configurables.AUTO_TOP3);
        getAndScoreStackCone(Configurables.AUTO_TOP4);
        getAndScoreStackCone(Configurables.AUTO_TOP5);

        robot.getArm().moveMid();
    }

    private void getAndScoreStackCone(int height) {
        // Get the cone off the stack
        this.robot.getLift().move(height);
        this.drive.followTrajectory(getStackCone);
        this.robot.getClaw().close();

        // Move back to the junction
        this.robot.getLift().slideMed();
        sleep(500);
        this.drive.followTrajectory(scoreStackCone);

        // Score
        this.robot.getLift().lowJunc();
        sleep(100);
        this.robot.getClaw().open();
        this.robot.getArm().moveMid();
        sleep(500);
    }
}
