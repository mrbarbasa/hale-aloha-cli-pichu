package edu.hawaii.halealohacli.command;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import org.wattdepot.client.WattDepotClient;


/**
 * Retrieves the current power data
 * from the source specified by user.
 * 
 * @author Team Pichu
 */
public class CurrentPower {
  

  /**
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
   * Reads in source name and retrieve the 
   * current power data of that source.
   * 
   * @param args - source name.
   * @throws Exception - error.
   */
  public static void main(String[] args) throws Exception {
    CurrentPower cp = new CurrentPower();
    //Current day
    Calendar current = Calendar.getInstance(Locale.US);
    long time = current.getTimeInMillis();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss", Locale.US);
    String url = "http://server.wattdepot.org:8190/wattdepot/";
    //Name of source to retrieve data from
    String source = args[1];
    
    WattDepotClient client = new WattDepotClient(url);
    //Check for success of connection to server
    cp.checkConnection(client, url);
    
    double power = client.getLatestEnergyConsumedToDate(source) / 1000;
    System.out.format("%s power as of %s was: %.2f kW", source, df.format(time), power);
    
  }
}
