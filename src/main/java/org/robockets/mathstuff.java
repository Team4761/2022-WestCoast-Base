package org.robockets;

public class mathstuff {
    //ALL MEASUREMENTS IN METRIC (because math)
    public static final double robotWidth = 3;
    public static final double robotMass = 50;
    public static final double limelightMountHeight = 0.9144;
    public static final double targetHeight = 2.4384;
    public static final double limelightMountAngle = 30;
    public static final double shooterAngle = 60; //measure tonight if possible
    public static final double r_wheel_meters = 0.05;
    public static final double gearRatio = 4.8; //approx
    public static final double shootingDistance = 4;
    //TODO GET REAL VALUES


    public static double getDistanceTower(double ty){
        return ((targetHeight - limelightMountHeight)/(Math.tan(limelightMountAngle + ty)));
        //ty is passed through because it changes depending on when method is called.
    }

    public static double findVelocity(double ty){
        double distance = getDistanceTower(ty);
        return Math.sqrt(
                (-9.81 * distance * distance)
                        /
                (2 * Math.cos(shooterAngle) * Math.cos(shooterAngle) * (targetHeight - distance * Math.tan(shooterAngle)))
        );
    }

    public static double metersToPosition(double meters) {
        double circumference = Math.PI * 2.0 * r_wheel_meters;
        return (meters / circumference) * gearRatio;
    }







}
