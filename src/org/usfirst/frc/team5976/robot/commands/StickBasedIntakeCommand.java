package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.XBoxController;
import org.usfirst.frc.team5976.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.VictorSP;

public class StickBasedIntakeCommand extends IntakeCommand{
	
	private XBoxController xBox;
	
	public StickBasedIntakeCommand(XBoxController xBox, VictorSP intakeMotor, IntakeSubsystem intakeSubsystem) {
		super(intakeMotor, intakeSubsystem);
		this.xBox = xBox;
	}

	@Override
	protected void execute() {
		double rawSpeed = xBox.getLeftJoyY();
		double speedToSet = 0.0;
		if(rawSpeed > 0.2) speedToSet = motorSpeed;
		else if(rawSpeed < -0.2) speedToSet = -motorSpeed;
		intakeMotor.set(speedToSet);
	}
}
