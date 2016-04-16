package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team5976.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team5976.robot.subsystems.ShovelSubsystem;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class MultiMoveCommandWithTurn extends CommandGroup {
	public MultiMoveCommandWithTurn(RobotDrive robotDrive, DriveSubsystem driveBase, ShovelSubsystem shovelSubsystem, IntakeSubsystem intakeSubsystem, MultiMoveConfig config){
			AutonomousMoveShovelCommand shovelDown = new AutonomousMoveShovelCommand(shovelSubsystem, DoubleSolenoid.Value.kReverse);
			AutonomousDriveCommand backward = new AutonomousDriveCommand("Initial move", robotDrive, driveBase, config.initialMoveTime, config.speed, config.speed, null);
			AutonomousDriveCommand delay1 = new AutonomousDriveCommand("Delay1", robotDrive, driveBase, config.delayTime, 0.0, 0.0, backward);
			AutonomousDriveCommand turn = new AutonomousDriveCommand("Turning", robotDrive, driveBase, config.turnTime, config.speed, -config.speed, delay1);
			AutonomousDriveCommand delay2 = new AutonomousDriveCommand("Delay2", robotDrive, driveBase, config.delayTime, 0.0, 0.0, turn);
			AutonomousDriveCommand forward = new AutonomousDriveCommand("Second move", robotDrive, driveBase, config.secondMoveTime, -config.speed, -config.speed, delay2);
			IntakeCommand ballOut = new AutonomousIntakeCommand(intakeSubsystem.getIntakeMotor(), intakeSubsystem);
			
			addSequential(shovelDown);
			addSequential(backward);
			addSequential(delay1);
			addSequential(turn);
			addSequential(delay2);
			addSequential(forward);
			if(config.ballOut) addSequential(ballOut);
	}
}
