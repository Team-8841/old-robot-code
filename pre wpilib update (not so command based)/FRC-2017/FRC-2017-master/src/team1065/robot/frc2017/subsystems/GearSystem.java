package team1065.robot.frc2017.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team1065.robot.frc2017.RobotMap;
import team1065.robot.frc2017.commands.ManualGearControl;

/**
 *
 */
public class GearSystem extends Subsystem {

    Solenoid intake, pusher, backLED, rightLED, leftLED;
    DigitalInput ir;
    
    public GearSystem(){
    	intake = new Solenoid(RobotMap.GEAR_INTAKE_SOLENOID_PORT);
    	pusher = new Solenoid(RobotMap.GEAR_PUSHER_SOLENOID_PORT);
    	ir = new DigitalInput(RobotMap.GEAR_IR_SENSOR_PORT);
    	backLED = new Solenoid(RobotMap.BACK_LED_PCM_PORT);
    	leftLED = new Solenoid(RobotMap.LEFT_LED_PCM_PORT);
    	rightLED = new Solenoid(RobotMap.RIGHT_LED_PCM_PORT);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new ManualGearControl());
    }
    
    public void openGearIntake(){
    	intake.set(true);
    }
    
    public void closeGearIntake(){
    	intake.set(false);
    }
    
    public void extendGearPusher(){
    	pusher.set(true);
    }
    
    public void retractGearPusher(){
    	pusher.set(false);
    }
    
    public boolean isGearDetected(){
    	return ir.get();
    }
    
    public void setLED(boolean val){
    	backLED.set(val);
    	leftLED.set(val);
    	rightLED.set(val);
    }
    
    public void updateStatus(){
    	SmartDashboard.putBoolean("Gear Detected", isGearDetected());
    }
}

