/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ManualArmControl extends Command {
  public ManualArmControl() {
    requires(Robot.m_arm);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.m_oi.getArmUpSwitch()){
      Robot.m_arm.setArmActuators(true);;
    }
    else if(Robot.m_oi.getArmDownSwitch()){
      Robot.m_arm.setArmActuators(false);
    }else{
        Robot.m_arm.setArmActuators(false);
    }

    if(!Robot.m_oi.getGrabberControl()){
      Robot.m_arm.setGrabberActuator(true);
    }else{
      Robot.m_arm.setGrabberActuator(false);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_arm.setArmActuators(false);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
