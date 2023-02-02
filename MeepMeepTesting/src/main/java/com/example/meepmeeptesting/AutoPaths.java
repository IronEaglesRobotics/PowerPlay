package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class AutoPaths {
    public RoadRunnerBotEntity blueMidBot;

    Pose2d mid_start = new Pose2d(-30.5,63.8,Math.toRadians(90));
    Pose2d mid_intStart = new Pose2d(-36, 12, Math.toRadians(90));

    Pose2d mid_score = new Pose2d(-35, 24, Math.toRadians(0));
    Pose2d mid_score_line = new Pose2d(-32, 24, Math.toRadians(0));
    Pose2d mid_int = new Pose2d(-45, 12, Math.toRadians(0));
    Pose2d mid_load = new Pose2d(-63, 12, Math.toRadians(0));

    Pose2d mid_park1 = new Pose2d(-32, 28, Math.toRadians(0));
    Pose2d mid_park12 = new Pose2d(-12, 36, Math.toRadians(0));

    Pose2d mid_park2 = new Pose2d(-36, 36, Math.toRadians(0));

    Pose2d mid_park3 = new Pose2d(-42, 36, Math.toRadians(0));
    Pose2d mid_park32 = new Pose2d(-63, 36, Math.toRadians(0));

    public RoadRunnerBotEntity redMidBot;

    Pose2d rmid_start = new Pose2d(-30.5,-63.8,Math.toRadians(-90));
    Pose2d rmid_intStart = new Pose2d(-36, -12, Math.toRadians(-90));

    Pose2d rmid_score = new Pose2d(-32, -24, Math.toRadians(0));
    Pose2d rmid_int = new Pose2d(-42, -12, Math.toRadians(0));
    Pose2d rmid_load = new Pose2d(-63, -12, Math.toRadians(0));

    Pose2d rmid_park1 = new Pose2d(-32, -28, Math.toRadians(0));
    Pose2d rmid_park12 = new Pose2d(-12, -36, Math.toRadians(0));

    Pose2d rmid_park2 = new Pose2d(-36, -36, Math.toRadians(0));

    Pose2d rmid_park3 = new Pose2d(-42, -36, Math.toRadians(0));
    Pose2d rmid_park32 = new Pose2d(-63, -36, Math.toRadians(0));

    public RoadRunnerBotEntity redMidBot2;

    Pose2d rmid_start2 = new Pose2d(30.5,-63.8,Math.toRadians(-90));
    Pose2d rmid_intStart2 = new Pose2d(36, -12, Math.toRadians(-90));

    Pose2d rmid_score2 = new Pose2d(32, -24, Math.toRadians(180));
    Pose2d rmid_int2 = new Pose2d(42, -12, Math.toRadians(180));
    Pose2d rmid_load2 = new Pose2d(63, -12, Math.toRadians(180));

    Pose2d r2mid_park1 = new Pose2d(32, -28, Math.toRadians(180));
    Pose2d r2mid_park12 = new Pose2d(12, -36, Math.toRadians(180));

    Pose2d rmid_park22 = new Pose2d(36, -36, Math.toRadians(180));

    Pose2d r2mid_park3 = new Pose2d(42, -36, Math.toRadians(180));
    Pose2d r2mid_park32 = new Pose2d(63, -36, Math.toRadians(180));




    public RoadRunnerBotEntity blueCarryBot;

    Pose2d blue_start = new Pose2d(-36,66,Math.toRadians(90));
    Pose2d blue_intStart = new Pose2d(-36, 24, Math.toRadians(90));

    Pose2d blue_score1 = new Pose2d(-32, 8, Math.toRadians(-45));
    Pose2d blue_int1 = new Pose2d(-42, 12, Math.toRadians(0));
    Pose2d blue_load1 = new Pose2d(-66, 12, Math.toRadians(0));

    Pose2d blue_intInt = new Pose2d(-24, 12, Math.toRadians(0));

    Pose2d blue_load2 = new Pose2d(66, 12, Math.toRadians(0));
    Pose2d blue_int2 = new Pose2d(48, 12, Math.toRadians(0));
    Pose2d blue_score2 = new Pose2d(32, 1-8, Math.toRadians(-45));

    public RoadRunnerBotEntity redCarryBot;

    Pose2d red_start = new Pose2d(-36,66,Math.toRadians(90));
    Pose2d red_idle1 = new Pose2d(-36, 12, Math.toRadians(90));
    Pose2d red_idle2 = new Pose2d(36, 12, Math.toRadians(0));

    Vector2d red_load1 = new Vector2d(-66, 12);
    Vector2d red_score1 = new Vector2d(-24, 0);

    Vector2d red_load2 = new Vector2d(66, 12);
    Vector2d red_score2 = new Vector2d(24, 0);


    double vTime = 1;
    double hTime = 1;

    public AutoPaths(MeepMeep meepMeep) {
        redMidBot = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(35, 35, 60, 60, 11)
                .setDimensions(13.5,13.5)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(rmid_start)
                        // preload
                        .lineToLinearHeading(rmid_intStart)
                        .setReversed(false)
                        .lineToLinearHeading(rmid_score)

                        .setReversed(true)
                        .splineToLinearHeading(rmid_int, Math.toRadians(180))
                        .lineToLinearHeading(rmid_load)
                        .lineToLinearHeading(rmid_int)
                        .splineToLinearHeading(rmid_score, Math.toRadians(0))

//                        .setReversed(true)
//                        .splineToLinearHeading(rmid_int, Math.toRadians(180))
//                        .lineToLinearHeading(rmid_load)
//                        .lineToLinearHeading(rmid_int)
//                        .splineToLinearHeading(rmid_score, Math.toRadians(0))
//
//                        .setReversed(true)
//                        .splineToLinearHeading(rmid_int, Math.toRadians(180))
//                        .lineToLinearHeading(rmid_load)
//                        .lineToLinearHeading(rmid_int)
//                        .splineToLinearHeading(rmid_score, Math.toRadians(0))
//
//                        .setReversed(true)
//                        .splineToLinearHeading(rmid_int, Math.toRadians(180))
//                        .lineToLinearHeading(rmid_load)
//                        .lineToLinearHeading(rmid_int)
//                        .splineToLinearHeading(rmid_score, Math.toRadians(0))
//
//                        .setReversed(true)
//                        .splineToLinearHeading(rmid_int, Math.toRadians(180))
//                        .lineToLinearHeading(rmid_load)
//                        .lineToLinearHeading(rmid_int)
//                        .splineToLinearHeading(rmid_score, Math.toRadians(0))

                        //parks
//                        .setReversed(true)
//                        .lineToSplineHeading(mid_park1)
//                        .splineToSplineHeading(mid_park12, Math.toRadians(0))

//                        .lineToLinearHeading(mid_park2)

                        .setReversed(true)
                        .splineToLinearHeading(rmid_park3, Math.toRadians(180))
                        .lineToLinearHeading(rmid_park32)

                        .build());

        redMidBot2 = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(35, 35, 60, 60, 11)
                .setDimensions(13.5,13.5)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(rmid_start2)
                        // preload
                        .lineToLinearHeading(rmid_intStart2)
                        .setReversed(false)
                        .lineToLinearHeading(rmid_score2)

                        .setReversed(true)
                        .splineToLinearHeading(rmid_int2, Math.toRadians(0))
                        .lineToLinearHeading(rmid_load2)
                        .lineToLinearHeading(rmid_int2)
                        .splineToLinearHeading(rmid_score2, Math.toRadians(180))

//                        .setReversed(true)
//                        .splineToLinearHeading(rmid_int2, Math.toRadians(0))
//                        .lineToLinearHeading(rmid_load2)
//                        .lineToLinearHeading(rmid_int2)
//                        .splineToLinearHeading(rmid_score2, Math.toRadians(180))
//
//                        .setReversed(true)
//                        .splineToLinearHeading(rmid_int2, Math.toRadians(0))
//                        .lineToLinearHeading(rmid_load2)
//                        .lineToLinearHeading(rmid_int2)
//                        .splineToLinearHeading(rmid_score2, Math.toRadians(180))
//
//                        .setReversed(true)
//                        .splineToLinearHeading(rmid_int2, Math.toRadians(0))
//                        .lineToLinearHeading(rmid_load2)
//                        .lineToLinearHeading(rmid_int2)
//                        .splineToLinearHeading(rmid_score2, Math.toRadians(180))
//
//                        .setReversed(true)
//                        .splineToLinearHeading(rmid_int2, Math.toRadians(0))
//                        .lineToLinearHeading(rmid_load2)
//                        .lineToLinearHeading(rmid_int2)
//                        .splineToLinearHeading(rmid_score2, Math.toRadians(180))


                        //parks
//                        .setReversed(true)
//                        .lineToSplineHeading(mid_park1)
//                        .splineToSplineHeading(mid_park12, Math.toRadians(0))

//                        .lineToLinearHeading(mid_park2)

                        .setReversed(true)
                        .splineToLinearHeading(r2mid_park3, Math.toRadians(0))
                        .lineToLinearHeading(r2mid_park32)

                        .build());


        blueMidBot = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(35, 35, 60, 60, 11)
                .setDimensions(13.5,13.5)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(mid_start)
                        // preload
                        .lineToLinearHeading(mid_intStart)
                        .setReversed(false)
                        .lineToLinearHeading(mid_score)

