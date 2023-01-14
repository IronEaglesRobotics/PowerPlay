package org.firstinspires.ftc.teamcode.opmode.autonomous;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.hardware.Slides;
import org.firstinspires.ftc.teamcode.util.CameraPosition;

@Config
@Autonomous(name = "Left Park Auto", group = "Competition", preselectTeleOp = "Blue TeleOp")
public class LeftParkAuto extends AbstractAuto {

    public Trajectory parkOne1;
    public Trajectory parkOne2;
    public Trajectory parkTwo1;
    public Trajectory parkThree1;
    public Trajectory parkThree2;

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
        Pose2d start = new Pose2d(-36,66, Math.toRadians(180));

        Pose2d one1 = new Pose2d(-36,36, Math.toRadians(180));
        Pose2d one2 = new Pose2d(-12,36, Math.toRadians(180));

        Pose2d two1 = new Pose2d(-36,36, Math.toRadians(180));

        Pose2d three1 = new Pose2d(-36,36, Math.toRadians(180));
        Pose2d three2 = new Pose2d(-60,36, Math.toRadians(0));

        parkOne1 = robot.drive.trajectoryBuilder(start)
                .lineToSplineHeading(one1)
                .build();
        parkOne2 = robot.drive.trajectoryBuilder(parkOne1.end())
                .lineToSplineHeading(one2)
                .build();

        parkTwo1 = robot.drive.trajectoryBuilder(start)
                .lineToSplineHeading(two1)
                .build();

        parkThree1 = robot.drive.trajectoryBuilder(start)
                .lineToSplineHeading(three1)
                .build();
        parkThree2 = robot.drive.trajectoryBuilder(parkThree1.end())
                .lineToSplineHeading(three2)
                .build();

        robot.drive.setPoseEstimate(start);
    }

    @Override
    public void initializeSteps(int location) {

        switch (location) {
            case 1:
                followTrajectory(parkOne1);
                followTrajectory(parkOne2);
                break;
            case 2:
                followTrajectory(parkTwo1);
                break;
            case 3:
                followTrajectory(parkThree1);
                followTrajectory(parkThree2);
                break;
        }
    }
}
