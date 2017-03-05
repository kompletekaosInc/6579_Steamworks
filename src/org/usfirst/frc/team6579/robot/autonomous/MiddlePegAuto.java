package org.usfirst.frc.team6579.robot.autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6579.robot.Robot;

/**
 * This strategy is to use the gyro and vision tracking to place a gear on the middle peg
 * Created by Jiah on 5/3/17.
 */
public class MiddlePegAuto extends AutoStrategy{
    //Strategy:
    //drive forwards for a second (straight)
    private long msDrivingForwardStartTime = 0;
    //stop
    //start tracking for three seconds with slow down concept
    private long msTrackingStartTime = 0;

    /**
     * This method is where we start and do our auto strategy
     * @param robot
     */
    public void start(Robot robot){

        super.start(robot);

        robot.getDrivetrain().resetGyro();
        double targetAngle = robot.getDrivetrain().getModGyroAngle();
        msDrivingForwardStartTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - msDrivingForwardStartTime <1000){
            robot.getDrivetrain().followGyro(0.4,targetAngle);
        }
        robot.getDrivetrain().stop();

        robot.getVision().startTrackingPeg();
        msTrackingStartTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - msTrackingStartTime < 3000){
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
                robot.getVision().stopTrackingPeg();
            }
        }
        robot.getDrivetrain().stop();

    }


    @Override
    public void run(Robot robot) {

    }
    //stop


}
