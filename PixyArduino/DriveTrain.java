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

public class DriveTrain extends Subsystem{
	
	public TalonSRX frontLeft, frontRight, backLeft, backRight;
	public AHRS ahrs;
	
	public Timer timer;
	
	public double CountsPerRev = 20;
	
	public double GearReduction = 1.0;
	
	public double WheelDiameter = 6;
	
	public double CountsPerInch = ((CountsPerRev*GearReduction)/(WheelDiameter*Math.PI));
	
	@Override
	protected void initDefaultCommand() {
		
		frontLeft = new TalonSRX(0);
		frontRight = new TalonSRX(1);
		backLeft = new TalonSRX(2);
		backRight = new TalonSRX(3);
		
		try {
	          
	          ahrs = new AHRS(Port.kMXP); 
	          
	    } 
		catch (RuntimeException ex ) {
			
	          DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
	          
	    }
		
		
	}
	
	private void setupTalons(TalonSRX talon, boolean left) {
		
		talon.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 5, 100);
        final ErrorCode sensorPresent = talon.configSelectedFeedbackSensor(FeedbackDevice
                .CTRE_MagEncoder_Relative, 0, 100); //primary closed-loop, 100 ms timeout
        if (sensorPresent != ErrorCode.OK) {
            DriverStation.reportError("Could not detect " + (left ? "left" : "right") + " encoder: " + sensorPresent, false);
        }
        talon.setInverted(!left);
        talon.setSensorPhase(true);
        talon.enableVoltageCompensation(true);
        
		
	}
	
	private double getEncoderValue(TalonSRX talon) {
		
		return talon.getSelectedSensorPosition(0);
		
	}
	
	private double getHeading() {
		
		return ahrs.getAngle();
		
	}
	
	private void stopDrive() {
		
		frontLeft.set(ControlMode.PercentOutput, 0);
		frontRight.set(ControlMode.PercentOutput, 0);
		backLeft.set(ControlMode.PercentOutput, 0);
		backRight.set(ControlMode.PercentOutput, 0);
		
	}
	
	private void setTargetPos(TalonSRX talon, double target) {
		
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
	
	public void drive(double inches, double timeout) {
		
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
		
		stopDrive();
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
		
		stopDrive();
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
			
			stopDrive();
			
		}
		
	}

}
