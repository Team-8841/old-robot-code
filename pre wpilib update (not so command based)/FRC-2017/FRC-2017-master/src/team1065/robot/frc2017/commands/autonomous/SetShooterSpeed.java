package team1065.robot.frc2017.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import team1065.robot.frc2017.Robot;

/**
 *
 */
public class SetShooterSpeed extends Command {
    double speed;
    public SetShooterSpeed(double speed) {
        requires(Robot.shooter);
        this.speed = speed;
        this.setTimeout(.1);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(Robot.oi.getShooterOverride() || speed == 0){
    		Robot.shooter.setOpenLoop(speed/4000);
    	}
    	else{
    		Robot.shooter.setRpm(speed);
    	}
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
