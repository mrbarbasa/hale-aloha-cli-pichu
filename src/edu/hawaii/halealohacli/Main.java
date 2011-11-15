package edu.hawaii.halealohacli;

import java.util.Scanner;
import org.wattdepot.client.WattDepotClient;
import edu.hawaii.halealohacli.processor.Processor;

/**
 * Driver class to run the Hale Aloha
 * command line interface program.
 * 
 * @author Team Pichu
 */
public class Main {
  
  /**
   * Runs the Hale Aloha command line interface program.
   * 
   * @param args no command line arguments required
   */
  public static void main(String[] args) {
    final String url = "http://server.wattdepot.org:8190/wattdepot/";
    WattDepotClient client = new WattDepotClient(url);
    
    // Check to make sure a connection can be made
    if (client.isHealthy()) {
      System.out.println("Connected successfully to the Hale Aloha WattDepot server.");
    }
    // If no connection, then exit right now
    else {
      System.out.format("Could not connect to: %s%n", url);
      return; // Prematurely exit the program
    }
    
    // Main loop that runs the program until the "quit" command is processed
    boolean quit = false;
    Scanner scan = new Scanner(System.in);
    String input = "";
    while (!quit) {
      System.out.print("> ");
      if (scan.hasNextLine()) {
        input = scan.nextLine();
        System.out.println(input);
        Processor pro = new Processor(input);
        pro.run();
      }
    }
  }

}
