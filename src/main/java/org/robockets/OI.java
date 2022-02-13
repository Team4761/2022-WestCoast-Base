package org.robockets;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import org.robockets.limelight.adjustToAll;
import org.robockets.limelight.adjustToAngle;
import org.robockets.limelight.adjustToDistance;
import org.robockets.movement.driveGo;
import org.robockets.movement.speedDecrease;
import org.robockets.movement.speedIncrease;

public class OI {

    public static Joystick xbox = new Joystick(0);
    public static Joystick xbox2 = new Joystick(1);

    public static JoystickButton xbox01 = new JoystickButton(xbox, 1); // a
    public static JoystickButton xbox02 = new JoystickButton(xbox, 2); // b
    public static JoystickButton xbox03 = new JoystickButton(xbox, 3); // x
    public static JoystickButton xbox04 = new JoystickButton(xbox, 4); // y
    public static JoystickButton xbox05 = new JoystickButton(xbox, 5);
    public static JoystickButton xbox06 = new JoystickButton(xbox, 6);
    public static JoystickButton xbox07 = new JoystickButton(xbox, 7);
    public static JoystickButton xbox08 = new JoystickButton(xbox, 8);
    public static JoystickButton xbox09 = new JoystickButton(xbox, 9);
    public static JoystickButton xbox010 = new JoystickButton(xbox, 10);
    public static JoystickButton xbox011 = new JoystickButton(xbox, 11);

    public static JoystickButton xbox11 = new JoystickButton(xbox2, 1); // a
    public static JoystickButton xbox12 = new JoystickButton(xbox2, 2); // b
    public static JoystickButton xbox13 = new JoystickButton(xbox2, 3); // x
    public static JoystickButton xbox14 = new JoystickButton(xbox2, 4); // y
    public static JoystickButton xbox15 = new JoystickButton(xbox2, 5);
    public static JoystickButton xbox16 = new JoystickButton(xbox2, 6);
    public static JoystickButton xbox17 = new JoystickButton(xbox2, 7);
    public static JoystickButton xbox18 = new JoystickButton(xbox2, 8);
    public static JoystickButton xbox19 = new JoystickButton(xbox2, 9);
    public static JoystickButton xbox110 = new JoystickButton(xbox2, 10);
    public static JoystickButton xbox111 = new JoystickButton(xbox2, 11);

    public OI(){
        xbox03.whenPressed(new adjustToAll());
        xbox08.whenPressed(new driveGo());
        xbox01.whenPressed(new speedIncrease());
        xbox11.whenPressed(new speedDecrease());
    }





}
