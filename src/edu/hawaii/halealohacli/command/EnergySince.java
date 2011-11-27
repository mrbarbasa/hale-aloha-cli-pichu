package edu.hawaii.halealohacli.command;

import java.util.Date;
import java.util.Locale;
import javax.xml.datatype.XMLGregorianCalendar;
import org.wattdepot.client.WattDepotClient;
import org.wattdepot.util.tstamp.Tstamp;
import edu.hawaii.halealohacli.Main;
import java.text.SimpleDateFormat;

/**
 * Retrieves the energy consumed by a source from the date
 * specified by the user to the time of the latest sensor data.
 * 
 * @author Team Pichu
 */
public class EnergySince implements Command {
  
  private String tower;
  private String date;
  private WattDepotClient client;
  private String output;
  private double energySince;
  
  /**
   * Creates a new instance of the energy-since command.
   * 
   * @param tower the tower specified
   * @param date the date specified
   */
  public EnergySince(String tower, String date) {
    this.client = Main.CLIENT;
    this.tower = tower;
    this.date = date;
    this.energySince = 0;
  }
  
  /**
   * Returns the value of the energy consumed since the date specified.
   * 
   * @return the double value of the energy consumed
   */
  public double getEnergySince() {
    return this.energySince;
  }
  
  /**
   * Parses a XMLGregorianCalendar object of format "yyyy-MM-dd'T'HH:mm:ss.mls-HH:mm"
   * (where the last "-HH:mm" is simply the time zone)
   * into "yyyy-MM-dd  HH:mm:ss.mls" and returns the parsed result as a string.
   * 
   * @param dateTime the XMLGregorianCalendar object to parse
   * @return a string representation of the date and time
   */
  private String parseDateTime(XMLGregorianCalendar dateTime) {
    String dt = String.valueOf(dateTime);
    int indexT = dt.indexOf('T'); // 'T' separates the date from the time
    String parsedResult = dt.substring(0, indexT) + "  "; // Acquire the date "yyyy-MM-dd  "
    parsedResult += dt.substring(indexT + 1, dt.lastIndexOf('-')); // Add "HH:mm:ss.mls"
    return parsedResult;
  }
  
  /**
   * Runs this command.
   * Saves the total energy consumed in kWh since the start date
   * at 00:00:00.000 to the date and time of the latest sensor data.
   * 
   * @throws Exception If problems occur in retrieving data from WattDepot or the
   * start date provided by the user is after the date of the latest sensor data.
   */
  public void run() throws Exception {
    // Create a Date object needed to acquire the starting timestamp
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    Date startDate = sdf.parse(this.date);
    
    // Set the startTime to 00:00:00.000 on the date given by the user
    XMLGregorianCalendar startTime = Tstamp.makeTimestamp(startDate.getTime());
    startTime.setTime(0, 0, 0, 0);
    
    // Set the endTime to the date and time given by the timestamp on the latest sensor data
    XMLGregorianCalendar endTime = this.client.getLatestSensorData(this.tower).getTimestamp();
    double energy = this.client.getEnergyConsumed(this.tower, startTime, endTime, 0);
    energy /= 1000; // Because 1 watt-hour x (1 kWh / 1000 watt-hours) = 0.001 kWh
    this.energySince = energy;
    
    // Save the output // energy-since Lehua-E 2011-11-14
    this.output = "Total energy consumption by " + this.tower + "\n";
    this.output += "  from  " + this.parseDateTime(startTime) + "\n";
    this.output += "  to    " + this.parseDateTime(endTime) + "\n";
    this.output += "  is:   " + String.format("%.1f",energy)  + " kWh";
  }
  
  /**
   * Returns a string representation of the output of calling this command.
   * 
   * @return the output of calling this command
   */
  public String getOutput() {
    return this.output;
  }
  
  /**
   * Retrieves a description of this command and its functionality.
   * 
   * @return a description of this command
   */
  public String description() {
    String description = "Usage: energy-since [tower | lounge] [date]\n";
    description += "  Retrieves the energy consumed by a source from the date\n";
    description += "  specified by the user to the time of the latest sensor data.\n\n";
    return description;
  }
  
}
