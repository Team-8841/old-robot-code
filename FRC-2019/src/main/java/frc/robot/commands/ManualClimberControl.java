/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ManualClimberControl extends Command {
  public boolean previousState;
  public boolean wantedFrontstate;
  public ManualClimberControl() {
    requires(Robot.m_climber);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    wantedFrontstate = false;
    previousState = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.m_oi.getClimberExtend()){
      Robot.m_climber.setBackActuator(true);
    }
    else{
      Robot.m_climber.setBackActuator(false);
    }

    if(Robot.m_climber.isBackActuatorExtended()){
      Robot.m_climber.setMotor(Robot.m_oi.getRightJoystickY());
    }
    else{
      Robot.m_climber.setMotor(0);
    }
    
    boolean currentState;
    currentState = Robot.m_oi.getLeftJoystick11Button() && Robot.m_oi.getLeftJoystickTrigger();

    if(currentState == true && previousState == false){
      wantedFrontstate = !wantedFrontstate;
    }

    Robot.m_climber.setFrontActuator(wantedFrontstate);

    previousState = currentState;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_climber.setFrontActuator(false);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
