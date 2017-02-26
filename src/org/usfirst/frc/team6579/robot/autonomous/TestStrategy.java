package org.usfirst.frc.team6579.robot.autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6579.robot.Robot;


/**
 * Created by Jiah on 26/2/17.
 */
public class TestStrategy extends AutoStrategy {

    public void run(Robot robot) {
        SmartDashboard.putString("Auton selection", this.toString());

        if (timer.get() < 2){
            robot.getDrivetrain().setPower(0.2,0.2);
        }
        else if (timer.get() <=3 ){
            robot.getDrivetrain().stop();
        }

        }

}
