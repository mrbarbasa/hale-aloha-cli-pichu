package edu.hawaii.halealohacli.command;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.xml.datatype.XMLGregorianCalendar;
import org.wattdepot.client.WattDepotClient;
import org.wattdepot.resource.source.jaxb.Source;
import org.wattdepot.util.tstamp.Tstamp;
import edu.hawaii.halealohacli.Main;

/**
 * Retrieves energy consumption from the sources
 * and ranks them from least to most energy consumed.
 * 
 * @author Team Pichu
 */
public class RankTowers implements Command {

  private WattDepotClient client;
  private SortedSet<SensorWatts> energies = new TreeSet<SensorWatts>();
  private String output;
  private String start, end;
  
  /**
   * Creates a new instance of the rank-towers command.
   */
  public RankTowers() {
    this.output = "";
  }
  
  /**
   * Creates a new instance of the rank-towers command.
   * 
   * @param start the start time specified
   * @param end the end time specified
   * @throws Exception - error.
   */
  public RankTowers(String start, String end) throws Exception {
    this.client = Main.CLIENT;
    this.start = start;
    this.end = end;
    
  } 
  
  /**
   * Retrieves each tower's energy and
   * sorts data from least to most energy consumed.
   * 
   * @throws Exception - error.
   */
  private void getEnergy() throws Exception {
    // Create a Date object needed to acquire the starting and ending timestamp
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    Date startDate = sdf.parse(this.start);
    Date endDate = sdf.parse(this.end);
    // Set the startTime to 00:00:00.000 on the date given by the user
    XMLGregorianCalendar startTime = Tstamp.makeTimestamp(startDate.getTime());
    startTime.setTime(0, 0, 0, 0);
    XMLGregorianCalendar endTime = Tstamp.makeTimestamp(endDate.getTime());
    endTime.setTime(0, 0, 0, 0);
    
    List<Source> sources = client.getSources();
    
    //Iterate through the sources and search for a tower name 
    //without a dash in it
    for (int i = 0; i < sources.size(); i++) {
      String name = sources.get(i).getName();
      
      if (!(name.contains("-"))) {
        double energy = client.getEnergyConsumed(name, startTime, endTime, 0);
        energies.add(new SensorWatts(name, energy / 1000));
      }
    }
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
   * Runs this command.
   * 
   * @throws Exception - error.
   */
  public void run() throws Exception {
    Date today = new Date();
    Date startDate = new SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(this.start);
    Date endDate = new SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(this.end);
    
    if (checkDate(today.getTime(), startDate.getTime()) == 1 ||
        checkDate(today.getTime(), endDate.getTime()) == 1) {
      this.output = "Too early to retrieve data for the dates entered";
    }
    else {
      getEnergy();
      this.output = "For the interval " + start + " to " + end;
      this.output += " energy consumption by tower was: \n\n";
      for (SensorWatts watt : energies) {
        this.output += String.format("%-20s    %.1f\n", watt.getSourceName(), 
                                     watt.getWatts());
      }
    }
  }

  /**
   * Returns a string representation of the output of this command.
   * 
   * @return the output of this command.
   */
  @Override
  public String getOutput() {
    return this.output;
  }
  
  /**
   * Retrieves a description of this command and its functionality.
   * 
   * @return a description of this command.
   */
  @Override
  public String getHelp() {
    String description = "rank-towers\n";
    description += "  Usage: rank-towers [start] [end]\n";
    description += "    Retrieves a list in sorted order from least to most energy consumed\n";
    description += "    between the [start] and [end] dates specified by the user.\n\n";
    return description;
  }

}
