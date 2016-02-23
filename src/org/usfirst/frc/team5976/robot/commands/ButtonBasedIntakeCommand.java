package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.XBoxController;
import org.usfirst.frc.team5976.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.VictorSP;

public class ButtonBasedIntakeCommand extends IntakeCommand{

	private Double speed;

	public ButtonBasedIntakeCommand(boolean intakeIn, VictorSP intakeMotor, IntakeSubsystem intakeSubsystem) {
		super(intakeMotor, intakeSubsystem);
		speed = intakeIn ? motorSpeed:-motorSpeed;
	}

	@Override
	protected void execute() {
		intakeMotor.set(speed);
	}

}
