package edu.hawaii.halealohacli.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import edu.hawaii.halealohacli.command.CurrentPower;

/**
 * Tests the CurrentPower class.
 * 
 * @author Team Pichu
 */
public class TestCurrentPower {
  
  /**
   * Test current power output, and invalid
   * tower name input.
   * 
   * @throws Exception - error.
   */
  @Test
  public void testCurrentPower() throws Exception {
    CurrentPower power = new CurrentPower("Ilima");
    power.run();
    assertTrue("Testing current power", power.getCurrentPower() > 0);
    
    //Input is suppose to be a tower name, but a date was entered
    CurrentPower invalid = new CurrentPower("2011-11-24");
    invalid.run();
    String message = "Tower name invalid.";
    assertEquals("Test invalid input", message, invalid.getOutput());
  }

}
