package org.usfirst.frc.team6579.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;

public class JonahControl implements DriveControl {
	
	// Define Controllers
	private Joystick joystick;
	private XboxController xbox; 
	
	
	// Controller Values
	private double xboxStickLeftX;
	private double xboxStickLeftY;
	private double xboxStickRight;

	// Modes
	boolean tankDrive;
	boolean invertedMode;
	
	
	public JonahControl(){
		// Initialize Controllers
		xbox = new XboxController(1);
	
		invertedMode = false;
		tankDrive = true;
		
	}
	
	// Drive function called periodically
	public void giveCommands(Robot robot)
	{
		drive(robot.drivetrain);
		climb(robot.climber);
		
		Robot.displayValue("Inverted", invertedMode);
		Robot.displayValue("Inverted", tankDrive);
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
		checkChangeMode();
		if (checkSmallAdjustment())
		{
			smallAdjustment();
		}
		else {
			if (tankDrive)
			{
				tankDrive();
			}
			else
			{
				arcadeDrive();
			}
		}

	}
	private boolean checkSmallAdjustment()
	{
		if(xbox.getBumper(Hand.kLeft))
		{
			return true;
		}
		if(xbox.getBumper(Hand.kRight))
		{
			return true;
		}
		return false;
	}
	private void smallAdjustment()
	{
		double leftSpeed = 0;
		double rightSpeed = 0;
		if(xbox.getBumper(Hand.kLeft))
		{
			leftSpeed = 0.3;
		}
		if(xbox.getBumper(Hand.kRight))
		{
			rightSpeed = 0.3;
		}
		if (invertedMode)
		{
			robot.drivetrain.setPower(leftSpeed, rightSpeed);
		}
		else{
			robot.drivetrain.setPower(-leftSpeed, -rightSpeed);
		}
	}
	private void checkChangeMode()
	{
		if(xbox.getRawButton(9) && tankDrive == true)
		{
			tankDrive = false;
			System.out.println("Changing to arcade drive");
		}
		if(xbox.getRawButton(10) && tankDrive == false)
		{
			tankDrive = true;
			System.out.println("Changing to tank drive");
		}
		if(xbox.getStartButton() && invertedMode == false)
		{
			invertedMode = true;
			System.out.println("Changing to inverted mode");
		}
		if(xbox.getBackButton() && invertedMode == true)
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
