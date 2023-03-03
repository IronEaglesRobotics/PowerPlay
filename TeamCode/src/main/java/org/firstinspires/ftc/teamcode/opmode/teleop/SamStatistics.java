package org.firstinspires.ftc.teamcode.opmode.teleop;

import static org.firstinspires.ftc.teamcode.opmode.Alliance.BLUE;
import static org.firstinspires.ftc.teamcode.opmode.Alliance.RED;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.controller.Controller;
import org.firstinspires.ftc.teamcode.opmode.Alliance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@TeleOp(name = "Sam Stats")
public class SamStatistics extends OpMode {
    Controller driver1;

    // r = red cone, b = blue cone, R = red beacon, B = blue beacon, the strings will look like "rrbrbrbrbR"
    private ArrayList<ArrayList<String>> field;
    private String topLeftRedTerminal = "";
    private String topRightBlueTerminal = "";
    private String bottomLeftBlueTerminal = "";
    private String bottomRightRedTerminal = "";
    private int droppedBlueCones = 0;
    private int droppedRedCones = 0;

    private ArrayList<Vector2d> pastPositions = new ArrayList<>();
    private HashMap<String, Double> buttonPresses = new HashMap<>();
    private HashMap<String, Double> buttonReleases = new HashMap<>();

    double runTime;
    double runTimeForFirstButton;

    int yCoord = -1;
    int xCoord = -1;

