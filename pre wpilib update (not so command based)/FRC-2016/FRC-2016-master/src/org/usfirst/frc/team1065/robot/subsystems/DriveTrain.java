package org.usfirst.frc.team1065.robot.subsystems;

import org.usfirst.frc.team1065.robot.RobotMap;
import org.usfirst.frc.team1065.robot.commands.DriveWithJoysticks;
import org.usfirst.frc.team1065.robot.subsystems.utility.DummyOutput;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {
	private Talon leftTalon, rightTalon;
    private PIDController straightPID;
	private Encoder leftEncoder, rightEncoder;
	private AHRS navX;
	private DummyOutput dummyStraight;
	
	private static double ratePIDPTerm, ratePIDITerm, ratePIDDTerm, straightPIDPTerm, straightPIDITerm, straightPIDDTerm, PIDPeriod;
    
    public DriveTrain(){
    	leftEncoder = new Encoder(RobotMap.LEFT_DRIVE_ENCODER_PORT_A,RobotMap.LEFT_DRIVE_ENCODER_PORT_B,true,CounterBase.EncodingType.k1X);
    	rightEncoder = new Encoder(RobotMap.RIGHT_DRIVE_ENCODER_PORT_A,RobotMap.RIGHT_DRIVE_ENCODER_PORT_B,false,CounterBase.EncodingType.k1X);
    	leftEncoder.setDistancePerPulse((RobotMap.DRIVE_WHEEL_DIAMETER * Math.PI)/(RobotMap.DRIVE_ENCODERS_COUNTS_PER_REV));
    	rightEncoder.setDistancePerPulse((RobotMap.DRIVE_WHEEL_DIAMETER * Math.PI)/(RobotMap.DRIVE_ENCODERS_COUNTS_PER_REV));
    	leftEncoder.setPIDSourceType(PIDSourceType.kRate);
    	rightEncoder.setPIDSourceType(PIDSourceType.kRate);
    	
    	leftTalon = new Talon(RobotMap.LEFT_DRIVE_MOTOR_PORT);
    	rightTalon = new Talon(RobotMap.RIGHT_DRIVE_MOTOR_PORT);
    	rightTalon.setInverted(true);
    	
    	straightPIDPTerm = 0.055;
    	straightPIDITerm = 0.0;
    	straightPIDDTerm = 0.0;
    	
    	PIDPeriod = 0.02;
   	
    	try {
    		navX = new AHRS(SPI.Port.kMXP);
    		navX.reset();
    		navX.zeroYaw();
        } catch (RuntimeException ex ) {
            System.out.println("Error instantiating navX MXP:  " + ex.getMessage());
        }
    	
    	dummyStraight = new DummyOutput();
    	straightPID = new PIDController(straightPIDPTerm, straightPIDITerm, straightPIDDTerm, navX, dummyStraight, PIDPeriod);
    	straightPID.setInputRange(-180.0f,  180.0f);
    	straightPID.setOutputRange(-0.5, 0.5);
    	straightPID.setAbsoluteTolerance(2);
    	straightPID.setContinuous(true);
    	straightPID.setSetpoint(0.0);
    	
    	
    	LiveWindow.addActuator("DriveTrain","Left Motor Controller", leftTalon);
    	LiveWindow.addActuator("DriveTrain","Right Motor Controller", rightTalon);
    	LiveWindow.addSensor("DriveTrain", "Left Encoder", leftEncoder);
    	LiveWindow.addSensor("DriveTrain", "Right Encoder", rightEncoder);
    	LiveWindow.addSensor("DriveTrain", "NavX", navX);
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new DriveWithJoysticks());
    }
    
    public void tankDrive(double leftSpeed, double rightSpeed){

    		leftTalon.set(leftSpeed);
        	rightTalon.set(rightSpeed);
        	
    	//TODO: remove
    	//SmartDashboard.putNumber("Drive Angle", navX.getYaw());
    	//SmartDashboard.putNumber("left speed", leftEncoder.getRate());
    	//SmartDashboard.putNumber("right speed", rightEncoder.getRate());
    }
    
    public void DriveStraight(double speed){
    	if(straightPID.isEnabled()){
    		double leftSpeed = speed + dummyStraight.get();
    		double rightSpeed = speed - dummyStraight.get();
    		tankDrive(leftSpeed, rightSpeed);
    	}
    	else{
    		tankDrive(speed, speed);
    	}
    }
    
    public void resetEncoders() {
    	leftEncoder.reset();
    	rightEncoder.reset();
	}

	public double getRightEncoderDistance() {
		return rightEncoder.getDistance();
	}

	public double getLeftEncoderDistance() {
		return leftEncoder.getDistance();
	}
    
    public void enableStraightController(){
    	if(!straightPID.isEnabled()){
    		straightPID.enable();
    	}
    }
    
    public void disableStraightControllers(){
    	if(straightPID.isEnabled()){
    		straightPID.disable();
    	}
    }
	
	public void resetAngle(){
		navX.zeroYaw();
	}
	
	public double getAngle(){
		return navX.getYaw();//-180 - 180
	}
	
	public boolean angleOnTarget(){
		return straightPID.onTarget();
	}
	
	public void setAngle(double angle){
		straightPID.setSetpoint(angle);
	}
}

