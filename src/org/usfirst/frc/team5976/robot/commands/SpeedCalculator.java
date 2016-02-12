package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.CMHCommandBasedRobot;
import org.usfirst.frc.team5976.robot.XBoxController;

public class SpeedCalculator {
	
	private static double expoFactor = 0.2;
	private XBoxController driveController;
	private boolean isAutonomous, isLeft;
	private double targetSpeed;
	private double lastSpeed;
	
	public SpeedCalculator(boolean isAutonomous, boolean isLeft, double targetSpeed, XBoxController driveController){
		this.driveController = driveController;
		this.isAutonomous = isAutonomous;
		this.isLeft = isLeft;
		this.targetSpeed = targetSpeed;
		lastSpeed = calcNext(); 
	}
	
	public double calcNext(){
		double currentSpeedReading = adjustSpeed(getRawReading());
		double delta = currentSpeedReading - lastSpeed;
		
		if(Math.abs(delta) > CMHCommandBasedRobot.maxAllowedSpeedChange){
			delta = Math.signum(delta) * CMHCommandBasedRobot.maxAllowedSpeedChange;
		}
		
		double cappedNewSpeed = lastSpeed + delta;
		
		if(Math.abs(cappedNewSpeed) > 1){
			cappedNewSpeed = Math.signum(cappedNewSpeed);
		}

		lastSpeed = cappedNewSpeed;
		
		return cappedNewSpeed;
	}
	
	public double getAbsMaxSpeed(){
		return targetSpeed;
	}
	
	public double getLastSpeed(){
		return lastSpeed;
	}
	
	private double getRawReading(){
		if(isAutonomous) return targetSpeed;
		else if(isLeft) return driveController.getLeftJoyY();
		else return driveController.getRightJoyY();
	}
	
    public static double adjustSpeed(double d){
    	double speed = Math.signum(d) * Math.pow(Math.abs(d), Math.pow(4, expoFactor));
    	return speed;
    }
}
