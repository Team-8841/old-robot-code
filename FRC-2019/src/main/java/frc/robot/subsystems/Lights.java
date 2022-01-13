/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.LEDControl;

public class Lights extends Subsystem {

    private Solenoid m_CargoLED, m_LED2, m_LED3, m_LED4;
    private DigitalInput lineSensor;

    public Lights() {
        m_CargoLED = new Solenoid(1, RobotMap.CARGO_LED_PORT);
        m_LED2 = new Solenoid(1, RobotMap.LED2_PORT);
        m_LED3 = new Solenoid(1, RobotMap.LED3_PORT);
        m_LED4 = new Solenoid(1, RobotMap.LED4_PORT);

        lineSensor = new DigitalInput(RobotMap.FRONT_LINE_SENSOR_PORT);
    }

    @Override
    public void initDefaultCommand() {
    setDefaultCommand(new LEDControl());
    }

    public void setCargoLED(boolean on){
        m_CargoLED.set(on);
    }

    public void setLED2(boolean on){
        m_LED2.set(on);
    }

    public void setLED3(boolean on){
        m_LED3.set(on);
    }

    public void setLED4(boolean on){
        m_LED4.set(on);
    }

    public boolean isLineDetected(){
        return lineSensor.get();
    }

    public void updateStatus(){
        SmartDashboard.putBoolean("[Lights] Line Detected", isLineDetected());
      }
}
