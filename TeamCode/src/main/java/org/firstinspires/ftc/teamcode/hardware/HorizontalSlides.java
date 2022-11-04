package org.firstinspires.ftc.teamcode.hardware;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class HorizontalSlides {
    private Servo pivot;

    public static double intake = 0.75;
    public static double score = 0.1;

    private boolean isInIntake = true;

    public HorizontalSlides(HardwareMap hardwareMap) {
        pivot = hardwareMap.get(Servo.class, "pivot");

        goToIntake();
    }

    public void toggle() {
        if (isInIntake) {
            goToScore();
        } else {
            goToIntake();
        }
        isInIntake = !isInIntake;
    }

    public void update() {
        if (isInIntake) {
            goToIntake();
        } else {
            goToScore();
        }
    }


    public void goToScore() {
        pivot.setPosition(score);
    }

    public void goToIntake() {
        pivot.setPosition(intake);
    }

    public String getTelemetry() {
        return "";
    }
}
