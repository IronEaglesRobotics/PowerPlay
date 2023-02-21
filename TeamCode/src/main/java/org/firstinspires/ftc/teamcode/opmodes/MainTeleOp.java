package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.GO_OTHER_WAY;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.GO_SLOW;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.OTHER_WAY;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.WHY_TURN;

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
        this.robot = new Robot().init(hardwareMap);
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
            this.robot.getArm().moveRightTele();
        } else if (pressLeft) {
            this.robot.getArm().moveLeftTele();
        } else if (pressMid) {
            this.robot.getArm().moveMidTele();
        } else {
            this.robot.getArm().drop();
        }

        if (this.robot.getArm().getCurrentPosition() < GO_OTHER_WAY) {
            this.robot.getClaw().twistDown();
        } else if (this.robot.getArm().getCurrentPosition() > OTHER_WAY) {
            this.robot.getClaw().twistUp();
        }

        int Position = this.robot.getLift().slide.getCurrentPosition();
        int Position2 = this.robot.getLift().slide2.getCurrentPosition();
        int armPosition = this.robot.getArm().getCurrentPosition();
        double clawGripPosition = this.robot.getClaw().getCurrentPosition();

        // Lift
        boolean upPressed = gamepad2.y;
        boolean downPressed = gamepad2.a;

        telemetry.addData("Slide Position", (Position));
        telemetry.addData("Slide Position2", (Position2));
        telemetry.addData("armPosition", armPosition);
        telemetry.addData("Claw Grip Position", clawGripPosition);
        telemetry.update();


        if (upPressed && !prevUpPressed) {
            this.robot.getLift().slideMax();
        } else if (gamepad2.dpad_up && !prevUpPressed) {
            this.robot.getLift().slideMax();
            this.robot.getArm().moveLeft();
        } else if (gamepad2.left_bumper && !prevUpPressed) {
            this.robot.getLift().slideLow();
        } else if (gamepad2.right_bumper && !prevUpPressed) {
            this.robot.getLift().lowJunction();
        } else if (gamepad2.dpad_down && !prevUpPressed) {
            this.robot.getLift().slideMed();
            this.robot.getClaw().open();
            this.robot.getArm().moveRight();
        } else if (downPressed) {
            this.robot.getLift().slideDown();
        } else if ((prevUpPressed != upPressed) || prevDownPressed) {
            this.robot.getLift().slideStop();
        }

        prevUpPressed = upPressed;
        prevDownPressed = downPressed;

        //Slow Mode

        if(gamepad1.a || gamepad1.y || gamepad1.right_bumper) {
            GO_SLOW = 4;
            WHY_TURN = 3.5;
        } else if (gamepad1.x){
            GO_SLOW = 1;
            WHY_TURN = 1.25;
        } else if (gamepad1.dpad_up){
            GO_SLOW = 1;
            WHY_TURN = 1.25;
        } else {
            GO_SLOW = 1.4;
            WHY_TURN = 1.25;
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
            this.robot.getClaw().open();
        } else {
            this.robot.getClaw().close();
        }

    }
}