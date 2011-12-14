package edu.hawaii.halealohacli.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import edu.hawaii.halealohacli.command.MonitorPower;

/**
 * JUnit test class for the MonitorPower command. 
 * 
 * @author Russell Vea
 */
public class TestMonitorPower {

  /**
   * JUnit test case.
   * It check to see if the interval defaults to zero.
   * Checks to see if interval is set to user input.
   * Checks to see if the second run is greater than the last one.
   * @throws Exception upon error with the server.
   */
  @Test public void test() throws Exception {
    MonitorPower monmon = null;
    monmon = new MonitorPower("Ilima");
    assertEquals("Check to see if interval defaults to 10000 milliseconds"
        , monmon.getInterval(), 10000);
    monmon.runTimes(1);
    double energy = monmon.getLatestEnergy();
    System.out.println(energy);
    assertTrue("Check to see if energy output is greater than 0", energy > 0);
    double secondEnergy = 0;
    monmon = new MonitorPower("Ilima", "4");
    assertEquals("Check to see if interval defaults to 4000 milliseconds"
        , monmon.getInterval(), 4000);
    monmon.runTimes(1);
    secondEnergy = monmon.getLatestEnergy();
    assertTrue("Check to see if energy output is greater than 0", secondEnergy > 0);
    assertTrue("check to see if newer input is equal or greater than older",
        secondEnergy >= energy);
  }
}
