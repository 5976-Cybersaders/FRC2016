package org.usfirst.frc.team5976.robot.subsystems;

import org.usfirst.frc.team5976.robot.RobotMap;
import org.usfirst.frc.team5976.robot.XBoxController;
import org.usfirst.frc.team5976.robot.commands.TeleOpTankDriveCommand;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSubsystem extends Subsystem {

	private RobotDrive robotDrive = new RobotDrive(RobotMap.leftMotor, RobotMap.rightMotor);
	private XBoxController xBox;
	
	@Override
	protected void initDefaultCommand() {
		robotDrive.setExpiration(0.1);
		xBox = new XBoxController(0);
		
		setDefaultCommand(new TeleOpTankDriveCommand(xBox, robotDrive, this));
	}

	public RobotDrive getRobotDrive() {
		return robotDrive;
	}

	public void setRobotDrive(RobotDrive robotDrive) {
		this.robotDrive = robotDrive;
	}
}
