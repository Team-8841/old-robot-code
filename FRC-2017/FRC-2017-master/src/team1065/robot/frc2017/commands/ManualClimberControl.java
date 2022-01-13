package team1065.robot.frc2017.commands;

import edu.wpi.first.wpilibj.command.Command;
import team1065.robot.frc2017.Robot;

/**
 *
 */
public class ManualClimberControl extends Command {

    public ManualClimberControl() {
        requires(Robot.climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.oi.getClimberUpSwitch()){
    		Robot.climber.goUp();
    	}
    	else if(Robot.oi.getClimberDownSwitch()){
    		Robot.climber.stop();
    	}
    	else{
    		Robot.climber.stop();
    	}
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
