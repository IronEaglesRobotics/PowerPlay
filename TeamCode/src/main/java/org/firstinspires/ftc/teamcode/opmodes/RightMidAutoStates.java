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
@Autonomous(name = "RightMid", group = "Competition", preselectTeleOp = "MainTeleOp")
public class RightMidAutoStates extends AutoBase {

    public static double fasterVelocity = 90;


    @Override
    public void initializeTrajectories() {
        Configurables.AUTOSCORE= Configurables.SLIDE_MID;
        Configurables.ARM_AUTO = Configurables.ARM_TILT;
        // START
        this.initialPosition = new Pose2d(35, -59.5, Math.toRadians(90));
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
                .lineToLinearHeading(new Pose2d(32.5, -11.5, Math.toRadians(46)))
                .build();

        // Cone 1
        this.getStackConeOne = this.robot.getDrive().trajectoryBuilder(scorePreload.end())
                .splineTo(new Vector2d(55, -9), Math.toRadians(0))
                .addTemporalMarker(0.15, robot.getClaw()::twistUp)
                .build();
        this.scoreStackConeOne = this.robot.getDrive().trajectoryBuilder(getStackConeOne.end())
                .lineToSplineHeading(new Pose2d(34,-12.7,Math.toRadians(28)))
                .addTemporalMarker(0.5, robot.getClaw()::twistDown)
                .build();

        // Cone 2
        this.getStackConeTwo = this.robot.getDrive().trajectoryBuilder(scorePreload.end())
                .splineTo(new Vector2d(55, -9), Math.toRadians(0))
                .addTemporalMarker(0.15, robot.getClaw()::twistUp)
                .build();
        this.scoreStackConeTwo= this.robot.getDrive().trajectoryBuilder(getStackConeTwo.end())
                .lineToSplineHeading(new Pose2d(34,-12.7,Math.toRadians(26)))
                .addTemporalMarker(0.5, robot.getClaw()::twistDown)
                .build();

        // Cone 3
        this.getStackConeThree = this.robot.getDrive().trajectoryBuilder(scoreStackConeTwo.end())
                .splineTo(new Vector2d(52, -9), Math.toRadians(0))
                .addTemporalMarker(0.15, robot.getClaw()::twistUp)
                .build();
        this.scoreStackConeThree = this.robot.getDrive().trajectoryBuilder(getStackConeThree.end())
                .lineToSplineHeading(new Pose2d(35,-12.7,Math.toRadians(31)))
                .addTemporalMarker(0.3, robot.getClaw()::twistDown)
                .build();



        this.getStackConeFour = this.robot.getDrive().trajectoryBuilder(scoreStackConeThree.end())
                .splineTo(new Vector2d(52, -7), Math.toRadians(0))
                .addTemporalMarker(0.15, robot.getClaw()::twistUp)
                .build();
        this.scoreStackConeFour = this.robot.getDrive().trajectoryBuilder(getStackConeThree.end())
                .lineToSplineHeading(new Pose2d(35,-9,Math.toRadians(38)))
                .addTemporalMarker(0.3, robot.getClaw()::twistDown)
                .build();

        // Cone 5
        this.getStackConeFive = this.robot.getDrive().trajectoryBuilder(scoreStackConeFour.end())
                .splineTo(new Vector2d(54, -6), Math.toRadians(0))
                .addTemporalMarker(0.15, robot.getClaw()::twistUp)
                .build();
        this.scoreStackConeFive = this.robot.getDrive().trajectoryBuilder(getStackConeFour.end())
                .lineToSplineHeading(new Pose2d(33,-8,Math.toRadians(38)))
                .addTemporalMarker(0.3, robot.getClaw()::twistDown)
                .build();

        // STACK -> PARK1
        this.park3 = this.robot.getDrive().trajectoryBuilder(scoreStackConeFive.end())
                .splineTo(new Vector2d(57, -5.5), Math.toRadians(0))
                .addDisplacementMarker(1, this.robot.getArm()::moveMid)
                .build();
        // STACK -> PARK2
        this.park2 = this.robot.getDrive().trajectoryBuilder(scoreStackConeFive.end())
                .lineToSplineHeading(new Pose2d(34.6,-2,Math.toRadians(0)))
                .addDisplacementMarker(1, this.robot.getArm()::moveMid)
                .build();
        // PARK2 -> PARK3
        this.park1 = this.robot.getDrive().trajectoryBuilder(park2.end())
                .back(24)
                .addDisplacementMarker(1, this.robot.getArm()::moveMid)
                .build();
    }
}
