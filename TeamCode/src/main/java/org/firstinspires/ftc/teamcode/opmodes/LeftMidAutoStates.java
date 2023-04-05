package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables;

@Config
@Autonomous(name = "LeftMid", group = "Competition", preselectTeleOp = "MainTeleOp")
public class LeftMidAutoStates extends AutoBase {

    public static double fasterVelocity = 110;

    @Override
    public void initializeTrajectories() {
        Configurables.AUTOSCORE= Configurables.SLIDE_MID;
        Configurables.ARM_AUTO = Configurables.SCORE_AUTO;
        // START
        this.initialPosition = new Pose2d(-33.7, -59.5, Math.toRadians(90));
        this.moveBeacon = this.robot.getDrive().trajectoryBuilder(initialPosition)
                .lineToSplineHeading(new Pose2d(-35,-8, Math.toRadians(90)),
                        SampleMecanumDrive.getVelocityConstraint(fasterVelocity, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL))
                .addTemporalMarker(0.3, () -> {
                    robot.getArm().moveTilt();
                })
                .build();
        // START -> SCORE
        this.scorePreload = this.robot.getDrive().trajectoryBuilder(moveBeacon.end())
                .lineToLinearHeading(new Pose2d(-32, -14, Math.toRadians(146)))
                .build();

        // Cone 1
        this.getStackConeOne = this.robot.getDrive().trajectoryBuilder(scorePreload.end())
                .lineToSplineHeading(new Pose2d(-53, -8, Math.toRadians(180)))
                .addTemporalMarker(0.15, robot.getClaw()::twistUp)
                .addTemporalMarker(.5, robot.getArm()::moveScore)

                .build();
        this.scoreStackConeOne = this.robot.getDrive().trajectoryBuilder(getStackConeOne.end())
                .lineToSplineHeading(new Pose2d(-33.4,-14.5,Math.toRadians(148)))
                .addTemporalMarker(0.1, robot.getClaw()::twistDown)
                .build();

        // Cone 2
        this.getStackConeTwo = this.getStackConeOne;
        this.scoreStackConeTwo = this.scoreStackConeOne;

        // Cone 3
        this.getStackConeThree = this.robot.getDrive().trajectoryBuilder(scoreStackConeTwo.end())
                .lineToSplineHeading(new Pose2d(-52.3, -7, Math.toRadians(180)))
                .addTemporalMarker(0.15, robot.getClaw()::twistUp)
                .addTemporalMarker(.5, robot.getArm()::moveScore)
                .build();
        this.scoreStackConeThree = this.robot.getDrive().trajectoryBuilder(getStackConeThree.end())
                .lineToSplineHeading(new Pose2d(-33.4,-12.5,Math.toRadians(148)))
                .addTemporalMarker(0.1, robot.getClaw()::twistDown)
                .build();

        // Cone 4
        this.getStackConeFour = this.getStackConeThree;
        this.scoreStackConeFour = this.scoreStackConeThree;

        // Cone 5
        this.getStackConeFive = this.robot.getDrive().trajectoryBuilder(scoreStackConeFour.end())
                .lineToSplineHeading(new Pose2d(-52.3, -7, Math.toRadians(180)))
                .addTemporalMarker(0.15, robot.getClaw()::twistUp)
                .addTemporalMarker(.5, robot.getArm()::moveScore)
                .build();
        this.scoreStackConeFive = this.robot.getDrive().trajectoryBuilder(getStackConeFive.end())
                .lineToSplineHeading(new Pose2d(-33.4,-12.5,Math.toRadians(148)))
                .addTemporalMarker(0.1, robot.getClaw()::twistDown)
                .build();

        // STACK -> PARK1
        this.park1 = this.robot.getDrive().trajectoryBuilder(scoreStackConeFive.end())
                .splineTo(new Vector2d(-54, -8), Math.toRadians(180))
                .addDisplacementMarker(1, this.robot.getArm()::moveMid)
                .build();
        // STACK -> PARK2
        this.park2 = this.robot.getDrive().trajectoryBuilder(scoreStackConeFive.end())
                .lineToSplineHeading(new Pose2d(-34,-6.5,Math.toRadians(180)))
                .addDisplacementMarker(1, this.robot.getArm()::moveMid)
                .build();
        // PARK2 -> PARK3
        this.park3 = this.robot.getDrive().trajectoryBuilder(park2.end())
                .back(23)
                .addDisplacementMarker(1, this.robot.getArm()::moveMid)
                .build();
    }
}
