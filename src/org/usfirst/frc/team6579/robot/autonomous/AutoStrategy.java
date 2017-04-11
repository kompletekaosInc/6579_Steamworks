package org.usfirst.frc.team6579.robot.autonomous;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6579.robot.Robot;


/**
 * An abstract class defining what an autonomous strategy for Steamworks.  This class has some helper methods
 * that most strategies will need.  Simply subclass and implement <i>start</i>.
 *
 * Created by Jiah on 26/2/17.
 */
public abstract class AutoStrategy {

    // can be overridden but the default is to drive for 2 seconds to get to baseline
    protected long getMsToBaseline() {
        return 1800;//changed from 2000
    }

    // can be overridden but default is to drive for 5 seconds
    protected long getMsForVisionTracking() {
        return 5000;
    }


    /**
     * This code is basically auto.init This is the main method to implement the autostrategy.  It will run
     * just one single pass through the code, triggered by Robot.autoInit().  Subclass must provide an implementation
     * of this method.
     */
    public abstract void start(Robot robot);

    /**
     * This method drives the robot from the starting wall to the baseline.
     * Leverages the constant secondsToBaseline to determine how long this will take.
     * TODO: put the power 0.4 into a constant.
     * @param robot
     */
    protected void driveToBaseLine(Robot robot) {
        long msDrivingForwardStartTime = 0;
        robot.getDrivetrain().resetGyro();
        double followAngle = robot.getDrivetrain().getModGyroAngle();

        msDrivingForwardStartTime = System.currentTimeMillis();
        while ((System.currentTimeMillis() - msDrivingForwardStartTime) < getMsToBaseline())
        {
            robot.publishSubSystemStats();
            robot.getDrivetrain().followGyro(0.4,followAngle);
        }
        robot.getDrivetrain().stop();
    }


    /**
     * This method assumes that the robot is facing the peg and is able to
     * drive to the peg within 5 seconds at or below 30% power.  It will
     * use vision tracking to refine drive to peg.
     *
     * @param robot
     */
    protected void visionTrackToPeg(Robot robot) {
        // vision track to peg for 4 seconds
        long msTrackingPegStartTime = 0;
        robot.getVision().startTrackingPeg();
        msTrackingPegStartTime = System.currentTimeMillis();

        // drive for 5 seconds   TODO: pull the 5000ms into a constant or even better into the SmartDashboard
        while (System.currentTimeMillis()-msTrackingPegStartTime < getMsForVisionTracking())
        {

            robot.getVision().processImageInPipeline();

            if (Robot.debug && robot.getVision().isPerformVisionTracking())
                System.out.println("visionTrackToPeg [y:" + robot.getVision().getTapeY() + "] isPerformVisionTracking="+robot.getVision().isPerformVisionTracking());

            robot.publishSubSystemStats();

            // TODO:  need to refine what happens if there is no Tape showing in image, what value will y equal?
            if (robot.getVision().getTapeY() > 10) {//this number is the distance to stop tracking. TODO: Recalibrate this number
                if (Robot.debug)
                    SmartDashboard.putBoolean("Close to Peg", false);
                robot.followX(0.3);
            }
            else
            {
                if (Robot.debug)
                    SmartDashboard.putBoolean("Close to Peg", true);

                // we are close to the peg, slow down
                //robot.followX(0.2);
                // TODO:  this would be the time to shimmy around if we are worried the robot might ping the beg on the gear.
                robot.getDrivetrain().setPower(0.2, 0.2);
                robot.getVision().stopTrackingPeg();
            }
        }

        robot.getDrivetrain().stop();
    }


}
