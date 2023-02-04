package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class SemiAutos {
    public RoadRunnerBotEntity rightHighBot;

    Pose2d start = new Pose2d(-31.25,64,Math.toRadians(90));
    Pose2d pushCone = new Pose2d(-38, 11, Math.toRadians(90));
    Pose2d scorePreload = new Pose2d(-32, 19.5, Math.toRadians(30));
    Pose2d intermediatePreload = new Pose2d(-52, 12, Math.toRadians(0));

    Pose2d score = new Pose2d(-8, 19.5, Math.toRadians(30));
    Pose2d intermediate = new Pose2d(-26, 12, Math.toRadians(0));
    Pose2d load = new Pose2d(-56, 12, Math.toRadians(0));


    Pose2d park1 = new Pose2d(-12, 12, Math.toRadians(0));

    Pose2d park2 = new Pose2d(-36, 12, Math.toRadians(0));

    Pose2d park3 = new Pose2d(-56, 12, Math.toRadians(0));

    public SemiAutos(MeepMeep meepMeep) {
        rightHighBot = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(60, 40, 80, 80, 11)
                .setDimensions(13.5,13.5)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(start)
                        // preload
                        .lineToLinearHeading(pushCone)
//                        .setReversed(false)
                        .lineToLinearHeading(scorePreload)

                        // cone stack
                        .setReversed(true)
                        .splineToSplineHeading(intermediatePreload, Math.toRadians(180))
                        .lineToSplineHeading(load)
                        .setReversed(false)
                        .lineToSplineHeading(intermediate)
                        .splineToSplineHeading(score, Math.toRadians(30))

                        .setReversed(true)
                        .splineToSplineHeading(intermediatePreload, Math.toRadians(180))
                        .lineToSplineHeading(load)
                        .setReversed(false)
                        .lineToSplineHeading(intermediate)
                        .splineToSplineHeading(score, Math.toRadians(30))

                        .setReversed(true)
                        .splineToSplineHeading(intermediatePreload, Math.toRadians(180))
                        .lineToSplineHeading(load)
                        .setReversed(false)
                        .lineToSplineHeading(intermediate)
                        .splineToSplineHeading(score, Math.toRadians(30))

                        .setReversed(true)
                        .splineToSplineHeading(intermediatePreload, Math.toRadians(180))
                        .lineToSplineHeading(load)
                        .setReversed(false)
                        .lineToSplineHeading(intermediate)
                        .splineToSplineHeading(score, Math.toRadians(30))

                        .setReversed(true)
                        .splineToSplineHeading(intermediatePreload, Math.toRadians(180))
                        .lineToSplineHeading(load)
                        .setReversed(false)
                        .lineToSplineHeading(intermediate)
                        .splineToSplineHeading(score, Math.toRadians(30))

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
    }
}