package team1065.robot.frc2017.subsystems;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team1065.robot.frc2017.RobotMap;
import team1065.robot.frc2017.commands.DriveWithJoysticks;

/**
 *
 */
public class DriveTrain extends Subsystem {
	public enum State{TANK_DRIVE, STRAIGHT_DRIVE, MECANUM_DRIVE, STRAIGHT_MECANUM};
	
	private VictorSP leftFrontMotor, leftBackMotor, rightFrontMotor, rightBackMotor;
	private AHRS navX;
	private RobotDrive robotDrive;
	private State state;
	private Encoder encoder;
	private Solenoid dropDown;
	
	double setAngle;
	
	public DriveTrain(){
		leftFrontMotor = new VictorSP(RobotMap.LEFT_FRONT_DRIVE_MOTOR_PORT);
		leftBackMotor = new VictorSP(RobotMap.LEFT_BACK_DRIVE_MOTOR_PORT);
		rightFrontMotor = new VictorSP(RobotMap.RIGHT_FRONT_DRIVE_MOTOR_PORT);
		rightBackMotor = new VictorSP(RobotMap.RIGHT_BACK_DRIVE_MOTOR_PORT);
		rightFrontMotor.setInverted(true);
		rightBackMotor.setInverted(true);
    	
    	robotDrive = new RobotDrive(leftFrontMotor, leftBackMotor, rightFrontMotor, rightBackMotor);
    	robotDrive.setSafetyEnabled(false);
    	state = State.TANK_DRIVE;
		robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, false);
    	robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, false);
    	
    	encoder = new Encoder(RobotMap.DRIVE_ENCODER_PORT_A,RobotMap.DRIVE_ENCODER_PORT_B,true);
    	encoder.setDistancePerPulse(((RobotMap.DRIVE_WHEEL_DIAMETER * Math.PI)/(RobotMap.DRIVE_ENCODERS_COUNTS_PER_REV))/10.8);//10.8 for gearing
    	
    	dropDown = new Solenoid(RobotMap.DROPDOWN_SOLENOID_PORT);
    	
    	setAngle = 0;
    	
    	try {
            navX = new AHRS(SPI.Port.kMXP);
        } catch (RuntimeException ex ) {
            DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
        }
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new DriveWithJoysticks());
    }
    
    public void tankDrive(double leftValue, double rightValue){
    	if(state != State.TANK_DRIVE){
    		state = State.TANK_DRIVE;
    		robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, false);
        	robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, false);
    	}
    	robotDrive.tankDrive(leftValue, rightValue, false);
    }
    
    public void mecanumDrive(double x, double y, double rotation){
    	if(state != State.MECANUM_DRIVE){
    		state = State.MECANUM_DRIVE;
    		robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        	robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
    	}
    	robotDrive.mecanumDrive_Cartesian(x, -y, rotation, 0);
    }
    
    public void straightDrive(double velocity){
    	if(state != State.STRAIGHT_DRIVE){
    		state = State.STRAIGHT_DRIVE;
    		setAngle = getAngle();
    		robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        	robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
    	}
    	double curAngle = getAngle();
    	double error = curAngle - setAngle;
    	if(Math.abs(error) > 180){
    		error = error>0 ? error-360 : error+360;
    	}
    	robotDrive.mecanumDrive_Cartesian(0, -velocity, -(error * RobotMap.MECANUM_STRAIGHT_DRIVE_P), 0);
    }
    
    public void mecanumStraightDrive(double x, double y){
    	if(state != State.STRAIGHT_MECANUM){
    		state = State.STRAIGHT_MECANUM;
    		setAngle = getAngle();
    		robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        	robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
    	}
    	double curAngle = getAngle();
    	double error = curAngle - setAngle;
    	if(Math.abs(error) > 180){
    		error = error>0 ? error-360 : error+360;
    	}
    	robotDrive.mecanumDrive_Cartesian(x, -y, -(error * RobotMap.MECANUM_STRAIGHT_DRIVE_P), 0);
    }
    
    public double getAngle(){
    	try{
    		return navX.getAngle();
    	} catch (RuntimeException ex ) {
            return 0;
        }
    }
    
    public void resetAngle(){
    	try{
    		navX.zeroYaw();
    	} catch (RuntimeException ex ) {
        }
    }
    
    public void resetEncoder() {
    	encoder.reset();
	}

	public double getEncoderDistance() {
		return encoder.getDistance();
	}
	
	public void enableDropDownTraction(){
		dropDown.set(true);
	}
    
	public void disableDropDownTraction(){
		dropDown.set(false);
	}
	
    public void updateStatus(){
    	SmartDashboard.putNumber("[DT] Angle", getAngle());
    	SmartDashboard.putNumber("[DT] Distance", getEncoderDistance());
    }
    
}

