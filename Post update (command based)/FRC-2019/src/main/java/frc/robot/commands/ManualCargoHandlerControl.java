/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.CargoHandler.IntakeState;
import frc.robot.subsystems.CargoHandler.ShooterState;

public class ManualCargoHandlerControl extends Command {
  public ManualCargoHandlerControl() {
    requires(Robot.m_cargoHandler);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    if(Robot.m_oi.getIntakeIn()){
      Robot.m_cargoHandler.setIntakeState(IntakeState.IN);
    }
    else if(Robot.m_oi.getIntakeOut()){
      Robot.m_cargoHandler.setIntakeState(IntakeState.OUT);
    }
    else{
      Robot.m_cargoHandler.setIntakeState(IntakeState.OFF);
    }


    if(Robot.m_oi.getRightJoystickTrigger() && Robot.m_oi.getRightJoystickTopMiddle()){
      Robot.m_cargoHandler.setShooterState(ShooterState.FRONT);
    }
    else if(Robot.m_oi.getRightJoystickTrigger() && Robot.m_oi.getRightJoystickTopLeft()){
      //Robot.m_cargoHandler.setShooterState(ShooterState.LEFT);
      Robot.m_cargoHandler.setShooterState(ShooterState.OFF);
    }
    else if(Robot.m_oi.getRightJoystickTrigger() && Robot.m_oi.getRightJoystickTopRight()){
      Robot.m_cargoHandler.setShooterState(ShooterState.OFF);
    }
    else{
      Robot.m_cargoHandler.setShooterState(ShooterState.OFF);
    }
    
    if(Robot.m_oi.getElevatorOverride()){
      Robot.m_cargoHandler.setShooterSpeedPercent(Robot.m_oi.getShooterDesiredSpeedPercentage());
    }
    else{
      Robot.m_cargoHandler.setShooterSpeed(Robot.m_oi.getShooterDesiredSpeed());
    }
    

    Robot.m_cargoHandler.update();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_cargoHandler.setIntakeState(IntakeState.OFF);
    Robot.m_cargoHandler.setShooterState(ShooterState.OFF);
    Robot.m_cargoHandler.update();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
