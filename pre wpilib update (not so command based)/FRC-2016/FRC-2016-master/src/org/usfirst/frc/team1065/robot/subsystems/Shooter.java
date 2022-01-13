package org.usfirst.frc.team1065.robot.subsystems;

import org.usfirst.frc.team1065.robot.RobotMap;
import org.usfirst.frc.team1065.robot.commands.ManualShooterControl;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Shooter extends Subsystem {
    private VictorSP shooterMotor;
    private Counter shooterCounter;
    //private PIDController shooterController;
    private Solenoid shooterHood, shooterTrim;
    
    private double ratePIDPTerm, ratePIDITerm, ratePIDDTerm, ratePIDPeriod;
    private double prevCount, prevTime;
    private boolean shooterHoodForcedClose;
    
    //status
    private double currentSpeed,  controllerSetpoint;
    private boolean controllerEnabled;
    
    public Shooter(){  	
    	currentSpeed = 0;
    	controllerSetpoint = 0;
    	controllerEnabled = false;
    	
    	shooterHood = new Solenoid(RobotMap.SHOOTER_HOOD_PORT);
    	shooterHoodForcedClose = false;
    	
    	shooterTrim =new Solenoid(RobotMap.SHOOTER_TRIM_PORT);
    	
    	
    	shooterMotor = new VictorSP(RobotMap.SHOOTER_MOTOR_PORT);
    	shooterMotor.setInverted(true);
    	//Banner
    	/*
    	shooterCounter = new Counter(RobotMap.SHOOTER_COUNTER_PORT);
    	shooterCounter.setDistancePerPulse((1.0/4.0)*60.0);//4 counts per revolution. 60 to transform seconds to minutes
    	shooterCounter.setPIDSourceType(PIDSourceType.kRate);
    	shooterCounter.setSamplesToAverage(4);////5ms (1), 10ms (2), 20ms (5)
    	shooterCounter.setSemiPeriodMode(true);
    	shooterCounter.reset();
    	
    	ratePIDPTerm = 1.0;//Making P very high so it behaves as a bang bang Controller
    	ratePIDITerm = 0.0;
    	ratePIDDTerm = 0.0;
    	ratePIDPeriod = 0.01;//10 ms
    	*/
    	
    	//Quad Encoder
    	shooterCounter = new Counter(RobotMap.SHOOTER_ENCODER_PORT);
    	//shooterCounter.setSemiPeriodMode(false);
    	shooterCounter.setDistancePerPulse((1.0/360.0) * 60.0);
    	shooterCounter.setPIDSourceType(PIDSourceType.kRate);
    	shooterCounter.setSamplesToAverage(127);//5ms (30-120), 10ms (60-240) 
    	
    	
    	ratePIDPTerm = 100.0;//Making P very high so it behaves as a bang bang Controller
    	ratePIDITerm = 0.0;
    	ratePIDDTerm = 0.0;
    	ratePIDPeriod = 0.005;//5 ms
    	
    	//shooterController = new PIDController(ratePIDPTerm,ratePIDITerm, ratePIDDTerm, shooterCounter, shooterMotor, ratePIDPeriod);
    	//shooterController.setOutputRange(0, 1);//don't allow reverse so that we can behave as a bang bang controller
    	//shooterController.setPercentTolerance(2.5);
    	
    	//LiveWindow.addActuator("Shooter", "Motor", shooterMotor);
    	//LiveWindow.addActuator("Shooter", "PID Controller", shooterController);
    	//LiveWindow.addSensor("Shooter", "Counter", shooterCounter);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ManualShooterControl());
    }
    
    public void updateStatus(){
    	currentSpeed = getSpeed();
    	//controllerSetpoint = shooterController.getSetpoint();
    	//controllerEnabled = shooterController.isEnabled();
    }
    
    public void setShooterLongDistance(boolean value){
    	shooterTrim.set(value);
    }
    
    public void enablePIDController(){
    	/*if(!controllerEnabled){
    		//shooterController.enable();
    	}*/
    	controllerEnabled = true;
    }
    
    public void disablePIDController(){
    	/*
    	if(controllerEnabled){
    		shooterController.disable();
    	}*/
    	controllerEnabled = false;
    }
    
    public boolean isPIDEnabled(){
    	return controllerEnabled;
    }
    
    public boolean isShooterOn(){
    	if(Math.abs(shooterMotor.get()) > 0.1 || controllerSetpoint > 10.0){
    		return true;
    	}
    	else{
    		return false;
    	}
    }
    
    public boolean onTarget(){
    	if(controllerSetpoint > 10.0){
    		double rate = currentSpeed;
			if(rate > (controllerSetpoint - 250) &&
					rate < (controllerSetpoint + 250)){
				return true;
			}
			else{
				return false;
			}
    	}
    	else{
    		return false;
    	}
    }
    
    public double getSpeed(){
    	double time = Timer.getFPGATimestamp();
    	double timeDiff = time - prevTime;
    	prevTime = time;
    	
    	double count = shooterCounter.get();
    	double countDiff = count - prevCount;
    	prevCount = count;
    	
    	return ((countDiff/timeDiff)*60.0)/360.0;
    }
    
    public void ForceHoodClose(boolean state){
    	shooterHoodForcedClose = state;
    }
    
    public void set(double speed){
    	//set the hood actuator to extend if the shooter is set to spin
    	if(speed > 0.1 && !shooterHoodForcedClose){
    		shooterHood.set(true);
    	}
    	else{
    		shooterHood.set(false);
    	}
    	controllerSetpoint = speed;
    	//check if we should be setting the motor or the controller
    	if(controllerEnabled){
    		if(currentSpeed < controllerSetpoint){
    			shooterMotor.set(1);
    		}
    		else{
    			shooterMotor.set(0);
    		}
    		//shooterController.setSetpoint(speed);
    	}
    	else{
    		shooterMotor.set(speed);
    	}
    	
    	//TODO: delete
    	SmartDashboard.putNumber("Shooter Speed", shooterCounter.getRate());
    	SmartDashboard.putNumber("Shooter Calculated Speed", currentSpeed);
    }
}

