package edu.hawaii.halealohacli.test;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import edu.hawaii.halealohacli.processor.Processor;

/**
 * Tests the Processor class. 
 * 
 * @author Team Pichu
 */
public class TestProcessor {
  
  /**
   * Confirms that the current-power command is called.
   * 
   * @throws Exception Doesn't really throw an exception.
   */
  @Test
  public void testCurrentPower() throws Exception {
    Processor pro = new Processor("current-power Ilima");
    pro.run();
    assertTrue("Testing that current-power is called", ("current-power").equals(pro.getTest()));
  }
  
  /**
   * Confirms that the daily-energy command is called.
   * 
   * @throws Exception Doesn't really throw an exception.
   */
  @Test
  public void testDailyEnergy() throws Exception {
    Processor pro = new Processor("daily-energy Ilima 2011-11-23");
    pro.run();
    assertTrue("Testing that daily-energy is called", ("daily-energy").equals(pro.getTest()));
  }
  
  /**
   * Confirms that the energy-since command is called.
   * 
   * @throws Exception Doesn't really throw an exception.
   */
  @Test
  public void testEnergySince() throws Exception {
    Processor pro = new Processor("energy-since Ilima 2011-11-23");
    pro.run();
    assertTrue("Testing that energy-since is called", ("energy-since").equals(pro.getTest()));
  }
  
  /**
   * Confirms that the rank-towers command is called.
   * 
   * @throws Exception Doesn't really throw an exception.
   */
  @Test
  public void testRankTowers() throws Exception {
    Processor pro = new Processor("rank-towers 2011-11-23 2011-11-26");
    pro.run();
    assertTrue("Testing that rank-towers is called", ("rank-towers").equals(pro.getTest()));
  }
  
  /**
   * Confirms that the help command is called.
   * 
   * @throws Exception Doesn't really throw an exception.
   */
  @Test
  public void testHelp() throws Exception {
    Processor pro = new Processor("help");
    pro.run();
    assertTrue("Testing that help is called", ("help").equals(pro.getTest()));
  }
  
  /**
   * Confirms that the quit command is called.
   * 
   * @throws Exception Doesn't really throw an exception.
   */
  @Test
  public void testQuit() throws Exception {
    Processor pro = new Processor("quit");
    pro.run();
    assertTrue("Testing that quit is called", ("quit").equals(pro.getTest()));
  }
  
  /**
   * Confirms that an invalid command is called.
   * 
   * @throws Exception Doesn't really throw an exception.
   */
  @Test
  public void testArguments() throws Exception {
    Processor pro = new Processor("exit");
    pro.run();
    assertTrue("Testing that an invalid command is called", ("fail").equals(pro.getTest()));
  }
  
}
