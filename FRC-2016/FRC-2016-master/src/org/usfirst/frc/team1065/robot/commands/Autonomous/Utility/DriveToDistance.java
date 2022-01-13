package org.usfirst.frc.team1065.robot.commands.Autonomous.Utility;

import org.usfirst.frc.team1065.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveToDistance extends Command {
	double speed, distance;
	//set speed negative to go backwards
    public DriveToDistance(double speed, double distance, double time) {
        requires(Robot.drive);
        
        this.speed = speed;
        this.distance = Math.abs(distance);
        this.setTimeout(time);
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.resetEncoders();
    	Robot.drive.setAngle(Robot.drive.getAngle());
    	Robot.drive.enableStraightController();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Math.abs(Robot.drive.getLeftEncoderDistance()) > distance-10){ //slow down 10 inches from the target
    		speed = speed * 0.75;
    	}
    	
    	Robot.drive.DriveStraight(speed);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return  Math.abs(Robot.drive.getLeftEncoderDistance()) >= distance || this.isTimedOut();
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
