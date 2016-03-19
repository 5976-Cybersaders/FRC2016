package org.usfirst.frc.team5976.robot.subsystems;

import org.usfirst.frc.team5976.robot.commands.ShovelOff;
import org.usfirst.frc.team5976.robot.commands.TeleOpTankDriveCommand;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShovelSubsystem extends Subsystem {

	private DoubleSolenoid doubleSolenoid;
	private Compressor compressor;
	
	public ShovelSubsystem(){
        compressor = new Compressor();
        compressor.setClosedLoopControl(true);
        doubleSolenoid = new DoubleSolenoid(0, 1);
	}
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ShovelOff(this));
	}
	
	public DoubleSolenoid getDoubleSolenoid(){
		return doubleSolenoid;
	}

}
