package team1065.robot.frc2017.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoTest extends CommandGroup {

    public AutoTest() {
    	addSequential(new DriveToDistance(0.4, 40, 15.0));
    	addSequential(new DriveForTime(0,0,0.5));
    	addSequential(new RotateToAngle(.4, -90, 15.0));
    	addSequential(new DriveForTime(0,0,0.5));
    	addSequential(new RotateToAngle(.4, 90, 15.0));
    	addSequential(new DriveForTime(0,0,0.5));
    	addSequential(new RotateToAngle(.4, 0, 15.0));
    	//addSequential(new DriveToDistance(0.4, 30, 15.0));
    	//addSequential(new PushGearForTime(2.0));
    	//addSequential(new SetIntake(.8));
    	//addSequential(new SetShooterSpeed(1800));
    	//addSequential(new ShootForTime(5.0));
    }
}
