package edu.hawaii.halealohacli.command;

/**
 * Interface for program commands.
 * @author Team Pichu
 */
public interface Command {
  
  void run();
  String getOutput();
  String description();
}
