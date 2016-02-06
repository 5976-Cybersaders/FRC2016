package org.usfirst.frc.team5976.robot;

import edu.wpi.first.wpilibj.buttons.Button;

public class XBoxButton extends Button {

	XBoxController xBox;
	int buttonNumber;
	
	public XBoxButton(XBoxController xBox, int buttonNumber){
		this.xBox = xBox;
		this.buttonNumber = buttonNumber;
	}
	
	@Override
	public boolean get() {
		return xBox.getRawButton(buttonNumber);
	}
	
}
