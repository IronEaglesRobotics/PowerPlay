package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.teamcode.util.Configurables.ARM_STARTING;
import static org.firstinspires.ftc.teamcode.util.Configurables.ARM_UPRIGHT;
import static org.firstinspires.ftc.teamcode.util.Configurables.GO_SLOW;
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
    private boolean prevClawPressed = false;

    @Override
    public void init() {
        this.robot = new Robot().init(hardwareMap, null);
    }

    @Override
    public void loop() {
        // Drive
        this.robot.getDrive().setInput(gamepad1, gamepad2);

        // Arm
        boolean pressRight = gamepad2.dpad_right;
        boolean pressLeft = gamepad2.dpad_left;
        boolean pressMid = gamepad2.x;

        if (pressRight) {
            this.robot.getArm().moveRight();
        } else if (pressLeft) {
            this.robot.getArm().moveLeft();
        } else if (pressMid) {
            this.robot.getArm().moveMid();
        } else {
            this.robot.getArm().drop();
        }

        if (this.robot.getArm().getCurrentPosition() < ARM_STARTING) {
            this.robot.getClaw().twistDown();
        } else {
            this.robot.getClaw().twistUp();
        }

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


        if (upPressed && !prevUpPressed) {
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

        //Slow Mode

        if(gamepad1.a) {
            GO_SLOW = 4;
        } else {
            GO_SLOW = 1.4;
        }

        // Claw

//        boolean clawPressed = gamepad2.b;
//
//        if (gamepad2.b && !prevClawPressed) {
//            if (this.robot.getClaw().isOpen()) {
//                this.robot.getClaw().close();
//            } else {
//                this.robot.getClaw().open();
//            }
//        }
//
//        prevClawPressed = clawPressed;

        if (gamepad2.b) {
            this.robot.getClaw().close();
        } else {
            this.robot.getClaw().open();
        }

    }
}