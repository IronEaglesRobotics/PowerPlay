package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueLight;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
import com.noahbres.meepmeep.roadrunner.trajectorysequence.TrajectorySequence;

public class MeepMeepTesting {
    public static void main(String[] args) {

        Pose2d startOP = new Pose2d(-34,-60,Math.toRadians(90));
        Pose2d pos1 = new Pose2d(-34,-36, Math.toRadians(90));


        // Declare a MeepMeep instance
        // With a field size of 800 pixels
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity autonomousOP = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, 60, 60, Math.toRadians(180))
                .setDimensions(16,16)
                .setStartPose(startOP)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startOP)
                                .forward(12)
                                .splineTo(new Vector2d(-24, -36), Math.toRadians(0))
                                .splineTo(new Vector2d(-11, -34), Math.toRadians(45))
                                .forward(2)
                                .back(2)
                                .splineTo(new Vector2d(-24, -36), Math.toRadians(180))
                                .back(36)







                                .build());
        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_KAI_DARK)
                .addEntity(autonomousOP)
                .start();



    }


}
