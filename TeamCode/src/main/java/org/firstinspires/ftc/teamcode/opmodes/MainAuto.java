package org.firstinspires.ftc.teamcode.opmodes;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.hardware.Robot;

@Autonomous(name = "MainAuto", group = "Competition")
public class MainAuto extends LinearOpMode {
    private int parkPosition = 2;
    private Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        this.robot = new Robot().init(hardwareMap, true);

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startOP = new Pose2d(-34, -60, Math.toRadians(90));
        Pose2d afterScore = new Pose2d(-14, -36, Math.toRadians(180));

        drive.setPoseEstimate(startOP);

        this.robot.getClaw().open();
        this.robot.getClaw().twistUp();


        //Trajectories

        Trajectory scorePreLoad = drive.trajectoryBuilder(startOP)
                .forward(12)
                .splineTo(new Vector2d(-24, -36), Math.toRadians(0))
                .splineTo(new Vector2d(-11, -34), Math.toRadians(45))
                .build();


        Trajectory park1 = drive.trajectoryBuilder(afterScore)
                .splineTo(new Vector2d(-60, -34), Math.toRadians(0))
                .build();

        Trajectory park2 = drive.trajectoryBuilder(afterScore)
                .splineTo(new Vector2d(-36, -35), Math.toRadians(0))
                .build();

        Trajectory park3 = drive.trajectoryBuilder(afterScore)
                .splineTo(new Vector2d(-14, -37), Math.toRadians(0))
                .build();

        Trajectory score = drive.trajectoryBuilder(scorePreLoad.end())
                .forward(2)
                .build();

        Trajectory backLittle = drive.trajectoryBuilder(score.end())
                .back(6)
                .build();

        Trajectory toPark = drive.trajectoryBuilder(new Pose2d(backLittle.end().getX(), backLittle.end().getY(), 0))
                .splineTo(new Vector2d(-14, -36), Math.toRadians(0))
                .build();

        while (!isStarted()) {
            this.parkPosition = robot.getAutoCamera().getMarkerId();
        }
        // Do stuff

        //Cone Scoring
//        this.robot.getArm().moveRight();
//        sleep(1000);
//        this.robot.getArm().drop();
        drive.followTrajectory(scorePreLoad);
        this.robot.getLift().slideUp();
        sleep(3000);
        drive.followTrajectory(score);
        this.robot.getClaw().close();
        sleep(500);
        this.robot.getClaw().open();
        drive.followTrajectory(backLittle);
        sleep(1000);
        this.robot.getLift().slideDown();
        sleep(3000);
        drive.turn(Math.toRadians(-45));
        drive.followTrajectory(toPark);

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
