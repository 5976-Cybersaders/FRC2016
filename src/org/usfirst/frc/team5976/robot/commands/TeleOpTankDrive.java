package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.CMHCommandBasedRobot;
import org.usfirst.frc.team5976.robot.XBoxController;
import org.usfirst.frc.team5976.robot.subsystems.DriveBase;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Command;

public class TeleOpTankDrive extends Command {

	private final XBoxController driveController = CMHCommandBasedRobot.oi.getDriveController();
	private final RobotDrive robotDrive = CMHCommandBasedRobot.driveBase.getRobotDrive();
	private final SpeedCalculator leftSpeedCalculator = new SpeedCalculator(false, true, 1, driveController);
	private final SpeedCalculator rightSpeedCalculator = new SpeedCalculator(false, false, 1, driveController);
	
	public TeleOpTankDrive(){
		requires(CMHCommandBasedRobot.driveBase);
	}

	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		robotDrive.tankDrive(leftSpeedCalculator.calcNext(), rightSpeedCalculator.calcNext());
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
}
