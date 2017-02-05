package org.usfirst.frc.team6579.robot;


/** This class is for testing the features of the robot. It requires a joystick in port 0
 * 
 * @author Lachlan
 *
 */

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TestDriveControl implements DriveControl 
{

	Joystick stick;
	double stickX;
	double stickY;
	double stickThrottle;
	
	/**
	 * This initialises the TestDriveCotrol object
	 */
	public TestDriveControl()
	{
		stick = new Joystick(0);
	}
	
	public void giveCommands(Robot robot) 
	{
		arcadeDrive(robot.drivetrain);
		climbing(robot.climber);
		manageFuelFlap(robot.fuelSystem);
	}


	/**
	 * Drives the robot with arcade drive in test mode
	 */
	private void arcadeDrive(Drivetrain drivetrain)
	{
		stickX = stick.getX();
		stickY = stick.getY();
		
		drivetrain.setPower((stickY - stickX),(stickY + stickX));
			
	}
	
	private void climbing(Climber climber)
	{
		stickThrottle = ((-stick.getThrottle() + 1)/2);
		//if (stick.getRawButton(8))
		//{
		//	climber.setPower(stickThrottle);
		//}		
		if (stick.getRawButton(7))
		{
			climber.setPower(-stickThrottle);
		}
		else
		{
			climber.setPower(0);
		}
		SmartDashboard.putNumber("throttle",stickThrottle);
	}

	private void manageFuelFlap(FuelSystem fuelSystem){
		if (stick.getRawButton(3)){
			fuelSystem.raiseFlap();
		}
		else if (stick.getRawButton(4)){
			fuelSystem.lowerFlap();
		}
		else{
			fuelSystem.stopFuelFlap();
		}
	}
}
