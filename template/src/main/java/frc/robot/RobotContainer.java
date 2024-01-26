package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.autos.AutoTemplate;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

public class RobotContainer {

    /* Controllers */
    //instantiate any controllers that you are using.
    private final Joystick driver = new Joystick(0);

    /* Drive Controls */
    //this is where you determine what buttons you want to use.
    private final int translationAxis = XboxController.Axis.kLeftY.value;
    private final int strafeAxis = XboxController.Axis.kLeftX.value;
    private final int rotationAxis = XboxController.Axis.kRightX.value;
    private final JoystickButton robotCentric = new JoystickButton(driver, XboxController.Button.kLeftBumper.value);
    private final JoystickButton zeroGyro = new JoystickButton(driver, XboxController.Button.kY.value);


    /* Operator Buttons */
    //this is for the controller that is not driving.

    /* Subsystems */
    //instantiate any subsystems you use here
    private final Swerve s_Swerve = new Swerve();


    /* Driving Speed Control */
    //this places a limit on the max speed. currently there is no limit, as it is going at 100%
    public static final double desiredSpeed = 1;
    public static double speedController = desiredSpeed;
    public static double turnController = speedController*0.4;



    /* The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {

        //Setting the default commands makes a certain command, such as joystick input, the primary driver of each subsystem. 
        //This makes any other command that requires the same subsystem, such as the ones in ConfigureButtonBindings(), interrupt the default command until it is finished.

        //Cubing the input leads to smoother acceleration while still keeping the same maximum value
        //this is the code that makes the robot drive with the joysticks in teleop
        s_Swerve.setDefaultCommand(
            new TeleopSwerve(
                s_Swerve, 
                () -> speedController*Math.pow(-driver.getRawAxis(translationAxis), 3), 
                () -> speedController*Math.pow(-driver.getRawAxis(strafeAxis), 3), 
                () -> turnController*Math.pow(-driver.getRawAxis(rotationAxis), 3), 
                () -> robotCentric.getAsBoolean()
            )
        );

        //Periodically checks buttons and executes commands accordingly
        configureButtonBindings();
	}


    private void configureButtonBindings() {
        //here is where you add the buttons on your controller that you want to use.
        zeroGyro.onTrue(new InstantCommand(() -> s_Swerve.zeroGyro()));
        
    }
    

    //this is where you choose what autonomous to run. 
    public Command getAutonomousCommand() {
        return new AutoTemplate(s_Swerve);
    }
}
