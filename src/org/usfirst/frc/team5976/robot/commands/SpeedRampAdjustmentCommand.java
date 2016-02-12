package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.CMHCommandBasedRobot;

import edu.wpi.first.wpilibj.command.Command;

public class SpeedRampAdjustmentCommand extends Command{

	private double adjustmentAmount;
	
	public SpeedRampAdjustmentCommand(double adjustmentAmount){
		this.adjustmentAmount = adjustmentAmount;
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		CMHCommandBasedRobot.maxAllowedSpeedChange += adjustmentAmount;
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
		end();
	}

}
