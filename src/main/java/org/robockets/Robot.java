// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.robockets;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import org.robockets.movement.drivetrain;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

import static org.robockets.RobotMap.*;
import static org.robockets.mathstuff.limelightMountAngle;


public class Robot extends TimedRobot {

    public NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    public NetworkTableEntry ty = table.getEntry("ty");
    public NetworkTableEntry tx = table.getEntry("tx");
    private static final String DEFAULT_AUTO = "Default";
    private static final String CUSTOM_AUTO = "My Auto";
    private String autoSelected;
    private final SendableChooser<String> chooser = new SendableChooser<>();

    public static OI oi;
    public static RobotMap robotMap;
    public static drivetrain Drivetrain;

    public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM, maxVel, minVel, maxAcc, allowedErr;

    public static boolean isDrive = true;


    @Override
    public void robotInit() {
        chooser.setDefaultOption("Default Auto", DEFAULT_AUTO);
        chooser.addOption("My Auto", CUSTOM_AUTO);
        SmartDashboard.putData("Auto choices", chooser);
        oi = new OI();
        robotMap = new RobotMap();
        Drivetrain = drivetrain.getInstance();

        kP = 0.5;
        kI = 0.00005;
        kD = 0.2;
        kIz = 0;
        kFF = 0.000015;
        kMaxOutput = 0.4;
        kMinOutput = -0.4;
        maxRPM = 5700;
        maxVel = 2000;
        maxAcc = 1500;


        // set PID coefficients
        c_leftPID.setP(kP);
        c_leftPID.setI(kI);
        c_leftPID.setD(kD);
        c_leftPID.setIZone(kIz);
        c_leftPID.setFF(kFF);
        c_leftPID.setOutputRange(kMinOutput, kMaxOutput);

        c_rightPID.setP(kP);
        c_rightPID.setI(kI);
        c_rightPID.setD(kD);
        c_rightPID.setIZone(kIz);
        c_rightPID.setFF(kFF);
        c_rightPID.setOutputRange(kMinOutput, kMaxOutput);

        int smartMotionSlotRight = 0;
        c_rightPID.setSmartMotionMaxVelocity(maxVel, smartMotionSlotRight);
        c_rightPID.setSmartMotionMinOutputVelocity(minVel, smartMotionSlotRight);
        c_rightPID.setSmartMotionMaxAccel(maxAcc, smartMotionSlotRight);
        c_rightPID.setSmartMotionAllowedClosedLoopError(allowedErr, smartMotionSlotRight);

        int smartMotionSlotLeft = 0;
        c_leftPID.setSmartMotionMaxVelocity(maxVel, smartMotionSlotLeft);
        c_leftPID.setSmartMotionMinOutputVelocity(minVel, smartMotionSlotLeft);
        c_leftPID.setSmartMotionMaxAccel(maxAcc, smartMotionSlotLeft);
        c_leftPID.setSmartMotionAllowedClosedLoopError(allowedErr, smartMotionSlotLeft);

        // display PID coefficients on SmartDashboard
        SmartDashboard.putNumber("P Gain", kP);
        SmartDashboard.putNumber("I Gain", kI);
        SmartDashboard.putNumber("D Gain", kD);
        SmartDashboard.putNumber("I Zone", kIz);
        SmartDashboard.putNumber("Feed Forward", kFF);
        SmartDashboard.putNumber("Max Output", kMaxOutput);
        SmartDashboard.putNumber("Min Output", kMinOutput);

        SmartDashboard.putNumber("Max Velocity", maxVel);
        SmartDashboard.putNumber("Min Velocity", minVel);
        SmartDashboard.putNumber("Max Acceleration", maxAcc);
        SmartDashboard.putNumber("Allowed Closed Loop Error", allowedErr);
        SmartDashboard.putNumber("Set Position", 0);
        SmartDashboard.putNumber("Set Velocity", 0);

        c_left.burnFlash();
        c_right.burnFlash();
    }


