package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.subsystems.ShovelSubsystem;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class MoveShovel extends Command{

	ShovelSubsystem shovel;
	DoubleSolenoid.Value direction;
	
	public MoveShovel(ShovelSubsystem shovel, DoubleSolenoid.Value direction){
		this.shovel = shovel;
		this.direction = direction;
		requires(shovel);
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		System.out.println("MOVING " + direction);
		shovel.getDoubleSolenoid().set(direction);		
	}

	@Override
	protected boolean isFinished() {
		//Returns false because command is active while the button is held
		return false;
	}

	@Override
	protected void end() {
		shovel.getDoubleSolenoid().set(DoubleSolenoid.Value.kOff);
	}

	@Override
	protected void interrupted() {
		System.out.println("INTERRUPTED " + direction);
		end();
	}
}