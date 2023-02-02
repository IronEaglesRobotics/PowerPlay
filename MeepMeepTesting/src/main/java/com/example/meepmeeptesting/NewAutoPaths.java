//package com.example.meepmeeptesting;
//
//import com.acmerobotics.roadrunner.geometry.Pose2d;
//import com.noahbres.meepmeep.MeepMeep;
//import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
//import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
//import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
//
//public class NewAutoPaths {
//    public RoadRunnerBotEntity blueHighBot;
//
//    Pose2d mid_start = new Pose2d(-30.5, 63.8, Math.toRadians(90));
//    Pose2d mid_intStart = new Pose2d(-36, 12, Math.toRadians(90));
//
//    Pose2d mid_score = new Pose2d(-32, 24, Math.toRadians(0));
//    Pose2d mid_int = new Pose2d(-42, 12, Math.toRadians(0));
//    Pose2d mid_load = new Pose2d(-63, 12, Math.toRadians(0));
//
//    Pose2d mid_park1 = new Pose2d(-32, 28, Math.toRadians(0));
//    Pose2d mid_park12 = new Pose2d(-12, 36, Math.toRadians(0));
//
//    Pose2d mid_park2 = new Pose2d(-36, 36, Math.toRadians(0));
//
//    Pose2d mid_park3 = new Pose2d(-42, 36, Math.toRadians(0));
//    Pose2d mid_park32 = new Pose2d(-63, 36, Math.toRadians(0));
//
//    public NewAutoPaths(MeepMeep meepMeep) {
//        blueHighBot = new DefaultBotBuilder(meepMeep)
//                .setColorScheme(new ColorSchemeRedDark())
//                .setConstraints(35, 35, 60, 60, 11)
//                .setDimensions(13.5, 13.5)
//                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(rmid_start)
//                        // preload
//                        .lineToLinearHeading(rmid_intStart)
//                        .setReversed(false)
//                        .lineToLinearHeading(rmid_score)
//
//                        .setReversed(true)
//                        .splineToLinearHeading(rmid_int, Math.toRadians(180))
//                        .lineToLinearHeading(rmid_load)
//                        .lineToLinearHeading(rmid_int)
//                        .splineToLinearHeading(rmid_score, Math.toRadians(0))
//
////                        .setReversed(true)
////                        .splineToLinearHeading(rmid_int, Math.toRadians(180))
////                        .lineToLinearHeading(rmid_load)
////                        .lineToLinearHeading(rmid_int)
////                        .splineToLinearHeading(rmid_score, Math.toRadians(0))
////
////                        .setReversed(true)
////                        .splineToLinearHeading(rmid_int, Math.toRadians(180))
////                        .lineToLinearHeading(rmid_load)
////                        .lineToLinearHeading(rmid_int)
////                        .splineToLinearHeading(rmid_score, Math.toRadians(0))
////
////                        .setReversed(true)
////                        .splineToLinearHeading(rmid_int, Math.toRadians(180))
////                        .lineToLinearHeading(rmid_load)
////                        .lineToLinearHeading(rmid_int)
////                        .splineToLinearHeading(rmid_score, Math.toRadians(0))
////
////                        .setReversed(true)
////                        .splineToLinearHeading(rmid_int, Math.toRadians(180))
////                        .lineToLinearHeading(rmid_load)
////                        .lineToLinearHeading(rmid_int)
////                        .splineToLinearHeading(rmid_score, Math.toRadians(0))
//
//                        //parks
////                        .setReversed(true)
////                        .lineToSplineHeading(mid_park1)
////                        .splineToSplineHeading(mid_park12, Math.toRadians(0))
//
////                        .lineToLinearHeading(mid_park2)
//
//                        .setReversed(true)
//                        .splineToLinearHeading(rmid_park3, Math.toRadians(180))
//                        .lineToLinearHeading(rmid_park32)
//
//                        .build());
//
//    }
//}
