package org.usfirst.frc.team6579.robot;


import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This is a class for the fuel system. 
 * This should have two buttons, up and down for the fuel flap. 
 * This uses PWM port 5
 * ToDo limit switches???
 * @author Jiah
 *
 */
public class FuelSystem {
	private VictorSP fuelMotor;
	private DigitalInput upLimitSwitch;
	private DigitalInput downLimitSwitch;
	
	private static final double MOTOR_POWER = 0.5;
	private static final double STOP_MOTOR = 0;
	
	private boolean upperLimitReached = true;
	private boolean lowerLimitReached = true;
	
	/**
	 * This is the method for moving the fuel flap
	 * motor is in port 5
	 * Top limit switch is port 8
	 * Bottom limit switch is port 9
	 */
	public FuelSystem(){
		
		//review post-it note 
		fuelMotor = new VictorSP(5);
		upLimitSwitch = new DigitalInput(8);
		downLimitSwitch = new DigitalInput(9);	
		
	}	
	
	public void displayStatus(){
		upperLimitReached = !upLimitSwitch.get();
		lowerLimitReached = !downLimitSwitch.get();
		
		SmartDashboard.putBoolean("upperLimitReached", upperLimitReached);
		SmartDashboard.putBoolean("lowerLimitReached", lowerLimitReached);
	}
	
	/**
	 * This moves the flap up
	 */
	public void raiseFlap(){
	    //this moves the flap up
		
		if (upperLimitReached){
			stopFuelFlap();
		}
		else {
			fuelMotor.set(-MOTOR_POWER);
		}  
	}
	
	/**
	 * This lowers the flap
	 */
	public void lowerFlap(){
		//this moves the flap down
		
		
		boolean lowerLimitReached = !downLimitSwitch.get(); //TRUE = flap at minimum
		
		SmartDashboard.putBoolean("lowerLimitReached", lowerLimitReached);
		
		if (lowerLimitReached){
			stopFuelFlap();
		}
		else {
			fuelMotor.set(MOTOR_POWER);
		}
	}
	public void stopFuelFlap(){
		fuelMotor.set(STOP_MOTOR);
	}
	
}