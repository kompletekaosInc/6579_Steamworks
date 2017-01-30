package org.usfirst.frc.team6579.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Joystick;

public class JonahControl {
	
	// Define Controllers
	Joystick joystick;
	XboxController xbox; 
	
	Robot robot;
	
	// Controller Values
	double xboxStickLeftX;
	double xboxStickLeftY;
	double xboxStickRight;

	// Modes
	boolean tankDrive;
	boolean invertedMode;
	
	
	public JonahControl(Robot robotInit){
		// Initialize Controllers
		xbox = new XboxController(1);
	
		invertedMode = false;
		tankDrive = true;
		robot = robotInit;
		
	}
	
	// Drive function called periodically
	public void giveCommands(Robot robot)
	{
		drive(robot.drivetrain);
		climb(robot.climber);
	}
	public void climb(Climber climber)
	{
		if (xbox.getBButton())
		{
			climber.setPower(1);
		}
		else if (xbox.getYButton())
		{
			climber.setPower(-1);
		}
		else
		{
			climber.setPower(0);
		}
	}
	public void drive(Drivetrain drivetrain)
	{
		if (tankDrive)
		{
			tankDrive();
		}
		else
		{
			arcadeDrive();
		}
		if(xbox.getBackButton() && tankDrive == true)
		{
			tankDrive = false;
			System.out.println("Changing to arcade drive");
		}
		if(xbox.getStartButton() && tankDrive == false)
		{
			tankDrive = true;
			System.out.println("Changing to tank drive");
		}
		if(xbox.getRawButton(9) && invertedMode == false)
		{
			invertedMode = true;
			System.out.println("Changing to inverted mode");
		}
		if(xbox.getRawButton(10) && invertedMode == true)
		{
			invertedMode = false;
			System.out.println("Changing from inverted mode");
		}
	}
	
	private void tankDrive()
	{
		xboxStickLeftX = xbox.getY(Hand.kLeft);
		xboxStickRight = xbox.getY(Hand.kRight);
		
		if(invertedMode)
		{
			robot.drivetrain.setPower(-xboxStickLeftX, -xboxStickRight);
		}
		else
		{
			robot.drivetrain.setPower(xboxStickLeftX, xboxStickRight);
		}
	
	}	
	private void arcadeDrive()
	{
		xboxStickLeftX = xbox.getX(Hand.kLeft);
		xboxStickLeftY = xbox.getY(Hand.kLeft);
		if(invertedMode)
		{
			robot.drivetrain.setPower(-(xboxStickLeftY - xboxStickLeftX), -(xboxStickLeftY + xboxStickLeftX));
		}
		else
		{
			robot.drivetrain.setPower(xboxStickLeftY - xboxStickLeftX, xboxStickLeftY + xboxStickLeftX);
		}
		
	}
	
}
