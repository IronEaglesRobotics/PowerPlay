package org.firstinspires.ftc.teamcode.opmode.autonomous;

import static org.firstinspires.ftc.teamcode.hardware.Slides.heights;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.hardware.Arm;
import org.firstinspires.ftc.teamcode.hardware.Slides;
import org.firstinspires.ftc.teamcode.roadrunner.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.util.CameraPosition;

@Disabled
@Autonomous(name = "Right Safe High 1 + 5", group = "Right Competition", preselectTeleOp = "Main TeleOp")
public class RightSafeAuto extends AbstractAuto {

    // trajectories
    public Trajectory start;
    public Trajectory startScore;
    public Trajectory load1;
    public Trajectory load2;
    public Trajectory load3;
    public Trajectory load4;
    public Trajectory load5;
    public Trajectory score1;
    public Trajectory score2;
    public Trajectory score3;
    public Trajectory score4;
    public Trajectory score5;
    public Trajectory park1;
    public Trajectory park2;
    public Trajectory park3;

    public double maxVel = 74;
    public double maxAcc = 48;

    @Override
    public void setAlliance() {}

    @Override
    public void setCameraPosition() {
        cameraPosition = CameraPosition.RIGHT;
    }

    @Override
    public boolean useCamera() {
        return true;
    }

