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
		AutonomousDriveCommand delay1 = new AutonomousDriveCommand("Delay", robotDrive, driveBase, 500, 0.0, 0.0, null);
		AutonomousDriveCommand forward = new AutonomousDriveCommand("Forward", robotDrive, driveBase, 4000, -.65, -.65, delay1);
		AutonomousDriveCommand delay2 = new AutonomousDriveCommand("Delay", robotDrive, driveBase, 500, 0.0, 0.0, forward);
		AutonomousDriveCommand backward = new AutonomousDriveCommand("Backward", robotDrive, driveBase, 4000, .65, .65, delay2);
		
		//addSequential(shovelDown);
		addSequential(delay1);
		addSequential(forward);
		addSequential(delay2);
		addSequential(backward);
	}
}
