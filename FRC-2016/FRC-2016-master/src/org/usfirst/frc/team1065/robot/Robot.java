
package org.usfirst.frc.team1065.robot;

import org.usfirst.frc.team1065.robot.RobotMap.Obstacle;
import org.usfirst.frc.team1065.robot.RobotMap.StartingPosition;
import org.usfirst.frc.team1065.robot.RobotMap.TargetGoal;
import org.usfirst.frc.team1065.robot.RobotMap.TargetPosition;
import org.usfirst.frc.team1065.robot.commands.Autonomous.AutoCross;
import org.usfirst.frc.team1065.robot.commands.Autonomous.AutoCrossBack;
import org.usfirst.frc.team1065.robot.commands.Autonomous.AutoCrossDelayAndFollow;
import org.usfirst.frc.team1065.robot.commands.Autonomous.AutoReach;
import org.usfirst.frc.team1065.robot.commands.Autonomous.AutoShoot;
import org.usfirst.frc.team1065.robot.commands.Autonomous.AutoShootDelayAndFollow;
import org.usfirst.frc.team1065.robot.commands.Autonomous.Utility.DriveForTime;
import org.usfirst.frc.team1065.robot.subsystems.CameraSystem;
import org.usfirst.frc.team1065.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1065.robot.subsystems.Intake;
import org.usfirst.frc.team1065.robot.subsystems.Lighting;
import org.usfirst.frc.team1065.robot.subsystems.ObstacleManipulator;
import org.usfirst.frc.team1065.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot {

	public static OI oi;
	public static DriveTrain drive;
	public static Intake intake;
	public static Shooter shooter;
	public static ObstacleManipulator manipulator;
	public static Lighting lighting;
	public static CameraSystem camera;

    Command autonomousCommand;

    public void robotInit() {
		oi = new OI();
		drive = new DriveTrain();
		intake = new Intake();
		shooter = new Shooter();
		manipulator = new ObstacleManipulator();
		lighting = new Lighting();
		camera = new CameraSystem();
		
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
    	drive.resetAngle();
    	drive.resetEncoders();
    	
    	Command[][] CommandsArray = {
				{
					new AutoShoot(Obstacle.LowBar,TargetGoal.None, StartingPosition.FarLeft, TargetPosition.Left),
					new AutoShoot(Obstacle.LowBar,TargetGoal.High, StartingPosition.FarLeft, TargetPosition.Left),
					new AutoShoot(Obstacle.LowBar,TargetGoal.Low, StartingPosition.FarLeft, TargetPosition.Left),
				},
				{
					new AutoCross(Obstacle.Ramparts),
					new AutoShoot(Obstacle.Ramparts,TargetGoal.High, StartingPosition.Middle, TargetPosition.Center),
					new AutoShoot(Obstacle.Ramparts,TargetGoal.High, StartingPosition.Right, TargetPosition.Center),
				},
				{
					new AutoShoot(Obstacle.Ramparts,TargetGoal.None, StartingPosition.Right, TargetPosition.Right),
					new AutoShoot(Obstacle.Ramparts,TargetGoal.High, StartingPosition.Right, TargetPosition.Right),
					new AutoShoot(Obstacle.Ramparts,TargetGoal.Low, StartingPosition.Right, TargetPosition.Right),
				},
				{
					new AutoShoot(Obstacle.Ramparts,TargetGoal.None, StartingPosition.FarRight, TargetPosition.Right),
					new AutoShoot(Obstacle.Ramparts,TargetGoal.High, StartingPosition.FarRight, TargetPosition.Right),
					new AutoShoot(Obstacle.Ramparts,TargetGoal.Low, StartingPosition.FarRight, TargetPosition.Right),
				},
		};

    	//Selector 0 == low bar, 1 == CDF/port, 2 == 4th, 3 == brick/5th
    	//switch 0 == middle, 1 == in, 2 == out
    	int autoSelector = oi.getAutoKnobPosition();
    	int autoSwitch = oi.getAutoSwitchPosition();
    	
    	autonomousCommand = CommandsArray[autoSelector][autoSwitch];
    	
    	
        if (autonomousCommand != null){
        	autonomousCommand.start();
        }
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
    	if (autonomousCommand != null){
    		autonomousCommand.cancel();
    	}
    	
    	drive.resetAngle();
    	shooter.ForceHoodClose(false);
    }

    public void disabledInit(){

    }

    public void teleopPeriodic() {
    	shooter.updateStatus();
        Scheduler.getInstance().run();
    }
    
    public void testPeriodic() {
        LiveWindow.run();
    }
}
