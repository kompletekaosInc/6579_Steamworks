package org.usfirst.frc.team6579.robot.subsystem.vision;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
    private Mat sourceMat = new Mat();  // Maps are expensive, so we will re-use this

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
            System.out.println("Exception constructing camera in RobotVision");
            e.printStackTrace();
            camera = null;  // we need to test the camera is NOT null before trying to use it
            setPegX(0);  // default Peg x is directly in front
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
            sourceMat.empty();  // we re-use the same Map to process the source image
            CvSink cvSink = CameraServer.getInstance().getVideo();
            cvSink.grabFrame(sourceMat);

            // create an instance of the GRIP gripPipeline
            gripPipeline.process(sourceMat);

            System.out.println("Inside thread [contours:" + gripPipeline.filterContoursOutput().size() + "] [processCount:" + processCount++ + "]");

            //process image through gripPipeline

            if (!gripPipeline.filterContoursOutput().isEmpty()) {
                // process contours
                double finalPosition = getFinalPosition();

                setPegX(finalPosition);
                System.out.println("Peg X = " + getPegX());
            }
            else
            {
                setPegX(0);
            }

            // release any resources held in the source Map as these can be expensive
            sourceMat.release();  // todo: after testing that a memory leak occurs when we do not  release, remove the comment block
        }
    }

    /**
     * We have at least one contour when this is called
     *
     * @return
     */
    private double getFinalPosition() {
        //Declares tape variables
        Rect leftTape = null;
        double leftMiddle = 0;

        Rect rightTape = null;
        double rightMiddle = 0;

        double finalPosition = 0;

        double powerIncrement = 0;

        //This is the middle of the distance between the tapes
        double centreOfDistanceBetweenTapes;

        //assumes that contour 0 is right hand tape
        rightTape = Imgproc.boundingRect(gripPipeline.filterContoursOutput().get(0));
        SmartDashboard.putNumber("rightTape.x", rightTape.x);
        SmartDashboard.putNumber("rightTape.width", rightTape.width);
        rightMiddle = rightTape.x + (rightTape.width/2);
        SmartDashboard.putNumber("rightMiddle",rightMiddle);

        if (gripPipeline.filterContoursOutput().size()==2){
            //Assumes we have two tapes, and that contour 1 is the left tape
            leftTape = Imgproc.boundingRect(gripPipeline.filterContoursOutput().get(1));
            SmartDashboard.putNumber("leftTape.x", leftTape.x);
            SmartDashboard.putNumber("leftTape.width", leftTape.width);
            leftMiddle = leftTape.x + (leftTape.width/2);
            SmartDashboard.putNumber("leftMiddle", leftMiddle);


            //final position for two tapes
            centreOfDistanceBetweenTapes = (rightMiddle - leftMiddle) / 2;
            SmartDashboard.putNumber("centreOfDistanceBetweenTapes",centreOfDistanceBetweenTapes);
            finalPosition = rightMiddle-centreOfDistanceBetweenTapes;


            SmartDashboard.putBoolean("Two contours",true);

        }
        else{

            finalPosition = (rightTape.x + (rightTape.width / 2));
            // scale back to a number between -1 to 1, and then divides by 3 for a power cap, hence 240
            SmartDashboard.putBoolean("Two contours",false);


        }

        SmartDashboard.putNumber("Final middle X", finalPosition);

        finalPosition = finalPosition-80;


        powerIncrement = finalPosition / 240;

        SmartDashboard.putNumber("scaled finalPosition",finalPosition);
        SmartDashboard.putNumber("powerIncrement", powerIncrement);
        return powerIncrement;
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

    /**
     * Has to be a number between -1 and 1 where 0 is the peg directly in front.
     * @param x
     */
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
