package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.hardware.Robot;

@Autonomous(name = "MainAuto", group = "Competition")
public class MainAuto extends OpMode {
    private Robot robot;
    @Override
    public void init() {
        this.robot = new Robot().init(hardwareMap, true);
    }

    @Override
    public void loop() {

    }
}
