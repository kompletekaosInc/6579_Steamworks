package org.usfirst.frc.team6579.robot.subsystem;

/**
 * This interface can be used to identify a SubSystem.
 *
 * Created by Jiah on 14/2/17.
 */
public interface SubSystem {

    /**
     * Any SubSystem must be able to publish statistics about itself when requested.
     * These statistics should be published to the SmartDashboard.
     */
    public void publishStats();
}
