package org.firstinspires.ftc.teamcode.vision;

import static org.firstinspires.ftc.teamcode.util.Constants.ANCHOR;
import static org.firstinspires.ftc.teamcode.util.Constants.BLUR_SIZE;
import static org.firstinspires.ftc.teamcode.util.Constants.ERODE_DILATE_ITERATIONS;
import static org.firstinspires.ftc.teamcode.util.Constants.STRUCTURING_ELEMENT;
import static org.firstinspires.ftc.teamcode.vision.OpenCVUtil.YELLOW_LOWER;
import static org.firstinspires.ftc.teamcode.vision.OpenCVUtil.YELLOW_UPPER;

import org.firstinspires.ftc.teamcode.util.Color;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

public class JunctionPipeline extends OpenCvPipeline {
    public static Scalar GREEN = new Scalar(0, 255, 0);
    public static Scalar RED = new Scalar(0, 0, 255);
    public static Scalar YELLOW = new Scalar(0, 255, 255);

    Mat blurred = new Mat();
    Mat hsv = new Mat();
    Mat colorMask = new Mat();

    List<Detection> detections = new ArrayList<>();
    Detection closestDetection = null;
    Color centerColor;

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.blur(input, blurred, BLUR_SIZE);
        Imgproc.cvtColor(blurred, hsv, Imgproc.COLOR_BGR2HSV);
        Core.inRange(hsv , new Scalar(YELLOW_LOWER.get()), new Scalar(YELLOW_UPPER.get()), colorMask);
        Imgproc.erode(colorMask, colorMask, STRUCTURING_ELEMENT, ANCHOR, ERODE_DILATE_ITERATIONS);
        Imgproc.dilate(colorMask, colorMask, STRUCTURING_ELEMENT, ANCHOR, ERODE_DILATE_ITERATIONS);

        this.centerColor = new Color(input.get(input.cols() / 2, input.rows() / 2));

        detections.clear();
        ArrayList<MatOfPoint> colorContours = new ArrayList<>();
        Imgproc.findContours(colorMask, colorContours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
        double closestWidth = 0;
        for (int i = 0; i < colorContours.size(); i++) {
            Detection detection = new Detection(input.size(),0.005);
            detection.setContour(colorContours.get(i));
            detections.add(detection);

            detection.drawAngledRect(input, YELLOW, true);
            if (detection.isValid()) {
                Point p = detection.getTopCenterOfAngledRect();
                OpenCVUtil.drawPoint(input, p, RED, 10);

                double width = detection.getWidthOfAngledRect();
                if (width > closestWidth) {
                    closestDetection = detection;
                    closestWidth = width;
                }
            }
        }

        if (closestDetection != null) {
            OpenCVUtil.drawPoint(input, closestDetection.getTopCenterOfAngledRect(), GREEN, 10);
        }

        return input;
    }

    public Detection getClosestDetection() {
        return this.closestDetection;
    }

    public Color getCenterColor() {
        return this.centerColor;
    }
}
