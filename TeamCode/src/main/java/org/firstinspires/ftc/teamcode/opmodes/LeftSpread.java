package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables;

@Config
@Autonomous(name = "LeftSpread", group = "Competition", preselectTeleOp = "MainTeleOp")
public class LeftSpread extends AutoBaseSpread {

    public static double fasterVelocity = 110;

    @Override
    public void initializeTrajectories() {
        this.getStackResetPos = new Vector2d(-53.4, -8);
        Configurables.AUTOSCORE = Configurables.SLIDE_MAX_AUTO;
        Configurables.ARM_AUTO = Configurables.SCORE_AUTO;
        // START
        this.initialPosition = new Pose2d(-34, -59.5, Math.toRadians(90));
        this.moveBeacon = this.robot.getDrive().trajectoryBuilder(initialPosition)
                .lineToSplineHeading(new Pose2d(-35, -1, Math.toRadians(90)),
                        SampleMecanumDrive.getVelocityConstraint(fasterVelocity, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL))

                .addTemporalMarker(0.2, robot.getArm()::moveMid)
                .addTemporalMarker(0.6, robot.getClaw()::twistDown)
                .build();
        // START -> SCORE
        this.scorePreload = this.robot.getDrive().trajectoryBuilder(moveBeacon.end())
                .lineToLinearHeading(new Pose2d(-32, -14.5, Math.toRadians(146)))
                .addTemporalMarker(0.5, robot.getArm()::moveAuto)
                .build();

        // Cone 1
        this.getStackConePreload = this.robot.getDrive().trajectoryBuilder(scorePreload.end())
                .lineToSplineHeading(new Pose2d(-51, -8, Math.toRadians(180)))
                .addTemporalMarker(0.45, robot.getClaw()::twistUp)
                .addTemporalMarker(0.3, robot.getWale()::deploy)
                .addTemporalMarker(.5, robot.getArm()::moveScore)
                .build();
//High
        this.scoreStackConeHigh = this.robot.getDrive().trajectoryBuilder(getStackConePreload.end())
                .lineToSplineHeading(new Pose2d(-5, -11, Math.toRadians(148)))
                .addTemporalMarker(.01,robot.getLift()::slideHighAuto)
                .addTemporalMarker(0.5, robot.getClaw()::twistDown)
                .addTemporalMarker(.7, robot.getArm()::moveAuto)
                .build();

        this.getStackConeHigh = this.robot.getDrive().trajectoryBuilder(scoreStackConeHigh.end())
                .lineToLinearHeading(new Pose2d(-51, -8, Math.toRadians(180)))
                .addTemporalMarker(.5, robot.getArm()::moveScore)
                .addTemporalMarker(0.8, robot.getWale()::deploy)
                .addTemporalMarker(0.6, robot.getClaw()::twistUp)
                .build();
//Mid
        this.scoreStackConeMid = this.robot.getDrive().trajectoryBuilder(getStackConePreload.end())
                .lineToLinearHeading(new Pose2d(-30, -13, Math.toRadians(146)))
                .addTemporalMarker(.01,robot.getLift()::slideMidAuto)
                .addTemporalMarker(0.3, robot.getClaw()::twistDown)
                .addTemporalMarker(0.5, robot.getArm()::moveAuto)
                .build();
        this.getStackConeMid = this.robot.getDrive().trajectoryBuilder(scoreStackConeMid.end())
                .lineToLinearHeading(new Pose2d(-51, -8, Math.toRadians(180)))
                .addTemporalMarker(3, robot.getArm()::moveScore)
                .addTemporalMarker(0.8, robot.getWale()::deploy)
                .addTemporalMarker(0.5, robot.getClaw()::twistUp)
                .build();
//Low
        this.scoreStackConeLow = this.robot.getDrive().trajectoryBuilder(getStackConePreload.end())
                .lineToSplineHeading(new Pose2d(-56, -9, Math.toRadians(138)))
                .addTemporalMarker(.01,robot.getLift()::slideLowAuto)
                .addTemporalMarker(0.1, robot.getClaw()::twistDown)
                .addTemporalMarker(.1, robot.getArm()::moveLeft)
                .build();

        this.getStackConeLow = this.robot.getDrive().trajectoryBuilder(scoreStackConeLow.end())
                .lineToLinearHeading(new Pose2d(-51, -8, Math.toRadians(180)))
                .addTemporalMarker(.001, robot.getArm()::moveScore)
                .addTemporalMarker(0.8, robot.getWale()::deploy)
                .addTemporalMarker(0.6, robot.getClaw()::twistUp)
                .build();

 //DangerHigh
        this.scoreStackContHigh = this.robot.getDrive().trajectoryBuilder(getStackConePreload.end())
                .lineToSplineHeading(new Pose2d(-27,-3,Math.toRadians(220)))
                .addTemporalMarker(.01,robot.getLift()::slideHighAuto)
                .addTemporalMarker(0.5, robot.getClaw()::twistDown)
                .addTemporalMarker(1, robot.getArm()::moveAuto)
                .build();

        this.getStackContHigh = this.robot.getDrive().trajectoryBuilder(scoreStackContHigh.end())
                .lineToSplineHeading(new Pose2d(-51, -8, Math.toRadians(180)))
                .addTemporalMarker(.5, robot.getArm()::moveScore)
                .addTemporalMarker(0.3, robot.getWale()::deploy)
                .addTemporalMarker(0.6, robot.getClaw()::twistUp)
                .build();

//Implementation
        getStackOne = getStackConePreload;
        scoreStackOne = scoreStackConeHigh;

        getStackTwo = getStackConeHigh;
        scoreStackTwo = scoreStackConeHigh;

        getStackThree = getStackConeHigh;
        scoreStackThree = scoreStackConeMid;

        getStackFour = getStackConeMid;
        scoreStackFour = scoreStackConeLow;

        getStackFive = getStackConeLow;
        scoreStackFive = scoreStackContHigh;

        // STACK -> PARK1
        this.park1 = this.robot.getDrive().trajectoryBuilder(scoreStackContHigh.end())
                .splineTo(new Vector2d(-58, -9), Math.toRadians(180))
                .addDisplacementMarker(2.4, this.robot.getArm()::moveMid)
                .build();
        // STACK -> PARK2
        this.park2 = this.robot.getDrive().trajectoryBuilder(scoreStackContHigh.end())
                .lineToSplineHeading(new Pose2d(-34, -15, Math.toRadians(180)))
                .addDisplacementMarker(1, this.robot.getArm()::moveMid)
                .build();
        // PARK2 -> PARK3
        this.park3 = this.robot.getDrive().trajectoryBuilder(park2.end())
                .back(23)
                .addDisplacementMarker(1.3, this.robot.getArm()::moveMid)
                .build();
    }
}