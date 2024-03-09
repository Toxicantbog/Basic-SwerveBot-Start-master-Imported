package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class HangingSubsystem extends SubsystemBase {
    private CANSparkMax hangingMotor = new CANSparkMax(16, MotorType.kBrushless);
    private static final HangingSubsystem hangingSubsytem = new HangingSubsystem();

    public static HangingSubsystem getInstance(){
        return hangingSubsytem;
    }

    public HangingSubsystem(){
        hangingMotor.setSmartCurrentLimit(80);
    }

    public void pullUp(){
        hangingMotor.set(0.4);
    }

    public void pullDown(){
        hangingMotor.set(-0.4);
    }
    public void stopPull(){
        hangingMotor.set(0);
    }
}
