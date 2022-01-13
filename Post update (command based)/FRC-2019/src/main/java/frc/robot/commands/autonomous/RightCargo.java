package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

// Right Cargo
public class RightCargo extends CommandGroup {

    public RightCargo() {
		addSequential(new DriveToDistance(-.60, 110, 15));
		addSequential(new DriveForTime(-.3, -.3, 1.5));  // Deploy Panel
		addParallel(new AutoArmControl(false, true));

		addSequential(new DriveToDistance(.7, 13, 15));
		//addSequential(new DriveForTime(0,0,0.5));
		

		addSequential(new RotateToAngle(.65, 125, 15));
		//addSequential(new DriveForTime(0,0,0.5));
		addParallel(new AutoArmControl(false, false));

		addSequential(new DriveToDistance(-.75, 102, 15));
		//addSequential(new DriveForTime(0,0,0.5));
		addSequential(new RotateToAngle(.55, 180, 15));
		//addSequential(new DriveForTime(0,0,0.5));
		addParallel(new AutoArmControl(false, true));

		addSequential(new DriveToDistance(-.6, 60, 15));

		addSequential(new DriveForTime(-0.3, -0.3, 1.5));
		addParallel(new AutoArmControl(false, false));

		addSequential(new DriveToDistance(.8, 70, 15));
		//addParallel(new AutoArmControl(false, false));
		//addSequential(new DriveForTime(0, 0, 0.5));
		//addParallel(new AutoArmControl(false, false));

		//maybe just stop short and wait for the drivers to pick up the hatch so we dont break the grabber if we are misaligned

		//addSequential(new DriveToDistance(-.7, 45, 15));
		//addSequential(new DriveForTime(0,0,0.5)); // Pickup panel
		//addParallel(new AutoArmControl(false, false));

		//addSequential(new DriveToDistance(.7, 50, 15));
		//addSequential(new DriveForTime(0,0,0.5));
		
		//addSequential(new RotateToAngle(.65, 300, 15));
		//addSequential(new DriveForTime(0,0,0.5));

		//addSequential(new DriveToDistance(-.8, 130, 15));
		//addSequential(new DriveForTime(0,0,0.5));

		//addSequential(new RotateToAngle(.65, 359, 15));
		//addSequential(new DriveForTime(0,0,0.5));
		//addSequential(new DriveToDistance(-.7, 25, 15));

    }
}
