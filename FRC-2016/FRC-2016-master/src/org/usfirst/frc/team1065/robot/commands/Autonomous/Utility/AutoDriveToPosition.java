package org.usfirst.frc.team1065.robot.commands.Autonomous.Utility;

import org.usfirst.frc.team1065.robot.Robot;
import org.usfirst.frc.team1065.robot.RobotMap.StartingPosition;
import org.usfirst.frc.team1065.robot.RobotMap.TargetPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoDriveToPosition extends CommandGroup {
    
	public  AutoDriveToPosition(StartingPosition startingPosition, TargetPosition targetPosition) {
    	
    	if(targetPosition == TargetPosition.Left){
    		switch(startingPosition){
    		    case FarLeft:
    		    	addSequential(new RotateToAngle(.55, 63, 5));
    		    	addSequential(new DriveToDistance(.5,72,5));
    		    	break;
    		    case Left:
    		    	break;
    		    case Middle:
    		    	break;
    		    case Right:
    		    	break;
    		    case FarRight:
    		    	break;
    			default:
    				break;
    		}
    	}
    	else if(targetPosition == TargetPosition.Center){
    		switch(startingPosition){
		    case FarLeft:
		    	break;
		    case Left:
		    	break;
		    case Middle:
		    	addSequential(new DriveForTime(0,0,1));
		    	addSequential(new RotateToAngle(.55, 45, 2));
		    	addSequential(new DriveToDistance(.75,48,4));
		    	addSequential(new RotateToAngle(.5, 0, 2));
		    	addSequential(new DriveToDistance(.75,20,4));
		    	addSequential(new DriveForTime(.4,.4,2));
		    	addSequential(new DriveToDistance(-.4,36,3));
		    	break;
		    case Right:
		    	addSequential(new DriveForTime(0,0,1));
		    	addSequential(new RotateToAngle(.55, -18, 2));
		    	addSequential(new DriveToDistance(.65,14,4));
		    	addSequential(new RotateToAngle(.5, 0, 2));
		    	addSequential(new DriveToDistance(.75,20,4));
		    	addSequential(new DriveForTime(.4,.4,2));
		    	addSequential(new DriveToDistance(-.4,36,3));
		    	break;
		    case FarRight:
		    	break;
			default:
				break;
    		}
    	}
    	else if(targetPosition == TargetPosition.Right){
    		switch(startingPosition){
		    case FarLeft:
		    	break;
		    case Left:
		    	break;
		    case Middle:
		    	break;
		    case Right:
		    	addSequential(new RotateToAngle(.5, 60, 4));
		    	addSequential(new DriveToDistance(.5,45,4));
		    	addSequential(new RotateToAngle(.55, 0, 4));
		    	addSequential(new DriveToDistance(.5,80,5));
		    	addSequential(new DriveForTime(.35,.35,1));
		    	addSequential(new DriveToDistance(-.35,17,5));
		    	addSequential(new RotateToAngle(.55, -65, 4));
		    	break;
		    case FarRight:
		    	//addSequential(new RotateToAngle(.5, 30, 4));
		    	//addSequential(new DriveToDistance(.5,15,5));
		    	addSequential(new RotateToAngle(.5, 0, 4));
		    	addSequential(new DriveToDistance(.5,110,5));
		    	addSequential(new DriveForTime(.35,.35,2));
		    	addSequential(new DriveToDistance(-.35,17,4));
		    	addSequential(new RotateToAngle(.55, -62, 4));
		    	break;
			default:
				break;
    		}
    	}
    }
}
