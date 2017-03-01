package org.usfirst.frc.team6579.robot.drivecontrol;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6579.robot.Robot;
import org.usfirst.frc.team6579.robot.subsystem.Drivetrain;

/**
 * This is a joystick class to override JoystickDriveControl
 * Created by Jiah on 24/2/17.
 */
public class SteamworksJoystickDriveControl extends JoystickDriveControl {

    private double takenAngle; //used for the followGyro method

    @Override
    protected void arcadeDrive(Drivetrain drivetrain) {

        if (stick.getRawButton(5)){
            //Arcade drive is disabled as we are using followGyro
            SmartDashboard.putBoolean("arcadeDrive",false);
        }
        else if (stick.getRawButton(10)){
            //turning with gyro
            SmartDashboard.putBoolean("gyro turning",true);
            SmartDashboard.putBoolean("arcadeDrive",false);
        }
        else {
            super.arcadeDrive(drivetrain);
        }
    }

    /**
     * Only overrides the buttons actually being used
     * @param robot
     */
    protected void processThrottle(Robot robot )
    {
        if (stick.getThrottle()>0)
        {
            robot.getDrivetrain().setFuelFront();
        }
        else
        {
            robot.getDrivetrain().setGearFront();
        }
    }

    //These are the buttons that are overridden
    protected void processButton1(Robot robot )
    {
        //raise fuel flap
        robot.getVision().startTrackingPeg();
    }
    //These are the buttons that are overridden
    protected void processButton2(Robot robot )
    {
        //raise fuel flap
        robot.getVision().stopTrackingPeg();
    }
    protected void processButton3(Robot robot )
    {
        //raise fuel flap
        robot.getFuelSystem().raiseFlap();
    }
    protected void processButton4(Robot robot )
    {
        //lower fuel flap to offload
        robot.getFuelSystem().offloadFuelFlap();
    }
    protected void processButton5(Robot robot )
    {
        //drives with the followGyro method
        robot.getDrivetrain().followGyro((stick.getY()),takenAngle);
    }
    protected void processButton7(Robot robot )
    {
        //prepares for climber: lowers fuel flap all the way and spins colanders
        robot.getFuelSystem().fullyLowerFlap();
        robot.getClimber().collectRope();
    }
    protected void processButton8(Robot robot )
    {
        //climbs rope at climbing power
        robot.getClimber().climbRope();
    }
    protected void processButton9(Robot robot )
    {
        //touches the davit softly
        robot.getClimber().touchDavit();
    }
    protected void processButton10(Robot robot )
    {
        //turn 180 degrees
        robot.getDrivetrain().gyroTurnLeft(180);
    }
    protected void processButton11(Robot robot){
        robot.getDrivetrain().resetGyro();
    }
    protected void processButton12(Robot robot )
    {
        //Takes gyro angle
        takenAngle = robot.getDrivetrain().getGyroAngle();
        SmartDashboard.putNumber("takenAngle",takenAngle);
    }
    protected void processNoButtons(Robot robot )
    {
        robot.getFuelSystem().stopFuelFlap();
        robot.getClimber().stop();
    }
}
