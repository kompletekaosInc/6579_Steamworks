package org.usfirst.frc.team6579.robot.subsystem;

/**
 * This class is for controlling the climber module
 */
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6579.robot.subsystem.SubSystem;

public class Climber implements SubSystem{

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
		SmartDashboard.putNumber("Climber Speed", power);
	}


	@Override
	public void publishStats() {

	}
}
