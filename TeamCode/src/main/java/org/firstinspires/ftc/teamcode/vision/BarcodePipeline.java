package org.firstinspires.ftc.teamcode.vision;

import static org.firstinspires.ftc.teamcode.util.BarcodeLocation.LEFT;
import static org.firstinspires.ftc.teamcode.util.BarcodeLocation.MIDDLE;
import static org.firstinspires.ftc.teamcode.util.BarcodeLocation.RIGHT;
import static org.firstinspires.ftc.teamcode.util.BarcodeLocation.UNKNOWN;
import static org.firstinspires.ftc.teamcode.util.Constants.ANCHOR;
import static org.firstinspires.ftc.teamcode.util.Constants.ERODE_DILATE_ITERATIONS;
import static org.firstinspires.ftc.teamcode.util.Constants.GREEN;
import static org.firstinspires.ftc.teamcode.util.Constants.STRUCTURING_ELEMENT;
import static org.firstinspires.ftc.teamcode.util.Constants.WHITE;
import static org.firstinspires.ftc.teamcode.vision.OpenCVUtil.LEFT_BOUNDARY;
import static org.firstinspires.ftc.teamcode.vision.OpenCVUtil.RIGHT_BOUNDARY;
import static org.firstinspires.ftc.teamcode.vision.OpenCVUtil.YELLOW_LOWER;
import static org.firstinspires.ftc.teamcode.vision.OpenCVUtil.YELLOW_UPPER;

import org.firstinspires.ftc.teamcode.util.BarcodeLocation;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;

// Class for the pipeline that is used to detect the StarterStack
// This one uses the traditional open cv color matching method
public class BarcodePipeline extends OpenCvPipeline  {
    private Detection teamElement;

    private Mat blurred = new Mat();
    private Mat hsv = new Mat();
    private Mat yellowMask = new Mat();

    // Init
    @Override
    public void init(Mat input) {
        teamElement = new Detection(input.size(), 0.01);
    }

    // Process each frame that is received from the webcam
    @Override
    public Mat processFrame(Mat input)
    {
        Imgproc.GaussianBlur(input, blurred, new Size(7, 7), 0);
        Imgproc.cvtColor(blurred, hsv, Imgproc.COLOR_RGB2HSV);

        findTeamElement(input);

        return input;
    }

    private void findTeamElement(Mat input) {
        Core.inRange(hsv, new Scalar(YELLOW_LOWER.get()), new Scalar(YELLOW_UPPER.get()), yellowMask);
        Imgproc.erode(yellowMask, yellowMask, STRUCTURING_ELEMENT, ANCHOR, ERODE_DILATE_ITERATIONS);
        Imgproc.dilate(yellowMask, yellowMask, STRUCTURING_ELEMENT, ANCHOR, ERODE_DILATE_ITERATIONS);

        // set the largest detection that was found to be the Team Element
        ArrayList<MatOfPoint> contours = new ArrayList<>();
        Imgproc.findContours(yellowMask, contours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
        teamElement.setContour(OpenCVUtil.getLargestContour(contours));

        // draw the Team Element detection
        teamElement.draw(input, WHITE);
        Imgproc.line(input, new Point(320*(-LEFT_BOUNDARY/100.0), 0), new Point(320*(-LEFT_BOUNDARY/100.0), 240), GREEN, 2);
        Imgproc.line(input, new Point(320-320*(RIGHT_BOUNDARY/100.0), 0), new Point(320-320*(RIGHT_BOUNDARY/100.0),240), GREEN, 2);
        //320x240
    }

    // Get the StarterStack
    public Detection getTeamElement() {
        return teamElement;
    }

    public BarcodeLocation getTeamElementLocation() {
        if (teamElement.isValid()) {
            if (teamElement.getCenter().x < LEFT_BOUNDARY) {
                return LEFT;
            } else if (teamElement.getCenter().x > RIGHT_BOUNDARY) {
                return RIGHT;
            } else {
                return MIDDLE;
            }
        }
        return UNKNOWN;
    }
}