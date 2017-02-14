package org.usfirst.frc.team6579.robot.drivecontrol;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import org.usfirst.frc.team6579.robot.subsystem.Climber;
import org.usfirst.frc.team6579.robot.subsystem.Drivetrain;
import org.usfirst.frc.team6579.robot.Robot;

public class LachlanControl extends DriveControl {
	
	// Define Controllers
	Joystick joystick;
	XboxController xbox; 
	
	
	// Controller Values
	double xboxStickLeft;
	double xboxStickRight;
	double joystickX;
	double joystickY;
	// Modes
	boolean tankDrive;
	boolean invertedMode;
	
	
	public LachlanControl(){
		// Initialize Controllers
		xbox = new XboxController(1);
		joystick = new Joystick(2);
		
		tankDrive = true;
	
		
	}
	
	// Drive function called periodically
	public void giveCommands(Robot robot)
	{
		drive(robot.getDrivetrain());
		climb(robot.getClimber());
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
			tankDrive(drivetrain);
		}
		else
		{
			arcadeDrive(drivetrain);
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
	
	private void tankDrive(Drivetrain drivetrain)
	{
		xboxStickLeft = xbox.getY(Hand.kLeft);
		xboxStickRight = xbox.getY(Hand.kRight);
		drivetrain.setPower(xboxStickLeft, xboxStickRight);
	
	}	
	protected void arcadeDrive(Drivetrain drivetrain)
	{
		joystickX = joystick.getX();
		joystickY = joystick.getY();
		if(invertedMode)
		{
			drivetrain.setPower(joystickY-joystickX,joystickY+joystickX);
		}
		else
		{
			drivetrain.setPower(-(joystickY-joystickX),-(joystickY+joystickX));
			
		}
	}
	
}