//                        .lineToLinearHeading(mid_int)
//                        .splineToLinearHeading(mid_load, Math.toRadians(180))
//                        .setReversed(false)
//                        .splineToLinearHeading(mid_int, Math.toRadians(0))
//                        .lineToLinearHeading(mid_score)

                        .setReversed(true)
                        .splineToLinearHeading(mid_int, Math.toRadians(180))
                        .lineToLinearHeading(mid_load)
                        .lineToLinearHeading(mid_int)
                        .splineToLinearHeading(mid_score, Math.toRadians(0))
                        .lineToLinearHeading(mid_score_line)

//                        .setReversed(true)
//                        .splineToLinearHeading(mid_int, Math.toRadians(180))
//                        .lineToLinearHeading(mid_load)
//                        .lineToLinearHeading(mid_int)
//                        .splineToLinearHeading(mid_score, Math.toRadians(0))
//
//                        .setReversed(true)
//                        .splineToLinearHeading(mid_int, Math.toRadians(180))
//                        .lineToLinearHeading(mid_load)
//                        .lineToLinearHeading(mid_int)
//                        .splineToLinearHeading(mid_score, Math.toRadians(0))
//
//                        .setReversed(true)
//                        .splineToLinearHeading(mid_int, Math.toRadians(180))
//                        .lineToLinearHeading(mid_load)
//                        .lineToLinearHeading(mid_int)
//                        .splineToLinearHeading(mid_score, Math.toRadians(0))
//
//                        .setReversed(true)
//                        .splineToLinearHeading(mid_int, Math.toRadians(180))
//                        .lineToLinearHeading(mid_load)
//                        .lineToLinearHeading(mid_int)
//                        .splineToLinearHeading(mid_score, Math.toRadians(0))

                        //parks
