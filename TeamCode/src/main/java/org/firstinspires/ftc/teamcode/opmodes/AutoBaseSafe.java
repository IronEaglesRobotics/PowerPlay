package org.firstinspires.ftc.teamcode.opmodes;

import android.annotation.SuppressLint;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables;
import org.firstinspires.ftc.teamcode.hardware.Robot;

public abstract class AutoBaseSafe extends LinearOpMode {
    public static double coneTargetDistance = 3.5;
    public static double tolerance = .75;
    public static double speed = 0.2;
    protected Robot robot;
    protected Pose2d initialPosition;
    protected Trajectory moveBeacon;
    protected Trajectory scorePreload;
    protected Trajectory getStackConePreload;
    protected Trajectory scoreStackConeLow;
    protected Trajectory scoreStackConeMid;
    protected Trajectory getStackConeMid;
    protected Trajectory getStackConeLow;
    protected Trajectory getStackConeHigh;
    protected Trajectory scoreStackConeHigh;
    protected Trajectory getStackContHigh;
    protected Trajectory scoreStackContHigh;
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
    protected Trajectory park1;
    protected Trajectory park2;
    protected Trajectory park3;
    protected int parkPosition = 2;

    abstract protected void initializeTrajectories();

    @Override
    public void runOpMode() throws InterruptedException {
        Configurables.ARM_POWER = 0.7;
        this.robot = new Robot().init(hardwareMap);


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

        getAndScoreStackCone(getStackOne, scoreStackOne, Configurables.AUTO_TOP1, coneTargetDistance, Configurables.SLIDE_HIGH_AUTO);
        getAndScoreStackCone(getStackTwo, scoreStackTwo, Configurables.AUTO_TOP2, coneTargetDistance, Configurables.SLIDE_HIGH_AUTO);
        getAndScoreStackCone(getStackThree, scoreStackThree, Configurables.AUTO_TOP3, coneTargetDistance +.5, Configurables.SLIDE_MID_AUTO);
        getAndScoreStackCone(getStackFour, scoreStackFour, Configurables.AUTO_TOP4, coneTargetDistance + 0.75, Configurables.SLIDE_LOW_AUTO);
//        getAndScoreStackCone(getStackFive, scoreStackFive, Configurables.AUTO_TOP5, coneTargetDistance + 1, Configurables.SLIDE_HIGH_AUTO);

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

    protected void getAndScoreStackCone(Trajectory getStackConeTrajectory, Trajectory scoreStackConeTrajectory, int height, double coneTargetDistance, int slideHeight) {
        if (getStackConeTrajectory == null || scoreStackConeTrajectory == null) {
            return;
        }

        // Get the cone off the stack
        this.robot.getDrive().followTrajectory(getStackConeTrajectory);
        this.waleAlign(coneTargetDistance);
        sleep(25);
        this.robot.getArm().moveRight();
        this.robot.getWale().stow();
        sleep(200);
        this.robot.getClaw().close();
        sleep(150);

        // Move back to the junction
//        this.robot.getLift().slideToHeight(slideHeight);
        this.robot.getArm().moveMid();
        sleep(50);
        this.robot.getDrive().followTrajectory(scoreStackConeTrajectory);

        // Score
        this.robot.getArm().moveLeft();
        sleep(50);
        this.robot.getClaw().open();
        this.robot.getLift().slideToCone(height);
    }

    private double getCurrentDistance(int samples) {
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
    private void waleAlign(double coneTargetDistance) {
        // Bump the wall
        bump();

        double currentDistance = getCurrentDistance(1);
        double error = Math.abs(coneTargetDistance - currentDistance);


        // Strafe
        telemetry.addLine(String.format("PoseBeforeMove: %s", this.robot.getDrive().getPoseEstimate()));
        if (currentDistance > coneTargetDistance + tolerance) {
            telemetry.addLine(String.format("Moving Left %f inches", error));
            telemetry.addData("currentDistance", currentDistance);
            this.robot.getDrive().update();
            this.robot.getDrive().followTrajectory(this.robot.getDrive().trajectoryBuilder(this.robot.getDrive().getPoseEstimate()).strafeLeft(error).build());
        } else if (currentDistance < coneTargetDistance - tolerance) {
            telemetry.addLine(String.format("Moving Right %f inches", error));
            telemetry.addData("currentDistance", currentDistance);
            this.robot.getDrive().update();
            this.robot.getDrive().followTrajectory(this.robot.getDrive().trajectoryBuilder(this.robot.getDrive().getPoseEstimate()).strafeRight(error).build());
        }
        telemetry.addLine(String.format("PoseAfterMove: %s", this.robot.getDrive().getPoseEstimate()));
        telemetry.update();

        // Update Pose Estimate
        this.robot.getDrive().setPoseEstimate(new Pose2d(-53.4, -8, this.robot.getDrive().getPoseEstimate().getHeading()));
    }

    private void bump() {
        this.robot.getWale().deploy();
        sleep(300);

        while (!this.robot.getWale().isPressed() && opModeIsActive()) {
            this.robot.getDrive().setWeightedDrivePower(new Pose2d(speed, 0, 0));
        }

        this.robot.getDrive().setWeightedDrivePower(new Pose2d(0, 0, 0));

        this.robot.getWale().stow();
    }
}
