/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ManualElevatorControl extends Command {
  public ManualElevatorControl() {
    requires(Robot.m_elevator);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.m_oi.getClimberExtend()){
      Robot.m_elevator.setVoltage(-0.3);//tune
    }
    else{
      if(Robot.m_oi.getElevatorOverride()){
        
        double ElevatorStickY = Robot.m_oi.getElevatorStickY();
          
          if(Math.abs(ElevatorStickY - RobotMap.ELEVATOR_JOYSTICK_MIDDLE) > RobotMap.ELEVATOR_JOYSTICK_DEADBAND){
            Robot.m_elevator.setVoltage((ElevatorStickY - RobotMap.ELEVATOR_JOYSTICK_MIDDLE)/RobotMap.ELEVATOR_JOYSTICK_MIDDLE);
          }
          else {
            Robot.m_elevator.setVoltage(0);
          }
      }
      else{
        Robot.m_elevator.setPosition(Robot.m_oi.getElevatorDesiredPosition());
      }
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
    Robot.m_elevator.setVoltage(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
