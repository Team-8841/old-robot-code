package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

// Right Rocket

public class RightRocket extends CommandGroup {

    public RightRocket() {
		
		addSequential(new DriveToDistance(-.9, 50, 15));

		addSequential(new RotateToAngle(.7, -75, 15));
		addParallel(new AutoArmControl(true, false)); // Raise arm

		addSequential(new DriveToDistance(-.9, 90, 15));
		addSequential(new DriveForTime(0,0,0.5));
		addParallel(new AutoArmControl(true, true)); // Deploy hatch

		addSequential(new DriveToDistance(.9, 35, 15));
		addParallel(new AutoArmControl(false, true)); // Drop arm

		addSequential(new RotateToAngle(.7, 90, 15));

		addSequential(new DriveToDistance(-.9, 65, 15));
		//addSequential(new DriveForTime(0,0,0.5));
		//addParallel(new AutoArmControl(false, false)); // Grab hatch

		//addSequential(new DriveToDistance(.9, 50, 15));

		//addSequential(new RotateToAngle(.7, -90, 15));
		
		//addSequential(new DriveToDistance(-.9, 40, 15));
		
		
		
		/*addSequential(new DriveToDistance(.9, 65, 15));

		addSequential(new RotateToAngle(.7, -90, 15));

		addSequential(new DriveToDistance(.9, 150, 15));

		addSequential(new RotateToAngle(.7, -135, 15));
		addParallel(new AutoArmControl(true, false));//lift arm
		
		addSequential(new DriveToDistance(-.7, 30, 15));
		addSequential(new DriveForTime(0,0,0.5));
		addParallel(new AutoArmControl(true, true));//deploy with armed raised

		addSequential(new DriveToDistance(.7, 30, 15));

		addSequential(new RotateToAngle(.7, -90, 15));
		addParallel(new AutoArmControl(false, true));

		addSequential(new DriveToDistance(-.9, 150, 15));*/
		//maybe just stop short and wait for the drivers to pick up the hatch so we dont break the grabber if we are misaligned

		//take new panel
		//addSequential(new DriveToDistance(.6, 175, 15));

		//addSequential(new RotateToAngle(.7, -135, 15));

		//addSequential(new DriveToDistance(-.7, 30, 15));

		//addSequential(new DriveToDistance(.7, 30, 15));

		/*
		addSequential(new DriveToDistance(.9, 120, 15));

		addSequential(new RotateToAngle(.8, 40, 15));

		addSequential(new DriveToDistance(.9, 110, 15));

		addSequential(new RotateToAngle(.8, -40, 15));

		addSequential(new DriveToDistance(-.5, 15, 15)); 
		// Deploy Panel
		addSequential(new DriveForTime(0, 0, 1));

		addSequential(new DriveToDistance(.6, 30, 15));

		addSequential(new RotateToAngle(.8, 0, 15));

		addSequential(new DriveToDistance(-.9, 70, 15));

		addSequential(new RotateToAngle(.8, -35, 15));

		addSequential(new DriveToDistance(-.8, 30, 15));

		addSequential(new RotateToAngle(.8, 0, 15));

		addSequential(new DriveToDistance(-.8, 60, 15)); 
		// Get New Panel
		addSequential(new DriveForTime(0, 0, 1));

		addSequential(new DriveToDistance(.7, 20, 15));

		addSequential(new RotateToAngle(.8, -10, 15));

		addSequential(new DriveToDistance(1, 150, 15));

		addSequential(new RotateToAngle(.8, -120, 15));

		addSequential(new DriveToDistance(-.7, 25, 15));

		addSequential(new RotateToAngle(.8, -35, 15));

		addSequential(new DriveToDistance(-.6, 10, 15)); 
		// Deploy Panel
		*/
    }
}
