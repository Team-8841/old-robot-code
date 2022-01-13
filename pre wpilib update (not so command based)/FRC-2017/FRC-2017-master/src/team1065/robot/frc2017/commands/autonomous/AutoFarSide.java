package team1065.robot.frc2017.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team1065.robot.frc2017.Robot;

/**
 *
 */
public class AutoFarSide extends CommandGroup {

    public AutoFarSide() {
    	int allianceMultiplier = Robot.oi.isRedAlliance()? 1:-1;
    	//go score a gear
    	addSequential(new DriveToDistance(0.4, 101, 15.0));
    	addSequential(new RotateToAngle(.4, 54.5 * allianceMultiplier, 15.0));
    	addSequential(new DriveToDistance(0.5, 20, 15.0));
    	addParallel(new PushGearForTime(2.0));
    	addSequential(new DriveForTime(0,0,0.8));
    	addSequential(new DriveToDistance(-0.5, 30, 15.0));
    	//go hopper
    	addSequential(new RotateToAngle(.4, 0 * allianceMultiplier, 15.0));
    	addSequential(new DriveToDistance(0.8, 200, 15.0));
    }
}
