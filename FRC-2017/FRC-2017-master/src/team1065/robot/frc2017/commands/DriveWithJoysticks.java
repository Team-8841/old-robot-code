package team1065.robot.frc2017.commands;

import edu.wpi.first.wpilibj.command.Command;
import team1065.robot.frc2017.Robot;
import team1065.robot.frc2017.RobotMap;


public class DriveWithJoysticks extends Command {

    public DriveWithJoysticks() {
    	requires(Robot.driveTrain);
    }

    protected void initialize() {
    }

    protected void execute() {
    	double leftY = Robot.oi.getLeftJoystickY();
    	double rightY = Robot.oi.getRightJoystickY();
    	double rightX = Robot.oi.getRightJoystickX();
    	
    	if(Robot.oi.getLeftJoystickTrigger()){
    		leftY *= .5;
    		rightY *= .5;
    	}
    	
    	if(Robot.oi.getLeftJoystickTop()){
    		Robot.driveTrain.enableDropDownTraction();
    	}
    	else{
    		Robot.driveTrain.disableDropDownTraction();
    	}
    	
    	if(Robot.oi.getRightJoystickTop() && !Robot.oi.getLeftJoystickTop()){
    		Robot.driveTrain.mecanumStraightDrive(rightX, rightY);
    	}
    	else{
    		double averageY = (leftY + rightY)/2.0;
        	double joystickDiff = Math.abs(leftY-rightY);
        	
    		if((leftY * rightY >= 0) && joystickDiff < (Math.abs(averageY) * RobotMap.DRIVE_STRAIGHT_BAND_PERCENTAGE)){
    			Robot.driveTrain.straightDrive((leftY + rightY)/2);
    		}
    		else{
    			Robot.driveTrain.tankDrive(leftY, rightY);
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
