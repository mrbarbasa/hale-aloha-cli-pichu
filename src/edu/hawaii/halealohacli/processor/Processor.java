package edu.hawaii.halealohacli.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import edu.hawaii.halealohacli.command.CurrentPower;
import edu.hawaii.halealohacli.command.DailyEnergy;
import edu.hawaii.halealohacli.command.EnergySince;
import edu.hawaii.halealohacli.command.Help;
import edu.hawaii.halealohacli.command.InvalidArgumentsException;
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
  
  /**
   * Gives all commands access to the valid tower, Ilima.
   */
  public static final String ILIMA = "Ilima";
  
  /**
   * Gives all commands access to the valid tower, Lehua.
   */
  public static final String LEHUA = "Lehua";
  
  /**
   * Gives all commands access to the valid tower, Lokelani.
   */
  public static final String LOKELANI = "Lokelani";
  
  /**
   * Gives all commands access to the valid tower, Mokihana.
   */
  public static final String MOKIHANA = "Mokihana";
  
  // List of commands as constants
  private static final String CURRENT_POWER = "current-power";
  private static final String DAILY_ENERGY = "daily-energy";
  private static final String ENERGY_SINCE = "energy-since";
  private static final String RANK_TOWERS = "rank-towers";
  private static final String HELP = "help";
  private static final String QUIT = "quit";
  
  /**
   * Creates a new processor instance given a string input.
   * 
   * @param input the string input to process
   */
  public Processor(String input) {
    this.input = input;
    this.components = new ArrayList<String>();
    this.test = "fail";
  }
  
  /**
   * Parses the input to the command line interface into an array list.
   */
  private void parseInput() {
    // Trim off any leading or trailing whitespace
    this.input = this.input.trim();
    StringTokenizer st = new StringTokenizer(this.input);
    while (st.hasMoreTokens()) {
      this.components.add(st.nextToken());
    }
    if (this.components.size() > 0) {
      this.command = this.components.get(0);
    }
    else {
      this.command = this.input;
    }
  }
  
  /**
   * Determines which class corresponds to the command.
   * Handles errors by providing specific output messages to the user.
   */
  private void callCommand() {
    String expected = "Expected: ";
    String arguments = " argument(s)\n\n";
    try {
      if ((CURRENT_POWER).equals(this.command)) {
        if (this.components.size() == CurrentPower.ARGS + 1) {
          CurrentPower currentPower = new CurrentPower(this.components.get(1));
          currentPower.run();
          this.output = currentPower.getOutput();
          this.test = this.command;
        }
        else {
          this.output = expected + CurrentPower.ARGS + arguments;
          CurrentPower cp = new CurrentPower();
          this.output += cp.getHelp();
        }
      }
      else if ((DAILY_ENERGY).equals(this.command)) {
        if (this.components.size() == DailyEnergy.ARGS + 1) {
          DailyEnergy dailyEnergy = new DailyEnergy(this.components.get(1), 
              this.components.get(2));
          dailyEnergy.run();
          this.output = dailyEnergy.getOutput();
          this.test = this.command;
        }
        else {
          this.output = expected + DailyEnergy.ARGS + arguments;
          DailyEnergy de = new DailyEnergy();
          this.output += de.getHelp();
        }
      }
      else if ((ENERGY_SINCE).equals(this.command)) {
        if (this.components.size() == EnergySince.ARGS + 1) {
          EnergySince energySince = new EnergySince(this.components.get(1), 
              this.components.get(2));
          energySince.run();
          this.output = energySince.getOutput();
          this.test = this.command;
        }
        else {
          this.output = expected + EnergySince.ARGS + arguments;
          EnergySince es = new EnergySince();
          this.output += es.getHelp();
        }
      }
      else if ((RANK_TOWERS).equals(this.command)) {
        if (this.components.size() == RankTowers.ARGS + 1) {
          RankTowers rankTowers = new RankTowers(this.components.get(1), 
              this.components.get(2));
          rankTowers.run();
          this.output = rankTowers.getOutput();
          this.test = this.command;
        }
        else {
          this.output = expected + RankTowers.ARGS + arguments;
          RankTowers rt = new RankTowers();
          this.output += rt.getHelp();
        }
      }
      else if ((HELP).equals(this.command)) {
        // Expected arguments notice is not needed for Help, since it takes 0 arguments
        Help help = new Help();
        help.run();
        this.output = help.getOutput();
        this.test = this.command;
      }
      else if ((QUIT).equals(this.command)) {
        // Expected arguments notice is not needed for Quit, since it takes 0 arguments
        Quit quit = new Quit();
        quit.run();
        this.output = quit.getOutput();
        this.test = this.command;
      }
      else {
        if (this.components.size() == 0) {
          this.output = "Nothing was entered.\n";
          this.output += "For a list of available commands, type: help";
        }
        else {
          this.output = "There is no such command.\n";
          this.output += "For a list of available commands, type: help";
        }
      }
    }
    catch (InvalidArgumentsException e) {
      this.output = "Invalid arguments.\n";
      this.output += "For help with arguments, type: help";
    }
    catch (Exception e) { // All other types of exceptions
      // Most likely thrown due to a WattDepot server error in fetching the data
      this.output = "No data received.\n";
      this.output += "Please try again or try different towers/lounges/dates.";
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
   * Runs all the necessary tasks for this processor.
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