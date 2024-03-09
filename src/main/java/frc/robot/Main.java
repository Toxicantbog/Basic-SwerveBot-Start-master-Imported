// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
/*
  Code written in Spring 2023 by Rafael Cavazos(Comp Sci teacher), Hector Corpus(Jr), 
  and Aiden Iao(Jr). Questions should be directed to these individuals, if they are still around.

  Special thanks to Team 8019 Patriot Engineering and Team 2583 Orange Dynamites
  for their help with our coding. Thank yhttps://maven.ctr-electronics.com/release/com/ctre/phoenix/Phoenix5-frc2023-latest.jsonou to Vector for helping us with parts for the swerve drive.

  This is a Swerve Drive Robot that uses MK4i swerve module wheels for navigation. We use a CommandXboxController
  to drive it. We can add on to this code, this is just a basis for swerve drive.

  Online imports
  https://software-metadata.revrobotics.com/REVLib-2023.json
  https://maven.ctr-electronics.com/release/com/ctre/phoenix/Phoenix5-frc2023-latest.json
  https://raw.githubusercontent.com/SwerveDriveSpecialties/swerve-lib/master/SdsSwerveLib.json
  https://dev.studica.com/releases/2023/NavX.json
*/

package frc.robot;

import edu.wpi.first.wpilibj.RobotBase;

public final class Main {
  private Main() {}

  public static void main(String... args) {
    RobotBase.startRobot(Robot::new);
  }
}
