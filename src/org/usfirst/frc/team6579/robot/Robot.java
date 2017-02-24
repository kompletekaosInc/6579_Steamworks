package org.usfirst.frc.team6579.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6579.robot.drivecontrol.DriveControl;
import org.usfirst.frc.team6579.robot.subsystem.Climber;
import org.usfirst.frc.team6579.robot.subsystem.Drivetrain;
import org.usfirst.frc.team6579.robot.subsystem.FuelSystem;
import org.usfirst.frc.team6579.robot.subsystem.SubSystem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
    private Drivetrain drivetrain = null;
    private Climber climber = null;
    private FuelSystem fuelSystem = null;


    // manage a collection of all the subsystems for the robot
    private List subSystems = new ArrayList();


    // attributes

    private Timer timer = new Timer();

    private DriveControl driveControl;



    //private Camera camera = new Camera(); 14/02


    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {

        try {
            // manage the collection of SubSystems
            drivetrain = new Drivetrain();
            climber = new Climber();
            fuelSystem = new FuelSystem();

            subSystems.add( drivetrain );
            subSystems.add( climber );
            subSystems.add( fuelSystem );



        } catch (Exception e) {
            System.out.println("Error loading sub-systems");
            e.printStackTrace();


        }

<<<<<<< HEAD
		// In future, create selector for if there is more than one drive control
		driveControl = new DriveControl();


		// publish stats to the smart dashboard for all known subsystems
		publishSubSystemStats();  // TODO: in future create a new thread / period to constantly call this from this point onwards
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

		//Publishes the subsystem status'
		publishSubSystemStats();

		// Example code for driving for 2 seconds
//		if (timer.get() < 2.0) {
//			drivetrain.setPower(0.5, 0.5);
//		}
//		else {
//			drivetrain.setPower(0, 0);
//		}
		SmartDashboard.putNumber("Timer", timer.get());
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
		//publishes the stats of the various subystems
		publishSubSystemStats();


		driveControl.giveCommands(this); // Give control of the robot to the driveControl object

		

	}

	/**
	 * Iterate the list of SubSystems and ask them to publish their stats.
	 */
	private void publishSubSystemStats()
	{
		Iterator i = subSystems.iterator();
		while (i.hasNext())
		{
			SubSystem nextSubSystem = (SubSystem) i.next();
			nextSubSystem.publishStats();
		}


	}
	

	// simple accessor methods
=======
        // In future, create selector for if there is more than one drive control
        driveControl = new DriveControl();


        // publish stats to the smart dashboard for all known subsystems
        publishSubSystemStats();  // TODO: in future create a new thread / period to constantly call this from this point onwards
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

        //Publishes the subsystem status'
        publishSubSystemStats();

        // Example code for driving for 2 seconds
        if (timer.get() < 2.0) {
            drivetrain.setPower(0.5, 0.5);
        }
        else {
            drivetrain.setPower(0, 0);
        }
        SmartDashboard.putNumber("Timer", timer.get());
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
        //publishes the stats of the various subystems
        publishSubSystemStats();


        driveControl.giveCommands(this); // Give control of the robot to the driveControl object



    }

    /**
     * Iterate the list of SubSystems and ask them to publish their stats.
     */
    private void publishSubSystemStats()
    {
        Iterator i = subSystems.iterator();
        while (i.hasNext())
        {
            SubSystem nextSubSystem = (SubSystem) i.next();
            nextSubSystem.publishStats();
        }


    }


    // simple accessor methods
>>>>>>> origin/master

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
