package team1065.robot.frc2017.commands.SelfTest;

import edu.wpi.first.wpilibj.command.Command;
import team1065.robot.frc2017.RobotMap;
import team1065.robot.lib.MooseMotor;

/**
 *
 */
public class TestMotor extends Command {
	enum State{TESTING_FOWARD,TESTING_REVERSE,TESTING_STOP,TESTING_DONE};
    MooseMotor motor;
    boolean pass;
    State state;
    public TestMotor(MooseMotor motor) {
    	this.motor = motor;
    	this.setTimeout(3);
    	pass = false;
    }
    public TestMotor(MooseMotor motor,double time) {
    	this.motor = motor;
    	this.setTimeout(time);
    	pass = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	state = State.TESTING_STOP;
    	motor.setMotor(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//TODO
    	switch(state){
    		case TESTING_STOP:
    			motor.setMotor(0);
    			if(motor.getCurrent()<RobotMap.MOTOR_TEST_STOP_CURRENT+.5){
    				
    			}
    			break;
    		case TESTING_FOWARD:
    			//4
    			break;
    		case TESTING_REVERSE:
    			//4
    			break;
    		case TESTING_DONE:
    			pass = true;
    			//print motor passs test
    			break;
    		default:
    			//state not recognized
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return this.isTimedOut() || pass;
    }

    // Called once after isFinished returns true
    protected void end() {
    	if(pass){
    		//TODO
    	}
    	else{
    		//TODO
    		//motor x failed selftest during STATE
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
