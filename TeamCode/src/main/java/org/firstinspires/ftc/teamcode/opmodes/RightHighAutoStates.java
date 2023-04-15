package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables;
@Disabled
@Config
@Autonomous(name = "RightHighStates", group = "Competition", preselectTeleOp = "MainTeleOp")
public class RightHighAutoStates extends AutoBaseSafe {

    public static double fasterVelocity = 110;

    @Override
    public void initializeTrajectories() {
        this.getStackResetPos = new Vector2d(53.4, -8);
        Configurables.AUTOSCORE= Configurables.SLIDE_MAX;
        Configurables.ARM_AUTO = Configurables.ARM_LEFT;
        // START
        this.initialPosition = new Pose2d(33.5, -59.5, Math.toRadians(90));
        this.moveBeacon = this.robot.getDrive().trajectoryBuilder(initialPosition)
                .lineToSplineHeading(new Pose2d(35,-8, Math.toRadians(90)),
                        SampleMecanumDrive.getVelocityConstraint(fasterVelocity, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL))
                .addTemporalMarker(0.3, () -> {
                    robot.getArm().moveTilt();
                    robot.getLift().slideMed();
                })
                .build();
        // START -> SCORE
        this.scorePreload = this.robot.getDrive().trajectoryBuilder(moveBeacon.end())
                .lineToLinearHeading(new Pose2d(33, -11.5, Math.toRadians(43)))
                .build();

        // Cone 1
        this.getStackOne = this.robot.getDrive().trajectoryBuilder(scorePreload.end())
                .splineTo(new Vector2d(53.2, -9), Math.toRadians(0))
                .addTemporalMarker(0.15, robot.getClaw()::twistUp)
                .build();
        this.scoreStackOne = this.robot.getDrive().trajectoryBuilder(getStackOne.end())
                .lineToSplineHeading(new Pose2d(34,-7,Math.toRadians(-40)))
                .addTemporalMarker(0.5, robot.getClaw()::twistDown)
                .addTemporalMarker(.5, robot.getArm()::moveScore)
                .build();

        // Cone 2
        this.getStackTwo = this.robot.getDrive().trajectoryBuilder(scoreStackOne.end())
                .lineToSplineHeading(new Pose2d(53.2, -9.5, Math.toRadians(0)))
                .addTemporalMarker(0.15, robot.getClaw()::twistUp)
                .build();

        this.scoreStackTwo = this.robot.getDrive().trajectoryBuilder(getStackTwo.end())
                .lineToSplineHeading(new Pose2d(33,-7,Math.toRadians(-38)))
                .addTemporalMarker(0.5, robot.getClaw()::twistDown)
                .addTemporalMarker(.5, robot.getArm()::moveScore)
                .build();

        // Cone 3
        this.getStackThree = this.robot.getDrive().trajectoryBuilder(scoreStackTwo.end())
                .lineToSplineHeading(new Pose2d(52.3,-10.5, Math.toRadians(0)))
                .addTemporalMarker(0.15, robot.getClaw()::twistUp)
                .build();
        this.scoreStackThree = this.robot.getDrive().trajectoryBuilder(getStackThree.end())
                .lineToSplineHeading(new Pose2d(33,-7,Math.toRadians(-38)))
                .addTemporalMarker(0.3, robot.getClaw()::twistDown)
                .addTemporalMarker(.5, robot.getArm()::moveScore)
                .build();

        // Cone 4
        this.getStackFour = this.getStackThree;
        this.scoreStackFour = this.scoreStackThree;

        // Cone 5
        this.getStackFive = this.robot.getDrive().trajectoryBuilder(scoreStackThree.end())
                .lineToSplineHeading(new Pose2d(52.3, -11.5, Math.toRadians(0)))
                .addTemporalMarker(0.15, robot.getClaw()::twistUp)
                .build();
        this.scoreStackFive = this.robot.getDrive().trajectoryBuilder(getStackFive.end())
                .lineToSplineHeading(new Pose2d(33,-7,Math.toRadians(-38)))
                .addTemporalMarker(0.3, robot.getClaw()::twistDown)
                .addTemporalMarker(.5, robot.getArm()::moveScore)
                .build();

        // STACK -> PARK1

        // STACK -> PARK2
        this.park2 = this.robot.getDrive().trajectoryBuilder(scoreStackFive.end())
                .lineToSplineHeading(new Pose2d(36,-12,Math.toRadians(0)))
                .addDisplacementMarker(1, this.robot.getArm()::moveMid)
                .build();
        // PARK2 -> PARK3
        this.park3 = this.robot.getDrive().trajectoryBuilder(scoreStackFive.end())
                .splineTo(new Vector2d(57, -11), Math.toRadians(0))
                .addDisplacementMarker(1, this.robot.getArm()::moveMid)
                .build();

        this.park1 = this.robot.getDrive().trajectoryBuilder(park2.end())
                .back(23)
                .addDisplacementMarker(1, this.robot.getArm()::moveMid)
                .build();

    }
}
