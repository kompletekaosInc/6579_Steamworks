package org.usfirst.frc.team6579.robot.subsystem.vision;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionPipeline;
import edu.wpi.first.wpilibj.vision.VisionThread;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team6579.robot.subsystem.SubSystem;

/**
 * Created by Jiah on 27/2/17.
 */
public class RobotVision extends Thread implements SubSystem {

    // indicate if we should be processing or not
    private boolean performVisionTracking = false;  // don't perform expensive processing unless we are asked to

    //private VisionThread visionThread;
    private long processCount = 0;
    private double centreX = 0.0;

    private final Object imgLock = new Object();  // to lock access to te centreX variable

    private UsbCamera camera = null;  // it will remain null if we have no camera plugged into the USB ports
    private Mat sourceMap = new Mat();;  // Maps are expensive, so we will re-use this

    private GripPipeline gripPipeline = new GripPipeline();

    // to see the GRIP gripPipeline using the USB camera from GRIP use an IP camera source with:  http://roborio-3452-frc.local:1181/?action=stream


    /**
     * This class tracks the peg using vision tracking
     */
    public RobotVision() {

        try {
            //get a reference to the camera to use for vision
            camera = CameraServer.getInstance().startAutomaticCapture();

            // by default, configure the camera for capturing dark images for vision tracking
            configureVisionCameraSettingsForTracking();

        } catch (Exception e) {
            System.out.println("Exception constructing RobotVision");
            e.printStackTrace();
            camera = null;  // we need to test the camera is NOT null before trying to use it
        }

    }

    private void configureVisionCameraSettingsForTracking() {

        camera.setResolution(320, 240);
        camera.setBrightness(0);
        camera.setExposureManual(0);
        camera.setWhiteBalanceManual(0);
    }

    /**
     * Run one off process of the image to find x.
     */
    public void processImageInPipeline()
    {
        // if we have no camera we can't process images
        if (camera == null)
            return;

        if (performVisionTracking) {

            // get camera image
            sourceMap.empty();  // we re-use the same Map to process the source image
            CvSink cvSink = CameraServer.getInstance().getVideo();
            cvSink.grabFrame(sourceMap);

            // create an instance of the GRIP gripPipeline
            gripPipeline.process(sourceMap);

            System.out.println("Inside thread [contours:" + gripPipeline.filterContoursOutput().size() + "] [processCount:" + processCount++ + "]");

            //process image through gripPipeline

            if (!gripPipeline.filterContoursOutput().isEmpty()) {
                // how many contours do we have?



                Rect r = Imgproc.boundingRect(gripPipeline.filterContoursOutput().get(0));
                SmartDashboard.putNumber("r.x", r.x);
                SmartDashboard.putNumber("r.width", r.width);


                System.out.println("centre X " + r.x + (r.width / 2));
                setPegX(r.x + (r.width / 2));
            }
            else
            {
                setPegX(0);
            }

            // release any resources held in the source Map as these can be expensive
            //sourceMap.release();  // todo: after testing that a memory leak occurs when we do not  release, remove the comment block
        }
    }

    /**
     * Using the Camera, take an image and process using the GRIP gripPipeline
     * To find the contours of the tape around the peg.
     * <p>
     * return double X co-ordinate that is the centre between the tapes.
     */
    public void startTrackingPeg() {
        performVisionTracking = true;
        //visionThread.start();
    }

    /**
     * Stops the vision thread
     */
    public void stopTrackingPeg(){
        // stop the vision thread from performing the expensive processing of the video images
        //visionThread.interrupt();
        performVisionTracking = false;

        // todo:  maybe restore video to normal capture settings
    }

    /**
     * Gets centreX
     * @return
     */
    public double getPegX(){
        synchronized (imgLock) {
            return centreX;
        }
    }
    private void setPegX(double x)
    {
        synchronized (imgLock) {
            centreX = x;
        }
    }

    /**
     * Change camera settings to allow normal video viewing.
     */
    public void startNormalVideo(){
        // todo: might be just changing the camera settings to allow normal exposure and brightness etc... for video view from robot
    }

    @Override
    public void publishStats() {
        SmartDashboard.putBoolean("Vision.performVisionTracking",performVisionTracking);
        SmartDashboard.putNumber("Peg X", getPegX());
        SmartDashboard.putNumber("number of contours", gripPipeline.filterContoursOutput().size());
    }
}
