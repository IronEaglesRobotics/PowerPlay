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
                                    .lineToSplineHeading(new Pose2d(-34,-17,Math.toRadians(150)))
                                    .waitSeconds(.5)
                                    .splineTo(new Vector2d(-54.5, -12),Math.toRadians(180))
                                    .waitSeconds(1.5)
                                    .setReversed(true)

                                    .splineTo(new Vector2d(-34,-17),Math.toRadians(-40))
                                    .waitSeconds(0.5)
                                    .setReversed(false)
                                    .splineTo(new Vector2d(-54.5, -12),Math.toRadians(180))
                                    .waitSeconds(1.5)
                                    .setReversed(true)

                                    .splineTo(new Vector2d(-34,-17),Math.toRadians(-40))
                                    .waitSeconds(0.5)
                                    .setReversed(false)
                                    .splineTo(new Vector2d(-54.5, -12),Math.toRadians(180))
                                    .waitSeconds(1.5)
                                    .setReversed(true)

                                    .splineTo(new Vector2d(-34,-17),Math.toRadians(-40))
                                    .waitSeconds(0.5)
                                    .setReversed(false)
                                    .splineTo(new Vector2d(-54.5, -12),Math.toRadians(180))
                                    .waitSeconds(1.5)
                                    .setReversed(true)

                                    .splineTo(new Vector2d(-34,-17),Math.toRadians(-40))
                                    .waitSeconds(0.5)
                                    .setReversed(false)
                                    .turn(Math.toRadians(50))




                                    .build();
                        }
                                );
        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_KAI_DARK)
                .addEntity(autonomousOP)
                .start();



    }


}
