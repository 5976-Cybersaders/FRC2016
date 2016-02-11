package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.CMHCommandBasedRobot;

import edu.wpi.first.wpilibj.command.Command;

/*
 * @author Theo Hornung
 */

public class DriveCommand extends Command{
	private long timeMS, t0;
	private double leftSpeed, rightSpeed;
	
	public DriveCommand(long timeMS, double leftSpeed, double rightSpeed){
		this.timeMS = timeMS;
		this.leftSpeed = leftSpeed;
		this.rightSpeed = rightSpeed;
		requires(CMHCommandBasedRobot.driveBase);
	}
	
	@Override
	protected void initialize() {
		t0 = System.currentTimeMillis();
	}

	@Override
	protected void execute() {
		CMHCommandBasedRobot.driveBase.getRobotDrive().tankDrive(leftSpeed, rightSpeed);
		long tl = timeMS - (System.currentTimeMillis() - t0);
		System.out.println("Driving " + timeMS + " " + leftSpeed +  " " + rightSpeed  +  " " + tl);
	}

	@Override
	protected boolean isFinished() {
		boolean done = System.currentTimeMillis() - t0 >= timeMS;
		if (done) System.out.println(getClass().getSimpleName() + " DONE");
		return done;
	}

	@Override
	protected void end() {
		CMHCommandBasedRobot.driveBase.getRobotDrive().tankDrive(0.0, 0.0);
		
	}

	@Override
	protected void interrupted() {
		end();
		
	}

}