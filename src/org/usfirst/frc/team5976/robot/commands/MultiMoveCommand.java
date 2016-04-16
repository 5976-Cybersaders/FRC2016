package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team5976.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team5976.robot.subsystems.ShovelSubsystem;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class MultiMoveCommand extends CommandGroup {
	public MultiMoveCommand(RobotDrive robotDrive, DriveSubsystem driveBase, ShovelSubsystem shovelSubsystem, IntakeSubsystem intakeSubsystem){
		AutonomousMoveShovelCommand shovelDown = new AutonomousMoveShovelCommand(shovelSubsystem, DoubleSolenoid.Value.kReverse);
		AutonomousDriveCommand delay1 = new AutonomousDriveCommand("Delay", robotDrive, driveBase, 1000, 0.0, 0.0, null);
		AutonomousDriveCommand backward = new AutonomousDriveCommand("Backward 5.5s", robotDrive, driveBase, 5500, .65, .65, delay1);
	
		addSequential(shovelDown);
		addSequential(delay1);
		addSequential(backward);
	}
}
