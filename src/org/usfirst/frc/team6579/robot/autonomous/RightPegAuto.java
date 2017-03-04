package org.usfirst.frc.team6579.robot.autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6579.robot.Robot;

/**
 * Created by Jiah on 4/3/17.
 */
public class RightPegAuto extends AutoStrategy{
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

        else if (timer.get() <= 3){
            robot.getDrivetrain().gyroTurnLeft(60);
        }
        else if (timer.get() <= 4){
            robot.getVision().startTrackingPeg();
        }
        else if (timer.get() <= 7){
            robot.followX(0.2);
        }
        else if (timer.get() <= 8){
            robot.getDrivetrain().stop();
        }


    }

}
