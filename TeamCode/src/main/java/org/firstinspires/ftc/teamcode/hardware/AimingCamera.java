package org.firstinspires.ftc.teamcode.hardware;

import static org.firstinspires.ftc.teamcode.util.Constants.AIMING_CAMERA;
import static org.firstinspires.ftc.teamcode.util.Constants.AIMING_WEBCAM_HEIGHT;
import static org.firstinspires.ftc.teamcode.util.Constants.AIMING_WEBCAM_WIDTH;
import static org.firstinspires.ftc.teamcode.util.Constants.AUTO_WEBCAM_HEIGHT;
import static org.firstinspires.ftc.teamcode.util.Constants.INVALID_POINT;
import static org.firstinspires.ftc.teamcode.util.Constants.WEBCAM_ROTATION;
import static org.firstinspires.ftc.teamcode.util.Constants.AUTO_WEBCAM_WIDTH;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.util.Color;
import org.firstinspires.ftc.teamcode.vision.Detection;
import org.firstinspires.ftc.teamcode.vision.JunctionPipeline;
import org.opencv.core.Point;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;

public class AimingCamera {
    private boolean cameraInitialized;
    private OpenCvCamera camera;
    private JunctionPipeline junctionPipeline;

    public AimingCamera init(HardwareMap hardwareMap) {
        int stackCameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        this.camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, AIMING_CAMERA), stackCameraMonitorViewId);
        this.camera
        // AprilTag pipeline
        this.junctionPipeline = new JunctionPipeline();
        camera.setPipeline(this.junctionPipeline);
        // OpenCV pipeline
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.startStreaming(AIMING_WEBCAM_WIDTH, AIMING_WEBCAM_HEIGHT, WEBCAM_ROTATION);
                cameraInitialized = true;
                FtcDashboard.getInstance().startCameraStream(camera, 0);
            }

            @Override
            public void onError(int errorCode) {

            }
        });

        return this;
    }

    // Close the Barcode Camera
    public void stopAimingCamera() {
        camera.closeCameraDeviceAsync(() -> cameraInitialized = false);
    }

    public Point getTopOfJunction() {
        Detection closestDetection = junctionPipeline.getClosestDetection();
        if (closestDetection != null) {
            return closestDetection.getTopCenterOfAngledRect();
        }

        return INVALID_POINT;
    }

    public Color getCenterColor() {
        Color centerColor = junctionPipeline.getCenterColor();
        return centerColor;
    }
}
