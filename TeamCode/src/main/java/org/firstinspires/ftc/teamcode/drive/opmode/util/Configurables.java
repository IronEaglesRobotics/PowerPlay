package org.firstinspires.ftc.teamcode.drive.opmode.util;

import com.acmerobotics.dashboard.config.Config;

@Config
public class Configurables {
    //Drive
    public static double GO_SLOW = 1.4;
    public static double WHY_TURN = 1.25;


    // Slide
    public static double SLIDE_POWER_UP = 1.0;
    public static int SLIDE_MAX = 3200;
    public static int SLIDE_LOW = 2350;
    public static int LOW_DUNK = 1000;

    //    public static int SLIDE_MED = 3050;
//    public static int SLIDE_POSITION = 0;
    public static int dunk = 1400;
//    public static int twoCone = 3050;
//    public static int threeCone = 3050;
//    public static int CLEAR = 500;
    public static int STOP = 10;
    public static int LOW_JUNC = 1400;
    public static int AUTO_TOP = 480;
    public static int AUTO_TOP2 = 360;
    public static int AUTO_TOP3 = 280;
    public static int AUTO_TOP4 = 190;
    public static int AUTO_TOP5 = 50;
    public static int AUTO_TOP6 = 10;

    // Claw
    public static double CLAW_CLOSED = 0.459;
    public static double CLAW_OPEN = 0.26;
    public static double CLAW_UP = 0.02;
    public static double CLAW_DOWN = 0.675;
    public static double AUTO = 0.005;
    public static double CLAW_AUTO = 0.4;
    public static double GO_OTHER_WAY = -100;
    public static double OTHER_WAY = 300;

    // Arm
    public static int ARM_UPRIGHT = 353;
    public static int ARM_STARTING = 0;
    public static int ARM_LEFT = -520;
    public static int ARM_RIGHT = 1221;
    public static int ARM_UPRIGHT_TELE = 0;
    public static int ARM_LEFT_TELE = -884;
    public static int ARM_RIGHT_TELE = 912;
    public static double ARM_POWER = 0.7;
    public static double ARM_P = 1.0;
    public static double ARM_I = 0;
    public static double ARM_D = 0;

    //Auto
    public static int PARK_POSITION = 1;

    public static double AIMING_KP = 0.0011;
    public static double AIMING_KI = 0.015;
    public static double AIMING_KD = 0.00001;
    public static double AIMING_TOLERANCE = 15;
}
