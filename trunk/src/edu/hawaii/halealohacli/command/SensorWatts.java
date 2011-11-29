package edu.hawaii.halealohacli.command;


/**
 * Represents a sensor with attributes of
 * the source's name and the energy (in kWh).
 * 
 * @author Team Pichu
 */
public class SensorWatts implements Comparable<SensorWatts> {
  
  private String sourceName;
  private double watts;
  
  /**
   * Creates an instance of the SensorWatts class.
   * 
   * @param sourceName name of the source from WattDepot
   * @param watts energy used by the sensor
   */
  public SensorWatts(String sourceName, double watts) {
    this.sourceName = sourceName;
    this.watts = watts;
  }
  
  /**
   * Returns the name of the source.
   * @return the name of the source
   */
  public String getSourceName() {
    return sourceName;
  }
  
  /**
   * Returns the energy used by the sensor.
   * @return the energy used by the sensor
   */
  public double getWatts() {
    return watts;
  }
  
  /**
   * Returns the hash code value of this instance.
   * 
   * @return the hash code value of this instance
   */
  @Override
  public int hashCode() {
    int hash = 31;
    int result = 1;
    
    result = hash * result + (int) watts;
    result = hash * result + (null == sourceName ? 0 : sourceName.hashCode());
    return result;
  }
  
  /**
   * Tests the equality of SensorWatts instances.
   * 
   * @param obj a generic object
   * @return true if equal; false otherwise
   */
  @Override
  public boolean equals(Object obj) {   
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    
    SensorWatts sw = (SensorWatts) obj;
    
    if (watts != sw.watts) {
      return false;
    }
    if (sourceName == null) {
      if (sw.sourceName == null) {
        return false;
      }
    }
    else if (!this.sourceName.equals(sw.sourceName)) {
      return false;
    }
    
    return true;
  }
  
  /**
   * Compares the watts (energy) for use in sorting.
   * 
   * @param sw SensorWatts instance to be compared with
   * @return the value indicating the comparison between the watts
   */
  @Override
  public int compareTo(SensorWatts sw) {
    return this.watts < sw.watts ? -1 : this.watts > sw.watts ? 1 : 0;
  }
  
}
