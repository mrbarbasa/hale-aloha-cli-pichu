package edu.hawaii.halealohacli.test;

import static org.junit.Assert.assertSame;
import org.junit.Test;
import edu.hawaii.halealohacli.Main;

/**
 * Tests the Main class.
 * 
 * @author Team Pichu
 */
public class TestMain {
  // TODO: Still needs a lot of work.  May change if Reflection is implemented.

  /**
   * Tests if checkConnection returns true for successful connection
   * to the WattDepot server.
   */
  @Test
  public void testCheckConnection() {
    Main.checkConnection();
    assertSame("Testing the connection to the WattDepot server", Main.getCheckConnection(), true);   
  }
  
  /**
   * Tests if run returns true if successfully called.
   */
  @Test
  public void testRun() {
    Main.run("quit");
    assertSame("Testing if run is called successfully", Main.getRun(), true);   
  }

}
