package org.usfirst.frc.team5976.robot;

import org.usfirst.frc.team5976.robot.commands.MoveShovel;
import org.usfirst.frc.team5976.robot.commands.SpeedRampAdjustmentCommand;
import org.usfirst.frc.team5976.robot.subsystems.ShovelSubsystem;

import edu.wpi.first.wpilibj.DoubleSolenoid;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	
	private static final double adjustmentAmount = 0.05;
	private XBoxController driveController;
	private XBoxController shovelController;
	private XBoxButton shovelUpButton, shovelDownButton, increaseSpeedAdjustmentButton, decreaseSpeedAdjustmentButton;
	
	public OI(ShovelSubsystem shovel){
		driveController = new XBoxController(0);
		shovelController = new XBoxController(1);
		shovelUpButton = new XBoxButton(shovelController, 5);
		shovelDownButton = new XBoxButton(shovelController, 6);
		//increaseSpeedAdjustmentButton = new XBoxButton(shovelController, 1);
		//decreaseSpeedAdjustmentButton = new XBoxButton(shovelController, 2);
		
		shovelUpButton.whileHeld(new MoveShovel(shovel, DoubleSolenoid.Value.kForward));
		shovelDownButton.whileHeld(new MoveShovel(shovel, DoubleSolenoid.Value.kReverse));
		//increaseSpeedAdjustmentButton.whenPressed(new SpeedRampAdjustmentCommand(adjustmentAmount));
		//decreaseSpeedAdjustmentButton.whenPressed(new SpeedRampAdjustmentCommand(-adjustmentAmount));
	}

	public XBoxController getDriveController() {
		return driveController;
	}

	public void setDriveController(XBoxController driveController) {
		this.driveController = driveController;
	}
}