package org.usfirst.frc.team1065.robot.commands.Autonomous.Utility;

import org.usfirst.frc.team1065.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateToAngle extends Command {
	double speed, angle;
	
    public RotateToAngle(double speed, double angle, double time) {
        requires(Robot.drive);
        
        this.speed = Math.abs(speed);//0,1
        this.angle = angle;//-180,180
        this.setTimeout(time);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.setAngle(angle);
    	Robot.drive.enableStraightController();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double currentAngle = Robot.drive.getAngle();
    	double motorSpeed = speed;
    	//slow down 10 degrees from the target
    	if(currentAngle > angle-10 && currentAngle < angle+10){
    		motorSpeed = motorSpeed * 0.9;
    	}
    	
    	//direction of rotation decided based on target angle
    	if(Robot.drive.getAngle() > angle){
    		motorSpeed = motorSpeed * -1;
    	}
    	
    	Robot.drive.tankDrive(motorSpeed, -motorSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.drive.angleOnTarget() || this.isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.disableStraightControllers();
    	Robot.drive.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
