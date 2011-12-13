package edu.hawaii.halealohacli.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;
import org.wattdepot.client.WattDepotClient;
import javax.xml.datatype.XMLGregorianCalendar;
import org.wattdepot.util.tstamp.Tstamp;
import edu.hawaii.halealohacli.Main;
import edu.hawaii.halealohacli.processor.Processor;

/**
 * This command defines [date] as the "baseline" day for [tower | lounge].  [date] is 
 * an optional argument in YYYY-MM-DD format and defaults to yesterday.  When this command 
 * is executed, the system should obtain and save the amount of energy used during each of 
 * the 24 hours of that day for the given tower or lounge.  These 24 values define the 
 * baseline power for that tower or lounge for that one hour time interval.  For example, 
 * if lounge Ilima-A used 100 kWh of energy during the hour 6am-7am, then the baseline power 
 * during the interval 6am - 7am for Ilima-A is 100 kW. 
 *
 * @author David Wilkie
 */
public class SetBaseline implements Command {

  private String location;
  private String date;
  private WattDepotClient client;
  private String output = "";
  private double[] energies = new double[24];

  /**
   * The command energy-since takes 2 arguments.
   */
  public static final int ARGS = 2;
  
  /**
   * Creates a new instance of the energy-since command.
   * 
   * @param location the tower or lounge specified
   * @param date the date specified
   * @throws InvalidArgumentsException If the arguments supplied by the user are invalid.
   */
  public SetBaseline(String location, String date) throws InvalidArgumentsException {
    if (this.checkArgs(location, date)) {
      this.client = Main.CLIENT;
      this.location = location;
      this.date = date;
    }
    else {
      throw new InvalidArgumentsException();
    }
  }
  
  /**
   * Creates a new instance of the daily-energy command.
   */
  public SetBaseline() {
    this.output = "";
  }

  /**
   * Runs this command.
   * Saves the total energy consumed in kWh since the start date
   * at 00:00:00.000 to the date and time of the latest sensor data.
   * 
   * @throws Exception If problems occur in retrieving data from WattDepot or the
   * start date provided by the user is after the date of the latest sensor data.
   */
  @Override
  public void run() throws Exception {
    this.output += "Hour-by-hour energy consumption by " + this.location + "\n";
    // Create a Date object needed to acquire the starting timestamp
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    Date startDate = sdf.parse(this.date);
    int hour = 0;
    while (hour < 24) {
      // Set the startTime to 00:00:00.000 on the date given by the user
      XMLGregorianCalendar startTime = Tstamp.makeTimestamp(startDate.getTime());
      startTime.setTime(hour, 0, 0, 0);
      // Set the endTime to an hour after and get the energy data from the sensor
      XMLGregorianCalendar endTime = Tstamp.makeTimestamp(startDate.getTime());
      endTime.setTime(hour, 59, 59, 999);
      hour++;
      
      this.client.getLatestSensorData(this.location);      
      double energy = this.client.getEnergyConsumed(this.location, startTime, endTime, 0);
      // Because 1 watt-hour x (1 kWh / 1000 watt-hours) = 0.001 kWh
      energy /= 1000; 
      this.energies[hour - 1] = energy;

      // Save the output
      this.output += "  from  " + this.parseDateTime(startTime) + " to " + 
          this.parseDateTime(endTime) + " is:  " + String.format("%.1f",energy)  + "kWh\n";    
    }
  }
  
  /**
   * Returns true if the arguments to energy-since are valid.
   * 
   * @param args the arguments to be checked for validity
   * @return true if all arguments are valid; false otherwise
   */
  public boolean checkArgs(String... args) {
    boolean valid = false;
    String lounge = "-[A-E]";
    if (((Processor.ILIMA).equals(args[0]) 
        || Pattern.matches(Processor.ILIMA + lounge, args[0])
        || (Processor.LEHUA).equals(args[0]) 
        || Pattern.matches(Processor.LEHUA + lounge, args[0])
        || (Processor.LOKELANI).equals(args[0]) 
        || Pattern.matches(Processor.LOKELANI + lounge, args[0])
        || (Processor.MOKIHANA).equals(args[0]) 
        || Pattern.matches(Processor.MOKIHANA + lounge, args[0]))
        && Pattern.matches("[0-2][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]", args[1])) {
      valid = true;
    }
    return valid;
  }
  
  /**
   * Parses an XMLGregorianCalendar object of format "yyyy-MM-dd'T'HH:mm:ss.mls-HH:mm"
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
   * Checks if the tower name is actually a word
   * instead of a date (e.g., 2011-10-31).
   * 
   * @return an integer value
   */
  public int checkTowerName() {
    try {
      SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
      date.parse(this.location);
      return 0;
    }
    catch (ParseException e) {
     return 1;
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
    String description = "set-baseline\n";
    description += "  Usage: set-baseline [tower | lounge] [date]\n";
    description += "    This command defines [date] as the \"baseline\" day for tower or lounge.\n";
    description += "    When this command is executed, the system should obtain and save the]\n";
    description += "    amount of energy used during each of the 24 hours of that day for the\n";
    description += "    given tower or lounge.  ";
    return description;
  }  
  
  /**
   * Returns an array of the hourly energy consumption amounts in kilowatts.  
   * 
   * @return the energies
   */
  public double[] getEnergies() {
    return this.energies.clone();
  }
}
