package edu.hawaii.halealohacli.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;
import javax.xml.datatype.XMLGregorianCalendar;
import org.wattdepot.client.WattDepotClient;
import org.wattdepot.util.tstamp.Tstamp;
import edu.hawaii.halealohacli.Main;
import edu.hawaii.halealohacli.processor.Processor;

/**
 * Retrieves a certain source's energy data
 * on a certain day. Data starts at 12 a.m.
 * of that day to 12 a.m. of the next day.
 * 
 * @author Team Pichu
 */
public class DailyEnergy implements Command {
  
  private String tower;
  private String day;
  private WattDepotClient client;
  private String output;
  private double dailyEnergy;
  
  /**
   * The command daily-energy takes 2 arguments.
   */
  public static final int ARGS = 2;
  
  /**
   * Creates a new instance of the daily-energy command.
   */
  public DailyEnergy() {
    this.output = "";
  }
  
  /**
   * Creates a new instance of the daily-energy command.
   * 
   * @param tower the tower or lounge specified
   * @param day the date specified
   * @throws InvalidArgumentsException If the arguments supplied by the user are invalid.
   */
  public DailyEnergy(String tower, String day) throws InvalidArgumentsException {
    if (this.checkArgs(tower, day)) {
      this.client = Main.CLIENT;
      this.tower = tower;
      this.day = day;
      this.dailyEnergy = 0;
    }
    else {
      throw new InvalidArgumentsException();
    }
  }
  
  /**
   * Returns true if the arguments to daily-energy are valid.
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
   * Checks if the user entered a date that's out of the range.
   * For example, if the user entered 11/22, but today is 11/21,
   * then the server doesn't have data from 11/22 yet.
   * 
   * @param today today's date
   * @param inputDate date that the user entered
   * @return value indicating if the user entered a date that's out of range
   */
  public int checkDate(long today, long inputDate) {  
    if (inputDate > today || inputDate == today) {
      return 1;
    }
    
    return 0;
  }

  
  /**
   * Retrieves the energy of the certain day
   * specified by the user. Data range is from
   * 12 a.m. of specified day to 12 a.m. of the 
   * next day. In other words from start of day
   * to end of day.
   *
   * @return the amount of energy used by the source
   */
  public double getDailyEnergy() {
    return this.dailyEnergy;
  }
  
  /**
   * Runs this command.
   * Saves the total energy consumed in kWh since the start date
   * at 00:00:00.000 to the next day at 00:00:00.000.
   * 
   * @throws Exception If problems occur in retrieving data from WattDepot or the
   * start date provided by the user is after the date of the latest sensor data.
   */
  @Override
  public void run() throws Exception {    
    try {
      Date today = new Date();
      Date date = new SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(this.day);
      
      //The date is too far ahead of the current date
      if (checkDate(today.getTime(), date.getTime()) == 1) {
        this.output = "Too early to retrieve data for the date of " + this.day;
      }
      else {        
        //Setting time for beginning of the day
        XMLGregorianCalendar start = Tstamp.makeTimestamp(date.getTime());
        start.setTime(0, 0, 0, 0);
        
        //Setting the time for end of the day
        XMLGregorianCalendar end = Tstamp.incrementDays(Tstamp.makeTimestamp(date.getTime()), 1);
        end.setTime(0, 0, 0, 0);
        double energy = this.client.getEnergyConsumed(this.tower, start, end, 0);
        energy /= 1000;
        this.dailyEnergy = energy;
        
        this.output = this.tower + "'s energy consumption for ";
        this.output += this.day + " was: " + String.format("%.1f",energy) + " kWh.";
      }
    }
    catch (ParseException e) {
      this.output = "Invalid input.";
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
    String description = "daily-energy\n";
    description += "  Usage: daily-energy [tower | lounge] [date]\n";
    description += "    Retrieves the energy consumed by the source from\n";
    description += "    the date specified by the user.";
    return description;
  }

}
