package org.firstinspires.ftc.teamcode.opmode.autonomous;

import static org.firstinspires.ftc.teamcode.hardware.Slides.heights;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.hardware.Slides;
import org.firstinspires.ftc.teamcode.roadrunner.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.util.CameraPosition;

@Config
@Autonomous(name = "Right Carry Auto", group = "Competition", preselectTeleOp = "Blue TeleOp")
public class RightCarryAuto extends AbstractAuto {

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

        Pose2d start = new Pose2d(-36,65.5, Math.toRadians(180));
        Pose2d start2 = new Pose2d(-36,24, Math.toRadians(180));
        Pose2d score = new Pose2d(-31,6,Math.toRadians(135));

        Pose2d sLoad1 = new Pose2d(-48, 12+0.0, Math.toRadians(180));
        Pose2d sLoad2 = new Pose2d(-48, 12+0.5, Math.toRadians(180));
        Pose2d sLoad3 = new Pose2d(-48, 12+1.0, Math.toRadians(180));
        Pose2d sLoad4 = new Pose2d(-48, 12+1.5, Math.toRadians(180));
        Pose2d sLoad5 = new Pose2d(-48, 12+2.0, Math.toRadians(180));
        Pose2d lLoad1 = new Pose2d(-63,12+0.0,Math.toRadians(180));
        Pose2d lLoad2 = new Pose2d(-63,12+0.5,Math.toRadians(180));
        Pose2d lLoad3 = new Pose2d(-64,12+1.0,Math.toRadians(180));
        Pose2d lLoad4 = new Pose2d(-64,12+1.5,Math.toRadians(180));
        Pose2d lLoad5 = new Pose2d(-65,12+2.0,Math.toRadians(180));

        Pose2d lScore1 = new Pose2d(-48,11.5+0.0,Math.toRadians(180));
        Pose2d lScore2 = new Pose2d(-48,11.5+0.5,Math.toRadians(180));
        Pose2d lScore3 = new Pose2d(-48,11.5+1.0,Math.toRadians(180));
        Pose2d lScore4 = new Pose2d(-48,11.5+1.5,Math.toRadians(180));
        Pose2d lScore5 = new Pose2d(-48,11.5+2.0,Math.toRadians(180));
        Pose2d sScore1 = new Pose2d(-30.5,7,Math.toRadians(135));
        Pose2d sScore2 = new Pose2d(-30.5,7.5,Math.toRadians(135));
        Pose2d sScore3 = new Pose2d(-31.5,7.5,Math.toRadians(135));
        Pose2d sScore4 = new Pose2d(-33,8,Math.toRadians(135));
        Pose2d sScore5 = new Pose2d(-33,8,Math.toRadians(135));

//        Pose2d sPark2 = new Pose2d(-36,24, Math.toRadians(90));
        Pose2d lPark1 = new Pose2d(-12,12, Math.toRadians(90));

        Pose2d sPark2 = new Pose2d(-36,24, Math.toRadians(90));
        Pose2d lPark2 = new Pose2d(-36,36, Math.toRadians(90));

        Pose2d sPark3 = new Pose2d(-48, 12+2.0, Math.toRadians(180));
        Pose2d lPark3 = new Pose2d(-62,12+2.0,Math.toRadians(180));

        startScore = robot.drive.trajectoryBuilder(start)
                .lineToSplineHeading(start2,
                        SampleMecanumDrive.getVelocityConstraint(40, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL)
                )
                .splineToSplineHeading(score, Math.toRadians(-45))
//                .addTemporalMarker(0, () -> robot.claw.strongClose())
//                .addTemporalMarker(0.1, () -> robot.slides.setTarget(Slides.Position.HIGH))
//                .addTemporalMarker(1.5, () -> robot.hSlides.goToScore())
                .build();

        load1 = robot.drive.trajectoryBuilder(startScore.end())
                .splineToSplineHeading(sLoad1, Math.toRadians(180))
                .lineToSplineHeading(lLoad1)
//                .addTemporalMarker(0.2, () -> robot.hSlides.goToIntake())
//                .addTemporalMarker(0.7, () -> robot.slides.setTarget(heights[4]))
//                .addTemporalMarker(1.4, () -> robot.claw.open())
//                .addDisplacementMarker(() -> robot.claw.strongClose())
                .build();
        score1 = robot.drive.trajectoryBuilder(load1.end())
                .lineToSplineHeading(lScore1)
                .splineToSplineHeading(sScore1, Math.toRadians(-45))
//                .addTemporalMarker(0.1, () -> robot.slides.setTarget(Slides.Position.HIGH))
//                .addTemporalMarker(1.5, () -> robot.hSlides.goToScore())
                .build();

        load2 = robot.drive.trajectoryBuilder(score1.end())
                .splineToSplineHeading(sLoad2, Math.toRadians(180))
                .lineToSplineHeading(lLoad2)
//                .addTemporalMarker(0.2, () -> robot.hSlides.goToIntake())
//                .addTemporalMarker(0.7, () -> robot.slides.setTarget(heights[3]))
//                .addTemporalMarker(1.4, () -> robot.claw.open())
//                .addDisplacementMarker(() -> robot.claw.strongClose())
                .build();
        score2 = robot.drive.trajectoryBuilder(load2.end())
                .lineToSplineHeading(lScore2)
                .splineToSplineHeading(sScore2, Math.toRadians(-45))
//                .addTemporalMarker(0.1, () -> robot.slides.setTarget(Slides.Position.HIGH))
//                .addTemporalMarker(1.5, () -> robot.hSlides.goToScore())
                .build();

