package edu.hawaii.halealohacli.test;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import edu.hawaii.halealohacli.command.DailyEnergy;
import edu.hawaii.halealohacli.command.SetBaseline;

/**
 * JUnit test class for the <code>SetBaseline</code> command. 
 * 
 * @author David Wilkie
 */
public class TestSetBaseline {
  
  //if the difference between dailyEnergy and sum of the hourly setBaseline values is not 
  //less than this, the test fails.
  private static final double EPSILON = 2.0;

  /**
   * JUnit test case, which assures that dailyEnergy and sum of the hourly setBaseline 
   * values are equal, and that the hourly energy totals are greater than 0.
   */
  @Test 
  public void dailyEnergyEqualsBaselineTotal() {
    double totalEnergy = 0;
    try {
      SetBaseline sb = new SetBaseline("Mokihana", "2011-12-01");
      DailyEnergy de = new DailyEnergy("Mokihana", "2011-12-01");
      sb.run();
      de.run();
      double[] energies = sb.getEnergies();
      for (double d : energies) {
        totalEnergy += d;
        assertTrue("energy value was non-positive", d > 0);
      }
      double dailyEnergy = de.getDailyEnergy();
      assertTrue("dailyEnergy and sum of setBaseline values didn't match",
          Math.abs(dailyEnergy - totalEnergy) < EPSILON);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
