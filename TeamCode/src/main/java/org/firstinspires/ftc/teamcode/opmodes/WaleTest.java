package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.hardware.Robot;

@Config
@Autonomous(name = "WALE", group = "Competition", preselectTeleOp = "MainTeleOp")
public class WaleTest extends LinearOpMode {
    Robot robot;
    private final double targetDistance = 4.0;
    private final double tolerance = 0.1;
    public static double speed = 0.2;

    @Override
    public void runOpMode() throws InterruptedException {
        this.robot = new Robot().init(hardwareMap);
        this.robot.getClaw().openWide();

        while(!isStarted()) {
            telemetry.addData("distance", this.robot.getWale().getDistance(DistanceUnit.INCH));
            telemetry.addData("touch", this.robot.getWale().isPressed());
            telemetry.update();
        }

        waitForStart();

        while(!this.robot.getWale().isPressed() && opModeIsActive()) {
            this.robot.getDrive().setWeightedDrivePower(new Pose2d(speed ,0, 0));
        }
        this.robot.getDrive().followTrajectory(this.robot.getDrive().trajectoryBuilder(new Pose2d()).back(2.0).build());

        double currentDistance = Double.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            double d = robot.getWale().getDistance(DistanceUnit.INCH);
            if (d < currentDistance) {
                currentDistance = d;
            }
        }
        double error = Math.abs(targetDistance - currentDistance);

        if (currentDistance > targetDistance) {
            telemetry.addLine("Moving Left");
            telemetry.addData("currentDistance", currentDistance);
            telemetry.update();
            this.robot.getDrive().followTrajectory(this.robot.getDrive().trajectoryBuilder(new Pose2d()).strafeLeft(error).build());
        } else if (currentDistance < targetDistance) {
            telemetry.addLine("Moving Right");
            telemetry.addData("currentDistance", currentDistance);
            telemetry.update();
            this.robot.getDrive().followTrajectory(this.robot.getDrive().trajectoryBuilder(new Pose2d()).strafeRight(error).build());
        }
//
//        while(opModeIsActive()) {
//            currentDistance = this.robot.getWale().getDistance(DistanceUnit.INCH);
//            error = targetDistance - currentDistance;
//
//            telemetry.addData("distance", currentDistance);
//            telemetry.addData("error", error);
//            telemetry.update();
//        }
    }
}