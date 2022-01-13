package team1065.robot.frc2017.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import team1065.robot.frc2017.Robot;

/**
 *
 */
public class ShootForTime extends Command {

    public ShootForTime(double time) {
    	this.setTimeout(time);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.shooter.setAgitator(.5);
		Robot.shooter.setIndexerOpen();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return this.isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.setAgitator(0);
		Robot.shooter.setIndexerClose();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
