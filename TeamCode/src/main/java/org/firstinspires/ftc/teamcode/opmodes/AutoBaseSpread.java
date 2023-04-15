package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.roadrunner.trajectory.Trajectory;

public abstract class AutoBaseSpread extends AbstractAuto {
    @Override
    protected boolean getAndScoreStackCone(Trajectory getStackConeTrajectory, Trajectory scoreStackConeTrajectory, int height, double coneTargetDistance) {
        if (getStackConeTrajectory == null || scoreStackConeTrajectory == null) {
            return false;
        }

        // Get the cone off the stack
        this.robot.getDrive().followTrajectory(getStackConeTrajectory);
        if (!this.waleAlign(coneTargetDistance)) {
            return false;
        }
        sleep(25);
        this.robot.getArm().moveRight();
        this.robot.getWale().stow();
        sleep(200);
        this.robot.getClaw().close();
        sleep(150);

        // Move back to the junction
        this.robot.getArm().moveMid();
        sleep(50);
        this.robot.getDrive().followTrajectory(scoreStackConeTrajectory);

        // Score
        this.robot.getArm().moveLeft();
        sleep(100);
        this.robot.getClaw().open();
        this.robot.getLift().slideToCone(height);
        sleep(150);

        return true;
    }
}
