package team1065.robot.frc2017.commands;

import edu.wpi.first.wpilibj.command.Command;
import team1065.robot.frc2017.Robot;

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
    	//TODO: Check edge transitions instead of sending one or the other
    	if(Robot.oi.getShooterOverride() || Robot.oi.getShooterDesiredSpeed() == 0){
    		Robot.shooter.setOpenLoop(Robot.oi.getShooterDesiredSpeed());
    	}
    	else{
    		Robot.shooter.setRpm(Robot.oi.getShooterDesiredSpeed());
    	}
    	
    	//if commanded to shoot and the shooter is on target or we are already shooting
    	if(Robot.oi.getRightJoystickTrigger() && (Robot.shooter.isOnTarget() || Robot.shooter.isIndexerOpen())){
    		Robot.shooter.setAgitator(.65);
    		Robot.shooter.setIndexerOpen();
    	}
    	else if(Robot.oi.getIntakeInSwitch()){
    		//Robot.shooter.setAgitator(-.2);
    		Robot.shooter.setIndexerClose();
    	}
    	else{
    		Robot.shooter.setAgitator(0);
    		Robot.shooter.setIndexerClose();
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
