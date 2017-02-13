package org.usfirst.frc.team6579.robot.drivecontrol;

import edu.wpi.first.wpilibj.Joystick;
import org.usfirst.frc.team6579.robot.Climber;
import org.usfirst.frc.team6579.robot.Drivetrain;
import org.usfirst.frc.team6579.robot.FuelSystem;
import org.usfirst.frc.team6579.robot.Robot;

/**
 * this is the drive control that gives commands to the robot
 */
public class DriveControl {


    protected Joystick stick;


    /**
     * This initialises the DriveControl object
     */
    public DriveControl()
    {
        stick = new Joystick(0);
    }

    /**
     * This is the method that gives commands to the robot
     * This is called only in teleop
     * This should give commands to the robot and it's subsystems
     * @param robot
     */
    public void giveCommands(Robot robot)
    {
        //This is declaring the different subsystems
        arcadeDrive(robot.getDrivetrain());
        manageClimber(robot.getClimber());
        manageFuelFlap(robot.getFuelSystem());
    }


    /**
     * Drives the robot with arcade drive in test mode
     */
    protected void arcadeDrive(Drivetrain drivetrain)
    {
        double stickX;
        double stickY;
        stickX = stick.getX();
        stickY = stick.getY();

        drivetrain.setPower((stickY - stickX),(stickY + stickX));

    }

    /**
     * This method controls the climber.
     * Spins climber clockwise using button 7
     * Controls power via stick throttle
     * @param climber
     */
    protected void manageClimber(Climber climber)
    {
        double stickThrottle;

        stickThrottle = ((-stick.getThrottle() + 1)/2);

        if (stick.getRawButton(7))
        {
            climber.setPower(-stickThrottle);
        }
        else
        {
            climber.setPower(0);
        }
        Robot.displayValue("Joystick Throttle", stickThrottle);
    }

    /**
     * This method controls the fuel flap.
     * Raises flap at 50% power using button 3, and lowers at 50% using button 4
     * Power is set in the climber methos as well as the limit switches
     * @param fuelSystem
     */
    protected void manageFuelFlap(FuelSystem fuelSystem){

        //ToDo: control the method for lowering to the offloading height once created

        if (stick.getRawButton(3)){
            fuelSystem.raiseFlap();
        }
        else if (stick.getRawButton(4)){
            fuelSystem.offloadFuelFlap();

        }
        else if (stick.getRawButton(7)){
            fuelSystem.fullyLowerFlap();
        }

        else{
            fuelSystem.stopFuelFlap();
        }
    }
}
