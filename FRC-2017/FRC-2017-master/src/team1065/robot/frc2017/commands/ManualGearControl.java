package team1065.robot.frc2017.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import team1065.robot.frc2017.Robot;

/**
 *
 */
public class ManualGearControl extends Command {

    public ManualGearControl() {
        requires(Robot.gearSystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.shooter.isOnTarget()){
    		double timeDecimal = Timer.getFPGATimestamp() % 0.4;
    		if(timeDecimal < 0.25){
    			Robot.gearSystem.setLED(true);
    		}
    		else{
    			Robot.gearSystem.setLED(false);
    		}
    	}
    	else{
    		Robot.gearSystem.setLED(Robot.gearSystem.isGearDetected());
    	}
    	
    	if(Robot.oi.getLeftJoystickTrigger()){
    		Robot.gearSystem.extendGearPusher();
    	}
    	else{
    		Robot.gearSystem.retractGearPusher();
    	}
    	
    	if(Robot.oi.getGearIntakeCloseSwitch()){
    		Robot.gearSystem.closeGearIntake();
    	}
    	else{
    		Robot.gearSystem.openGearIntake();
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
