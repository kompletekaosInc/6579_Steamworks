package org.usfirst.frc.team6579.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;

public class DriveControlSelection {
	
	Robot robot;
	JonahControl jonahControl;
	LachlanControl lachlanControl;
	JiahControl jiahControl;
		
	driveControl currentControlType;
	
	public enum driveControl {
		JonahControl, JiahControl,LachlanControl, JoshControl
	}
	
	public DriveControlSelection(){
		currentControlType = driveControl.JonahControl;
		initDriveControls();
		currentControlType = driveControl.JonahControl;
	}	
	public DriveControlSelection(Robot robotInit){
		robot = robotInit;
		initDriveControls();
		currentControlType = driveControl.JonahControl;		
	}
	private void initDriveControls()
	{
		jonahControl = new JonahControl(robot);
		lachlanControl = new LachlanControl(robot);
		jiahControl = new JiahControl(robot);
	}
	public void setControl(driveControl controlType)
	{
		currentControlType = controlType;
	}
	
	// Drive function called periodically
	public void giveCommands(Robot robot)
	{
		if (currentControlType == driveControl.JonahControl)
		{
			jonahControl.giveCommands(robot);
		}
		else if (currentControlType == driveControl.LachlanControl)
		{
			lachlanControl.giveCommands(robot);
		}
		else if (currentControlType == driveControl.JiahControl)
		{
			jiahControl.giveCommands(robot);
		}
	}
	public DriveControl returnCurrentType()
	{
		if (currentControlType == driveControl.JonahControl)
		{
			return jonahControl;
		}
		else if (currentControlType == driveControl.LachlanControl)
		{
			return lachlanControl;
		}
		else if (currentControlType == driveControl.JiahControl)
		{
			return jiahControl;
		}
		return jonahControl;
	}
	

	

	
}
