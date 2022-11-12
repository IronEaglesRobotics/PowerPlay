package org.firstinspires.ftc.teamcode.opmode.autonomous;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.hardware.Slides;
import org.firstinspires.ftc.teamcode.util.CameraPosition;

@Config
@Autonomous(name = "Right Carry Auto", group = "Competition", preselectTeleOp = "TeleOp")
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
        cameraPosition = CameraPosition.LEFT;
    }

    @Override
    public boolean useCamera() {
        return true;
    }


    @Override
    public void makeTrajectories() {

        int[] heights = {0, (int)(145.1*(32/112.0)), (int)(145.1*(64/112.0)), (int)(145.1*(96/112.0)), (int)(145.1*(128/112.0))};

        Pose2d start = new Pose2d(-36,66, Math.toRadians(180));
        Pose2d start2 = new Pose2d(-36,24, Math.toRadians(180));
        Pose2d score = new Pose2d(-32,8,Math.toRadians(135));

        Pose2d sLoad1 = new Pose2d(-48, 12+0.0, Math.toRadians(180));
        Pose2d sLoad2 = new Pose2d(-48, 12+0.5, Math.toRadians(180));
        Pose2d sLoad3 = new Pose2d(-48, 12+1.0, Math.toRadians(180));
        Pose2d sLoad4 = new Pose2d(-48, 12+1.5, Math.toRadians(180));
        Pose2d sLoad5 = new Pose2d(-48, 12+2.0, Math.toRadians(180));
        Pose2d lLoad1 = new Pose2d(-62,12+0.0,Math.toRadians(180));
        Pose2d lLoad2 = new Pose2d(-62,12+0.5,Math.toRadians(180));
        Pose2d lLoad3 = new Pose2d(-62,12+1.0,Math.toRadians(180));
        Pose2d lLoad4 = new Pose2d(-62,12+1.5,Math.toRadians(180));
        Pose2d lLoad5 = new Pose2d(-62,12+2.0,Math.toRadians(180));

        Pose2d lScore1 = new Pose2d(-48,12+0.0,Math.toRadians(180));
        Pose2d lScore2 = new Pose2d(-48,12+0.5,Math.toRadians(180));
        Pose2d lScore3 = new Pose2d(-48,12+1.0,Math.toRadians(180));
        Pose2d lScore4 = new Pose2d(-48,12+1.5,Math.toRadians(180));
        Pose2d lScore5 = new Pose2d(-48,12+2.0,Math.toRadians(180));
        Pose2d sScore1 = new Pose2d(-32,8,Math.toRadians(135));
        Pose2d sScore2 = new Pose2d(-32,8,Math.toRadians(135));
        Pose2d sScore3 = new Pose2d(-32,8,Math.toRadians(135));
        Pose2d sScore4 = new Pose2d(-32,8,Math.toRadians(135));
        Pose2d sScore5 = new Pose2d(-32,8,Math.toRadians(135));

//        Pose2d sPark2 = new Pose2d(-36,24, Math.toRadians(90));
        Pose2d lPark1 = new Pose2d(-12,12, Math.toRadians(90));

        Pose2d sPark2 = new Pose2d(-36,24, Math.toRadians(90));
        Pose2d lPark2 = new Pose2d(-36,36, Math.toRadians(90));

        Pose2d sPark3 = new Pose2d(-48, 12+2.0, Math.toRadians(180));
        Pose2d lPark3 = new Pose2d(-62,12+2.0,Math.toRadians(180));

        startScore = robot.drive.trajectoryBuilder(start)
                .lineToSplineHeading(start2)
                .splineToSplineHeading(score, Math.toRadians(-45))
                .addTemporalMarker(0, () -> robot.claw.strongClose())
                .addTemporalMarker(0.1, () -> robot.slides.setTarget(Slides.Position.HIGH))
                .addTemporalMarker(1.5, () -> robot.hSlides.goToScore())
                .build();

        load1 = robot.drive.trajectoryBuilder(startScore.end())
                .splineToSplineHeading(sLoad1, Math.toRadians(180))
                .lineToSplineHeading(lLoad1)
                .addTemporalMarker(0.2, () -> robot.hSlides.goToIntake())
                .addTemporalMarker(0.7, () -> robot.slides.setTarget(heights[4]))
                .addTemporalMarker(1.4, () -> robot.claw.open())
                .addDisplacementMarker(() -> robot.claw.strongClose())
                .build();
        score1 = robot.drive.trajectoryBuilder(load1.end())
                .lineToSplineHeading(lScore1)
                .splineToSplineHeading(sScore1, Math.toRadians(-45))
                .addTemporalMarker(0.1, () -> robot.slides.setTarget(Slides.Position.HIGH))
                .addTemporalMarker(1.5, () -> robot.hSlides.goToScore())
                .build();

        load2 = robot.drive.trajectoryBuilder(score1.end())
                .splineToSplineHeading(sLoad2, Math.toRadians(180))
                .lineToSplineHeading(lLoad2)
                .addTemporalMarker(0.2, () -> robot.hSlides.goToIntake())
                .addTemporalMarker(0.7, () -> robot.slides.setTarget(heights[3]))
                .addTemporalMarker(1.4, () -> robot.claw.open())
                .addDisplacementMarker(() -> robot.claw.strongClose())
                .build();
        score2 = robot.drive.trajectoryBuilder(load2.end())
                .lineToSplineHeading(lScore2)
                .splineToSplineHeading(sScore2, Math.toRadians(-45))
                .addTemporalMarker(0.1, () -> robot.slides.setTarget(Slides.Position.HIGH))
                .addTemporalMarker(1.5, () -> robot.hSlides.goToScore())
                .build();

        load3 = robot.drive.trajectoryBuilder(score2.end())
                .splineToSplineHeading(sLoad3, Math.toRadians(180))
                .lineToSplineHeading(lLoad3)
                .addTemporalMarker(0.2, () -> robot.hSlides.goToIntake())
                .addTemporalMarker(0.7, () -> robot.slides.setTarget(heights[2]))
                .addTemporalMarker(1.4, () -> robot.claw.open())
                .addDisplacementMarker(() -> robot.claw.strongClose())
                .build();
        score3 = robot.drive.trajectoryBuilder(load3.end())
                .lineToSplineHeading(lScore3)
                .splineToSplineHeading(sScore3, Math.toRadians(-45))
                .addTemporalMarker(0.1, () -> robot.slides.setTarget(Slides.Position.HIGH))
                .addTemporalMarker(1.5, () -> robot.hSlides.goToScore())
                .build();

        load4 = robot.drive.trajectoryBuilder(score3.end())
                .splineToSplineHeading(sLoad4, Math.toRadians(180))
                .lineToSplineHeading(lLoad4)
                .addTemporalMarker(0.2, () -> robot.hSlides.goToIntake())
                .addTemporalMarker(0.7, () -> robot.slides.setTarget(heights[1]))
                .addTemporalMarker(1.4, () -> robot.claw.open())
                .addDisplacementMarker(() -> robot.claw.strongClose())
                .build();
        score4 = robot.drive.trajectoryBuilder(load4.end())
                .lineToSplineHeading(lScore4)
                .splineToSplineHeading(sScore4, Math.toRadians(-45))
                .addTemporalMarker(0.1, () -> robot.slides.setTarget(Slides.Position.HIGH))
                .addTemporalMarker(1.5, () -> robot.hSlides.goToScore())
                .build();

        load5 = robot.drive.trajectoryBuilder(score4.end())
                .splineToSplineHeading(sLoad5, Math.toRadians(180))
                .lineToSplineHeading(lLoad5)
                .addTemporalMarker(0.2, () -> robot.hSlides.goToIntake())
                .addTemporalMarker(0.7, () -> robot.slides.setTarget(heights[0]))
                .addTemporalMarker(1.4, () -> robot.claw.open())
                .addDisplacementMarker(() -> robot.claw.strongClose())
                .build();
        score5 = robot.drive.trajectoryBuilder(load5.end())
                .lineToSplineHeading(lScore5)
                .splineToSplineHeading(sScore5, Math.toRadians(-45))
                .addTemporalMarker(0.1, () -> robot.slides.setTarget(Slides.Position.HIGH))
                .addTemporalMarker(1.5, () -> robot.hSlides.goToScore())
                .build();


        park1 = robot.drive.trajectoryBuilder(score5.end())
                .lineToSplineHeading(lPark1)
                .addTemporalMarker(0.2, () -> robot.hSlides.goToIntake())
                .addTemporalMarker(0.7, () -> robot.slides.setTarget(0))
                .addTemporalMarker(1.4, () -> robot.claw.open())
                .build();

        park2 = robot.drive.trajectoryBuilder(score5.end())
                .splineToSplineHeading(sPark2, Math.toRadians(90))
                .lineToSplineHeading(lPark2)
                .addTemporalMarker(0.2, () -> robot.hSlides.goToIntake())
                .addTemporalMarker(0.7, () -> robot.slides.setTarget(0))
                .addTemporalMarker(1.4, () -> robot.claw.open())
                .build();

        park3 = robot.drive.trajectoryBuilder(score5.end())
                .splineToSplineHeading(sPark3, Math.toRadians(180))
                .lineToSplineHeading(lPark3)
                .addTemporalMarker(0.2, () -> robot.hSlides.goToIntake())
                .addTemporalMarker(0.7, () -> robot.slides.setTarget(0))
                .addTemporalMarker(1.4, () -> robot.claw.open())
                .build();

        robot.drive.setPoseEstimate(start);
    }

    @Override
    public void initializeSteps(int location) {
        followTrajectory(startScore);

        addOpenClaw(0.2);
        addCloseClaw(0);

        followTrajectory(load1);
        followTrajectory(score1);

        addOpenClaw(0.2);
        addCloseClaw(0);

        followTrajectory(load2);
        followTrajectory(score2);

        addOpenClaw(0.2);
        addCloseClaw(0);

        followTrajectory(load3);
        followTrajectory(score3);

        addOpenClaw(0.2);
        addCloseClaw(0);

        followTrajectory(load4);
        followTrajectory(score4);

        addOpenClaw(0.2);
        addCloseClaw(0);

        followTrajectory(load5);
        followTrajectory(score5);

        addOpenClaw(0.2);
        addCloseClaw(0);

        switch (location) {
            case 1:
                followTrajectory(park1);
                break;
            case 2:
                followTrajectory(park2);
                break;
            case 3:
                followTrajectory(park3);
                break;
        }
    }
}
