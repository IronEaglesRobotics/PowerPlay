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
@Autonomous(name = "TestAuto", group = "Competition")
public class testauto extends LinearOpMode {
    public static int parkPosition = 1;
    private Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        this.robot = new Robot().init(hardwareMap);

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startOP = new Pose2d(-34, -60, Math.toRadians(90));

        drive.setPoseEstimate(startOP);

        this.robot.getClaw().close();

        Trajectory push = drive.trajectoryBuilder(startOP)
                .forward(47)
                .build();

        Trajectory medium = drive.trajectoryBuilder(push.end())
                .lineToSplineHeading(new Pose2d(-35.25 , -16, Math.toRadians(151)))
                .build();

        Trajectory getConeOne = drive.trajectoryBuilder(medium.end())
                .lineToSplineHeading(new Pose2d(-53.25, -12, Math.toRadians(180)))
                .build();

        Trajectory goScore = drive.trajectoryBuilder(getConeOne.end())
                .lineToSplineHeading(new Pose2d(-54, -9.5, Math.toRadians(113)))
                .build();

        Trajectory getConeAgain = drive.trajectoryBuilder(goScore.end())
                .lineToSplineHeading(new Pose2d(-53.25, -12, Math.toRadians(180)))
                .build();

        Trajectory park = drive.trajectoryBuilder(getConeAgain.end())
                .back(48)
                .build();

        while (!isStarted()) {
        }

        //do stuff
        drive.followTrajectory(push);
        robot.getClaw().twistDown();
        robot.getLift().slideMed();
        robot.getArm().moveLeft();
        drive.followTrajectory(medium);
        robot.getLift().autoTop();
        sleep(150);
        robot.getClaw().open();
        robot.getArm().moveRight();
        sleep(200);
        robot.getClaw().twistUp();
        drive.followTrajectory(getConeOne);

        sleep(150);
        robot.getClaw().close();
        sleep(150);
        robot.getLift().lowJunc();
        robot.getArm().moveLeft();
        sleep(100);
        robot.getClaw().twistDown();
        drive.followTrajectory(goScore);
        robot.getLift().lowDunk();
        sleep(250);
        robot.getClaw().open();
        robot.getArm().moveRight();
        sleep(200);
        robot.getClaw().twistUp();
        drive.followTrajectory(getConeAgain);

        robot.getLift().autoTop2();
        sleep(400);
        robot.getClaw().close();
        sleep(150);
        robot.getLift().lowJunc();
        robot.getArm().moveLeft();
        sleep(100);
        robot.getClaw().twistDown();
        drive.followTrajectory(goScore);
        robot.getLift().lowDunk();
        sleep(150);
        robot.getClaw().open();
        robot.getArm().moveRight();
        sleep(200);
        robot.getClaw().twistUp();
        drive.followTrajectory(getConeAgain);

        robot.getLift().autoTop3();
        sleep(500);
        robot.getClaw().close();
        sleep(150);
        robot.getLift().lowJunc();
        robot.getArm().moveLeft();
        sleep(100);
        robot.getClaw().twistDown();
        drive.followTrajectory(goScore);
        robot.getLift().lowDunk();
        sleep(150);
        robot.getClaw().open();
        robot.getArm().moveRight();
        sleep(200);
        robot.getClaw().twistUp();
        drive.followTrajectory(getConeAgain);

        robot.getLift().autoTop4();
        sleep(650);
        robot.getClaw().close();
        sleep(150);
        robot.getLift().lowJunc();
        robot.getArm().moveLeft();
        sleep(100);
        robot.getClaw().twistDown();
        drive.followTrajectory(goScore);
        robot.getLift().lowDunk();
        sleep(150);
        robot.getClaw().open();
        robot.getArm().moveRight();
        sleep(200);
        robot.getClaw().twistUp();
        drive.followTrajectory(getConeAgain);

        robot.getLift().autoTop5();
        sleep(800);
        robot.getClaw().close();
        sleep(150);
        robot.getLift().lowJunc();
        robot.getArm().moveLeft();
        sleep(100);
        robot.getClaw().twistDown();
        drive.followTrajectory(goScore);
        robot.getLift().lowDunk();
        sleep(150);
        robot.getClaw().open();
        robot.getArm().moveMid();
        robot.getLift().slideDown();
        drive.followTrajectory(getConeAgain);
        drive.followTrajectory(park);





//
//        Trajectory park = drive.trajectoryBuilder(getCone.end())
//                .lineToSplineHeading(new Pose2d(36, -12, Math.toRadians(180)))
//                .lineToSplineHeading(new Pose2d(12, -12, Math.toRadians(180)))
//                .build();

        //Trajectories
//        Trajectory park1 = drive.trajectoryBuilder(pushBack.end())
//                .forward(6)
//                .splineTo(new Vector2d(-57,-36),Math.toRadians(180))
//                .build();
//
//        Trajectory park2 = drive.trajectoryBuilder(pushBack.end())
//                .forward(14)
//                .build();
//
//        Trajectory park3 = drive.trajectoryBuilder(pushBack.end())
//                .forward(6)
//                .splineTo(new Vector2d(-8,-32),Math.toRadians(0))
//                .build();

        while (!isStarted()) {
            parkPosition = robot.getAutoCamera().getMarkerId();
            telemetry.addData("parkPosition", (parkPosition));
            telemetry.update();
        }
        // Do stuff



//        switch (parkPosition) {
//            case 1:
//                drive.followTrajectory(park1);
//                // Park on the outside edge of the field
//                break;
//            case 2:
//                drive.followTrajectory(park2);
//                break;
//            case 3:
//                drive.followTrajectory(park3);
//                // Park in the middle of the field
//                break;
//            default:
//                break;
//            // AHHH!!!!!
//
//        }


    }
}
