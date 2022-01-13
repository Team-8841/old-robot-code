package team1065.robot.frc2017.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team1065.robot.frc2017.Robot;

/**
 *
 */
public class AutoCenterShoot extends CommandGroup {

    public AutoCenterShoot() {
    	int allianceMultiplier = Robot.oi.isRedAlliance()? 1:-1;
    	
    	addSequential(new DriveToDistance(0.4, 78, 15.0));
    	addParallel(new PushGearForTime(2.0));
    	addSequential(new DriveForTime(0,0,0.8));
    	addSequential(new DriveToDistance(-0.42, 43, 15.0));
    	
    	addSequential(new RotateToAngle(.5, 90 * allianceMultiplier, 15.0));
    	addSequential(new DriveToDistance(0.5, 118, 15.0));
    	addSequential(new RotateToAngle(.5, 135 * allianceMultiplier, 15.0));
    	addSequential(new SetShooterSpeed(1750));
    	//addSequential(new DriveToDistance(0.4, 12, 15.0));
    	addParallel(new ShootForTime(5.0));
    	addSequential(new DriveForTime(0.4,0.4,1.0));
    	addSequential(new DriveForTime(0,0,4.0));
    	
    }
}
