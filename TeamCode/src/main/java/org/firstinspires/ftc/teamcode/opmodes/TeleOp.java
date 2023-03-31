package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.GO_OTHER_WAY;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.GO_SLOW;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.OTHER_WAY;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.WHY_TURN;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables;
import org.firstinspires.ftc.teamcode.hardware.Robot;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp (name = "Tele-Op", group = "Development")
public class TeleOp extends OpMode {
    private Robot robot;
    private boolean prevUpPressed = false;
    private boolean prevUpPressed1 = false;
    private boolean prevDownPressed1 = false;

    private boolean prevDownPressed = false;
    private boolean prevClawPressed = false;

    @Override
    public void init() {
        this.robot = new Robot().init(hardwareMap);
    }

    @Override
    public void loop() {
        Configurables.ARM_POWER=1;
        // Drive
        this.robot.getDrive().setInput(gamepad1, gamepad2);


        // Arm
        boolean pressRight = gamepad2.dpad_right;
        boolean pressLeft = gamepad2.dpad_left;
        boolean pressMid = gamepad2.x;

        if (pressRight || gamepad1.dpad_right) {
            this.robot.getArm().moveRightTele();
        } else if (pressLeft ||gamepad1.dpad_left) {
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
        boolean upPressed1 = gamepad1.b;
        boolean downPressed1 = gamepad1.a;

        telemetry.addData("Slide Position", (Position));
        telemetry.addData("Slide Position2", (Position2));
        telemetry.addData("armPosition", armPosition);
        telemetry.addData("Claw Grip Position", clawGripPosition);
        telemetry.update();


        if (upPressed || upPressed1 && !prevUpPressed) {
            this.robot.getLift().slideMax();
        } else if (gamepad2.dpad_up || gamepad1.dpad_up) {
            this.robot.getLift().slideMax();
        } else if (gamepad2.left_bumper || gamepad1.left_bumper) {
            this.robot.getLift().slideMedTele();
        } else if (gamepad2.right_bumper || gamepad1.dpad_down) {
            this.robot.getLift().lowJunction();
        } else if (gamepad2.dpad_down) {
            this.robot.getLift().slideDown();
        } else if (downPressed || downPressed1) {
            this.robot.getLift().slideDown();
        } else if ((prevUpPressed != upPressed) || prevDownPressed) {
            this.robot.getLift().slideStop();
        } else if ((prevUpPressed1 != upPressed1) || prevDownPressed1) {
            this.robot.getLift().slideStop();
        }
        prevUpPressed = upPressed;
        prevDownPressed = downPressed;
        prevUpPressed1 = upPressed1;
        prevDownPressed1 = downPressed1;

        //Slow Mode

        if(gamepad1.y) {
            GO_SLOW = 4;
            WHY_TURN = 3;
        } else if (gamepad1.x){
            GO_SLOW = 1;
            WHY_TURN = 1;
        } else {
            GO_SLOW = 1.4;
            WHY_TURN = 1;
        }

        if (gamepad2.b || gamepad1.right_bumper) {
            this.robot.getClaw().open();
        } else {
            this.robot.getClaw().close();
        }

    }
}