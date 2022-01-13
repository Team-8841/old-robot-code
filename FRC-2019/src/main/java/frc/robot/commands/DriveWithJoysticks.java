package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;


public class DriveWithJoysticks extends Command {

    public DriveWithJoysticks() {
    	requires(Robot.m_driveTrain);
    }

    protected void initialize() {
    }

    protected void execute() {

    	double leftY = Robot.m_oi.getLeftJoystickY();
    	double rightY = Robot.m_oi.getRightJoystickY();
    	double rightX = Robot.m_oi.getRightJoystickX();
    	
    	if(Robot.m_oi.getLeftJoystickTrigger()){
    		leftY *= .5;
    		rightY *= .5;
    	}
    	
    	if(Robot.m_oi.getLeftJoystickTop()){
    		Robot.m_driveTrain.enableDropDownTraction();
    	}
    	else{
    		Robot.m_driveTrain.disableDropDownTraction();
    	}
    	
    	if(Robot.m_oi.getRightJoystickTop() && !Robot.m_oi.getLeftJoystickTop()){
    		Robot.m_driveTrain.mecanumStraightDrive(rightX, rightY);
    	}
    	else{
    		double averageY = (leftY + rightY)/2.0;
        	double joystickDiff = Math.abs(leftY-rightY);
        	
    		if((leftY * rightY >= 0) && joystickDiff < (Math.abs(averageY) * RobotMap.DRIVE_STRAIGHT_BAND_PERCENTAGE)){
    			Robot.m_driveTrain.straightDrive((leftY + rightY)/2);
    		}
    		else{
    			Robot.m_driveTrain.tankDrive(leftY, rightY);
    		}
    	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
