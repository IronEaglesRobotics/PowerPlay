package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables;
import org.firstinspires.ftc.teamcode.hardware.Robot;

@Config
@Autonomous(name = "LeftAutoRegionals", group = "Competition", preselectTeleOp = "MainTeleOp")
public class LeftAutoRegionals extends LinearOpMode {
    private Robot robot;
    private SampleMecanumDrive drive;
    private Trajectory getStackCone;
    private Trajectory scoreStackCone;
    private int parkPosition = -1;

    @Override
    public void runOpMode() throws InterruptedException {

        this.robot = new Robot().init(hardwareMap);
        this.drive = new SampleMecanumDrive(hardwareMap);

        Pose2d initialPosition = new Pose2d(-32.5, -60, Math.toRadians(90));

        drive.setPoseEstimate(initialPosition);

        // START -> SCORE
        Trajectory scorePreload = drive.trajectoryBuilder(initialPosition)
                .lineToLinearHeading(new Pose2d(-35.0, -11.5, Math.toRadians(138)))
                .addTemporalMarker(1.5, () -> {
                    robot.getArm().moveLeft();
                })
                .build();
        // SCORE -> STACK
        this.getStackCone = drive.trajectoryBuilder(scorePreload.end())
                .splineTo(new Vector2d(-54.3, -10.2), Math.toRadians(180))
                .addTemporalMarker(0.05, () -> {

                    robot.getClaw().twistUp();
                })
                .build();
        // STACK -> SCORE
        this.scoreStackCone = drive.trajectoryBuilder(getStackCone.end())
                .lineToSplineHeading(new Pose2d(-35,-11.5,Math.toRadians(138)))
                .addTemporalMarker(0.3, () -> {
                    robot.getClaw().twistDown();
                })
                .build();
        // STACK -> PARK1
        Trajectory park1 = drive.trajectoryBuilder(scoreStackCone.end())
                .splineTo(new Vector2d(-58, -10.2), Math.toRadians(180))
                .addDisplacementMarker(1, () -> {
                    this.robot.getArm().moveMid();
                })
                .build();
        // STACK -> PARK2
        Trajectory park2 = drive.trajectoryBuilder(getStackCone.end())
                .lineToSplineHeading(new Pose2d(-36,-11,Math.toRadians(180)))
                .addDisplacementMarker(1, () -> {
                    this.robot.getArm().moveMid();
                })
                .build();
        // PARK2 -> PARK3
        Trajectory park3 = drive.trajectoryBuilder(park2.end())
                .back(24)
                .addDisplacementMarker(1, () -> {
                    this.robot.getArm().moveMid();
                })
                .build();

        this.robot.useAutoCamera();

        while (!isStarted()) {
            this.parkPosition = robot.getAutoCamera().getMarkerId();
            telemetry.addData("parkPosition", (parkPosition));
            telemetry.update();
        }

        waitForStart();

        // Score the preloaded cone
        robot.getLift().slideMed();
        drive.followTrajectory(scorePreload);
        robot.getLift().autoTop(); // Lower the slide to the height of the top cone
        sleep(100);
        robot.getClaw().open();
        robot.getArm().moveRight();

        getAndScoreStackCone(Configurables.AUTO_TOP);
        getAndScoreStackCone(Configurables.AUTO_TOP2);
        getAndScoreStackCone(Configurables.AUTO_TOP3);
        getAndScoreStackCone(Configurables.AUTO_TOP4);
        getAndScoreStackCone(Configurables.AUTO_TOP5);

        robot.getArm().moveMid();

        switch (this.parkPosition) {
            case 2:
                drive.followTrajectory(park2);
                break;
            case 3:
                drive.followTrajectory(park2);
                drive.followTrajectory(park3);
                break;
            default:
                drive.followTrajectory(park1);
                break;
        }
    }


    private void getAndScoreStackCone(int height) {
        // Get the cone off the stack
        this.drive.followTrajectory(getStackCone);
        this.robot.getClaw().close();
        sleep(100);

        // Move back to the junction
        this.robot.getLift().slideMed();
        this.robot.getArm().moveLeft();
        sleep(200);
        this.drive.followTrajectory(scoreStackCone);

        // Score
        this.robot.getLift().move(height);
        sleep(100);
        this.robot.getClaw().open();
        this.robot.getArm().moveRight();
        sleep(100);
    }
}
