/**
 * <p> Web Service Application Programming Interface</p>
 * 
 *

 * 
 * @author Bernardo Tabuenca Archilla
 * @version 1.0
 */
package org.btb.analyticsws.db;

public class DbConnectException extends Exception {

  /**
    * Mensaje por defecto de la excepción de conexión a la base de datos
    */
     private String message = "Error de conexión a la base de datos";

   /**
    * Construye un objeto de tipo DbConnectException especificando un mensaje
    * @param message El mensaje.
    */
   public DbConnectException(String message) {
       super(message);
       this.message = message;
   }

   /**
    *  Contruye un objeto de tipo DbConnectException sin especificar el mensaje
    */
   public DbConnectException() {
       super("Error de conexión a la base de datos");
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


