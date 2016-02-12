
package org.usfirst.frc.team5976.robot;

import org.usfirst.frc.team5976.robot.commands.*;

import org.usfirst.frc.team5976.robot.subsystems.DriveBase;
import org.usfirst.frc.team5976.robot.subsystems.Shovel;

import edu.wpi.first.wpilibj.IterativeRobot;
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
	
	public static DriveBase driveBase;
	public static Shovel shovel;
	public static double maxAllowedSpeedChange = .05;
	public static final OI oi = new OI();

    Command autonomousCommand;
    SendableChooser chooser, chooser2;

    public CMHCommandBasedRobot(){
    	System.out.println("CONSTRUCTING COMMAND BASED ROBOT");
    }
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	System.out.println("INIT COMMAND-BASED ROBOT");
		driveBase = new DriveBase();
		shovel = new Shovel();
		
		SmartDashboard.putData("ButtonPressed", new PressedCommand());
		chooser = makeChooser();
		chooser2 = makeChooser();
		
		SmartDashboard.putData("CMH Autonomous Mode 1", chooser);
		//SmartDashboard.putData("CMH Autonomous Mode 2", chooser2);
        /*chooser = new SendableChooser();
        chooser.addDefault("Drive Auto", new TeleOpTankDrive());
        chooser.addObject("Button Pressed Auto", new PressedCommand());
        SmartDashboard.putData("Auto mode", chooser);*/
    }
	
	
	public SendableChooser makeChooser(){
		SendableChooser chooser = new SendableChooser();
        chooser.addDefault("Drive Forward 2s", new DriveCommand(2000, -.5, -.5));
        chooser.addObject("Drive Backward 3s", new DriveCommand(3000, .5, .5));
        chooser.addObject("Turn Left", new DriveCommand(1000, 0, -.5));
        chooser.addObject("Turn Right", new DriveCommand(1000, -.5, 0));
        chooser.addObject("Multiple Moves", new MultiMoveCommand());
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
        autonomousCommand = (Command) chooser.getSelected();
        
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
        
        
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) {
        	System.out.println("Selected command is " + autonomousCommand.getClass().getSimpleName());
        	autonomousCommand.start();
        } else{
        	System.out.println("Autnonomous command is null");
        }
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
        System.out.println();
    	Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
