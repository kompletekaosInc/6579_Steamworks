package org.usfirst.frc.team6579.robot.autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6579.robot.Robot;


/**
 * A test autonomous strategy, used as default in the autonomous selector.
 * Created by Jiah on 26/2/17.
 */
public class TestStrategy extends AutoStrategy {

    /**
     * Logic for running autonomous
     * @param robot
     */
    public void run(Robot robot) {
        SmartDashboard.putString("Auton selection", this.getClass().toString());

        if (timer.get() < 2){
            //robot.getDrivetrain().setPower(-0.4,-0.4);

            robot.getDrivetrain().resetGyro();
            double followAngle = robot.getDrivetrain().getModGyroAngle();

            robot.getDrivetrain().followGyro(0.4,followAngle);


        }
        else if (timer.get() <=2.5){
            robot.getDrivetrain().hardStop();
        }
        else if (timer.get() <=2.7 ){
            robot.getDrivetrain().stop();
        }

        }

}
