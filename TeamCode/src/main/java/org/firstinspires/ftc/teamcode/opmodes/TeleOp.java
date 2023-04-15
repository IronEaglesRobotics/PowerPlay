package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.GO_OTHER_WAY;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.GO_SLOW;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.OTHER_WAY;
import static org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables.WHY_TURN;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.drive.opmode.util.Configurables;
import org.firstinspires.ftc.teamcode.hardware.Robot;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Tele-Op", group = "Development")
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
        Configurables.ARM_POWER = 1;
        Configurables.AUTOSCORE= Configurables.SLIDE_MAX_AUTO;
        // Drive
        this.robot.getDrive().setInput(gamepad1, gamepad2);


        // Arm
        boolean pressRight = gamepad2.dpad_left;
        boolean pressLeft = gamepad2.dpad_right;
        boolean pressMid = gamepad2.x;

        if (pressRight|| gamepad2.dpad_down) {
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
        boolean downPressed = gamepad2.a || gamepad2.dpad_down;

        telemetry.addData("Slide Position", (Position));
        telemetry.addData("Slide Position2", (Position2));
        telemetry.addData("armPosition", armPosition);
        telemetry.addData("Claw Grip Position", clawGripPosition);
        telemetry.update();


        if (upPressed && !prevUpPressed) {
            this.robot.getLift().slideMax();
        } else if (gamepad2.dpad_up) {
            this.robot.getLift().slideMax();
        } else if (gamepad2.left_bumper) {
            this.robot.getLift().slideMed();
        } else if (gamepad2.right_bumper) {
            this.robot.getLift().lowJunction();
        } else if (downPressed) {
            this.robot.getLift().slideDown();
        } else if ((prevUpPressed != upPressed) || prevDownPressed) {
            this.robot.getLift().slideStop();
        }

        prevUpPressed = upPressed;
        prevDownPressed = downPressed;


        //Slow Mode

        if (gamepad1.y) {
            GO_SLOW = 4;
            WHY_TURN = 3;
        } else if (gamepad1.x || gamepad1.right_bumper) {
            GO_SLOW = 1;
            WHY_TURN = 1;
        } else {
            GO_SLOW = 1.4;
            WHY_TURN = 1;
        }

        if (gamepad2.b || gamepad2.dpad_down) {
            this.robot.getClaw().open();
        } else {
            this.robot.getClaw().close();
        }

    }
}