//                        .setReversed(true)
//                        .lineToSplineHeading(mid_park1)
//                        .splineToSplineHeading(mid_park12, Math.toRadians(0))

//                        .lineToLinearHeading(mid_park2)

                        .setReversed(true)
                        .splineToLinearHeading(mid_park3, Math.toRadians(180))
                        .lineToLinearHeading(mid_park32)

                        .build());
        redCarryBot = new DefaultBotBuilder(meepMeep)
            .setColorScheme(new ColorSchemeBlueDark())
            .setConstraints(60, 60, 60, 60, 10)
            .setDimensions(13.5,13.5)
            .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(red_start)
                // preload
                .lineToSplineHeading(red_idle1)
                .addTemporalMarker(() -> meepMeep.requestToAddEntity(new SlideEntity(meepMeep, red_idle1.vec(), red_score1, vTime)))
                .addTemporalMarker(() -> meepMeep.requestToAddEntity(new SlideEntity(meepMeep, red_idle1.vec(), red_load1, hTime)))
                .waitSeconds(vTime+hTime)

                // 1st 5 stack
                .addTemporalMarker(() -> meepMeep.requestToAddEntity(new SlideEntity(meepMeep, red_idle1.vec(), red_score1, vTime)))
                .addTemporalMarker(() -> meepMeep.requestToAddEntity(new SlideEntity(meepMeep, red_idle1.vec(), red_load1, hTime)))
                .waitSeconds(vTime+hTime)
                .addTemporalMarker(() -> meepMeep.requestToAddEntity(new SlideEntity(meepMeep, red_idle1.vec(), red_score1, vTime)))
                .addTemporalMarker(() -> meepMeep.requestToAddEntity(new SlideEntity(meepMeep, red_idle1.vec(), red_load1, hTime)))
                .waitSeconds(vTime+hTime)
                .addTemporalMarker(() -> meepMeep.requestToAddEntity(new SlideEntity(meepMeep, red_idle1.vec(), red_score1, vTime)))
                .addTemporalMarker(() -> meepMeep.requestToAddEntity(new SlideEntity(meepMeep, red_idle1.vec(), red_load1, hTime)))
                .waitSeconds(vTime+hTime)
                .addTemporalMarker(() -> meepMeep.requestToAddEntity(new SlideEntity(meepMeep, red_idle1.vec(), red_score1, vTime)))
                .addTemporalMarker(() -> meepMeep.requestToAddEntity(new SlideEntity(meepMeep, red_idle1.vec(), red_load1, hTime)))
                .waitSeconds(vTime+hTime)
                .addTemporalMarker(() -> meepMeep.requestToAddEntity(new SlideEntity(meepMeep, red_idle1.vec(), red_score1, vTime)))
