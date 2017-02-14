package org.usfirst.frc.team6579.robot.drivecontrol;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6579.robot.subsystem.Climber;
import org.usfirst.frc.team6579.robot.subsystem.Drivetrain;
import org.usfirst.frc.team6579.robot.subsystem.FuelSystem;
import org.usfirst.frc.team6579.robot.Robot;

/**
 * this is the drive control that gives commands to the robot
 */
public class DriveControl {


    protected Joystick stick;

    private double takenAngle; //used for the followGyro method

    /**
     * This initialises the DriveControl object
     */
    public DriveControl()
    {
        stick = new Joystick(0);
    }

    /**
     * This is the method that gives commands to the robot
     * This is called only in teleop
     * This should give commands to the robot and it's subsystems
     * @param robot
     */
    public void giveCommands(Robot robot)
    {
        //Uses the joystick to use an arcade drive style drive control
        arcadeDrive(robot.getDrivetrain());

        //ToDo: look into parallel threads for separate button and driving controls to avoid potential lag in controls

        //This checks every button and makes actions based on activated buttons
        processStickButtons(robot);


    }


    /**
     * Drives the robot with arcade drive in test mode
     */
    protected void arcadeDrive(Drivetrain drivetrain)
    {
        if (stick.getRawButton(5)){
            //Arcade drive is disabled as we are using followGyro
            SmartDashboard.putBoolean("arcadeDrive",false);
        }
        else {
            SmartDashboard.putBoolean("arcadeDrive", true);
            //Gets the stick values
            double stickX = stick.getX();
            double stickY = stick.getY();

            //calculates the power to drive the robot ToDo: Consider capping the powers/powerband
            double leftPowerMath = (stickY - stickX);
            double rightPowerMath = (stickY + stickX);

            //puts the calculated power back to the drive train
            drivetrain.setPower(leftPowerMath, rightPowerMath);
        }

    }
    /**
     * This method is basically a scheduler of the methods to be called by the joystick during a match
     * @param robot
     */
    protected void processStickButtons(Robot robot){
        if(stick.getRawButton(1)){
            //Free button
        }
        else if (stick.getRawButton(2)){
            //Free button
        }
        else if (stick.getRawButton(3)){
            //Raises the fuel flap
            robot.getFuelSystem().raiseFlap();
        }
        else if (stick.getRawButton(4)){
            //Lowers fuel flap to offload state
            robot.getFuelSystem().offloadFuelFlap();
        }
        else if (stick.getRawButton(5)){
            //Uses the followGyro method to follow a driver selected angle taken from button 12
            robot.getDrivetrain().followGyro((stick.getY()),takenAngle);
        }
        else if (stick.getRawButton(6)){
            //Free button
        }
        else if (stick.getRawButton(7)){
            //Lowers the flap all the way
            robot.getFuelSystem().fullyLowerFlap();

            //Spins the climber for rope collection
            robot.getClimber().collectRope();
        }
        else if (stick.getRawButton(8)){
            //Starts to climb the rope once rope is caught
            robot.getClimber().climbRope();
        }
        else if (stick.getRawButton(9)){
            //Slows down climbing for touching the davit
            robot.getClimber().touchDavit();
        }
        else if (stick.getRawButton(10)){
            //Free button
        }
        else if (stick.getRawButton(11)){
            //This button is for switching the direction

            if (robot.getDrivetrain().isFrontIsGear()){
                robot.getDrivetrain().setFuelFront();
            }
            else{
                robot.getDrivetrain().setGearFront();
            }
        }
        else if (stick.getRawButton(12)){
            //Takes the current gyro angle
            takenAngle = robot.getDrivetrain().getGyroAngle();
            SmartDashboard.putNumber("takenAngle",takenAngle);
        }
        else{
            robot.getClimber().stop();
            robot.getFuelSystem().stopFuelFlap();
        }
    }
}
