/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ManualArmControl;

public class Arm extends Subsystem {
    private Solenoid armSolenoid, grabberSolenoid;

  public Arm() {
    armSolenoid = new Solenoid(RobotMap.ARM_SOLENOID_PORT);
    grabberSolenoid = new Solenoid(RobotMap.GRABBER_SOLENOID_PORT);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ManualArmControl());
  }


  public void setArmActuators(boolean extend) {
    armSolenoid.set(extend);
  }
  
  public void setGrabberActuator(boolean extend){
      grabberSolenoid.set(extend);
  }
}
