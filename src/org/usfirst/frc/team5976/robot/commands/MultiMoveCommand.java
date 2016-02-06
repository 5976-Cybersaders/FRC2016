package org.usfirst.frc.team5976.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MultiMoveCommand extends CommandGroup {
	public MultiMoveCommand(){
		addSequential(new DriveCommand(500, 0.0, 0.5));
		addSequential(new DriveCommand(1000, 0.5, 0.5));
		addSequential(new DriveCommand(500, 0.5, 0.0));
	}
}
