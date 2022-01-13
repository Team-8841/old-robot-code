
package team1065.robot.frc2017;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import team1065.robot.frc2017.commands.SelfTest.AutoSelfTest;
import team1065.robot.frc2017.commands.autonomous.AutoBoiler;
import team1065.robot.frc2017.commands.autonomous.AutoCenterRun;
import team1065.robot.frc2017.commands.autonomous.AutoCenterShoot;
import team1065.robot.frc2017.commands.autonomous.AutoFarSide;
import team1065.robot.frc2017.commands.autonomous.AutoTest;
import team1065.robot.frc2017.subsystems.CameraSystem;
import team1065.robot.frc2017.subsystems.Climber;
import team1065.robot.frc2017.subsystems.DriveTrain;
import team1065.robot.frc2017.subsystems.GearSystem;
import team1065.robot.frc2017.subsystems.Intake;
import team1065.robot.frc2017.subsystems.Shooter;

public class Robot extends IterativeRobot {

	public static OI oi;
	public static DriveTrain driveTrain;
	public static Shooter shooter;
	public static Intake intake;
	public static Compressor compressor;
	public static CameraSystem cameras;
	public static GearSystem gearSystem;
	public static Climber climber;
	public static PowerDistributionPanel pdp;
	
	Command autonomousCommand;

    public void robotInit() {
		oi = new OI();
		driveTrain = new DriveTrain();
		shooter = new Shooter();
		intake = new Intake();
		compressor = new Compressor();
		cameras = new CameraSystem();
		gearSystem = new GearSystem();
		climber = new Climber();
		pdp = new PowerDistributionPanel();
    }
	
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
    	driveTrain.resetAngle();
    	driveTrain.resetEncoder();
    	
    	//testing
    	/*
    	Command[] CommandsArray = {
			new AutoTest(),
			new AutoTest(),
			new AutoTest(),
			new AutoTest(),
		};*/
    	
    	Command[] CommandsArray = {
			new AutoBoiler(),
			new AutoCenterShoot(),
			new AutoCenterRun(),
			new AutoFarSide(),
		};

    	//Selector 0 == Boiler(gear then shoot), 1 == Center(gear then shoot), 2 == Center(gear then go to neutral), 3 == Hooper (gear the go to neutral)
    	int autoSelector = oi.getAutoKnobPosition();
    	
    	//Do SelfTest if left joystick top and trigger buttons are pressed when autonomous mode is started
    	if(oi.getLeftJoystickTop() && oi.getLeftJoystickTrigger()){
    		//TODO:add
    		autonomousCommand = new AutoSelfTest();
    	}
    	else{
    		autonomousCommand = CommandsArray[autoSelector];
    	}
        if (autonomousCommand != null){
        	autonomousCommand.start();
        }
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        driveTrain.updateStatus();
        shooter.updateStatus();
        gearSystem.updateStatus();
    }

    public void teleopInit() {
    	driveTrain.resetAngle();
    	driveTrain.resetEncoder();
    	if (autonomousCommand != null){
    		autonomousCommand.cancel();
    	}
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        driveTrain.updateStatus();
        shooter.updateStatus();
        gearSystem.updateStatus();
        cameras.updateStatus();
        
        if(oi.getCompressorOverride()){
        	compressor.setClosedLoopControl(false);
        }
        else{
        	compressor.setClosedLoopControl(true);
        }
        
    }
    
    public void testPeriodic() {
        LiveWindow.run();
    }
}
