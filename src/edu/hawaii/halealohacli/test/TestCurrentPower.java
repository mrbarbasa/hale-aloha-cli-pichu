package edu.hawaii.halealohacli.test;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import edu.hawaii.halealohacli.command.CurrentPower;
import edu.hawaii.halealohacli.command.InvalidArgumentsException;

/**
 * Tests the CurrentPower class.
 * 
 * @author Team Pichu
 */
public class TestCurrentPower {
  
  /**
   * Tests current-power output, and invalid
   * tower name input.
   * 
   * @throws Exception If problems occur in retrieving data from WattDepot.
   */
  @Test
  public void testCurrentPower() throws Exception {
    CurrentPower power = new CurrentPower("Ilima");
    power.run();
    assertTrue("Testing current-power", power.getCurrentPower() > 0);
    
    //Input is suppose to be a tower name, but a date was entered    
    boolean caught = false;
    try {
      new CurrentPower("2011-11-24");
    }
    catch (InvalidArgumentsException e) {
      caught = true;
    }
    assertSame("Test invalid input", caught, true);
  }

}
