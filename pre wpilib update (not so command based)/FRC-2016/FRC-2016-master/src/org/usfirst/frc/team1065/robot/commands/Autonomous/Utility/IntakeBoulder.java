package org.usfirst.frc.team1065.robot.commands.Autonomous.Utility;

import org.usfirst.frc.team1065.robot.Robot;
import org.usfirst.frc.team1065.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeBoulder extends Command {

    public IntakeBoulder(double time) {
        requires(Robot.intake);
        this.setTimeout(time);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intake.setIntakeIn(RobotMap.INTAKE_IN_SPEED);
    	Robot.intake.setQueuingUp(RobotMap.QUEUING_UP_SPEED);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.intake.getQueuingIR() || this.isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.setIntake(0);
    	Robot.intake.setQueuing(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
