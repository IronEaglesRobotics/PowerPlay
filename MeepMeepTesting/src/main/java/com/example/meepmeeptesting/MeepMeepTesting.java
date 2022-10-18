package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueLight;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {

    public static void main(String[] args) {

        Pose2d start1 = new Pose2d(-12,65.5,Math.toRadians(-90));
        Pose2d turretJ1 = new Pose2d(-12,12,Math.toRadians(-90));
        Pose2d start2 = new Pose2d(12,65.5,Math.toRadians(-90));
        Pose2d intermediate = new Pose2d(12,24,Math.toRadians(-90));
        Pose2d turretJ2 = new Pose2d(12,12,Math.toRadians(-135));

        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity turretBot = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, 60, 60, Math.toRadians(180))
                .setDimensions(13.5,13.5)
                .setStartPose(start1)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(start1)
                                .lineToLinearHeading(turretJ1)
                                .lineToLinearHeading(start1)
                                .build()
                        );
        RoadRunnerBotEntity simpleBot = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, 60, 60, Math.toRadians(180))
                .setDimensions(13.5,13.5)
                .setStartPose(start1)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(start2)
                                .splineToSplineHeading(turretJ2, Math.toRadians(-90))


                                .setReversed(true)
                                .splineToSplineHeading(start2, Math.toRadians(90))
//                                .lineToSplineHeading(intermediate)
//                                .splineToSplineHeading(new Pose2d(intermediate.getX()+0.001, intermediate.getY(), intermediate.getHeading()), Math.toRadians(-90))
//                                .lineToSplineHeading(turretJ2)
//                                .splineToSplineHeading(turretJ2, Math.toRadians(-90))

//                                .splineToSplineHeading(intermediate, Math.toRadians(-90))
//                                .splineToSplineHeading(start2, Math.toRadians(-90))
                                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(turretBot)
                .addEntity(simpleBot)
                .start();

    }
}