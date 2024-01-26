/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import java.util.function.DoubleSupplier;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

public class ExampleSparkMax extends SubsystemBase 
{
  
  /* Motors/Encoders */
  CANSparkMax exampleMotor= Robot.exampleMotor;
  RelativeEncoder encoder = exampleMotor.getEncoder();
  
 
  /* Default Teleop Command (driven directly by joysticks) */
  public void TeleOpMotor(DoubleSupplier wristRotation) 
  {
    exampleMotor.set(wristRotation.getAsDouble());
  }


  /* Returns the Current Wrist Position */
  public double WristPosition(){
    return encoder.getPosition();
  }


  /* Sets Forward and Reverse Soft Limits for the Wrist */
  public void SetWristSoftLimits()
  {
    encoder.setPosition(0);
    exampleMotor.setSoftLimit(SoftLimitDirection.kForward, 200);
    exampleMotor.enableSoftLimit(SoftLimitDirection.kReverse, true);
    exampleMotor.setSoftLimit(SoftLimitDirection.kReverse, 0);
    exampleMotor.setIdleMode(IdleMode.kBrake);
    exampleMotor.enableSoftLimit(SoftLimitDirection.kForward, true);
  }


  /* Periodically Posts Wrist Position to SmartDashboard */
  public void UpdateSmartDashNums()
  {
    SmartDashboard.putNumber("ExampleMotor Position:", encoder.getPosition());
  }
  

  /* Stops the Wrist Motor */
  public void Stop()
  {
    exampleMotor.set(0);
  }


  public boolean isFinished() 
  {
    return true;
  }


public void RunSparkMax() {
}

}
