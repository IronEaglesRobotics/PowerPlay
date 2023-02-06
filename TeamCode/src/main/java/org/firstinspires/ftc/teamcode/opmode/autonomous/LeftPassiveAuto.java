package org.firstinspires.ftc.teamcode.opmode.autonomous;

import static org.firstinspires.ftc.teamcode.hardware.Slides.heights;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.hardware.Slides;
import org.firstinspires.ftc.teamcode.util.CameraPosition;

@Autonomous(name = "Left Mid 1 + 5", group = "Left Competition", preselectTeleOp = "Main TeleOp")
public class LeftPassiveAuto extends AbstractAuto {

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
    public Trajectory park2;
    public Trajectory park22;
    public Trajectory park3;
    public Trajectory park32;

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
        double y1 = 12.0;
        double y2 = 11.5;
        double y3 = 11.0;
        double y4 = 10.5;
        double y5 = 10.0;

        // positions
        Pose2d start = new Pose2d(31.25,63,Math.toRadians(90));
        Pose2d start2 = new Pose2d(42,10,Math.toRadians(90));
        Pose2d score = new Pose2d(29.5,19.0,Math.toRadians(155));

        Pose2d sLoad1 = new Pose2d(50,y1,Math.toRadians(180));
        Pose2d sLoad2 = new Pose2d(49,y2,Math.toRadians(180));
        Pose2d sLoad3 = new Pose2d(48,y3,Math.toRadians(180));
        Pose2d sLoad4 = new Pose2d(47,y4,Math.toRadians(180));
        Pose2d sLoad5 = new Pose2d(46,y5,Math.toRadians(180));
        Pose2d lLoad1 = new Pose2d(56.5,y1,Math.toRadians(180));
        Pose2d lLoad2 = new Pose2d(56.0,y2,Math.toRadians(180));
        Pose2d lLoad3 = new Pose2d(55.5,y3,Math.toRadians(180));
        Pose2d lLoad4 = new Pose2d(55.0,y4,Math.toRadians(180));
        Pose2d lLoad5 = new Pose2d(54.5,y5,Math.toRadians(180));

        Pose2d lScore1 = new Pose2d(50,y1,Math.toRadians(180));
        Pose2d lScore2 = new Pose2d(49,y2,Math.toRadians(180));
        Pose2d lScore3 = new Pose2d(48,y3,Math.toRadians(180));
        Pose2d lScore4 = new Pose2d(47,y4,Math.toRadians(180));
        Pose2d lScore5 = new Pose2d(46,y5,Math.toRadians(180));
        Pose2d sScore1 = new Pose2d(29.0,19.0,Math.toRadians(135));
        Pose2d sScore2 = new Pose2d(27.5,19.0,Math.toRadians(135));
        Pose2d sScore3 = new Pose2d(26.0,19.0,Math.toRadians(135));
        Pose2d sScore4 = new Pose2d(25.0,19.0,Math.toRadians(135));
        Pose2d sScore5 = new Pose2d(24.0,19.0,Math.toRadians(135));

        Pose2d sPark1 = new Pose2d(46, 10, Math.toRadians(180));
        Pose2d lPark1 = new Pose2d(55.5,10,Math.toRadians(180));

        Pose2d lPark2 = new Pose2d(34,20, Math.toRadians(90));

        Pose2d lPark3 = new Pose2d(6,11, Math.toRadians(90));


        // preload
        startScore1 = robot.drive.trajectoryBuilder(start)
                .lineToSplineHeading(start2)
                .build();
        startScore2 = robot.drive.trajectoryBuilder(startScore1.end())
                .lineToSplineHeading(score)
                .build();

        // cone stacks
        load1 = robot.drive.trajectoryBuilder(startScore2.end(), true)
                .splineToSplineHeading(sLoad1, Math.toRadians(0))
                .lineToSplineHeading(lLoad1)
                .build();
        score1 = robot.drive.trajectoryBuilder(load1.end())
                .lineToSplineHeading(lScore1)
                .splineToSplineHeading(sScore1, Math.toRadians(135))
                .build();

        load2 = robot.drive.trajectoryBuilder(score1.end(), true)
                .splineToSplineHeading(sLoad2, Math.toRadians(0))
                .lineToSplineHeading(lLoad2)
                .build();
        score2 = robot.drive.trajectoryBuilder(load2.end())
                .lineToSplineHeading(lScore2)
                .splineToSplineHeading(sScore2, Math.toRadians(135))
                .build();

        load3 = robot.drive.trajectoryBuilder(score2.end(), true)
                .splineToSplineHeading(sLoad3, Math.toRadians(0))
                .lineToSplineHeading(lLoad3)
                .build();
        score3 = robot.drive.trajectoryBuilder(load3.end())
                .lineToSplineHeading(lScore3)
                .splineToSplineHeading(sScore3, Math.toRadians(135))
                .build();

        load4 = robot.drive.trajectoryBuilder(score3.end(), true)
                .splineToSplineHeading(sLoad4, Math.toRadians(0))
                .lineToSplineHeading(lLoad4)
                .build();
        score4 = robot.drive.trajectoryBuilder(load4.end())
                .lineToSplineHeading(lScore4)
                .splineToSplineHeading(sScore4, Math.toRadians(135))
                .build();

        load5 = robot.drive.trajectoryBuilder(score4.end(), true)
                .splineToSplineHeading(sLoad5, Math.toRadians(0))
                .lineToSplineHeading(lLoad5)
                .build();
        score5 = robot.drive.trajectoryBuilder(load5.end())
                .lineToSplineHeading(lScore5)
                .splineToSplineHeading(sScore5, Math.toRadians(135))
                .build();

        // parks
        park1 = robot.drive.trajectoryBuilder(score5.end(), true)
                .splineToSplineHeading(sPark1, Math.toRadians(0))
                .lineToSplineHeading(lPark1)
                .build();

        park2 = robot.drive.trajectoryBuilder(score5.end(), true)
                .back(15)
                .build();
        park22 = robot.drive.trajectoryBuilder(park2.end(), true)
                .lineToSplineHeading(lPark2)
                .build();

        park3 = robot.drive.trajectoryBuilder(score5.end(), true)
                .back(14)
                .build();
        park32 = robot.drive.trajectoryBuilder(park3.end(), true)
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
                followAndReset(park1, 0);
                break;
            case 2:
                followAndOpen(park2);
                followAndReset(park22, 0);
                break;
            case 3:
                followAndOpen(park3);
                followAndReset(park32, 0);
                break;
        }
    }
}
