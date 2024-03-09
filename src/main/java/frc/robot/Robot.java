// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends TimedRobot {

  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;

   // We created a timer to use for autonomous
   Timer timer;

   //String to hold option for autonomous
   String autonomousOption;

   //Gives the options for autonomous
   public static SendableChooser<String> sendableChooser;


  // This method is run when the robot is first started up. Anything that you want to be "started" when the robot is turned on
  // (such as a timer, gyroscope calibration, etc.) should be made here.
  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();

    // The timer has been instatiated.
    timer = new Timer();

        
    // This creastes the options for the autonomous
    sendableChooser = new SendableChooser<String>();
    sendableChooser.setDefaultOption("Move Foward","foward");
    SmartDashboard.putData("Autonomous", sendableChooser);
  }

  // This method is called every 20 ms (0.02 seconds, or 50 times a second). Use this for things like diagnostics that you want to 
  // run during disabled, autonomous, teleoperated, and test.
  @Override
  public void robotPeriodic() {
    // Runs the scheduler. This is responsible for polling buttons, adding newly-scheduled commands, running already
    // scheduled commands, removing finished or interrupted commands, and running subsystems periodic() methods. This
    // must be called from the robot's periodic block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  // This method is called once each time the robot enters Disabled mode.
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void disabledExit() {}

  // This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
  // We use this to start the time and select the option for autonomous
  @Override
  public void autonomousInit() {

    timer.reset();
    timer.start();

    autonomousOption = sendableChooser.getSelected();
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedules the autonomous command, which in this case has nothing in it as of now, it's just an example
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  // This method is called periodically during autonomous
  // This is where we add our autonomous
  @Override
  public void autonomousPeriodic() {
    //m_robotContainer.autoShootTop();
    //if(timer.get()>2){
      //m_robotContainer.autoShootBottom();
   // }
  }


  @Override
  public void autonomousExit() {
  }

  // This makes sure that the autonomous stops running when teleop starts running. If you want the 
  // autonomous to continue until interrupted by another command, remove this line or comment it out.
  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  // This method is called periodically during operator control
  // This is where we put out xboxcontrols method for controller bindings
  @Override
  public void teleopPeriodic() {
    m_robotContainer.xboxcontrols();
  }

  @Override
  public void teleopExit() {}

  // Cancels all running commands at the start of test mode
  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  // This method is called periodically during test mode
  @Override
  public void testPeriodic() {}

  //This method is called once when the robot is first started up
  @Override
  public void simulationInit() {}

  // This method is called periodically while in simulation
  @Override
  public void simulationPeriodic() {}
  
  @Override
  public void testExit() {}
}
