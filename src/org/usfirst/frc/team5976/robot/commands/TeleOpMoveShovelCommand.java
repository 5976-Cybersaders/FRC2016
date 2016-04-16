package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.subsystems.ShovelSubsystem;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class TeleOpMoveShovelCommand extends Command{

	DoubleSolenoid doubleSolenoid;
	DoubleSolenoid.Value direction;
	
	public TeleOpMoveShovelCommand(ShovelSubsystem shovel, DoubleSolenoid.Value direction){
		doubleSolenoid = shovel.getDoubleSolenoid();
		this.direction = direction;
		requires(shovel);
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		System.out.println("MOVING " + direction);
		doubleSolenoid.set(direction);		
	}

	@Override
	protected boolean isFinished() {
		//Returns false because command is active while the button is held
		return false;
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