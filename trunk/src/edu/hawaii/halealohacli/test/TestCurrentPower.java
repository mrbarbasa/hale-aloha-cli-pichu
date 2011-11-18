package edu.hawaii.halealohacli.test;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import edu.hawaii.halealohacli.command.CurrentPower;

/**
 * 
 * 
 * @author Team Pichu
 */
public class TestCurrentPower {
  
  /**
   * Test current power output.
   * 
   * @throws Exception - error.
   */
  @Test
  public void testCurrentPower() throws Exception {
    CurrentPower power = new CurrentPower("Ilima-04-lounge");
    
    assertTrue("Test current power", power.getCurrentPower() > 0);
  }

}
