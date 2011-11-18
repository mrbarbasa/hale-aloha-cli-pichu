package edu.hawaii.halealohacli.command;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import org.wattdepot.client.WattDepotClient;
import edu.hawaii.halealohacli.Main;


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
  
  /**
   * Creates a new instance of the current-power command.
   * 
   * @param tower the tower specified
   */
  public CurrentPower(String tower) {
    this.tower = tower;
    this.client = Main.CLIENT;
  }
  
  
  /**
   * 
   * @return amount of power used by the source.
   * @throws Exception - error.
   */
  public double getCurrentPower() throws Exception {
    return this.client.getLatestPowerConsumed(this.tower);
  }
  
  /**
   * Runs this command.
   * 
   * @throws Exception - error.
   */
  public void run() throws Exception {
    double power = this.getCurrentPower() / 1000;
    Calendar date = Calendar.getInstance(Locale.US);
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss", Locale.US);
 
    this.output = this.tower + "'s power as of " + df.format(date.getTimeInMillis());
    this.output += " was " + String.format("%.1f", power) + " kW.";
  }
  
  /**
   * Returns a string representation of the output of this command.
   * 
   * @return the output of this command.
   */
  public String getOutput() {
    return this.output;
  }
  
  
  /**
   * Retrieves a description of this command and its functionality.
   * 
   * @return a description of this command.
   */
  public String description() {
    
    String description = "Usage current-power [tower | lounge]\n";
    description += "  Retrieves the power of the particular source.";
    
    return description;
  }
}
