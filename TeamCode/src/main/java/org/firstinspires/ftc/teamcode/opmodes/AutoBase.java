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
    protected Trajectory park1;
    protected Trajectory park2;
    protected Trajectory park3;

    protected int parkPosition = -1;
    protected Pose2d initialPosition;

    abstract protected void initializeTrajectories();

    @Override
    public void runOpMode() throws InterruptedException {
        this.robot = new Robot().init(hardwareMap);
        this.drive = new SampleMecanumDrive(hardwareMap);
        drive.setPoseEstimate(initialPosition);

        initializeTrajectories();

        this.robot.useAutoCamera();

        while (!isStarted()) {
            this.parkPosition = robot.getAutoCamera().getMarkerId();
            telemetry.addData("parkPosition", (parkPosition));
            telemetry.update();
        }

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

    protected void getAndScoreStackCone(int height) {
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
