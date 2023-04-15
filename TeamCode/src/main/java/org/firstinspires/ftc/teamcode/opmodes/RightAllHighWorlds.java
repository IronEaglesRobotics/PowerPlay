package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables;

@Config
@Autonomous(name = "RightAllHigh", group = "Competition", preselectTeleOp = "MainTeleOp")
public class RightAllHighWorlds extends AutoBase {
    public static double fasterVelocity = 110;

    @Override
    public void initializeTrajectories() {
        this.getStackResetPos = new Vector2d(53.4, -8);
        Configurables.AUTOSCORE= Configurables.SLIDE_MAX_AUTO;
        Configurables.ARM_AUTO = Configurables.SCORE_AUTO;
        // START
        this.initialPosition = new Pose2d(34, -59.5, Math.toRadians(90));
        this.moveBeacon = this.robot.getDrive().trajectoryBuilder(initialPosition)
                .lineToSplineHeading(new Pose2d(35,-1, Math.toRadians(90)),
                        SampleMecanumDrive.getVelocityConstraint(fasterVelocity, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL))

                .addTemporalMarker(0.2, robot.getArm()::moveMid)
                .addTemporalMarker(0.6, robot.getClaw()::twistDown)
                .build();
        // START -> SCORE
        this.scorePreload = this.robot.getDrive().trajectoryBuilder(moveBeacon.end())
                .lineToLinearHeading(new Pose2d(30,-14, Math.toRadians(39)))
                .addTemporalMarker(0.5, robot.getArm()::moveAuto)
                .build();

        // Cone 1
        this.getStackOne = this.robot.getDrive().trajectoryBuilder(scorePreload.end())
                .lineToSplineHeading(new Pose2d(51, -9, Math.toRadians(3)))
                .addTemporalMarker(0.45, robot.getClaw()::twistUp)
                .addTemporalMarker(0.7, robot.getWale()::deploy)
                .addTemporalMarker(.6, robot.getArm()::moveScore)
                .build();
        this.scoreStackOne = this.robot.getDrive().trajectoryBuilder(getStackOne.end())
                .lineToSplineHeading(new Pose2d(27.5,-3,Math.toRadians(317.5)))
                .addTemporalMarker(.01,robot.getLift()::slideHighAuto)
                .addTemporalMarker(0.5, robot.getClaw()::twistDown)
                .addTemporalMarker(1, robot.getArm()::moveAuto)
                .build();

        // Cone 2
        this.getStackTwo = this.robot.getDrive().trajectoryBuilder(scoreStackOne.end())
                .lineToSplineHeading(new Pose2d(51, -9, Math.toRadians(3)))
                .addTemporalMarker(.3, robot.getArm()::moveScore)
                .addTemporalMarker(.7, robot.getWale()::deploy)
                .addTemporalMarker(0.6, robot.getClaw()::twistUp)
                .build();



        // Cone 3
        scoreStackTwo = scoreStackOne;
        getStackThree = getStackTwo;
        scoreStackThree = scoreStackOne;
        getStackFour = getStackTwo;
        scoreStackFour = scoreStackOne;
        getStackFive = getStackTwo;
        scoreStackFive = scoreStackOne;

        // STACK -> PARK1
        this.park3 = this.robot.getDrive().trajectoryBuilder(scoreStackTwo.end())
                .splineTo(new Vector2d(56.5, -8), Math.toRadians(0))
                .addDisplacementMarker(1, this.robot.getArm()::moveMid)
                .build();
        // STACK -> PARK2
        this.park2 = this.robot.getDrive().trajectoryBuilder(scoreStackTwo.end())
                .lineToSplineHeading(new Pose2d(34,-15,Math.toRadians(0)))
                .addDisplacementMarker(1, this.robot.getArm()::moveMid)
                .build();
        // PARK2 -> PARK3
        this.park1 = this.robot.getDrive().trajectoryBuilder(park2.end())
                .back(23)
                .addDisplacementMarker(1, this.robot.getArm()::moveMid)
                .build();
    }
}