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
                .setDimensions(15.5,16)
                .setStartPose(startOP)
                .followTrajectorySequence(drive -> {
                            return drive.trajectorySequenceBuilder(startOP)

                                    //five cone
//                                    .lineToSplineHeading(new Pose2d(56, -12, Math.toRadians(180)))
//                                    .lineToSplineHeading(new Pose2d(30, -16, Math.toRadians(225)))
//                                    .lineToSplineHeading(new Pose2d(56, -12, Math.toRadians(180)))
//                                    .lineToSplineHeading(new Pose2d(30, -16, Math.toRadians(225)))
//                                    .lineToSplineHeading(new Pose2d(56, -12, Math.toRadians(180)))
//                                    .lineToSplineHeading(new Pose2d(30, -16, Math.toRadians(225)))
//                                    .lineToSplineHeading(new Pose2d(56, -12, Math.toRadians(180)))
//                                    .lineToSplineHeading(new Pose2d(30, -16, Math.toRadians(225)))
//                                    .lineToSplineHeading(new Pose2d(36, -12, Math.toRadians(180)))
//                                    .lineToSplineHeading(new Pose2d(12, -12, Math.toRadians(180)))

                                    //test

                                    .forward(60)
                                    .back(6)
                                    .setReversed(true)
                                    .waitSeconds(0.5)
                                    .splineTo(new Vector2d(-34, -10), Math.toRadians(-30))
                                    .setReversed(false)
////                                    .turn(Math.toRadians(-45))
////                                    .back(6)

//                                    .splineTo(new Vector2d(40,-12), Math.toRadians(0))
//                                    .lineToSplineHeading(new Pose2d(54, -12, Math.toRadians(0)))
//                                    .setReversed(true)
//                                    .lineToSplineHeading(new Pose2d(40, -12, Math.toRadians(0)))
//                                    .splineTo(new Vector2d(36, -13), Math.toRadians(-135))
//                                    .setReversed(false)
                                    .build();
                        }
                                );
        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_KAI_DARK)
                .addEntity(autonomousOP)
                .start();



    }


}
