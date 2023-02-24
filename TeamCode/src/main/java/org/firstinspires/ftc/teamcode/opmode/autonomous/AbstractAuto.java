package org.firstinspires.ftc.teamcode.opmode.autonomous;

import static org.firstinspires.ftc.teamcode.hardware.Arm.Position.SCORE;
import static org.firstinspires.ftc.teamcode.hardware.Claw.Position.FLIPPED;

import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.PoseStorage;
import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.hardware.Slides;
import org.firstinspires.ftc.teamcode.util.CameraPosition;

import java.util.ArrayList;
import java.util.Locale;

public abstract class AbstractAuto extends LinearOpMode {
    public Robot robot;

    private int teamElementLocation = 2;
    private ArrayList<Step> steps;
    private double currentRuntime;
    public CameraPosition cameraPosition;

    // Main method to run all the steps for autonomous
    @Override
    public void runOpMode() {
//        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        telemetry.addLine("Initializing Robot...");
        telemetry.update();

        setCameraPosition();

        robot = new Robot(hardwareMap, SCORE, FLIPPED, cameraPosition);
//        robot.claw.close();
//        robot.claw.upright();
//        robot.claw.flip();
//        robot.arm.goToScore();

        makeTrajectories();

        while (robot.camera.getFrameCount() < 1) {
            idle();
        }

        // wait for start
        while (!(isStarted() || isStopRequested())) {
            currentRuntime = getRuntime();
            robot.update(currentRuntime);
            robot.claw.close();

            int newLocation = robot.camera.getMarkerId();
            if (newLocation != -1) {
                teamElementLocation = newLocation;
            }

            telemetry.addLine("Initialized");
            telemetry.addLine("Randomization: "+teamElementLocation);
            telemetry.addLine(robot.getTelemetry());
            telemetry.update();
        }
        PoseStorage.AutoJustEnded = true;
        resetRuntime();

        // build the first step
        steps = new ArrayList<>();
        stopCamera();
        initializeSteps(teamElementLocation);

        int stepNumber = 0;
        double stepTimeout;
        Step step = steps.get(stepNumber);
        stepTimeout = step.getTimeout() != -1 ? currentRuntime + step.getTimeout() : Double.MAX_VALUE;
        step.start();

        // run the remaining steps
        while (opModeIsActive()) {
            currentRuntime = getRuntime();
            // once a step finishes
            if (step.isFinished() || currentRuntime >= stepTimeout) {
                // do the finishing move
                step.end();
                stepNumber++;
                // if it was the last step break out of the while loop
                if (stepNumber > steps.size() - 1) {
                    break;
                }
                // else continue to the next step
                step = steps.get(stepNumber);
                stepTimeout = step.getTimeout() != -1 ? currentRuntime + step.getTimeout() : Double.MAX_VALUE;
                step.start();
            }

//            // update turret and slides position
//            PoseStorage.slidesPosition = robot.actuators.getSlides();
//            PoseStorage.turretPosition = robot.actuators.getTurret();
            PoseStorage.currentPose = robot.drive.getPoseEstimate();

            // while the step is running display telemetry
            step.whileRunning();
            robot.update(currentRuntime);

            telemetry.addLine(String.format(Locale.US, "Runtime: %.0f", currentRuntime));
            telemetry.addLine("Step " + (stepNumber + 1) + " of " + steps.size() + ", " + step.getTelemetry() + "\n");
            telemetry.addLine(robot.getTelemetry());
            telemetry.update();
        }
    }

    // Load up all of the steps for the autonomous: to be overridden with the specific steps in the specific auto
    public abstract void initializeSteps(int location);

    //methods to be implemented in the specific autos
    public abstract void setAlliance();

    public abstract void setCameraPosition();

    public abstract boolean useCamera();

    public abstract void makeTrajectories();


//    public abstract void setArm(Arm.Position armPos, Claw.Position clawPos);

    //other methods that do certain tasks

    public void turn(double degrees) {
        steps.add(new Step("Following a trajectory") {
            @Override
            public void start() {
                robot.drive.turn(degrees);
            }

            @Override
            public void whileRunning() {
                robot.drive.update();
            }

            @Override
            public void end() {
            }

            @Override
            public boolean isFinished() {
                return !robot.drive.isBusy();
            }
        });
    }

    public void followTrajectory(Trajectory trajectory) {
        steps.add(new Step("Following a trajectory") {
            @Override
            public void start() {
                robot.drive.followTrajectoryAsync(trajectory);
            }

            @Override
            public void whileRunning() {
                robot.drive.update();
            }

            @Override
            public void end() {
            }

            @Override
            public boolean isFinished() {
                return !robot.drive.isBusy();
            }
        });
    }

