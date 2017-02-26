package org.usfirst.frc.team6579.robot.autonomous;


import edu.wpi.first.wpilibj.Timer;

/**
 * Created by Jiah on 26/2/17.
 */
public abstract class AutoStrategy {

    private Timer timer = new Timer();

    public abstract void run();
    public void start(){
        timer.reset();
        timer.start();

    }

}
