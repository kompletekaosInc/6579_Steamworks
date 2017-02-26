package org.usfirst.frc.team6579.robot.autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6579.robot.Robot;

/**
 * Created by Jiah on 26/2/17.
 */
public class TestStrategy2 extends AutoStrategy {

    public void run(Robot robot) {

        SmartDashboard.putString("Auton selection", this.toString());

    }
}
