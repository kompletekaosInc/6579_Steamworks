package org.usfirst.frc.team6579.robot.autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6579.robot.Robot;

/**
 * Created by Jiah on 5/3/17.
 */
public class LeftPegAuto extends AutoStrategy {

    /**
     * Drives the peg to the left of the airship to the peg in auto.
     *
     * @param robot
     */
    public void start(Robot robot)
    {
        // Start from the back wall
        driveToBaseLine(robot);

        //Turns 60 degrees tp the left
        robot.getDrivetrain().gyroTurn(60,false);
        robot.getDrivetrain().stop();

        //starts tracking the peg
        visionTrackToPeg(robot);
    }
}
