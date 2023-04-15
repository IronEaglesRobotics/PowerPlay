package org.firstinspires.ftc.teamcode.opmodes;

import android.annotation.SuppressLint;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables;
import org.firstinspires.ftc.teamcode.hardware.Robot;

public abstract class AbstractAuto extends LinearOpMode {
    protected Robot robot;
    public static double bumpTimeout = 5.0;
    public static double bumpSpeed = 0.2;
    public static double bailThreshold = 10.0;
    public static double alignmentTolerance = .75;
    public static double coneTargetDistance = 3.5;
    protected Vector2d getStackResetPos;
    protected Trajectory scoreStackOne;
    protected Trajectory getStackOne;
    protected Trajectory scoreStackTwo;
    protected Trajectory getStackTwo;
    protected Trajectory scoreStackThree;
    protected Trajectory getStackThree;
    protected Trajectory scoreStackFour;
    protected Trajectory getStackFour;
    protected Trajectory scoreStackFive;
    protected Trajectory getStackFive;

    protected Pose2d initialPosition;
    protected Trajectory moveBeacon;
    protected Trajectory scorePreload;

    protected Trajectory park1;
    protected Trajectory park2;
    protected Trajectory park3;
    protected int parkPosition = 2;

    abstract protected void initializeTrajectories();

    @Override
    public void runOpMode() throws InterruptedException {
        this.robot = new Robot().init(hardwareMap);
        Configurables.ARM_POWER = 0.7;

        initializeTrajectories();

        this.robot.getDrive().setPoseEstimate(initialPosition);

        this.robot.useAutoCamera();

        while (!isStarted()) {
            this.parkPosition = robot.getAutoCamera().getMarkerId();
            telemetry.addData("parkPosition", (parkPosition));
            telemetry.update();
        }

        telemetry.addData("distance", this.robot.getWale().getDistance(DistanceUnit.INCH));
        telemetry.addData("touch", this.robot.getWale().isPressed());
        telemetry.update();

        // Score the preloaded cone
        int armPosition = this.robot.getArm().getCurrentPosition();
        telemetry.addData("Arm_Position", (armPosition));
        telemetry.update();
        robot.getArm().moveRight();
        this.robot.getDrive().followTrajectory(moveBeacon);
        robot.getLift().slideMedTele();
        this.robot.getDrive().followTrajectory(scorePreload);
        robot.getArm().moveLeft();
        robot.getLift().autoTop(); // Lower the slide to the height of the top cone
        sleep(100);
        robot.getClaw().open();
        robot.getArm().moveScore();

        scoreStackCones();

        robot.getArm().moveMid();

        switch (this.parkPosition) {
            case 1:
                this.robot.getArm().moveLeft();
                this.robot.getDrive().followTrajectory(park1);
                this.robot.getArm().moveMid();
                break;
            case 3:
                this.robot.getArm().moveLeft();
                this.robot.getDrive().followTrajectory(park3);
                this.robot.getArm().moveMid();
                break;
            default:
                this.robot.getArm().moveLeft();
                this.robot.getDrive().followTrajectory(park2);
                this.robot.getArm().moveMid();
                break;
        }
    }

    protected boolean bump() {
        this.robot.getWale().deploy();
        sleep(300);

        double startTime = this.getRuntime();
        while (!this.robot.getWale().isPressed() && opModeIsActive()) {
            if (this.getRuntime() > startTime + bumpTimeout) {
                this.robot.getDrive().setWeightedDrivePower(new Pose2d(0, 0, 0));
                return false;
            }
            this.robot.getDrive().setWeightedDrivePower(new Pose2d(bumpSpeed, 0, 0));
        }

        this.robot.getDrive().setWeightedDrivePower(new Pose2d(0, 0, 0));

        this.robot.getWale().stow();
        return true;
    }

    protected double getCurrentDistance(int samples) {
        double runningTotal = 0;
        int i = 0;
        while (i < samples) {
            double d = this.robot.getWale().getDistance(DistanceUnit.INCH);
            if (d > 20.0) {
                continue;
            }
            runningTotal += this.robot.getWale().getDistance(DistanceUnit.INCH);
            i++;
        }
        return runningTotal / samples;
    }

    @SuppressLint("DefaultLocale")
    protected boolean waleAlign(double coneTargetDistance) {
        double currentDistance = getCurrentDistance(1);
        if (!bump() || currentDistance > bailThreshold) {
            return false;
        }
        double error = Math.abs(coneTargetDistance - currentDistance);

        // Strafe
        telemetry.addLine(String.format("PoseBeforeMove: %s", this.robot.getDrive().getPoseEstimate()));
        if (currentDistance > coneTargetDistance + alignmentTolerance) {
            telemetry.addLine(String.format("Moving Left %f inches", error));
            telemetry.addData("currentDistance", currentDistance);
            this.robot.getDrive().update();
            this.robot.getDrive().followTrajectory(this.robot.getDrive().trajectoryBuilder(this.robot.getDrive().getPoseEstimate()).strafeLeft(error).build());
        } else if (currentDistance < coneTargetDistance - alignmentTolerance) {
            telemetry.addLine(String.format("Moving Right %f inches", error));
            telemetry.addData("currentDistance", currentDistance);
            this.robot.getDrive().update();
            this.robot.getDrive().followTrajectory(this.robot.getDrive().trajectoryBuilder(this.robot.getDrive().getPoseEstimate()).strafeRight(error).build());
        }
        telemetry.addLine(String.format("PoseAfterMove: %s", this.robot.getDrive().getPoseEstimate()));
        telemetry.update();

        // Update Pose Estimate
        this.robot.getDrive().setPoseEstimate(new Pose2d(getStackResetPos.getX(), getStackResetPos.getY(), this.robot.getDrive().getPoseEstimate().getHeading()));

        return true;
    }

    protected abstract boolean getAndScoreStackCone(Trajectory getStackCone, Trajectory scoreStackCone, int height, double coneTargetDistance);

    protected void scoreStackCones() {
        if (!getAndScoreStackCone(getStackOne, scoreStackOne, Configurables.AUTO_TOP1, coneTargetDistance)) {
            return;
        }

        if (!getAndScoreStackCone(getStackTwo, scoreStackTwo, Configurables.AUTO_TOP2, coneTargetDistance)) {
            return;
        }

        if (!getAndScoreStackCone(getStackThree, scoreStackThree, Configurables.AUTO_TOP3, coneTargetDistance +.5)) {
            return;
        }

        if (!getAndScoreStackCone(getStackFour, scoreStackFour, Configurables.AUTO_TOP4, coneTargetDistance + 0.75)) {
            return;
        }

        if (!getAndScoreStackCone(getStackFive, scoreStackFive, Configurables.AUTO_TOP5, coneTargetDistance + 1)) {
            return;
        }
    }
}
