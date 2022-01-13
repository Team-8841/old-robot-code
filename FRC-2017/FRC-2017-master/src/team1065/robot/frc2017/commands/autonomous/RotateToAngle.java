package team1065.robot.frc2017.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import team1065.robot.frc2017.Robot;

/**
 *
 */
public class RotateToAngle extends Command {
	double speed, angle;
	int onAngleCounter;

    public RotateToAngle(double speed, double angle, double time) {
        requires(Robot.driveTrain);
        this.speed = Math.abs(speed);//0,1
        this.angle = angle;//-180,180
        this.setTimeout(time);
        onAngleCounter = 0;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double currentAngle = Robot.driveTrain.getAngle();
    	double motorSpeed = speed;
    	//slow down if close to target
    	if(currentAngle > angle-5 && currentAngle < angle+5){
    		motorSpeed = motorSpeed * 0.3;
    	}
    	else if(currentAngle > angle-10 && currentAngle < angle+10){
    		motorSpeed = motorSpeed * 0.4;
    	}
    	else if(currentAngle > angle-15 && currentAngle < angle+15){
    		motorSpeed = motorSpeed * 0.5;
    	}
    	else if(currentAngle > angle-20 && currentAngle < angle+20){
    		motorSpeed = motorSpeed * 0.6;
    	}
    	else if(currentAngle > angle-30 && currentAngle < angle+30){
    		motorSpeed = motorSpeed * 0.75;
    	}
    	
    	if(motorSpeed < .25){
    		motorSpeed = .25;
    	}
    	
    	//direction of rotation decided based on target angle
    	if(Robot.driveTrain.getAngle() > angle){
    		motorSpeed = motorSpeed * -1;
    	}
    	
    	if(Math.abs(Robot.driveTrain.getAngle() - angle) < 1.1){
    		motorSpeed = 0;
    		onAngleCounter++;
    	}
    	else{
    		onAngleCounter = 0;
    	}
    	
    	Robot.driveTrain.tankDrive(motorSpeed, -motorSpeed);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return onAngleCounter > 5  || this.isTimedOut();
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
