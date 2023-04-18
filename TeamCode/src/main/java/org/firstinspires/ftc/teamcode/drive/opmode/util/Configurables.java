package org.firstinspires.ftc.teamcode.drive.opmode.util;

import com.acmerobotics.dashboard.config.Config;

@Config
public class Configurables {
    //Drive
    public static double GO_SLOW = 1.4;
    public static double WHY_TURN = 1.25;

    // Slide
    public static double SCALE_FACTOR = 1;
    public static double SLIDE_POWER_UP = 1.0;
    public static double SLIDE_POWER_DOWN = 1;
    public static int SLIDE_MAX = (int)(3050 * SCALE_FACTOR);
    public static int SLIDE_MAX_AUTO = (int)(2050 * SCALE_FACTOR);
    public static int SLIDE_MID = (int)(2200 * SCALE_FACTOR);
    public static int SLIDE_MID_TELE = (int)(1200 * SCALE_FACTOR);
    public static int LOW_DUNK = (int)(1000 * SCALE_FACTOR);
    public static int SLIDE_HIGH = (int)(2130 * SCALE_FACTOR);
    public static int STOP = (int)(30 * SCALE_FACTOR);
    public static int LOW_JUNC = (int)(1300* SCALE_FACTOR);
    public static int AUTO_TOP1 = (int)(410 * SCALE_FACTOR);
    public static int AUTO_TOP2 = (int)(250 * SCALE_FACTOR);
    public static int AUTO_TOP3 = (int)(160 * SCALE_FACTOR);
    public static int AUTO_TOP4 = (int)(50 * SCALE_FACTOR);
    public static int AUTO_TOP5 = 0;
    public static int AUTOSCORE = SLIDE_MAX;
    public static int SLIDE_HIGH_AUTO = (int)(1985 * SCALE_FACTOR);
    public static int SLIDE_MID_AUTO = (int)(1150 * SCALE_FACTOR);
    public static int SLIDE_LOW_AUTO = (int)(1120 * SCALE_FACTOR);


    // Claw
    public static double CLAW_CLOSED = 0.6;
    public static double CLAW_OPEN = 0.4;
    public static double CLAW_OPENWIDE = 0.23;
    public static double CLAW_UP = 0.08;
    public static double CLAW_DOWN = 0.73;
    public static double GO_OTHER_WAY = -100;
    public static double OTHER_WAY = 300;

    // Arm
    public static int ARM_UPRIGHT = 200;
    public static int ARM_LEFT = -690;
    public static int ARM_RIGHT = 2000;
    public static int ARM_SCORE = 690;
    public static int ARM_TILT = -1000;
    public static int ARM_UPRIGHT_TELE = 0;
    public static int ARM_SCORE_TELE = -400;
    public static int ARM_LEFT_TELE = -900;
    public static int ARM_RIGHT_TELE = 900;
    public static double ARM_POWER = 1;
    public static double ARM_P = 1.0;
    public static double ARM_I = 0;
    public static double ARM_D = 0;
    public static int ARM_AUTO = -690;
    public static int SCORE_AUTO = -250;

    // WALE
    public static double DEPLOY = 0.685;
    public static double STOW = 0.3;

    //Auto
    public static int PARK_POSITION = 1;

    public static double AIMING_KP = 0.0011;
    public static double AIMING_KI = 0.015;
    public static double AIMING_KD = 0.00001;
    public static double AIMING_TOLERANCE = 15;
}
