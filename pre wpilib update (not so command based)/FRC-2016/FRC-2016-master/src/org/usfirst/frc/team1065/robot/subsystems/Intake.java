package org.usfirst.frc.team1065.robot.subsystems;

import org.usfirst.frc.team1065.robot.RobotMap;
import org.usfirst.frc.team1065.robot.commands.ManualBoulderControl;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class Intake extends Subsystem {
    private Talon intakeMotor, queuingMotor;
    private DigitalInput queuingIR;
    
	public Intake(){
		intakeMotor = new Talon(RobotMap.INTAKE_MOTOR_PORT);
		queuingMotor = new Talon(RobotMap.QUEUING_MOTOR_PORT);
		intakeMotor.setInverted(true);
		queuingMotor.setInverted(true);
		
		queuingIR = new DigitalInput(RobotMap.QUEUING_IR_PORT);
		
		LiveWindow.addActuator("Intake","Intake Motor", intakeMotor);
		LiveWindow.addActuator("Intake", "Queuing Motor", queuingMotor);
		LiveWindow.addSensor("Intake", "queuingIR", queuingIR);
	}

    public void initDefaultCommand() {
        setDefaultCommand(new ManualBoulderControl());
    }
    
    public void setQueuingDown(double speed){
    	queuingMotor.set(Math.abs(speed));
    }
    
    public void setQueuingUp(double speed){
    	queuingMotor.set(-Math.abs(speed));
    }
    
    public void setQueuing(double speed){
    	queuingMotor.set(speed);
    }
    
    public void setIntakeIn(double speed){
    	intakeMotor.set(Math.abs(speed));
    }
    
    public void setIntakeOut(double speed){
    	intakeMotor.set(- Math.abs(speed));
    }
    
    public void setIntake(double speed){
    	intakeMotor.set(speed);
    }
    
    public boolean getQueuingIR(){
    	return !queuingIR.get();
    }
}

