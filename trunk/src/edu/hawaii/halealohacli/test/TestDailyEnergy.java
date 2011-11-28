package edu.hawaii.halealohacli.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
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
    //Normal input
    DailyEnergy deOne = new DailyEnergy("Ilima", "2011-11-23");
    deOne.run();
    assertTrue("Testing daily energy", deOne.getDailyEnergy() > 0);
    
    
    Calendar today = Calendar.getInstance(Locale.US);
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    //Adding one day to the current day
    today.add(Calendar.DATE, 1);
    String dayAhead = df.format(today.getTimeInMillis());
    String message = "Too early to retrieve data for the date of " + dayAhead;
    //Test one day ahead of current day
    DailyEnergy deTwo = new DailyEnergy("Ilima", dayAhead);
    deTwo.run();
    assertEquals("Test one day ahead", message, deTwo.getOutput());
    
  }
  

}
