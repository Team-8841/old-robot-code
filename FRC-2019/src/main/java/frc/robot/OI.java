/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  Joystick leftJoystick, rightJoystick, enhancedDS;
	
	public OI() {
		leftJoystick = new Joystick(RobotMap.LEFT_JOYSTICK_PORT);
		rightJoystick = new Joystick(RobotMap.RIGHT_JOYSTICK_PORT);
	    enhancedDS = new Joystick(RobotMap.ENHANCED_DS_PORT);
	}
	
	public double getLeftJoystickY () {
		if(Math.abs(leftJoystick.getY()) < RobotMap.JOYSTICK_DEADBAND){
			return 0;
		}
	    else {
	    	return -leftJoystick.getY();
	    }
	}
	
	public double getRightJoystickY () {
		if(Math.abs(rightJoystick.getY()) < RobotMap.JOYSTICK_DEADBAND){
			return 0;
		}
	    else {
	    	return -rightJoystick.getY();
	    }
	}
	
	public double getRightJoystickX () {
		if(Math.abs(rightJoystick.getX()) < RobotMap.JOYSTICK_DEADBAND){
			return 0;
		}
	    else {
	    	return rightJoystick.getX();
	    }
	}
	public boolean getRightJoystickTrigger(){
    	return rightJoystick.getTrigger();
    }
    
    public boolean getRightJoystickTop(){
    	return rightJoystick.getTop();
    }

    public boolean getRightJoystickTopMiddle(){
    	return rightJoystick.getRawButton(3);
    }

    public boolean getRightJoystickTopRight(){
        return rightJoystick.getRawButton(5);
    }

    public boolean getRightJoystickTopLeft(){
        return rightJoystick.getRawButton(4);
    }
    
    public boolean getLeftJoystickTop(){
    	return leftJoystick.getTop();
    }
    
    public boolean getLeftJoystickTrigger(){
    	return leftJoystick.getTrigger();
    }

    public boolean getLeftJoystickTopMiddle(){
    	return leftJoystick.getRawButton(3);
    }

    public boolean getLeftJoystick11Button(){
        return leftJoystick.getRawButton(10);
    }
    
    public boolean getCompressorOverride(){
    	return enhancedDS.getRawButton(RobotMap.COMPRESSOR_CONTROL_OVERRIDE);
    }
    
    public boolean getArmUpSwitch(){
    	return enhancedDS.getRawButton(RobotMap.ARM_UP_PORT);
    }
    
    public boolean getArmDownSwitch(){
    	return enhancedDS.getRawButton(RobotMap.ARM_DOWN_PORT);
    }
    
    public double getElevatorStickY(){
    	return enhancedDS.getRawAxis(RobotMap.ELEVATOR_JOYSTICK_Y_PORT);
    }

    public double getCameraStickX(){
        return enhancedDS.getRawAxis(RobotMap.CAMERA_JOYSTICK_X_PORT);
    }

    public double getCameraStickY(){
        return enhancedDS.getRawAxis(RobotMap.CAMERA_JOYSTICK_Y_PORT);
    }

    public boolean getElevatorOverride(){
    	return enhancedDS.getRawButton(RobotMap.ELEVATOR_CONTROL_OVERRIDE);
    }

    public boolean getGrabberControl(){
        return enhancedDS.getRawButton(RobotMap.GRABBER_SOLENOID_CONTROL);
    }
    
    public boolean getIntakeIn(){
    	return enhancedDS.getRawButton(RobotMap.INTAKE_IN_PORT);
    }
    
    public boolean getIntakeOut(){
    	return enhancedDS.getRawButton(RobotMap.INTAKE_OUT_PORT);
    }

    public boolean getClimberExtend(){
    	return enhancedDS.getRawButton(RobotMap.CLIMBER_EXTEND_PORT);
    }

    public double getElevatorDesiredPosition(){
    	double position;
		double knobValue = enhancedDS.getRawAxis(RobotMap.ELEVATOR_KNOB_PORT);
		double threshold = 0.011;
		
		//If Station Knob is at 0
		if(knobValue < RobotMap.ELEVATOR_KNOB_POS_0 + threshold){
			position = RobotMap.ELEVATOR_POS_0;
        }
        //If Station Knob is at 1
        else if(knobValue >= RobotMap.ELEVATOR_KNOB_POS_1 - threshold && knobValue < RobotMap.ELEVATOR_KNOB_POS_1 + threshold){
        	position = RobotMap.ELEVATOR_POS_1;
        }
        //If Station Knob is at 2
        else if(knobValue >= RobotMap.ELEVATOR_KNOB_POS_2 - threshold && knobValue < RobotMap.ELEVATOR_KNOB_POS_2 + threshold){
        	position = RobotMap.ELEVATOR_POS_2;
        }
        //If Station Knob is at 3
        else if(knobValue >= RobotMap.ELEVATOR_KNOB_POS_3 - threshold && knobValue < RobotMap.ELEVATOR_KNOB_POS_3 + threshold){
        	position = RobotMap.ELEVATOR_POS_3;
        }
        //If Station Knob is at 4
        else if(knobValue >= RobotMap.ELEVATOR_KNOB_POS_4 - threshold && knobValue < RobotMap.ELEVATOR_KNOB_POS_4 + threshold){
        	position = RobotMap.ELEVATOR_POS_4;
        }
        //If Station Knob is at 5
        else if(knobValue >= RobotMap.ELEVATOR_KNOB_POS_5 - threshold){
        	position = RobotMap.ELEVATOR_POS_5;
        }
        else
        {
        	position = RobotMap.ELEVATOR_POS_0;
        }
		
		return position;
	}
    
    public double getShooterDesiredSpeedPercentage(){
        double speed;
        double knobValue = enhancedDS.getRawAxis(RobotMap.AUTO_KNOB_PORT);
        double threshold = 0.010;

        //If Shooter Knob is at 1
        if(knobValue < RobotMap.AUTO_KNOB_POS_0 + threshold){
            speed = RobotMap.SHOOTER_SPEED_PERCENTAGE_0;
        }
        //If Shooter Knob is at 2
        else if(knobValue >= RobotMap.AUTO_KNOB_POS_1 - threshold && knobValue < RobotMap.AUTO_KNOB_POS_1 + threshold){
            speed = RobotMap.SHOOTER_SPEED_PERCENTAGE_1;
        }
        //If Shooter Knob is at 3
        else if(knobValue >= RobotMap.AUTO_KNOB_POS_2 - threshold && knobValue < RobotMap.AUTO_KNOB_POS_2 + threshold){
            speed = RobotMap.SHOOTER_SPEED_PERCENTAGE_2;
        }
        //If Shooter Knob is at 4
        else if(knobValue >= RobotMap.AUTO_KNOB_POS_3 - threshold && knobValue < RobotMap.AUTO_KNOB_POS_3 + threshold){
            speed = RobotMap.SHOOTER_SPEED_PERCENTAGE_3;
        }
        else
        {
            speed = RobotMap.SHOOTER_SPEED_PERCENTAGE_0;
        }
      
      return speed;
    }
    
    public double getShooterDesiredSpeed(){
        double speed;
        double knobValue = enhancedDS.getRawAxis(RobotMap.AUTO_KNOB_PORT);
        double threshold = 0.010;

        //If Shooter Knob is at 1
        if(knobValue < RobotMap.AUTO_KNOB_POS_0 + threshold){
            speed = RobotMap.SHOOTER_SPEED_0;
        }
        //If Shooter Knob is at 2
        else if(knobValue >= RobotMap.AUTO_KNOB_POS_1 - threshold && knobValue < RobotMap.AUTO_KNOB_POS_1 + threshold){
            speed = RobotMap.SHOOTER_SPEED_1;
        }
        //If Shooter Knob is at 3
        else if(knobValue >= RobotMap.AUTO_KNOB_POS_2 - threshold && knobValue < RobotMap.AUTO_KNOB_POS_2 + threshold){
            speed = RobotMap.SHOOTER_SPEED_2;
        }
        //If Shooter Knob is at 4
        else if(knobValue >= RobotMap.AUTO_KNOB_POS_3 - threshold && knobValue < RobotMap.AUTO_KNOB_POS_3 + threshold){
            speed = RobotMap.SHOOTER_SPEED_3;
        }
        else
        {
            speed = RobotMap.SHOOTER_SPEED_0;
        }
      
      return speed;
	}
}
