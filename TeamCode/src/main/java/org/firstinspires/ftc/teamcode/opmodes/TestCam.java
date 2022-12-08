package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.teamcode.hardware.Robot.Vision.AIMING;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.AIMING_KP;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.opencv.core.Point;

@Config
@TeleOp(name = "TestCam", group = "Competition")
public class TestCam extends OpMode {
    FtcDashboard dashboard = FtcDashboard.getInstance();
    Telemetry dashboardTelemetry = dashboard.getTelemetry();
    PController pController;

    private Robot robot;

    @Override
    public void init() {
        this.robot = new Robot().init(hardwareMap, AIMING);

        this.pController = new PController(AIMING_KP);
        this.pController.setSetPoint(320);
    }


    @Override
    public void loop() {
        Point topOfJunction = this.robot.getAimingCamera().getTopOfJunction();
        this.pController.setP(AIMING_KP);

        double output = pController.calculate(topOfJunction.x) * -1;

        if (gamepad1.a && !this.pController.atSetPoint()) {
            this.robot.getDrive().setInput(0, 0, output);
        } else {
            this.robot.getDrive().setInput(0, 0, 0);
        }

        dashboardTelemetry.addData("Output", output);
        dashboardTelemetry.addData("Junction Top", topOfJunction.toString());
        dashboardTelemetry.update();
    }


    public void aim() {
        this.pController = new PController(AIMING_KP);
        this.pController.setSetPoint(320);

        Point topOfJunction = this.robot.getAimingCamera().getTopOfJunction();
        while(!pController.atSetPoint()) {
            double output = pController.calculate(topOfJunction.x) * -1;
            this.robot.getDrive().setInput(0, 0, output);
        }

        this.robot.getDrive().setInput(0, 0, 0);
    }
}
