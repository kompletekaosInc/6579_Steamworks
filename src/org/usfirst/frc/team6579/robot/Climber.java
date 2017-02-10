package org.usfirst.frc.team6579.robot;

/**
 * This class is for controlling the climber module
 */
import edu.wpi.first.wpilibj.VictorSP;

public class Climber {

	private VictorSP climbA;
	private VictorSP climbB;
		
	public Climber()
	{
		climbA = new VictorSP(8);
		climbB = new VictorSP(9);
	}
	
	public void setPower(double power)
	{
		climbA.set(power);
		climbB.set(power);
		Robot.displayValue("Climber Speed", power);
	}
		
}
