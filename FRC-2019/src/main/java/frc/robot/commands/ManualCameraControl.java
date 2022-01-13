/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.VisionSystem.State;

public class ManualCameraControl extends Command {
  public ManualCameraControl() {
    requires(Robot.m_vision);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double cameraStickX = Robot.m_oi.getCameraStickX();
    double cameraStickY = Robot.m_oi.getCameraStickY();

      if(cameraStickY > 0.07){
        Robot.m_vision.setCamState(State.FRONT_VIEW);
      }else if (cameraStickY < 0.03){
        Robot.m_vision.setCamState(State.BACK_VIEW);
      }
      if(cameraStickX > 0.07){
        Robot.m_vision.setCamState(State.RIGHT_VIEW);
      }

      Robot.m_vision.update();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
