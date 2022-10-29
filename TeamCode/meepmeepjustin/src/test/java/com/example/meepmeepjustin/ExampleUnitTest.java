package com.example.meepmeepjustin;

import org.junit.Test;

import static org.junit.Assert.*;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MeepMeepJustin {
    public static void main(String args[]) {
        MeepMeep mm = new MeepMeep(800)
                .setBackground(MeepMeep.Background.FIELD_FREIGHTFRENZY_ADI_DARK)
                .setTheme(new ColorSchemeRedDark())
                .setBackgroundAlpha(1f)
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180))
                .followTrajectorySequenceBuilder(new Pose2d(0, 0, 0,))
                        .forwerd(30)
                        .build()
                );
    }
}