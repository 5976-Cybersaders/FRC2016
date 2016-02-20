package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.SpeedSource;
import org.usfirst.frc.team5976.robot.XBoxController;

class AutonomousSpeedCalculator extends SpeedCalculator {

	private SpeedSource initialSpeedSource;
	private double targetSpeed;
	
	public AutonomousSpeedCalculator(double targetSpeed, SpeedSource initialSpeedSource) {
		super();
		
		this.targetSpeed = targetSpeed;
		this.initialSpeedSource = initialSpeedSource;
		System.out.println("Created SpeedCalculator, target speed " + targetSpeed);
	}

	@Override
	protected double getRawReading() {
		return targetSpeed;
	}
	
	public double getAbsMaxSpeed(){
		return targetSpeed;
	}

	@Override
	public double getInitialSpeed() {
		if(initialSpeedSource == null) return 0;
		return initialSpeedSource.getLastSpeed();
	}
	
	@Override
	public String toString() {
		return "Auto Initial=" + getInitialSpeed() + " Target=" + targetSpeed + " Last= " + getLastSpeed();		
	}
	
}
