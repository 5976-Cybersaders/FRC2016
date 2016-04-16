package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.VictorSP;

public class AutonomousIntakeCommand extends IntakeCommand{
	
	long t0;
	
	public AutonomousIntakeCommand(VictorSP intakeMotor, IntakeSubsystem intakeSubsystem) {
		super(intakeMotor, intakeSubsystem);
	}

	@Override
	protected void initialize(){
		t0 = System.currentTimeMillis();
	}
	
	@Override
	protected void execute() {
		intakeMotor.set(1);
	}

	@Override
	protected boolean isFinished(){
		return System.currentTimeMillis() - t0 >= 2500;
	}
}
