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
  private double currentPower;
  
  /**
   * Creates a new instance of the current-power command.
   */
  public CurrentPower() {
    this.output = "";
  }
  
  /**
   * Creates a new instance of the current-power command.
   * 
   * @param tower the tower specified
   */
  public CurrentPower(String tower) {
    this.tower = tower;
    this.client = Main.CLIENT;
    this.currentPower = 0;
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
   * Runs this command.
   * 
   * @throws Exception - error.
   */
  public void run() throws Exception {
    double power = this.client.getLatestPowerConsumed(this.tower);
    power /= 1000;
    this.currentPower = power;
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
  public String getHelp() {
    String description = "current-power\n";
    description += "  Usage: current-power [tower | lounge]\n";
    description += "    Retrieves the current power of the particular source.\n\n";
    return description;
  }
}
