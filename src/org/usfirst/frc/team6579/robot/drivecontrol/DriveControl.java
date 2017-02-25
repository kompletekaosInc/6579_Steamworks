package org.usfirst.frc.team6579.robot.drivecontrol;

import org.usfirst.frc.team6579.robot.Robot;

/**
 * This interface indicates that implementations of this interface can give commands to the robot
 * Created by Jiah on 24/2/17.
 */
public interface DriveControl {

    /**
     * This is the method that gives commands to the robot
     * This is called only in teleop
     * This should give commands to the robot and it's subsystems
     * @param robot
     */
    public void giveCommands(Robot robot);
}
