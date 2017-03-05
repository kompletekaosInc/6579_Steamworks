package org.usfirst.frc.team6579.robot.autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6579.robot.Robot;

/**
 * This strategy is to use the gyro and vision tracking to place a gear on the middle peg
 * Created by Jiah on 5/3/17.
 */
public class MiddlePegAuto extends AutoStrategy{

    @Override
    protected long getMsToBaseline() {
        return 1000;
    }

    @Override
    protected long getMsForVisionTracking() {
        return 3000;
    }

    /**
     * Drives the peg to the left of the airship to the peg in auto.
     *
     * @param robot
     */
    public void start(Robot robot)
    {
        // Start from the back wall
        // this will run shorter than the super class as msToBaseline is reduced to 1 second from th default 2
        driveToBaseLine(robot);

        //starts tracking the peg, this will run for less time that
        //the super class as msForVisionTracking is reduced to 3 from the default 5
        visionTrackToPeg(robot);
    }

}
