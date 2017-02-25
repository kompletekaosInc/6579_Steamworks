package org.usfirst.frc.team6579.robot.drivecontrol;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6579.robot.subsystem.Climber;
import org.usfirst.frc.team6579.robot.subsystem.Drivetrain;
import org.usfirst.frc.team6579.robot.subsystem.FuelSystem;
import org.usfirst.frc.team6579.robot.Robot;

/**
 * this is the drive control that gives commands to the robot
 * This class should never be changed to include button or joystick logic apart from barebones things
 */
public abstract class JoystickDriveControl implements DriveControl{


    protected Joystick stick;

    //private double takenAngle; //used for the followGyro method

    /**
     * This initialises the JoystickDriveControl object
     */
    public JoystickDriveControl()
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
        // process the throttle
        processThrottle(robot);

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

            SmartDashboard.putBoolean("gyro turning",false);
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
    /**
     * This method is basically a scheduler of the methods to be called by the joystick during a match
     * @param robot
     */
    private void processStickButtons(Robot robot){



        if(stick.getRawButton(1)){
            //Free button
            processButton1(robot);
        }
        else if (stick.getRawButton(2)){
            //Free button
            processButton2(robot);
        }
        else if (stick.getRawButton(3)){
            //Raises the fuel flap
            processButton3(robot);
        }
        else if (stick.getRawButton(4)){
            //Lowers fuel flap to offload state
            processButton4(robot);
        }
        else if (stick.getRawButton(5)){
            //Uses the followGyro method to follow a driver selected angle taken from button 12
            processButton5(robot);
        }
        else if (stick.getRawButton(6)){
            //Free button
            processButton6(robot);
        }
        else if (stick.getRawButton(7)){

            processButton7(robot);
            //Lowers the flap all the way
            //robot.getFuelSystem().fullyLowerFlap();

            //Spins the climber for rope collection
            //robot.getClimber().collectRope();
        }
        else if (stick.getRawButton(8)){

            processButton8(robot);
            //Starts to climb the rope once rope is caught
            //robot.getClimber().climbRope();
        }
        else if (stick.getRawButton(9)){

            processButton9(robot);
            //Slows down climbing for touching the davit
            //robot.getClimber().touchDavit();
        }
        else if (stick.getRawButton(10)){

            processButton10(robot);
            //turn 180 degrees
            //robot.getDrivetrain().gyroTurnLeft(180);
        }
        else if (stick.getRawButton(11)){
            processButton11(robot);
            //This button is for switching the direction

//            if (robot.getDrivetrain().isFrontIsGear()){
//                robot.getDrivetrain().setFuelFront();
//            }
//            else{
//                robot.getDrivetrain().setGearFront();
//            }
        }
        else if (stick.getRawButton(12)){
            processButton12(robot);
            //Takes the current gyro angle
//            takenAngle = robot.getDrivetrain().getGyroAngle();
//            SmartDashboard.putNumber("takenAngle",takenAngle);
        }
        else{
              processNoButtons(robot);
//            robot.getClimber().stop();
//            robot.getFuelSystem().stopFuelFlap();
        }
    }

    /**
     * Process any specific events for button 1 on the joystick.
     * @param robot
     */
    protected void processButton1( Robot robot )
    {
        //This is the opportunity for a subclass to create button one
    }
    /**
     * Process any specific events for button 2 on the joystick.
     * @param robot
     */
    protected void processButton2( Robot robot )
    {
        //This is the opportunity for a subclass to create button two
    }
    /**
     * Process any specific events for button 3 on the joystick.
     * @param robot
     */
    protected void processButton3( Robot robot )
    {
        //This is the opportunity for a subclass to create button three
    }
    /**
     * Process any specific events for button 4 on the joystick.
     * @param robot
     */
    protected void processButton4( Robot robot )
    {
        //This is the opportunity for a subclass to create button four
    }
    /**
     * Process any specific events for button 5 on the joystick.
     * @param robot
     */
    protected void processButton5( Robot robot )
    {
        //This is the opportunity for a subclass to create button five
    }
    /**
     * Process any specific events for button 6 on the joystick.
     * @param robot
     */
    protected void processButton6( Robot robot )
    {
        //This is the opportunity for a subclass to create button six
    }
    /**
     * Process any specific events for button 7 on the joystick.
     * @param robot
     */
    protected void processButton7( Robot robot )
    {
        //This is the opportunity for a subclass to create button seven
    }
    /**
     * Process any specific events for button 8 on the joystick.
     * @param robot
     */
    protected void processButton8( Robot robot )
    {
        //This is the opportunity for a subclass to create button eight
    }
    /**
     * Process any specific events for button 9 on the joystick.
     * @param robot
     */
    protected void processButton9( Robot robot )
    {
        //This is the opportunity for a subclass to create button nine
    }
    /**
     * Process any specific events for button 10 on the joystick.
     * @param robot
     */
    protected void processButton10( Robot robot )
    {
        //This is the opportunity for a subclass to create button ten
    }
    /**
     * Process any specific events for button 11 on the joystick.
     * @param robot
     */
    protected void processButton11( Robot robot )
    {
        //This is the opportunity for a subclass to create button eleven
    }
    /**
     * Process any specific events for button 12 on the joystick.
     * @param robot
     */
    protected void processButton12( Robot robot )
    {
        //This is the opportunity for a subclass to create button twelve
    }
    /**
     * Process any specific events for no buttons on the joystick.
     * @param robot
     */
    protected void processNoButtons( Robot robot )
    {
        //This is the opportunity for a subclass to create no button
    }
    /**
     * Process any specific events for throttle on the joystick.
     * @param robot
     */
    protected void processThrottle( Robot robot )
    {
        //This is the opportunity for a subclass to create throttle
    }









}
