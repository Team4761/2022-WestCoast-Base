package org.robockets.limelight;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj2.command.CommandBase;
import org.robockets.mathstuff;

import static org.robockets.Robot.isDrive;
import static org.robockets.RobotMap.*;
import static org.robockets.mathstuff.metersToPosition;
import static org.robockets.mathstuff.shootingDistance;


public class adjustToDistance extends CommandBase {

    public NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    public NetworkTableEntry ty = table.getEntry("ty");
    public static double distance;
    public static double distanceGoal;



    public adjustToDistance() {
        // each subsystem used by the command must be passed into the
        // addRequirements() method (which takes a vararg of Subsystem)
        addRequirements();
    }

    @Override
    public void initialize() {
        isDrive = false;
        c_leftEncode.setPosition(0.0);
        c_rightEncode.setPosition(0.0);
        distanceGoal = mathstuff.getDistanceTower(ty.getDouble(0.0)) - metersToPosition(shootingDistance);
        //sets distance goal to difference of current distance to disntace needed to shoot.
    }

    @Override
    public void execute() {
        distance = (c_leftEncode.getPosition() + c_rightEncode.getPosition())/2; //take avg for accuracy i guess
        c_leftPID.setReference(metersToPosition(distanceGoal), CANSparkMax.ControlType.kPosition);
        c_rightPID.setReference(metersToPosition(-distanceGoal), CANSparkMax.ControlType.kPosition);
        //To set to specific meter distances I'm going to make a method that does that, this is only for in conjunction with autotargeting.
        //TODO may go backwards may need to switch the - signs
    }

    @Override
    public boolean isFinished() {

        if(distanceGoal - distance < 0.5 && distanceGoal - distance > -0.5){
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
