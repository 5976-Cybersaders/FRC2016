package org.usfirst.frc.team5976.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class PressedCommand extends Command {

	private boolean finished;
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		System.out.println("COMMAND INITIALIZED");
		finished = false;
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		System.out.println("BUTTON PRESSED");
		finished = true;
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return finished;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		finished = true;
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		end();
	}

}
