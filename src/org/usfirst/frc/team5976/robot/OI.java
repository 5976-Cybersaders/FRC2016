package org.usfirst.frc.team5976.robot;

import org.usfirst.frc.team5976.robot.commands.MoveShovel;
import org.usfirst.frc.team5976.robot.commands.PressedCommand;
import org.usfirst.frc.team5976.robot.commands.ShovelOff;
import org.usfirst.frc.team5976.robot.commands.ShovelUp;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
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
	
	private XBoxController driveController;
	private XBoxController shovelController;
	/*private Joystick stick = new Joystick(0);
	private JoystickButton button = new JoystickButton(stick, 0);*/
	private XBoxButton upButton, downButton;
	
	public OI(){
		driveController = new XBoxController(0);
		shovelController = new XBoxController(1);
		upButton = new XBoxButton(shovelController, 5);
		downButton = new XBoxButton(shovelController, 6);
		
		upButton.whileHeld(new MoveShovel(DoubleSolenoid.Value.kForward));
		downButton.whileHeld(new MoveShovel(DoubleSolenoid.Value.kReverse));
	}

	public XBoxController getDriveController() {
		return driveController;
	}

	public void setDriveController(XBoxController driveController) {
		this.driveController = driveController;
	}
}