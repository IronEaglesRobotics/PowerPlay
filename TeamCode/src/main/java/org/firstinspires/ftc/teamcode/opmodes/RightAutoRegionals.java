package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Config
@Autonomous(name = "RightAutoRegionals", group = "Competition", preselectTeleOp = "MainTeleOp")
public class RightAutoRegionals extends AutoBase {
    @Override
    public void initializeTrajectories() {
        // START
        this.initialPosition = new Pose2d(-32.5, -60, Math.toRadians(90));
        // START -> SCORE
        this.scorePreload = drive.trajectoryBuilder(initialPosition)
                .lineToLinearHeading(new Pose2d(-35.0, -11.5, Math.toRadians(138)))
                .addTemporalMarker(1.5, () -> {
                    robot.getArm().moveLeft();
                })
                .build();
        // SCORE -> STACK
        this.getStackCone = drive.trajectoryBuilder(scorePreload.end())
                .splineTo(new Vector2d(-54.3, -10.2), Math.toRadians(180))
                .addTemporalMarker(0.05, () -> {

                    robot.getClaw().twistUp();
                })
                .build();
        // STACK -> SCORE
        this.scoreStackCone = drive.trajectoryBuilder(getStackCone.end())
                .lineToSplineHeading(new Pose2d(-35,-11.5,Math.toRadians(138)))
                .addTemporalMarker(0.3, () -> {
                    robot.getClaw().twistDown();
                })
                .build();
        // STACK -> PARK1
        this.park1 = drive.trajectoryBuilder(scoreStackCone.end())
                .splineTo(new Vector2d(-58, -10.2), Math.toRadians(180))
                .addDisplacementMarker(1, () -> {
                    this.robot.getArm().moveMid();
                })
                .build();
        // STACK -> PARK2
        this.park2 = drive.trajectoryBuilder(getStackCone.end())
                .lineToSplineHeading(new Pose2d(-36,-11,Math.toRadians(180)))
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
