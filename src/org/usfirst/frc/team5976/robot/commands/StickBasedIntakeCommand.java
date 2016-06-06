package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.XBoxController;
import org.usfirst.frc.team5976.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.VictorSP;


public class StickBasedIntakeCommand extends IntakeCommand {

	private XBoxController xBox;
	private PowerDistributionPanel mPdp;
	private final SpeedCalculator mSpeedCalculator;
	private boolean maxCurrentExceeded;
	private double currentExceededWithSpeed;
	private double maxAllowedCurrent = 10;

	public StickBasedIntakeCommand(XBoxController xBox, VictorSP intakeMotor, IntakeSubsystem intakeSubsystem) {
		super(intakeMotor, intakeSubsystem);
		mSpeedCalculator = new TeleOpSpeedCalculator(true, xBox);
		mPdp = new PowerDistributionPanel();
		this.xBox = xBox;
	}

	@Override
	protected void execute() {
		double speedToSet = mSpeedCalculator.calcNext();
		System.out.println(mPdp.getCurrent(5));
		if (mPdp.getCurrent(0) > maxAllowedCurrent) {
			maxCurrentExceeded = true;
			currentExceededWithSpeed = speedToSet;
		}
		if (maxCurrentExceeded && Math.signum(speedToSet) != Math.signum(currentExceededWithSpeed)) {
			maxCurrentExceeded = false;
		}

		if (Math.abs(speedToSet) < 0.05 || maxCurrentExceeded) {
			speedToSet = 0.0;
		}

		intakeMotor.set(speedToSet);
	}
}