        load3 = robot.drive.trajectoryBuilder(score2.end())
                .splineToSplineHeading(sLoad3, Math.toRadians(180))
                .lineToSplineHeading(lLoad3)
//                .addTemporalMarker(0.2, () -> robot.hSlides.goToIntake())
//                .addTemporalMarker(0.7, () -> robot.slides.setTarget(heights[2]))
//                .addTemporalMarker(1.4, () -> robot.claw.open())
//                .addDisplacementMarker(() -> robot.claw.strongClose())
                .build();
        score3 = robot.drive.trajectoryBuilder(load3.end())
                .lineToSplineHeading(lScore3)
                .splineToSplineHeading(sScore3, Math.toRadians(-45))
//                .addTemporalMarker(0.1, () -> robot.slides.setTarget(Slides.Position.HIGH))
//                .addTemporalMarker(1.5, () -> robot.hSlides.goToScore())
                .build();

        load4 = robot.drive.trajectoryBuilder(score3.end())
                .splineToSplineHeading(sLoad4, Math.toRadians(180))
                .lineToSplineHeading(lLoad4)
//                .addTemporalMarker(0.2, () -> robot.hSlides.goToIntake())
//                .addTemporalMarker(0.7, () -> robot.slides.setTarget(heights[1]))
//                .addTemporalMarker(1.4, () -> robot.claw.open())
//                .addDisplacementMarker(() -> robot.claw.strongClose())
                .build();
        score4 = robot.drive.trajectoryBuilder(load4.end())
                .lineToSplineHeading(lScore4)
                .splineToSplineHeading(sScore4, Math.toRadians(-45))
//                .addTemporalMarker(0.1, () -> robot.slides.setTarget(Slides.Position.HIGH))
//                .addTemporalMarker(1.5, () -> robot.hSlides.goToScore())
                .build();

        load5 = robot.drive.trajectoryBuilder(score4.end())
                .splineToSplineHeading(sLoad5, Math.toRadians(180))
                .lineToSplineHeading(lLoad5)
//                .addTemporalMarker(0.2, () -> robot.hSlides.goToIntake())
//                .addTemporalMarker(0.7, () -> robot.slides.setTarget(heights[0]))
//                .addTemporalMarker(1.4, () -> robot.claw.open())
//                .addDisplacementMarker(() -> robot.claw.strongClose())
                .build();
        score5 = robot.drive.trajectoryBuilder(load5.end())
                .lineToSplineHeading(lScore5)
                .splineToSplineHeading(sScore5, Math.toRadians(-45))
//                .addTemporalMarker(0.1, () -> robot.slides.setTarget(Slides.Position.HIGH))
//                .addTemporalMarker(1.5, () -> robot.hSlides.goToScore())
                .build();


        park1 = robot.drive.trajectoryBuilder(score5.end())
                .lineToSplineHeading(lPark1)
//                .addTemporalMarker(0.2, () -> robot.hSlides.goToIntake())
//                .addTemporalMarker(0.7, () -> robot.slides.setTarget(0))
//                .addTemporalMarker(1.4, () -> robot.claw.open())
                .build();

        park2 = robot.drive.trajectoryBuilder(score5.end())
                .splineToSplineHeading(sPark2, Math.toRadians(90))
                .lineToSplineHeading(lPark2)
//                .addTemporalMarker(0.2, () -> robot.hSlides.goToIntake())
//                .addTemporalMarker(0.7, () -> robot.slides.setTarget(0))
//                .addTemporalMarker(1.4, () -> robot.claw.open())
                .build();

        park3 = robot.drive.trajectoryBuilder(score5.end())
                .splineToSplineHeading(sPark3, Math.toRadians(180))
                .lineToSplineHeading(lPark3)
//                .addTemporalMarker(0.2, () -> robot.hSlides.goToIntake())
//                .addTemporalMarker(0.7, () -> robot.slides.setTarget(0))
//                .addTemporalMarker(1.4, () -> robot.claw.open())
                .build();

        robot.drive.setPoseEstimate(start);
    }

    @Override
    public void initializeSteps(int location) {
        followAndExtend(startScore, Slides.Position.HIGH);

        followAndReset(load1, heights[4]);
        followAndExtend(score1, Slides.Position.HIGH);

        followAndReset(load2, heights[3]);
        followAndExtend(score2, Slides.Position.HIGH);

        followAndReset(load3, heights[2]);
        followAndExtend(score3, Slides.Position.HIGH);

        followAndReset(load4, heights[1]);
        followAndExtend(score4, Slides.Position.HIGH);

//        followAndReset(load5, heights[0]);
//        followAndExtend(score5, Slides.Position.HIGH);

        switch (location) {
            case 1:
                followAndReset(park1, 0);
                break;
            case 2:
                followAndReset(park2, 0);
                break;
            case 3:
                followAndReset(park3, 0);
                break;
        }
    }
}
