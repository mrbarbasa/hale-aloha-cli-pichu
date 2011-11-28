package edu.hawaii.halealohacli.command;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.xml.datatype.XMLGregorianCalendar;
import org.wattdepot.client.WattDepotClient;
import org.wattdepot.util.tstamp.Tstamp;
import edu.hawaii.halealohacli.Main;

/**
 * Retrieves a certain source's energy data
 * on a certain day. Data starts at 12 a.m.
 * of that day to 12 a.m.of the next day.
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
   * Creates a new instance of the daily-energy command.
   */
  public DailyEnergy() {
    this.output = "";
  }
  
  /**
   * Creates a new instance of the daily-energy command.
   * 
   * @param tower - name of the tower or lounge.
   * @param day - the date specified by user.
   */
  public DailyEnergy(String tower, String day) {
    this.client = Main.CLIENT;
    this.tower = tower;
    this.day = day;
    this.dailyEnergy = 0;
  }
  
  /**
   * Checks if user entered a date that's out of the range.
   * Example, if user entered 11/22, but today is 11/21 then
   * the server doesn't have data of that date yet.
   * 
   * @param today - today's date.
   * @param inputDate - date that user entered.
   * @return - value indicating if user entered date that's out of range.
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
   * @return the amount of energy used by the source.
   */
  public double getDailyEnergy() {
    return this.dailyEnergy;
  }
  
  /**
   * Runs this command.
   * 
   * @throws Exception - error.
   */
  public void run() throws Exception {
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
    String description = "daily-energy\n";
    description += "  Usage: daily-energy [tower | lounge] [date]\n";
    description += "    Retrieves the energy consumed by the source from\n";
    description += "    the date specified by the user.\n\n";
    return description;
  }

}