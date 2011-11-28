package edu.hawaii.halealohacli.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import edu.hawaii.halealohacli.command.CurrentPower;
import edu.hawaii.halealohacli.command.DailyEnergy;
import edu.hawaii.halealohacli.command.EnergySince;
import edu.hawaii.halealohacli.command.Help;
import edu.hawaii.halealohacli.command.Quit;
import edu.hawaii.halealohacli.command.RankTowers;

/**
 * Parses user input, determines what the command is,
 * and dispatches the particular command to be handled
 * by the corresponding class.
 * 
 * @author Team Pichu
 */
public class Processor {
  
  private String input;
  private List<String> components;
  private String command;
  private String output;
  private String test;
  
  // List of commands as constants
  private static final String CURRENT_POWER = "current-power";
  private static final String DAILY_ENERGY = "daily-energy";
  private static final String ENERGY_SINCE = "energy-since";
  private static final String RANK_TOWERS = "rank-towers";
  private static final String HELP = "help";
  private static final String QUIT = "quit";
  
  /**
   * Creates a new processor instance given a String input.
   * 
   * @param input the string input to process
   */
  public Processor(String input) {
    this.input = input;
    this.components = new ArrayList<String>();
    this.test = "fail";
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
  private void callCommand() {
    try {
      if ((CURRENT_POWER).equals(this.command)) {
        CurrentPower currentPower = new CurrentPower(this.components.get(1));
        currentPower.run();
        this.output = currentPower.getOutput();
        this.test = this.command;
      }
      else if ((DAILY_ENERGY).equals(this.command)) {
        DailyEnergy dailyEnergy = new DailyEnergy(this.components.get(1), 
            this.components.get(2));
        dailyEnergy.run();
        this.output = dailyEnergy.getOutput();
        this.test = this.command;
      }
      else if ((ENERGY_SINCE).equals(this.command)) {
        EnergySince energySince = new EnergySince(this.components.get(1), 
            this.components.get(2));
        energySince.run();
        this.output = energySince.getOutput();
        this.test = this.command;
      }
      else if ((RANK_TOWERS).equals(this.command)) {
        RankTowers rankTowers = new RankTowers(this.components.get(1), 
            this.components.get(2));
        rankTowers.run();
        this.output = rankTowers.getOutput();
        this.test = this.command;
      }
      else if ((HELP).equals(this.command)) {
        Help help = new Help();
        help.run();
        this.output = help.getOutput();
        this.test = this.command;
      }
      else if ((QUIT).equals(this.command)) {
        Quit quit = new Quit();
        quit.run();
        this.output = quit.getOutput();
        this.test = this.command;
      }
      else {
        this.output = "There is no such command.";
      }
    }
    catch (Exception e) {
      this.output = "No data received.";
    }
  }
  
  /**
   * Returns the test string for the TestProcessor class.
   * 
   * @return the test string
   */
  public String getTest() {
    return this.test;
  }
  
  /**
   * Runs all the necessary tasks for this class.
   */
  public void run() {
    this.parseInput();
    this.callCommand();
  }
  
  /**
   * Returns a string representation of the output received by the processor.
   * 
   * @return the output received by the processor
   */
  public String getOutput() {
    return this.output;
  }
  
}