    @Override
    public void init() {
        driver1 = new Controller(gamepad1);

        // create the field array
        field = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ArrayList<String> row = new ArrayList<>();
            row.add("");
            row.add("");
            row.add("");
            row.add("");
            row.add("");
            field.add(row);
        }

    }

    // return the point value for any given junction
    private int getJunctionScore(int row, int col) {
        // check for ground junction
        if (row % 2 != 0 && col % 2 != 0) {
            return 2;
        }
        // check for low junction
        else if (row == 1 || col == 1 || row == 5 || col == 5) {
            return 3;
        }
        // check for mid junction
        else if (row % 2 == 0 && col % 2 == 0) {
            return 4;
        }
        // check for high junction
        return 5;
    }

    // calculate the score based off of the field array
    private String calculateScore() {
        double red = 0;
        double blue = 0;

        // iterate through the junctions
        for (int r = 1; r < field.size(); r++) {
            for (int c = 1; c <= 5; c++) {
                // get each stack of elements
                String stack = field.get(r-1).get(c-1);
                char cone = 'A';
                if (stack.length() > 0) {
                    cone = stack.charAt(0);
                }

                // iterate through the stack of elements
                for (int e = 0; e < stack.length(); e++) {
                    cone = stack.charAt(e);

                    // give points based on what the element is
                    switch (cone) {
                        case 'r':
                            red += getJunctionScore(r, c);
                            break;
                        case 'b':
                            blue += getJunctionScore(r, c);
                            break;
                    }
                }
                // calculate ownership from the last element
                switch (cone) {
                    case 'r':
                        red += 3;
                        break;
                    case 'b':
                        blue += 3;
                        break;
                    case 'R':
                        red += 10;
                        break;
                    case 'B':
                        blue += 10;
                        break;
                }
            }
        }

        red += (topLeftRedTerminal.length() + bottomRightRedTerminal.length());
        blue += (topRightBlueTerminal.length() + bottomLeftBlueTerminal.length());

        return "Red: "+red+"Blue: "+blue;
    }

    @Override
    public void loop() {
        driver1.update();
        runTime = getRuntime();

        // check for any buttons pressed
        if (driver1.getLeftBumper().isJustPressed()) { buttonPresses.put("lb", runTime); }
        if (driver1.getLeftTrigger().isJustPressed()) { buttonPresses.put("lt", runTime); }
        if (driver1.getRightBumper().isJustPressed()) { buttonPresses.put("rb", runTime); }
        if (driver1.getRightTrigger().isJustPressed()) { buttonPresses.put("rt", runTime); }
        if (driver1.getX().isJustPressed()) { buttonPresses.put("x", runTime); }
        if (driver1.getY().isJustPressed()) { buttonPresses.put("y", runTime); }
        if (driver1.getB().isJustPressed()) { buttonPresses.put("b", runTime); }
        if (driver1.getA().isJustPressed()) { buttonPresses.put("a", runTime); }
        if (driver1.getTouchpad().isJustPressed()) { buttonPresses.put("tp", runTime); }
        if (driver1.getDUp().isJustPressed()) { buttonPresses.put("du", runTime); }
        if (driver1.getDDown().isJustPressed()) { buttonPresses.put("dd", runTime); }
        if (driver1.getDLeft().isJustPressed()) { buttonPresses.put("undo", runTime); }
        if (driver1.getDRight().isJustPressed()) { buttonPresses.put("redo", runTime); }

        // check for any buttons released
        if (driver1.getLeftBumper().isJustReleased()) { buttonReleases.put("lb", runTime); }
        if (driver1.getLeftTrigger().isJustReleased()) { buttonReleases.remove("lt", runTime); }
        if (driver1.getRightBumper().isJustReleased()) { buttonReleases.remove("rb", runTime); }
        if (driver1.getRightTrigger().isJustReleased()) { buttonReleases.remove("rt", runTime); }
        if (driver1.getX().isJustReleased()) { buttonReleases.remove("x", runTime); }
        if (driver1.getY().isJustReleased()) { buttonReleases.remove("y", runTime); }
        if (driver1.getB().isJustReleased()) { buttonReleases.remove("b", runTime); }
        if (driver1.getA().isJustReleased()) { buttonReleases.remove("a", runTime); }
        if (driver1.getTouchpad().isJustReleased()) { buttonReleases.put("tp", runTime); }
        if (driver1.getDUp().isJustReleased()) { buttonReleases.put("du", runTime); }
        if (driver1.getDDown().isJustReleased()) { buttonReleases.put("dd", runTime); }
        if (driver1.getDLeft().isJustReleased()) { buttonReleases.put("undo", runTime); }
        if (driver1.getDRight().isJustReleased()) { buttonReleases.put("redo", runTime); }

        // if a button was released for more than 0.3 seconds, remove it
        for (Map.Entry<String, Double> key : buttonReleases.entrySet()) {
            if (getRuntime() - key.getValue() > 0.3) {
                buttonPresses.remove(key.getKey());
                buttonReleases.remove(key.getKey());
            }
        }

        // if all buttons are released, figure out what junction was called
        if (driver1.getAllButtons().isJustReleased()) {

            Alliance alliance = BLUE;
            if (buttonPresses.containsKey("tp")) {
                alliance = RED;
            }

            // figure out what element it is
            String element;
            if (alliance == RED) {
                driver1.rumble(100);
                if (buttonPresses.containsKey("du")) {
                    driver1.setColor(0,0,255,100);
                    element = "B";
                } else if (buttonPresses.containsKey("dd")) {
                    driver1.setColor(255,0,0,100);
                    element = "R";
                } else {
                    driver1.setColor(255,0,0,100);
                    element = "r";
                }
            } else {
                driver1.rumble(100);
                if (buttonPresses.containsKey("du")) {
                    driver1.setColor(255,0,0,100);
                    element = "R";
                } else if (buttonPresses.containsKey("dd")) {
                    driver1.setColor(0,0,255,100);
                    element = "B";
                } else {
                    driver1.setColor(0,0,255,100);
                    element = "b";
                }
            }

            // figure out what the coordinates are
            if (buttonPresses.containsKey("lb") && buttonPresses.containsKey("lt") && buttonPresses.containsKey("rb") && buttonPresses.containsKey("rt")) {
                if (buttonPresses.containsKey("x")) {
                    topLeftRedTerminal += "r";
                } else if (buttonPresses.containsKey("y")) {
                    topRightBlueTerminal += "b";
                } else if (buttonPresses.containsKey("b")) {
                    bottomRightRedTerminal += "r";
                } else if (buttonPresses.containsKey("a")) {
                    bottomLeftBlueTerminal += "b";
                }
            }
            else {
                if (buttonPresses.containsKey("lb") && buttonPresses.containsKey("lt")) {
                    xCoord = 5;
                } else if (buttonPresses.containsKey("rt")) {
                    xCoord = 4;
                } else if (buttonPresses.containsKey("rb")) {
                    xCoord = 3;
                } else if (buttonPresses.containsKey("lt")) {
                    xCoord = 2;
                } else if (buttonPresses.containsKey("lb")) {
                    xCoord = 1;
                } else {
                    xCoord = -1;
                }
                if (buttonPresses.containsKey("x") && buttonPresses.containsKey("a")) {
                    yCoord = 5;
                } else if (buttonPresses.containsKey("a")) {
                    yCoord = 4;
                } else if (buttonPresses.containsKey("b")) {
                    yCoord = 3;
                } else if (buttonPresses.containsKey("y")) {
                    yCoord = 2;
                } else if (buttonPresses.containsKey("x")) {
                    yCoord = 1;
                } else {
                    yCoord = -1;
                }

                // update the field array to reflect the new cone or beacon placed
                if (yCoord != -1 && xCoord != -1) {
                    field.get(xCoord-1).set(yCoord-1, field.get(xCoord-1).get(yCoord-1)+element);
                    pastPositions.add(new Vector2d(xCoord-1,yCoord-1));
                }
            }
            if (buttonPresses.containsKey("undo")) {
                if (pastPositions.size() > 0) {
                    Vector2d lastPosition = pastPositions.get(pastPositions.size()-1);
                    String stack = field.get((int)lastPosition.getX()).get((int)lastPosition.getY());
                    String newStack = stack.substring(0, stack.length()-1);
                    field.get((int)lastPosition.getX()).set((int)lastPosition.getY(), newStack);
                    pastPositions.remove(pastPositions.size()-1);
                }
            }

            buttonPresses.clear();
            buttonReleases.clear();
        }

        // calculate the score
        telemetry.addLine(calculateScore());
        telemetry.addLine(String.format("(0,0): %s\n(0,1): %s\n(0,2): %s", field.get(0).get(0), field.get(0).get(1), field.get(0).get(2)));
        telemetry.addLine(String.format("(1,0): %s\n(1,1): %s\n(1,2): %s", field.get(1).get(0), field.get(1).get(1), field.get(1).get(2)));
        telemetry.addLine(String.format("(2,0): %s\n(2,1): %s\n(2,2): %s", field.get(2).get(0), field.get(2).get(1), field.get(2).get(2)));
        telemetry.addLine(String.format("Red Far Terminal %s", topLeftRedTerminal));
        telemetry.addLine(String.format("Blue Far Terminal %s", topRightBlueTerminal));
        telemetry.addLine(String.format("Red Close Terminal %s", bottomRightRedTerminal));
        telemetry.addLine(String.format("Blue Close Terminal %s", bottomLeftBlueTerminal));
        telemetry.update();
    }
}
