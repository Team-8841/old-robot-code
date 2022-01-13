package org.usfirst.frc.team1065.robot.commands;

import org.usfirst.frc.team1065.robot.Robot;
import org.usfirst.frc.team1065.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualBoulderControl extends Command {

	boolean shoot;
	double timer;
    public ManualBoulderControl() {
        requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	shoot = false;
    	timer = 0.0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() { 	
    	if(shoot){
    		if(timer > 0.25){//latch shoot signal for 2 seconds
    			shoot = false;
    		}
    		timer += 0.02;
    	}
    	
    	if(Robot.oi.getIntakeOutSwitch()){
    		Robot.intake.setIntakeOut(RobotMap.INTAKE_OUT_SPEED);
    		
    		if(Robot.oi.getRightJoystickTrigger() && Robot.oi.getRightJoystickTop()){
    			Robot.intake.setQueuingDown(RobotMap.QUEUING_DOWN_SPEED);
    		}
    		else{
    			Robot.intake.setQueuingDown(0.0);
    		}
    	}
    	else if(Robot.oi.getIntakeInSwitch()){
    		
    		if(Robot.oi.getRightJoystickTrigger() && Robot.oi.getRightJoystickTop()){
    			//reset timer if shooter was already up to speed
    			if(shoot){
    				timer = 0.0;
    			}
    			//send shoot command if the shooter is up to speed
    			else if(Robot.shooter.onTarget() || !Robot.shooter.isPIDEnabled()){
    				shoot = true;
    				timer = 0.0;
    			}
			}
    		
    		if(shoot){
    			Robot.intake.setQueuingUp(RobotMap.QUEUING_SHOOTING_SPEED);
    		}
    		else if(!Robot.intake.getQueuingIR()){//took out requirement for shooter to be at speed
				Robot.intake.setQueuingUp(RobotMap.QUEUING_UP_SPEED);
			}
			else{
				Robot.intake.setQueuingDown(0.0);
			}
    		
    		
    		if(Robot.intake.getQueuingIR()){
    			Robot.intake.setIntakeOut(0.0);//might want reverse
    		}
    		else{
    			Robot.intake.setIntakeIn(RobotMap.INTAKE_IN_SPEED);
    		}
    	}
    	else{
    		Robot.intake.setIntake(0.0);
    		Robot.intake.setQueuing(0.0);
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
