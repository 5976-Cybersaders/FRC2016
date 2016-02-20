package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.SpeedSource;
import org.usfirst.frc.team5976.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Command;

/*
 * @author Ryan Knuese
 */

public class DriveCommand extends Command {
	private long timeMS, t0;
	private String desc;
	private SpeedCalculator leftSpeedCalculator, rightSpeedCalculator;
	private final RobotDrive robotDrive;
	
	public DriveCommand(String desc, RobotDrive robotDrive, DriveSubsystem driveSubsystem, 
			long timeMS, double leftSpeedTarget, double rightSpeedTarget, DriveCommand previousDriveCommand){
		
		this.desc = desc;
		this.robotDrive = robotDrive;
		this.timeMS = timeMS;
		
		SpeedSource leftSpeedSource = null;
		SpeedSource rightSpeedSource = null;
		
		if(previousDriveCommand != null){
			leftSpeedSource = previousDriveCommand.getLeftSpeedSource();
			rightSpeedSource = previousDriveCommand.getRightSpeedSource();
		}
		
		leftSpeedCalculator = new AutonomousSpeedCalculator(leftSpeedTarget, leftSpeedSource);
		rightSpeedCalculator = new AutonomousSpeedCalculator(rightSpeedTarget, rightSpeedSource);
		requires(driveSubsystem);
	}
	
	@Override
	protected void initialize() {
		//System.out.println("maxAllowedSpeedChange set to " + Preferences.getInstance().getDouble("maxAllowedSpeedChange", 0.2));
		t0 = System.currentTimeMillis();
		leftSpeedCalculator.initialize();
		rightSpeedCalculator.initialize();
		System.out.println("INIT " + this);
	}

	@Override
	protected void execute() {
		double nextLeftSpeed = leftSpeedCalculator.calcNext();
		double nextRightSpeed = rightSpeedCalculator.calcNext();
		robotDrive.tankDrive(nextLeftSpeed, nextRightSpeed);
		long tl = timeMS - (System.currentTimeMillis() - t0);
		//System.out.println("Driving " + timeMS + " LL=" + leftSpeedCalculator.getLastSpeed() + " NL=" + nextLeftSpeed + " ML="+ leftSpeedCalculator.getAbsMaxSpeed() 
			//+  " LR=" + rightSpeedCalculator.getLastSpeed() + " NR=" + nextRightSpeed + " MR=" + rightSpeedCalculator.getAbsMaxSpeed()  +  " " + tl);
	}
	
	@Override
	protected boolean isFinished() {
		boolean done = System.currentTimeMillis() - t0 >= timeMS;
		if (done) System.out.println(getClass().getSimpleName() + " DONE");
		return done;
	}

	@Override
	protected void end() {
		//CMHCommandBasedRobot.driveBase.getRobotDrive().tankDrive(0.0, 0.0);
		System.out.println("END " + this);
	}

	@Override
	protected void interrupted() {
		end();
	}

	public SpeedSource getLeftSpeedSource() {
		return leftSpeedCalculator;
	}

	public SpeedSource getRightSpeedSource() {
		return rightSpeedCalculator;
	}

	@Override
	public String toString() {
		return desc + " (" + timeMS + "ms): Left->" + leftSpeedCalculator + "; Right->" + rightSpeedCalculator;
	}
}