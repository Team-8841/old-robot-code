package org.usfirst.frc.team1065.robot.subsystems;

import org.usfirst.frc.team1065.robot.RobotMap;
import org.usfirst.frc.team1065.robot.commands.ManualObstacleManipulatorControl;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ObstacleManipulator extends Subsystem {
    
	private Compressor compressor;
	private Solenoid actuator;
	
	public ObstacleManipulator(){
		compressor = new Compressor();
		compressor.start();
		
		actuator = new Solenoid(RobotMap.OBSTACLE_MANIPULATOR_PORT);
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new ManualObstacleManipulatorControl());
    }
    
    public void setActuators(boolean value){
    	actuator.set(value);
    }
    
    public void stopConpressor(){
    	if(compressor.enabled()){
    		compressor.stop();
    	}
    }
    
    public void startConpressor(){
    	if(!compressor.enabled()){
    		compressor.start();
    	}
    }
}

