package team1065.robot.frc2017.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import team1065.robot.frc2017.RobotMap;
import team1065.robot.frc2017.commands.ManualClimberControl;

/**
 *
 */
public class Climber extends Subsystem {

    private Talon climberMotor;
    
    public Climber(){
    	climberMotor = new Talon(RobotMap.CLIMBER_MOTOR_PORT);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new ManualClimberControl());
    }
    
    public void goUp(){
    	climberMotor.set(1);
    }
    
    public void goUpSlow(){
    	climberMotor.set(1);
    }
    
    public void goDown(){
    	//climberMotor.set(-1);
    }
    
    public void stop(){
    	climberMotor.set(0);
    }
}

