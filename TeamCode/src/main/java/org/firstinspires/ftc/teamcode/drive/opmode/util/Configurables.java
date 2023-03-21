package org.firstinspires.ftc.teamcode.drive.opmode.util;

import com.acmerobotics.dashboard.config.Config;

@Config
public class Configurables {
    //Drive
    public static double GO_SLOW = 1.4;
    public static double WHY_TURN = 1.25;


    // Slide
    public static double SLIDE_POWER_UP = 1.0;
    public static double SLIDE_POWER_DOWN = 0.001;
    public static int SLIDE_MAX = 3050;
    public static int SLIDE_MID = 1275;
    public static int SLIDE_MID_TELE = 2300;
    public static int LOW_DUNK = 1000;
    public static int SLIDE_HIGH = 2130;
    public static int STOP = 30;
    public static int LOW_JUNC = 1400;
    public static int AUTO_TOP1 = 380;
    public static int AUTO_TOP2 = 270;
    public static int AUTO_TOP3 = 160;
    public static int AUTO_TOP4 = 50;
    public static int AUTO_TOP5 = 0;

    // Claw
    public static double CLAW_CLOSED = 0.576;
    public static double CLAW_OPEN = 0.4;
    public static double CLAW_OPENWIDE = 0.23;
    public static double CLAW_UP = 0.02;
    public static double CLAW_DOWN = 0.675;
    public static double GO_OTHER_WAY = -100;
    public static double OTHER_WAY = 300;

    // Arm
    public static int ARM_UPRIGHT = 353;
    public static int ARM_LEFT = -690;
    public static int ARM_RIGHT = 1210;
    public static int ARM_SCORE = 690;
    public static int ARM_TILT = -200;
    public static int ARM_UPRIGHT_TELE = 0;
    public static int ARM_LEFT_TELE = -850;
    public static int ARM_RIGHT_TELE = 840;
    public static double ARM_POWER = 1;
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
