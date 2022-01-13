package org.usfirst.frc.team1065.robot.commands;

import org.usfirst.frc.team1065.robot.Robot;
import org.usfirst.frc.team1065.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualCameraControl extends Command {
	
	public ManualCameraControl() {
        requires(Robot.camera);
    }

    protected void initialize() {
    }

	protected void execute() {
		 boolean leftTrig = Robot.oi.getLeftJoystickTrigger();
		 
		 if(leftTrig){
			 Robot.camera.switchCamera(); 
		 }
		 
		 Robot.camera.sendImageToDS();
	}

	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	protected void end() {
		// TODO Auto-generated method stub
		
	}

	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}
}