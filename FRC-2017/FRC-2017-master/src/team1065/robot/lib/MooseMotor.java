package team1065.robot.lib;

import edu.wpi.first.wpilibj.SpeedController;
import team1065.robot.frc2017.Robot;

public class MooseMotor {
	private SpeedController speedController;
	private String name;
	private int pdpPort;
	
	public MooseMotor(SpeedController speedController, String name, int pdpPort){
		this.speedController = speedController;
		this.name = name;
		this.pdpPort = pdpPort;
	}
	
	public void setMotor(double val){
		speedController.set(val);
	}
	
	public double getCurrent(){
		return Robot.pdp.getCurrent(pdpPort);
	}
}
