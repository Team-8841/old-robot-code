package org.usfirst.frc.team1065.robot.subsystems.utility;

import edu.wpi.first.wpilibj.SpeedController;

/**
 * Represents a fake speed controller
 */
public class DummyOutput implements SpeedController {

    private double output;

    public double get() {
        return output;
    }

    public void set(double speed, byte syncGroup) {
        set(speed);
    }

    public void set(double speed) {
        output = speed;
    }

    public void disable() {
    }

    public void pidWrite(double output) {
        this.output = output;
    }

	public void setInverted(boolean isInverted) {
	}
	
	public boolean getInverted() {
		return false;
	}

	public void stopMotor() {
		output = 0;
		
	}
}
