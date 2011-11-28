package edu.hawaii.halealohacli.command;


/**
 * Represent a sensor with attributes of
 * the source's name and the energy (in kWh).
 * 
 * @author Team Pichu
 *
 */
public class SensorWatts implements Comparable<SensorWatts> {
  
  private String sourceName;
  private double watts;
  
  /**
   * Creating object of SensorWatts class.
   * 
   * @param sourceName - name of the source from WattDepot.
   * @param watts - energy used by the sensor.
   */
  public SensorWatts(String sourceName, double watts) {
    this.sourceName = sourceName;
    this.watts = watts;
  }
  
  /**
   * Returns name of the source.
   * @return - sourceName
   */
  public String getSourceName() {
    return sourceName;
  }
  
  /**
   * Returns the energy used by the sensor.
   * @return - watts
   */
  public double getWatts() {
    return watts;
  }
  
  /**
   * Value of the object.
   * 
   * @return - hashValue
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
   * Test equality of objects.
   * 
   * @param obj - generic Object.
   * @return - true or false.
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
   * Compares the watts (energy) to use in sorting.
   * 
   * @param sw - SensorWatts object.
   * @return - value indicating the comparison between the watts.
   */
  @Override
  public int compareTo(SensorWatts sw) {
    return this.watts < sw.watts ? -1 : this.watts > sw.watts ? 1 : 0;
  }
  
}
