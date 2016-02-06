package org.usfirst.frc.team5976.robot.subsystems;

import org.usfirst.frc.team5976.robot.commands.ShovelOff;
import org.usfirst.frc.team5976.robot.commands.TeleOpTankDrive;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shovel extends Subsystem {

	private DoubleSolenoid doubleSolenoid;
	private Compressor compressor;
	
	public Shovel(){
        compressor = new Compressor();
        compressor.setClosedLoopControl(true);
        doubleSolenoid = new DoubleSolenoid(0, 1);
	}
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ShovelOff());
	}
	
	public DoubleSolenoid getDoubleSolenoid(){
		return doubleSolenoid;
	}

}
