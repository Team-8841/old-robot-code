package team1065.robot.frc2017.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import team1065.robot.frc2017.Robot;

/**
 *
 */
public class DriveForTime extends Command {
	double leftSpeed, rightSpeed;
    public DriveForTime(double leftSpeed, double rightSpeed, double time) {
    	requires(Robot.driveTrain);
        
        this.leftSpeed = leftSpeed;
        this.rightSpeed = rightSpeed;
        this.setTimeout(time);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.tankDrive(leftSpeed, rightSpeed);
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
