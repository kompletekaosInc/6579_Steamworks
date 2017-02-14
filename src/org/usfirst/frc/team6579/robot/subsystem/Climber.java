package org.usfirst.frc.team6579.robot.subsystem;

/**
 * This class is for controlling the climber module
 */
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6579.robot.subsystem.SubSystem;

public class Climber implements SubSystem{

	private Toughbox climberToughbox;

	/**
	 * This sets the toughbox motor ports to 8 & 9.
	 */
	public Climber()
	{
		climberToughbox = new Toughbox(8,9);
	}

	/**
	 * This sets the motor power for the climber toughbox
	 * @param power
	 */
	private void setPower(double power)
	{
		climberToughbox.set(power);

	}

	/**
	 * This method spins the colanders at the optimum speed for collecting a rope
	 */
	public void collectRope(){
		setPower(1);
	}

	/**
	 * This method spins the colanders at the optimum speed for climbing the rope
	 */
	public void climbRope(){
		setPower(0.8);
	}

	/**
	 * This method slowly reaches the top as to not destroy the davit
	 */
	public void touchDavit(){
		setPower(0.2);
	}

	/**
	 * This method stops the climber
	 */
	public void stop(){
		setPower(0);
	}


	@Override
	public void publishStats() {
		SmartDashboard.putNumber("Climber Speed",climberToughbox.get());
	}
}
