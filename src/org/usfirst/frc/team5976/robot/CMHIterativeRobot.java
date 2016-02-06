
package org.usfirst.frc.team5976.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class CMHIterativeRobot extends IterativeRobot {
    final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    String autoSelected;
    SendableChooser chooser;
	RobotDrive robotDrive;  // class that handles basic drive operations
    XBoxController xBox1, xBox2;
    PowerDistributionPanel pdp;
    Compressor compressor;
    DoubleSolenoid solenoid;
    private boolean logEnabled = true;
    private static double expoFactor = 0.2;
    
    public CMHIterativeRobot() {
        
    }
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
        //Our stuff
        robotDrive = new RobotDrive(0, 1);
        robotDrive.setExpiration(0.1);
        xBox1 = new XBoxController(0);
        xBox2 = new XBoxController(1);
        pdp = new PowerDistributionPanel();
        compressor = new Compressor();
        compressor.setClosedLoopControl(true);
        solenoid = new DoubleSolenoid(0, 1);
    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {
    	autoSelected = (String) chooser.getSelected();
//		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		log("Auto selected: " + autoSelected);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	switch(autoSelected) {
    		case customAuto:
    			//Put custom auto code here   
    			log("Running custom autonomous...");
    			break;
    		case defaultAuto:
    		default:
    			//Put default auto code here
    			log("Running default autonomous...");
    			break;
    	}
    }

    public void teleopInit() {
        log("Teleoperated init");
      }
    
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	double xBoxRight, xBoxLeft;
    	boolean enabled = compressor.enabled();
    	boolean pressureSwitch = compressor.getPressureSwitchValue();
    	float current = compressor.getCompressorCurrent();
    	
    	log("Compressor values: " + enabled + " " + pressureSwitch + " " + current);
    	
    	while (isOperatorControl() && isEnabled()) {
    		compressor.start();
        	xBoxRight = xBox1.getRightJoyY();
        	xBoxLeft = xBox1.getLeftJoyY();
        	
        	robotDrive.tankDrive(adjustSpeed(xBoxLeft), adjustSpeed(xBoxRight));
        	
        	if(xBox2.getButtonLB()){
        		log("SOLENOID FORWARD");
        		solenoid.set(DoubleSolenoid.Value.kForward);
        	}
        	else{
        		if(xBox2.getButtonRB()){
        			log("SOLENOID REVERSE");
        			solenoid.set(DoubleSolenoid.Value.kReverse);
        		}
        		else{
        			log("SOLENOID OFF");
        			solenoid.set(DoubleSolenoid.Value.kOff);
        		}
        	}
            
        	Timer.delay(0.005);
        }
    	compressor.stop();
    }
    
    public void disabledInit(){
    	compressor.setClosedLoopControl(false);
    	solenoid.set(DoubleSolenoid.Value.kOff);
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
    public static double adjustSpeed(double d){
    	double speed = Math.signum(d) * Math.pow(Math.abs(d), Math.pow(4, expoFactor));
    	return speed;
    }
    
    public void log(String s){
    	if(logEnabled){
    		System.out.println(s);
    	}
    }
}
