package edu.hawaii.halealohacli.command;

import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;
import org.wattdepot.client.BadXmlException;
import org.wattdepot.client.MiscClientException;
import org.wattdepot.client.NotAuthorizedException;
import org.wattdepot.client.ResourceNotFoundException;
import org.wattdepot.client.WattDepotClient;
import org.wattdepot.util.tstamp.Tstamp;
import edu.hawaii.halealohacli.Main;

/**
 * This command prints out a timestamp and the current power for [tower | lounge] 
 * every [interval] seconds.  [interval] is an optional integer greater than 0 
 * and defaults to 10 seconds. Entering any character (such as a carriage return) 
 * stops this monitoring process and returns the user to the command loop.  
 *
 * @author Russell Vea
 */
public class MonitorPower implements Command {

  private String tower;
  private WattDepotClient client;
  private int interval;
  private double latestOutput;

  /**
   * Instantiates a new MonitorPower class.
   * @param tower the tower to query
   * @param interval an optional interval
   */
  public MonitorPower(String tower, int... interval) {
    this.client = Main.CLIENT;
    this.tower = tower;
    if (interval[0] == 0) {
      this.interval = 10000;
    } 
    else {
      this.interval = interval[0] * 1000;
    }
  }

  /**
   * Creates a timer and a new MonPow class.
   * Schedules the timer task and stays in the loop
   * until user enters input, then cancels the timer.
   * @throws Exception upon error with the server
   */
  @Override public void run() throws Exception {
    Timer timer = new Timer();
    MonPow monmon = new MonPow(this.tower, this.client);
    timer.scheduleAtFixedRate(monmon, 0, this.interval);
    while (System.in.available() == 0) {
      this.latestOutput = monmon.getPowerConsumed();
    }
    timer.cancel();
    timer.purge();
  }

  /**
   * Not sure what to do with this one.
   * @return the output I guess
   */
  @Override public String getOutput() {
    return Double.toString(this.latestOutput);
  }

  /**
   * For use with processor class.
   * @return usage string
   */
  @Override public String getHelp() {
    return "USAGE: monitor-power [tower | lounge] [interval]\n" +
        "This command prints out a timestamp and the current power for " +
        "[tower | lounge] every [interval] seconds.  [interval] is an optional" +
        " integer greater than 0 and defaults to 10 seconds. Entering any character" +
        " (such as a carriage return) stops this monitoring process and returns the" +
        "  user to the command loop";
  }
  
  /**
   * For debuggin porpoises.
   * @param args arguments
   * @throws Exception Upon error with the server
   */
  public static void main(String[] args) throws Exception {
    MonitorPower pow = new MonitorPower("Ilima", 5);
    pow.run();
  }
}

/**
 * An extension of TimerTask that queries the source for 
 * current power.
 *@author Russell Vea
 */
class MonPow extends TimerTask {

  private String sourceString;
  private WattDepotClient client;
  private double powerConsumed;
  
  /**
   * Instantiates an object.
   * @param sourceString the source to query
   * @param client the WattDepotClient
   */
  public MonPow(String sourceString, WattDepotClient client) {
    this.sourceString = sourceString;
    this.client = client;
  }
  
  @Override
  public void run() {
    if (this.client != null) {
      try {
        powerConsumed = this.client.getLatestPowerConsumed(this.sourceString);
        GregorianCalendar cal = Tstamp.makeTimestamp().toGregorianCalendar();
        System.out.format("Timestamp: %tD %tl:%tM        Power Consumed: %f%n", 
            cal, cal, cal, powerConsumed);
      }
      catch (NotAuthorizedException e) {
        e.printStackTrace();
      }
      catch (ResourceNotFoundException e) {
        e.printStackTrace();
      }
      catch (BadXmlException e) {
        e.printStackTrace();
      }
      catch (MiscClientException e) {
        e.printStackTrace();
      }
    }
  }

  /***
   * For future use with JUnit.
   * @return the last power consumed.
   */
  public double getPowerConsumed() {
    return this.powerConsumed;
  }
}

