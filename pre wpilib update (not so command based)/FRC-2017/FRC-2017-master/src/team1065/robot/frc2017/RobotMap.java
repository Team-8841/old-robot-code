package team1065.robot.frc2017;

public class RobotMap {
	
	//PID Constants
    public static final double MECANUM_STRAIGHT_DRIVE_P = 0.05,
    		                   SHOOTER_F = 0.066,//1.68 (20 count encoder)
	                		   SHOOTER_P = 0.45,//13 (20 count encoder)
            				   SHOOTER_I = 0.000002,//0.0001 (20 count encoder)
    						   SHOOTER_D = 0;
    
    //Shooter RPM values
    public static final double SHOOTER_RPM_0 = 0.0,
    						   SHOOTER_RPM_1 = 1600.0,
    						   SHOOTER_RPM_2 = 1650.0,
    						   SHOOTER_RPM_3 = 1700.0,
    						   SHOOTER_RPM_4 = 1750.0,
    						   SHOOTER_RPM_5 = 1800.0;
    
    //Shooter Voltage values
    public static final double SHOOTER_VOLT_0 = 0.0,
    						   SHOOTER_VOLT_1 = 0.5,
    						   SHOOTER_VOLT_2 = 0.6,
    						   SHOOTER_VOLT_3 = 0.7,
    						   SHOOTER_VOLT_4 = 0.8,
    						   SHOOTER_VOLT_5 = 1.0;
    
    //Double Constants
    public static final double JOYSTICK_DEADBAND = 0.05,
			                   DRIVE_STRAIGHT_BAND_PERCENTAGE = 0.25,
	    					   DRIVE_WHEEL_DIAMETER = 6,
							   DRIVE_ENCODERS_COUNTS_PER_REV = 20,
							   MOTOR_TEST_MOVING_CURRENT = 1.0,
							   MOTOR_TEST_STOP_CURRENT = 0.0;
	
	//PWM Ports
    public static final int LEFT_FRONT_DRIVE_MOTOR_PORT = 0,
    				  	    RIGHT_FRONT_DRIVE_MOTOR_PORT = 1,
		  	    			LEFT_BACK_DRIVE_MOTOR_PORT = 2,
    				  	    RIGHT_BACK_DRIVE_MOTOR_PORT = 3,
    				  	    INTAKE_MOTOR_PORT = 4,
    				  	    AGITATOR_MOTOR_PORT = 5,
    				  	    CLIMBER_MOTOR_PORT = 6,
    				  	    CAMERA_SERVO_PORT = 7;
    
    //PCM Ports
    public static final int INDEXER_SOLENOID_PORT = 0,
    						GEAR_INTAKE_SOLENOID_PORT = 1,
    						GEAR_PUSHER_SOLENOID_PORT = 2,
    						DROPDOWN_SOLENOID_PORT = 3,
    						LEFT_LED_PCM_PORT = 5,
    						RIGHT_LED_PCM_PORT = 6,
    						BACK_LED_PCM_PORT = 7;
    
    //Digital Ports
    public static final int DRIVE_ENCODER_PORT_A = 0,
	    				    DRIVE_ENCODER_PORT_B = 1,
	    				    GEAR_IR_SENSOR_PORT = 2;
    
    //CANTalon Ports
    public static final int MASTER_TALON_PORT = 1,
    						SLAVE_TALON_PORT = 2;
    
    //OI
    public static final int LEFT_JOYSTICK_PORT = 0,					//usb
					  		RIGHT_JOYSTICK_PORT = 1,				//usb
				  			ENHANCED_DS_PORT = 2,					//usb
					  		GEAR_INTAKE_CLOSE_SWITCH_PORT = 1,	    //digital
					  		COMPRESSOR_CONTROL_OVERRIDE = 2,		//Digital
						    INTAKE_IN_PORT = 4,						//Digital
						    INTAKE_OUT_PORT = 5,					//Digital
						    CLIMB_UP_PORT = 6,						//Digital
						    CLIMB_DOWN_PORT = 8,					//Digital
						    SHOOTER_CONTROLLER_OVERRIDE = 9,		//Digital
						    SHOOTER_LONG_DISTANCE_SWITCH_PORT = 12,	//digital
				    		SHOOTER_KNOB_PORT = 0,					//Analog
				    		AUTO_KNOB_PORT = 1,                     //Analog
				    		CAMERA_JOYSTICK_X_PORT = 4;			    //Analog
    
    public static final double SHOOTER_KNOB_POS_0 = 0.000,
			   SHOOTER_KNOB_POS_1 = 0.024,
			   SHOOTER_KNOB_POS_2 = 0.047,
			   SHOOTER_KNOB_POS_3 = 0.071,
			   SHOOTER_KNOB_POS_4 = 0.094,
			   SHOOTER_KNOB_POS_5 = 0.118,
			   AUTO_KNOB_POS_0 = 0.000,
			   AUTO_KNOB_POS_1 = 0.024,
			   AUTO_KNOB_POS_2 = 0.055,
			   AUTO_KNOB_POS_3 = 0.087;
}
