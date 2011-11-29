package edu.hawaii.halealohacli.command;

/**
 * Returns a boolean concerning whether
 * or not the program should exit.
 * 
 * @author Team Pichu
 */
public class Quit implements Command {
  
  private String output;
  
  /**
   * The command quit takes 0 arguments.
   */
  public static final int ARGS = 0;
  
  /**
   * Creates a new instance of the quit command.
   */
  public Quit() {
    this.output = "";
  }
  
  /**
   * Runs this command.
   * 
   * @throws Exception Doesn't really throw an exception.
   */
  @Override
  public void run() throws Exception {
    this.output = "quit";
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
    String description = "quit\n";
    description += "  Usage: quit\n";
    description += "    Terminates execution.";
    return description;
  }

}
