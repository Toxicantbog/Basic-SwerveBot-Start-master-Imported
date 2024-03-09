package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SwerveConstants;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.Pigeon2;
import com.revrobotics.SparkMaxAlternateEncoder;
import com.revrobotics.SparkAbsoluteEncoder;

public class swervemodule extends SubsystemBase {
    private CANSparkMax driveMotor;
    private CANSparkMax turnmotor;
    private RelativeEncoder drivEncoder;
    private RelativeEncoder turnEncoder;
    private CANcoder absolutNcoder;
    private Pigeon2 turnNcoder;
    private PIDController turnController;
    private double absolutNcoderOffset;
    private boolean absolutNcoderReverse;
    private Rotation2d lastAngle;
    private DutyCycleOut driveMotorRequest = new DutyCycleOut(0.0);


    public swervemodule(int driveMotorID, int turnMotorID, boolean driveMotorReversed, boolean turnMotorReverse,
    int absolutNcoderID, double absolutNcoderOffset, boolean absolutNcoderReverse) {

        

        absolutNcoder = new CANcoder(absolutNcoderID);
        driveMotor = new CANSparkMax(driveMotorID, MotorType.kBrushless);
        turnmotor = new CANSparkMax(turnMotorID, MotorType.kBrushless);
        drivEncoder = driveMotor.getEncoder();
        turnEncoder = turnmotor.getEncoder();

        this.absolutNcoderOffset = absolutNcoderOffset;
        this.absolutNcoderReverse = absolutNcoderReverse;

        driveMotor.setInverted(driveMotorReversed);
        turnmotor.setInverted(turnMotorReverse);
        driveMotor.setIdleMode(IdleMode.kCoast);
        turnmotor.setIdleMode(IdleMode.kBrake);
        driveMotor.setSmartCurrentLimit(25);
        turnmotor.setSmartCurrentLimit(25);

        turnController = new PIDController(1, 0, 0);
        turnController.enableContinuousInput(-Math.PI, Math.PI);

        resetEncoders();
        lastAngle = getState().angle;

    }

    @Override 
    public void periodic() {}

    public double getAbsoluteEncoderAngle() {
        double angle = absolutNcoder.getAbsolutePosition().getValueAsDouble();
        angle -= absolutNcoderOffset;
        angle *= (2 * Math.PI);

        return angle * (absolutNcoderReverse ? -1.0 : 1.0);

    }
      public double getDriveMotorPosition(){
    return drivEncoder.getPosition()* SwerveConstants.DRIVE_MOTOR_PCONVERSION;
  }
  public double getDriveMotorVelocity(){
    return drivEncoder.getVelocity() * SwerveConstants.DRIVE_MOTOR_VCONVERSION;
  }
  public double getTurnMotorPosition(){
    return turnEncoder.getPosition() * SwerveConstants.TURN_MOTOR_PCONVERSION;
  }

    public void resetEncoders() {
        //
        absolutNcoder.setPosition(getAbsoluteEncoderAngle() / 1);
    }
    
    public void stop(){
        driveMotor.set(0);
        turnmotor.set(0);
      }

        public SwerveModuleState getState(){
    return new SwerveModuleState(getDriveMotorVelocity(), new Rotation2d(getTurnMotorPosition()));
  }
  private void setAngle(SwerveModuleState desiredState){
    Rotation2d angle = (Math.abs(desiredState.speedMetersPerSecond) <= (SwerveConstants.DRIVETRAIN_MAX_SPEED * 0.01)) ? lastAngle : desiredState.angle; //Prevent rotating module if speed is less then 1%. Prevents Jittering.
    
    turnmotor.set(turnController.calculate(getTurnMotorPosition(), desiredState.angle.getRadians()));
    lastAngle = angle;
  }
   public SwerveModulePosition getPosition(){
    return new SwerveModulePosition(getDriveMotorPosition(), new Rotation2d(getTurnMotorPosition()));
  }
}
