package frc.robot;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;

public final class Constants {

    public final static class DriveConstants{
        // Change the decimal numbers here to inches. If the distance between the wheels is 40 inches,
        // then type a 40 in place of the '0.2794'. If the distance is 40 and a half inches, type it
        // in as 40.5.
        public static final double DRIVETRAIN_TRACKWIDTH_METERS = Units.inchesToMeters(24);
        public static final double DRIVETRAIN_WHEELBASE_METERS = Units.inchesToMeters(24);

        public static final int DRIVETRAIN_PIGEON_ID = 9; // don't need this, but if we get one, we need to assign it an ID

        //Change the numbers to match the ID's, for the drive, steer, and encoder
        // Set the Encoder ID in the Phoenix Tuner software
        // Mess with the offset values so that the robot wheels end up straight, they are degree values
        // The stock offset value is 0, if we ever want to return to "default" settings
        // public static final int FRONT_LEFT_MODULE_DRIVE_MOTOR = 6;
        // public static final int FRONT_LEFT_MODULE_STEER_MOTOR = 5;
        // public static final int FRONT_LEFT_MODULE_STEER_ENCODER = 12;
        // public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(33); 

        // public static final int FRONT_RIGHT_MODULE_DRIVE_MOTOR = 1;
        // public static final int FRONT_RIGHT_MODULE_STEER_MOTOR = 2;
        // public static final int FRONT_RIGHT_MODULE_STEER_ENCODER = 11;
        // public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(78);

        // public static final int BACK_LEFT_MODULE_DRIVE_MOTOR = 8;
        // public static final int BACK_LEFT_MODULE_STEER_MOTOR = 7;
        // public static final int BACK_LEFT_MODULE_STEER_ENCODER = 13;
        // public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(105);

        // public static final int BACK_RIGHT_MODULE_DRIVE_MOTOR = 3;
        // public static final int BACK_RIGHT_MODULE_STEER_MOTOR = 4;
        // public static final int BACK_RIGHT_MODULE_STEER_ENCODER = 10;
        // public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(60);

        public static final boolean Field_Relative_Drive = true;

        public static final int Steer_Current_Limit = 20;
        public static final int Drive_Current_Limit = 40;

        public static final double JoyStick_Limit = 0.80;
        public static final double Joystick_Deadband = 0.2;
    }

    public static final class ShooterConstants{
        public static final int TOP_SHOOTER_MOTOR = 15;
        public static final int BOTTOM_SHOOTER_MOTOR = 14;
        public static final double ShootingSpeed = 1;
        public static final double SlowShootingSpeed = 0.15;
        public static final double IntakeSpeed = -0.1;
    }


    public static final class SwerveConstants {
        public static final int LEFT_FRONT_DRIVE_ID = 6;
        public static final int RIGHT_FRONT_DRIVE_ID = 1;
        public static final int LEFT_BACK_DRIVE_ID = 8;
        public static final int RIGHT_BACK_DRIVE_ID = 3;
        
        public static final int LEFT_FRONT_TURN_ID = 5;
        public static final int RIGHT_FRONT_TURN_ID = 2;
        public static final int LEFT_BACK_TURN_ID = 7;
        public static final int RIGHT_BACK_TURN_ID = 4;
        
        public static final int LEFT_FRONT_CANCODER_ID = 12;
        public static final int RIGHT_FRONT_CANCODER_ID = 11;
        public static final int LEFT_BACK_CANCODER_ID = 13;
        public static final int RIGHT_BACK_CANCODER_ID = 10;

        //Drivetrain characteristics
        //Change
        public static final double LEFT_FRONT_OFFSET = -0.080;
        public static final double RIGHT_FRONT_OFFSET = -0.221;
        public static final double LEFT_BACK_OFFSET = 0.280;
        public static final double RIGHT_BACK_OFFSET = 0.237;

        public static final double WHEEL_DIAMETER = Units.inchesToMeters(3);
        public static final double DRIVE_MOTOR_GEAR_RATIO = 5.25;
        public static final double TURN_MOTOR_GEAR_RATIO = 55.965; 
        //Change

        public static final double DRIVE_MOTOR_PCONVERSION = Math.PI * WHEEL_DIAMETER / DRIVE_MOTOR_GEAR_RATIO;
        public static final double TURN_MOTOR_PCONVERSION = 2 * Math.PI / TURN_MOTOR_GEAR_RATIO;
        public static final double DRIVE_MOTOR_VCONVERSION = DRIVE_MOTOR_PCONVERSION;
        public static final double TURN_MOTOR_VCONVERSION = TURN_MOTOR_PCONVERSION / 60.0;
        public static final double KP_TURNING = 0.7;

        public static final double DRIVETRAIN_MAX_SPEED = 5.266;
        public static final double DRIVETRAIN_MAX_ANGULAR_SPEED = 3.6 * Math.PI;

        //Teleop constraints
        public static final double TELE_DRIVE_MAX_SPEED = DRIVETRAIN_MAX_SPEED / 1;
        public static final double TELE_DRIVE_MAX_ANGULAR_SPEED = DRIVETRAIN_MAX_ANGULAR_SPEED / 1.5;
        public static final double TELE_DRIVE_MAX_ACCELERATION = 3;
        public static final double TELE_DRIVE_MAX_ANGULAR_ACCELERATION = 3;

        //Auton constraints
        public static final double AUTO_DRIVE_MAX_SPEED = DRIVETRAIN_MAX_SPEED / 1.5;
        public static final double AUTO_DRIVE_MAX_ANGULAR_SPEED = DRIVETRAIN_MAX_ANGULAR_SPEED / 5;
        public static final double AUTO_DRIVE_MAX_ACCELERATION = 3;
        public static final double AUTO_DRIVE_MAX_ANGULAR_ACCELERATION = Math.PI / 2;

        public static final double AUTO_kP_FRONT = 0.5;
        public static final double AUTO_kP_SIDE = 0.5;
        public static final double AUTO_kP_TURN = 0.2;

        public static final TrapezoidProfile.Constraints AUTO_TURN_CONTROLLER_CONSTRAINTS = new TrapezoidProfile.Constraints(
                AUTO_DRIVE_MAX_ANGULAR_SPEED,
                AUTO_DRIVE_MAX_ANGULAR_ACCELERATION);

        //Swerve Kinematics
        public static final double TRACK_WIDTH = Units.inchesToMeters(18.0);
        public static final double WHEEL_BASE = Units.inchesToMeters(17.0);
        public static final SwerveDriveKinematics DRIVE_KINEMATICS = new SwerveDriveKinematics(
            new Translation2d(WHEEL_BASE / 2, TRACK_WIDTH / 2),
            new Translation2d(WHEEL_BASE / 2, -TRACK_WIDTH / 2),
            new Translation2d(-WHEEL_BASE / 2, TRACK_WIDTH / 2),
            new Translation2d(-WHEEL_BASE / 2, -TRACK_WIDTH / 2)
        );
    }
}   
