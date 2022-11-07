package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.vision.AprilTagDetectionPipeline;
import org.openftc.apriltag.AprilTagDetection;

import java.util.ArrayList;

@Autonomous(name = "MainAuto", group = "Competition")
public class MainAuto extends LinearOpMode {
    private int parkPosition = -1;
    private Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        this.robot = new Robot().init(hardwareMap, true);
        this.parkPosition = robot.getAutoCamera().getMarkerId();

        // Do stuff



        switch(this.parkPosition) {
            case 1:
                // Park on the outside edge of the field
                break;
            case 2:
                // Park on the interior of the three options
                break;
            case 3:
                // Park in the middle of the field
                break;
            default:
                // AHHH!!!!!




        }
    }
}
