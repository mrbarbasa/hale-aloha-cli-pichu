package edu.hawaii.halealohacli.test;

import static org.junit.Assert.assertEquals;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import org.junit.Test;
import edu.hawaii.halealohacli.command.RankTowers;

/**
 * 
 * 
 * @author Team Pichu
 */
public class TestRankTowers {

  /**
   * Test if the dates entered actually allows
   * the retrieval of data from the sources.
   * 
   * @throws Exception - error.
   */
  @Test
  public void test() throws Exception {
    
    Calendar today = Calendar.getInstance(Locale.US);
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    //Adding one day to the current day
    today.add(Calendar.DATE, 1);
    String dayAhead = df.format(today.getTimeInMillis());
    RankTowers test = new RankTowers("2011-11-23", dayAhead);
    test.run();
    String message = "Too early to retrieve data for the dates entered";
    
    assertEquals("Test dates", message, test.getOutput());
  }

}
