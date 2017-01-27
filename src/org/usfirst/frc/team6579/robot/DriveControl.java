package org.usfirst.frc.team6579.robot;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
public class DriveControl {
	
	// Define drivetrain motor controllers

	//Left drive
	VictorSP leftA;
	VictorSP leftB;

	//Right drive
	VictorSP rightA;
	VictorSP rightB;
	
	// Define Controllers
	Joystick joystick;
	XboxController xbox; 
	
	// Controller Values
	double xboxStickLeft;
	double xboxStickRight;
	double joystickX;
	double joystickY;
	
	double leftPower;
	double rightPower;
	
	
	public void Drivetrain() {
		// Initialize drivetrain motor controllers
		// Left drive
		leftA = new VictorSP(0);
		leftB = new VictorSP(1);
		// Right drive
		rightA = new VictorSP(2);
		rightB = new VictorSP(3);
		// Initialize Controllers
		xbox = new XboxController(1);
		joystick = new Joystick(2);
	}
	
	public void tankDrive()
	{
		xboxStickLeft = xbox.getY(Hand.kLeft);
		xboxStickRight = xbox.getY(Hand.kRight);
		
		leftPower = xboxStickLeft;
		rightPower = xboxStickRight;
		
		// Power to Motors
		leftA.set(leftPower);
		leftB.set(leftPower);
		rightA.set(rightPower);
		rightB.set(rightPower);
		
	}
}
