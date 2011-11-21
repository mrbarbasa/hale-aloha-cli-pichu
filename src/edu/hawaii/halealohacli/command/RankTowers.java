package edu.hawaii.halealohacli.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
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
 * 
 * 
 * @author Team Pichu
 */
public class RankTowers implements Command {

  private String[] towerList = null;
  private double[] towerEnergy;
  private WattDepotClient client;
  private String output;
  private String start, end;

  /**
   * Creates a new instance of the current-power command.
   * 
   * @param start the start time specified
   * @param end the end time specified
   * @throws MiscClientException for miscellaneous clients.
   * @throws BadXmlException for improperly formed xml strings.
   * @throws NotAuthorizedException if user is not authorized for server.
   * @throws ParseException if string cannot be parsed.
   * @throws ResourceNotFoundException if server is not available.
   */
  public RankTowers(String start, String end) throws NotAuthorizedException, BadXmlException,
      MiscClientException, ResourceNotFoundException, ParseException {
    this.client = Main.CLIENT;
    this.start = start;
    this.end = end;
    this.towerList = getTowers();
    this.towerEnergy = getEnergy();
  }

  /**
   * Retrieves the tower names from source list.
   * 
   * @return towerList, a string array of tower names.
   * @throws NotAuthorizedException if user is not authorized for server.
   * @throws BadXmlException for improperly formed xml strings.
   * @throws MiscClientException for miscellaneous clients.
   */
  @SuppressWarnings("null")
  private String[] getTowers() throws NotAuthorizedException, BadXmlException, MiscClientException {
    int towerCount = 0;
    for (Source tower : client.getSources()) {
      String name = tower.getName();
      if (!name.contains("-")) {
        towerCount++;
      }
    }
    String[] towerList = new String[towerCount];
    int i = 0;
    for (Source tower : client.getSources()) {
      String name = tower.getName();
      if (!name.contains("-")) {
        towerList[i] = tower.getName();
      }
      i++;
    }
    return towerList;
  }

  /**
   * Runs this command.
   * 
   * @throws ParseException if string cannot be parsed.
   * @throws MiscClientException for miscellaneous clients.
   * @throws BadXmlException for improperly formed xml strings.
   * @throws ResourceNotFoundException if server is not available.
   * @throws NotAuthorizedException if user is not authorized for server.
   */
  public void run() throws NotAuthorizedException, ResourceNotFoundException, BadXmlException,
      MiscClientException, ParseException {
    getEnergy();
    sortTowers();
    // Concatenates tower list into output message
    for (String tower : towerList) {
      output = output + "\n" + tower;
    }
  }

  /**
   * Retrieves each tower's energy.
   * 
   * @throws MiscClientException for miscellaneous clients.
   * @throws BadXmlException for improperly formed xml strings.
   * @throws ResourceNotFoundException if server is not available.
   * @throws NotAuthorizedException if user is not authorized for server.
   * @throws ParseException if string cannot be parsed.
   * @return towerEnergy, a double array of the energy for each tower in list.
   */
  private double[] getEnergy() throws NotAuthorizedException, ResourceNotFoundException,
      BadXmlException, MiscClientException, ParseException {
    // Create a Date object needed to acquire the starting and ending timestamp
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    Date startDate = sdf.parse(this.start);
    Date endDate = sdf.parse(this.end);
    // Set the startTime to 00:00:00.000 on the date given by the user
    XMLGregorianCalendar startTime = Tstamp.makeTimestamp(startDate.getTime());
    startTime.setTime(0, 0, 0, 0);
    XMLGregorianCalendar endTime = Tstamp.makeTimestamp(endDate.getTime());
    endTime.setTime(0, 0, 0, 0);

    for (int i = 0; i < towerList.length; i++) {
      towerEnergy[i] = client.getEnergyConsumed(towerList[i], startTime, endTime, 0);
    }
    return towerEnergy;
  }

  /**
   * Sorts towers by energy consumed.
   */
  private void sortTowers() {
    for (int i = 0; i < towerList.length; i++) {
      for (int j = 0; j < towerEnergy.length - 1; j++) {
        if (towerEnergy[j] > towerEnergy[j + 1]) {
          double tempEnergy = towerEnergy[j];
          String tempTower = towerList[j];
          towerEnergy[j + 1] = towerEnergy[j];
          towerList[j + 1] = towerList[j];
          towerEnergy[j] = tempEnergy;
          towerList[j] = tempTower;
        }
      }
    }

  }

  @Override
  public String getOutput() {
    return this.output;
  }

  @Override
  public String description() {
    String message =
        "Returns a list in sorted order from least to most energy consumed"
            + " between the [start] and [end] date (yyyy-mm-dd)";
    return message;
  }

}
