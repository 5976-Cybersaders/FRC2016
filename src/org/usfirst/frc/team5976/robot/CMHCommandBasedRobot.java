
package org.usfirst.frc.team5976.robot;

import org.usfirst.frc.team5976.robot.commands.*;

import org.usfirst.frc.team5976.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team5976.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team5976.robot.subsystems.ShovelSubsystem;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class CMHCommandBasedRobot extends IterativeRobot {
	
	public DriveSubsystem driveSubsystem;
	public ShovelSubsystem shovelSubsystem;
	public IntakeSubsystem intakeSubsystem;
	public static double maxAllowedSpeedChange = 0.2;
	public Preferences preferences;
	public OI oi;

    Command autonomousCommand;
    SendableChooser chooser;

    public CMHCommandBasedRobot(){
    	System.out.println("CONSTRUCTING COMMAND BASED ROBOT");
    }
    
    public void startCompetition(){
    	System.out.println("STARTING COMPETITION");
    	super.startCompetition();
    }
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	System.out.println("START ROBOT INIT");
    	preferences = Preferences.getInstance();
    	//maxAllowedSpeedChange = preferences.getDouble("maxAllowedSpeedChange", maxAllowedSpeedChange);
		driveSubsystem = new DriveSubsystem();
		shovelSubsystem = new ShovelSubsystem();
		intakeSubsystem = new IntakeSubsystem();
		oi = new OI(shovelSubsystem, intakeSubsystem);
		
		chooser = makeChooser();
		
		SmartDashboard.putData("CMH Autonomous Mode 1", chooser);
		System.out.println("END ROBOT INIT");
    }
	
	public SendableChooser makeChooser(){
		SendableChooser chooser = new SendableChooser();
		double speed = 0.65;
        chooser.addDefault("Drive Forward", new AutonomousDriveCommand("Forward", driveSubsystem.getRobotDrive(), driveSubsystem, 4000, -.65, -.65, null));
        chooser.addObject("Drive Backward", new AutonomousDriveCommand("Back", driveSubsystem.getRobotDrive(), driveSubsystem, 2000, .5, .5, null));
        chooser.addObject("Turn Left", new AutonomousDriveCommand("Left", driveSubsystem.getRobotDrive(), driveSubsystem, 4000, speed, -speed, null));
        chooser.addObject("Turn Right", new AutonomousDriveCommand("Right", driveSubsystem.getRobotDrive(), driveSubsystem, 4000, -speed, speed, null));
        chooser.addObject("Multiple Moves", new MultiMoveCommand(driveSubsystem.getRobotDrive(), driveSubsystem));
        chooser.addObject("Do Nothing", new AutonomousDriveCommand("Nothing", driveSubsystem.getRobotDrive(), driveSubsystem, 15000, 0.0, 0.0, null));
        chooser.addDefault("Drive Forward 1s, 0.61", new AutonomousDriveCommand("Forward", driveSubsystem.getRobotDrive(), driveSubsystem, 1000, -.61, -.61, null));
        chooser.addDefault("Drive Backward 5s, 0.65", new AutonomousDriveCommand("Backward", driveSubsystem.getRobotDrive(), driveSubsystem, 5000, .65, .65, null));
        return chooser;
	}
    
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
    	System.out.println("START AUTO INIT");
        autonomousCommand = (Command) chooser.getSelected();
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) {
        	System.out.println("START selected command " + autonomousCommand.getClass().getSimpleName());
        	autonomousCommand.start();
        } else{
        	System.out.println("Autnonomous command is null");
        }
        System.out.println("END AUTO INIT");
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
