/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  //Elevator positions
  public static final double ELEVATOR_POS_0 = 0.0,//Rocket Low and cargo side
                             ELEVATOR_POS_1 = 11300.0,//Cargo front
                             ELEVATOR_POS_2 = 27500.0,//Rocket Mid
                             ELEVATOR_POS_3 = 27500.0,//Rocket High
                             ELEVATOR_POS_4 = 27500.0,//Rocket High
                             ELEVATOR_POS_5 = 27500.0;//Rocket High

  //Shooter Speeds Percentage
  public static final double SHOOTER_SPEED_PERCENTAGE_0 = 0,
                             SHOOTER_SPEED_PERCENTAGE_1 = 0.5,
                             SHOOTER_SPEED_PERCENTAGE_2 = 0.75,
                             SHOOTER_SPEED_PERCENTAGE_3 = 1.0;

  //Shooter Speeds
  public static final double SHOOTER_SPEED_0 = 0,
                             SHOOTER_SPEED_1 = 5500,
                             SHOOTER_SPEED_2 = 6000,
                             SHOOTER_SPEED_3 = 6500;

  //PID Constants
  public static final double MECANUM_STRAIGHT_DRIVE_P = 0.023,
                             ELEVATOR_F = 0,
                             ELEVATOR_P = 0.2,
                             ELEVATOR_I = 0.0005,
                             ELEVATOR_D = 0,
                             SHOOTER_F = 0.0575,//tune F and then P/D to get good control.
                             SHOOTER_P = 0.95,//0.11
                             SHOOTER_I = 0.00,
                             SHOOTER_D = 0.0;

  //Int Constants
  public static final int ELEVATOR_ERROR = 30,
                          SHOOTER_ERROR = 300;//needs to be tuned

  //Double Constants
  public static final double JOYSTICK_DEADBAND = 0.05,
                              DRIVE_STRAIGHT_BAND_PERCENTAGE = 0.25,
                              DRIVE_WHEEL_DIAMETER = 6,
                              DRIVE_ENCODERS_COUNTS_PER_REV = 20,
                              ELEVATOR_JOYSTICK_MIDDLE = 0.063,
							                ELEVATOR_JOYSTICK_DEADBAND = 0.01;

  //PWM Ports
  public static final int LEFT_FRONT_DRIVE_MOTOR_PORT = 0,
                          RIGHT_FRONT_DRIVE_MOTOR_PORT = 1,
                          LEFT_BACK_DRIVE_MOTOR_PORT = 2,
                          RIGHT_BACK_DRIVE_MOTOR_PORT = 3,
                          //CLIMBER_MOTOR_PORT = 4,
                          CAMERA_SERVO_PORT = 5;

 //CAN Ports
  public static final int INTAKE_VICTOR_PORT = 1,
                          INDEXER_MID_VICTOR_PORT = 2,
                          CLIMBER_VICTOR_PORT = 3,
                          ELEVATOR_MASTER_TALON_PORT = 1,
                          ELEVATOR_SLAVE_TALON_PORT = 2,
                          SHOOTER_TALON_PORT = 3;

  //PCM 1 Ports
  public static final int ARM_SOLENOID_PORT = 4,
                          GRABBER_SOLENOID_PORT = 5,
                          FRONT_CLIMBER_SOLENOID_PORT = 6;
                          


  //PCM 2 Ports
  public static final int CARGO_LED_PORT = 0,
                          LED2_PORT = 1,
                          LED3_PORT = 2,
                          LED4_PORT = 3,
                          DROPDOWN_SOLENOID_PORT = 4,
                          BACK_CLIMBER_SOLENOID_PORT = 5;
                         
                          

  //Digital Ports
  public static final int DRIVE_ENCODER_PORT_A = 0, 
                          DRIVE_ENCODER_PORT_B = 1,
                          CARGO_SWITCH_PORT = 2,
                          ELEVATOR_BOTTOM_LIMIT_PORT = 3,
                          ELEVATOR_TOP_LIMIT_PORT = 4,
                          //SIDE_LINE_SENSOR_PORT = 5,
                          //BACK_LINE_SENSOR_PORT = 6,
                          AUTO_SIDE_SWITCH_PORT = 7, // On = Right Off = Left
                          AUTO_MODE_SWITCH_PORT = 8, // On = rocket Off = Cargo
                          FRONT_LINE_SENSOR_PORT = 9; 

  //OI
  public static final int LEFT_JOYSTICK_PORT = 0,					//usb
                          RIGHT_JOYSTICK_PORT = 1,				//usb
                          ENHANCED_DS_PORT = 2,					//usb
                          CLIMBER_EXTEND_PORT = 1,	    			//Digital
                          COMPRESSOR_CONTROL_OVERRIDE = 2,		//Digital
                          INTAKE_IN_PORT = 4,					//Digital
                          INTAKE_OUT_PORT = 5,					//Digital
                          ARM_UP_PORT = 6,						//Digital 
                          ARM_DOWN_PORT = 8,					//Digital 
                          ELEVATOR_CONTROL_OVERRIDE = 9,				//Digital 
                          GRABBER_SOLENOID_CONTROL = 12,		//Digital
                          ELEVATOR_KNOB_PORT = 0,					//Analog
                          AUTO_KNOB_PORT = 1,                     //Analog
                          ELEVATOR_JOYSTICK_Y_PORT = 2,			    //Analog			
                          CAMERA_JOYSTICK_X_PORT = 4,			    //Analog			
                          CAMERA_JOYSTICK_Y_PORT = 5;			    //Analog			

  public static final double  ELEVATOR_KNOB_POS_0 = 0.000,
                              ELEVATOR_KNOB_POS_1 = 0.024,
                              ELEVATOR_KNOB_POS_2 = 0.047,
                              ELEVATOR_KNOB_POS_3 = 0.071,
                              ELEVATOR_KNOB_POS_4 = 0.094,
                              ELEVATOR_KNOB_POS_5 = 0.118,
                              AUTO_KNOB_POS_0 = 0.000,
                              AUTO_KNOB_POS_1 = 0.024,
                              AUTO_KNOB_POS_2 = 0.055,
                              AUTO_KNOB_POS_3 = 0.087;
}
