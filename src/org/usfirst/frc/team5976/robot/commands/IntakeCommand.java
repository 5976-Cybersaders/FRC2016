package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.XBoxController;
import org.usfirst.frc.team5976.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Command;

public abstract class IntakeCommand extends Command{
	
	protected VictorSP intakeMotor;
	protected double motorSpeed = 1.0;
	
	public IntakeCommand(VictorSP intakeMotor, IntakeSubsystem intakeSubsystem){
		this.intakeMotor = intakeMotor;
		requires(intakeSubsystem);
	}
	
	@Override
	protected void initialize() {
		System.out.println("Initialized Intake Command");
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		intakeMotor.set(0.0);
	}

	@Override
	protected void interrupted() {
		end();
	}

}
