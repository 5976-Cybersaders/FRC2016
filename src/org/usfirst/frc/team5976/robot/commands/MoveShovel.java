package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.CMHCommandBasedRobot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class MoveShovel extends Command{

	DoubleSolenoid.Value direction;
	
	public MoveShovel(DoubleSolenoid.Value direction){
		this.direction = direction;
		requires(CMHCommandBasedRobot.shovel);
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		System.out.println("MOVING " + direction);
		CMHCommandBasedRobot.shovel.getDoubleSolenoid().set(direction);		
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		CMHCommandBasedRobot.shovel.getDoubleSolenoid().set(DoubleSolenoid.Value.kOff);
	}

	@Override
	protected void interrupted() {
		System.out.println("INTERRUPTED " + direction);
		end();
	}
}