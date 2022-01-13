package org.usfirst.frc.team1065.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//Integer Constants
    public static final int MOOSE_MOTOR_UPDATE_TIME = 10;
    
    //Double Constants
    public static final double DRIVE_TOP_SPEED = 140.0,// inches per second TODO: change
							   DRIVE_WHEEL_DIAMETER = 7.65,
							   DRIVE_ENCODERS_COUNTS_PER_REV = 360,
	    					   INTAKE_IN_SPEED = 0.9,
	    					   INTAKE_OUT_SPEED = 1.0,
	    					   JOYSTICK_DEADBAND = 0.05,
	    					   QUEUING_UP_SPEED = 0.7,
	    					   QUEUING_SHOOTING_SPEED =1.0,
	    					   QUEUING_DOWN_SPEED = 1.0,
	    					   DRIVE_STRAIGHT_BAND_PERCENTAGE = 0.2;
    
    //Shooter RPM values
    public static final double SHOOTER_RPM_0 = 0.0,
    						   SHOOTER_RPM_1 = 5600.0,
    						   SHOOTER_RPM_2 = 5700.0,
    						   SHOOTER_RPM_3 = 5800.0,
    						   SHOOTER_RPM_4 = 5850.0,
    						   SHOOTER_RPM_5 = 5900.0;
    
    //Shooter Voltage values
    public static final double SHOOTER_VOLT_0 = 0.0,
    						   SHOOTER_VOLT_1 = 0.6,
    						   SHOOTER_VOLT_2 = 0.7,
    						   SHOOTER_VOLT_3 = 0.8,
    						   SHOOTER_VOLT_4 = 0.9,
    						   SHOOTER_VOLT_5 = 1.0;
    
    //PWM Ports
    public static final int LEFT_DRIVE_MOTOR_PORT = 0,
    				  	    RIGHT_DRIVE_MOTOR_PORT = 1,
				  	    	INTAKE_MOTOR_PORT = 2,
				  	    	QUEUING_MOTOR_PORT = 3,
				  	    	SHOOTER_MOTOR_PORT = 4;
    //PCM Ports
    public static final int OBSTACLE_MANIPULATOR_PORT = 0,
	  	    				SHOOTER_HOOD_PORT = 1,
	  	    				LEFT_LED_PORT = 2,
	  		  	    		FRONT_LED_PORT = 3,
	  		  	    		RIGHT_LED_PORT = 4,
	  		  	    		BACK_LED_PORT = 5,
	  		  	    		BACK_LED_2_PORT = 6,
	  		  	    		SHOOTER_TRIM_PORT = 7;
    //Digital Ports
    public static final int LEFT_DRIVE_ENCODER_PORT_A = 0,
	    				    LEFT_DRIVE_ENCODER_PORT_B = 1,
						    RIGHT_DRIVE_ENCODER_PORT_A = 2,
	    				    RIGHT_DRIVE_ENCODER_PORT_B = 3,
	    				    QUEUING_IR_PORT = 4,
	    				    SHOOTER_ENCODER_PORT = 5;
    
    //OI
    public static final int LEFT_JOYSTICK_PORT = 0,
					  		RIGHT_JOYSTICK_PORT = 1,
					  		ENHANCED_DS_PORT = 2,
					  		DRIVE_CONTROL_OVERRIDE = 2,//Digital
						    INTAKE_IN_PORT = 4,//Digital
						    INTAKE_OUT_PORT = 5,//Digital
						    SHOOTER_CONTROLLER_OVERRIDE = 9,//Digital
						    OBSTACLE_MANIPULATOR_SWITCH_PORT = 1,//digital
						    SHOOTER_LONG_DISTANCE_SWITCH_PORT = 12,//digital
				    		SHOOTER_KNOB_PORT = 0,//Analog
				    		AUTO_KNOB_PORT = 1;//Analog
    
    public static final double SHOOTER_KNOB_POS_0 = 0.000,
    						   SHOOTER_KNOB_POS_1 = 0.024,
    						   SHOOTER_KNOB_POS_2 = 0.047,
    						   SHOOTER_KNOB_POS_3 = 0.071,
    						   SHOOTER_KNOB_POS_4 = 0.094,
    						   SHOOTER_KNOB_POS_5 = 0.118,
							   AUTO_KNOB_POS_0 = 0.000,
							   AUTO_KNOB_POS_1 = 0.024,
							   AUTO_KNOB_POS_2 = 0.055,
							   AUTO_KNOB_POS_3 = 0.087;;
    
    //Camera Names
    public static String FRONT_CAM = "cam0", 
    					 BACK_CAM  = "cam1";
    
    public enum StartingPosition{FarLeft,Left,Middle,Right,FarRight};
    public enum TargetGoal{Low,High,None};
    public enum TargetPosition{Left,Center,Right};
    public enum Obstacle{LiftGate,Seesaw,Moat,Ramparts,Drawbridge,Door,RockWall,RoughTerrian,LowBar};
}
