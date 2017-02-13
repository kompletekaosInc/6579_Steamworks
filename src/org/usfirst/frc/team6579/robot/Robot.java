package org.usfirst.frc.team6579.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6579.robot.drivecontrol.DriveControl;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot 
{



    // subsystems
	private Drivetrain drivetrain = new Drivetrain();
	private Climber climber = new Climber();   // Never Tested
	private FuelSystem fuelSystem = new FuelSystem();
	
	
	// attributes
	
	private Timer timer = new Timer();

	private DriveControl driveControl;
	
    private ADXRS450_Gyro gyro = null;

	private Camera camera = new Camera();
	
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		//gyro.calibrate(); Takes a long time, will have to test if necessary
		try {
            gyro = new ADXRS450_Gyro();
            Robot.displayValue("Gyro Installed", "yes");
    		gyro.reset(); // Reset the angle the gyro is pointing towards to 0

        } catch (Exception e) {
            System.out.println("Gyro not installed correctly" + e.toString());
            Robot.displayValue("Gyro Installed", "no");
        }

		// In future, create selector for if there is more than one drive control
		driveControl = new DriveControl();

	}

	/**
	 * This function is run once each time the robot enters autonomous mode
	 */
	@Override
	public void autonomousInit() {
		// Example code to start timer for counting drive forward time 
		timer.reset(); 
		timer.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		// Example code for driving for 2 seconds
		if (timer.get() < 2.0) {
			drivetrain.setPower(0.5, 0.5);
		} 
		else {
			drivetrain.setPower(0, 0);
		}
		displayValue("Timer", timer.get());
	}

	/**
	 * This function is called once each time the robot enters tele-operated
	 * mode
	 */
	@Override
	public void teleopInit() {
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		// TODO: fix, this is short term HACK to ensure that the switch values are displayed with updates through out teleop
		fuelSystem.getSwitchStatus();
		
		driveControl.giveCommands(this); // Give control of the robot to the driveControl object
		displayValue("Gyro angle", gyro.getAngle());
		

	}
	
	// Method for displaying information on the smart dashboard
	public static void displayValue(String name, Object value) {
		SmartDashboard.putString(name, value.toString());
	}

	// simple accessor methods

    public Drivetrain getDrivetrain() {
        return drivetrain;
    }

    public Climber getClimber() {
        return climber;
    }

    public FuelSystem getFuelSystem() {
        return fuelSystem;
    }
}