//                .addTemporalMarker(() -> meepMeep.requestToAddEntity(new SlideEntity(meepMeep, red_idle1.vec(), red_load1, hTime)))
                .waitSeconds(vTime+hTime)

                // 2nd 5 stack
                .lineToSplineHeading(red_idle2)
                .addTemporalMarker(() -> meepMeep.requestToAddEntity(new SlideEntity(meepMeep, red_idle2.vec(), red_load2, hTime)))
                .waitSeconds(vTime+hTime)
                .addTemporalMarker(() -> meepMeep.requestToAddEntity(new SlideEntity(meepMeep, red_idle2.vec(), red_score2, vTime)))
                .addTemporalMarker(() -> meepMeep.requestToAddEntity(new SlideEntity(meepMeep, red_idle2.vec(), red_load2, hTime)))
                .waitSeconds(vTime+hTime)
                .addTemporalMarker(() -> meepMeep.requestToAddEntity(new SlideEntity(meepMeep, red_idle2.vec(), red_score2, vTime)))
                .addTemporalMarker(() -> meepMeep.requestToAddEntity(new SlideEntity(meepMeep, red_idle2.vec(), red_load2, hTime)))
                .waitSeconds(vTime+hTime)
                .addTemporalMarker(() -> meepMeep.requestToAddEntity(new SlideEntity(meepMeep, red_idle2.vec(), red_score2, vTime)))
                .addTemporalMarker(() -> meepMeep.requestToAddEntity(new SlideEntity(meepMeep, red_idle2.vec(), red_load2, hTime)))
                .waitSeconds(vTime+hTime)
                .addTemporalMarker(() -> meepMeep.requestToAddEntity(new SlideEntity(meepMeep, red_idle2.vec(), red_score2, vTime)))
                .addTemporalMarker(() -> meepMeep.requestToAddEntity(new SlideEntity(meepMeep, red_idle2.vec(), red_load2, hTime)))
                .waitSeconds(vTime+hTime)
                .addTemporalMarker(() -> meepMeep.requestToAddEntity(new SlideEntity(meepMeep, red_idle2.vec(), red_score2, vTime)))
