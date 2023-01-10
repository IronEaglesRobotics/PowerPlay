package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class AutoPaths {
    public RoadRunnerBotEntity blueCarryBot;

    Pose2d blue_start = new Pose2d(-36,-66,Math.toRadians(180));
    Pose2d blue_intStart = new Pose2d(-36, -24, Math.toRadians(180));

    Pose2d blue_score1 = new Pose2d(-32, -8, Math.toRadians(-135));
    Pose2d blue_int1 = new Pose2d(-48, -12, Math.toRadians(180));
    Pose2d blue_load1 = new Pose2d(-66, -12, Math.toRadians(180));

    Pose2d blue_intInt = new Pose2d(-24, -12, Math.toRadians(0));

    Pose2d blue_load2 = new Pose2d(66, -12, Math.toRadians(0));
    Pose2d blue_int2 = new Pose2d(48, -12, Math.toRadians(0));
    Pose2d blue_score2 = new Pose2d(32, 1-8, Math.toRadians(-45));

    public RoadRunnerBotEntity redCarryBot;

    Pose2d red_start = new Pose2d(-36,66,Math.toRadians(180));
    Pose2d red_idle1 = new Pose2d(-36, 12, Math.toRadians(180));
    Pose2d red_idle2 = new Pose2d(36, 12, Math.toRadians(0));

    Vector2d red_load1 = new Vector2d(-66, 12);
    Vector2d red_score1 = new Vector2d(-24, 0);

    Vector2d red_load2 = new Vector2d(66, 12);
    Vector2d red_score2 = new Vector2d(24, 0);


    double vTime = 1;
    double hTime = 1;

    public AutoPaths(MeepMeep meepMeep) {
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
            .setConstraints(70, 90, 70, 90, 10)
            .setDimensions(12,12)
            .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(blue_start)
                // preload
                .lineToSplineHeading(blue_intStart)
                .splineToSplineHeading(blue_score1, Math.toRadians(45))
                .setReversed(false)

                // 1st 5 stack
                .splineToSplineHeading(blue_int1, Math.toRadians(180))
                .lineToSplineHeading(blue_load1)
                .lineToSplineHeading(blue_int1)
                .splineToSplineHeading(blue_score1, Math.toRadians(45))
                .setReversed(false)
                .splineToSplineHeading(blue_int1, Math.toRadians(180))
                .lineToSplineHeading(blue_load1)
                .lineToSplineHeading(blue_int1)
                .splineToSplineHeading(blue_score1, Math.toRadians(45))
                .setReversed(false)
                .splineToSplineHeading(blue_int1, Math.toRadians(180))
                .lineToSplineHeading(blue_load1)
                .lineToSplineHeading(blue_int1)
                .splineToSplineHeading(blue_score1, Math.toRadians(45))
                .setReversed(false)
                .splineToSplineHeading(blue_int1, Math.toRadians(180))
                .lineToSplineHeading(blue_load1)
                .lineToSplineHeading(blue_int1)
                .splineToSplineHeading(blue_score1, Math.toRadians(45))
                .setReversed(false)
                .splineToSplineHeading(blue_int1, Math.toRadians(180))
                .lineToSplineHeading(blue_load1)
                .lineToSplineHeading(blue_int1)
                .splineToSplineHeading(blue_score1, Math.toRadians(45))
                .setReversed(false)

                // 2nd 5 stack
                .splineToSplineHeading(blue_intInt, Math.toRadians(0))
                .lineToSplineHeading(blue_load2)
                .lineToSplineHeading(blue_int2)
                .splineToSplineHeading(blue_score2, Math.toRadians(135))
                .setReversed(false)
                .splineToSplineHeading(blue_int2, Math.toRadians(0))
                .lineToSplineHeading(blue_load2)
                .lineToSplineHeading(blue_int2)
                .splineToSplineHeading(blue_score2, Math.toRadians(135))
                .setReversed(false)
                .splineToSplineHeading(blue_int2, Math.toRadians(0))
                .lineToSplineHeading(blue_load2)
                .lineToSplineHeading(blue_int2)
                .splineToSplineHeading(blue_score2, Math.toRadians(135))
                .setReversed(false)
                .splineToSplineHeading(blue_int2, Math.toRadians(0))
                .lineToSplineHeading(blue_load2)
                .lineToSplineHeading(blue_int2)
                .splineToSplineHeading(blue_score2, Math.toRadians(135))
                .setReversed(false)
                .splineToSplineHeading(blue_int2, Math.toRadians(0))
                .lineToSplineHeading(blue_load2)
                .lineToSplineHeading(blue_int2)
                .splineToSplineHeading(blue_score2, Math.toRadians(135))
                .setReversed(false)

                .build()
            );
    }

}
