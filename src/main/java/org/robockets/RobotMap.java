package org.robockets;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class RobotMap {


    public static CANSparkMax c_left;
    public static CANSparkMax c_right;
    public static DifferentialDrive c_robotDrive;
    public static SparkMaxPIDController c_leftPID;
    public static SparkMaxPIDController c_rightPID;
    public static RelativeEncoder c_leftEncode;
    public static RelativeEncoder c_rightEncode;


    public RobotMap(){
        c_left = new CANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushless);
        c_right = new CANSparkMax(2, CANSparkMaxLowLevel.MotorType.kBrushless);
        c_robotDrive = new DifferentialDrive(c_left,c_right);
        c_leftPID = c_left.getPIDController();
        c_rightPID = c_right.getPIDController();
        c_leftEncode = c_left.getEncoder();
        c_rightEncode = c_right.getEncoder();
    }
}
