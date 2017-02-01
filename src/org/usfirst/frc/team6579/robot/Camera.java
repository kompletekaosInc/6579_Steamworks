package org.usfirst.frc.team6579.robot;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.cscore.UsbCamera;

public class Camera {
	int currentCamera; // 0 is front, 1 is back
	UsbCamera cameraFront;
	UsbCamera cameraBack;
	public Camera()
	{
		currentCamera = 0; //Default camera
		try {
			startFrontCamera();
		}
		catch (Exception e){
			
		}

	}
	// TODO: Test code for switching between front and back camera to save bandwidth
	public void switchCamera()
	{
		if(currentCamera == 0)
		{
			cameraBack.free();
			startBackCamera();
		}
		else {
			cameraFront.free();
			startFrontCamera();
		}

	}
	
	private void startFrontCamera()
	{
		cameraFront = CameraServer.getInstance().startAutomaticCapture(currentCamera);
		cameraFront.setFPS(30);
		cameraFront.setResolution(320, 240);
	}
	private void startBackCamera()
	{
		cameraBack = CameraServer.getInstance().startAutomaticCapture(1-currentCamera);
		cameraBack.setFPS(30);
		cameraBack.setResolution(320, 240);
	}
}
