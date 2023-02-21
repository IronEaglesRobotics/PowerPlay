package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.hardware.Robot;

@Config
@Autonomous(name = "LeftAutoChampionships", group = "Competition")
public class LeftAutoChampionships extends LinearOpMode {
    public static int parkPosition = 1;
    private Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        this.robot = new Robot().init(hardwareMap);

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startOP = new Pose2d(-34, -60, Math.toRadians(90));

        drive.setPoseEstimate(startOP);

        this.robot.getClaw().close();

        //Trajectories
        Trajectory push = drive.trajectoryBuilder(startOP)
                .forward(47)
                .build();

        Trajectory scoreOnMedium = drive.trajectoryBuilder(push.end())
                .lineToSplineHeading(new Pose2d(-35.25, -16, Math.toRadians(151)))
                .build();

        Trajectory getFirstConeFromStack = drive.trajectoryBuilder(scoreOnMedium.end())
                .lineToSplineHeading(new Pose2d(-53, -11.5, Math.toRadians(180)))
                .build();

        Trajectory scoreOnLow = drive.trajectoryBuilder(getFirstConeFromStack.end())
                .lineToSplineHeading(new Pose2d(-53.5, -10, Math.toRadians(116.5)))
                .build();

        Trajectory getFromStack = drive.trajectoryBuilder(scoreOnLow.end())
                .lineToSplineHeading(new Pose2d(-53.25, -12, Math.toRadians(180)))
                .build();

        Trajectory scoreOnLowAgain = drive.trajectoryBuilder(getFromStack.end())
                .lineToSplineHeading(new Pose2d(-53.5, -10.25, Math.toRadians(116)))
                .build();

        Trajectory park1 = drive.trajectoryBuilder(getFromStack.end())
                .forward(4)
                .build();

        Trajectory park2 = drive.trajectoryBuilder(getFromStack.end())
                .back(17)
                .build();

        Trajectory park3 = drive.trajectoryBuilder(getFromStack.end())
                .back(42)
                .build();

        //Init
        this.robot.useAutoCamera();


        while (!isStarted()) {
            parkPosition = robot.getAutoCamera().getMarkerId();
            telemetry.addData("parkPosition", (parkPosition));
            telemetry.addData("armPosition", (this.robot.getArm().getCurrentPosition()));
            telemetry.update();
        }

        //do stuff
        drive.followTrajectory(push);
        robot.getClaw().twistDown();
        robot.getLift().slideMed();
        robot.getArm().moveLeft();
        drive.followTrajectory(scoreOnMedium);
        robot.getLift().autoTop();
        sleep(150);
        robot.getClaw().open();
        robot.getArm().moveRight();
        sleep(200);
        robot.getClaw().twistUp();
        drive.followTrajectory(getFirstConeFromStack);

        sleep(150);
        robot.getClaw().close();
        sleep(150);
        robot.getLift().lowJunction();
        robot.getArm().moveLeft();
        sleep(100);
        robot.getClaw().twistDown();
        drive.followTrajectory(scoreOnLowAgain);
        robot.getLift().lowDunk();
        sleep(250);
        robot.getClaw().open();
        robot.getArm().moveRight();
        sleep(200);
        robot.getClaw().twistUp();
        drive.followTrajectory(getFromStack);

        robot.getLift().autoTop2();
        sleep(400);
        robot.getClaw().close();
        sleep(150);
        robot.getLift().lowJunction();
        robot.getArm().moveLeft();
        sleep(100);
        robot.getClaw().twistDown();
        drive.followTrajectory(scoreOnLowAgain);
        robot.getLift().lowDunk();
        sleep(150);
        robot.getClaw().open();
        robot.getArm().moveRight();
        sleep(200);
        robot.getClaw().twistUp();
        drive.followTrajectory(getFromStack);

        robot.getLift().autoTop3();
        sleep(500);
        robot.getClaw().close();
        sleep(150);
        robot.getLift().lowJunction();
        robot.getArm().moveLeft();
        sleep(100);
        robot.getClaw().twistDown();
        drive.followTrajectory(scoreOnLowAgain);
        robot.getLift().lowDunk();
        sleep(150);
        robot.getClaw().open();
        robot.getArm().moveRight();
        sleep(200);
        robot.getClaw().twistUp();
        drive.followTrajectory(getFromStack);

        robot.getLift().autoTop4();
        sleep(650);
        robot.getClaw().close();
        sleep(150);
        robot.getLift().lowJunction();
        robot.getArm().moveLeft();
        sleep(100);
        robot.getClaw().twistDown();
        drive.followTrajectory(scoreOnLowAgain);
        robot.getLift().lowDunk();
        sleep(150);
        robot.getClaw().open();
        robot.getArm().moveRight();
        sleep(200);
        robot.getClaw().twistUp();
        drive.followTrajectory(getFromStack);

        robot.getLift().autoTop5();
        sleep(800);
        robot.getClaw().close();
        sleep(150);
        robot.getLift().lowJunction();
        robot.getArm().moveLeft();
        sleep(100);
        robot.getClaw().twistDown();
        drive.followTrajectory(scoreOnLowAgain);
        robot.getLift().lowDunk();
        sleep(150);
        robot.getClaw().open();
        robot.getArm().moveMid();
        robot.getLift().slideDown();
        drive.followTrajectory(getFromStack);

        //Park
        switch (parkPosition) {
            case 1:
                drive.followTrajectory(park1);
                robot.getArm().moveMid();
                break;
            case 2:
                drive.followTrajectory(park2);
                robot.getArm().moveMid();
                break;
            case 3:
                drive.followTrajectory(park3);
                robot.getArm().moveMid();
                break;
            default:
                drive.followTrajectory(park1);
                robot.getArm().moveMid();
                break;
            // AHHH!!!!!
        }
    }
}