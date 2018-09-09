package org.usfirst.frc.team5872.robot;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain extends Subsystem{
	
	public AHRS Ahrs;
	
	public Timer timer;
	
	public double CountsPerRev = 20;
	
	public double GearReduction = 1.0;
	
	public double WheelDiameter = 6;
	
	public double CountsPerInch = ((CountsPerRev*GearReduction)/(WheelDiameter*Math.PI));
	
	TalonSRX frontLeft;
	TalonSRX frontRight;
	TalonSRX backLeft;
	TalonSRX backRight;
	
	void setupNavx(AHRS ahrs) {
		
		ahrs = Ahrs;
		
		try {
	          
	          ahrs = new AHRS(Port.kMXP); 
	          
	    } 
		catch (RuntimeException ex ) {
			
	          DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
	          
	    }
		
	}
	
	@Override
	protected void initDefaultCommand() {
		
		
		
		
	}
	
	void setupTalon(TalonSRX Talon, boolean left, TalonSRX frontRight, boolean left2, TalonSRX backLeft, boolean left3, TalonSRX backRight, boolean left4 ) {
		
		frontLeft = Talon;
		frontRight = frontRight;
		backLeft = backLeft;
		backRight = backRight;
		
		frontLeft.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 5, 100);
        final ErrorCode sensorPresent = frontLeft.configSelectedFeedbackSensor(FeedbackDevice
                .CTRE_MagEncoder_Relative, 0, 100); //primary closed-loop, 100 ms timeout
        if (sensorPresent != ErrorCode.OK) {
            DriverStation.reportError("Could not detect " + (left ? "left" : "right") + " encoder: " + sensorPresent, false);
        }
        frontLeft.setInverted(!left);
        frontLeft.setSensorPhase(true);
        frontLeft.enableVoltageCompensation(true);
        
        frontRight.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 5, 100);
        final ErrorCode sensorPresent2 = frontRight.configSelectedFeedbackSensor(FeedbackDevice
                .CTRE_MagEncoder_Relative, 0, 100); //primary closed-loop, 100 ms timeout
        if (sensorPresent2 != ErrorCode.OK) {
            DriverStation.reportError("Could not detect " + (left ? "left" : "right") + " encoder: " + sensorPresent2, false);
        }
        frontRight.setInverted(!left);
        frontRight.setSensorPhase(true);
        frontRight.enableVoltageCompensation(true);
		
        backLeft.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 5, 100);
        final ErrorCode sensorPresent3 = backLeft.configSelectedFeedbackSensor(FeedbackDevice
                .CTRE_MagEncoder_Relative, 0, 100); //primary closed-loop, 100 ms timeout
        if (sensorPresent3 != ErrorCode.OK) {
            DriverStation.reportError("Could not detect " + (left ? "left" : "right") + " encoder: " + sensorPresent3, false);
        }
        backLeft.setInverted(!left);
        backLeft.setSensorPhase(true);
        backLeft.enableVoltageCompensation(true);
        
        backRight.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 5, 100);
        final ErrorCode sensorPresent4 = backRight.configSelectedFeedbackSensor(FeedbackDevice
                .CTRE_MagEncoder_Relative, 0, 100); //primary closed-loop, 100 ms timeout
        if (sensorPresent4 != ErrorCode.OK) {
            DriverStation.reportError("Could not detect " + (left ? "left" : "right") + " encoder: " + sensorPresent4, false);
        }
        backRight.setInverted(!left);
        backRight.setSensorPhase(true);
        backRight.enableVoltageCompensation(true);
        
	}
	
	double getEncoderValue(TalonSRX talon) {
		
		return talon.getSelectedSensorPosition(0);
		
	}
	
	double getHeading() {
		
		return Ahrs.getAngle();
		
	}
	
	void stopDrive(DriveTrain driveTrain) {
		
		driveTrain.frontLeft.set(ControlMode.PercentOutput, 0);
		driveTrain.frontRight.set(ControlMode.PercentOutput, 0);
		driveTrain.backLeft.set(ControlMode.PercentOutput, 0);
		driveTrain.backRight.set(ControlMode.PercentOutput, 0);
		
	}
	
	void setPower(DriveTrain driveTrain, double flSpeed, double frSpeed, double blSpeed, double brSpeed) {
		
		driveTrain.frontLeft.set(ControlMode.PercentOutput, flSpeed);
		driveTrain.frontRight.set(ControlMode.PercentOutput, frSpeed);
		driveTrain.backLeft.set(ControlMode.PercentOutput, blSpeed);
		driveTrain.backRight.set(ControlMode.PercentOutput, brSpeed);
		
	}
	
	void setTargetPos(TalonSRX talon, double target) {
		
		while(true) {
			
			double currentPos = getEncoderValue(talon);
			double distanceFromTarget = currentPos - target;
			double speedConstant = (66.6666666666 * distanceFromTarget);
			
			while(currentPos > target) {
				
				talon.set(ControlMode.PercentOutput, speedConstant);
				
			}
			talon.set(ControlMode.PercentOutput, 0);
			
		}
		
	}
	
	/*public void drive(DriveTrain driveTrain, double inches, double timeout) {
		
		timer.reset();
		timer.start();
		
		double newFrontLeftTarget;
		double newFrontRightTarget;
		double newBackLeftTarget;
		double newBackRightTarget;
		
		while(true && timeout > timer.get()) {
			
			newFrontLeftTarget = getEncoderValue(frontLeft) + (inches * CountsPerInch);
			newFrontRightTarget = getEncoderValue(frontRight) + (inches * CountsPerInch);
			newBackLeftTarget = getEncoderValue(backLeft) + (inches * CountsPerInch);
			newBackRightTarget = getEncoderValue(backRight) + (inches * CountsPerInch);
			
			setTargetPos(frontLeft, newFrontLeftTarget);
			setTargetPos(frontRight, newFrontRightTarget);
			setTargetPos(backLeft, newBackLeftTarget);
			setTargetPos(backRight, newBackRightTarget);
			
		}
		
		stopDrive(Robot.driveTrain);
		timer.stop();
		
	}
	
	public void turnByInches(double inches, double timeout) {
		
		timer.reset();
		timer.start();
		
		double newFrontLeftTarget;
		double newFrontRightTarget;
		double newBackLeftTarget;
		double newBackRightTarget;
		
		while(true && timeout > timer.get()) {
			
			newFrontLeftTarget = getEncoderValue(frontLeft) + (inches * CountsPerInch);
			newFrontRightTarget = -getEncoderValue(frontRight) + (inches * CountsPerInch);
			newBackLeftTarget = getEncoderValue(backLeft) + (inches * CountsPerInch);
			newBackRightTarget = -getEncoderValue(backRight) + (inches * CountsPerInch);
			
			setTargetPos(frontLeft, newFrontLeftTarget);
			setTargetPos(frontRight, newFrontRightTarget);
			setTargetPos(backLeft, newBackLeftTarget);
			setTargetPos(backRight, newBackRightTarget);
			
		}
		
		stopDrive(Robot.driveTrain);
		timer.stop();
		
	}
	
	public void turn(double speed) {
		
		while(true) {
			
			frontLeft.set(ControlMode.PercentOutput, speed);
			frontRight.set(ControlMode.PercentOutput, -speed);
			backLeft.set(ControlMode.PercentOutput, speed);
			backRight.set(ControlMode.PercentOutput, -speed);
			
		}
		
		
	}
	
	public void turnByAngle(double angle, double timeout) {
		
		double angleError;
		
		double turningConstant = 0.0055555555555556;
		
		while(true) {
			
			if(angle > 0) {
				
				angleError = getHeading() - angle;
			
			}
			else if(angle < 0) {
				
				angleError = getHeading() + angle;
				
			}
			else {
				
				angleError = 0;
				
			}
			
			while(getHeading() != angleError) {
				 
				turn(angleError * turningConstant);
					
			}
			
			stopDrive(Robot.driveTrain);
			
		}
	}
	
	public void getTelemetry() {
		
		SmartDashboard.putNumber("FrontLeft Encoder: ", getEncoderValue(Robot.driveTrain.frontLeft));
		SmartDashboard.putNumber("FrontRight Encoder: ", getEncoderValue(Robot.driveTrain.frontRight));
		SmartDashboard.putNumber("BackLeft Encoder: ", getEncoderValue(Robot.driveTrain.backLeft));
		SmartDashboard.putNumber("BackRight Encoder: ", getEncoderValue(Robot.driveTrain.backRight));
		
		SmartDashboard.putNumber("Navx Heading: ", Ahrs.getAngle());
		
	}*/

}
