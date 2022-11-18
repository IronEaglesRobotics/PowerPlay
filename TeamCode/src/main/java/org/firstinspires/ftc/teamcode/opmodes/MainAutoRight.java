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
@Autonomous(name = "MainAutoRight", group = "Competition")
public class MainAutoRight extends LinearOpMode {
    public static int TO_PARK_ROTATION = 180;
    private int parkPosition = 2;
    private Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        this.robot = new Robot().init(hardwareMap, Robot.Vision.AUTO);

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startOP = new Pose2d(34, -60, Math.toRadians(90));

        drive.setPoseEstimate(startOP);

        this.robot.getClaw().open();
        this.robot.getClaw().twistUp();


        //Trajectories
        Trajectory push = drive.trajectoryBuilder(startOP)
                .forward(30)
                .build();

        Trajectory pushBack = drive.trajectoryBuilder(push.end())
                .back(30)
                .build();

        Trajectory scorePreLoad = drive.trajectoryBuilder(pushBack.end())
                .forward(12)
                .splineTo(new Vector2d(24, -40), Math.toRadians(180))
                .splineTo(new Vector2d(10, -38), Math.toRadians(125))
                .build();


        Trajectory score = drive.trajectoryBuilder(scorePreLoad.end())
                .forward(3)
                .build();


        Trajectory backLittle = drive.trajectoryBuilder(score.end())
                .back(3)
                .build();

        Trajectory toPark = drive.trajectoryBuilder(new Pose2d(backLittle.end().getX(), backLittle.end().getY(), Math.toRadians(180)))
                .back(17)
                .build();

        Trajectory park1 = drive.trajectoryBuilder(toPark.end())
                .back(36)
                .build();

        Trajectory park2 = drive.trajectoryBuilder(toPark.end())
                .back(12)
                .build();

        Trajectory park3 = drive.trajectoryBuilder(toPark.end())
                .forward(11)
                .build();

        //Camera look stuff
        while (!isStarted()) {
            this.parkPosition = robot.getAutoCamera().getMarkerId();
            telemetry.addData("parkPosition", (parkPosition));
            telemetry.update();
        }


        //Cone Scoring
        this.robot.getArm().moveRight();
        sleep(750);
        this.robot.getArm().drop();
        drive.followTrajectory(push);
        drive.followTrajectory(pushBack);
        drive.followTrajectory(scorePreLoad);
        this.robot.getLift().slideUp();
        sleep(2000);
        drive.followTrajectory(score);
        sleep(500);
        this.robot.getLift().dunk();
        sleep(500);
        this.robot.getClaw().close();
        drive.followTrajectory(backLittle);
        sleep(300);
        this.robot.getLift().slideDown();
        sleep(2000);
        drive.turn(-Math.toRadians(-25));
        drive.followTrajectory(toPark);

        //Parking
        switch (this.parkPosition) {
            case 1:
                drive.followTrajectory(park3);
                this.robot.getClaw().auto();
                sleep(1000);
                // Park on the outside edge of the field
                break;
            case 2:
                drive.followTrajectory(park2);
                this.robot.getClaw().auto();
                sleep(1000);
                break;
            case 3:
                drive.followTrajectory(park1);
                this.robot.getClaw().auto();
                sleep(1000);
                // Park in the middle of the field
                break;
            default:

                break;
            // AHHH!!!!!

        }
    }
}
