package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueLight;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {

    public static void main(String[] args) {
    //challenge1
        Pose2d start1 = new Pose2d(-12,60,Math.toRadians(-90));
        Pose2d servant1 = new Pose2d(-12,36,Math.toRadians(-90));
        Pose2d servant2 = new Pose2d(-36,36,Math.toRadians(-90));
        Pose2d servant3 = new Pose2d(-36,12,Math.toRadians(-90));
        Pose2d servant4 = new Pose2d(-12,12,Math.toRadians(-90));

    //challenge2
        Pose2d start2 = new Pose2d(12,60,Math.toRadians(-90));
        Pose2d simp1 = new Pose2d(12,12,Math.toRadians(-90));
        Pose2d simp2 = new Pose2d(36,12,Math.toRadians(-90));
        Pose2d simp3 = new Pose2d(36,36,Math.toRadians(-90));
        Pose2d simp4 = new Pose2d(-12,36,Math.toRadians(-90));
        Pose2d simp5 = new Pose2d(-12,60,Math.toRadians(-90));
        Pose2d simp6 = new Pose2d(-60,60,Math.toRadians(-90));

    //challenge3
        Pose2d start3 = new Pose2d(12,-60,Math.toRadians(90));
        Pose2d sus1 = new Pose2d(12,-36,Math.toRadians(90));
        Pose2d sus2 = new Pose2d(-12,-36,Math.toRadians(90));
        Pose2d sus3 = new Pose2d(-12,-12,Math.toRadians(90));
        Pose2d sus4 = new Pose2d(36,-12,Math.toRadians(90));
        Pose2d sus5 = new Pose2d(36,-60,Math.toRadians(90));
        Pose2d sus6 = new Pose2d(60,-60,Math.toRadians(90));
        Pose2d sus7 = new Pose2d(60,12,Math.toRadians(90));
        Pose2d sus8 = new Pose2d(-36,12,Math.toRadians(90));
        Pose2d sus9 = new Pose2d(-36,-60,Math.toRadians(90));

    //challenge4
        Pose2d start4 = new Pose2d(12,-60,Math.toRadians(90));
        Pose2d austin1 = new Pose2d(12,12,Math.toRadians(90));
        Pose2d austin2 = new Pose2d(-36,12,Math.toRadians(90));
        Pose2d austin3 = new Pose2d(-36,-12,Math.toRadians(90));
        Pose2d austin4 = new Pose2d(-60,-12,Math.toRadians(90));
        Pose2d austin5 = new Pose2d(-60,-60,Math.toRadians(90));
        Pose2d austin6 = new Pose2d(-12,-60,Math.toRadians(90));
        Pose2d austin7 = new Pose2d(-12,-12,Math.toRadians(90));
        Pose2d austin8 = new Pose2d(12,-12,Math.toRadians(90));

    //challenge5
        Pose2d start5 = new Pose2d(12,-60,Math.toRadians(90));
        Pose2d croissant1 = new Pose2d(60,-60,Math.toRadians(90));
        Pose2d croissant2 = new Pose2d(60,-36,Math.toRadians(90));
        Pose2d croissant3 = new Pose2d(36,-36,Math.toRadians(90));
        Pose2d croissant4 = new Pose2d(36,-12,Math.toRadians(90));
        Pose2d croissant5 = new Pose2d(12,-12,Math.toRadians(90));
        Pose2d croissant6 = new Pose2d(12,12,Math.toRadians(90));
        Pose2d croissant7 = new Pose2d(-60,12,Math.toRadians(90));
        Pose2d croissant8 = new Pose2d(-60,-36,Math.toRadians(90));
        Pose2d croissant9 = new Pose2d(-36,-36,Math.toRadians(90));
        Pose2d croissant10 = new Pose2d(-36,-12,Math.toRadians(90));
        Pose2d croissant11 = new Pose2d(-12,-12,Math.toRadians(90));
        Pose2d croissant12 = new Pose2d(-12,-36,Math.toRadians(90));
        Pose2d croissant13 = new Pose2d(12,-36,Math.toRadians(90));

    //challenge6
        Pose2d start6 = new Pose2d(12,-60,Math.toRadians(90));
        Pose2d optimusPrime1 = new Pose2d(12,-12,Math.toRadians(90));
        Pose2d optimusPrime2 = new Pose2d(36,-12,Math.toRadians(90));
        Pose2d optimusPrime3 = new Pose2d(36,12,Math.toRadians(90));
        Pose2d optimusPrime4 = new Pose2d(12,12,Math.toRadians(90));
        Pose2d optimusPrime5 = new Pose2d(12,36,Math.toRadians(90));
        Pose2d optimusPrime6 = new Pose2d(-12,36,Math.toRadians(90));
        Pose2d optimusPrime7 = new Pose2d(-12,12,Math.toRadians(90));
        Pose2d optimusPrime8 = new Pose2d(-36,12,Math.toRadians(90));
        Pose2d optimusPrime9 = new Pose2d(-36,-12,Math.toRadians(90));
        Pose2d optimusPrime10 = new Pose2d(-12,-12,Math.toRadians(90));
        Pose2d optimusPrime11 = new Pose2d(-12,-60,Math.toRadians(90));
        Pose2d optimusPrime12 = new Pose2d(60,-60,Math.toRadians(90));
        Pose2d optimusPrime13 = new Pose2d(60,-24,Math.toRadians(90));

    //challenge7
        Pose2d start7 = new Pose2d(-12,-60,Math.toRadians(90));
        Pose2d mario1 = new Pose2d(-12,12,Math.toRadians(90));
        Pose2d mario2 = new Pose2d(12,12,Math.toRadians(90));
        Pose2d mario3 = new Pose2d(12,-36,Math.toRadians(90));
        Pose2d mario4 = new Pose2d(36,-36,Math.toRadians(90));
        Pose2d mario5 = new Pose2d(36,36,Math.toRadians(90));
        Pose2d mario6 = new Pose2d(-36,36,Math.toRadians(90));
        Pose2d mario7 = new Pose2d(-36,-36,Math.toRadians(90));
        Pose2d mario8 = new Pose2d(-60,-36,Math.toRadians(90));
        Pose2d mario9 = new Pose2d(-60,60,Math.toRadians(90));
        Pose2d mario10 = new Pose2d(60,60,Math.toRadians(90));
        Pose2d mario11 = new Pose2d(60,-60,Math.toRadians(90));

    //challenge8
        Pose2d start8 = new Pose2d(12,-60,Math.toRadians(90));
        Pose2d docock1 = new Pose2d(12,-36,Math.toRadians(90));
        Pose2d docock2 = new Pose2d(36,-12,Math.toRadians(90));
        Pose2d docock3 = new Pose2d(36,12,Math.toRadians(90));
        Pose2d docock4 = new Pose2d(12,36,Math.toRadians(90));
        Pose2d docock5 = new Pose2d(-12,36,Math.toRadians(90));
        Pose2d docock6 = new Pose2d(-36,12,Math.toRadians(90));
        Pose2d docock7 = new Pose2d(-36,-12,Math.toRadians(90));
        Pose2d docock8 = new Pose2d(-12,-36,Math.toRadians(90));
        Pose2d docock9 = new Pose2d(-12,-60,Math.toRadians(90));

    //challenge9
        Pose2d start9 = new Pose2d(12,-60,Math.toRadians(90));
        Pose2d batman1 = new Pose2d(12,60,Math.toRadians(90));
        Pose2d batman2 = new Pose2d(-48,24,Math.toRadians(90));
        Pose2d batman3 = new Pose2d(-24,0,Math.toRadians(90));
        Pose2d batman4 = new Pose2d(-48,-24,Math.toRadians(90));

    //challenge10
        Pose2d start10 = new Pose2d(12,-60,Math.toRadians(90));
        Pose2d yourmom1 = new Pose2d(12,-36,Math.toRadians(90));
        Pose2d yourmom2 = new Pose2d(36,0,Math.toRadians(90));
        Pose2d yourmom3 = new Pose2d(0,36,Math.toRadians(90));
        Pose2d yourmom4 = new Pose2d(-36,0,Math.toRadians(90));
        Pose2d yourmom5 = new Pose2d(-12,-36,Math.toRadians(90));
        Pose2d yourmom6 = new Pose2d(-12,-60,Math.toRadians(90));
    //challenge12
        Pose2d start12 = new Pose2d(12,-60,Math.toRadians(90));
        Pose2d northKorea1 = new Pose2d(12,-36,Math.toRadians(90));
        Pose2d northKorea2 = new Pose2d(-12,-36,Math.toRadians(90));
        Pose2d northKorea3 = new Pose2d(-36,-12,Math.toRadians(90));
        Pose2d northKorea4 = new Pose2d(-36,12,Math.toRadians(90));
        Pose2d northKorea5 = new Pose2d(-12,36,Math.toRadians(90));
        Pose2d northKorea6 = new Pose2d(12,36,Math.toRadians(90));
        Pose2d northKorea7 = new Pose2d(36,12,Math.toRadians(90));
        Pose2d northKorea8 = new Pose2d(36,-12,Math.toRadians(90));
        Pose2d northKorea9 = new Pose2d(12,-36,Math.toRadians(90));
        Pose2d northKorea10 = new Pose2d(12,-60,Math.toRadians(90));
        Pose2d northKorea11 = new Pose2d(60,-60,Math.toRadians(90));
        Pose2d northKorea12 = new Pose2d(60,60,Math.toRadians(90));
        Pose2d northKorea13 = new Pose2d(-60,60,Math.toRadians(90));
        Pose2d northKorea14 = new Pose2d(-60,-60,Math.toRadians(90));
        Pose2d northKorea15 = new Pose2d(-12,-60,Math.toRadians(90));

    //challenge13
        Pose2d start13 = new Pose2d(-12,-60,Math.toRadians(90));
        Pose2d varoom1 = new Pose2d(36,-60,Math.toRadians(90));
        Pose2d varoom2 = new Pose2d(48,-48,Math.toRadians(90));
        Pose2d varoom3 = new Pose2d(-12,0,Math.toRadians(90));
        Pose2d varoom4 = new Pose2d(48,48,Math.toRadians(90));
        Pose2d varoom5 = new Pose2d(36,60,Math.toRadians(90));
        Pose2d varoom6 = new Pose2d(-48,0,Math.toRadians(90));

    //challenge14
        Pose2d start14 = new Pose2d(12,-60,Math.toRadians(90));
        Pose2d x1 = new Pose2d(12,-30,Math.toRadians(90));
        Pose2d x2 = new Pose2d(18,-30,Math.toRadians(90));
        Pose2d x3 = new Pose2d(18,-18,Math.toRadians(90));
        Pose2d x4 = new Pose2d(36,-18,Math.toRadians(90));
        Pose2d x5 = new Pose2d(36,18,Math.toRadians(90));
        Pose2d x6 = new Pose2d(18,18,Math.toRadians(90));
        Pose2d x7 = new Pose2d(18,30,Math.toRadians(90));
        Pose2d x8 = new Pose2d(-18,30,Math.toRadians(90));
        Pose2d x9 = new Pose2d(-18,18,Math.toRadians(90));
        Pose2d x10 = new Pose2d(-42,18,Math.toRadians(90));
        Pose2d x11 = new Pose2d(-42,6,Math.toRadians(90));
        Pose2d x12 = new Pose2d(-30,6,Math.toRadians(90));
        Pose2d x13 = new Pose2d(-30,-6,Math.toRadians(90));
        Pose2d x14 = new Pose2d(-42,-6,Math.toRadians(90));
        Pose2d x15 = new Pose2d(-42,-18,Math.toRadians(90));
        Pose2d x16 = new Pose2d(-6,-18,Math.toRadians(90));
        Pose2d x17 = new Pose2d(-6,-30,Math.toRadians(90));

    //challenge20
        Pose2d start20 = new Pose2d(12,-60,Math.toRadians(90));
        Pose2d y1 = new Pose2d(12,-36,Math.toRadians(90));
        Pose2d y2 = new Pose2d(12,36,Math.toRadians(90));
        Pose2d y3 = new Pose2d(-60,-36,Math.toRadians(90));
        Pose2d y4 = new Pose2d(48,0,Math.toRadians(90));
        Pose2d y5 = new Pose2d(-60,36,Math.toRadians(90));

    //autonomousOP
        Pose2d startOP = new Pose2d(-36,60,Math.toRadians(180));
        Pose2d rob1 = new Pose2d(-36,-12,Math.toRadians(-90));
        Pose2d rob2 = new Pose2d(-36,12,Math.toRadians(-90));
        Pose2d rob3 = new Pose2d(-12,12,Math.toRadians(-90));
        Pose2d rob4 = new Pose2d(-60,12,Math.toRadians(-90));
        Pose2d end1 = new Pose2d(-36,36,Math.toRadians(-90));
        Pose2d end2 = new Pose2d(-12,36,Math.toRadians(-90));
        Pose2d end3 = new Pose2d(-60,36,Math.toRadians(-90));
        Pose2d score = new Pose2d(-30,6,Math.toRadians(-45));
        Pose2d load = new Pose2d(-60,12,Math.toRadians(-90));
        Pose2d carry = new Pose2d(-36,12,Math.toRadians(180));
    //leftAuto
        Pose2d startAuto = new Pose2d(36,66, Math.toRadians(0));
        Pose2d startAuto2 = new Pose2d(36,24, Math.toRadians(0));
        Pose2d scoreAuto = new Pose2d(32,8,Math.toRadians(45));

        Pose2d sLoad1 = new Pose2d(48, 12, Math.toRadians(0));
        Pose2d sLoad2 = new Pose2d(48, 12, Math.toRadians(0));
        Pose2d sLoad3 = new Pose2d(48, 12, Math.toRadians(0));
        Pose2d sLoad4 = new Pose2d(48, 12, Math.toRadians(0));
        Pose2d sLoad5 = new Pose2d(48, 12, Math.toRadians(0));
        Pose2d lLoad1 = new Pose2d(63.5,12,Math.toRadians(0));
        Pose2d lLoad2 = new Pose2d(63.5,12,Math.toRadians(0));
        Pose2d lLoad3 = new Pose2d(63.5,12,Math.toRadians(0));
        Pose2d lLoad4 = new Pose2d(63.5,12,Math.toRadians(0));
        Pose2d lLoad5 = new Pose2d(63.5,12,Math.toRadians(0));

        Pose2d lScore1 = new Pose2d(48,12,Math.toRadians(0));
        Pose2d lScore2 = new Pose2d(48,12,Math.toRadians(0));
        Pose2d lScore3 = new Pose2d(48,12,Math.toRadians(0));
        Pose2d lScore4 = new Pose2d(48,12,Math.toRadians(0));
        Pose2d lScore5 = new Pose2d(48,12,Math.toRadians(0));
        Pose2d sScore1 = new Pose2d(32,8,Math.toRadians(45));
        Pose2d sScore2 = new Pose2d(32,8,Math.toRadians(45));
        Pose2d sScore3 = new Pose2d(32,8,Math.toRadians(45));
        Pose2d sScore4 = new Pose2d(32,8,Math.toRadians(45));
        Pose2d sScore5 = new Pose2d(32,8,Math.toRadians(45));
        Pose2d lPark1 = new Pose2d(36,36,Math.toRadians(-90));

        MeepMeep meepMeep = new MeepMeep(600);

        RoadRunnerBotEntity leftAuto = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, 60, 60, Math.toRadians(180))
                .setDimensions(12,12)
                .setStartPose(startOP)
                .followTrajectorySequence(drive ->
                                drive.trajectorySequenceBuilder(startAuto)
                                        .lineToSplineHeading(startAuto2)
                                        .splineToSplineHeading(scoreAuto, Math.toRadians(-135))
                                        .setReversed(false)
                                        .splineToSplineHeading(sLoad1, Math.toRadians(0))
                                        .lineToSplineHeading(lLoad1)
                                        .lineToSplineHeading(lScore1)
                                        .splineToSplineHeading(sScore1, Math.toRadians(-135))
                                        .setReversed(false)
                                        .splineToSplineHeading(sLoad2, Math.toRadians(0))
                                        .lineToSplineHeading(lLoad2)
                                        .lineToSplineHeading(lScore2)
                                        .splineToSplineHeading(sScore2, Math.toRadians(-135))
                                        .setReversed(false)
                                        .splineToSplineHeading(sLoad3, Math.toRadians(0))
                                        .lineToSplineHeading(lLoad3)
                                        .lineToSplineHeading(lScore3)
                                        .splineToSplineHeading(sScore3, Math.toRadians(-135))
                                        .setReversed(false)
                                        .splineToSplineHeading(sLoad4, Math.toRadians(0))
                                        .lineToSplineHeading(lLoad4)
                                        .lineToSplineHeading(lScore4)
                                        .splineToSplineHeading(sScore4, Math.toRadians(-135))
                                        .setReversed(false)
                                        .splineToSplineHeading(sLoad5, Math.toRadians(0))
                                        .lineToSplineHeading(lLoad5)
                                        .lineToSplineHeading(lScore5)
                                        .splineToSplineHeading(sScore5, Math.toRadians(-135))
                                        .lineToSplineHeading(lPark1)
                                        .build()
                );
        RoadRunnerBotEntity autonomousOP = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, 60, 60, Math.toRadians(180))
                .setDimensions(12,12)
                .setStartPose(startOP)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startOP)
                                .lineToSplineHeading(new Pose2d(-36,24, Math.toRadians(180)))
                                .splineToSplineHeading(new Pose2d(-32, 8, Math.toRadians(135)), Math.toRadians(-45))
                                .setReversed(false)
                                .splineToSplineHeading(new Pose2d(-48, 12, Math.toRadians(180)), Math.toRadians(180))
                                .lineToSplineHeading(new Pose2d(-60, 12, Math.toRadians(180)))
                                .lineToSplineHeading(new Pose2d(-48, 12, Math.toRadians(180)))
                                .splineToSplineHeading(new Pose2d(-32, 8, Math.toRadians(135)), Math.toRadians(-45))
                                .setReversed(false)
                                .splineToSplineHeading(new Pose2d(-48, 12, Math.toRadians(180)), Math.toRadians(180))
                                .lineToSplineHeading(new Pose2d(-60, 12, Math.toRadians(180)))
                                .lineToSplineHeading(new Pose2d(-48, 12, Math.toRadians(180)))
                                .splineToSplineHeading(new Pose2d(-32, 8, Math.toRadians(135)), Math.toRadians(-45))
                                .setReversed(false)
                                .splineToSplineHeading(new Pose2d(-48, 12, Math.toRadians(180)), Math.toRadians(180))
                                .lineToSplineHeading(new Pose2d(-60, 12, Math.toRadians(180)))
                                .lineToSplineHeading(new Pose2d(-48, 12, Math.toRadians(180)))
                                .splineToSplineHeading(new Pose2d(-32, 8, Math.toRadians(135)), Math.toRadians(-45))
                                .setReversed(false)
                                .splineToSplineHeading(new Pose2d(-48, 12, Math.toRadians(180)), Math.toRadians(180))
                                .lineToSplineHeading(new Pose2d(-60, 12, Math.toRadians(180)))
                                .lineToSplineHeading(new Pose2d(-48, 12, Math.toRadians(180)))
                                .splineToSplineHeading(new Pose2d(-32, 8, Math.toRadians(135)), Math.toRadians(-45))
                                .setReversed(false)
                                .splineToSplineHeading(new Pose2d(-48, 12, Math.toRadians(180)), Math.toRadians(180))
                                .lineToSplineHeading(new Pose2d(-60, 12, Math.toRadians(180)))
                                .lineToSplineHeading(new Pose2d(-48, 12, Math.toRadians(180)))
                                .splineToSplineHeading(new Pose2d(-32, 8, Math.toRadians(135)), Math.toRadians(-45))
                                .setReversed(false)
                                .splineToSplineHeading(new Pose2d(-36, 24, Math.toRadians(90)), Math.toRadians(90))
                                .lineToSplineHeading(new Pose2d(-36, 36, Math.toRadians(90)))
