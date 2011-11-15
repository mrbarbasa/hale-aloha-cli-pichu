package edu.hawaii.halealohacli.command;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import javax.xml.datatype.XMLGregorianCalendar;
import org.wattdepot.client.WattDepotClient;
import org.wattdepot.util.tstamp.Tstamp;

/**
 * Retrieves a certain source's energy data
 * on a certain day. Data starts at 12 a.m.
 * to 11:59 p.m. on that day.
 * 
 * @author Team Pichu
 */
public class DailyEnergy {
  
  /**
   * Creates a new instance of the current-power command.
   * Just a placeholder for the code below.
   */
  public DailyEnergy() {
    // TODO
  }
  
  /**
   * Creates a new instance of the daily-energy command.
   * 
   * @param tower the tower specified
   * @param date the date specified
   */
  public DailyEnergy(String tower, String date) {
    // TODO
  }
  
  /**
   * Runs this command.
   */
  public void run() {
    // TODO
  }
  
  /**
   * Checks if the connection is successfully
   * connected to the server.
   * 
   * @param client - WattDepotClient object.
   * @param address - url address to the server.
   */
  public void checkConnection(WattDepotClient client, String address) {
    
    if (!client.isHealthy()) {
      System.out.format("Could not connect to: %s%n", address);
    }
    
  }
  
  /**
   * Retrieves the energy of the certain day
   * specified by the user. Data range is from
   * 12 a.m. to 11:59 p.m. of that day.
   *
   * @param client - WattDepotClient object.
   * @param source - name of the source.
   * @param date - they day to retrieve the data.
   * @return - energy data in watt-hour.
   * @throws Exception - error.
   */
  public double getEnergy(WattDepotClient client, String source, String date) throws Exception {
    
    Calendar midNight = Calendar.getInstance(Locale.US);
    Calendar endOfDay = Calendar.getInstance(Locale.US);
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    XMLGregorianCalendar start;
    XMLGregorianCalendar end;
    double energy = 0;
    
    // Setting the day and that day's midnight time
    midNight.setTime(df.parse(date));
    midNight.set(Calendar.HOUR, 0);
    midNight.set(Calendar.MINUTE, 0);
    midNight.set(Calendar.SECOND, 0);
    start = Tstamp.makeTimestamp(midNight.getTimeInMillis());
    
    // Setting the day and that day's time to 11:59
    endOfDay.setTime(df.parse(date));
    endOfDay.set(Calendar.HOUR, 23);
    endOfDay.set(Calendar.MINUTE, 59);
    endOfDay.set(Calendar.SECOND, 59);
    end = Tstamp.makeTimestamp(endOfDay.getTimeInMillis());
    
    energy = client.getEnergyConsumed(source, start, end, 60);
  
    
    return energy;
  }
  
  
  /**
   * Reads in source name and the date to
   * retrieve the energy.
   * 
   * @param args - source name and the date.
   * @throws Exception - error.
   */
  public static void main(String[] args) throws Exception {
    DailyEnergy de = new DailyEnergy();
    String source = args[1];
    String date = args[2];
    String url = "http://server.wattdepot.org:8190/wattdepot/";
    
    WattDepotClient client = new WattDepotClient(url);
    // Check for success of connection to server
    de.checkConnection(client, url);
    
    double energy = de.getEnergy(client, source, date) / 1000;
    
    System.out.format("%s's energy consumption for %s was: %.2f kWh", source, date, energy);
  }

}
