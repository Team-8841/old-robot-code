package team1065.robot.frc2017.subsystems;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team1065.robot.frc2017.RobotMap;
import team1065.robot.frc2017.commands.ManualShooterControl;

/**
 *
 */
public class Shooter extends Subsystem {

    CANTalon masterTalon, slaveTalon;
    Talon agitator;
    Solenoid indexer;
    
    public Shooter(){
    	masterTalon = new CANTalon(RobotMap.MASTER_TALON_PORT);
    	slaveTalon = new CANTalon(RobotMap.SLAVE_TALON_PORT);
    	
    	slaveTalon.changeControlMode(CANTalon.TalonControlMode.Follower);
    	slaveTalon.set(RobotMap.MASTER_TALON_PORT);
    	slaveTalon.enableBrakeMode(false);
    	slaveTalon.clearStickyFaults();
    	slaveTalon.reverseOutput(false);//TODO: verify
    	slaveTalon.setVoltageRampRate(36.0);
    	slaveTalon.configNominalOutputVoltage(+0.0f, -0.0f);
    	slaveTalon.configPeakOutputVoltage(+12.0f, 0.0f);
    	
    	/*masterTalon.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
    	if (masterTalon.isSensorPresent(
                CANTalon.FeedbackDevice.CtreMagEncoder_Relative) != CANTalon.FeedbackDeviceStatus.FeedbackStatusPresent) {
            DriverStation.reportError("Could not detect shooter encoder!", false);
        }*/
    	masterTalon.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    	masterTalon.configEncoderCodesPerRev(1024);
    	masterTalon.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    	masterTalon.reverseSensor(true);
    	masterTalon.reverseOutput(false);
    	masterTalon.setVoltageRampRate(36.0);
    	masterTalon.enableBrakeMode(false);
    	masterTalon.clearStickyFaults();
    	masterTalon.setProfile(0);
    	masterTalon.setF(RobotMap.SHOOTER_F);
    	masterTalon.setP(RobotMap.SHOOTER_P);
    	masterTalon.setI(RobotMap.SHOOTER_I); 
        masterTalon.setD(RobotMap.SHOOTER_D);
        masterTalon.configNominalOutputVoltage(+0.0f, -0.0f);
    	masterTalon.configPeakOutputVoltage(+12.0f, 0.0f);
        
        agitator = new Talon(RobotMap.AGITATOR_MOTOR_PORT);
        indexer = new Solenoid(RobotMap.INDEXER_SOLENOID_PORT);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new ManualShooterControl());
    }
    
    public void setRpm(double rpm) {
    	masterTalon.changeControlMode(CANTalon.TalonControlMode.Speed);
        masterTalon.set(rpm);
    }

    public void setOpenLoop(double speed) {
    	masterTalon.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    	masterTalon.set(speed);
    }
    
    public void setAgitator(double speed){
    	agitator.set(speed);
    }
    
    public void setIndexerOpen(){
    	indexer.set(true);
    }
    
    public void setIndexerClose(){
    	indexer.set(false);
    }
    
    public boolean isIndexerOpen(){
    	return indexer.get();
    }
    
    public boolean isOnTarget(){
    	double setpoint = masterTalon.getSetpoint();
    	double speed = masterTalon.getSpeed();
    	//make sure we are trying to shoot
    	if (setpoint > .1){
    		//if speed is within +-5% or we are on voltage mode
    		if(masterTalon.getControlMode() == CANTalon.TalonControlMode.PercentVbus|| 
    				speed > (setpoint*.95) && speed < (setpoint*1.05)){
    			return true;
    		}
    		else{
    			return false;
    		}
    	}
    	else
    	{
    		return false;
    	}
    }
    
    public void updateStatus(){
    	SmartDashboard.putNumber("Shooter Talon Speed", masterTalon.getSpeed());
    	SmartDashboard.putNumber("Shooter Setpoint", masterTalon.getSetpoint());
    	SmartDashboard.putBoolean("Shooter on Target", isOnTarget());
    }
}

