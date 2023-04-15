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

        Pose2d startOP =new Pose2d(-34, -59.5, Math.toRadians(90));


        // Declare a MeepMeep instance
        // With a field size of 800 pixels
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity autonomousOP = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, 60, 60, Math.toRadians(360))
                .setDimensions(15.5,16)
                .setStartPose(startOP)
                .followTrajectorySequence(drive -> {
                            return drive.trajectorySequenceBuilder(startOP)
                                    .lineToSplineHeading(new Pose2d(-35, -1, Math.toRadians(90)))
                                    .lineToLinearHeading(new Pose2d(-32, -14.5, Math.toRadians(146)))
                                    .lineToSplineHeading(new Pose2d(-51, -8, Math.toRadians(180)))
                                    .lineToSplineHeading(new Pose2d(45,-8,Math.toRadians(0)))
                                    .splineToConstantHeading(new Vector2d(47, -9), Math.toRadians(0))






                                    .build();
                        }
                                );
        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_KAI_DARK)
                .addEntity(autonomousOP)
                .start();



    }


}
