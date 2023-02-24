package com.example.meepmeeptesting;

import com.noahbres.meepmeep.MeepMeep;

public class MeepMeepTesting {

    public static void main(String[] args) {

        MeepMeep meepMeep = new MeepMeep(900);

        OptimizationTesting optimizationTesting = new OptimizationTesting(meepMeep);
        AutoPaths autoPaths = new AutoPaths(meepMeep);
        SemiAutos semiAutos = new SemiAutos(meepMeep);

        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)

//                .addEntity(optimizationTesting.turretBot)
//                .addEntity(optimizationTesting.passthroughBot)
//                .addEntity(optimizationTesting.driftBot)
//            .addEntity(autoPaths.blueCarryBot)
//                .addEntity(autoPaths.redMidBot)
//                .addEntity(autoPaths.redMidBot2)
//            .addEntity(autoPaths.redCarryBot)
                .addEntity(semiAutos.lrightHighBot)

                .start();

    }
}