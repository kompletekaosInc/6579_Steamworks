package org.usfirst.frc.team6579.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6579.robot.autonomous.*;
import org.usfirst.frc.team6579.robot.drivecontrol.DriveControl;
import org.usfirst.frc.team6579.robot.drivecontrol.DebugJoystickDriveControl;
import org.usfirst.frc.team6579.robot.drivecontrol.SteamworksJoystickDriveControl;
import org.usfirst.frc.team6579.robot.subsystem.Climber;
import org.usfirst.frc.team6579.robot.subsystem.Drivetrain;
import org.usfirst.frc.team6579.robot.subsystem.FuelSystem;
import org.usfirst.frc.team6579.robot.subsystem.SubSystem;
import org.usfirst.frc.team6579.robot.subsystem.vision.RobotVision;


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

    public static boolean debug = false;


    // subsystems
	private Drivetrain drivetrain = null;
	private Climber climber = null;
	private FuelSystem fuelSystem = null;
	//Tracking
	private RobotVision vision;



	// manage a collection of all the subsystems for the robot
	private List subSystems = new ArrayList();

	
	// attributes
    //private UsbCamera camera = null;

	private DriveControl driveControl;
    //Joystick selector
    private SendableChooser stickChoice;


	//private Camera camera = null;// 14/02

    //Autonomous strategy selector
    private AutoStrategy autoStrategy;
    private SendableChooser autoChooser;




	
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
            vision = new RobotVision();

			subSystems.add( drivetrain );
			subSystems.add( climber );
			subSystems.add( fuelSystem );
			subSystems.add( vision );



        } catch (Exception e) {
			System.out.println("Error loading sub-systems");
			e.printStackTrace();


		}




		// In future, create selector for if there is more than one drive control
		driveControl = new SteamworksJoystickDriveControl(); //DebugJoystickDriveControl(); // we only have one implementation, once we have more we will make a selector

        //Stick selector
        //populateDriveControlSelector();
        //driveControl = (DriveControl) stickChoice.getSelected();

        //Auto selector
        populateAutoSelector();


        // publish stats to the smart dashboard for all known subsystems
		publishSubSystemStats();  // TODO: in future create a new thread / period to constantly call this from this point onwards
	}

    /**
     * This is the joystick selector. Add all joysticks here. Default should always be SteamworksJoystickDriveControl.
     */
	private void populateDriveControlSelector(){
	    //Adds a default to the joystick selector
	    stickChoice.addDefault("Steamworks Joystick Drive Control", new SteamworksJoystickDriveControl());
	    //Adds another option to the joystick selector
        stickChoice.addObject("Debug Joystick Drive Control", new DebugJoystickDriveControl());

        //Puts the selector into SmartDashboard
        SmartDashboard.putData("Joystick Selector", stickChoice);

    }

    /**
     * This is the autonomous strategy selector. Add all new strategies here.
     */
    private void populateAutoSelector() {
        //populating chooser and sending to dashboard
        autoChooser = new SendableChooser();
        //Picks a default autonomous strategy
        autoChooser.addDefault("Middle Peg Auto", new MiddlePegAuto());
        //Adds another object to the chooser
        autoChooser.addObject("Right Peg Auto", new RightPegAuto());
        //Adds another object to the chooser
        autoChooser.addObject("Left Peg Autonomous", new LeftPegAuto());

		autoChooser.addObject("Left Peg then Fuel Autonomous", new LeftPegThenFuelAuto());

        //Puts to SmartDashboard
        SmartDashboard.putData("Autonomous Code Selector", autoChooser);
        //SmartDashboard.putData("Autonomous code selector", autoChooser);

    }

    /**
	 * This function is run once each time the robot enters autonomous mode
	 */
	@Override
	public void autonomousInit() {
	    autoStrategy = (AutoStrategy) autoChooser.getSelected();
        autoStrategy.start(this);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		//Publishes the subsystem status'
		publishSubSystemStats();
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
		//publishes the stats of the various subsystems
		publishSubSystemStats();

        vision.processImageInPipeline();

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

    public Drivetrain getDrivetrain() {
        return drivetrain;
    }

    public Climber getClimber() {
        return climber;
    }

    public FuelSystem getFuelSystem() {
        return fuelSystem;
    }

    public RobotVision getVision() {
        return vision;
    }

    //Vision

    /**
     * This function is for aligning to the vision tape on the peg.
     */
    public void followX( double basePower ){

	    double powerIncrement;
	    powerIncrement = getVision().getPegX();
	    double leftPower = basePower;
	    double rightPower = basePower;

	    if (powerIncrement > 0)
	    {
	        // the tape is to the right side of the robot
            // more power to left motor
            leftPower = basePower + (powerIncrement);
	        //drivetrain.setPower(-finalPosition , 0);
        }
        else if (powerIncrement < 0)
        {
	        // the tape is on the left side of the robot
            // more power to right motor
            rightPower = basePower + Math.abs(powerIncrement);
            //drivetrain.setPower(0, finalPosition);
        }
        else
        {
            // the robot is directly in front of the tape
            // we are on target, just use basePower
        }

        drivetrain.setPower(leftPower, rightPower);
    }
}
