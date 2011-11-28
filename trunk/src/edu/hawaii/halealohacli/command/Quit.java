package edu.hawaii.halealohacli.command;

/**
 * Command that returns a boolean concerning
 * whether or not the program should exit.
 * 
 * @author Team Pichu
 */
public class Quit implements Command {
  
  /**
   * Creates a new instance of the quit command.
   */
  public Quit() {
    // TODO
  }
  
  /**
   * Runs this command.
   * 
   * @throws Exception This command doesn't really throw an exception.
   */
  public void run() throws Exception {
    // TODO
  }
  
  /**
   * Returns a string representation of the output of calling this command.
   * 
   * @return the output of calling this command
   */
  public String getOutput() {
    return "quit";
  }
  
  /**
   * Retrieves a description of this command and its functionality.
   * 
   * @return a description of this command
   */
  public String getHelp() {
    String description = "quit\n";
    description += "  Usage: quit\n";
    description += "    Terminates execution.";
    return description;
  }

}
