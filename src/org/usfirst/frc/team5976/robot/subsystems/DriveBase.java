package org.usfirst.frc.team5976.robot.subsystems;

import org.usfirst.frc.team5976.robot.RobotMap;
import org.usfirst.frc.team5976.robot.XBoxController;
import org.usfirst.frc.team5976.robot.commands.TeleOpTankDrive;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveBase extends Subsystem {

	private RobotDrive robotDrive;
	private XBoxController xBox;
	
	@Override
	protected void initDefaultCommand() {
		setRobotDrive(new RobotDrive(RobotMap.leftMotor, RobotMap.rightMotor));
		getRobotDrive().setExpiration(0.1);
		setXBox(new XBoxController(0));
		
		setDefaultCommand(new TeleOpTankDrive());
	}

	public RobotDrive getRobotDrive() {
		return robotDrive;
	}

	public void setRobotDrive(RobotDrive robotDrive) {
		this.robotDrive = robotDrive;
	}

	public XBoxController getXBox() {
		return xBox;
	}

	public void setXBox(XBoxController xBox) {
		this.xBox = xBox;
	}
}
