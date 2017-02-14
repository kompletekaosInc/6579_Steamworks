package org.usfirst.frc.team6579.robot.subsystem;

import edu.wpi.first.wpilibj.VictorSP;
import org.usfirst.frc.team6579.robot.subsystem.SubSystem;

/**
 * This class is the drive train class. all attributes and objects of the drivetrain belong here.
 * This class owns PWM ports 0,1 (left hand side of the robot) ,2 and 3 (right hand side of the robot). All four of these motor controllers are VictorSP speed controllers.
 * The methods currently in this class are setPower, and setInvertedPower.
 */
public class Drivetrain implements SubSystem {
	
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

    /**
     * This is the method used to move the drivetrain, used by arcade drive currently.
     * @param leftPower
     * @param rightPower
     */
	public void setPower(double leftPower, double rightPower)
	{
	    //sets the left motor controllers
		leftA.set(-leftPower);
		leftB.set(-leftPower);
		//sets the right motor controllers
		rightA.set(rightPower);
		rightB.set(rightPower);
		
	}

    /**
     * This method inverts the motor powers, therefor rotation the drive direction of the robot
     * @param leftPower
     * This is input from the joystick to give the left and right powers
     * @param rightPower
     */
	public void setInvertedPower(double leftPower, double rightPower){

	    //sets the left motor controllers
	    leftA.set(leftPower);
	    leftB.set(leftPower);
	    //sets the right motor controllers
        rightA.set(-rightPower);
        rightB.set(-rightPower);
	}

    @Override
    public void publishStats() {

    }
}
