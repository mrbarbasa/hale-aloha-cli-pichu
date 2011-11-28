package edu.hawaii.halealohacli.command;

/**
 * Interface for the Hale Aloha CLI program commands.
 * 
 * @author Team Pichu
 */
public interface Command {
  
  /**
   * Causes command to execute "main" code for functionality.
   * 
   * @throws Exception Generally, if problems occur in retrieving data from WattDepot.
   */
  public void run() throws Exception;

  /**
   * Retrieves output of method.
   * 
   * @return String output of method.
   */
  public String getOutput();

  /**
   * Retrieves a description of the method and its functionality.
   * 
   * @return String description of method.
   */
  public String getHelp();
}
