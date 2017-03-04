package org.usfirst.frc.team6579.robot.subsystem;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6579.robot.subsystem.SubSystem;

/**
 * This class is the drive train class. all attributes and objects of the drivetrain belong here.
 * This class owns PWM ports 0,1 (left hand side of the robot) ,2 and 3 (right hand side of the robot). All four of these motor controllers are VictorSP speed controllers.
 *
 */
public class Drivetrain implements SubSystem {
	
	// Define driveTrain motor controllers

	// the two drivetrain toughboxes
	private Toughbox toughbox1 = new Toughbox(0,1);;
    private Toughbox toughbox2 = new Toughbox(2,3);;

    //Default the Gear is the front of robot
    private boolean frontIsGear = true;
    //Left drive
    private Toughbox leftToughbox = toughbox1;

	//Right drive
    private Toughbox rightToughbox = toughbox2;


	//Gyro
    private ADXRS450_Gyro gyro = null;


    /**
     * Declaration of class, tries for gyro and sets the gear side as the default front of robot
     */
	public Drivetrain() {

		try {
            gyro = new ADXRS450_Gyro();
            SmartDashboard.putBoolean("Gyro Installed", true);

            //calibrates and resets the gyro to 0
            gyro.reset(); // Reset the angle the gyro is pointing towards to 0
            gyro.calibrate(); //Takes a long time, will have to test if necessary
        }
        catch (Exception e)
        {
            System.out.println("Gyro not installed correctly" + e.toString());
            SmartDashboard.putBoolean("Gyro Installed", false);
        }

        // set the direction of the drivetrain
        setGearFront();
	}

    /**
     * This method makes the front of the robot the gear side
     */
	public void setGearFront(){
	    leftToughbox = toughbox1;
	    rightToughbox = toughbox2;

	    frontIsGear = true;
    }

    public boolean isFrontIsGear() {
        return frontIsGear;
    }

    /**
     * This method makes the fuel flap and climber side of the robot the front
     */
    public void setFuelFront(){
	    leftToughbox = toughbox2;
	    rightToughbox = toughbox1;

	    frontIsGear = false;

    }

    /**
     * This is the method used to move the drivetrain, used by arcade drive currently.
     * @param leftPower
     * @param rightPower
     */
	public void setPower(double leftPower, double rightPower)
	{
	    //sets the left motor controllers
		leftToughbox.set(leftPower);
		//sets the right motor controllers
		rightToughbox.set(-rightPower);
		
	}

	public void stop(){
	    setPower(0,0);
    }

    /**
     * This method makes the robot stop on the spot.
     */
    public void hardStop(){
        double leftStopPower = 0.1;
        double rightStopPower = 0.1;

        if (leftToughbox.get()>0){
	        leftStopPower = -0.1;

        }
        else{
            leftStopPower = 0.1;
        }

        if (rightToughbox.get() > 0){
            rightStopPower = -0.1;
        }
        else{
            rightStopPower = 0.1;
        }
        SmartDashboard.putNumber("leftStopPower",leftStopPower);
        SmartDashboard.putNumber("rightStopPower",rightStopPower);
	    setPower(leftStopPower,rightStopPower);

    }

    /**
     * Follows an angle off the gyro at a driver defined speed
     * @param power
     * @param gyroTarget
     */
    public void followGyro(double power, double gyroTarget)
    {
        // ToDo: fill in this method
        //proportionally drives in the direction of a gyro heading, turning to face the right direction
        double currentGyroAngle = getGyroAngle() % 360;
        double gyroPowerAdjustment = 0;
        double gyroGain = 0.05;


        //Calculates how much to turn based on the current heading and the target heading
        gyroPowerAdjustment = currentGyroAngle - (gyroTarget % 360);
        gyroPowerAdjustment = gyroPowerAdjustment * gyroGain;

        double gyroMotorPowerLeft = power - gyroPowerAdjustment;
        double gyroMotorPowerRight = -power - gyroPowerAdjustment;

        //Makes the motors move
        leftToughbox.set(gyroMotorPowerLeft);
        rightToughbox.set(gyroMotorPowerRight);
    }

    /**
     * This method turns the robot to a degree defined by the user
     */
    public void gyroTurnLeft(double targetAngle){
        double currentGyroAngle = (getGyroAngle() % 360); //ToDo: Get this to work!! start to figure out why the refresh won't work and figure out mod command ("%")
        double angleToTurn = currentGyroAngle + targetAngle;

        while ((angleToTurn - getModGyroAngle()) >= 70) {
            setPower(-0.5, 0.5);
        }
        stop();

        while ((angleToTurn - getModGyroAngle()) > 1) {
            setPower(-0.25, 0.25);
        }

        stop();
    }


    /**
     * Gets the current gyro angle
     */
    public double getGyroAngle(){
        publishStats();
        double gyroAngle = 0;

        //error handling for if there is no gyro
        if (gyro != null){
            gyroAngle = gyro.getAngle();
        }
        return gyroAngle;
    }

    /**
     * Gets the moderated gyro angle (makes values between -360 and 360)
     * @return
     */
    public double getModGyroAngle(){
        return getGyroAngle() % 360;
    }

    /**
     * Allows the gyro value to be set to 0
     */
    public void resetGyro(){
        gyro.reset();//resets gyro value to 0
    }


    /**
     * Method required by SubSystem for this class to publish important statistics
     */
    @Override
    public void publishStats() {
        SmartDashboard.putNumber("Gyro Angle", gyro.getAngle());
        SmartDashboard.putBoolean("frontIsGear", frontIsGear);

        SmartDashboard.putNumber("leftToughbox", leftToughbox.get());
        SmartDashboard.putNumber("rightToughbox", rightToughbox.get());

    }
}
