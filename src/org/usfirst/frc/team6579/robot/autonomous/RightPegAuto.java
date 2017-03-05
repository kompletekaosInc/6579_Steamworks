package org.usfirst.frc.team6579.robot.autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6579.robot.Robot;

/**
 * This strategy is used when attempting to deliver gear to to the right hand side of airship.
 * Set up robot about a metre to the right of the gear on the starting wall.
 *
 * Created by Jiah on 4/3/17.
 */
public class RightPegAuto extends AutoStrategy{
    /**
     * Start running the strategy.
     * This will be called in Robot.autoInit() and is a single sequential process.  It will ignore autoPeriodic.
     *
     * @param robot
     */
    public void start(Robot robot)
    {
        // Start from the back wall
        driveToBaseLine(robot);

        //Turns 60 degrees tp the left
        robot.getDrivetrain().gyroTurn(60,true);
        robot.getDrivetrain().stop();

        //starts tracking the peg
        visionTrackToPeg(robot);
    }

}
