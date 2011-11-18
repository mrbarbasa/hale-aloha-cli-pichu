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
  
  private String testSource = "Ilima-04-lounge";
  private String testDate = "2011-10-26";
  
  /**
   * Test the results returned.
   * 
   * @throws Exception - error.
   */
  @Test
  public void testGetDailyEnergy() throws Exception {
    
    DailyEnergy energy = new DailyEnergy(testSource, testDate);
    
    assertTrue("Testing daily energy", energy.getEnergy() > 0);
    assertFalse("Test daily energy less than 0", energy.getEnergy() < 0);
  }
  

}
