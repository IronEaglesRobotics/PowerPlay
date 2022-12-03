package org.firstinspires.ftc.teamcode.hardware;

import static org.firstinspires.ftc.teamcode.util.Constants.WEBCAM_HEIGHT;
import static org.firstinspires.ftc.teamcode.util.Constants.WEBCAM_ROTATION;
import static org.firstinspires.ftc.teamcode.util.Constants.WEBCAM_WIDTH;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.util.CameraPosition;
import org.firstinspires.ftc.teamcode.vision.AprilTagDetectionPipeline;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;

import java.util.ArrayList;

@Config
// Class for the camera
public class Camera {
    private boolean signalWebcamInitialized;

    // AprilTag stuff
    AprilTagDetectionPipeline aprilTagDetectionPipeline;
    ArrayList<AprilTagDetection> detections;
    static final double FEET_PER_METER = 3.28084;
    public CameraPosition cameraPosition;

    // Lens intrinsics
    // UNITS ARE PIXELS
    // NOTE: this calibration is for the C920 webcam at 800x448.
    // You will need to do your own calibration for other configurations!
    public static double fx = 578.272;
    public static double fy = 578.272;
    public static double cx = 402.145;
    public static double cy = 221.506;

    // UNITS ARE METERS
    public static double tagsize = 0.0381; // 0.0508

    int numFramesWithoutDetection = 0;

    final float DECIMATION_HIGH = 3;
    final float DECIMATION_LOW = 2;
    final float THRESHOLD_HIGH_DECIMATION_RANGE_METERS = 1.0f;
    final int THRESHOLD_NUM_FRAMES_NO_DETECTION_BEFORE_LOW_DECIMATION = 4;

    // OpenCV stuff
    private OpenCvCamera signalWebcam;

    // Constructor
    public Camera(CameraPosition cameraPosition) {
        this.cameraPosition = cameraPosition;
    }

    // Initiate the Barcode Camera
    public void init(HardwareMap hardwareMap) {
        int stackCameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        if (cameraPosition == CameraPosition.RIGHT) {
            this.signalWebcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), stackCameraMonitorViewId);
        } else {
            this.signalWebcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 2"), stackCameraMonitorViewId);
        }
        // AprilTag pipeline
        this.aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);
        signalWebcam.setPipeline(aprilTagDetectionPipeline);
        // OpenCV pipeline
        signalWebcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                signalWebcam.startStreaming(WEBCAM_WIDTH, WEBCAM_HEIGHT, WEBCAM_ROTATION);
                signalWebcamInitialized = true;
                FtcDashboard.getInstance().startCameraStream(signalWebcam, 0);
            }

            @Override
            public void onError(int errorCode) {

            }
        });
    }

    // Close the Barcode Camera
    public void stopBarcodeWebcam() {
        signalWebcam.closeCameraDeviceAsync(() -> signalWebcamInitialized = false);
    }


    // AprilTag methods:
    public int getMarkerId() {
        detections = aprilTagDetectionPipeline.getLatestDetections();

        // If there's been a new frame...
        if (detections != null) {
            // If we don't see any tags
            if (detections.size() == 0) {
                numFramesWithoutDetection++;

                // If we haven't seen a tag for a few frames, lower the decimation
                // so we can hopefully pick one up if we're e.g. far back
                if (numFramesWithoutDetection >= THRESHOLD_NUM_FRAMES_NO_DETECTION_BEFORE_LOW_DECIMATION) {
                    aprilTagDetectionPipeline.setDecimation(DECIMATION_LOW);
                }
            }
            // We do see tags!
            else {
                numFramesWithoutDetection = 0;

                // If the target is within 1 meter, turn on high decimation to
                // increase the frame rate
                if (detections.get(0).pose.z < THRESHOLD_HIGH_DECIMATION_RANGE_METERS) {
                    aprilTagDetectionPipeline.setDecimation(DECIMATION_HIGH);
                }

                return detections.get(0).id;

            }
        }
        return -1;
    }

    // Get the number of frames the current camera has processed
    public int getFrameCount() {
        if (signalWebcamInitialized) {
            return signalWebcam.getFrameCount();
        } else {
            return 0;
        }
    }


    // Get Telemetry for the current active camera
    public String getTelemetry() {
        if (signalWebcamInitialized) {
            if (detections != null) {
                StringBuilder info = new StringBuilder();
                for (AprilTagDetection detection : detections) {
                    info.append("Detected tag ID: ").append(detection.id);
                    info.append("\nPosition: ").append(detection.center.x);
                }
                return String.valueOf(info);
            }
            return "No Detection";
        }
        return ("No Camera Initialized");
    }
}