package org.usfirst.frc.team6579.robot.autonomous;


import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc.team6579.robot.Robot;


/**
 * An abstract class defining what an autonomous strategy should look like
 * Created by Jiah on 26/2/17.
 */
public abstract class AutoStrategy {

    protected Timer timer = new Timer();

    /**
     * This class is the actual autonomous code running
     * @param robot
     */
    public abstract void run(Robot robot);

    /**
     * This code is basically auto.init for the timer and auto strategy side of the world.
     */
    public void start(){
        timer.reset();
        timer.start();

    }

}
