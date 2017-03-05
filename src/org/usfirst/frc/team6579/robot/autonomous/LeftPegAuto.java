package org.usfirst.frc.team6579.robot.autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6579.robot.Robot;

/**
 * Created by Jiah on 5/3/17.
 */
public class LeftPegAuto extends AutoStrategy {
    // Basic Strategy is:
    // drive froward to baseline (2 seconds)
    private long secondsToBaseline = 2000;
    private long msDrivingForwardStartTime = 0;
    // stop
    // turn 60 right
    // stop
    // vision track to peg for 4 seconds
    private long msTrackingPegStartTime = 0;
    // stop

    public void start(Robot robot)
    {
        super.start(robot);


        // do the strategy here:

        robot.getDrivetrain().resetGyro();
        double followAngle = robot.getDrivetrain().getModGyroAngle();

        msDrivingForwardStartTime = System.currentTimeMillis();
        while ((System.currentTimeMillis() - msDrivingForwardStartTime) < secondsToBaseline)
        {
            robot.getDrivetrain().followGyro(0.4,followAngle);
        }
        robot.getDrivetrain().stop();

        //Turns 60 degrees tp the left
        robot.getDrivetrain().gyroTurn(60,false);

        robot.getDrivetrain().stop();

        //starts tracking the peg
        robot.getVision().startTrackingPeg();
        msTrackingPegStartTime = System.currentTimeMillis();

        while (System.currentTimeMillis()-msTrackingPegStartTime < 5000)
        {
            //System.out.println("tracking Peg in auto");
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
}
