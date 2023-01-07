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

@Autonomous(name = "AutoCamTest", group = "Competition", preselectTeleOp = "MainTeleOp")
public class AutoCamTest extends LinearOpMode {
    public static int parkPosition = 1;
    private Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {

        this.robot = new Robot().init(hardwareMap);
//        this.robot.useAutoCamera();

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startOP = new Pose2d(-34, -60, Math.toRadians(90));

        drive.setPoseEstimate(startOP);

        this.robot.getClaw().open();
        this.robot.getClaw().twistDown();

        Trajectory push = drive.trajectoryBuilder(startOP)
                .forward(60)
                .build();

        Trajectory pushback = drive.trajectoryBuilder(push.end())
                .back(6)
                .build();

        Trajectory score = drive.trajectoryBuilder(pushback.end(), true)
                .lineToSplineHeading(new Pose2d(-34, -13, Math.toRadians(135)))
                .build();

        Trajectory ahhhhh = drive.trajectoryBuilder(score.end(), true)
                .back(2)
                .build();
        Trajectory getCone = drive.trajectoryBuilder(ahhhhh.end())
                .splineTo(new Vector2d(-34.00001, -13), Math.toRadians(180))
                .splineTo(new Vector2d(-50.5, -13), Math.toRadians(180))
                .build();

        Trajectory scoreAgain = drive.trajectoryBuilder(getCone.end(), true)
                .lineToSplineHeading(new Pose2d(-40, -13, Math.toRadians(180)))
                .lineToSplineHeading(new Pose2d(-34, -13, Math.toRadians(135)))
                .build();

        Trajectory ahhhhha = drive.trajectoryBuilder(scoreAgain.end(), true)
                .back(1)
                .build();

        Trajectory getAgain = drive.trajectoryBuilder(ahhhhha.end())
                .lineToSplineHeading(new Pose2d(55, -10, Math.toRadians(0)))
                .build();

        Trajectory scoreAgain2 = drive.trajectoryBuilder(getCone.end(), true)
                .lineToSplineHeading(new Pose2d(-40, -13, Math.toRadians(180)))
                .lineToSplineHeading(new Pose2d(-34, -13, Math.toRadians(132)))
                .build();

        Trajectory getAgain2 = drive.trajectoryBuilder(scoreAgain2.end())
                .splineTo(new Vector2d(-34.00001, -13), Math.toRadians(180))
                .splineTo(new Vector2d(-50.5, -14), Math.toRadians(180))
                .build();

        Trajectory getAgain3 = drive.trajectoryBuilder(ahhhhha.end())
                .splineTo(new Vector2d(-34.00001, -13), Math.toRadians(180))
                .splineTo(new Vector2d(-50.5, -14), Math.toRadians(180))
                .build();

        Trajectory park3 = drive.trajectoryBuilder(ahhhhha.end())
                .splineTo(new Vector2d(-34.00001, -13), Math.toRadians(180))
                .splineTo(new Vector2d(-55, -14), Math.toRadians(180))
                .build();

        Trajectory park2 = drive.trajectoryBuilder(ahhhhha.end())
                .lineToSplineHeading(new Pose2d(-35,-14, Math.toRadians(180)))
                .build();

        Trajectory park1 = drive.trajectoryBuilder(park2.end())
                .back(25)
                .build();


        robot.useAimingCamera();

        while (!isStarted()) {
//            parkPosition = robot.getAutoCamera().getMarkerId();
            telemetry.addData("parkPosition", (parkPosition));
            telemetry.update();
        }


        // Do stuff

        this.robot.aimSync();



//        switch (parkPosition) {
//            case 1:
//                drive.followTrajectory(park2);
//                drive.followTrajectory(park1);
//                break;
//            case 2:
//                drive.followTrajectory(park2);
//                break;
//            case 3:
//                drive.followTrajectory(getAgain3);
//                // Park in the middle of the field
//                break;
//            default:
//                drive.followTrajectory(park2);
//                break;
//            // AHHH!!!!!
//
//        }


    }
}
