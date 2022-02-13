package org.robockets.movement;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import org.robockets.OI;
import org.robockets.RobotMap;

import static org.robockets.Robot.isDrive;
import static org.robockets.RobotMap.*;


public class driveGo extends CommandBase {
//    private final drivetrain drivetrain = org.robockets.movement.drivetrain.getInstance();


    public driveGo() {
        // each subsystem used by the command must be passed into the
        // addRequirements() method (which takes a vararg of Subsystem)
        //      addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        System.out.println("Drive GO INIT");


    }

    @Override
    public void execute() {
        if (isDrive) {
            /*
            double setPointL = OI.xbox2.getRawAxis(1);
            double setPointR = OI.xbox.getRawAxis(1);
            c_leftPID.setReference(setPointL * drivetrain.drivetrainSpeed, CANSparkMax.ControlType.kVelocity);
            c_rightPID.setReference(setPointR * drivetrain.drivetrainSpeed, CANSparkMax.ControlType.kVelocity);

             */
            c_robotDrive.tankDrive(OI.xbox.getRawAxis(1) * drivetrain.drivetrainSpeed, OI.xbox2.getRawAxis(1) * drivetrain.drivetrainSpeed);
        }

    }

    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