    public void followAndExtend(Trajectory trajectory, Slides.Position pos) {
        steps.add(new Step("Following a trajectory") {
            @Override
            public void start() {
                robot.drive.followTrajectoryAsync(trajectory);
                if (pos == Slides.Position.HIGH) {
                    robot.runningMacro = 3;
                } else if (pos == Slides.Position.MEDIUM) {
                    robot.runningMacro = 2;
                } else if (pos == Slides.Position.LOW) {
                    robot.runningMacro = 1;
                }
                robot.extendMacro(pos, currentRuntime);
            }

            @Override
            public void whileRunning() {
                if (robot.runningMacro != 0) {
                    robot.extendMacro(pos, currentRuntime);
                }

                robot.drive.update();
            }

            @Override
            public void end() {
            }

            @Override
            public boolean isFinished() {
                return !robot.drive.isBusy() && robot.runningMacro == 0;
            }
        });
    }

    public void followAndOpen(Trajectory trajectory) {
        steps.add(new Step("Following a trajectory") {
            @Override
            public void start() {
                robot.claw.open();
            }

            @Override
            public void whileRunning() {
                robot.drive.update();
            }

            @Override
            public void end() {
            }

            @Override
            public boolean isFinished() {
                return !robot.drive.isBusy();
            }
        });
    }

    public void followAndResetEnd(Trajectory trajectory, int pos) {
        steps.add(new Step("Following a trajectory") {
            @Override
            public void start() {
                robot.drive.followTrajectoryAsync(trajectory);
                robot.runningMacro = 4;
                robot.resetMacroEnd(pos, currentRuntime);
            }

            @Override
            public void whileRunning() {
                if (robot.runningMacro != 0) {
                    robot.resetMacroEnd(pos, currentRuntime);
                }
                robot.drive.update();
            }

            @Override
            public void end() {
//                robot.slides.setTarget(Slides.Position.HIGH); // start moving slides
//                robot.claw.close();
//                robot.claw.update();
            }

            @Override
            public boolean isFinished() {
                return !robot.drive.isBusy() && robot.runningMacro == 0;
            }
        });
    }

    public void followAndReset(Trajectory trajectory, int pos) {
        steps.add(new Step("Following a trajectory") {
            @Override
            public void start() {
                robot.drive.followTrajectoryAsync(trajectory);
                robot.runningMacro = 4;
                robot.resetMacro(pos, currentRuntime);
            }

            @Override
            public void whileRunning() {
                if (robot.runningMacro != 0) {
                    robot.resetMacro(pos, currentRuntime);
//                } else {
//                    if (robot.claw.getTriggerDistance() < 30) {
//                        robot.claw.close();
//                    }
//                    else {
//                        robot.claw.open();
//                    }
                }
                robot.drive.update();
            }

            @Override
            public void end() {
                robot.slides.setTarget(Slides.Position.HIGH); // start moving slides
                robot.claw.close();
                robot.claw.update();
            }

            @Override
            public boolean isFinished() {
                return !robot.drive.isBusy() && robot.runningMacro == 0;
            }
        });
    }

    // Functions to add steps
    public void addDelay(double timeout) {
        steps.add(new Step("Waiting for " + timeout + " seconds", timeout) {
            @Override
            public void start() {
            }

            @Override
            public void whileRunning() {
            }

            @Override
            public void end() {
            }

            @Override
            public boolean isFinished() {
                return false;
            }
        });
    }

    public void addCloseClaw(double timeout) {
        steps.add(new Step("", timeout) {
            @Override
            public void start() {
                robot.claw.close();
            }

            @Override
            public void whileRunning() {
            }

            @Override
            public void end() {
            }

            @Override
            public boolean isFinished() {
                return false;
            }
        });
    }

    public void addOpenClaw(double timeout) {
        steps.add(new Step("", timeout) {
            @Override
            public void start() {
                robot.claw.open();
            }

            @Override
            public void whileRunning() {
            }

            @Override
            public void end() {
            }

            @Override
            public boolean isFinished() {
                return false;
            }
        });
    }

    public void stopCamera() {
        steps.add(new Step("Stopping Signal Camera") {
            @Override
            public void start() {
                robot.camera.stopBarcodeWebcam();
            }

            @Override
            public void whileRunning() {
            }

            @Override
            public void end() {
            }

            @Override
            public boolean isFinished() {
                return true;
            }
        });
    }
}