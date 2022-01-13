package team1065.robot.frc2017.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import team1065.robot.frc2017.Robot;

/**
 *
 */
public class DriveToDistance extends Command {
	double speed, distance;
	int atDistanceCounter;
	//set speed negative to go backwards
    public DriveToDistance(double speed, double distance, double time) {
    	requires(Robot.driveTrain);
    	this.speed = speed;
        this.distance = Math.abs(distance);
        this.setTimeout(time);
    }

    
    protected void initialize() {
    	Robot.driveTrain.resetEncoder();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double motorSpeed = speed;
    	if(Math.abs(Robot.driveTrain.getEncoderDistance()) > distance-20){ //slow down 20 inches from the target
    		motorSpeed = motorSpeed * 0.5;
    	}
    	else if(Math.abs(Robot.driveTrain.getEncoderDistance()) > distance-10){ //slow down 20 inches from the target
    		motorSpeed = motorSpeed * 0.4;
    	}
    	else if(Math.abs(Robot.driveTrain.getEncoderDistance()) > distance-5){ //slow down 20 inches from the target
    		motorSpeed = motorSpeed * 0.25;
    	}
    	
    	if(Math.abs(motorSpeed) < .21){
    		
    		motorSpeed = motorSpeed>0? .21 : -.21;
    	}
    	
    	if(Math.abs(Robot.driveTrain.getEncoderDistance()) >= distance+1.25){
    		motorSpeed *= -1;
    		atDistanceCounter = 0;
    	}
    	else if(Math.abs(Robot.driveTrain.getEncoderDistance()) <= distance-1.25){
    		atDistanceCounter = 0;
    	}
    	else{
    		motorSpeed = 0;
    		atDistanceCounter++;
    	}
    	
    	Robot.driveTrain.straightDrive(motorSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return atDistanceCounter > 10 || this.isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
