/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ManualClimberControl;

public class Climber extends Subsystem {
  private Solenoid frontActuator, backActuator;
  private VictorSPX backMotor;
  //private VictorSP backMotor;

  public Climber() {
    frontActuator = new Solenoid(RobotMap.FRONT_CLIMBER_SOLENOID_PORT);
    backActuator = new Solenoid(1, RobotMap.BACK_CLIMBER_SOLENOID_PORT);

    backMotor = new VictorSPX(RobotMap.CLIMBER_VICTOR_PORT);
  }
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ManualClimberControl());
  }

  public void setFrontActuator(boolean extend) {
    frontActuator.set(extend);
  }

  public void setBackActuator(boolean extend) {
    backActuator.set(extend);
  }

  public boolean isBackActuatorExtended(){
    return backActuator.get();
  }

  public void setMotor(double speed){
    backMotor.set(ControlMode.PercentOutput, -speed);
  }
  public void updateStatus(){
  }
}

