package org.firstinspires.ftc.teamcode.opmode.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;

@Autonomous
public class BlueRightAuto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        int[] heights = {0, (int)(145.1*(32/112.0)), (int)(145.1*(64/112.0)), (int)(145.1*(96/112.0)), (int)(145.1*(128/112.0))};

        Pose2d start = new Pose2d(-36,66, Math.toRadians(180));
        Pose2d start2 = new Pose2d(-36,24, Math.toRadians(180));
        Pose2d score = new Pose2d(-32,8,Math.toRadians(135));

        Pose2d sLoad1 = new Pose2d(-48, 12, Math.toRadians(180));
        Pose2d sLoad2 = new Pose2d(-48, 12, Math.toRadians(180));
        Pose2d sLoad3 = new Pose2d(-48, 12, Math.toRadians(180));
        Pose2d sLoad4 = new Pose2d(-48, 12, Math.toRadians(180));
        Pose2d sLoad5 = new Pose2d(-48, 12, Math.toRadians(180));
        Pose2d lLoad1 = new Pose2d(-62,12,Math.toRadians(180));
        Pose2d lLoad2 = new Pose2d(-62,12,Math.toRadians(180));
        Pose2d lLoad3 = new Pose2d(-62,12,Math.toRadians(180));
        Pose2d lLoad4 = new Pose2d(-62,12,Math.toRadians(180));
        Pose2d lLoad5 = new Pose2d(-62,12,Math.toRadians(180));

        Pose2d lScore1 = new Pose2d(-48,12,Math.toRadians(180));
        Pose2d lScore2 = new Pose2d(-48,12,Math.toRadians(180));
        Pose2d lScore3 = new Pose2d(-48,12,Math.toRadians(180));
        Pose2d lScore4 = new Pose2d(-48,12,Math.toRadians(180));
        Pose2d lScore5 = new Pose2d(-48,12,Math.toRadians(180));
        Pose2d sScore1 = new Pose2d(-32,8,Math.toRadians(135));
        Pose2d sScore2 = new Pose2d(-32,8,Math.toRadians(135));
        Pose2d sScore3 = new Pose2d(-32,8,Math.toRadians(135));
        Pose2d sScore4 = new Pose2d(-32,8,Math.toRadians(135));
        Pose2d sScore5 = new Pose2d(-32,8,Math.toRadians(135));

        Trajectory startScore = drive.trajectoryBuilder(start)
                .lineToSplineHeading(start2)
                .splineToSplineHeading(score, Math.toRadians(-45))
                .build();
        Trajectory load1 = drive.trajectoryBuilder(startScore.end())
                .lineToSplineHeading(sLoad1)
                .splineToSplineHeading(lLoad1, Math.toRadians(180))
                .build();
        Trajectory score1 = drive.trajectoryBuilder(load1.end())
                .lineToSplineHeading(start2)
                .splineToSplineHeading(score, Math.toRadians(-45))
                .build();


        drive.setPoseEstimate(start);//12.5x12  11.25

        waitForStart();
        if(isStopRequested()) return;

    }
}
