// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import java.text.DecimalFormat;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DrivetrainSubsystem;

public class DriveCommand extends Command {
  /** Creates a new SwerveDrive. */
  private RobotContainer robotContainer = new RobotContainer();
  DrivetrainSubsystem _driveTrain;
  public DriveCommand(DrivetrainSubsystem drivetrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    _driveTrain = drivetrain;
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    robotContainer.m_DriveTrain.swerveDrive(
      -robotContainer.controller.getLeftY(), 
      -robotContainer.controller.getLeftX(), 
      -robotContainer.controller.getRightX(), 
      !robotContainer.controller.getRawButton(XboxController.Button.kA.value),
      true);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    robotContainer.m_DriveTrain.stopModules();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}