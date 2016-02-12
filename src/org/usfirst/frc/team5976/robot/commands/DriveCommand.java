package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.CMHCommandBasedRobot;
import org.usfirst.frc.team5976.robot.XBoxController;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Command;

/*
 * @author Theo Hornung
 */

public class DriveCommand extends Command{
	private long timeMS, t0;
	private XBoxController driveController = CMHCommandBasedRobot.oi.getDriveController();
	private SpeedCalculator leftSpeedCalculator, rightSpeedCalculator;
	private final RobotDrive robotDrive = CMHCommandBasedRobot.driveBase.getRobotDrive();
	
	public DriveCommand(long timeMS, double leftSpeedTarget, double rightSpeedTarget){
		this.timeMS = timeMS;
		leftSpeedCalculator = new SpeedCalculator(true, true, leftSpeedTarget, driveController);
		rightSpeedCalculator = new SpeedCalculator(true, false, rightSpeedTarget, driveController);
		requires(CMHCommandBasedRobot.driveBase);
	}
	
	@Override
	protected void initialize() {
		t0 = System.currentTimeMillis();
	}

	@Override
	protected void execute() {
		robotDrive.tankDrive(leftSpeedCalculator.calcNext(), rightSpeedCalculator.calcNext());
		long tl = timeMS - (System.currentTimeMillis() - t0);
		System.out.println("Driving " + timeMS + " " + leftSpeedCalculator.getLastSpeed() + " " + leftSpeedCalculator.getAbsMaxSpeed() 
			+  " " + rightSpeedCalculator.getLastSpeed() + " " + rightSpeedCalculator.getAbsMaxSpeed()  +  " " + tl);
	}
	
	@Override
	protected boolean isFinished() {
		boolean done = System.currentTimeMillis() - t0 >= timeMS;
		if (done) System.out.println(getClass().getSimpleName() + " DONE");
		return done;
	}

	@Override
	protected void end() {
		//CMHCommandBasedRobot.driveBase.getRobotDrive().tankDrive(0.0, 0.0);
	}

	@Override
	protected void interrupted() {
		end();
	}

}