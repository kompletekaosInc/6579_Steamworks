package org.usfirst.frc.team6579.robot.autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6579.robot.Robot;

/**
 * Another test autonomous strategy to test the selector on the smart dashboard
 * Created by Jiah on 26/2/17.
 */
public class TestStrategy2 extends AutoStrategy {

    /**
     * This method is where all strategy code should be for doing autonomous
     * @param robot
     */
    public void run(Robot robot) {

        SmartDashboard.putString("Auton selection", this.toString());

    }
}
