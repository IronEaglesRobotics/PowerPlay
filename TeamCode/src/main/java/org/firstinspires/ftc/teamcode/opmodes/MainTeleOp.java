package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.teamcode.util.Configurables.ARM_UPRIGHT;
import static org.firstinspires.ftc.teamcode.util.Configurables.SLIDE_POSITION;
import static org.firstinspires.ftc.teamcode.util.Configurables.STOP;

import android.transition.Slide;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardware.Robot;

@TeleOp(name = "Main TeleOp", group = "Development")
public class MainTeleOp extends OpMode {
    private Robot robot;
    private boolean prevUpPressed = false;
    private boolean prevDownPressed = false;

    @Override
    public void init() {
        this.robot = new Robot().init(hardwareMap, false);
    }

    @Override
    public void loop() {
        // Drive
        this.robot.getDrive().setInput(gamepad1, gamepad2);

        // Arm
        boolean pressRight = gamepad2.dpad_right;
        boolean pressLeft = gamepad2.dpad_left;
        if (pressRight) {
            this.robot.getArm().moveRight();
        } else if (pressLeft) {
            this.robot.getArm().moveLeft();
        } else {
            this.robot.getArm().drop();
        }
        this.robot.getArm().update();

        this.robot.getClaw().twistUp();

//        if (this.robot.getArm().getCurrentPosition() < ARM_UPRIGHT) {
////            this.robot.getClaw().twistDown();
//        } else {
//            this.robot.getClaw().twistUp();
//        }

        int Position = this.robot.getLift().slide.getCurrentPosition();
        int Position2 = this.robot.getLift().slide2.getCurrentPosition();
        int armPosition = this.robot.getArm().getCurrentPosition();

        // Lift
        boolean upPressed = gamepad2.y;
        boolean downPressed = gamepad2.a;

        telemetry.addData("Slide Position", (Position));
        telemetry.addData("Slide Position2", (Position2));
        telemetry.addData("armPosition", armPosition);
        telemetry.update();


        if (gamepad2.x && !prevUpPressed) {
            this.robot.getLift().slideUpFree();
        } else if (upPressed && !prevUpPressed) {
            this.robot.getLift().slideUp();
        } else if (gamepad2.dpad_up && !prevUpPressed) {
            this.robot.getLift().slideMed();
        } else if (gamepad2.dpad_down && !prevUpPressed) {
            this.robot.getLift().slideLow();
        } else if (downPressed) {
            this.robot.getLift().slideDown();
        } else if ((prevUpPressed != upPressed) || prevDownPressed) {
            this.robot.getLift().slideStop();
        }

        prevUpPressed = upPressed;
        prevDownPressed = downPressed;

        // Claw
        if (gamepad2.b) {
            this.robot.getClaw().close();
        } else {
            this.robot.getClaw().open();
        }
    }
}