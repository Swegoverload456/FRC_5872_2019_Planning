/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5872.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	private static final String kDefaultAuto = "Default";
	private static final String kCustomAuto = "My Auto";
	private String m_autoSelected;
	private SendableChooser<String> m_chooser = new SendableChooser<>();
	
	boolean a;
	boolean b;
	boolean c;
	boolean d;
	boolean e;
	boolean f;
	boolean g;
	
	int xVal;

	//public AnalogInput pixyX;
	//public AnalogInput pixyY;
	
	Servo x;
	Servo y;
	
	DigitalInput pixyX;
	
	Timer reset;
	Timer interval;
	//public AnalogInput pixyW;
	//public AnalogInput pixyH;
	
	/*public NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
	public NetworkTableEntry tx = table.getEntry("tx");
	public NetworkTableEntry ty = table.getEntry("ty");
	public NetworkTableEntry ta = table.getEntry("ta");
	public double x = tx.getDouble(0);
	public double y = ty.getDouble(0);
	public double area = ta.getDouble(0);
	
	public TalonSRX frontLeft, frontRight, backLeft, backRight;
	
	public static DriveTrain driveTrain;*/
	
	//public static PixyVision pixy;
	
	/*public AHRS ahrs;
	public double rotateToAngleRate;
	public PIDController turnController;
	
	public Joystick joystick;
	
	static final double kP = 0.03;
	static final double kI = 0.00;
	static final double kD = 0.00;
	static final double kF = 0.00;
	
	float Kp = -0.1f;
	float min_command = 0.05f;
	
	static final double kToleranceDegrees = 2.0f;
	
	static final double kTargetAngleDegrees = 90.0f;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_chooser.addDefault("Default Auto", kDefaultAuto);
		m_chooser.addObject("My Auto", kCustomAuto);
		SmartDashboard.putData("Auto choices", m_chooser);
		
		/*try {
	          
	          ahrs = new AHRS(Port.kMXP); 
	          
	    } 
		catch (RuntimeException ex ) {
			
	          DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
	          
	    }
		
		driveTrain = new DriveTrain();*/
		
		//pixy = new PixyVision();
		
		x = new Servo(2);
		y = new Servo(0);
		
		/*frontLeft = new TalonSRX(0);
		frontRight = new TalonSRX(1);
		backLeft = new TalonSRX(2);
		backRight = new TalonSRX(3);
		
		joystick = new Joystick(0);
		
	    turnController = new PIDController(kP, kI, kD, kF, ahrs, (PIDOutput) this);
	    turnController.setInputRange(-180.0f,  180.0f);
	    turnController.setOutputRange(-1.0, 1.0);
	    turnController.setAbsoluteTolerance(kToleranceDegrees);
	    turnController.setContinuous(true);*/
		
		pixyX = new DigitalInput(9);
		
		reset = new Timer();
		interval = new Timer();
		//pixyY = new AnalogInput(2);
		//pixyW = new AnalogInput(2);
		//pixyH = new AnalogInput(3);
		
		//driveTrain.setupTalon(frontLeft, true, frontRight, false, backLeft, true, backRight, false);
		
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional comparisons to
	 * the switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		m_autoSelected = m_chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + m_autoSelected);
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		switch (m_autoSelected) {
			case kCustomAuto:
				// Put custom auto code here
				
				//driveTrain.getEncoderValue(driveTrain.frontLeft);
				
				break;
			case kDefaultAuto:
			default:
				// Put default auto code here
				break;
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		
		double angle = 0.5;
		
		int i = 0;
		
		/*if(joystick.getRawButton(1)) {
			
			double heading_error = (x*-1);
	        double steering_adjust = 0.0f;
	        if (x > 1.0)
	        {
	                steering_adjust = Kp*heading_error - min_command;
	        }
	        else if (x < 1.0)
	        {
	                steering_adjust = Kp*heading_error + min_command;
	        }
	        double left_command = steering_adjust;
	        double right_command = steering_adjust;
	        
	        driveTrain.setPower(driveTrain, left_command, right_command, left_command, right_command);
			
		}
		else {
			
			driveTrain.stopDrive(driveTrain);
			
		}*/
		reset.start();
		while(reset.get() <= 105) {
			
			if(pixyX.get()) {
				
				a = pixyX.get();
				delay(15);
				b = pixyX.get();
				delay(15);
				c = pixyX.get();
				delay(15);
				d = pixyX.get();
				delay(15);
				e = pixyX.get();
				delay(15);
				f = pixyX.get();
				delay(15);
				g = pixyX.get();
				delay(15);
				
				i++;
				
				if(a == true) {
					
					if(b && !c && d && !e && !f && !g) {
						
						xVal = 0;
						
					}
					else if(b && c && d && !e && !f && !g) {
						
						xVal = 10;
						
					}
					else if(!b && !c && d && e && !f && !g) {
						
						xVal = 20;
						
					}
					else if(b && !c && d && e && !f && !g) {
						
						xVal = 30;
						
       					}
					else if(!b && c && d && e && !f && !g) {
						
						xVal = 40;
						
					}
					else if(!b && !c && !d && e && !f && !g) {
						
						xVal = 50;
						
					}
					else if(b && c && !d && e && !f && !g) {
						
						xVal = 60;
						
					}
					else if(b && c && !d && e && !f && !g) {
						
						xVal = 70;
						
					}
					else if(!b && c && d && e && !f && !g) {
						
						xVal = 80;
						
					}
					else if(!b && !c && d && e && !f && !g) {
						
						xVal = 90;
						
					}
					else if(b && !c && d && e && !f && !g) {
						
						xVal = 100;
						
					}
					else if(!b && !c && !d &&  !e && f && !g) {
						
						xVal = 110;
						
					}
					else if(b && !c && !d && !e && f && !g) {
						
						xVal = 120;
						
					}
					else if(b && c && !d && !e && f && !g) {
						
						xVal = 130;
						
					}
					else if(b && c && d && !e && f && !g) {
						
						xVal = 140;
						
					}
					else if(!b && c && d && e && f && !g) {
						
						xVal = 150;
						
					}
					else if(!b && !c && d && e && f && !g) {
						
						xVal = 160;
						
					}
					else if(!b && !c && !d && e && f && !g) {
						
						xVal = 170;
						
					}
					else if(b && !c && !d && e && f && !g) {
						
						xVal = 180;
						
					}
					else if(!b && !c && !d && !e && !f && g) {
						
						xVal = 190;
						
					}
					else if(b && !c && !d && !e && !f && g) {
						
						xVal = 200;
						
					}
					else if(b && c && !d && !e && !f && g) {
						
						xVal = 210;
						
					}
					else if(b && c && d && !e && !f && g) {
						
						xVal = 220;
						
					}
					else if(b && c && d && e && !f && g) {
						
						xVal = 230;
						
					}
					else if(!b && c && d && e && f && g) {
						
						xVal = 240;
						
					}
					else if(!b && !c && d && e && f && g) {
						
						xVal = 250;
						
					}
					else if(b && c && d && !e && !f && !g) {
						
						xVal = 260;
						
					}
					else if(!b && c && !d && e && !f && !g) {
						
						xVal = 270;
						
					}
					else if(b && c && d && e && !f && !g) {
						
						xVal = 280;
						
					}
					else if(!b && c && d && !e && f && !g) {
						
						xVal = 290;
						
					}
					else if(!b && c && !d && !e && f && !g) {
						
						xVal = 300;
						
					}
					else if(!b && !c && d && !e && f && !g) {
						
						xVal = 310;
						
					}
					else if(b && c && d && e && f && !g) {
						
						xVal = 320;
						
					}
					
					if(i%2 == 0) {
						if(xVal < 99 || xVal > 219) {
							
							x.set(xVal * 0.00125);
							
						}
					}
					
					SmartDashboard.putNumber("X: ", xVal);
					SmartDashboard.putNumber("Servo Pos: ", xVal * 0.0099);
					
				}
				
				
			}
			
		}
		
		//pixyX.getV
		//x.set(pixyX.getVoltage());
		
		//if(pixyX.getVoltage() < 2.2 || pixyX.getVoltage() > 2.8) {
		
			//angle += (pixyX.getVoltage()/5);
			//x.set(angle);
			
		//}
		
		
		
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}

	/*public double convertPixyToAngle() {
		
		double turningAngle;
		
		if(pixyX.getValue() > 164) {
			
			turningAngle = (-1*(pixyX.getVoltage()/2));
			
		}
		else if(pixyX.getValue() < 154) {
			
			turningAngle = (pixyX.getVoltage()/2);
			
		}
		else {
			
			turningAngle = 0;
			
		}
		
		return turningAngle;
		
	}
	
	public void turnRobotOffPixy() {
		
		while(pixyX.getValue() > 164 || pixyX.getValue() < 154) {
			
			
			
		}
		
	}
	*/
	
	public void delay(int milliseconds){
    	try{
    		Thread.sleep(milliseconds);
    	}
    	catch(Exception e1){
    		e1.printStackTrace();
    	}
    }
	public void pidWrite(double output) {
		
	   //rotateToAngleRate = output;
	      
	}
	
}

