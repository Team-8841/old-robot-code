/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ManualCameraControl;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class VisionSystem extends Subsystem {
    public enum State{FRONT_VIEW, RIGHT_VIEW, BACK_VIEW};
    State curCamState;
    Servo mount;
	UsbCamera camera0;
	//UsbCamera camera1;
	//CvSink cvSink0, cvSink1;
	////CvSource outputStream;
	//Mat image;
	State currSession;
  public VisionSystem() {
    mount = new Servo(RobotMap.CAMERA_SERVO_PORT);
    setMount(0);

    setCamState(State.BACK_VIEW);
    
    camera0 = CameraServer.getInstance().startAutomaticCapture(0);
    camera0.setFPS(15);
    camera0.setResolution(320, 240);
    camera0.setExposureManual(40);
    camera0.setExposureHoldCurrent();   
  }

  public void update(){
      if(getState() == State.FRONT_VIEW){
          setMount(0.75);
      }else if (getState() == State.RIGHT_VIEW){
          setMount(0.3);
      }else if(getState() == State.BACK_VIEW){
          setMount(0);
      }
  }

  public void setMount(double val){
        mount.set(val);
    }

    public void setCamState(State state){
        curCamState = state;
    }

    public double getMount(){
        return mount.get();
    }

    public State getState(){
        return curCamState;
    }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ManualCameraControl());
  }

  public void updateStatus(){
      SmartDashboard.putNumber("CamJoystick ValX", Robot.m_oi.getCameraStickX());
      SmartDashboard.putNumber("CamJoystick ValY", Robot.m_oi.getCameraStickY());
      SmartDashboard.putString("Camera State", getState().toString());
      SmartDashboard.putNumber("Camera Pos", getMount());
  }
}
