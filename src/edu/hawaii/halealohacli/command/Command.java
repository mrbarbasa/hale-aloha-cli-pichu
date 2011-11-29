package edu.hawaii.halealohacli.command;

/**
 * Interface for the hale-aloha-cli-pichu program commands.
 * 
 * @author Team Pichu
 */
public interface Command {
  
  /**
   * Runs the command.
   * 
   * @throws Exception Generally, if problems occur in retrieving data from WattDepot.
   */
  public void run() throws Exception;

  /**
   * Returns a string representation of the output of calling the command.
   * 
   * @return the output of calling the command
   */
  public String getOutput();

  /**
   * Retrieves a description of the command and its functionality.
   * 
   * @return a description of the command and its functionality
   */
  public String getHelp();
  
}
