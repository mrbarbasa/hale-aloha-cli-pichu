package edu.hawaii.halealohacli.command;

/**
 * Command that returns a boolean concerning
 * whether or not the program should exit.
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
   * Not needed for the quit command.
   * 
   * @param args the arguments to be checked for validity
   * @return true by default (because quit has no arguments)
   */
  public boolean checkArgs(String... args) {
    return true;
  }
  
  /**
   * Runs this command.
   * 
   * @throws Exception This command doesn't really throw an exception.
   */
  public void run() throws Exception {
    this.output = "quit";
  }
  
  /**
   * Returns a string representation of the output of calling this command.
   * 
   * @return the output of calling this command
   */
  public String getOutput() {
    return this.output;
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