    @Override
    public void robotPeriodic() {

        CommandScheduler.getInstance().run();

        double p = SmartDashboard.getNumber("P Gain", 0);
        double i = SmartDashboard.getNumber("I Gain", 0);
        double d = SmartDashboard.getNumber("D Gain", 0);
        double iz = SmartDashboard.getNumber("I Zone", 0);
        double ff = SmartDashboard.getNumber("Feed Forward", 0);
        double max = SmartDashboard.getNumber("Max Output", 0);
        double min = SmartDashboard.getNumber("Min Output", 0);

        double maxV = SmartDashboard.getNumber("Max Velocity", 0);
        double minV = SmartDashboard.getNumber("Min Velocity", 0);
        double maxA = SmartDashboard.getNumber("Max Acceleration", 0);
        double allE = SmartDashboard.getNumber("Allowed Closed Loop Error", 0);

        // if PID coefficients on SmartDashboard have changed, write new values to controller
        if ((p != kP)) {
            c_leftPID.setP(p);
            c_rightPID.setP(p);
            kP = p;
        }
        if ((i != kI)) {
            c_leftPID.setI(i);
            c_rightPID.setI(i);
            kI = i;
        }
        if ((d != kD)) {
            c_leftPID.setD(d);
            c_rightPID.setD(d);
            kD = d;
        }
        if ((iz != kIz)) {
            c_leftPID.setIZone(iz);
            c_rightPID.setIZone(iz);
            kIz = iz;
        }
        if ((ff != kFF)) {
            c_leftPID.setFF(ff);
            c_rightPID.setFF(ff);
            kFF = ff;
        }
        if ((max != kMaxOutput) || (min != kMinOutput)) {
            c_leftPID.setOutputRange(min, max);
            c_rightPID.setOutputRange(min, max);
            kMinOutput = min;
            kMaxOutput = max;
        }

        if ((maxV != maxVel)) {
            c_leftPID.setSmartMotionMaxVelocity(maxV, 0);
            c_rightPID.setSmartMotionMaxVelocity(maxV, 0);
            maxVel = maxV;
        }
        if ((minV != minVel)) {
            c_leftPID.setSmartMotionMinOutputVelocity(minV, 0);
            c_rightPID.setSmartMotionMinOutputVelocity(minV, 0);
            minVel = minV;
        }
        if ((maxA != maxAcc)) {
            c_leftPID.setSmartMotionMaxAccel(maxA, 0);
            c_rightPID.setSmartMotionMaxAccel(maxA, 0);
            maxAcc = maxA;
        }
        if ((allE != allowedErr)) {
            c_leftPID.setSmartMotionAllowedClosedLoopError(allE, 0);
            c_rightPID.setSmartMotionAllowedClosedLoopError(allE, 0);
            allowedErr = allE;
        }


        SmartDashboard.putNumber("Distance to Target", mathstuff.getDistanceTower(ty.getDouble(0.0)) + limelightMountAngle);
        SmartDashboard.putNumber("Angle to Target", tx.getDouble(0.0));
        SmartDashboard.putNumber("Needed Velocity to Target", mathstuff.findVelocity(ty.getDouble(0.0)));


        System.out.println(c_leftEncode.getPosition());

    }


    @Override
    public void autonomousInit() {
        autoSelected = chooser.getSelected();
        // autoSelected = SmartDashboard.getString("Auto Selector", DEFAULT_AUTO);
        System.out.println("Auto selected: " + autoSelected);
    }


    /**
     * This method is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() {
        switch (autoSelected) {
            case CUSTOM_AUTO:
                // Put custom auto code here
                break;
            case DEFAULT_AUTO:
            default:
                // Put default auto code here
                break;
        }
    }


    /**
     * This method is called once when teleop is enabled.
     */
    @Override
    public void teleopInit() {
    }


    /**
     * This method is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() {
    }


    /**
     * This method is called once when the robot is disabled.
     */
    @Override
    public void disabledInit() {
    }


    /**
     * This method is called periodically when disabled.
     */
    @Override
    public void disabledPeriodic() {
    }


    /**
     * This method is called once when test mode is enabled.
     */
    @Override
    public void testInit() {
    }


    /**
     * This method is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {
    }
}
