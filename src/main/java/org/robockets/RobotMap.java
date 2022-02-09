package org.robockets;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class RobotMap {
    
    public static CANSparkMax left = new CANSparkMax(2, CANSparkMaxLowLevel.MotorType.kBrushless);
    public static CANSparkMax right = new CANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushless);

    public static DifferentialDrive m_drive = new DifferentialDrive(left,right);

    //Joystick
    public static Joystick joystick = new Joystick(0);
}
