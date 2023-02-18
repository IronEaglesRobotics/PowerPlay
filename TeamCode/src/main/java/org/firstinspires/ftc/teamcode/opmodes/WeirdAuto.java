package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.hardware.Robot;

@Config

@Autonomous(name = "WeirdAuto", group = "Competition", preselectTeleOp = "MainTeleOp")
public class WeirdAuto extends LinearOpMode {
    public static int parkPosition = 1;
    private Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {

        this.robot = new Robot().init(hardwareMap);

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startOP = new Pose2d(-32.5, -60, Math.toRadians(90));

        drive.setPoseEstimate(startOP);

        this.robot.getClaw().close();

        Trajectory shift = drive.trajectoryBuilder(startOP)
                .lineToSplineHeading(new Pose2d(-34,-60,Math.toRadians(90)))
                .build();

        Trajectory firstMedium = drive.trajectoryBuilder(shift.end())
                .lineToSplineHeading(new Pose2d(-35,-16,Math.toRadians(160)))
                .build();

        Trajectory getStuff = drive.trajectoryBuilder(firstMedium.end())
                .splineTo(new Vector2d(-54.5, -10.5),Math.toRadians(180))
                .build();

        Trajectory score2 = drive.trajectoryBuilder(getStuff.end())
                .lineToSplineHeading(new Pose2d(-34,-16,Math.toRadians(160)))
                .build();

        Trajectory get2 = drive.trajectoryBuilder(score2.end())
                .splineTo(new Vector2d(-54.5, -10),Math.toRadians(180))
                .build();

        Trajectory score3 = drive.trajectoryBuilder(getStuff.end())
                .lineToSplineHeading(new Pose2d(-34,-15.5,Math.toRadians(160)))
                .build();

        Trajectory get3 = drive.trajectoryBuilder(score2.end())
                .splineTo(new Vector2d(-54.5, -9.5),Math.toRadians(180))
                .build();

        Trajectory score4 = drive.trajectoryBuilder(getStuff.end())
                .lineToSplineHeading(new Pose2d(-34,-15,Math.toRadians(160)))
                .build();

        Trajectory get4 = drive.trajectoryBuilder(score2.end())
                .splineTo(new Vector2d(-54.5, -9),Math.toRadians(180))
                .build();

        Trajectory score5 = drive.trajectoryBuilder(getStuff.end())
                .lineToSplineHeading(new Pose2d(-34,-14.5,Math.toRadians(160)))
                .build();

        Trajectory get5 = drive.trajectoryBuilder(score2.end())
                .splineTo(new Vector2d(-54.5, -8),Math.toRadians(180))
                .build();

        Trajectory score6 = drive.trajectoryBuilder(getStuff.end())
                .lineToSplineHeading(new Pose2d(-34,-14,Math.toRadians(160)))
                .build();

        while (!isStarted()) {
//            parkPosition = robot.getAutoCamera().getMarkerId();
//            telemetry.addData("parkPosition", (parkPosition));
//            telemetry.update();
        }

        // Do stuff
        drive.followTrajectory(firstMedium);
        robot.getArm().moveMid();
        sleep(500);

        drive.followTrajectory(getStuff);
        robot.getArm().moveMid();
        sleep(700);
        drive.followTrajectory(score2);
        robot.getArm().moveMid();
        sleep(700);

        drive.followTrajectory(get2);
        robot.getArm().moveMid();
        sleep(700);
        drive.followTrajectory(score3);
        robot.getArm().moveMid();
        sleep(700);

        drive.followTrajectory(get3);
        robot.getArm().moveMid();
        sleep(700);
        drive.followTrajectory(score4);
        robot.getArm().moveMid();
        sleep(700);

        drive.followTrajectory(get4);
        robot.getArm().moveMid();
        sleep(700);
        drive.followTrajectory(score5);
        robot.getArm().moveMid();
        sleep(700);

        drive.followTrajectory(get5);
        robot.getArm().moveMid();
        sleep(700);
        drive.followTrajectory(score6);
        robot.getArm().moveMid();
        sleep(700);


        switch (parkPosition) {
            case 1:

                break;
            case 2:
                break;
            case 3:

                // Park in the middle of the field
                break;
            default:
                break;
            // AHHH!!!!!

        }


    }
}
