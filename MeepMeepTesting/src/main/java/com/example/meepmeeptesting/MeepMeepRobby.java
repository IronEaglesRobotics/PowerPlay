package com.example.meepmeeptesting;

import com.noahbres.meepmeep.MeepMeep;

public class MeepMeepRobby {

    public static void main(String[] args) {

        MeepMeep meepMeep = new MeepMeep(900);

        OptimizationTesting optimizationTesting = new OptimizationTesting(meepMeep);
        AutoPaths autoPaths = new AutoPaths(meepMeep);

        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)

                .addEntity(optimizationTesting.turretBot)
                .addEntity(optimizationTesting.passthroughBot)
                .addEntity(optimizationTesting.driftBot)
//            .addEntity(autoPaths.blueCarryBot)
//            .addEntity(autoPaths.redCarryBot)

                .start();

    }
}

//package com.example.meepmeeptesting;
//
//        import com.acmerobotics.roadrunner.geometry.Pose2d;
//        import com.noahbres.meepmeep.MeepMeep;
//        import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
//        import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueLight;
//        import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
//        import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
//        import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
//
//public class MeepMeepTesting {
//
//    public static void main(String[] args) {
//        MeepMeep meepMeep = new MeepMeep(800);
//
//        Pose2d startTurret = new Pose2d(-36, -66, Math.toRadians(90));
//        Pose2d endTurret = new Pose2d(-36,-8, Math.toRadians(90));
//        RoadRunnerBotEntity turretBot = new DefaultBotBuilder(meepMeep)
//                .setColorScheme(new ColorSchemeRedDark())
//                .setConstraints(60, 60, 60, 60, Math.toRadians(180))
//                .setDimensions(12,12)
//                .followTrajectorySequence(drive ->
//                        drive.trajectorySequenceBuilder(startTurret)
//                                .lineToLinearHeading(endTurret)
//                                .lineToLinearHeading(startTurret)
//
//                                .lineToLinearHeading(endTurret)
//                                .lineToLinearHeading(startTurret)
//                                .lineToLinearHeading(endTurret)
//                                .lineToLinearHeading(startTurret)
//                                .lineToLinearHeading(endTurret)
//                                .lineToLinearHeading(startTurret)
//                                .lineToLinearHeading(endTurret)
//                                .lineToLinearHeading(startTurret)
//                                .build()
//                );
//
//
//        Pose2d startSimple = new Pose2d(-12, -66, Math.toRadians(90));
//        Pose2d intermediateSimple = new Pose2d(-12, -20, Math.toRadians(90));
//        Pose2d endSimple = new Pose2d(-16,-8, Math.toRadians(135));
//        RoadRunnerBotEntity simpleBot = new DefaultBotBuilder(meepMeep)
//                .setColorScheme(new ColorSchemeRedDark())
//                .setConstraints(60, 60, 60, 60, Math.toRadians(180))
//                .setDimensions(12,12)
//                .followTrajectorySequence(drive ->
//                        drive.trajectorySequenceBuilder(startSimple)
//                                .splineToSplineHeading(intermediateSimple, Math.toRadians(90))
//                                .splineToSplineHeading(endSimple, Math.toRadians(135))
//                                .setReversed(true)
//                                .splineToSplineHeading(intermediateSimple, Math.toRadians(-90))
//                                .splineToSplineHeading(startSimple, Math.toRadians(-90))
//                                .setReversed(false)
//
//                                .splineToSplineHeading(intermediateSimple, Math.toRadians(90))
//                                .splineToSplineHeading(endSimple, Math.toRadians(135))
//                                .setReversed(true)
//                                .splineToSplineHeading(intermediateSimple, Math.toRadians(-90))
//                                .splineToSplineHeading(startSimple, Math.toRadians(-90))
//                                .setReversed(false)
//                                .splineToSplineHeading(intermediateSimple, Math.toRadians(90))
//                                .splineToSplineHeading(endSimple, Math.toRadians(135))
//                                .setReversed(true)
//                                .splineToSplineHeading(intermediateSimple, Math.toRadians(-90))
//                                .splineToSplineHeading(startSimple, Math.toRadians(-90))
//                                .setReversed(false)
//                                .splineToSplineHeading(intermediateSimple, Math.toRadians(90))
//                                .splineToSplineHeading(endSimple, Math.toRadians(135))
//                                .setReversed(true)
//                                .splineToSplineHeading(intermediateSimple, Math.toRadians(-90))
//                                .splineToSplineHeading(startSimple, Math.toRadians(-90))
//                                .setReversed(false)
//                                .splineToSplineHeading(intermediateSimple, Math.toRadians(90))
//                                .splineToSplineHeading(endSimple, Math.toRadians(135))
//                                .setReversed(true)
//                                .splineToSplineHeading(intermediateSimple, Math.toRadians(-90))
//                                .splineToSplineHeading(startSimple, Math.toRadians(-90))
//                                .setReversed(false)
//                                .build()
//                );
//
//        Pose2d startCircle = new Pose2d(12, -66, Math.toRadians(90));
//        Pose2d intermediateCircle1 = new Pose2d(12, -48, Math.toRadians(90));
//        Pose2d intermediateCircle2 = new Pose2d(12, -24, Math.toRadians(90));
//        Pose2d intermediateCircle3 = new Pose2d(24, -12, Math.toRadians(0));
//        Pose2d intermediateCircle4 = new Pose2d(36, -24, Math.toRadians(-90));
//        Pose2d intermediateCircle5 = new Pose2d(36, -48, Math.toRadians(-90));
//        Pose2d intermediateCircle6 = new Pose2d(24, -60, Math.toRadians(180));
//        RoadRunnerBotEntity circleBot = new DefaultBotBuilder(meepMeep)
//                .setColorScheme(new ColorSchemeRedDark())
//                .setConstraints(60, 60, 60, 60, Math.toRadians(180))
//                .setDimensions(12,12)
//                .followTrajectorySequence(drive ->
//                                drive.trajectorySequenceBuilder(startCircle)
//                                        .splineToSplineHeading(intermediateCircle1, Math.toRadians(90))
//                                        .splineToSplineHeading(intermediateCircle2, Math.toRadians(90))
//                                        .splineToSplineHeading(intermediateCircle3, Math.toRadians(0))
//                                        .splineToSplineHeading(intermediateCircle4, Math.toRadians(-90))
//                                        .splineToSplineHeading(intermediateCircle5, Math.toRadians(-90))
//                                        .splineToSplineHeading(intermediateCircle6, Math.toRadians(180))
//
//                                        .splineToSplineHeading(intermediateCircle1, Math.toRadians(90))
//                                        .splineToSplineHeading(intermediateCircle2, Math.toRadians(90))
//                                        .splineToSplineHeading(intermediateCircle3, Math.toRadians(0))
//                                        .splineToSplineHeading(intermediateCircle4, Math.toRadians(-90))
//                                        .splineToSplineHeading(intermediateCircle5, Math.toRadians(-90))
//                                        .splineToSplineHeading(intermediateCircle6, Math.toRadians(180))
//                                        .splineToSplineHeading(intermediateCircle1, Math.toRadians(90))
//                                        .splineToSplineHeading(intermediateCircle2, Math.toRadians(90))
//                                        .splineToSplineHeading(intermediateCircle3, Math.toRadians(0))
//                                        .splineToSplineHeading(intermediateCircle4, Math.toRadians(-90))
//                                        .splineToSplineHeading(intermediateCircle5, Math.toRadians(-90))
//                                        .splineToSplineHeading(intermediateCircle6, Math.toRadians(180))
//                                        .splineToSplineHeading(intermediateCircle1, Math.toRadians(90))
//                                        .splineToSplineHeading(intermediateCircle2, Math.toRadians(90))
//                                        .splineToSplineHeading(intermediateCircle3, Math.toRadians(0))
//                                        .splineToSplineHeading(intermediateCircle4, Math.toRadians(-90))
//                                        .splineToSplineHeading(intermediateCircle5, Math.toRadians(-90))
//                                        .splineToSplineHeading(intermediateCircle6, Math.toRadians(180))
//                                        .splineToSplineHeading(intermediateCircle1, Math.toRadians(90))
//                                        .splineToSplineHeading(intermediateCircle2, Math.toRadians(90))
//                                        .splineToSplineHeading(intermediateCircle3, Math.toRadians(0))
//                                        .splineToSplineHeading(intermediateCircle4, Math.toRadians(-90))
//                                        .splineToSplineHeading(intermediateCircle5, Math.toRadians(-90))
//                                        .splineToSplineHeading(intermediateCircle6, Math.toRadians(180))
//                                        .splineToSplineHeading(intermediateCircle1, Math.toRadians(90))
////                                .splineToSplineHeading(endCircle, Math.toRadians(-90))
//                                        .build()
//                );
//
//        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_OFFICIAL)
//                .setDarkMode(true)
//                .setBackgroundAlpha(0.95f)
//                .addEntity(turretBot)
////                .addEntity(simpleBot)
//                .addEntity(simpleBot)
//                .addEntity(circleBot)
//                .start();
//
//    }
//}