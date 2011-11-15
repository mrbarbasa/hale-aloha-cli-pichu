package edu.hawaii.halealohacli.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Parses user input, determines what the command is,
 * and dispatches the particular command to be handled
 * by the corresponding class.
 * 
 * @author Team Pichu
 */
public class Processor {
  
  private String input = "";
  private List<String> components;
  
  /**
   * Creates a new processor instance.
   * 
   * @param input the string input to process
   */
  public Processor(String input) {
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
  }
  
  /**
   * Determines which class corresponds to the command.
   */
  public void callCommand() {
    
  }
  
  /**
   * Runs all the necessary tasks for this class.
   */
  public void run() {
    this.parseInput();
    this.callCommand();
  }
  
}