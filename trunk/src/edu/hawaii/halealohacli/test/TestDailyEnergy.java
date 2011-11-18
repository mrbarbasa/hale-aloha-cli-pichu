package edu.hawaii.halealohacli.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;
import edu.hawaii.halealohacli.command.DailyEnergy;

/**
 * Test the DailyEnergy class.
 * 
 * @author Team Pichu
 */
public class TestDailyEnergy {
  
  /**
   * Test the results returned.
   * 
   * @throws Exception - error.
   */
  @Test
  public void testDailyEnergy() throws Exception {
    DailyEnergy energy = new DailyEnergy("Ilima-04-lounge", "2011-10-26");
    
    assertTrue("Testing daily energy", energy.getDailyEnergy() > 0);
    assertFalse("Test daily energy less than 0", energy.getDailyEnergy() < 0);
  }
  

}
