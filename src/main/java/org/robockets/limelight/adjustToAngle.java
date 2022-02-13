package org.robockets.limelight;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

import static org.robockets.Robot.isDrive;
import static org.robockets.RobotMap.c_leftPID;
import static org.robockets.RobotMap.c_rightPID;


public class adjustToAngle extends CommandBase {

    public NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    public NetworkTableEntry tx = table.getEntry("tx");
    public static double xAngle;
    public static double maxVoltage = 3;
    public static double minVoltage = 2.4;


    public adjustToAngle() {
        // each subsystem used by the command must be passed into the
        // addRequirements() method (which takes a vararg of Subsystem)
        addRequirements();
    }

    @Override
    public void initialize() {
        isDrive = false;


    }

    //maxVoltage * rampingFactor * ((Math.abs(xAngle) / 30)+1)
    @Override
    public void execute() {

        xAngle = tx.getDouble(0.0);

        if (xAngle > 1) {
            c_leftPID.setReference(400, CANSparkMax.ControlType.kVelocity);
            c_rightPID.setReference(400, CANSparkMax.ControlType.kVelocity);
        } else if (xAngle < -1) {
            c_leftPID.setReference(-400, CANSparkMax.ControlType.kVelocity);
            c_rightPID.setReference(-400, CANSparkMax.ControlType.kVelocity);
        }
        //TODO make sure robot actaully goes in right direction mess with the - signs.
        SmartDashboard.putNumber("tx", tx.getDouble(0.0));

    }

    @Override
    public boolean isFinished() {

        if (xAngle > -1 && xAngle < 1) {
            return true;
        } else {
            return false;
        }


    }

    @Override
    public void end(boolean interrupted) {
        c_leftPID.setReference(0, CANSparkMax.ControlType.kVelocity);
        c_rightPID.setReference(0, CANSparkMax.ControlType.kVelocity);
        isDrive = true;

    }
}
