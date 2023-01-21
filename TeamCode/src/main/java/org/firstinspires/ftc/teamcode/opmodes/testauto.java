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
                .lineToSplineHeading(new Pose2d(-35 , -18, Math.toRadians(151)))
                .build();

        Trajectory getConeOne = drive.trajectoryBuilder(medium.end())
                .lineToSplineHeading(new Pose2d(-54.5, -16, Math.toRadians(180)))
                .build();

        Trajectory goScore = drive.trajectoryBuilder(getConeOne.end())
                .lineToSplineHeading(new Pose2d(-54.3, -21.5, Math.toRadians(153)))
                .build();

        Trajectory low = drive.trajectoryBuilder(goScore.end())
                .forward(3)
                .build();

        Trajectory cone = drive.trajectoryBuilder(low.end())
                .back(3)
                .build();

        Trajectory low2 = drive.trajectoryBuilder(cone.end())
                .forward(3)
                .build();

        //do stuff
        drive.followTrajectory(push);
        robot.getClaw().twistDown();
        robot.getLift().slideMed();
        robot.getArm().moveLeft();
        drive.followTrajectory(medium);
        robot.getLift().dunk();
        sleep(300);
        robot.getClaw().open();
        robot.getArm().moveMid();
        drive.followTrajectory(getConeOne);
        robot.getClaw().twistUp();

        drive.followTrajectory(goScore);
        robot.getArm().moveRight();
        robot.getLift().autoTop();
        sleep(500);
        robot.getClaw().close();
        sleep(1000);
        robot.getLift().lowJunc();
        robot.getArm().moveLeft();
        robot.getClaw().twistDown();
        drive.followTrajectory(low);
        robot.getLift().lowDunk();
        sleep(1000);
        robot.getClaw().open();
        robot.getArm().moveRight();
        sleep(200);
        robot.getClaw().twistUp();

        drive.followTrajectory(cone);
        robot.getLift().autoTop();
        sleep(500);
        robot.getClaw().close();
        sleep(1000);
        robot.getLift().lowJunc();
        robot.getArm().moveLeft();
        robot.getClaw().twistDown();
        drive.followTrajectory(low2);
        robot.getLift().lowDunk();
        sleep(1000);
        robot.getClaw().open();
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
