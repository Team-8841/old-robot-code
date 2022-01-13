package org.usfirst.frc.team1065.robot.commands.Autonomous;

import org.usfirst.frc.team1065.robot.RobotMap;
import org.usfirst.frc.team1065.robot.RobotMap.Obstacle;
import org.usfirst.frc.team1065.robot.RobotMap.StartingPosition;
import org.usfirst.frc.team1065.robot.RobotMap.TargetGoal;
import org.usfirst.frc.team1065.robot.RobotMap.TargetPosition;
import org.usfirst.frc.team1065.robot.commands.Autonomous.Utility.AutoDriveToPosition;
import org.usfirst.frc.team1065.robot.commands.Autonomous.Utility.DriveForTime;
import org.usfirst.frc.team1065.robot.commands.Autonomous.Utility.DriveToDistance;
import org.usfirst.frc.team1065.robot.commands.Autonomous.Utility.SetIntakeSpeed;
import org.usfirst.frc.team1065.robot.commands.Autonomous.Utility.SetShooterSpeed;
import org.usfirst.frc.team1065.robot.commands.Autonomous.Utility.ShootHigh;
import org.usfirst.frc.team1065.robot.commands.Autonomous.Utility.ShootLow;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoShoot extends CommandGroup {
    
    public  AutoShoot(Obstacle obstacle, TargetGoal targetGoal, StartingPosition startingPosition, TargetPosition targetGoalPosition) {
    	addSequential(new AutoCross(obstacle));
    	addSequential(new AutoDriveToPosition(startingPosition, targetGoalPosition));
    	
    	if(targetGoal == TargetGoal.Low){
    		addSequential(new DriveToDistance(.65, 35, 2.5));
    		//addSequential(new SetIntakeSpeed(RobotMap.INTAKE_OUT_SPEED, 1.0));
    		addSequential(new ShootLow(6.0));
    	}
    	else if(targetGoal == TargetGoal.High){
    		//addSequential(new SetShooterSpeed(5600));
    		addSequential(new DriveForTime(0, 0, 2));
    		addSequential(new ShootHigh(5.0));
    	}
    }
}
