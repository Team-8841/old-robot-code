package org.usfirst.frc.team1065.robot.commands;

import org.usfirst.frc.team1065.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualShooterControl extends Command {

    public ManualShooterControl() {
        requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//TODO: consider only enabling disabling on an edge transition
    	if(Robot.oi.getShooterOverride()){
    		Robot.shooter.disablePIDController();
    	}
    	else{
    		Robot.shooter.enablePIDController();
    	}
    	
    	if(Robot.oi.getLongDistanceSwitch()){
    		Robot.shooter.setShooterLongDistance(true);
    	}
    	else{
    		Robot.shooter.setShooterLongDistance(false);
    	}
    	
    	Robot.shooter.set(Robot.oi.getShooterDesiredSpeed());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
