package edu.hawaii.halealohacli.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import org.wattdepot.client.WattDepotClient;
import edu.hawaii.halealohacli.command.CurrentPower;
import edu.hawaii.halealohacli.command.DailyEnergy;
import edu.hawaii.halealohacli.command.EnergySince;
import edu.hawaii.halealohacli.command.RankTowers;

/**
 * Parses user input, determines what the command is,
 * and dispatches the particular command to be handled
 * by the corresponding class.
 * 
 * @author Team Pichu
 */
public class Processor {
  
  private WattDepotClient client;
  private String input;
  private List<String> components;
  private String command;
  private String output;
  
  // List of commands as constants
  private static final String CURRENT_POWER = "current-power";
  private static final String DAILY_ENERGY = "daily-energy";
  private static final String ENERGY_SINCE = "energy-since";
  private static final String RANK_TOWERS = "rank-towers";
  
  /**
   * Creates a new processor instance.
   * 
   * @param client the client connected to the WattDepot server
   * @param input the string input to process
   */
  public Processor(WattDepotClient client, String input) {
    this.client = client;
    this.input = input;
    this.components = new ArrayList<String>();
  }
  
  /**
   * Parses the input to the command line interface
   * into an array list.
   */
  private void parseInput() {
    // Trim off any leading or trailing whitespace
    this.input = this.input.trim();
    StringTokenizer st = new StringTokenizer(this.input);
    while (st.hasMoreTokens()) {
      this.components.add(st.nextToken());
    }
    this.command = this.components.get(0);
  }
  
  /**
   * Determines which class corresponds to the command.
   */
  public void callCommand() {
    if ((CURRENT_POWER).equals(this.command)) {
      CurrentPower currentPower = new CurrentPower(this.client, this.components.get(1));
      currentPower.run();
      // this.output = currentPower.getOutput();
    }
    else if ((DAILY_ENERGY).equals(this.command)) {
      DailyEnergy dailyEnergy = new DailyEnergy(this.client, this.components.get(1), 
          this.components.get(2));
      dailyEnergy.run();
      // this.output = dailyEnergy.getOutput();
    }
    else if ((ENERGY_SINCE).equals(this.command)) {
      EnergySince energySince = new EnergySince(this.client, this.components.get(1), 
          this.components.get(2));
      try {
        energySince.run();
        this.output = energySince.getOutput();
      }
      catch (Exception e) {
        this.output = "No data received.";
      }
    }
    else if ((RANK_TOWERS).equals(this.command)) {
      RankTowers rankTowers = new RankTowers(this.client, this.components.get(1), 
          this.components.get(2));
      rankTowers.run();
      // this.output = rankTowers.getOutput();
    }
    else { // TODO
      System.out.println("This command is invalid.");
    }
  }
  
  /**
   * Runs all the necessary tasks for this class.
   */
  public void run() {
    this.parseInput();
    this.callCommand();
    System.out.println(this.output);
  }
  
}