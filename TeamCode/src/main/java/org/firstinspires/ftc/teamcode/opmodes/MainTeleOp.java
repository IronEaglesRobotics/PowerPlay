package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.teamcode.util.Configurables.ARM_UPRIGHT;

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

        if (this.robot.getArm().getCurrentPosition() < ARM_UPRIGHT) {
            this.robot.getClaw().twistDown();
        } else {
            this.robot.getClaw().twistUp();
        }

        // Lift
        boolean upPressed = gamepad2.y || gamepad1.y;
        boolean downPressed = gamepad2.x || gamepad1.x;
        if (upPressed && !prevUpPressed) {
            this.robot.getLift().slideUp();
        } else if (downPressed && !prevDownPressed) {
            this.robot.getLift().slideDown();
        } else if ((prevUpPressed != upPressed) || (prevDownPressed != downPressed)) {
            this.robot.getLift().slideStop();
        }
        prevUpPressed = upPressed;
        prevDownPressed = downPressed;

        // Claw
        if (gamepad2.a || gamepad1.b) {
            this.robot.getClaw().close();
        } else {
            this.robot.getClaw().open();
        }
    }
}