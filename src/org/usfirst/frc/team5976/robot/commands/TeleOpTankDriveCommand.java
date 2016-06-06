package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.CMHCommandBasedRobot;
import org.usfirst.frc.team5976.robot.XBoxController;
import org.usfirst.frc.team5976.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Command;

public class TeleOpTankDriveCommand extends Command {

	private final RobotDrive robotDrive;
	private final SpeedCalculator leftSpeedCalculator; 
	private final SpeedCalculator rightSpeedCalculator;
	private PowerDistributionPanel pdp = new PowerDistributionPanel(); 
	
	public TeleOpTankDriveCommand(XBoxController driveController, RobotDrive robotDrive, DriveSubsystem driveBase){
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
		//System.out.println("Current Left: " + (pdp.getCurrent(2) + pdp.getCurrent(3)) + " Current Right: " + (pdp.getCurrent(12) + pdp.getCurrent(13)));
		System.out.println("Current L1: " + pdp.getCurrent(2));
		System.out.println("Current L2: " + pdp.getCurrent(3));
		System.out.println("Current R1: " + pdp.getCurrent(12));
		System.out.println("Current R2: " + pdp.getCurrent(13));
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
