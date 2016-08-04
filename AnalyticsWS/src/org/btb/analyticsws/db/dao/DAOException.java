/**
 * <p> Web Service Application Programming Interface</p>
 * 
 *

 * 
 * @author Bernardo Tabuenca Archilla
 * @version 1.0
 */

package org.btb.analyticsws.db.dao;


public class DAOException extends Exception {

  /**
   * Mensaje por defecto de la excepción de operaciones sobre la BD
   */
  private String message = "Error al realizar una operacion sobre la BD";

  /**
   * Construye un objeto de tipo DAOException especificando un mensaje
   * @param message El mensaje.
   */
  public DAOException(String message) {
    super(message);
    this.message = message;
  }

  /**
   *  Contruye un objeto de tipo DAOException sin especificar el mensaje
   */
  public DAOException() {
    super("Error al realizar una operacion sobre la BD");
  }

  /**
   * Devuelve el valor del atributo message.
   * @return String El valor del atributo message, que describe la excepcion
   */
  public String getMessage() {
    return message;
  }

  /**
   * Establece el mensaje de error
   * @param message Mensaje que describe el error
   */
  public void setMessage(String message) {
    this.message=message;
  }

}
