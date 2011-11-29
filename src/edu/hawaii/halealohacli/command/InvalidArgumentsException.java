package edu.hawaii.halealohacli.command;

/**
 * Each Command class's constructor (with parameters) should throw this exception
 * if the command requires arguments.  Note that the default constructor of
 * each Command class does not need to throw this exception.
 * 
 * @author Team Pichu
 */
public class InvalidArgumentsException extends Exception {
  
  /**
   * Required default serial ID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Constructor that inherits from the superclass Exception.
   */
  public InvalidArgumentsException() {
    super();
  }

}
