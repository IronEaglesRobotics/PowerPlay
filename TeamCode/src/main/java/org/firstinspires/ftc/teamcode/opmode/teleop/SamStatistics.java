//package org.firstinspires.ftc.teamcode.opmode.teleop;
//
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//
//import org.firstinspires.ftc.teamcode.controller.Controller;
//
//import java.util.ArrayList;
//
//@TeleOp
//public class SamStatistics extends OpMode {
//    Controller driver1;
//
//    // r = red cone, b = blue cone, R = red beacon, B = blue beacon
//    private ArrayList<ArrayList<String>> field;
//    private String TopLeftRedTerminal = "";
//    private String TopRightBlueTerminal = "";
//    private String BottomLeftBlueTerminal = "";
//    private String BottomRightRedTerminal = ""; //"rrbrbrbrbR"
//
//    double runTime;
//    double runTimeForFirstButton;
//
//    @Override
//    public void init() {
//        driver1 = new Controller(gamepad1);
//
//        // create the field array
//        field = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            ArrayList<String> row = new ArrayList<>();
//            field.add(row);
//        }
//
//    }
//
//    private int getJunctionScore(int row, int col) {
//        // check for ground junction
//        if (row % 2 != 0 && col % 2 != 0) {
//            return 2;
//        }
//        // check for low junction
//        else if (row == 1 || col == 1 || row == 5 || col == 5) {
//            return 3;
//        }
//        // check for mid junction
//        else if (row % 2 == 0 && col % 2 == 0) {
//            return 4;
//        }
//        // check for high junction
//        return 5;
//    }
//
//    private String calculateScore() {
//        double red = 0;
//        double blue = 0;
//
//        for (int r = 1; r < field.size()) {
//            for (int r = 1; r <= 5; r++) {
//
//                String stack = row.get(r-1);
//                for (int c = 1; c <= stack.length(); c++) {
//                    char cone = stack.charAt(c-1);
//                    switch (cone) {
//                        case 'r':
//                            red += getJunctionScore(r, c);
//                            break;
//                        case 'b':
//                            blue += getJunctionScore(r, c);
//                            break;
//                        case 'R':
//                            red += 10;
//                            break;
//                        case 'B':
//                            blue += 10;
//                            break;
//                    }
//                }
//            }
//        }
//        for (int i = 0; i < 5; i++) {
//            ArrayList<String> row = new ArrayList<>();
//            field.add(row);
//        }
//
//        return "1 to 1";
//    }
//
//    @Override
//    public void loop() {
//        driver1.update();
//        runTime = getRuntime();
//
//        boolean lt = driver1.getLeftTrigger().getValue() > 0;
//        boolean rt = driver1.getRightTrigger().getValue() > 0;
//        boolean lb = driver1.getLeftBumper().isPressed();
//        boolean rb = driver1.getRightBumper().isPressed();
//
//        boolean jlt = driver1.getLeftTrigger().isJustPressed();
//        boolean jrt = driver1.getRightTrigger().isJustPressed();
//        boolean jlb = driver1.getLeftBumper().isJustPressed();
//        boolean jrb = driver1.getRightBumper().isJustPressed();
//
//        boolean a = driver1.getA().isPressed();
//        boolean b = driver1.getB().isPressed();
//        boolean x = driver1.getX().isPressed();
//        boolean y = driver1.getY().isPressed();
//
//        boolean ja = driver1.getA().isJustPressed();
//        boolean jb = driver1.getB().isJustPressed();
//        boolean jx = driver1.getX().isJustPressed();
//        boolean jy = driver1.getY().isJustPressed();
//
//        boolean du = driver1.getDUp().isPressed();
//        boolean dd = driver1.getDDown().isPressed();
//        boolean dl = driver1.getDLeft().isPressed();
//        boolean dr = driver1.getDRight().isPressed();
//
//        boolean jdu = driver1.getDUp().isJustPressed();
//        boolean jdd = driver1.getDDown().isJustPressed();
//        boolean jdl = driver1.getDLeft().isJustPressed();
//        boolean jdr = driver1.getDRight().isJustPressed();
//
//        boolean ls = driver1.getLeftStickButton().isPressed();
//        boolean rs = driver1.getRightStickButton().isPressed();
//        boolean jls = driver1.getLeftStickButton().isJustPressed();
//        boolean jrs = driver1.getRightStickButton().isJustPressed();
//
//        boolean tp = driver1.getTouchpad().isPressed();
//        boolean jtp = driver1.getTouchpad().isJustPressed();
//
//        int xCoord = 0;
//
//        if (driver1.getLeftBumper().isJustReleased()) {
//            runTimeForFirstButton = runTime;
//        }
//
//        if (driver1.getLeftBumper().isJustPressed()) {
//            xCoord = 1;
//        } else if (driver1.getLeftTrigger().isJustPressed()) {
//            xCoord = 2;
//        }
//
//    }
//}
