/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5872.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.SPI;
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
	boolean h;
	boolean l;
	boolean j;
	boolean k;
	
	Joystick stick;
	
	int xVal;
	
	double target = 160;
	
	double error, errord, errorg = 0;
	
	double output, outputd, outputg;
	
	double integral = 0;
	
	double leftEnc;
	
	double errorPrior;
	
	double Diameter = 4;
	
	double EncoderTicks = 560;
	
	double SpeedRatio = 1.0;
	
	//double ticksToInches = ((EncoderTicks * SpeedRatio) / (Diameter * Math.PI));
	
	double ticksToInches = 1.0;
	
	double sensitivity = 0.2;
	
	double tolerance = 1;
	
	double encRead[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	
	M_I2C ard;
	
	PixyPacket pkt;
	
	PIDController pOut;
	
	public static ADXRS450_Gyro gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
	
	TalonSRX left;
	TalonSRX right;
	
	Timer reset;
	Timer interval;
	Timer encoder;
	
	int iterationTime = 50;
	
	int _loops = 0;
	
	double kP = 0.7;
	double kI = 0.0;
	
	double kPD = 2;
	
	double integralPrior = 0;
	
	boolean vision = false;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_chooser.addDefault("Default Auto", kDefaultAuto);
		m_chooser.addObject("My Auto", kCustomAuto);
		SmartDashboard.putData("Auto choices", m_chooser);

		left = new TalonSRX(1);
		right = new TalonSRX(0);
		
		//right.setInverted(true);
		left.setInverted(true);
		
		left.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);
		right.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);
		
		//left.setSensorPhase(true);
		//right.setSensorPhase(true);
		
		left.config_kP(0, 105.06572411, 0);
		left.config_kI(0, 0.425, 0);
		left.config_kD(0, 0.10625, 0);
		
		//right.config_kP(0,  0.1,  0);
		right.config_kP(0, 105.06572411, 0);
		right.config_kI(0, 0.425, 0);
		right.config_kD(0, 0.10625, 0);
		
		gyro.calibrate();
		
		resetEncoders();

		stick = new Joystick(0);
		
		ard = new M_I2C();
		
		pkt = new PixyPacket();
		
		reset = new Timer();
		interval = new Timer();
		
		interval.reset();
				
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
		
		//double x = -stick.getRawAxis(0);
		double y = stick.getRawAxis(1);
		double z = -stick.getRawAxis(2) * 0.5;
		
		double fl = (y  + z);
		double fr = (y  + -z);
		
		int i = 0;
		
		SmartDashboard.putNumber("Left Enc: ", left.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Right Enc: ", right.getSelectedSensorPosition(0));
		
		interval.start();
		
		while(stick.getRawButton(1)) {
		
			pkt = ard.getPixy();
			
			double xVal = pkt.x;
			
			errord = 0.15 - pkt.area;
			
			error = 0.5 - xVal;
			
			integral = integral + (error * interval.get());
			
			output = (kP * error + kI * integral);
			
			outputd = (kPD * errord);
			
			errorPrior = error;
			//integralPrior = integral;
			
			SmartDashboard.putNumber("XPos: ", xVal);//print the data just to see
			SmartDashboard.putNumber("YPos: ", pkt.y * 240);
			
			SmartDashboard.putNumber("Motor Pwr: ", output);
			
			SmartDashboard.putNumber("Timer: ", interval.get());
			
			SmartDashboard.putNumber("Angle: ", gyro.getAngle());
	
			left.set(ControlMode.PercentOutput,  ((-output + -outputd)));
			right.set(ControlMode.PercentOutput, ((output + -outputd)));
			
			delay(iterationTime);
			interval.stop();
			interval.reset();
			
			SmartDashboard.putNumber("Area: ", pkt.area);
			
			if(xVal > 0.46 && xVal < 0.54) {
				
				vision = true;
				
			}
			else {
				
				vision = false;
				
			}
			
			SmartDashboard.putBoolean("Vision", vision);
		
		}
		
		while(stick.getRawButton(2)) {
			
			EncDrive(2500, 2500, 5000);
			
			//left.set(ControlMode.Position, 600);
		
		}
		
		if(stick.getRawButton(4)) {
			
			resetEncoders();
			
		}
		
		if(y < 0.1 && y > -0.1 && z < 0.1 && z > -0.1) {
			
			left.set(ControlMode.PercentOutput, 0);
			right.set(ControlMode.PercentOutput, 0);
			
		}
		else{
		
			left.set(ControlMode.PercentOutput, fl);
			right.set(ControlMode.PercentOutput, fr);
		
		}
		
		SmartDashboard.putNumber("Angle: ", gyro.getAngle());
		
	}	
	
	public void delay(int milliseconds){
    	try{
    		Thread.sleep(milliseconds);
    	}
    	catch(Exception e1){
    		e1.printStackTrace();
    	}
    }
	public void EncDrive(double leftDist, double rightDist, double timeOut) {
		
		
		//encoder.reset();
		//encoder.start();
		
		//while(encoder.get() < timeOut) {
		
			double leftTarget = leftDist;
			double rightTarget = rightDist;
			
			//double leftCurrent = left.getSelectedSensorPosition(0);
			//double rightCurrent = right.getSelectedSensorPosition(0);
			
			//leftTarget = (leftDist * ticksToInches) - left.getSelectedSensorPosition(0);
			//rightTarget = (rightDist * ticksToInches) - right.getSelectedSensorPosition(0);
		
			left.set(ControlMode.Position, leftDist);
			right.set(ControlMode.Position, rightDist);
			
			SmartDashboard.putNumber("Left Target: ", leftDist * ticksToInches);
			
			/*if(leftCurrent > leftTarget) {
				
				left.set(ControlMode.PercentOutput, 0);
				
				SmartDashboard.putString("Left Encoder Pos", "True");
				
			}
			if(rightCurrent > rightTarget) {
				
				right.set(ControlMode.PercentOutput, 0);
				
				SmartDashboard.putString("Right Encoder Pos", "True");
				
			}*/	
			
		//}
		
	}
	
	public void configForPos() {
		
		
		
	}
	
	public void resetEncoders() {
		
		left.getSensorCollection().setQuadraturePosition(0, 5);
		right.getSensorCollection().setQuadraturePosition(0, 5);
		
	}
	
}

