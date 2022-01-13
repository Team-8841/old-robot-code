package team1065.robot.frc2017.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import team1065.robot.frc2017.RobotMap;
import team1065.robot.frc2017.commands.ManualIntakeControl;

/**
 *
 */
public class Intake extends Subsystem {

    private Talon intakeMotor;
    
    public Intake(){
    	intakeMotor = new Talon(RobotMap.INTAKE_MOTOR_PORT);
    	intakeMotor.setInverted(false);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new ManualIntakeControl());
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
}

