package org.usfirst.frc.team6579.robot;

public class DriveControlSelection {
	
	private DriveControl currentDriveControl = new TestDriveControl();
	
	public DriveControl getCurrentDriveControl() {
		return currentDriveControl;
	}
	protected void setCurrentDriveControl(DriveControl currentDriveControl) {
		this.currentDriveControl = currentDriveControl;
	}
	
}
