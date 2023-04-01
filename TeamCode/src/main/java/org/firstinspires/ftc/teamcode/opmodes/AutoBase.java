package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables;
import org.firstinspires.ftc.teamcode.hardware.Robot;

public abstract class AutoBase extends LinearOpMode {
    protected Robot robot;
    protected Pose2d initialPosition;
    protected Trajectory moveBeacon;
    protected Trajectory scorePreload;
    protected Trajectory getStackConeOne;
    protected Trajectory scoreStackConeOne;
    protected Trajectory getStackConeTwo;
    protected Trajectory scoreStackConeTwo;
    protected Trajectory getStackConeThree;
    protected Trajectory scoreStackConeThree;
    protected Trajectory getStackConeFour;
    protected Trajectory scoreStackConeFour;
    protected Trajectory getStackConeFive;
    protected Trajectory scoreStackConeFive;
    protected Trajectory park1;
    protected Trajectory park2;
    protected Trajectory park3;
    protected int parkPosition = 2;

    abstract protected void initializeTrajectories();

    @Override
    public void runOpMode() throws InterruptedException {
        Configurables.ARM_POWER= 0.7;
        this.robot = new Robot().init(hardwareMap);


        initializeTrajectories();

        this.robot.getDrive().setPoseEstimate(initialPosition);

        this.robot.useAutoCamera();

        while (!isStarted()) {
            this.parkPosition = robot.getAutoCamera().getMarkerId();
            telemetry.addData("parkPosition", (parkPosition));
            telemetry.update();
        }


        // Score the preloaded cone
        int armPosition = this.robot.getArm().getCurrentPosition();
        telemetry.addData("Arm_Position",(armPosition));
        telemetry.update();
        robot.getClaw().twistDown();
        robot.getArm().moveMid();
        this.robot.getDrive().followTrajectory(moveBeacon);
        robot.getArm().moveTilt();
        this.robot.getDrive().followTrajectory(scorePreload);
        robot.getArm().moveLeft();
        robot.getLift().autoTop(); // Lower the slide to the height of the top cone
        sleep(100);
        robot.getClaw().open();
        robot.getArm().moveScore();


        getAndScoreStackCone(getStackConeOne, scoreStackConeOne, Configurables.AUTO_TOP1);
        getAndScoreStackCone(getStackConeTwo, scoreStackConeTwo, Configurables.AUTO_TOP2);
        getAndScoreStackCone(getStackConeThree, scoreStackConeThree, Configurables.AUTO_TOP3);
        getAndScoreStackCone(getStackConeFour, scoreStackConeFour, Configurables.AUTO_TOP4);
        getAndScoreStackCone(getStackConeFive, scoreStackConeFive, Configurables.AUTO_TOP5);

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

    protected void getAndScoreStackCone(Trajectory getStackConeTrajectory, Trajectory scoreStackConeTrajectory, int height) {
        // Get the cone off the stack
        this.robot.getDrive().followTrajectory(getStackConeTrajectory);
        robot.getClaw().openWide();
        robot.getArm().moveRight();
        sleep(250);
        this.robot.getClaw().close();
        sleep(200);

        // Move back to the junction
        this.robot.getLift().slideScorAuto();
        this.robot.getArm().moveAuto();
        sleep(100);
        this.robot.getDrive().followTrajectory(scoreStackConeTrajectory);

        // Score
        this.robot.getArm().moveLeft();
        sleep(100);
        this.robot.getClaw().open();
        this.robot.getLift().slideToCone(height);
    }
}
