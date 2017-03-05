package org.usfirst.frc.team6579.robot.autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6579.robot.Robot;

/**
 * Created by Jiah on 4/3/17.
 */
public class RightPegAuto extends AutoStrategy{
    // Basic Strategy is:
    // drive froward to baseline (2 seconds)
    private boolean reachedBaseline = false;
    // stop
    // turn 60 left
    private boolean turnComplete = false;
    // stop
    // vision track to peg for 4 seconds
    private boolean trackVisionComplete = false;
    private long msTrackingPegStartTime = 0;
    // stop

    public void start(Robot robot)
    {
        super.start(robot);
        reachedBaseline = false;
        turnComplete = false;
        trackVisionComplete = false;


        // do the strategy here:
        robot.getVision().startTrackingPeg();
        msTrackingPegStartTime = System.currentTimeMillis();

        while (System.currentTimeMillis()-msTrackingPegStartTime < 5000)
        {
            System.out.println("tracking Peg in auto");
            robot.getVision().processImageInPipeline();

            if (robot.getVision().getTapeY() > 50) {
                SmartDashboard.putBoolean("Close to Peg", false);
                robot.followX(0.3);
            }
            else
            {
                SmartDashboard.putBoolean("Close to Peg", true);
                // we are close to the peg, slow down
                //robot.followX(0.2);
                robot.getDrivetrain().setPower(0.2, 0.2);
            }
        }

        trackVisionComplete = true;

    }

    /**
     * Logic for running autonomous
     * @param robot
     */
    public void run(Robot robot) {
        /*
        SmartDashboard.putString("Auton selection", this.getClass().toString());

        SmartDashboard.putBoolean("reachedBaseline", reachedBaseline);
        SmartDashboard.putBoolean("turnComplete", turnComplete);
        SmartDashboard.putBoolean("trackVisionComplete", trackVisionComplete);

        if (timer.get() < 2){
            //robot.getDrivetrain().setPower(-0.4,-0.4);

            robot.getDrivetrain().resetGyro();
            double followAngle = robot.getDrivetrain().getModGyroAngle();

            robot.getDrivetrain().followGyro(0.4,followAngle);


        }
        else
        {
            robot.getDrivetrain().hardStop();
            reachedBaseline = true;
        }



//        if (reachedBaseline && (timer.get() <=2.5) ){
//            robot.getDrivetrain().hardStop();
//        }
//        else if (reachedBaseline && (timer.get() <=2.7 )){
//            robot.getDrivetrain().stop();
//        }

        if (reachedBaseline && !turnComplete)
        {
            robot.getDrivetrain().gyroTurn(60,true);

            robot.getVision().startTrackingPeg();
            // about to start tracking peg
            msTrackingPegStartTime = System.currentTimeMillis();

            turnComplete = true;
        }


        SmartDashboard.putNumber("msTrackingPegStartTime", msTrackingPegStartTime);
        SmartDashboard.putNumber("System.currentTimeMillis()", System.currentTimeMillis());
        SmartDashboard.putNumber("TrackingTime", System.currentTimeMillis()-msTrackingPegStartTime);

        if (turnComplete && !trackVisionComplete && (System.currentTimeMillis()-msTrackingPegStartTime < 5000))
        {
            System.out.println("tracking Peg in auto");
            if (robot.getVision().getTapeY() > 50) {
                SmartDashboard.putBoolean("Close to Peg", false);
                robot.followX(0.3);
            }
            else
            {
                SmartDashboard.putBoolean("Close to Peg", true);
                // we are close to the peg, slow down
                //robot.followX(0.2);
                robot.getDrivetrain().setPower(0.2, 0.2);
            }
        }
        else if (turnComplete && !trackVisionComplete)
        {
            robot.getVision().stopTrackingPeg();
            trackVisionComplete = true;
        }



//        else if (timer.get() >2.7 ){
//            robot.getDrivetrain().gyroTurn(60,true);
//        }

//        else if (timer.get() <= 4){
//            robot.getVision().startTrackingPeg();
//        }
//        else if (timer.get() <= 7){
//            robot.followX(0.2);
//        }
//        else if (timer.get() <= 8){
//            robot.getDrivetrain().stop();
//        }

*/
    }

}
