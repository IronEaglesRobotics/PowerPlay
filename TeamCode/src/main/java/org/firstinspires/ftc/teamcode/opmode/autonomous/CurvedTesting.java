package org.firstinspires.ftc.teamcode.opmode.autonomous;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;

@Config
@Autonomous
public class CurvedTesting extends LinearOpMode {
    public static int test_forward_tangent = 80;
    public static int test_backwards_tangent = 90;

    @Override
    public void runOpMode() throws InterruptedException {

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d start = new Pose2d(-12,65.5,Math.toRadians(-90));
        Pose2d back = new Pose2d(-12,62,Math.toRadians(-90));
        Pose2d intermediate = new Pose2d(-12, 30, Math.toRadians(-90));
        Pose2d turretJ1 = new Pose2d(-8,8,Math.toRadians(-135));
//        Pose2d turretJ2 = new Pose2d(-12,6,Math.toRadians(-90));
//        Pose2d turretJ3 = new Pose2d(-12,-18,Math.toRadians(-90));

        // turret paths
        Trajectory forwardFromStart = drive.trajectoryBuilder(start)
                .lineToSplineHeading(intermediate)
//                .splineTo(new Vector2d(turretJ1.getX(),turretJ1.getY()), turretJ1.getHeading())
//                .splineToSplineHeading(turretJ1, Math.toRadians(test_forawrd_tangent))
                .splineToSplineHeading(new Pose2d(intermediate.getX()+0.001,intermediate.getY(), intermediate.getHeading()), Math.toRadians(test_forward_tangent))
                .lineToSplineHeading(turretJ1)
                .build();
        Trajectory forwardFromBack = drive.trajectoryBuilder(back)
                .lineToSplineHeading(intermediate)
//                .splineTo(new Vector2d(turretJ1.getX(),turretJ1.getY()), turretJ1.getHeading())
//                .splineToSplineHeading(turretJ1, Math.toRadians(test_forawrd_tangent))
                .splineToSplineHeading(new Pose2d(intermediate.getX()+0.001,intermediate.getY(), intermediate.getHeading()), Math.toRadians(test_forward_tangent))
                .lineToSplineHeading(turretJ1)
                .build();
        Trajectory backFromForward = drive.trajectoryBuilder(turretJ1, true)
                .lineToSplineHeading(intermediate)
//                .splineTo(new Vector2d(intermediate.getX(),intermediate.getY()), Math.toRadians(test_backwards_tangent))
                .splineToSplineHeading(new Pose2d(intermediate.getX()+0.001,intermediate.getY(), intermediate.getHeading()), Math.toRadians(test_backwards_tangent))
                .lineToSplineHeading(back)
//                .splineTo(new Vector2d(intermediate.getX(),intermediate.getY()), Math.toRadians(90))
//                .splineTo(new Vector2d(back.getX(),back.getY()), Math.toRadians(90))
                .build();

//        // turret paths
//        Trajectory forwardFromStart = drive.trajectoryBuilder(start)
//                .lineToSplineHeading(intermediate)
////                .splineTo(new Vector2d(turretJ1.getX(),turretJ1.getY()), turretJ1.getHeading())
//                .splineToSplineHeading(turretJ1, Math.toRadians(test_forawrd_tangent))
////                .splineToSplineHeading(new Pose2d(intermediate.getX()+0.001,intermediate.getY()+0.001), intermediate.getHeading()+0.001)
////                .lineToSplineHeading(turretJ1)
//                .build();
//        Trajectory forwardFromBack = drive.trajectoryBuilder(back)
//                .lineToSplineHeading(intermediate)
////                .splineTo(new Vector2d(turretJ1.getX(),turretJ1.getY()), turretJ1.getHeading())
//                .splineToSplineHeading(turretJ1, Math.toRadians(test_forawrd_tangent))
////                .splineToSplineHeading(new Pose2d(intermediate.getX()+0.001,intermediate.getY()+0.001), intermediate.getHeading()+0.001)
////                .lineToSplineHeading(turretJ1)
//                .build();
//        Trajectory backFromForward = drive.trajectoryBuilder(turretJ1, true)
////                .lineToSplineHeading(intermediate)
//                .splineTo(new Vector2d(intermediate.getX(),intermediate.getY()), Math.toRadians(test_backwards_tangent))
//                .lineToSplineHeading(back)
////                .splineTo(new Vector2d(intermediate.getX(),intermediate.getY()), Math.toRadians(90))
////                .splineTo(new Vector2d(back.getX(),back.getY()), Math.toRadians(90))
//                .build();


        drive.setPoseEstimate(start);//12.5x12  11.25

        waitForStart();
        if(isStopRequested()) return;

        drive.followTrajectory(forwardFromStart);
        sleep(2000);
        drive.followTrajectory(backFromForward);
        sleep(2000);
//
        drive.followTrajectory(forwardFromBack);
        drive.followTrajectory(backFromForward);
        drive.followTrajectory(forwardFromBack);
        drive.followTrajectory(backFromForward);
        drive.followTrajectory(forwardFromBack);
        drive.followTrajectory(backFromForward);
        drive.followTrajectory(forwardFromBack);
        drive.followTrajectory(backFromForward);
        drive.followTrajectory(forwardFromBack);
        drive.followTrajectory(backFromForward);
    }
}
