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

@Autonomous(name = "Right High 1 + 5", group = "Right Competition", preselectTeleOp = "Main TeleOp")
public class RightHighAuto extends AbstractAuto {

    public Trajectory startScore1;
    public Trajectory startScore2;
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

        Pose2d start = new Pose2d(-32,63.8,Math.toRadians(90));
        Pose2d start2 = new Pose2d(-36,12,Math.toRadians(90));
        Pose2d score = new Pose2d(-29,23.9,Math.toRadians(0));

        Pose2d sLoad1 = new Pose2d(-18,11,Math.toRadians(0));
        Pose2d sLoad2 = new Pose2d(-18,11,Math.toRadians(0));
        Pose2d sLoad3 = new Pose2d(-18,11.5,Math.toRadians(0));
        Pose2d sLoad4 = new Pose2d(-18,12,Math.toRadians(0));
        Pose2d sLoad5 = new Pose2d(-18,12.5,Math.toRadians(0));
        Pose2d lLoad1 = new Pose2d(-57,11,Math.toRadians(0));
        Pose2d lLoad2 = new Pose2d(-57,11,Math.toRadians(0));
        Pose2d lLoad3 = new Pose2d(-57,11.5,Math.toRadians(0));
        Pose2d lLoad4 = new Pose2d(-57,12,Math.toRadians(0));
        Pose2d lLoad5 = new Pose2d(-57,12.5,Math.toRadians(0));

        Pose2d lScore1 = new Pose2d(-18,11.0,Math.toRadians(0));
        Pose2d lScore2 = new Pose2d(-18,11.0,Math.toRadians(0));
        Pose2d lScore3 = new Pose2d(-18,11.5,Math.toRadians(0));
        Pose2d lScore4 = new Pose2d(-18,12.0,Math.toRadians(0));
        Pose2d lScore5 = new Pose2d(-18,12.5,Math.toRadians(0));
        Pose2d sScore1 = new Pose2d(-5.0,25.1,Math.toRadians(0));
        Pose2d sScore2 = new Pose2d(-5.0,26.0,Math.toRadians(0));
        Pose2d sScore3 = new Pose2d(-5.0,26.7,Math.toRadians(0));
        Pose2d sScore4 = new Pose2d(-4.8,27.5,Math.toRadians(0));
        Pose2d sScore5 = new Pose2d(-4.6,28.4,Math.toRadians(0));

//        Pose2d sPark2 = new Pose2d(-36,24, Math.toRadians(90));
        // 1 and 2 are swapped
        Pose2d lPark1 = new Pose2d(-4.6,31, Math.toRadians(0));
        Pose2d sPark1 = new Pose2d(-8,40, Math.toRadians(0));

        Pose2d lPark2 = new Pose2d(-6,40, Math.toRadians(0));

        Pose2d sPark3 = new Pose2d(-10, 40, Math.toRadians(0));
        Pose2d lPark3 = new Pose2d(-57,40,Math.toRadians(0));

        startScore1 = robot.drive.trajectoryBuilder(start)
                .lineToSplineHeading(start2)
                .build();
        startScore2 = robot.drive.trajectoryBuilder(startScore1.end())
                .lineToSplineHeading(score)
                .build();


        load1 = robot.drive.trajectoryBuilder(startScore2.end(), true)
                .splineToLinearHeading(sLoad1, Math.toRadians(180))
                .lineToLinearHeading(lLoad1)
                .build();
        score1 = robot.drive.trajectoryBuilder(load1.end())
                .lineToLinearHeading(lScore1)
                .splineToLinearHeading(sScore1, Math.toRadians(0))
                .build();

        load2 = robot.drive.trajectoryBuilder(score1.end(), true)
                .splineToLinearHeading(sLoad2, Math.toRadians(180))
                .lineToLinearHeading(lLoad2)
                .build();
        score2 = robot.drive.trajectoryBuilder(load2.end())
                .lineToLinearHeading(lScore2)
                .splineToLinearHeading(sScore2, Math.toRadians(0))
                .build();

        load3 = robot.drive.trajectoryBuilder(score2.end(), true)
                .splineToLinearHeading(sLoad3, Math.toRadians(180))
                .lineToLinearHeading(lLoad3)
                .build();
        score3 = robot.drive.trajectoryBuilder(load3.end())
                .lineToLinearHeading(lScore3)
                .splineToLinearHeading(sScore3, Math.toRadians(0))
                .build();

        load4 = robot.drive.trajectoryBuilder(score3.end(), true)
                .splineToLinearHeading(sLoad4, Math.toRadians(180))
                .lineToLinearHeading(lLoad4)
                .build();
        score4 = robot.drive.trajectoryBuilder(load4.end())
                .lineToLinearHeading(lScore4)
                .splineToLinearHeading(sScore4, Math.toRadians(0))
                .build();

        load5 = robot.drive.trajectoryBuilder(score4.end(), true)
                .splineToLinearHeading(sLoad5, Math.toRadians(180))
                .lineToLinearHeading(lLoad5)
                .build();
        score5 = robot.drive.trajectoryBuilder(load5.end())
                .lineToLinearHeading(lScore5)
                .splineToLinearHeading(sScore5, Math.toRadians(0))
                .build();

        park1 = robot.drive.trajectoryBuilder(score5.end(), true)
                .lineToSplineHeading(lPark1)
                .splineToSplineHeading(sPark1, Math.toRadians(0))
                .build();

        park2 = robot.drive.trajectoryBuilder(score5.end(), true)
                .lineToLinearHeading(lPark2)
                .build();

        park3 = robot.drive.trajectoryBuilder(score5.end(), true)
                .splineToLinearHeading(sPark3, Math.toRadians(180))
                .lineToLinearHeading(lPark3)
                .build();

        robot.drive.setPoseEstimate(start);
    }

    @Override
    public void initializeSteps(int location) {
        followTrajectory(startScore1);
        followAndExtend(startScore2, Slides.Position.MEDIUM);

        followAndReset(load1, heights[4]);
        followAndExtend(score1, Slides.Position.HIGH);

        followAndReset(load2, heights[3]);
        followAndExtend(score2, Slides.Position.HIGH);

        followAndReset(load3, heights[2]);
        followAndExtend(score3, Slides.Position.HIGH);

        followAndReset(load4, heights[1]);
        followAndExtend(score4, Slides.Position.HIGH);

        followAndReset(load5, heights[0]);
        followAndExtend(score5, Slides.Position.HIGH);

        switch (location) {
            case 1:
                followAndReset(park2, 0);
                break;
            case 2:
                followAndReset(park1, 0);
                break;
            case 3:
                followAndReset(park3, 0);
                break;
        }
    }
}
