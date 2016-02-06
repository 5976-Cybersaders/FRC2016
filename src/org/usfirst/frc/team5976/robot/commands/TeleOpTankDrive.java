package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.CMHCommandBasedRobot;

import edu.wpi.first.wpilibj.command.Command;

public class TeleOpTankDrive extends Command {
	
	static double expoFactor = 0.2;
	
	public TeleOpTankDrive(){
		requires(CMHCommandBasedRobot.driveBase);
	}

	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		double xBoxLeft = CMHCommandBasedRobot.oi.getDriveController().getLeftJoyY();
		double xBoxRight = CMHCommandBasedRobot.oi.getDriveController().getRightJoyY();
		CMHCommandBasedRobot.driveBase.getRobotDrive().tankDrive(adjustSpeed(xBoxLeft), adjustSpeed(xBoxRight));
	}

	@Override
	protected boolean isFinished() {
		// Command is always on because we want to continuously respond to driving inputs
		return false;
	}

	@Override
	protected void end() {
		CMHCommandBasedRobot.driveBase.getRobotDrive().tankDrive(0.0, 0.0);
	}

	@Override
	protected void interrupted() {
		end();
	}
	
	
    public static double adjustSpeed(double d){
    	double speed = Math.signum(d) * Math.pow(Math.abs(d), Math.pow(4, expoFactor));
    	return speed;
    }
}
