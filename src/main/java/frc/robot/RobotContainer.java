// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.DrivetrainSubsystem;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
//import edu.wpi.first.wpilibj.AddressableLED;
//import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
//import edu.wpi.first.wpilibj.util.Color;
import frc.robot.Constants.DriveConstants; 
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.HangingSubsystem;
import frc.robot.commands.DriveCommand;

/*
 * This class is where the bulk of he robot should be declared. Since Command-based is a 
 * "declaritive" paradigm, very little robot logic should actually be handed in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, command, and trigger mappings) should be declared here.
 */

public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public final DrivetrainSubsystem m_DriveTrain = DrivetrainSubsystem.getInstance(); // This creates the swerve drive driveTrain
  private final ShooterSubsystem m_ShooterSubsystem = ShooterSubsystem.getInstance();
  private final HangingSubsystem m_HangingSubsystem = HangingSubsystem.getInstance();

  // private final Spark lightSpark = new Spark(0);
  
  //public final CommandXboxController cController = new CommandXboxController(0); // This is the controller we will be using.
  public final XboxController controller = new XboxController(0);

  //public final XboxController controller = new XboxController(1);
  // The container for the robot. Contains subsystems, OI devices, and commands
  public RobotContainer() {
    
    // Gets the number readings from the joysticks and sends those values to the DriveCommand to be used in chassisSpeeds to 
    // drive the robot. The left joystick drives you forwards, backwards,  strafe left, strafe right. The right joystick 
    // rotates the robot to "turn" right or left. We are multiplying by a number between 0 and 1 to slow down the speed of the wheels.
    // We can remove the multiplication to run at full speed.
    m_DriveTrain.setDefaultCommand(new DriveCommand(m_DriveTrain));
  }

  public static double deadband(double value, double deadband){
    if(Math.abs(value) > deadband){
      if(value > 0.0){
        return (value - deadband) / (1.0 - deadband);
      } else{
        return (value + deadband) / (1.0 - deadband);
      }
    } else{
      return 0.0;
    }
  }

  // Change the value of deadband in the parenthesis to increase the deadzone
  // A number between 0 and 1, bigger number means bigger deadzone
  private static double modifyAxis(double value){
    // deadband
    value = deadband(value, DriveConstants.Joystick_Deadband);
    // Square the axis
    value = Math.copySign(value * value, value);

    return value;
  }

  // This method is where we put our bindings for the xbox controller
  // We put the controls in if statements to run a method from other classes
  public void xboxcontrols(){

    if(checkToStopShooter()){
      m_ShooterSubsystem.stopShooter();
    }


    if(controller.getBButton()){
      m_ShooterSubsystem.intake();
    }

    if (controller.getXButton()){
      m_ShooterSubsystem.shootBottom();
    }

    if(controller.getYButton()){
      m_ShooterSubsystem.shootTop();
    }
  
    if(controller.getLeftBumper()){
      m_HangingSubsystem.pullUp();
    }

    if(controller.getRightBumper()){
      m_HangingSubsystem.pullDown();
    }
    
    if((controller.getRightBumper()==false)&&(controller.getLeftBumper()==false)){
      m_HangingSubsystem.stopPull();
    }

    if(controller.getRightStickButton()){
      m_ShooterSubsystem.shootSlow();
    }
    
    // if(controller.getLeftStickButton()) {
    //   // Toggles LED light between white or blue.
    //   double value = lightSpark.get();
    //   if(value == .93) {
    //     lightSpark.set(.87);
    //   } else {
    //     lightSpark.set(.93);
    //   }
    // }
  }

  
  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }

  // public void configureLights(double value) {
  //   System.out.println("Lights configured on channel: " + lightSpark.getChannel());
  //   lightSpark.set(value);
  // }

  public boolean checkToStopShooter(){
    if(controller.getYButton()){
      return false;
    }
    if(controller.getBButton()){
      return false;
    }
    if(controller.getXButton()){
      return false;
    }
    return true;
  }


  // These are the chassisSpeeds that we will use for autonomous
  private ChassisSpeeds chassisSpeedsFoward = new ChassisSpeeds(1.0, 0.0, 0.0);
  private ChassisSpeeds chassisSpeedsStop = new ChassisSpeeds(0.0, 0.0, 0.0);
  private ChassisSpeeds chassisSpeedsBack = new ChassisSpeeds(-1.0, 0.0, 0.0);
  private ChassisSpeeds chassisSpeedsTurn = new ChassisSpeeds(0, 0, 3.14);
  
  // These will be the methods used in autonomousperiodic
  // public void autoMoveFoward(){m_DriveTrain.swerveDrive();}
  // public void autoStop(){m_DriveTrain.drive(chassisSpeedsStop);}
  // public void autoMoveBack(){m_DriveTrain.drive(chassisSpeedsBack);}
  // public void autoRotate(){m_DriveTrain.drive(chassisSpeedsTurn);}
  // public void autoShootTop(){m_ShooterSubsystem.shootTop();}
  // public void autoShootBottom(){m_ShooterSubsystem.shootBottom();}
  // public void autoStopShooter(){m_ShooterSubsystem.stopShooter();}
}
