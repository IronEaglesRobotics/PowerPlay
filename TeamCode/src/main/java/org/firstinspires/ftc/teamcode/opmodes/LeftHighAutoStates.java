package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables;

@Config
@Autonomous(name = "LeftHigh", group = "Competition", preselectTeleOp = "MainTeleOp")
public class LeftHighAutoStates extends AutoBase {

    public static double fasterVelocity = 110;

    @Override
    public void initializeTrajectories() {
        Configurables.AUTOSCORE= Configurables.SLIDE_MAX_AUTO;
        Configurables.ARM_AUTO = Configurables.SCORE_AUTO;
        // START
        this.initialPosition = new Pose2d(-34, -59.5, Math.toRadians(90));
        this.moveBeacon = this.robot.getDrive().trajectoryBuilder(initialPosition)
                .lineToSplineHeading(new Pose2d(-35,-1, Math.toRadians(90)),
                        SampleMecanumDrive.getVelocityConstraint(fasterVelocity, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL))
                .addTemporalMarker(0.3, () -> {
                    robot.getArm().moveTilt();
                    robot.getLift().slideMed();
                })
                .build();
        // START -> SCORE
        this.scorePreload = this.robot.getDrive().trajectoryBuilder(moveBeacon.end())
                .lineToLinearHeading(new Pose2d(-33, -13, Math.toRadians(146)))
                .build();

        // Cone 1
        this.getStackConeOne = this.robot.getDrive().trajectoryBuilder(scorePreload.end())
                .lineToSplineHeading(new Pose2d(-53.4, -8, Math.toRadians(180)))
                .addTemporalMarker(0.45, robot.getClaw()::twistUp)
                .build();
        this.scoreStackConeOne = this.robot.getDrive().trajectoryBuilder(getStackConeOne.end())
                .lineToSplineHeading(new Pose2d(-27,-6.5,Math.toRadians(223)))
                .addTemporalMarker(0.5, robot.getClaw()::twistDown)
                .build();

        // Cone 2
        this.getStackConeTwo = this.robot.getDrive().trajectoryBuilder(scoreStackConeOne.end())
                .lineToSplineHeading(new Pose2d(-53.8, -10.5, Math.toRadians(180)))
                .addTemporalMarker(.5, robot.getArm()::moveScore)
                .addTemporalMarker(0.7, robot.getClaw()::twistUp)
                .build();

        this.scoreStackConeTwo = this.robot.getDrive().trajectoryBuilder(getStackConeTwo.end())
                .lineToSplineHeading(new Pose2d(-28,-7.5,Math.toRadians(223)))
                .addTemporalMarker(0.5, robot.getClaw()::twistDown)
                .build();

        // Cone 3
        this.getStackConeThree = this.robot.getDrive().trajectoryBuilder(scoreStackConeOne.end())
                .lineToSplineHeading(new Pose2d(-53.3,-11.5, Math.toRadians(180)))
                .addTemporalMarker(.5, robot.getArm()::moveScore)
                .addTemporalMarker(0.7, robot.getClaw()::twistUp)
                .build();
        this.scoreStackConeThree = this.robot.getDrive().trajectoryBuilder(getStackConeThree.end())
                .lineToSplineHeading(new Pose2d(-28,-9.8,Math.toRadians(223)))
                .addTemporalMarker(0.3, robot.getClaw()::twistDown)
                .build();

        // Cone 4
        this.getStackConeFour = this.getStackConeThree;
        this.scoreStackConeFour = this.scoreStackConeThree;

        // Cone 5
        this.getStackConeFive = this.robot.getDrive().trajectoryBuilder(scoreStackConeOne.end())
                .lineToSplineHeading(new Pose2d(-53.3, -14, Math.toRadians(180)))
                .addTemporalMarker(.5, robot.getArm()::moveScore)
                .addTemporalMarker(0.7, robot.getClaw()::twistUp)
                .build();
        this.scoreStackConeFive = this.robot.getDrive().trajectoryBuilder(getStackConeFive.end())
                .lineToSplineHeading(new Pose2d(-27,-12,Math.toRadians(223)))
                .addTemporalMarker(0.3, robot.getClaw()::twistDown)
                .build();

        // STACK -> PARK1
        this.park1 = this.robot.getDrive().trajectoryBuilder(scoreStackConeTwo.end())
                .splineTo(new Vector2d(-55, -15), Math.toRadians(180))
                .addDisplacementMarker(1, this.robot.getArm()::moveMid)
                .build();
        // STACK -> PARK2
        this.park2 = this.robot.getDrive().trajectoryBuilder(getStackConeTwo.end())
                .lineToSplineHeading(new Pose2d(-34,-20,Math.toRadians(180)))
                .addDisplacementMarker(1, this.robot.getArm()::moveMid)
                .build();
        // PARK2 -> PARK3
        this.park3 = this.robot.getDrive().trajectoryBuilder(park2.end())
                .back(24)
                .addDisplacementMarker(1, this.robot.getArm()::moveMid)
                .build();
    }
}