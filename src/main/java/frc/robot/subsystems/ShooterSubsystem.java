package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
//import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase{

    //This creates both shooting motors
    private CANSparkMax TopShooterMotor = new CANSparkMax(ShooterConstants.TOP_SHOOTER_MOTOR, MotorType.kBrushless);
    private CANSparkMax BottomShooterMotor = new CANSparkMax(ShooterConstants.BOTTOM_SHOOTER_MOTOR, MotorType.kBrushless);
    public ShooterSubsystem() {
        TopShooterMotor.setSmartCurrentLimit(80);
        BottomShooterMotor.setSmartCurrentLimit(80);

    }
    private static final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
    public static ShooterSubsystem getInstance(){
        return shooterSubsystem;
    }

    // idk why .set(num) on motors didn't work, but .setVoltage works
    // This is the method to shoot a node
    public void shootTop(){
        TopShooterMotor.set(ShooterConstants.ShootingSpeed);
    }

    public void shootBottom(){
        BottomShooterMotor.set(-ShooterConstants.ShootingSpeed);
    }

    public void shootSlow(){
        TopShooterMotor.set(ShooterConstants.SlowShootingSpeed);
        BottomShooterMotor.set(-ShooterConstants.SlowShootingSpeed);
    }

    // This is the method to take a node
    public void intake(){
        TopShooterMotor.set(ShooterConstants.IntakeSpeed);
        BottomShooterMotor.set(-ShooterConstants.IntakeSpeed);
    }

    // This is the method to stop the shooting motors
    public void stopShooter(){
        TopShooterMotor.set(0);
        BottomShooterMotor.set(0);
    }
}
