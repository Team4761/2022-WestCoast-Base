package org.robockets.limelight;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class adjustToAll extends SequentialCommandGroup {
    public adjustToAll() {
        // TODO: Add your sequential commands in the super() call, e.g.
        //           super(new FooCommand(), new BarCommand());
        super(new findTarget(), new adjustToAngle(), new adjustToDistance());
    }
}