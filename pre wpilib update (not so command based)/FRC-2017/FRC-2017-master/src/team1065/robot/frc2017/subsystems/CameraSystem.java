package team1065.robot.frc2017.subsystems;

import org.opencv.core.Mat;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team1065.robot.frc2017.RobotMap;
import team1065.robot.frc2017.commands.ManualCameraControl;

/**
 *
 */
public class CameraSystem extends Subsystem {
	public enum State{FRONT_CAMERA_FEED, SIDE_CAMERA_FEED};
	Servo mount;
	UsbCamera camera0;
	//UsbCamera camera1;
	//CvSink cvSink0, cvSink1;
	////CvSource outputStream;
	//Mat image;
	State currSession;
	
	public CameraSystem(){
		mount = new Servo(RobotMap.CAMERA_SERVO_PORT);
		setMount(0);
		
		camera0 = CameraServer.getInstance().startAutomaticCapture(0);
		camera0.setFPS(15);
		camera0.setResolution(320, 240);
		camera0.setExposureManual(40);
		camera0.setExposureHoldCurrent();
		
		/*camera1 = CameraServer.getInstance().startAutomaticCapture(1);
		camera1.setFPS(15);
		camera1.setResolution(320, 240);
		camera1.setExposureManual(20);
		camera1.setExposureHoldCurrent();*/
		
		/*cvSink0 = CameraServer.getInstance().getVideo(camera0);
        outputStream = CameraServer.getInstance().putVideo("Switcher", 320, 240);
        
        image = new Mat();
        currSession = State.FRONT_CAMERA_FEED;*/
	}
    public void initDefaultCommand() {
        setDefaultCommand(new ManualCameraControl());
    }
    
    public void setMount(double val){
    	if (val < 0.08){
    		val = 0.08;
    	}
    	else if (val > 0.44){
    		val = 0.44;
    	}
    	mount.set(val);
    }
    
    public double getMount(){
    	return mount.get();
    }
    
    /*
    public void switchCamera(){
		if(currSession == State.FRONT_CAMERA_FEED){
			switchToSideFeed();
		}
		else{
			switchToFrontFeed();
		}
	}
	
	public void switchToSideFeed(){
		cvSink0.setSource(camera1);
        currSession = State.SIDE_CAMERA_FEED;
	}
	
	public void switchToFrontFeed(){
		cvSink0.setSource(camera0);
        currSession = State.FRONT_CAMERA_FEED;
	}
	
	public void sendImageToDS(){
		cvSink0.grabFrame(image);
        outputStream.putFrame(image);
	}
    */
    
    public void updateStatus(){
    	SmartDashboard.putNumber("Camera Angle", getMount());
    }
}