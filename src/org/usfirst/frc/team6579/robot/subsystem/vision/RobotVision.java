package org.usfirst.frc.team6579.robot.subsystem.vision;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionThread;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team6579.robot.subsystem.SubSystem;

/**
 * Created by Jiah on 27/2/17.
 */
public class RobotVision implements SubSystem {


    private VisionThread visionThread;
    private double centreX = 0.0;

    private final Object imgLock = new Object();

    private UsbCamera camera = null;

    private GripPipeline pipeline;

    // to see the GRIP pipeline using the USB camera from GRIP use an IP camera source with:  http://roborio-3452-frc.local:1181/?action=stream


    /**
     * This class tracks the peg using vision tracking
     */
    public RobotVision() {

        try {
            //tries for camera
            camera = CameraServer.getInstance().startAutomaticCapture();
            camera.setResolution(320, 240);
            camera.setBrightness(0);
            camera.setExposureManual(0);
            camera.setWhiteBalanceManual(0);

            //try for pipeline
            pipeline = new GripPipeline();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * Using the Camera, take an image and process using the GRIP pipeline
     * To find the contours of the tape around the peg.
     * <p>
     * return double X co-ordinate that is the centre between the tapes.
     */
    public double findX() {
        double centreX = 0;  //default assume we are on track and centre x is directly in front
        // get camera image
        Mat source = new Mat();
        CvSink cvSink = CameraServer.getInstance().getVideo();
        cvSink.grabFrame(source);

        // create an instance of the GRIP pipeline
        pipeline.process(source);

        //process image through pipeline
        if (!pipeline.filterContoursOutput().isEmpty()) {
            // how many contours do we have?
            SmartDashboard.putNumber("number of contours", pipeline.filterContoursOutput().size());

            Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
            SmartDashboard.putNumber("r.x",r.x);
            SmartDashboard.putNumber("r.width",r.width);



            synchronized (imgLock) {
                centreX = r.x + (r.width / 2);
            }

        }

        return centreX;
    }

    @Override
    public void publishStats() {
        SmartDashboard.putNumber("CentreX",centreX);

    }
}
