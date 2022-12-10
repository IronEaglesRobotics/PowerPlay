package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.opmode.Alliance;

@TeleOp(name = "Blue TeleOp", group = "Competition")
public class BlueTeleOp extends AbstractTeleOp{
    public BlueTeleOp() {
        alliance = Alliance.BLUE;
    }
}
