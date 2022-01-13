package org.usfirst.frc.team1065.robot.commands;

import org.usfirst.frc.team1065.robot.Robot;
import org.usfirst.frc.team1065.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveWithJoysticks extends Command {

    public DriveWithJoysticks() {
        requires(Robot.drive);
    }

    protected void initialize() {
    }

    protected void execute() {
    	double leftY = Robot.oi.getLeftJoystickY();
    	double rightY = Robot.oi.getRightJoystickY();
    	double averageY = Robot.oi.getYAverage();
    	
    	double joystickDiff = Math.abs(leftY-rightY);
    	
    	if((leftY * rightY >= 0) && joystickDiff < (Math.abs(averageY) * RobotMap.DRIVE_STRAIGHT_BAND_PERCENTAGE))
        {
        	Robot.drive.tankDrive(averageY, averageY);
        }
        else
        {
        	Robot.drive.tankDrive(leftY, rightY);
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
