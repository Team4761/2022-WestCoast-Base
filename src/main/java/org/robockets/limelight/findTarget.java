package org.robockets.limelight;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;

import static org.robockets.Robot.isDrive;
import static org.robockets.RobotMap.c_leftPID;
import static org.robockets.RobotMap.c_rightPID;


public class findTarget extends CommandBase {

    public NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    public NetworkTableEntry tv = table.getEntry("tv");
    public boolean isTarget;

    public findTarget() {
        // each subsystem used by the command must be passed into the addRequirements() method (which takes a vararg of Subsystem)
        addRequirements();
    }

    @Override
    public void initialize() {
        isTarget = tv.getBoolean(false);
        isDrive = false;

    }

    @Override
    public void execute() {
        isTarget = tv.getBoolean(false);
        c_leftPID.setReference(300, CANSparkMax.ControlType.kVelocity);
        c_rightPID.setReference(-300, CANSparkMax.ControlType.kVelocity);
    }

    @Override
    public boolean isFinished() {
        if(isTarget){
            return true;
        }else{
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
