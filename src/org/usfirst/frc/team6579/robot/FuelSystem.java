package org.usfirst.frc.team6579.robot;


import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;


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
	
	private static final double MOTOR_POWER = 0.2;
	private static final double STOP_MOTOR = 0;
	/**
	 * This is the method for moving the fuel flap
	 * motor is in port 5
	 * Top limit switch is port 8
	 * Bottom limit switch is port 9
	 */
	public FuelSystem(){
		
		//
		fuelMotor = new VictorSP(5);
		upLimitSwitch = new DigitalInput(8);
		downLimitSwitch = new DigitalInput(9);	
	
	/**
	 * This moves the flap up
	 */
	}
	public void raiseFlap(){
	    //this moves the flap up
		fuelMotor.set(MOTOR_POWER);
        while (upLimitSwitch.get()){
        	fuelMotor.set(STOP_MOTOR);
        }
	}
	
	/**
	 * This lowers the flap
	 */
	public void lowerFlap(){
		//this moves the flap down
		fuelMotor.set(-MOTOR_POWER);
		
		//this stops the motor once it hits the down limit switch (port 9)
		while (downLimitSwitch.get()){
			fuelMotor.set(STOP_MOTOR);
		}
	}
	
}