    @Override
    public void makeTrajectories() {
        double xLoad = 1.4;
        double yLoad = -0.9;

        double xInt = 1.4;
        double yInt = -0.9;

        double xScore = 1.4;
        double yScore = -0.9;

        // positions
        Pose2d start1 = new Pose2d(-31.25,63,Math.toRadians(-90));
        Pose2d start2 = new Pose2d(-36,26,Math.toRadians(-90));
        Pose2d start3 = new Pose2d(-20,13,Math.toRadians(0));
        Pose2d start4 = new Pose2d(-6.1,17.5,Math.toRadians(45));

        Pose2d sLoad1 = new Pose2d(-25.0,9.3,Math.toRadians(0));
        Pose2d sLoad2 = sLoad1.plus(new Pose2d(xInt, yInt));
        Pose2d sLoad3 = sLoad2.plus(new Pose2d(xInt, yInt));
        Pose2d sLoad4 = sLoad3.plus(new Pose2d(xInt, yInt));
        Pose2d sLoad5 = sLoad4.plus(new Pose2d(xInt, yInt));
        Pose2d lLoad1 = new Pose2d(-59.5,9.3,Math.toRadians(0));
        Pose2d lLoad2 = lLoad1.plus(new Pose2d(xLoad, yLoad));
        Pose2d lLoad3 = lLoad2.plus(new Pose2d(xLoad, yLoad));
        Pose2d lLoad4 = lLoad3.plus(new Pose2d(xLoad, yLoad));
        Pose2d lLoad5 = lLoad4.plus(new Pose2d(xLoad, yLoad));

        Pose2d lScore1 = new Pose2d(-25.0,9.3,Math.toRadians(0));
        Pose2d lScore2 = lScore1.plus(new Pose2d(xInt, yInt));
        Pose2d lScore3 = lScore2.plus(new Pose2d(xInt, yInt));
        Pose2d lScore4 = lScore3.plus(new Pose2d(xInt, yInt));
        Pose2d lScore5 = lScore4.plus(new Pose2d(xInt, yInt));
        Pose2d sScore1 = new Pose2d(-4.8,17.5,Math.toRadians(45));
        Pose2d sScore2 = sScore1.plus(new Pose2d(xScore, yScore));
        Pose2d sScore3 = sScore2.plus(new Pose2d(xScore, yScore));
        Pose2d sScore4 = sScore3.plus(new Pose2d(xScore, yScore));
        Pose2d sScore5 = sScore4.plus(new Pose2d(xScore, yScore));

        Pose2d lPark1 = new Pose2d(-4,17, Math.toRadians(0));

        Pose2d sPark2 = new Pose2d(-21, 3, Math.toRadians(0));
        Pose2d lPark2 = new Pose2d(-28,3, Math.toRadians(0));

        Pose2d sPark3 = new Pose2d(-21, 5, Math.toRadians(0));
        Pose2d lPark3 = new Pose2d(-56.5,5,Math.toRadians(0));

        // preload
        this.start = robot.drive.trajectoryBuilder(start1)
                .lineToSplineHeading(start2,
                        SampleMecanumDrive.getVelocityConstraint(maxVel, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(maxAcc)
                )
                .splineToSplineHeading(start3, Math.toRadians(0),
                        SampleMecanumDrive.getVelocityConstraint(maxVel, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(maxAcc)
                )
                .splineToSplineHeading(start4, Math.toRadians(45),
                        SampleMecanumDrive.getVelocityConstraint(maxVel, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(maxAcc)
                )
                .build();
//        this.startScore = robot.drive.trajectoryBuilder(start.end())
//                .lineToSplineHeading(start4,
//                        SampleMecanumDrive.getVelocityConstraint(80, 180, DriveConstants.TRACK_WIDTH),
//                        SampleMecanumDrive.getAccelerationConstraint(maxAcc)
//                )
//                .build();

        // cone stacks
        load1 = robot.drive.trajectoryBuilder(start.end(), true)
                .splineToSplineHeading(sLoad1, Math.toRadians(180),
                        SampleMecanumDrive.getVelocityConstraint(maxVel, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(maxAcc)
                )
                .lineToSplineHeading(lLoad1,
                        SampleMecanumDrive.getVelocityConstraint(maxVel, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(maxAcc)
                )
                .addSpatialMarker(new Vector2d(lLoad1.plus(new Pose2d(2.5)).getX(), lLoad1.getY()), () -> {
                    robot.claw.close();
                })
                .build();
        score1 = robot.drive.trajectoryBuilder(load1.end())
                .lineToSplineHeading(lScore1,
                        SampleMecanumDrive.getVelocityConstraint(maxVel, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(maxAcc)
                )
                .splineToSplineHeading(sScore1, Math.toRadians(45),
                        SampleMecanumDrive.getVelocityConstraint(maxVel, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(maxAcc)
                )
                .build();

        load2 = robot.drive.trajectoryBuilder(score1.end(), true)
                .splineToSplineHeading(sLoad2, Math.toRadians(180),
                        SampleMecanumDrive.getVelocityConstraint(maxVel, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(maxAcc)
                )
                .lineToSplineHeading(lLoad2,
                        SampleMecanumDrive.getVelocityConstraint(maxVel, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(maxAcc)
                )
                .addSpatialMarker(new Vector2d(lLoad2.plus(new Pose2d(2.5)).getX(), lLoad2.getY()), () -> {
                    robot.claw.close();
                })
                .build();
        score2 = robot.drive.trajectoryBuilder(load2.end())
                .lineToSplineHeading(lScore2,
                        SampleMecanumDrive.getVelocityConstraint(maxVel, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(maxAcc)
                )
                .splineToSplineHeading(sScore2, Math.toRadians(45),
                        SampleMecanumDrive.getVelocityConstraint(maxVel, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(maxAcc)
                )
                .build();

        load3 = robot.drive.trajectoryBuilder(score2.end(), true)
                .splineToSplineHeading(sLoad3, Math.toRadians(180),
                        SampleMecanumDrive.getVelocityConstraint(maxVel, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(maxAcc)
                )
                .lineToSplineHeading(lLoad3,
                        SampleMecanumDrive.getVelocityConstraint(maxVel, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(maxAcc)
                )
                .addSpatialMarker(new Vector2d(lLoad3.plus(new Pose2d(2.5)).getX(), lLoad3.getY()), () -> {
                    robot.claw.close();
                })
                .build();
        score3 = robot.drive.trajectoryBuilder(load3.end())
                .lineToSplineHeading(lScore3,
                        SampleMecanumDrive.getVelocityConstraint(maxVel, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(maxAcc)
                )
                .splineToSplineHeading(sScore3, Math.toRadians(45),
                        SampleMecanumDrive.getVelocityConstraint(maxVel, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(maxAcc)
                )
                .build();

        load4 = robot.drive.trajectoryBuilder(score3.end(), true)
                .splineToSplineHeading(sLoad4, Math.toRadians(180),
                        SampleMecanumDrive.getVelocityConstraint(maxVel, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(maxAcc)
                )
                .lineToSplineHeading(lLoad4,
                        SampleMecanumDrive.getVelocityConstraint(maxVel, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(maxAcc)
                )
                .addSpatialMarker(new Vector2d(lLoad4.plus(new Pose2d(2.5)).getX(), lLoad4.getY()), () -> {
                    robot.claw.close();
                })
                .build();
        score4 = robot.drive.trajectoryBuilder(load4.end())
                .lineToSplineHeading(lScore4,
                        SampleMecanumDrive.getVelocityConstraint(maxVel, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(maxAcc)
                )
                .splineToSplineHeading(sScore4, Math.toRadians(45),
                        SampleMecanumDrive.getVelocityConstraint(maxVel, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(maxAcc)
                )
                .build();

        load5 = robot.drive.trajectoryBuilder(score4.end(), true)
                .splineToSplineHeading(sLoad5, Math.toRadians(180),
                        SampleMecanumDrive.getVelocityConstraint(maxVel, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(maxAcc)
                )
                .lineToSplineHeading(lLoad5,
                        SampleMecanumDrive.getVelocityConstraint(maxVel, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(maxAcc)
                )
                .addSpatialMarker(new Vector2d(lLoad5.plus(new Pose2d(2.5)).getX(), lLoad5.getY()), () -> {
                    robot.claw.close();
                })
                .build();
        score5 = robot.drive.trajectoryBuilder(load5.end())
                .lineToSplineHeading(lScore5,
                        SampleMecanumDrive.getVelocityConstraint(maxVel, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(maxAcc)
                )
                .splineToSplineHeading(sScore5, Math.toRadians(45),
                        SampleMecanumDrive.getVelocityConstraint(maxVel, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(maxAcc)
                )
                .build();

        // parks
        park1 = robot.drive.trajectoryBuilder(score5.end(), true)
                .lineToSplineHeading(lPark1,
                        SampleMecanumDrive.getVelocityConstraint(maxVel, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(maxAcc)
                )
                .build();

        park2 = robot.drive.trajectoryBuilder(score5.end(), true)
                .splineToSplineHeading(sPark2, Math.toRadians(180),
                        SampleMecanumDrive.getVelocityConstraint(maxVel, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(maxAcc)
                )
                .lineToSplineHeading(lPark2,
                        SampleMecanumDrive.getVelocityConstraint(maxVel, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(maxAcc)
                )
                .build();

        park3 = robot.drive.trajectoryBuilder(score5.end(), true)
                .splineToSplineHeading(sPark3, Math.toRadians(180),
                        SampleMecanumDrive.getVelocityConstraint(maxVel, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(maxAcc)
                )
                .lineToSplineHeading(lPark3,
                        SampleMecanumDrive.getVelocityConstraint(maxVel, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(maxAcc)
                )
                .build();

        // set pose estimate
        robot.drive.setPoseEstimate(start1);
    }

    @Override
    public void initializeSteps(int location) {
//        followTrajectory(start);
        followAndExtend(start, Slides.Position.HIGH, Arm.Position.SCORE);
//        followAndExtend(startScore, Slides.Position.HIGH);

        followAndReset(load1, heights[4]);
        followAndExtend(score1, Slides.Position.HIGH, Arm.Position.SCORE);

        followAndReset(load2, heights[3]);
        followAndExtend(score2, Slides.Position.HIGH, Arm.Position.SCORE);

        followAndReset(load3, heights[2]);
        followAndExtend(score3, Slides.Position.HIGH, Arm.Position.SCORE);

        followAndReset(load4, heights[1]);
        followAndExtend(score4, Slides.Position.HIGH, Arm.Position.SCORE);

        followAndReset(load5, heights[0]);
        followAndExtend(score5, Slides.Position.HIGH, Arm.Position.SCORE);

        switch (location) {
            case 1:
                followAndResetEnd(park1, 0);
                break;
            case 2:
                followAndResetEnd(park2, 0);
                break;
            case 3:
                followAndResetEnd(park3, 0);
                break;
        }
    }
}
