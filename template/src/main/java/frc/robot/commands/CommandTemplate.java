/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;

//this command uses the example spark max subsystem to create a command that takes in a value and runs the motor according to that value. 
public class CommandTemplate extends CommandBase
{
    private final ExampleSparkMax s_SparkMax;
    private final DoubleSupplier motorSpeed;
  

    public CommandTemplate(ExampleSparkMax subsystem, DoubleSupplier motorSpeed)
    {
        s_SparkMax = subsystem;
        this.motorSpeed = motorSpeed;
        
        addRequirements(s_SparkMax);
    }

    @Override
    public void initialize(){}
    
    @Override
    public void execute() 
    {  
        s_SparkMax.TeleOpMotor(motorSpeed);
     }

    @Override
    public boolean isFinished() 
    {
        return true;
    }
}