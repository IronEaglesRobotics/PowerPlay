package org.firstinspires.ftc.teamcode.opmodes;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.hardware.Robot;

@Autonomous(name = "TestAuto", group = "Competition")
public class testauto extends LinearOpMode {
    public int parkPosition = 2;
    private Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        this.robot = new Robot().init(hardwareMap, true);

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startOP = new Pose2d(-34, -60, Math.toRadians(90));

        drive.setPoseEstimate(startOP);

        this.robot.getClaw().open();


        //Trajectories
        Trajectory park1 = drive.trajectoryBuilder(startOP)
                .forward(18)
                .splineTo(new Vector2d(-57,-36),Math.toRadians(0))
                .build();

        Trajectory park2 = drive.trajectoryBuilder(startOP)
                .forward(18)
                .splineTo(new Vector2d(-34,-36),Math.toRadians(0))
                .build();

        Trajectory park3 = drive.trajectoryBuilder(startOP)
                .forward(12)
                .splineTo(new Vector2d(-24,-36),Math.toRadians(0))
                .splineTo(new Vector2d(-11,-34),Math.toRadians(-90))
                .build();

        while (!isStarted()) {
            this.parkPosition = robot.getAutoCamera().getMarkerId();
        }
        // Do stuff

        telemetry.addData("parkPosition", (parkPosition));

        switch (this.parkPosition) {
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
