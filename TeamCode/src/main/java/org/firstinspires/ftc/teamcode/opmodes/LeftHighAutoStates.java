package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@Config
@Autonomous(name = "LeftHigh", group = "Competition", preselectTeleOp = "MainTeleOp")
public class LeftHighAutoStates extends AutoBaseHigh {

    public static double fasterVelocity = 110;

    @Override
    public void initializeTrajectories() {
        // START
        this.initialPosition = new Pose2d(-33, -59.5, Math.toRadians(90));
        this.moveBeacon = drive.trajectoryBuilder(initialPosition)
                .lineToSplineHeading(new Pose2d(-35,-8, Math.toRadians(90)),
                        SampleMecanumDrive.getVelocityConstraint(fasterVelocity, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL))
                .addTemporalMarker(0.3, () -> {
                    robot.getArm().moveTilt();
                    robot.getLift().slideMed();
                })
                .build();
        // START -> SCORE
        this.scorePreload = drive.trajectoryBuilder(moveBeacon.end())
                .lineToLinearHeading(new Pose2d(-32, -13, Math.toRadians(146)))
                .build();
        // SCORE -> STACK
        this.getStackConeOne = drive.trajectoryBuilder(scorePreload.end())
                .lineToSplineHeading(new Pose2d(-52.4, -8, Math.toRadians(180)))
                .addTemporalMarker(0.15, () -> {

                    robot.getClaw().twistUp();
                })
                .build();
        // STACK -> SCORE
        this.scoreStackConeOne = drive.trajectoryBuilder(getStackConeOne.end())
                .lineToSplineHeading(new Pose2d(-30,-8.5,Math.toRadians(223)))
                .addTemporalMarker(0.5, () -> {
                    robot.getClaw().twistDown();
                })
                .build();

        this.getStackCone = drive.trajectoryBuilder(scoreStackConeOne.end())
                .lineToSplineHeading(new Pose2d(-52.7, -9.5, Math.toRadians(180)))
                .addTemporalMarker(0.15, () -> {

                    robot.getClaw().twistUp();
                })
                .build();

        this.scoreStackCone = drive.trajectoryBuilder(getStackCone.end())
                .lineToSplineHeading(new Pose2d(-30,-9.5,Math.toRadians(223)))
                .addTemporalMarker(0.5, () -> {
                    robot.getClaw().twistDown();
                })
                .build();
        // CORRECTIONS
        this.getStackConeCorrection = drive.trajectoryBuilder(scoreStackConeOne.end())
                .lineToSplineHeading(new Pose2d(-52.5,-11, Math.toRadians(180)))
                .addTemporalMarker(0.15, () -> {
                    robot.getClaw().twistUp();
                })
                .build();
        this.scoreStackConeCorrection = drive.trajectoryBuilder(getStackConeCorrection.end())
                .lineToSplineHeading(new Pose2d(-30,-11.8,Math.toRadians(223)))
                .addTemporalMarker(0.3, () -> {
                    robot.getClaw().twistDown();
                })
                .build();
        // CORRECTIONS LAST ONE
        this.getStackConeCorrectionLast = drive.trajectoryBuilder(scoreStackConeOne.end())
                .lineToSplineHeading(new Pose2d(-52.1, -13, Math.toRadians(180)))
                .addTemporalMarker(0.15, () -> {
                    robot.getClaw().twistUp();
                })
                .build();
        this.scoreStackConeCorrectionLast = drive.trajectoryBuilder(getStackConeCorrectionLast.end())
                .lineToSplineHeading(new Pose2d(-29,-14,Math.toRadians(223)))
                .addTemporalMarker(0.3, () -> {
                    robot.getClaw().twistDown();
                })
                .build();
        // STACK -> PARK1
        this.park1 = drive.trajectoryBuilder(scoreStackCone.end())
                .splineTo(new Vector2d(-56, -15), Math.toRadians(180))
                .addDisplacementMarker(1, () -> {
                    this.robot.getArm().moveMid();
                })
                .build();
        // STACK -> PARK2
        this.park2 = drive.trajectoryBuilder(getStackCone.end())
                .lineToSplineHeading(new Pose2d(-33,-17,Math.toRadians(180)))
                .addDisplacementMarker(1, () -> {
                    this.robot.getArm().moveMid();
                })
                .build();
        // PARK2 -> PARK3
        this.park3 = drive.trajectoryBuilder(park2.end())
                .back(24)
                .addDisplacementMarker(1, () -> {
                    this.robot.getArm().moveMid();
                })
                .build();
    }
}
