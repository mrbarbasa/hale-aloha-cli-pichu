package edu.hawaii.halealohacli.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Pattern;
import org.wattdepot.client.WattDepotClient;
import edu.hawaii.halealohacli.Main;
import edu.hawaii.halealohacli.processor.Processor;

/**
 * Retrieves the current power data
 * from the source specified by user.
 * 
 * @author Team Pichu
 */
public class CurrentPower implements Command {
  
  private String tower;
  private WattDepotClient client;
  private String output;
  private double currentPower;
  
  /**
   * The command current-power takes 1 argument.
   */
  public static final int ARGS = 1;
  
  /**
   * Creates a new instance of the current-power command.
   */
  public CurrentPower() {
    this.output = "";
  }
  
  /**
   * Creates a new instance of the current-power command.
   * 
   * @param tower the tower or lounge specified
   * @throws InvalidArgumentsException If the arguments supplied by the user are invalid.
   */
  public CurrentPower(String tower) throws InvalidArgumentsException {
    if (CurrentPower.checkArgs(tower)) {
      this.tower = tower;
      this.client = Main.CLIENT;
      this.currentPower = 0;
    }
    else {
      throw new InvalidArgumentsException();
    }
  }
  
  /**
   * Returns true if the argument to current-power is valid.
   * 
   * Note that for some odd reason, PMD throws an error just
   * for CurrentPower and none of the other commands.
   * The ConstructorCallsOverridableMethod error is only
   * relieved by making this particular method static
   * for this Command class only.
   * Because of this oddity, this method cannot be placed
   * in the Command interface; otherwise, it should be there
   * (but this particular static declaration prevents it).
   * 
   * @param args the arguments to be checked for validity
   * @return true if all arguments are valid; false otherwise
   */
  public static boolean checkArgs(String... args) {
    boolean valid = false;
    String lounge = "-[A-E]";
    if ((Processor.ILIMA).equals(args[0]) 
        || Pattern.matches(Processor.ILIMA + lounge, args[0])
        || (Processor.LEHUA).equals(args[0]) 
        || Pattern.matches(Processor.LEHUA + lounge, args[0])
        || (Processor.LOKELANI).equals(args[0]) 
        || Pattern.matches(Processor.LOKELANI + lounge, args[0])
        || (Processor.MOKIHANA).equals(args[0]) 
        || Pattern.matches(Processor.MOKIHANA + lounge, args[0])) {
      valid = true;
    }
    return valid;
  }
  
  /**
   * Returns the amount of power used by the source.
   * 
   * @return the amount of power used by the source
   */
  public double getCurrentPower() {
    return this.currentPower;
  }
  
  /**
   * Checks if the tower name is actually a word
   * instead of a date (e.g., 2011-10-31).
   * 
   * @return an integer value
   */
  public int checkTowerName() {
    try {
      SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
      date.parse(this.tower);
      return 0;
    }
    catch (ParseException e) {
     return 1;
    }
  
  }
  
  /**
   * Runs this command.
   * 
   * @throws Exception If problems occur in retrieving data from WattDepot.
   */
  @Override
  public void run() throws Exception {    
    if (checkTowerName() == 0) {
      this.output = "Tower name invalid.";
    }
    else {
      double power = this.client.getLatestPowerConsumed(this.tower);
      power /= 1000;
      this.currentPower = power;
      Calendar date = Calendar.getInstance(Locale.US);
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss", Locale.US);
   
      this.output = this.tower + "'s power as of " + df.format(date.getTimeInMillis());
      this.output += " was " + String.format("%.1f", power) + " kW.";
    }
  }
  
  /**
   * Returns a string representation of the output of calling this command.
   * 
   * @return the output of calling this command
   */
  @Override
  public String getOutput() {
    return this.output;
  }
  
  /**
   * Retrieves a description of this command and its functionality.
   * 
   * @return a description of this command and its functionality
   */
  @Override
  public String getHelp() {
    String description = "current-power\n";
    description += "  Usage: current-power [tower | lounge]\n";
    description += "    Retrieves the current power of the particular source.";
    return description;
  }
  
}
