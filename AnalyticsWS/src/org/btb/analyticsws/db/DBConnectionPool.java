/**
 * <p> Web Service Application Programming Interface</p>
 * 
 *

 * 
 * @author Bernardo Tabuenca Archilla
 * @version 1.0
 */

package org.btb.analyticsws.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Vector;


public class DBConnectionPool {

  /**
   * Variable para los log
   */
  String name=new String();
  String URL=new String();
  String user=new String();
  String password=new String();
  int maxConn;
  Vector freeConnections=new Vector();
  int checkedOut;

  /**
   * Constructor del objeto que modela un pool de conexiones.
   * @param URL String URL url del servidor, direccion con la que se prentende establecer el pool de
   * conexiones
   * @param user String Nombre del usuario para la conexion
   * @param password String Clave que permite a ese usuario establecer la conexion
   * @param maxConn int Numero maximo de conexiones simultaneas permitidas para el pool creado
   */
  public DBConnectionPool(String URL, String user, String password, int maxConn) {
    this.URL = URL;
    this.user = user;
    this.password = password;
    this.maxConn = maxConn;
  }

  /**
   * Metodo que devuelve una conexion si con la nueva conexion solicitada el numero
   * de conexiones ocupadas no supera el maximo de conexiones permitido para ese
   * pool de conexiones.
   *
   * @return Connection Nueva conexion, en caso de que se haya podido crear, null,
   * caso contrario.
   */
  public synchronized Connection getConnection() {
    Connection con = null;
    if (freeConnections.size() > 0) {
      // Pick the first Connection in the Vector
      // to get round-robin usage
      con = (Connection) freeConnections.firstElement();
      freeConnections.removeElementAt(0);

      System.out.println("Numero de conexiones libres "+freeConnections.size());

      //Definimos un statement para chequear la conexion
      Statement stmt = null;
      try {
        if (con.isClosed()) {
          // Intenta de nuevo recursivamente
          con = getConnection();
        } else {
          //Comprobamos si esta corrupta
          stmt = con.createStatement();
          stmt.close();
        }
      } catch (SQLException e) {
        try{
          con.close();
        } catch (SQLException ex) { }

        // Intenta de nuevo recursivamente
        con = getConnection();
      }
    } else if (maxConn == 0 || checkedOut < maxConn) {
      con = newConnection();
    }

    if (con != null) {
      checkedOut++;
    }
    System.out.println("Obtenida conexion "+con);
    return con;
  }

  /**
   * Metodo que crea una nueva conexion utilizando los atributos: URL, user y password
   *
   * @return Connection Conexion creada
   */
  private Connection newConnection() {
    Connection con = null;
    //ResultSet rs=null;
    //Statement sentencia=null;
    try {
      if (user == null) {
        con = DriverManager.getConnection(URL);
        /*sentencia=con.createStatement();
        rs= sentencia.executeQuery ("SET autocommit=0");
        rs.close();*/
      } else {
    	  System.out.println(URL+" "+user+" "+password);
        con = DriverManager.getConnection(URL, user, password);
        /* sentencia=con.createStatement();

        rs= sentencia.executeQuery ("SET autocommit=0");

        rs.close();
        sentencia.close();*/
      }
      con.setAutoCommit(false);
    } catch (SQLException e) {
    	System.out.println(e.toString());
      return null;
    }
    System.out.println("Conexion creada "+con);
    return con;
  }

  /**
   * Metodo que devuelve una conexion tras cumplirse el tiempo de espera (en milisegundos) que es pasado
   * como parametro. Es un metodo de sincronizacion.
   *
   * @param timeout long Tiempo de espera que se deja transcurrir antes de devolver la conexion.
   * @return Connection Conexion tras esperar el tiempo indicado.
   */

  public synchronized Connection getConnection(long timeout) {
    long startTime = new java.util.Date().getTime();
    Connection con;
    while ((con = getConnection()) == null) {
      try {
        wait(timeout);
      } catch (InterruptedException e) {}
      if ((new java.util.Date().getTime() - startTime) >= timeout) {
    	  System.out.println("Error obteniendo una conexion sincronizada");
        // Timeout has expired
        return null;
      }
    }
    return con;
  }

  /**
   * Metodo que incorpora la conexion indicada nuevamente al pool de conexiones
   * @param con Connection Conexion a liberar
   */
  public synchronized void freeConnection(Connection con) {
    // Put the connection at the end of the Vector

    freeConnections.addElement(con);
    notifyAll();
    checkedOut--;
    System.out.println(con+" Conexiones ocupadas "+checkedOut);
  }

  /**
   * Metodo que cierra todas las conexiones. Elimina todos los elementos del vector
   * de conexiones libres.
   */
  public synchronized void release() {
    Enumeration allConnections = freeConnections.elements();
    while (allConnections.hasMoreElements()) {
      Connection con = (Connection) allConnections.nextElement();
      try {
        con.close();
        System.out.println("Conexion cerrada para pool ");
      } catch (SQLException e) {
    	  System.out.println("No se puede cerrar conexion para pool ");
      }
    }
    freeConnections.removeAllElements();
  }

}



