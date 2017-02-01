package org.usfirst.frc.team6579.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;

public class LachlanControl extends DriveControl {
	
	// Define Controllers
	Joystick joystick;
	XboxController xbox; 
	
	Robot robot;
	
	// Controller Values
	double xboxStickLeft;
	double xboxStickRight;
	double joystickX;
	double joystickY;
	// Modes
	boolean tankDrive;
	boolean invertedMode;
	
	
	public LachlanControl(Robot robotInit){
		// Initialize Controllers
		xbox = new XboxController(1);
		joystick = new Joystick(2);
		
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
		if (joystick.getRawButton(8))
		{
			climber.setPower(1);
		}
		else if (joystick.getRawButton(7))
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
		if(xbox.getAButton())
		{
			tankDrive = false;
			System.out.println("Changing mode... ");
		}
		if(joystick.getRawButton(8))
		{
			tankDrive = true;
			System.out.println("Changing mode... ");
		}
	}
	
	private void tankDrive()
	{
		xboxStickLeft = xbox.getY(Hand.kLeft);
		xboxStickRight = xbox.getY(Hand.kRight);
		robot.drivetrain.setPower(xboxStickLeft, xboxStickRight);
	
	}	
	private void arcadeDrive()
	{
		joystickX = joystick.getX();
		joystickY = joystick.getY();
		if(invertedMode)
		{
			robot.drivetrain.setPower(joystickY-joystickX,joystickY+joystickX);
		}
		else
		{
			robot.drivetrain.setPower(-(joystickY-joystickX),-(joystickY+joystickX));
			
		}
	}
	
}
