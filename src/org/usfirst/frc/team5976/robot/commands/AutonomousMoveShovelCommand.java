package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.subsystems.ShovelSubsystem;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class AutonomousMoveShovelCommand extends Command{

	DoubleSolenoid doubleSolenoid;
	DoubleSolenoid.Value direction;
	boolean isFinished;
	int count;
	
	public AutonomousMoveShovelCommand(ShovelSubsystem shovel, DoubleSolenoid.Value direction){
		doubleSolenoid = shovel.getDoubleSolenoid();
		this.direction = direction;
		requires(shovel);
	}
	
	@Override
	protected void initialize() {
		isFinished = false;
		count = 0;
	}

	@Override
	protected void execute() {
		System.out.println("MOVING " + direction);
		doubleSolenoid.set(direction);	
		if(count > 10){
			isFinished = true;
		}
		count++;
	}

	@Override
	protected boolean isFinished() {
		return isFinished;
	}

	@Override
	protected void end() {
		doubleSolenoid.set(DoubleSolenoid.Value.kOff);
	}

	@Override
	protected void interrupted() {
		System.out.println("INTERRUPTED " + direction);
		end();
	}
}