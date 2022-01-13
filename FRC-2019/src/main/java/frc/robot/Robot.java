/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.commands.autonomous.RightRocket;
import frc.robot.commands.autonomous.RightCargo;
import frc.robot.commands.autonomous.LeftCargo;
import frc.robot.commands.autonomous.LeftRocket;
import frc.robot.subsystems.CargoHandler;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Lights;
import frc.robot.subsystems.VisionSystem;
import frc.robot.subsystems.Elevator;
import edu.wpi.first.wpilibj.Compressor;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static OI m_oi;
  public static DriveTrain m_driveTrain;
  public static CargoHandler m_cargoHandler;
  public static Elevator m_elevator;
  public static Climber m_climber;
  public static Arm m_arm;
  public static Lights m_lighting;
  public static VisionSystem m_vision;
  public static DigitalInput m_autoModeSwitch;
  public static DigitalInput m_autoSideSwitch;
  public static Compressor m_compressor;
  
  Command m_autonomousCommand;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_oi = new OI();
    m_driveTrain = new DriveTrain();
    m_cargoHandler = new CargoHandler();
    m_elevator = new Elevator();
    //m_climber = new Climber();
    m_arm = new Arm();
    m_compressor = new Compressor();
    m_lighting = new Lights();
    m_vision = new VisionSystem();

    m_autoModeSwitch = new DigitalInput(RobotMap.AUTO_MODE_SWITCH_PORT);
    m_autoSideSwitch = new DigitalInput(RobotMap.AUTO_SIDE_SWITCH_PORT);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    //m_driveTrain.updateStatus();
    //m_cargoHandler.updateStatus();
    //m_elevator.updateStatus();
    //m_climber.updateStatus();
    //m_vision.updateStatus();
    //m_lighting.updateStatus();
    //SmartDashboard.putBoolean("[AM] Mode", m_autoModeSwitch.get());
    //SmartDashboard.putBoolean("[AM] side", m_autoSideSwitch.get());
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_driveTrain.resetAngle();
    m_driveTrain.resetEncoder();

    //int autoPositionSelector = m_oi.getAutoKnobPosition();

    /*Command[] CommandsArray = {
      new LeftRocket(),
      new LeftCargo(),
      new RightCargo(),
      new RightRocket(),
    };
      m_autonomousCommand = CommandsArray[autoPositionSelector];
      m_autonomousCommand.start();*/

      if (m_autoModeSwitch.get() & m_autoSideSwitch.get()){ // Right Rocket
        m_autonomousCommand = new RightRocket();
      }
      else if (m_autoModeSwitch.get() & !m_autoSideSwitch.get()){ // Left Rockwt
        m_autonomousCommand = new LeftRocket();
      }
      else if (!m_autoModeSwitch.get() & m_autoSideSwitch.get()){ // Right Cargo
        m_autonomousCommand = new RightCargo();
      }
      else if (!m_autoModeSwitch.get() & !m_autoSideSwitch.get()){ // Left Cargo
        m_autonomousCommand = new LeftCargo();
      }

      m_autonomousCommand = new RightCargo();
      
      m_autonomousCommand.start();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    //if the driver presses both triggers he gains control of the robot during autonomous
    if(m_oi.getRightJoystickTrigger() && m_oi.getLeftJoystickTrigger()){
      m_driveTrain.resetAngle();
      m_driveTrain.resetEncoder();
      if (m_autonomousCommand != null) {
        m_autonomousCommand.cancel();
      }
    }

    if(m_oi.getCompressorOverride()){
			m_compressor.setClosedLoopControl(false);
    }else{
      m_compressor.setClosedLoopControl(true);
    }
  }

  @Override
  public void teleopInit() {
    m_driveTrain.resetAngle();
    m_driveTrain.resetEncoder();
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    if(m_oi.getCompressorOverride()){
			m_compressor.setClosedLoopControl(false);
    }else{
      m_compressor.setClosedLoopControl(true);
    }
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
