package team1065.robot.frc2017.commands;

import edu.wpi.first.wpilibj.command.Command;
import team1065.robot.frc2017.Robot;

/**
 *
 */
public class ManualCameraControl extends Command {

    public ManualCameraControl() {
        requires(Robot.cameras);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.oi.getCamerafeedSwitch()){
    		//Robot.cameras.switchCamera();
    	}
    	//Robot.cameras.sendImageToDS();
    	
    	double mountVal = Robot.cameras.getMount();
    	double cameraStickX = Robot.oi.getCameraStickX();
    	
    	if(cameraStickX > 0.073){
    		mountVal += .01;
    	}
    	else if(cameraStickX < 0.053){
    		mountVal -= .01;
    	}
    	
    	Robot.cameras.setMount(mountVal);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
