package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.SpeedSource;

import edu.wpi.first.wpilibj.Preferences;

abstract class SpeedCalculator implements SpeedSource {
	
	private static double expoFactor = 0.2;
	protected double lastSpeed;
	protected double maxAllowedSpeedChange = Preferences.getInstance().getDouble("maxAllowedSpeedChange", 0.2);
	
	public void initialize() {
		lastSpeed = getInitialSpeed();
		System.out.println("In SC init: " + this);
	}
	
	public double calcNext(){
		
		maxAllowedSpeedChange = Preferences.getInstance().getDouble("maxAllowedSpeedChange", 0.2);
		//System.out.println(maxAllowedSpeedChange);
		double currentSpeedReading = adjustSpeed(getRawReading());
		double delta = currentSpeedReading - lastSpeed;
		
		if(Math.abs(delta) > maxAllowedSpeedChange){
			System.out.println("Capped delta of " + delta + " at " + maxAllowedSpeedChange);
			delta = Math.signum(delta) * maxAllowedSpeedChange;
		} 
		
		double cappedNewSpeed = lastSpeed + delta;
		
		if(Math.abs(cappedNewSpeed) > 1){
			cappedNewSpeed = Math.signum(cappedNewSpeed);
		}

		lastSpeed = cappedNewSpeed;
		
		return cappedNewSpeed;
	}
	
	public double getLastSpeed(){
		return lastSpeed;
	}
	
	public abstract double getInitialSpeed();
	
	public abstract double getAbsMaxSpeed();
	
	protected abstract double getRawReading();		
	
    public double adjustSpeed(double d){
    	double speed = Math.signum(d) * Math.pow(Math.abs(d), Math.pow(4, expoFactor));
    	return speed;
    }
}