//                .addTemporalMarker(() -> meepMeep.requestToAddEntity(new SlideEntity(meepMeep, red_idle2.vec(), red_load2, hTime)))
                .waitSeconds(vTime+hTime)

                .build());

        blueCarryBot = new DefaultBotBuilder(meepMeep)
            .setColorScheme(new ColorSchemeRedDark())
            .setConstraints(60, 60, 60, 60, 11)
            .setDimensions(13,13)
            .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(blue_start)
                // preload
                .lineToSplineHeading(blue_intStart)
                .splineToSplineHeading(blue_score1, Math.toRadians(-45))
                .setReversed(true)

                // 1st 5 stack
                .splineToSplineHeading(blue_int1, Math.toRadians(180))
                .lineToSplineHeading(blue_load1)
                .lineToSplineHeading(blue_int1)
                .splineToSplineHeading(blue_score1, Math.toRadians(-45))
                .setReversed(true)
                .splineToSplineHeading(blue_int1, Math.toRadians(180))
                .lineToSplineHeading(blue_load1)
                .lineToSplineHeading(blue_int1)
                .splineToSplineHeading(blue_score1, Math.toRadians(-45))
                .setReversed(true)
                .splineToSplineHeading(blue_int1, Math.toRadians(180))
                .lineToSplineHeading(blue_load1)
                .lineToSplineHeading(blue_int1)
                .splineToSplineHeading(blue_score1, Math.toRadians(-45))
                .setReversed(false)
                .splineToSplineHeading(blue_int1, Math.toRadians(180))
                .lineToSplineHeading(blue_load1)
                .lineToSplineHeading(blue_int1)
                .splineToSplineHeading(blue_score1, Math.toRadians(-45))
                .setReversed(false)
                .splineToSplineHeading(blue_int1, Math.toRadians(180))
                .lineToSplineHeading(blue_load1)
                .lineToSplineHeading(blue_int1)
                .splineToSplineHeading(blue_score1, Math.toRadians(-45))
                .setReversed(false)

//                // 2nd 5 stack
//                .splineToSplineHeading(blue_intInt, Math.toRadians(0))
//                .lineToSplineHeading(blue_load2)
//                .lineToSplineHeading(blue_int2)
//                .splineToSplineHeading(blue_score2, Math.toRadians(135))
//                .setReversed(false)
//                .splineToSplineHeading(blue_int2, Math.toRadians(0))
//                .lineToSplineHeading(blue_load2)
//                .lineToSplineHeading(blue_int2)
//                .splineToSplineHeading(blue_score2, Math.toRadians(135))
//                .setReversed(false)
//                .splineToSplineHeading(blue_int2, Math.toRadians(0))
//                .lineToSplineHeading(blue_load2)
//                .lineToSplineHeading(blue_int2)
//                .splineToSplineHeading(blue_score2, Math.toRadians(135))
//                .setReversed(false)
//                .splineToSplineHeading(blue_int2, Math.toRadians(0))
//                .lineToSplineHeading(blue_load2)
//                .lineToSplineHeading(blue_int2)
//                .splineToSplineHeading(blue_score2, Math.toRadians(135))
//                .setReversed(false)
//                .splineToSplineHeading(blue_int2, Math.toRadians(0))
//                .lineToSplineHeading(blue_load2)
//                .lineToSplineHeading(blue_int2)
//                .splineToSplineHeading(blue_score2, Math.toRadians(135))
//                .setReversed(false)

                .build()
            );
    }

}
