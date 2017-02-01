package org.usfirst.frc.team6579.robot;

import edu.wpi.first.wpilibj.VictorSP;

public class Climber {
	//Left drive
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
		}
		
}