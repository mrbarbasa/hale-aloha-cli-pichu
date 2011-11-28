package edu.hawaii.halealohacli.test;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import edu.hawaii.halealohacli.command.Help;

/**
 * Tests the Help class.
 * 
 * @author Team Pichu
 */
public class TestHelp {

  /**
   * Confirms that there is a help entry for ARGUMENTS.
   * 
   * @throws Exception Doesn't really throw an exception.
   */
  @Test
  public void testArguments() throws Exception {
    Help help = new Help();
    help.run();
    assertTrue("Testing that a help entry for ARGUMENTS exists", 
        help.getOutput().contains("ARGUMENTS"));
  }
  
  /**
   * Confirms that there is a help entry for current-power.
   * 
   * @throws Exception Doesn't really throw an exception.
   */
  @Test
  public void testCurrentPower() throws Exception {
    Help help = new Help();
    help.run();
    assertTrue("Testing that a help entry for current-power exists", 
        help.getOutput().contains("current-power"));
  }
  
  /**
   * Confirms that there is a help entry for daily-energy.
   * 
   * @throws Exception Doesn't really throw an exception.
   */
  @Test
  public void testDailyEnergy() throws Exception {
    Help help = new Help();
    help.run();
    assertTrue("Testing that a help entry for daily-energy exists", 
        help.getOutput().contains("daily-energy"));
  }
  
  /**
   * Confirms that there is a help entry for energy-since.
   * 
   * @throws Exception Doesn't really throw an exception.
   */
  @Test
  public void testEnergySince() throws Exception {
    Help help = new Help();
    help.run();
    assertTrue("Testing that a help entry for energy-since exists", 
        help.getOutput().contains("energy-since"));
  }
  
  /**
   * Confirms that there is a help entry for rank-towers.
   * 
   * @throws Exception Doesn't really throw an exception.
   */
  @Test
  public void testRankTowers() throws Exception {
    Help help = new Help();
    help.run();
    assertTrue("Testing that a help entry for rank-towers exists", 
        help.getOutput().contains("rank-towers"));
  }
  
  /**
   * Confirms that there is a help entry for help.
   * 
   * @throws Exception Doesn't really throw an exception.
   */
  @Test
  public void testHelp() throws Exception {
    Help help = new Help();
    help.run();
    assertTrue("Testing that a help entry for help exists", 
        help.getOutput().contains("help"));
  }
  
  /**
   * Confirms that there is a help entry for quit.
   * 
   * @throws Exception Doesn't really throw an exception.
   */
  @Test
  public void testQuit() throws Exception {
    Help help = new Help();
    help.run();
    assertTrue("Testing that a help entry for quit exists", 
        help.getOutput().contains("quit"));
  }

}
