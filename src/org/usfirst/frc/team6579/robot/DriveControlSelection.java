package org.usfirst.frc.team6579.robot;

public class DriveControlSelection {
	
	private DriveControl currentDriveControl = new TestDriveControl();
		
	//driveControl currentControlType = new LachlanControl();
	




//	public enum driveControl {
//		JonahControl, JiahControl,LachlanControl, JoshControl
//	}
	
//	public DriveControlSelection(){
//		currentControlType = driveControl.JonahControl;
//		initDriveControls();
//		currentControlType = driveControl.JonahControl;
//	}	
//	public DriveControlSelection(Robot robotInit){
//		robot = robotInit;
//		initDriveControls();
//		currentControlType = driveControl.JonahControl;		
//	}
//	private void initDriveControls()
//	{
//		jonahControl = new JonahControl(robot);
//		lachlanControl = new LachlanControl(robot);
//		jiahControl = new JiahControl(robot);
//	}
//	public void setControl(driveControl controlType)
//	{
//		currentControlType = controlType;
//	}

//	public DriveControl returnCurrentType()
//	{
//		if (currentControlType == driveControl.JonahControl)
//		{
//			return jonahControl;
//		}
//		else if (currentControlType == driveControl.LachlanControl)
//		{
//			return lachlanControl;
//		}
//		else if (currentControlType == driveControl.JiahControl)
//		{
//			return jiahControl;
//		}
//		return jonahControl;
//	}
	

	
	
	
	public DriveControl getCurrentDriveControl() {
		return currentDriveControl;
	}
	protected void setCurrentDriveControl(DriveControl currentDriveControl) {
		this.currentDriveControl = currentDriveControl;
	}
	
}
