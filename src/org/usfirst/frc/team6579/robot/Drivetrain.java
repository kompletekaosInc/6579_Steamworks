package org.usfirst.frc.team6579.robot;

import edu.wpi.first.wpilibj.VictorSP;
public class Drivetrain {
	
	// Define driveTrain motor controllers

	//Left drive
	private VictorSP leftA;
	private VictorSP leftB;

	//Right drive
	private VictorSP rightA;
	private VictorSP rightB;
	

	
	public Drivetrain() {
		// Initialize drivetrain motor controllers
		// Left drive
		leftA = new VictorSP(0);
		leftB = new VictorSP(1);
		// Right drive
		rightA = new VictorSP(2);
		rightB = new VictorSP(3);
		
	}
	
	public void setPower(double leftPower, double rightPower)
	{
		leftA.set(-leftPower);
		leftB.set(-leftPower);
		rightA.set(rightPower);
		rightB.set(rightPower);
		
	}

}
