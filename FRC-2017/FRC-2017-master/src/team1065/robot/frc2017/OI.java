package team1065.robot.frc2017;

import edu.wpi.first.wpilibj.Joystick;

public class OI {
	public enum ALLIANCE{RED_ALLIANCE,BLUE_ALLIANCE};
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
    
    public boolean getLeftJoystickTop(){
    	return leftJoystick.getTop();
    }
    
    public boolean getLeftJoystickTrigger(){
    	return leftJoystick.getTrigger();
    }
    
    public boolean getCamerafeedSwitch(){
    	return leftJoystick.getRawButton(3) || rightJoystick.getRawButton(3);
    }
    
    public boolean getIntakeInSwitch(){
    	return enhancedDS.getRawButton(RobotMap.INTAKE_IN_PORT);
    }
    
    public boolean getIntakeOutSwitch(){
    	return enhancedDS.getRawButton(RobotMap.INTAKE_OUT_PORT);
    }
    
    public boolean getCompressorOverride(){
    	return enhancedDS.getRawButton(RobotMap.COMPRESSOR_CONTROL_OVERRIDE);
    }
    
    public boolean getShooterOverride(){
    	return enhancedDS.getRawButton(RobotMap.SHOOTER_CONTROLLER_OVERRIDE);
    }
    
    public boolean getGearIntakeCloseSwitch(){
    	return enhancedDS.getRawButton(RobotMap.GEAR_INTAKE_CLOSE_SWITCH_PORT);
    }
    
    public boolean getClimberUpSwitch(){
    	return enhancedDS.getRawButton(RobotMap.CLIMB_UP_PORT);
    }
    
    public boolean getClimberDownSwitch(){
    	return enhancedDS.getRawButton(RobotMap.CLIMB_DOWN_PORT);
    }
    
    public double getShooterDesiredSpeed(){
    	double speed;
		double knobValue = enhancedDS.getRawAxis(RobotMap.SHOOTER_KNOB_PORT);
		double threshold = 0.011;
		
		boolean useVoltageValues = getShooterOverride();
		
		//If Station Knob is at 0
		if(knobValue < RobotMap.SHOOTER_KNOB_POS_0 + threshold){
			speed = useVoltageValues ? RobotMap.SHOOTER_VOLT_0 : RobotMap.SHOOTER_RPM_0;
        }
        //If Station Knob is at 1
        else if(knobValue >= RobotMap.SHOOTER_KNOB_POS_1 - threshold && knobValue < RobotMap.SHOOTER_KNOB_POS_1 + threshold){
        	speed = useVoltageValues ? RobotMap.SHOOTER_VOLT_1 : RobotMap.SHOOTER_RPM_1;
        }
        //If Station Knob is at 2
        else if(knobValue >= RobotMap.SHOOTER_KNOB_POS_2 - threshold && knobValue < RobotMap.SHOOTER_KNOB_POS_2 + threshold){
        	speed = useVoltageValues ? RobotMap.SHOOTER_VOLT_2 : RobotMap.SHOOTER_RPM_2;
        }
        //If Station Knob is at 3
        else if(knobValue >= RobotMap.SHOOTER_KNOB_POS_3 - threshold && knobValue < RobotMap.SHOOTER_KNOB_POS_3 + threshold){
        	speed = useVoltageValues ? RobotMap.SHOOTER_VOLT_3 : RobotMap.SHOOTER_RPM_3;
        }
        //If Station Knob is at 4
        else if(knobValue >= RobotMap.SHOOTER_KNOB_POS_4 - threshold && knobValue < RobotMap.SHOOTER_KNOB_POS_4 + threshold){
        	speed = useVoltageValues ? RobotMap.SHOOTER_VOLT_4 : RobotMap.SHOOTER_RPM_4;
        }
        //If Station Knob is at 5
        else if(knobValue >= RobotMap.SHOOTER_KNOB_POS_5 - threshold){
        	speed = useVoltageValues ? RobotMap.SHOOTER_VOLT_5 : RobotMap.SHOOTER_RPM_5;
        }
        else
        {
        	speed = useVoltageValues ? RobotMap.SHOOTER_VOLT_0 : RobotMap.SHOOTER_RPM_0;
        }
		
		return speed;
	}
    
    public int getAutoKnobPosition(){
    	int position;
		double knobValue = enhancedDS.getRawAxis(RobotMap.AUTO_KNOB_PORT);
		double threshold = 0.010;
		
		//Introduce the use of a switch to double the number of auto modes we are able to select
		
		//If Station Knob is at 1
		if(knobValue < RobotMap.AUTO_KNOB_POS_0 + threshold){
            position = 0;
        }
        //If Station Knob is at 2
        else if(knobValue >= RobotMap.AUTO_KNOB_POS_1 - threshold && knobValue < RobotMap.AUTO_KNOB_POS_1 + threshold){
            position = 1;
        }
        //If Station Knob is at 3
        else if(knobValue >= RobotMap.AUTO_KNOB_POS_2 - threshold && knobValue < RobotMap.AUTO_KNOB_POS_2 + threshold){
            position = 2;
        }
        //If Station Knob is at 4
        else if(knobValue >= RobotMap.AUTO_KNOB_POS_3 - threshold && knobValue < RobotMap.AUTO_KNOB_POS_3 + threshold){
            position = 3;
        }
        else
        {
        	position = 0;
        }
		
		return position;
	}
    
    public double getCameraStickX(){
    	return enhancedDS.getRawAxis(RobotMap.CAMERA_JOYSTICK_X_PORT);
    }
    
    public ALLIANCE getAlliance(){
    	ALLIANCE alliance;
    	
    	if(enhancedDS.getRawButton(RobotMap.SHOOTER_LONG_DISTANCE_SWITCH_PORT)){
    		alliance = ALLIANCE.RED_ALLIANCE;
    	}
    	else{
    		alliance = ALLIANCE.BLUE_ALLIANCE;
    	}
    	
		return alliance;
	}
    
    public boolean isRedAlliance(){
		return getAlliance() == ALLIANCE.RED_ALLIANCE;
	}
    public boolean isBlueAlliance(){
		return getAlliance() == ALLIANCE.BLUE_ALLIANCE;
	}
}

