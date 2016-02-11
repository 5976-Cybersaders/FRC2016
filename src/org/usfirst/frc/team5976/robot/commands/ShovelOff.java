package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.CMHCommandBasedRobot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class ShovelOff extends Command{

	public ShovelOff(){
		requires(CMHCommandBasedRobot.shovel); 
	}
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		CMHCommandBasedRobot.shovel.getDoubleSolenoid().set(DoubleSolenoid.Value.kOff);		
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		
		
	}

	@Override
	protected void interrupted() {
		
	}

}
