package org.usfirst.frc.team6579.robot.subsystem;


import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6579.robot.subsystem.SubSystem;


/**
 * This is a class for the fuel system.
 * This should have two buttons, up and down for the fuel flap.
 * This uses PWM port 5
 * ToDo limit switches???
 * @author Jiah
 *
 */
public class FuelSystem implements SubSystem {
    private VictorSP fuelMotor;
    private DigitalInput upLimitSwitch;
    private DigitalInput downLimitSwitch;
    private DigitalInput offloadLimitSwitch;

    private static final double MOTOR_POWER = 0.5;
    private static final double STOP_MOTOR = 0;

    private boolean upperLimitReached = true;
    private boolean lowerLimitReached = true;
    private boolean offloadLimitReached = true;

    /**
     * This is the method for moving the fuel flap
     * motor is in port 5
     * Top limit switch is port 9
     * Offload limit switch is in port 8
     * Bottom limit switch is port 7
     */
    public FuelSystem() {

        //review post-it note
        fuelMotor = new VictorSP(5);
        upLimitSwitch = new DigitalInput(9);
        downLimitSwitch = new DigitalInput(7);
        offloadLimitSwitch = new DigitalInput(8);

        getSwitchStatus();
    }

    @Override
    public void publishStats() {
        // ensure that we have the most current value of the switches that we want to publish
        getSwitchStatus();

        // and display the current values to SmartDashboard
        SmartDashboard.putBoolean("upperLimitReached", upperLimitReached);
        SmartDashboard.putBoolean("lowerLimitReached", lowerLimitReached);
        SmartDashboard.putBoolean("offloadLimitReached", offloadLimitReached);

        SmartDashboard.putNumber("fuelMotor", fuelMotor.get());
    }

    /**
     * Read the value of all the Fuel switches to the boolean variables.  Display this to SmartDashboard.
     */
    private void getSwitchStatus() {
        upperLimitReached = !upLimitSwitch.get();
        lowerLimitReached = !downLimitSwitch.get();
        offloadLimitReached = !offloadLimitSwitch.get();
    }

    /**
     * This moves the flap up
     * Utilises the snowblower motor ( port 5 ) and the top limit switch (port 9)
     */
    public void raiseFlap() {
        //this moves the flap up
        //ToDo: Check motor rotation is correct and limit switches are correct
        getSwitchStatus();


        if (upperLimitReached) {
            stopFuelFlap();
        }
        else {
            //fuelMotor.set(-MOTOR_POWER); changed for the rotation of fuel snowblower. 12/02
            fuelMotor.set(MOTOR_POWER);
        }

    }

    /**
     * This lowers the flap all the way down for climbing
     * Utilises snowblower motor for flap (port 5) and the lower limit switch (port 7)
     *
     */
    public void fullyLowerFlap() {
        //this moves the flap down
        //ToDo: Check motor rotation is correct and limit switches are correct
        getSwitchStatus();

        if (lowerLimitReached) {
            stopFuelFlap();
        }
        else {
            //fuelMotor.set(MOTOR_POWER); changed for the rotation of fuel snowblower. 12/02
            fuelMotor.set(-MOTOR_POWER);

        }
    }

    //ToDo: Create the third method which goes down to offloading height

    /**
     * This lowers the fuel flap down to the offload position
     * Utilises the snowblower (port 5) and the offload limit switch (port 8)
     */
    public void offloadFuelFlap() {
        //this moves the fuel flap to the offload position
        getSwitchStatus();

        if(offloadLimitReached) {
            stopFuelFlap();

        }
        else {
            fuelMotor.set(-MOTOR_POWER);

        }


    }

    public void stopFuelFlap() {
        fuelMotor.set(STOP_MOTOR);
    }


}