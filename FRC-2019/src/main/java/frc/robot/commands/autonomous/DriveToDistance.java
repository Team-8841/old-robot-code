package frc.robot.commands.autonomous;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveToDistance extends Command {
	double targetSpeed, targetDistance, targetAngle;
	int atDestinationCounter;
	boolean endByStopping, useCurrentAngle;
	//set speed negative to go backwards
    public DriveToDistance(double speed, double distance, double time) {
    	requires(Robot.m_driveTrain);
    	targetSpeed = speed;
        targetDistance = Math.abs(distance);
        targetAngle = Robot.m_driveTrain.getAngle();
        useCurrentAngle = true;
        endByStopping =true;
        this.setTimeout(time);
    }
    
    /*public DriveToDistance(double speed, double distance, double time, boolean stop) {
    	requires(Robot.m_driveTrain);
    	targetSpeed = speed;
        targetDistance = Math.abs(distance);
        targetAngle = Robot.m_driveTrain.getAngle();
        endByStopping = stop;
        this.setTimeout(time);
    }*/
    
    public DriveToDistance(double speed, double distance, double angle, double time) {
    	requires(Robot.m_driveTrain);
    	targetSpeed = speed;
        targetDistance = Math.abs(distance);
        targetAngle = angle;
        endByStopping =true;
        useCurrentAngle = false;
        this.setTimeout(time);
    }
    
    /*public DriveToDistance(double speed, double distance, double angle, double time, boolean stop) {
    	requires(Robot.m_driveTrain);
    	targetSpeed = speed;
        targetDistance = Math.abs(distance);
        targetAngle = angle;
        endByStopping = stop;
        this.setTimeout(time);
    }*/
    
    protected void initialize() {
    	Robot.m_driveTrain.resetEncoder();
    	if(useCurrentAngle){
    		targetAngle = Robot.m_driveTrain.getAngle();
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double motorSpeed = targetSpeed;
    	int direction = (int) (targetSpeed/Math.abs(targetSpeed));
    	//Slow  down near the target
    	if(Math.abs(Robot.m_driveTrain.getEncoderDistance()) > targetDistance-5){ //slow down in last 5 inches
    		motorSpeed = direction * 0.15;
    	}
    	else if(Math.abs(Robot.m_driveTrain.getEncoderDistance()) > targetDistance-10){ //slow down in last 10 inches
    		motorSpeed = direction * 0.18;
    	}
    	else if(Math.abs(Robot.m_driveTrain.getEncoderDistance()) > targetDistance-20){ //slow down in first 5 inches
    		motorSpeed = direction * 0.25;
    	}
    	else if(Math.abs(Robot.m_driveTrain.getEncoderDistance()) > targetDistance-30 || //slow down in last 30 inches 
    			Math.abs(Robot.m_driveTrain.getEncoderDistance()) < 5){ //slow down in last 20 inches
    		motorSpeed = direction * 0.3;
    	}
    	
    	//minimum speed
    	if(Math.abs(motorSpeed) < .15){
    		
    		motorSpeed = direction * .15;
    	}
    	
    	if(Math.abs(Robot.m_driveTrain.getEncoderDistance()) >= targetDistance+4){
    		motorSpeed *= -1;
    		atDestinationCounter = 0;
    	}
    	else if(Math.abs(Robot.m_driveTrain.getEncoderDistance()) <= targetDistance-4){
    		atDestinationCounter = 0;
    	}
    	else{
    		if(endByStopping){
    			motorSpeed = 0;
    		}
    		atDestinationCounter++;
    	}
    	
    	Robot.m_driveTrain.straightDrive(motorSpeed, targetAngle);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(endByStopping){
    		return atDestinationCounter > 5 || this.isTimedOut();
    	}
    	else{
    		return atDestinationCounter > 0 || this.isTimedOut();
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	if(endByStopping){
    		Robot.m_driveTrain.tankDrive(0, 0);
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
