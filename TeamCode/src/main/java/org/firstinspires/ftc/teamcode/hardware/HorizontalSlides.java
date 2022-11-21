package org.firstinspires.ftc.teamcode.hardware;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class HorizontalSlides {
    private Servo pivot;

    public static double intake = 0.75; // up on joystick
    public static double score = 0.1; // down on joystick
    public static double scoreOffset = 0.15;//0.2

    public static double manualSpeed = 0.025;
    private double target = 0.1;

    public HorizontalSlides(HardwareMap hardwareMap) {
        pivot = hardwareMap.get(Servo.class, "pivot");

        goToIntake();
    }

    public void increaseTarget(double increase) {
        target -= (increase * manualSpeed);
        target = Math.min(intake, Math.max(score, target));
    }

    public void cancel() {
        // TODO finish function.
    }

    public void goToScore() {
        target = score;
    }
    public void goToScoreWithOffset() {
        target = score + scoreOffset;
    }

    public void goToIntake() {
        target = intake;
    }

    public void update() {
        pivot.setPosition(target);
    }

    public String getTelemetry() {
        return "";
    }
}
