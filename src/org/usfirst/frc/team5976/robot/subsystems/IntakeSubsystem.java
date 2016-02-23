package org.usfirst.frc.team5976.robot.subsystems;

import org.usfirst.frc.team5976.robot.RobotMap;
import org.usfirst.frc.team5976.robot.XBoxController;
import org.usfirst.frc.team5976.robot.commands.IntakeCommand;
import org.usfirst.frc.team5976.robot.commands.StickBasedIntakeCommand;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeSubsystem extends Subsystem {

	private VictorSP intakeMotor = new VictorSP(RobotMap.intakeMotor);
	private XBoxController xBox;
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new StickBasedIntakeCommand(xBox, intakeMotor, this));
	}

	public VictorSP getIntakeMotor() {
		return intakeMotor;
	}
}
