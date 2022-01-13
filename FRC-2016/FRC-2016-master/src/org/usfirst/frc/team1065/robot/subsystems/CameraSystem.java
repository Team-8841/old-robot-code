package org.usfirst.frc.team1065.robot.subsystems;

import org.usfirst.frc.team1065.robot.RobotMap;
import org.usfirst.frc.team1065.robot.commands.DriveWithJoysticks;
import org.usfirst.frc.team1065.robot.commands.ManualCameraControl;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.vision.USBCamera;


public class CameraSystem extends Subsystem{

	int currSession;
    int sessionfront;
    int sessionback;
    Image frame;
    USBCamera cam0, cam1;
    
    public CameraSystem(){
    	
    	frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
    	//TODO: make it safe in case the cameras are not connected
    	try{
    		sessionfront = NIVision.IMAQdxOpenCamera(RobotMap.FRONT_CAM, NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        	sessionback = NIVision.IMAQdxOpenCamera(RobotMap.BACK_CAM, NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        	currSession = sessionfront;
        	NIVision.IMAQdxConfigureGrab(currSession);
    	}
    	catch(Exception e){
    		System.out.print("camera exception!!!!");
    	}
    }
    
	protected void initDefaultCommand() {
		setDefaultCommand(new ManualCameraControl());	
	}
	
	public void switchCamera(){
		if(currSession == sessionfront){
			switchToBack();
		}
		else{
			switchToFront();
		}
	}
	
	public void switchToBack(){
		try{
			NIVision.IMAQdxStopAcquisition(currSession);
			currSession = sessionback;
	        NIVision.IMAQdxConfigureGrab(currSession);
		}
		catch(Exception e){
			System.out.print("camera exception!!!!");
		}
	}
	
	public void switchToFront(){
		try{
			NIVision.IMAQdxStopAcquisition(currSession);
			currSession = sessionfront;
			NIVision.IMAQdxConfigureGrab(currSession);
		}
		catch(Exception e){
			System.out.print("camera exception!!!!");
		}
	}
	
	public void sendImageToDS(){
		try{
			NIVision.IMAQdxGrab(currSession, frame, 1);
			CameraServer.getInstance().setImage(frame);
		}
		catch(Exception e){
			System.out.print("camera exception!!!!");
		}
	}
	
}