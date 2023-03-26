package org.firstinspires.ftc.teamcode.hardware;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Config
public class Claw { // TODO done in theory, but need to get the actual servo positions &&&& do the Sensor stuff
    private Servo pincher;
    private Servo wrist;
//    private RevColorSensorV3 sensor;

    public enum Position {
        UPRIGHT, FLIPPED
    }

    public static double pincherOpen = 0.01;
    public static double pincherAutoClosed = 0.18;
    public static double pincherClosed = 0.2;
    public static double strongPincherClose = 0.21;
    public static double strongPincherOpen = 0.01;
    public static double wristUpright = 0.935;
    public static double wristFlipped = 0.27;
    public static double triggerDistance = 20; // mm

    public boolean isOpen = true;
    public boolean isUpright = true;
    public boolean strongClose = false;
    public boolean autoClose = false;
    public boolean strongOpen = true;

    private double wristTarget = 0.5;
    public static double wristSpeed = 0.02;

//    private double timeSinceOpened = 0;
    public boolean justOpened = false;

    public Claw(HardwareMap hardwareMap, Position pos) {
        pincher = hardwareMap.get(Servo.class, "claw");
        wrist = hardwareMap.get(Servo.class, "wrist");


//        sensor = hardwareMap.get(RevColorSensorV3.class, "trigger");

        if (pos == Position.UPRIGHT) {
            upright();
        } else {
            flipped();
        }
        update();
//        open();
//        upright();
    }

//    public double getTriggerDistance() {
//        return sensor.getDistance(DistanceUnit.MM);
//    }

    public void toggle() {
        // I don't use isOpen != isOpen because I want the open method to be called to start the timeout for the sensor
        if (isOpen) {
            close();
        } else {
            open();
        }
    }
    public void strongToggle() {
        if (isOpen) {
            strongClose();
        } else {
            open();
        }
    }

    public void flip() {
        isUpright = !isUpright;
        if (isUpright) {
            wristTarget = wristUpright;
        } else {
            wristTarget = wristFlipped;
        }
    }

    public void increaseWristTarget() {
        wristTarget += wristSpeed;
        wristTarget = Math.min(0.99, Math.max(0.01, wristTarget));
    }

    public void decreaseWristTarget() {
        wristTarget -= wristSpeed;
        wristTarget = Math.min(0.99, Math.max(0.01, wristTarget));
    }

    public void update() {
        wrist.setPosition(wristTarget);
//        if (isUpright) {
//            wrist.setPosition(wristUpright);
////            upright();
//        } else {
//            wrist.setPosition(wristFlipped);
////            flipped();
//        }

        if (isOpen) {
            pincher.setPosition(strongOpen ? strongPincherOpen : pincherOpen);
//            open();
        } else {
            pincher.setPosition(strongClose ? strongPincherClose : (autoClose ? pincherAutoClosed : pincherClosed));
//            close();
        }

//        if (justOpened) {
//            timeSinceOpened = runTime;
//            justOpened = false;
//        }
//
//        if (runTime - timeSinceOpened > 0.5) {
//            if (getTriggerDistance() < triggerDistance) {
//                close();
//                liftArm = true; //TODO make the slides or claw lift up with this
//            }
//        }
    }

    public void close() {
        isOpen = false;
        strongClose = false;
        autoClose = false;
    }

    public void strongClose() {
        isOpen = false;
        strongClose = true;
        autoClose = false;
    }

    public void autoClose() {
        isOpen = false;
        autoClose = true;
        strongClose = false;
    }

    public void open() {
        isOpen = true;
        strongOpen = false;
        justOpened = true;
    }

    public void strongOpen() {
        isOpen = true;
        strongOpen = true;
        justOpened = true;
    }

    public void upright() {
//        wrist.setPosition(wristUpright);
        isUpright = true;
        wristTarget = wristUpright;
    }

    public void flipped() {
//        wrist.setPosition(wristFlipped);
        isUpright = false;
        wristTarget = wristFlipped;
    }

    public String getTelemetry() {
//        return "Pincher: "+pincher.getPosition()+"\nRotator: "+wrist.getPosition()+"\nProximitySensor: "+getTriggerDistance();
        return "Pincher: "+pincher.getPosition()+"\nRotator: "+wrist.getPosition();
    }
}
