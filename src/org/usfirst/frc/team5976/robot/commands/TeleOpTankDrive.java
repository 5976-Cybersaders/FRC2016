package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.CMHCommandBasedRobot;
import org.usfirst.frc.team5976.robot.XBoxController;
import org.usfirst.frc.team5976.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Command;

public class TeleOpTankDrive extends Command {

	private final RobotDrive robotDrive;
	private final SpeedCalculator leftSpeedCalculator; 
	private final SpeedCalculator rightSpeedCalculator;
	
	public TeleOpTankDrive(XBoxController driveController, RobotDrive robotDrive, DriveSubsystem driveBase){
		this.robotDrive = robotDrive;
		leftSpeedCalculator = new TeleOpSpeedCalculator(true, driveController);
		rightSpeedCalculator = new TeleOpSpeedCalculator(false, driveController);
		requires(driveBase);
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
		robotDrive.tankDrive(0.0, 0.0);
	}

	@Override
	protected void interrupted() {
		end();
	}
}
