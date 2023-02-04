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

    // trajectories
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
    public Trajectory park12;
    public Trajectory park2;
    public Trajectory park22;
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

        // positions
        Pose2d start = new Pose2d(-31.25,63,Math.toRadians(90));
        Pose2d start2 = new Pose2d(-42,10,Math.toRadians(90));
        Pose2d score = new Pose2d(-28,19.0,Math.toRadians(25));

        Pose2d sLoad1 = new Pose2d(-50,12.0,Math.toRadians(0));
        Pose2d sLoad2 = new Pose2d(-49,10.5,Math.toRadians(0));
        Pose2d sLoad3 = new Pose2d(-48,9.0,Math.toRadians(0));
        Pose2d sLoad4 = new Pose2d(-47,7.5,Math.toRadians(0));
        Pose2d sLoad5 = new Pose2d(-46,6.0,Math.toRadians(0));
        Pose2d lLoad1 = new Pose2d(-56.0,12.0,Math.toRadians(0));
        Pose2d lLoad2 = new Pose2d(-55.5,10.5,Math.toRadians(0));
        Pose2d lLoad3 = new Pose2d(-55.0,9.0,Math.toRadians(0));
        Pose2d lLoad4 = new Pose2d(-54.5,7.5,Math.toRadians(0));
        Pose2d lLoad5 = new Pose2d(-54.0,6.0,Math.toRadians(0));

        Pose2d lScore1 = new Pose2d(-50,12.0,Math.toRadians(0));
        Pose2d lScore2 = new Pose2d(-49,10.5,Math.toRadians(0));
        Pose2d lScore3 = new Pose2d(-48,9.0,Math.toRadians(0));
        Pose2d lScore4 = new Pose2d(-47,7.5,Math.toRadians(0));
        Pose2d lScore5 = new Pose2d(-46,6.0,Math.toRadians(0));
        Pose2d sScore1 = new Pose2d(-27.0,18.5,Math.toRadians(45));
        Pose2d sScore2 = new Pose2d(-25.5,18.0,Math.toRadians(45));
        Pose2d sScore3 = new Pose2d(-24.0,17.5,Math.toRadians(45));
        Pose2d sScore4 = new Pose2d(-22.5,17.0,Math.toRadians(45));
        Pose2d sScore5 = new Pose2d(-21.0,16.5,Math.toRadians(45));

        Pose2d lPark1 = new Pose2d(-4,11, Math.toRadians(90));

        Pose2d lPark2 = new Pose2d(-34,12, Math.toRadians(90));

        Pose2d sPark3 = new Pose2d(-46, 6, Math.toRadians(0));
        Pose2d lPark3 = new Pose2d(-54,6,Math.toRadians(0));

        // preload
        startScore1 = robot.drive.trajectoryBuilder(start)
                .lineToSplineHeading(start2)
                .build();
        startScore2 = robot.drive.trajectoryBuilder(startScore1.end())
                .lineToSplineHeading(score)
                .build();

        // cone stacks
        load1 = robot.drive.trajectoryBuilder(startScore2.end(), true)
                .splineToSplineHeading(sLoad1, Math.toRadians(180))
                .lineToSplineHeading(lLoad1)
                .build();
        score1 = robot.drive.trajectoryBuilder(load1.end())
                .lineToSplineHeading(lScore1)
                .splineToSplineHeading(sScore1, Math.toRadians(45))
                .build();

        load2 = robot.drive.trajectoryBuilder(score1.end(), true)
                .splineToSplineHeading(sLoad2, Math.toRadians(180))
                .lineToSplineHeading(lLoad2)
                .build();
        score2 = robot.drive.trajectoryBuilder(load2.end())
                .lineToSplineHeading(lScore2)
                .splineToSplineHeading(sScore2, Math.toRadians(45))
                .build();

        load3 = robot.drive.trajectoryBuilder(score2.end(), true)
                .splineToSplineHeading(sLoad3, Math.toRadians(180))
                .lineToSplineHeading(lLoad3)
                .build();
        score3 = robot.drive.trajectoryBuilder(load3.end())
                .lineToSplineHeading(lScore3)
                .splineToSplineHeading(sScore3, Math.toRadians(45))
                .build();

        load4 = robot.drive.trajectoryBuilder(score3.end(), true)
                .splineToSplineHeading(sLoad4, Math.toRadians(180))
                .lineToSplineHeading(lLoad4)
                .build();
        score4 = robot.drive.trajectoryBuilder(load4.end())
                .lineToSplineHeading(lScore4)
                .splineToSplineHeading(sScore4, Math.toRadians(45))
                .build();

        load5 = robot.drive.trajectoryBuilder(score4.end(), true)
                .splineToSplineHeading(sLoad5, Math.toRadians(180))
                .lineToSplineHeading(lLoad5)
                .build();
        score5 = robot.drive.trajectoryBuilder(load5.end())
                .lineToSplineHeading(lScore5)
                .splineToSplineHeading(sScore5, Math.toRadians(45))
                .build();

        // parks
        park1 = robot.drive.trajectoryBuilder(score5.end(), true)
                .back(15)
                .build();
        park12 = robot.drive.trajectoryBuilder(park1.end(), true)
                .lineToSplineHeading(lPark1)
                .build();

        park2 = robot.drive.trajectoryBuilder(score5.end(), true)
                .back(10)
                .build();
        park22 = robot.drive.trajectoryBuilder(park2.end(), true)
                .lineToSplineHeading(lPark2)
                .build();

        park3 = robot.drive.trajectoryBuilder(score5.end(), true)
                .splineToSplineHeading(sPark3, Math.toRadians(180))
                .lineToSplineHeading(lPark3)
                .build();

        // set pose estimate
        robot.drive.setPoseEstimate(start);
    }

    @Override
    public void initializeSteps(int location) {
        followTrajectory(startScore1);
        followAndExtend(startScore2, Slides.Position.MEDIUM);

        followAndReset(load1, heights[4]);
        followAndExtend(score1, Slides.Position.MEDIUM);

        followAndReset(load2, heights[3]);
        followAndExtend(score2, Slides.Position.MEDIUM);

        followAndReset(load3, heights[2]);
        followAndExtend(score3, Slides.Position.MEDIUM);

        followAndReset(load4, heights[1]);
        followAndExtend(score4, Slides.Position.MEDIUM);

        followAndReset(load5, heights[0]);
        followAndExtend(score5, Slides.Position.MEDIUM);

        switch (location) {
            case 1:
                followTrajectory(park1);
                followAndReset(park12, 0);
                break;
            case 2:
                followTrajectory(park2);
                followAndReset(park22, 0);
                break;
            case 3:
                followAndReset(park3, 0);
                break;
        }
    }
}
