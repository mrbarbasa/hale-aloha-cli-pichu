package edu.hawaii.halealohacli.command;

/**
 * Provides information on valid arguments and
 * acquires the description of each command.
 * 
 * @author Team Pichu
 */
public class Help implements Command {
  
  private String output;
  
  /**
   * The command help takes 0 arguments.
   */
  public static final int ARGS = 0;
  
  /**
   * Creates a new instance of the help command.
   */
  public Help() {
    this.output = "ARGUMENTS:\n";
    this.output += "  [tower | lounge] =>\n";
    this.output += "    [tower] = Mokihana | Ilima | Lehua | Lokelani\n";
    this.output += "    [lounge] = Mokihana | Ilima | Lehua | Lokelani\n";
    this.output += "      followed by \"-\" followed by A | B | C | D | E.\n";
    this.output += "      For example: Mokihana-A\n";
    this.output += "  [date] =>\n";
    this.output += "    The date in yyyy-mm-dd format.\n";
    this.output += "    Must be BEFORE today's date.\n";
    this.output += "  [start] =>\n";
    this.output += "    The starting date in yyyy-mm-dd format.\n";
    this.output += "    Must be BEFORE [end], which is BEFORE today's date.\n";
    this.output += "  [end] =>\n";
    this.output += "    The ending date in yyyy-mm-dd format.\n";
    this.output += "    Must be AFTER [start] and BEFORE today's date.\n\n";
  }
  
  /**
   * Runs this command.
   * 
   * @throws Exception Doesn't really throw an exception.
   */
  @Override
  public void run() throws Exception {
    this.output += "COMMANDS:\n";
    this.output += "Here are the available commands for this system.\n\n";
    String newline = "\n\n";
    CurrentPower cp = new CurrentPower();
    this.output += cp.getHelp() + newline;
    DailyEnergy de = new DailyEnergy();
    this.output += de.getHelp() + newline;
    EnergySince es = new EnergySince();
    this.output += es.getHelp() + newline;
    RankTowers rt = new RankTowers();
    this.output += rt.getHelp() + newline;
    SetBaseline sb = new SetBaseline();
    this.output += sb.getHelp() + newline;
    MonitorGoal mg = new MonitorGoal(null);
    this.output += mg.getHelp() + newline;
    MonitorPower mp = new MonitorPower();
    this.output += mp.getHelp() + newline;
    Help help = new Help();
    this.output += help.getHelp() + newline;
    Quit quit = new Quit();
    this.output += quit.getHelp();
  }
  
  /**
   * Returns a string representation of the output of calling this command.
   * 
   * @return the output of calling this command
   */
  @Override
  public String getOutput() {
    return this.output;
  }
  
  /**
   * Retrieves a description of this command and its functionality.
   * 
   * @return a description of this command and its functionality
   */
  @Override
  public String getHelp() {
    String description = "help\n";
    description += "  Usage: help\n";
    description += "    Provides a brief description of each command and its usage.";
    return description;
  }

}
