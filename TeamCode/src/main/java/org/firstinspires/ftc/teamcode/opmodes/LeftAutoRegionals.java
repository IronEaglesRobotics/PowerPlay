package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables;
import org.firstinspires.ftc.teamcode.hardware.Robot;

@Config
@Autonomous(name = "LeftAutoRegionals", group = "Competition", preselectTeleOp = "MainTeleOp")
public class LeftAutoRegionals extends AutoBase {

    public static double fasterVelocity = 90;

    @Override
    public void initializeTrajectories() {
        // START
        this.initialPosition = new Pose2d(-32.5, -60, Math.toRadians(90));
        this.moveBeacon = drive.trajectoryBuilder(initialPosition)
                .lineToSplineHeading(new Pose2d(-35,-8, Math.toRadians(90)),
                        SampleMecanumDrive.getVelocityConstraint(fasterVelocity, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL))
                .addTemporalMarker(0.3, () -> {
                    robot.getArm().moveLeft();
                    robot.getLift().slideMed();
                })
                .build();
        // START -> SCORE
        this.scorePreload = drive.trajectoryBuilder(moveBeacon.end())
                .lineToLinearHeading(new Pose2d(-34, -14, Math.toRadians(146)))
                .build();
        // SCORE -> STACK
        this.getStackCone = drive.trajectoryBuilder(scorePreload.end())
                .splineTo(new Vector2d(-54, -9.4), Math.toRadians(180))
                .addTemporalMarker(0.05, () -> {

                    robot.getClaw().twistUp();
                })
                .build();
        // STACK -> SCORE
        this.scoreStackCone = drive.trajectoryBuilder(getStackCone.end())
                .lineToSplineHeading(new Pose2d(-34.8,-13.25,Math.toRadians(150)))
                .addTemporalMarker(0.5, () -> {
                    robot.getClaw().twistDown();
                })
                .build();
        // CORRECTIONS
        this.getStackConeCorrection = drive.trajectoryBuilder(scorePreload.end())
                .splineTo(new Vector2d(-54, -7.6), Math.toRadians(180))
                .addTemporalMarker(0.05, () -> {
                    robot.getClaw().twistUp();
                })
                .build();
        this.scoreStackConeCorrection = drive.trajectoryBuilder(getStackCone.end())
                .lineToSplineHeading(new Pose2d(-34.8,-11.6,Math.toRadians(150)))
                .addTemporalMarker(0.3, () -> {
                    robot.getClaw().twistDown();
                })
                .build();
        // CORRECTIONS LAST ONE
        this.getStackConeCorrectionLast = drive.trajectoryBuilder(scorePreload.end())
                .splineTo(new Vector2d(-54.6, -7.2), Math.toRadians(180))
                .addTemporalMarker(0.05, () -> {
                    robot.getClaw().twistUp();
                })
                .build();
        this.scoreStackConeCorrectionLast = drive.trajectoryBuilder(getStackCone.end())
                .lineToSplineHeading(new Pose2d(-34.8,-11.2,Math.toRadians(150)))
                .addTemporalMarker(0.3, () -> {
                    robot.getClaw().twistDown();
                })
                .build();
        // STACK -> PARK1
        this.park1 = drive.trajectoryBuilder(scoreStackCone.end())
                .splineTo(new Vector2d(-58, -8), Math.toRadians(180))
                .addDisplacementMarker(1, () -> {
                    this.robot.getArm().moveMid();
                })
                .build();
        // STACK -> PARK2
        this.park2 = drive.trajectoryBuilder(getStackCone.end())
                .lineToSplineHeading(new Pose2d(-36,-9,Math.toRadians(180)))
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
