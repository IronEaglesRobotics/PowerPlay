package org.firstinspires.ftc.teamcode.opmodes;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.hardware.Robot;
@Config

@Autonomous(name = "TestAuto", group = "Competition")
public class testauto extends LinearOpMode {
    public static int parkPosition = 1;
    private Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        this.robot = new Robot().init(hardwareMap, true);

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startOP = new Pose2d(-34, -60, Math.toRadians(90));

        drive.setPoseEstimate(startOP);

        this.robot.getClaw().open();

        Trajectory push = drive.trajectoryBuilder(startOP)
                .forward(30)
                .build();

        Trajectory pushBack = drive.trajectoryBuilder(push.end())
                .back(18)
                .build();

        //Trajectories
        Trajectory park1 = drive.trajectoryBuilder(pushBack.end())
                .forward(6)
                .splineTo(new Vector2d(-57,-36),Math.toRadians(180))
                .build();

        Trajectory park2 = drive.trajectoryBuilder(pushBack.end())
                .forward(14)
                .build();

        Trajectory park3 = drive.trajectoryBuilder(pushBack.end())
                .forward(6)
                .splineTo(new Vector2d(-8,-32),Math.toRadians(0))
                .build();

        while (!isStarted()) {
            parkPosition = robot.getAutoCamera().getMarkerId();
            telemetry.addData("parkPosition", (parkPosition));
            telemetry.update();
        }
        // Do stuff

        drive.followTrajectory(push);
        drive.followTrajectory(pushBack);

        switch (parkPosition) {
            case 1:
                drive.followTrajectory(park1);
                // Park on the outside edge of the field
                break;
            case 2:
                drive.followTrajectory(park2);
                break;
            case 3:
                drive.followTrajectory(park3);
                // Park in the middle of the field
                break;
            default:
                break;
            // AHHH!!!!!

        }


    }
}
