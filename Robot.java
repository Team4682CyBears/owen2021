/*
.______   .______          ___   ____    ____  _______ .______     ______   .___________.    _______.
|   _  \  |   _  \        /   \  \   \  /   / |   ____||   _  \   /  __  \  |           |   /       |
|  |_)  | |  |_)  |      /  ^  \  \   \/   /  |  |__   |  |_)  | |  |  |  | `---|  |----`  |   (----`
|   _  <  |      /      /  /_\  \  \      /   |   __|  |   _  <  |  |  |  |     |  |        \   \    
|  |_)  | |  |\  \----./  _____  \  \    /    |  |____ |  |_)  | |  `--'  |     |  |    .----)   |   
|______/  | _| `._____/__/     \__\  \__/     |_______||______/   \______/      |__|    |_______/    
                                        The Team Strikes Back! 
                                            -frosty :D
*/

package frc.robot;

//imports stuff 
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.GenericHID;



//declares!
public class Robot extends TimedRobot {
  protected DifferentialDrive rightWheels;
  protected DifferentialDrive leftWheels;
  
  
  //config
  public static final int intakePort = 0;
  public static final int liftMotorPort = 5; 
  public static final int climberMotorPort = 6; 

  public static final int armSolenoidUpPort = 1;
  public static final int armSolenoidDownPort = 0;

  public static final int compressorOnePort = 2; 

  //morots 
  private PWMVictorSPX inTake; 
  private PWMVictorSPX liftMotor;
  private PWMVictorSPX climbMotor;
 

  //human interface devices
  private GenericHID xBox;
  protected Joystick leftJoystick;
  protected Joystick rightJoystick;

  //prenumatics 
  private Compressor compressorOne;
  private Solenoid armSolenoidUp;
  private Solenoid armSolenoidDown; 
  public boolean compressorStatus; 

  @Override
  public void robotInit() {
    
    //human inferface devies
    leftJoystick = new Joystick(0);
    rightJoystick = new Joystick(1);
    xBox = new XboxController(2);

    //left and right motor sets
    rightWheels = new DifferentialDrive(new PWMVictorSPX(4), new PWMVictorSPX(2));
    leftWheels = new DifferentialDrive(new PWMVictorSPX (1), new PWMVictorSPX(3));

    //doot 
    inTake = new PWMVictorSPX(intakePort); 
    liftMotor = new PWMVictorSPX(liftMotorPort);
    climbMotor = new PWMVictorSPX(climberMotorPort);

    //preumatics 
    compressorOne = new Compressor(compressorOnePort);
    armSolenoidUp = new Solenoid(armSolenoidUpPort);
    armSolenoidDown= new Solenoid(armSolenoidDownPort);
    compressorOne.setClosedLoopControl(true);
    armSolenoidUp.set(true);
    armSolenoidDown.set(false);
    
    boolean pressureSwitch = compressorOne.getPressureSwitchValue();
    boolean compressorStatus = compressorOne.enabled();
  }

   
  @Override
  public void teleopPeriodic() {


    //arcade drive 
    rightWheels.tankDrive(rightJoystick.getX(),rightJoystick.getY());
    leftWheels.tankDrive(leftJoystick.getY(),leftJoystick.getX());
    

    
    //climber
    if (xBox.getRawButton(1)) {
      climbMotor.set(1); 
    }else{
      climbMotor.set(0);
    }

    if (xBox.getRawButton(4)){
      climbMotor.set(-1); 
    } else {
      climbMotor.set(0);
    }

    if (xBox.getRawButton(7)){
      climbMotor.set(0.3);
    } else {
      climbMotor.set(0);
    }

    //intake
    if (xBox.getRawButton(5)){
      inTake.set(1);
    }else{
      inTake.set(0);
    }
 
    if (xBox.getRawButton(6)){
      inTake.set(-1);
    }else{
      inTake.set(0);
    }
    

    //armLifter
    if (xBox.getRawButton(3)){
      liftMotor.set(2); 

    }else{liftMotor.set(0);
    }

    if (xBox.getRawButton(2)){
      liftMotor.set(-1); 
    } else {
      liftMotor.set(0);
    }
    
    //armLifter prenumatics 
    if (xBox.getRawButton(3)){
      armSolenoidUp.set(true);
      armSolenoidDown.set(false);
    }

    if (xBox.getRawButton(2)){
      armSolenoidDown.set(true);
      armSolenoidUp.set(false);
    }

  }






}
  
