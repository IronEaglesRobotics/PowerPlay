package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables;
import org.firstinspires.ftc.teamcode.hardware.Robot;

public abstract class AutoBase extends LinearOpMode {
    protected Robot robot;
    protected SampleMecanumDrive drive;
    protected Trajectory scorePreload;
    protected Trajectory getStackCone;
    protected Trajectory scoreStackCone;
    protected Trajectory getStackConeCorrection;
    protected Trajectory scoreStackConeCorrection;
    protected Trajectory getStackConeCorrectionLast;
    protected Trajectory scoreStackConeCorrectionLast;
    protected Trajectory park1;
    protected Trajectory park2;
    protected Trajectory park3;
    protected Trajectory moveBeacon;

    protected int parkPosition = -1;
    protected Pose2d initialPosition;

    abstract protected void initializeTrajectories();

    @Override
    public void runOpMode() throws InterruptedException {
        Configurables.ARM_POWER= 0.7;
        this.robot = new Robot().init(hardwareMap);
        this.drive = new SampleMecanumDrive(hardwareMap);

        initializeTrajectories();

        drive.setPoseEstimate(initialPosition);

        this.robot.useAutoCamera();

        while (!isStarted()) {
            this.parkPosition = robot.getAutoCamera().getMarkerId();
            telemetry.addData("parkPosition", (parkPosition));
            telemetry.update();
        }

        // Score the preloaded cone
        robot.getClaw().twistDown();
        robot.getArm().moveMid();
        drive.followTrajectory(moveBeacon);
        drive.followTrajectory(scorePreload);
        robot.getArm().moveLeft();
        robot.getLift().autoTop(); // Lower the slide to the height of the top cone
        sleep(100);
        robot.getClaw().open();
        robot.getArm().moveScore();

        getAndScoreStackCone(Configurables.AUTO_TOP1);
        getAndScoreStackCone(Configurables.AUTO_TOP2);
        getAndScoreStackConeCorrection(Configurables.AUTO_TOP3);
        getAndScoreStackConeCorrection(Configurables.AUTO_TOP4);
        getAndScoreStackConeCorrectionLast(Configurables.AUTO_TOP5);

        robot.getArm().moveMid();

        switch (this.parkPosition) {
            case 1:
                drive.followTrajectory(park1);
                break;
            case 3:
                drive.followTrajectory(park3);
                break;
            default:
                drive.followTrajectory(park2);
                break;
        }
    }

    protected void getAndScoreStackCone(int height) {
        // Get the cone off the stack
        this.drive.followTrajectory(getStackCone);
        robot.getClaw().openWide();
        robot.getArm().moveRight();
        sleep(300);
        this.robot.getClaw().close();
        sleep(200);

        // Move back to the junction
        this.robot.getLift().slideMed();
        this.robot.getArm().moveTilt();
        sleep(150);
        this.drive.followTrajectory(scoreStackCone);

        // Score
        this.robot.getLift().move(height);
        robot.getArm().moveLeft();
        sleep(100);
        this.robot.getClaw().open();
        this.robot.getArm().moveScore();
        sleep(100);
    }

    protected void getAndScoreStackConeCorrection(int height) {
        // Get the cone off the stack
        this.drive.followTrajectory(getStackConeCorrection);
        robot.getClaw().openWide();
        robot.getArm().moveRight();
        sleep(300);
        this.robot.getClaw().close();
        sleep(200);

        // Move back to the junction
        this.robot.getLift().slideMed();
        this.robot.getArm().moveTilt();
        sleep(150);
        this.drive.followTrajectory(scoreStackConeCorrection);

        // Score
        this.robot.getLift().move(height);
        robot.getArm().moveLeft();
        sleep(100);
        this.robot.getClaw().open();
        this.robot.getArm().moveScore();
        sleep(100);
    }

    protected void getAndScoreStackConeCorrectionLast(int height) {
        // Get the cone off the stack
        this.drive.followTrajectory(getStackConeCorrectionLast);
        robot.getClaw().openWide();
        robot.getArm().moveRight();
        sleep(300);
        this.robot.getClaw().close();
        sleep(200);

        // Move back to the junction
        this.robot.getLift().slideMed();
        this.robot.getArm().moveTilt();
        sleep(150);
        this.drive.followTrajectory(scoreStackConeCorrectionLast);

        // Score
        this.robot.getLift().move(height);
        robot.getArm().moveLeft();
        sleep(100);
        this.robot.getClaw().open();
        this.robot.getArm().moveScore();
    }
}
