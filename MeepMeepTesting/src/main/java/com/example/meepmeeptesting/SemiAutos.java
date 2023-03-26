package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class SemiAutos {
//    public RoadRunnerBotEntity rightHighBot;
//
//    Pose2d start = new Pose2d(-31.25,64,Math.toRadians(-90));
//    Pose2d pushCone = new Pose2d(-34, 24, Math.toRadians(-90));
//    Pose2d pushCone2 = new Pose2d(-22, 12, Math.toRadians(-90));
//    Pose2d scorePreload = new Pose2d(-8, 16, Math.toRadians(45));
//
//    Pose2d score = new Pose2d(-8, 16, Math.toRadians(45));
//    Pose2d intermediate = new Pose2d(-26, 12, Math.toRadians(0));
//    Pose2d load = new Pose2d(-56, 12, Math.toRadians(0));
//
//    Pose2d park1 = new Pose2d(-12, 12, Math.toRadians(0));
//
//    Pose2d park2 = new Pose2d(-36, 12, Math.toRadians(0));
//
//    Pose2d park3 = new Pose2d(-56, 12, Math.toRadians(0));

    public RoadRunnerBotEntity rightHighBot;

    Pose2d start = new Pose2d(-31.25,64,Math.toRadians(-90));
    Pose2d pushCone = new Pose2d(-34, 9, Math.toRadians(-90));
//    Pose2d pushCone2 = new Pose2d(-29, 13, Math.toRadians(-90));
    Pose2d scorePreload = new Pose2d(-24, 6, Math.toRadians(-90));

    Pose2d score = new Pose2d(-24, 6, Math.toRadians(-90));
    Pose2d intermediate = new Pose2d(-38, 12, Math.toRadians(0));
    Pose2d load = new Pose2d(-56, 12, Math.toRadians(0));

    Pose2d park1 = new Pose2d(-12, 12, Math.toRadians(0));

    Pose2d park2 = new Pose2d(-36, 12, Math.toRadians(0));

    Pose2d park3 = new Pose2d(-56, 12, Math.toRadians(0));

    public RoadRunnerBotEntity lrightHighBot;

    Pose2d lstart = new Pose2d(31.25,63,Math.toRadians(-90));
    Pose2d lpushCone = new Pose2d(36, 12, Math.toRadians(-90));
//    Pose2d lpushCone2 = new Pose2d(29, 13, Math.toRadians(-90));
    Pose2d lscorePreload = new Pose2d(28, 19, Math.toRadians(135));

    Pose2d lscore = new Pose2d(28, 19, Math.toRadians(135));
    Pose2d lintermediate = new Pose2d(48, 11.5, Math.toRadians(180));
    Pose2d lload = new Pose2d(59.5, 11.5, Math.toRadians(180));

    Pose2d lpark1 = new Pose2d(12, 12, Math.toRadians(180));

    Pose2d lpark2 = new Pose2d(36, 12, Math.toRadians(180));

    Pose2d lpark3 = new Pose2d(56, 12, Math.toRadians(180));

    public SemiAutos(MeepMeep meepMeep) {

        rightHighBot = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(50, 35, 180, 180, 11)
                .setDimensions(13.5,13.5)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(start)
                        // preload
                        .lineToLinearHeading(pushCone)
                        .setReversed(false)
                        .splineToSplineHeading(scorePreload, Math.toRadians(45))
//                        .splineToConstantHeading(new Vector2d(pushCone2.getX(), pushCone2.getY()), Math.toRadians(-90))
//                        .splineToConstantHeading(new Vector2d(scorePreload.getX(), scorePreload.getY()), Math.toRadians(-90))
//                        .setReversed(false)
//                        .splineToSplineHeading(scorePreload, Math.toRadians(45))

                        // cone stack
                        .setReversed(true)
                        .splineToSplineHeading(intermediate, Math.toRadians(180))
                        .lineToSplineHeading(load)
                        .setReversed(false)
                        .lineToSplineHeading(intermediate)
                        .splineToSplineHeading(score, Math.toRadians(-90))
//
                        // parks
//                        .setReversed(true)
//                        .back(4)
//                        .lineToSplineHeading(park1)

//                        .setReversed(true)
//                        .splineToSplineHeading(intermediate, Math.toRadians(180))
//                        .lineToSplineHeading(park2)

                        .setReversed(true)
                        .splineToSplineHeading(intermediate, Math.toRadians(180))
                        .lineToSplineHeading(park3)

                        .build());

        lrightHighBot = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(73, 46, 180, 180, 11)
                .setDimensions(13.5,13.5)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(-9,18,Math.toRadians(30)))
                        // preload
                        .setReversed(true)
                        .splineToSplineHeading(new Pose2d(-32,10,0), Math.toRadians(180))
                        .lineToLinearHeading(new Pose2d(-59, 10, 0))
//                        .setReversed(false)
////                        .splineToConstantHeading(new Vector2d(lpushCone2.getX(), lpushCone2.getY()), Math.toRadians(-90))
////                        .splineToConstantHeading(new Vector2d(lscorePreload.getX(), lscorePreload.getY()), Math.toRadians(-90))
////                        .setReversed(false)
//                        .splineToSplineHeading(lscorePreload, Math.toRadians(135))
////                        .splineToSplineHeading(lscorePreload, Math.toRadians(-135))
//
//                        // cone stack
//                        .setReversed(true)
//                        .splineToSplineHeading(lintermediate, Math.toRadians(0))
//                        .lineToSplineHeading(lload)
//                        .setReversed(false)
//                        .lineToSplineHeading(lintermediate)
//                        .splineToSplineHeading(lscore, Math.toRadians(-135))
//
                        // parks
//                        .setReversed(true)
//                        .back(4)
//                        .lineToSplineHeading(park1)

//                        .setReversed(true)
//                        .splineToSplineHeading(intermediate, Math.toRadians(180))
//                        .lineToSplineHeading(park2)

//                        .setReversed(true)
//                        .splineToSplineHeading(lintermediate, Math.toRadians(180))
//                        .lineToSplineHeading(lpark3)

                        .build());

//        rightHighBot = new DefaultBotBuilder(meepMeep)
//                .setColorScheme(new ColorSchemeRedDark())
//                .setConstraints(80, 45, 120, 120, 11)
//                .setDimensions(13.5,13.5)
//                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(start)
//                        // preload
//                        .lineToLinearHeading(pushCone)
//                        .splineToSplineHeading(pushCone2, Math.toRadians(0))
////                        .setReversed(false)
//                        .splineToSplineHeading(scorePreload, Math.toRadians(45))
//
////                        // cone stack
////                        .setReversed(true)
////                        .splineToSplineHeading(intermediate, Math.toRadians(180))
////                        .lineToSplineHeading(load)
////                        .setReversed(false)
////                        .lineToSplineHeading(intermediate)
////                        .splineToSplineHeading(score, Math.toRadians(45))
////
//                        // parks
////                        .setReversed(true)
////                        .back(4)
////                        .lineToSplineHeading(park1)
//
////                        .setReversed(true)
////                        .splineToSplineHeading(intermediate, Math.toRadians(180))
////                        .lineToSplineHeading(park2)
//
//                        .setReversed(true)
//                        .splineToSplineHeading(intermediate, Math.toRadians(180))
//                        .lineToSplineHeading(park3)
//
//                        .build());


    }
}