//                                .splineToSplineHeading(new Pose2d(-60, 12, Math.toRadians(180)), Math.toRadians(180))
//                                .lineToLinearHeading(rob1)
//                                .lineToLinearHeading(rob2)
//                                .lineToLinearHeading(score)
//                                .splineToSplineHeading(new Pose2d(-60, 12, Math.toRadians(180)), Math.toRadians(180))
//                                .splineToSplineHeading(new Pose2d(-36, 6, Math.toRadians(-45)), Math.toRadians(180))
//                                .splineToSplineHeading(new Pose2d(-60, 12, Math.toRadians(180)), Math.toRadians(180))
//                                .splineToSplineHeading(new Pose2d(-36, 6, Math.toRadians(-45)), Math.toRadians(180))
//                                .splineToSplineHeading(new Pose2d(-60, 12, Math.toRadians(180)), Math.toRadians(180))
//                                .splineToSplineHeading(new Pose2d(-36, 6, Math.toRadians(-45)), Math.toRadians(180))
//                                .splineToSplineHeading(new Pose2d(-60, 12, Math.toRadians(180)), Math.toRadians(180))
//                                .splineToSplineHeading(new Pose2d(-36, 6, Math.toRadians(-45)), Math.toRadians(180))
//                                .splineToSplineHeading(new Pose2d(-60, 12, Math.toRadians(180)), Math.toRadians(180))
//                                .splineToSplineHeading(new Pose2d(-36, 6, Math.toRadians(-45)), Math.toRadians(180))
//                                .splineToSplineHeading(new Pose2d(-60, 12, Math.toRadians(180)), Math.toRadians(180))
//                                .splineToSplineHeading(new Pose2d(-36, 6, Math.toRadians(-45)), Math.toRadians(180))
//                                .lineToLinearHeading(rob2)
//                                .lineToLinearHeading(end1)
                                .build()
                );
        RoadRunnerBotEntity myServant = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, 60, 60, Math.toRadians(180))
                .setDimensions(13.5,13.5)
                .setStartPose(start1)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(start1)
                                .lineToLinearHeading(servant1)
                                .lineToLinearHeading(servant2)
                                .lineToLinearHeading(servant3)
                                .lineToLinearHeading(servant4)
                                .lineToLinearHeading(start1)
                                .build()
                        );

        RoadRunnerBotEntity simpBot = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, 60, 60, Math.toRadians(180))
                .setDimensions(13.5,13.5)
                .setStartPose(start2)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(start2)
                                .lineToLinearHeading(simp1)
                                .lineToLinearHeading(simp2)
                                .lineToLinearHeading(simp3)
                                .lineToLinearHeading(simp4)
                                .lineToLinearHeading(simp5)
                                .lineToLinearHeading(simp6)
                                .turn(Math.toRadians(-90))
                                .build()
                );
        RoadRunnerBotEntity susBot = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, 60, 60, Math.toRadians(180))
                .setDimensions(13.5,13.5)
                .setStartPose(start3)
                .followTrajectorySequence(drive ->
                                drive.trajectorySequenceBuilder(start3)
                                        .lineToLinearHeading(sus1)
                                        .lineToLinearHeading(sus2)
                                        .lineToLinearHeading(sus3)
                                        .lineToLinearHeading(sus4)
                                        .lineToLinearHeading(sus5)
                                        .lineToLinearHeading(sus6)
                                        .lineToLinearHeading(sus7)
                                        .lineToLinearHeading(sus8)
                                        .lineToLinearHeading(sus9)
                                        .build()
                );
        RoadRunnerBotEntity austinBot = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, 60, 60, Math.toRadians(180))
                .setDimensions(13.5,13.5)
                .setStartPose(start4)
                .followTrajectorySequence(drive ->
                                drive.trajectorySequenceBuilder(start4)
                                        .lineToLinearHeading(austin1)
                                        .lineToLinearHeading(austin2)
                                        .lineToLinearHeading(austin3)
                                        .lineToLinearHeading(austin4)
                                        .lineToLinearHeading(austin5)
                                        .lineToLinearHeading(austin6)
                                        .lineToLinearHeading(austin7)
                                        .lineToLinearHeading(austin8)
                                        .lineToLinearHeading(start4)
                                        .build()
                );
        RoadRunnerBotEntity croissantBot = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, 60, 60, Math.toRadians(180))
                .setDimensions(13.5,13.5)
                .setStartPose(start5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(start5)
                                .lineToLinearHeading(croissant1)
                                .lineToLinearHeading(croissant2)
                                .lineToLinearHeading(croissant3)
                                .lineToLinearHeading(croissant4)
                                .lineToLinearHeading(croissant5)
                                .lineToLinearHeading(croissant6)
                                .lineToLinearHeading(croissant7)
                                .lineToLinearHeading(croissant8)
                                .lineToLinearHeading(croissant9)
                                .lineToLinearHeading(croissant10)
                                .lineToLinearHeading(croissant11)
                                .lineToLinearHeading(croissant12)
                                .lineToLinearHeading(croissant13)
                                .build()
                );
        RoadRunnerBotEntity optimusPrimeBot = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, 60, 60, Math.toRadians(180))
                .setDimensions(13.5,13.5)
                .setStartPose(start6)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(start6)
                                .lineToLinearHeading(optimusPrime1)
                                .lineToLinearHeading(optimusPrime2)
                                .lineToLinearHeading(optimusPrime3)
                                .lineToLinearHeading(optimusPrime4)
                                .lineToLinearHeading(optimusPrime5)
                                .lineToLinearHeading(optimusPrime6)
                                .lineToLinearHeading(optimusPrime7)
                                .lineToLinearHeading(optimusPrime8)
                                .lineToLinearHeading(optimusPrime9)
                                .lineToLinearHeading(optimusPrime10)
                                .lineToLinearHeading(optimusPrime11)
                                .lineToLinearHeading(optimusPrime12)
                                .lineToLinearHeading(optimusPrime13)
                                .build()
                );
        RoadRunnerBotEntity marioBot = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, 60, 60, Math.toRadians(180))
                .setDimensions(13.5,13.5)
                .setStartPose(start7)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(start7)
                                .lineToLinearHeading(mario1)
                                .lineToLinearHeading(mario2)
                                .lineToLinearHeading(mario3)
                                .lineToLinearHeading(mario4)
                                .lineToLinearHeading(mario5)
                                .lineToLinearHeading(mario6)
                                .lineToLinearHeading(mario7)
                                .lineToLinearHeading(mario8)
                                .lineToLinearHeading(mario9)
                                .lineToLinearHeading(mario10)
                                .lineToLinearHeading(mario11)
                                .lineToLinearHeading(start7)
                                .build()
                );
        RoadRunnerBotEntity docockBot = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, 60, 60, Math.toRadians(180))
                .setDimensions(13.5,13.5)
                .setStartPose(start8)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(start8)
                                .lineToLinearHeading(docock1)
                                .lineToLinearHeading(docock2)
                                .lineToLinearHeading(docock3)
                                .lineToLinearHeading(docock4)
                                .lineToLinearHeading(docock5)
                                .lineToLinearHeading(docock6)
                                .lineToLinearHeading(docock7)
                                .lineToLinearHeading(docock8)
                                .lineToLinearHeading(docock9)
                                .build()
                );
        RoadRunnerBotEntity batmanBot = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, 60, 60, Math.toRadians(180))
                .setDimensions(13.5,13.5)
                .setStartPose(start9)
                .followTrajectorySequence(drive ->
                                drive.trajectorySequenceBuilder(start9)
                                        .lineToLinearHeading(batman1)
                                        .lineToLinearHeading(batman2)
                                        .lineToLinearHeading(batman3)
                                        .lineToLinearHeading(batman4)
                                        .lineToLinearHeading(start9)
                                        .build()
                );
        RoadRunnerBotEntity yourmomEXE = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, 60, 60, Math.toRadians(180))
                .setDimensions(13.5,13.5)
                .setStartPose(start10)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(start10)
                                .splineToConstantHeading(new Vector2d(36, 0), Math.toRadians(90))
                                .splineToConstantHeading(new Vector2d(0, 36), Math.toRadians(180))
                                .splineToConstantHeading(new Vector2d(-36, 0), Math.toRadians(-90))
                                .splineToConstantHeading(new Vector2d(-12, -36), Math.toRadians(0))
                                .lineToLinearHeading(yourmom6)
                                .build()
                );
        RoadRunnerBotEntity northKoreaBot = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, 60, 60, Math.toRadians(180))
                .setDimensions(13.5,13.5)
                .setStartPose(start12)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(start12)
                                .lineToLinearHeading(northKorea1)
                                .lineToLinearHeading(northKorea2)
                                .lineToLinearHeading(northKorea3)
                                .lineToLinearHeading(northKorea4)
                                .lineToLinearHeading(northKorea5)
                                .lineToLinearHeading(northKorea6)
                                .lineToLinearHeading(northKorea7)
                                .lineToLinearHeading(northKorea8)
                                .lineToLinearHeading(northKorea9)
                                .lineToLinearHeading(northKorea10)
                                .lineToLinearHeading(northKorea11)
                                .lineToLinearHeading(northKorea12)
                                .lineToLinearHeading(northKorea13)
                                .lineToLinearHeading(northKorea14)
                                .lineToLinearHeading(northKorea15)
                                .build()
                );
        RoadRunnerBotEntity varoomBot = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, 60, 60, Math.toRadians(180))
                .setDimensions(13.5,13.5)
                .setStartPose(start13)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(start13)
                                .lineToLinearHeading(varoom1)
                                .lineToLinearHeading(varoom2)
                                .lineToLinearHeading(varoom3)
                                .lineToLinearHeading(varoom4)
                                .lineToLinearHeading(varoom5)
                                .lineToLinearHeading(varoom6)
                                .lineToLinearHeading(varoom1)
                                .lineToLinearHeading(start13)
                                .build()
                );
        RoadRunnerBotEntity xBot = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, 60, 60, Math.toRadians(180))
                .setDimensions(13.5,13.5)
                .setStartPose(start14)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(start14)
                                .lineToLinearHeading(x1)
                                .lineToLinearHeading(x2)
                                .lineToLinearHeading(x3)
                                .lineToLinearHeading(x4)
                                .lineToLinearHeading(x5)
                                .lineToLinearHeading(x6)
                                .lineToLinearHeading(x7)
                                .lineToLinearHeading(x8)
                                .lineToLinearHeading(x9)
                                .lineToLinearHeading(x10)
                                .lineToLinearHeading(x11)
                                .lineToLinearHeading(x12)
                                .lineToLinearHeading(x13)
                                .lineToLinearHeading(x14)
                                .lineToLinearHeading(x15)
                                .lineToLinearHeading(x16)
                                .lineToLinearHeading(x17)
                                .lineToLinearHeading(x1)
                                .lineToLinearHeading(start14)
                                .build()
                );
        RoadRunnerBotEntity yBot = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, 60, 60, Math.toRadians(180))
                .setDimensions(13.5,13.5)
                .setStartPose(start20)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(start20)
                                .lineToLinearHeading(y1)
                                .lineToLinearHeading(y2)
                                .lineToLinearHeading(y3)
                                .lineToLinearHeading(y4)
                                .lineToLinearHeading(y5)
                                .lineToLinearHeading(y1)
                                .lineToLinearHeading(start20)
                                .build()
                );

        RoadRunnerBotEntity testBot = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, 60, 60, Math.toRadians(180))
                .setDimensions(12,12)
                .setStartPose(start20)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-32, 8, Math.toRadians(135)))
                                .splineToSplineHeading(new Pose2d(-12,12,Math.toRadians(90)), Math.toRadians(0))
                                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_OFFICIAL)
                //.setDarkMode(true)
                //.setBackgroundAlpha(0.95f)
                //.addEntity(myServant)
                //.addEntity(simpBot)
                //.addEntity(susBot)
                //.addEntity(austinBot)
                //.addEntity(croissantBot)
                //.addEntity(optimusPrimeBot)
                //.addEntity(marioBot)
                //.addEntity(docockBot)
                //.addEntity(batmanBot)
                //.addEntity(yourmomEXE)
                //.addEntity(northKoreaBot)
                //.addEntity(varoomBot)
                //.addEntity(xBot)
                //.addEntity(yBot)
//                .addEntity(autonomousOP)
                //.addEntity(testBot)
                .addEntity(leftAuto)
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