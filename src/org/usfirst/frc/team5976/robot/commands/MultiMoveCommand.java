package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class MultiMoveCommand extends CommandGroup {
	public MultiMoveCommand(RobotDrive robotDrive, DriveSubsystem driveBase){
		/*DriveCommand command1 = new DriveCommand("M1.Fwd", robotDrive, driveBase, 4000, -0.61, -0.61, null);
		DriveCommand delay1 = new DriveCommand("M2.Delay", robotDrive, driveBase, 500, 0.0, 0.0, command1);
		DriveCommand command2 = new DriveCommand("M3.Right", robotDrive, driveBase, 1467, -0.5, 0.5, null);
		DriveCommand delay2 = new DriveCommand("M4.Delay", robotDrive, driveBase, 500, 0.0, 0.0, command2);
		DriveCommand command3 = new DriveCommand("M5.Fwd", robotDrive, driveBase, 4000, -0.61, -0.61, delay2);
		
		addSequential(command1);
		addSequential(delay1);
		addSequential(command2);
		addSequential(delay2);
		addSequential(command3);*/
		
		DriveCommand leftTurn = new DriveCommand("Left", robotDrive, driveBase, 4000, .65, -.65, null);
		DriveCommand delay = new DriveCommand("Delay", robotDrive, driveBase, 1500, 0.0, 0.0, leftTurn);
		DriveCommand rightTurn = new DriveCommand("Right", robotDrive, driveBase, 4000, -.65, .65, delay);
		
		addSequential(leftTurn);
		addSequential(delay);
		addSequential(rightTurn);
	}
}
