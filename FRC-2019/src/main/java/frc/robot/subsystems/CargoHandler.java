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
import frc.robot.commands.ManualCargoHandlerControl;

public class CargoHandler extends Subsystem {
  public enum IntakeState{OFF, IN, OUT};
  public enum ShooterState{OFF, FRONT, RIGHT, LEFT};

  private VictorSPX intake, indexerMid;
  private TalonSRX shooter;
  private DigitalInput cargoDetection;
  private IntakeState intakeState;
  private ShooterState shooterState;
  private double shootingSpeed;

  public CargoHandler(){
    intake = new VictorSPX(RobotMap.INTAKE_VICTOR_PORT);
    indexerMid = new VictorSPX(RobotMap.INDEXER_MID_VICTOR_PORT);
    shooter = new TalonSRX(RobotMap.SHOOTER_TALON_PORT);

    shooter.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		shooter.setSensorPhase(true);
		shooter.setInverted(false);
		shooter.configNominalOutputForward(0.0, 0);
		shooter.configNominalOutputReverse(-0.0, 0);
		shooter.configPeakOutputForward(1, 0);
		shooter.configPeakOutputReverse(0, 0);//no reverse so we can use bang bang if need be
		//shooter.configAllowableClosedloopError(0, RobotMap.SHOOTER_ERROR, 0);
		shooter.config_kF(0, RobotMap.SHOOTER_F, 0);
		shooter.config_kP(0, RobotMap.SHOOTER_P, 0);
		shooter.config_kI(0, RobotMap.SHOOTER_I, 0);
    shooter.config_kD(0, RobotMap.SHOOTER_D, 0);

    intake.setInverted(true);
    indexerMid.setInverted(true);
    //indexerTop.setInverted(false);
    shooter.setInverted(false);

    cargoDetection = new DigitalInput(RobotMap.CARGO_SWITCH_PORT);
    
    intakeState = IntakeState.OFF;
    shooterState = ShooterState.OFF;
    shootingSpeed = 0;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ManualCargoHandlerControl());
  }

  public void update(){
    //INTAKE AND MIDDLE INDEXER
    if(intakeState == IntakeState.OFF){
      if(shooterState != ShooterState.OFF){
        setIntake(0.6); 
        setIndexerMid(0.6);
      }
      else{
        setIntake(0);
        setIndexerMid(0);
      }
    }
    else if(intakeState == IntakeState.IN){
      if(shooterState != ShooterState.OFF){
        setIntake(0.6);
        setIndexerMid(0.5);
      }
      else if(isCargoDetected()){
        setIntake(-0.8);
        setIndexerMid(0);
      }
      else{
        setIntake(0.6);
        setIndexerMid(0.6);
      }
    }
    else if(intakeState == IntakeState.OUT){
      if(shooterState != ShooterState.OFF){
        setIntake(1);
        setIndexerMid(1);
      }
      else{
        setIntake(-1);
        setIndexerMid(-1);
      }
    }

    //TOP INDEXER AND SHOOTER
    if(shooterState == ShooterState.OFF){
      //setShooter(0);
    }
    else if(shooterState == ShooterState.FRONT){
      //setShooter(0);
      setIntake(1);
      setIndexerMid(-1);
    }
    else if(shooterState == ShooterState.LEFT){
      //setShooter(-shootingSpeed);
    }
    else if(shooterState == ShooterState.RIGHT){
      setIntake(1);
      setIndexerMid(1);
      //setShooter(shootingSpeed);
    }
  }

  public boolean isCargoDetected(){
   return !cargoDetection.get();
  }

  public boolean isShooterUpToSpeed(){
    return shooter.getClosedLoopError(0) < RobotMap.SHOOTER_ERROR;
  }

  public void setIntake(double speed){
    intake.set(ControlMode.PercentOutput, speed);
  }

  public void setIndexerMid(double speed){
    indexerMid.set(ControlMode.PercentOutput, speed);
  }

  public void setIntakeState (IntakeState state){
    intakeState = state;
  }

  public void setShooterState (ShooterState state){
    shooterState = state;
  }

  public void setShooterSpeedPercent (double speed){
    shootingSpeed = speed;
    shooter.set(ControlMode.PercentOutput, speed);
  }

  public void setShooterSpeed(double speed){
    shootingSpeed = speed;
    shooter.set(ControlMode.Velocity, speed);
  }

  public void updateStatus(){
    SmartDashboard.putBoolean("[CH] Cargo Detected", isCargoDetected());
    SmartDashboard.putString("[DT] Intake State", intakeState.toString());
    SmartDashboard.putString("[DT] Shooting State", shooterState.toString());
    SmartDashboard.putNumber("[DT] Target Shooter Speed", shootingSpeed);
    SmartDashboard.putNumber("[DT] Shooter Speed", shooter.getSelectedSensorVelocity());
    SmartDashboard.putNumber("[DT] Shooter Error", shooter.getClosedLoopError());
    SmartDashboard.putNumber("[DT] Shooter Motor Percent", shooter.getMotorOutputPercent());
  }
}