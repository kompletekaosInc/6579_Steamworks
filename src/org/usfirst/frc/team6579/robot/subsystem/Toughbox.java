package org.usfirst.frc.team6579.robot.subsystem;

import edu.wpi.first.wpilibj.VictorSP;

/**
 * Thisu method ensures that we can never drive toughbox motors against each other
 * Created by Jiah on 14/2/17.
 */
public class Toughbox {
    private VictorSP motorA;
    private VictorSP motorB;

    /**
     * This establishes the toughbox, and requires the ports of both motors in the toughbox
     * @param pwmA
     * @param pwmB
     */
    public Toughbox(int pwmA, int pwmB) {
        motorA = new VictorSP(pwmA);
        motorB = new VictorSP(pwmB);
    }

    /**
     * This sets the toughbox motors to the same power
     * @param power
     */
    public void set(double power){
        motorA.set(power);
        motorB.set(power);
    }

    /**
     * This returns the current power of the motor=s in the toughbox
     * motorA is always going to equal motorB, so only calling one is fine
     * @return
     */
    public double get(){

        return motorA.get();
    }
}
