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
  }
  
  /**
   * Retrieves the energy of the certain day
   * specified by the user. Data range is from
   * 12 a.m. of specified day to 12 a.m. of the 
   * next day. In other words from start of day
   * to end of day.
   *
   * @return the amount of energy used by the source.
   * @throws Exception - error.
   */
  public double getEnergy() throws Exception {
    
    Date date = new SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(this.day);
    //Setting time for beginning of the day
    XMLGregorianCalendar start = Tstamp.makeTimestamp(date.getTime());
    start.setTime(0, 0, 0);
    
    //Setting the time for end of the day
    XMLGregorianCalendar end = Tstamp.incrementDays(Tstamp.makeTimestamp(date.getTime()), 
                                                    1);
    end.setTime(0, 0, 0);
    
    return this.client.getEnergyConsumed(this.tower, start, end, 0);
  }
  
  
  
  
  
  /**
   * Runs this command.
   * 
   * @throws Exception - error.
   */
  public void run() throws Exception {
    
    double energy = this.getEnergy();
    
    this.output = this.tower + "'s energy consumption for ";
    this.output += this.day + " was: " + energy + " kWh.";
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
    String description = "Usage daily-energy [tower | lounge] date\n";
    description += "  Retrieves the energy consumed by the source from\n";
    description += "  the date specified by the user.";
    
    return description;
  }

}
