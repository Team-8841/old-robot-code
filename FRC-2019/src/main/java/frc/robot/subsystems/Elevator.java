/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.ManualElevatorControl;

public class Elevator extends Subsystem {
  private TalonSRX masterTalon, slaveTalon;
  //private VictorSPX slaveTalon;//TODO: comment out on comp robot and use talonsrx
  private DigitalInput bottomLimit, topLimit;

  public Elevator (){
    bottomLimit = new DigitalInput(RobotMap.ELEVATOR_BOTTOM_LIMIT_PORT);
    topLimit = new DigitalInput(RobotMap.ELEVATOR_TOP_LIMIT_PORT);

    masterTalon = new TalonSRX(RobotMap.ELEVATOR_MASTER_TALON_PORT);
    slaveTalon = new TalonSRX(RobotMap.ELEVATOR_SLAVE_TALON_PORT);
    //slaveTalon = new VictorSPX(4);//TODO: comment out on comp robot and use talonsrx

    slaveTalon.follow(masterTalon);
		slaveTalon.setInverted(false);
		slaveTalon.configNominalOutputForward(0.0, 0);
		slaveTalon.configNominalOutputReverse(-0.0, 0);
		slaveTalon.configPeakOutputForward(1, 0);
		slaveTalon.configPeakOutputReverse(-1, 0);

    masterTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		masterTalon.setSensorPhase(true);
		masterTalon.setInverted(false);
		masterTalon.configNominalOutputForward(0.0, 0);
		masterTalon.configNominalOutputReverse(-0.0, 0);
		masterTalon.configPeakOutputForward(1, 0);
		masterTalon.configPeakOutputReverse(-1, 0);
		masterTalon.configAllowableClosedloopError(0, RobotMap.ELEVATOR_ERROR, 0);
		masterTalon.config_kF(0, RobotMap.ELEVATOR_F, 0);
		masterTalon.config_kP(0, RobotMap.ELEVATOR_P, 0);
		masterTalon.config_kI(0, RobotMap.ELEVATOR_I, 0);
		masterTalon.config_kD(0, RobotMap.ELEVATOR_D, 0);
		
		//There is an option to get the absolute value from the CTR sensor but we will just zero it out for now
		masterTalon.setSelectedSensorPosition(0, 0, 0);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ManualElevatorControl());
  }

  public void setPosition(double position) {
    //assume normally open switches
    if(position < masterTalon.getSelectedSensorPosition(0) && !bottomLimit.get()) {
      masterTalon.setSelectedSensorPosition(0, 0, 0);
      masterTalon.set(ControlMode.PercentOutput, 0);
    }
    else if (position > masterTalon.getSelectedSensorPosition(0) && !topLimit.get()) {
      masterTalon.set(ControlMode.PercentOutput, 0);
    }
    else{
      masterTalon.set(ControlMode.Position,position);
    }
  }

  public void setVoltage(double speed) {
    //assume normally open switches
    if((speed < 0 && !bottomLimit.get())){
      masterTalon.set(ControlMode.PercentOutput, 0);
      masterTalon.setSelectedSensorPosition(0, 0, 0);
    }
    else if(speed > 0 && !topLimit.get()){
      masterTalon.set(ControlMode.PercentOutput, 0);
    }
    else{
      masterTalon.set(ControlMode.PercentOutput, speed);
    }
  }
  
  public boolean isOnTarget(){
    return masterTalon.getClosedLoopError(0) < RobotMap.ELEVATOR_ERROR;
  }

  public boolean getBottomLimit(){
    return bottomLimit.get();
  }
  
  public void updateStatus(){
    SmartDashboard.putNumber("Elevator Talon Motor Speed", masterTalon.getMotorOutputPercent());
    SmartDashboard.putNumber("Elevator Setpoint", masterTalon.getClosedLoopTarget(0));
    SmartDashboard.putNumber("Elevator Error", masterTalon.getClosedLoopError(0));
    SmartDashboard.putNumber("Elevator Position", masterTalon.getSelectedSensorPosition(0));
    SmartDashboard.putBoolean("Elevator on Target", isOnTarget());
    SmartDashboard.putBoolean("Elevator Bottom Limit", !bottomLimit.get());
    SmartDashboard.putBoolean("Elevator Top Limit", !topLimit.get());
  }

}
