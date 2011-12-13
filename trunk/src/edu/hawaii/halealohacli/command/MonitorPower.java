package edu.hawaii.halealohacli.command;

import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;
import javax.xml.datatype.XMLGregorianCalendar;
import org.wattdepot.client.BadXmlException;
import org.wattdepot.client.MiscClientException;
import org.wattdepot.client.NotAuthorizedException;
import org.wattdepot.client.ResourceNotFoundException;
import org.wattdepot.client.WattDepotClient;
import org.wattdepot.resource.source.jaxb.Source;
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

  private Timer timer;
  private String tower;
  private WattDepotClient client;
  private int interval;

  private final int ARGS = 1;
  
  public MonitorPower(String tower, int... interval) {
    this.client = Main.CLIENT;
    this.tower = tower;
    if(interval[0] == 0) {
      this.interval = 10000;
    } else {
      this.interval = interval[0] * 1000;
    }
  }

  @Override
  public void run() throws Exception {
    this.timer = new Timer();
    this.timer.scheduleAtFixedRate(new MonPow(this.tower, this.client), 0, this.interval);
    while (System.in.available() == 0) {
      //do nothing
    }
    this.timer.cancel();
    this.timer.purge();
  }

  @Override
  public String getOutput() {
    return null;
  }

  @Override
  public String getHelp() {
    return "USAGE: monitor-power [tower | lounge] [interval]\n" +
        "This command prints out a timestamp and the current power for " +
        "[tower | lounge] every [interval] seconds.  [interval] is an optional" +
        " integer greater than 0 and defaults to 10 seconds. Entering any character" +
        " (such as a carriage return) stops this monitoring process and returns the" +
        "  user to the command loop";
  }
  
  public static void main(String[] args) throws Exception{
    MonitorPower pow = new MonitorPower("Ilima", 5);
    pow.run();
  }
}

class MonPow extends TimerTask {

  private String sourceString;
  private WattDepotClient client;
  private double powerConsumed;
  
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
        System.out.format("Timestamp: %tD %tl:%tM        Power Consumed: %f%n", cal, cal, cal, powerConsumed);
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
  
  public double getPowerConsumed() {
    return this.powerConsumed;
  }

}

