package edu.hawaii.halealohacli;

import java.util.Scanner;
import org.wattdepot.client.WattDepotClient;
import edu.hawaii.halealohacli.processor.Processor;

/**
 * Driver class that runs the hale-aloha-cli-pichu program.
 * 
 * @author Team Pichu
 */
public class Main {

  private static final String URL = "http://server.wattdepot.org:8190/wattdepot/";
  private static final String QUIT = "quit";
  private static boolean checkConnection;
  private static boolean run = false;
  
  /**
   * Gives all commands access to the WattDepot client.
   */
  public static final WattDepotClient CLIENT = new WattDepotClient(URL);
  
  /**
   * Returns true if a successful connection to the WattDepot
   * server is established; false otherwise.
   * 
   * @return true if successful connection; false otherwise
   */
  public static boolean getCheckConnection() {
    return checkConnection;
  }
  
  /**
   * Returns true if the run method was successfully called;
   * false otherwise.
   * 
   * @return true if run method was called; false otherwise
   */
  public static boolean getRun() {
    return run;
  }
  
  /**
   * Checks that a successful connection to the WattDepot server has been established.
   */
  public static void checkConnection() {
    // Check to make sure a connection can be made
    if (CLIENT.isHealthy()) {
      checkConnection = true;
      System.out.println("Connected successfully to the Hale Aloha WattDepot server.");
    }
    // If no connection, then exit right now
    else {
      checkConnection = false;
      System.out.format("Could not connect to: %s%n", URL);
      return; // Prematurely exit the program
    }
  }
  
  /**
   * Calls the Processor to process the user input.
   * 
   * @param input the command and arguments that the user has input
   * @return the output the processor has received from a command
   */
  public static String run(String input) {
    Processor pro = new Processor(input);
    pro.run();
    run = true;
    return pro.getOutput();
  }
  
  /**
   * Runs the hale-aloha-cli-pichu program.
   * 
   * @param args no command line arguments required
   */
  public static void main(String[] args) {
    checkConnection(); // Initially check that the connection to the server is healthy    
    Scanner scan = new Scanner(System.in);
    String input = "";
    boolean quit = false;
    // Main loop that runs the program until the quit command is processed
    while (!quit) {
      if (!CLIENT.isHealthy()) { // Check connection to the server before each loop
        checkConnection = false;
        System.out.format("Lost connection to: %s%n", URL);
        return; // Prematurely exit the program
      }
      System.out.print("> ");
      input = scan.nextLine();
      String output = Main.run(input);
      if ((QUIT).equals(output)) {
        quit = true;
        System.out.println("Concerned about energy and power?  You're awesome!  See ya!");
      }
      else {
        System.out.println(output);
      }
    }
  }
 
}
