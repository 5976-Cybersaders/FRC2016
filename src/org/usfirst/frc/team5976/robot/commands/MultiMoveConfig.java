package org.usfirst.frc.team5976.robot.commands;

public class MultiMoveConfig {
	final long initialMoveTime, turnTime, secondMoveTime, delayTime;
	final double speed;
	final boolean ballOut;
	
	public MultiMoveConfig(long initialMoveTime, long turnTime, long secondMoveTime, boolean ballOut, long delayTime, double speed){
		this.initialMoveTime = initialMoveTime;
		this.turnTime = turnTime;
		this.secondMoveTime = secondMoveTime;
		this.ballOut = ballOut;
		this.delayTime = delayTime;
		this.speed = speed;
	}	
}