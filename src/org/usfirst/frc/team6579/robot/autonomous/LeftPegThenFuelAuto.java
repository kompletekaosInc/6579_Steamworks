package org.usfirst.frc.team6579.robot.autonomous;

import org.usfirst.frc.team6579.robot.Robot;

/**
 * Created by Jiah on 5/3/17.
 */
public class LeftPegThenFuelAuto extends LeftPegAuto {

    /**
     * This does the left peg strategy (maybe a little faster) then attempts to
     * go and drop fuel in the low boiler.
     * @param robot
     */
    @Override
    public void start(Robot robot) {
        super.start(robot);

        // TODO:  see if robot can wait a few seconds then reverse with Fuel to the low boiler
    }
}
