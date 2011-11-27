package edu.hawaii.halealohacli.test;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import edu.hawaii.halealohacli.command.DailyEnergy;
import edu.hawaii.halealohacli.command.EnergySince;

/**
 * Tests the EnergySince class.
 * 
 * @author Team Pichu
 */
public class TestEnergySince {

  /**
   * Tests if the energy consumed since the date is 
   * greater than what was consumed on that date.
   * 
   * @throws Exception If problems occur in retrieving data from WattDepot.
   */
  @Test
  public void testEnergySince() throws Exception {
    String testSource = "Ilima";
    String testDate = "2011-11-23";
    EnergySince es = new EnergySince(testSource, testDate);
    DailyEnergy de = new DailyEnergy(testSource, testDate);
    es.run();
    de.run();
    assertTrue("Testing energy since", es.getEnergySince() > de.getDailyEnergy());
  }

}
