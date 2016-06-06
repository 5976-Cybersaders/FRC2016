package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.XBoxController;

class TeleOpSpeedCalculator extends SpeedCalculator {

	private XBoxController driveController;
	private boolean isLeft;

	public TeleOpSpeedCalculator(boolean isLeft, XBoxController driveController) {
		super();
		this.driveController = driveController;
		this.isLeft = isLeft;
	}

	@Override
	protected double getRawReading() {
		if (isLeft) return driveController.getLeftJoyY();
		return driveController.getRightJoyY();
	}

	@Override
	public double getAbsMaxSpeed() {
		return 1;
	}

	@Override
	public double getInitialSpeed() {
		return 0;
	}

}
