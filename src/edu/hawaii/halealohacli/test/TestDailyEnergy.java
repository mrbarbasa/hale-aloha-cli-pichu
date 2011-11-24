package edu.hawaii.halealohacli.test;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import edu.hawaii.halealohacli.command.DailyEnergy;

/**
 * Tests the DailyEnergy class.
 * 
 * @author Team Pichu
 */
public class TestDailyEnergy {
  
  /**
   * Tests if the energy consumed on the specified date is greater than 0.
   * 
   * @throws Exception If problems occur in retrieving data from WattDepot.
   */
  @Test
  public void testDailyEnergy() throws Exception {
    DailyEnergy energy = new DailyEnergy("Ilima", "2011-11-22");
    energy.run();
    assertTrue("Testing daily energy", energy.getDailyEnergy() > 0);
  }
  

}
