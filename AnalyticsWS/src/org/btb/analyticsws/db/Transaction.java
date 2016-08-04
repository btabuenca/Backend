/**
 * <p> Web Service Application Programming Interface</p>
 * 
 *

 * 
 * @author Bernardo Tabuenca Archilla
 * @version 1.0
 */
package org.btb.analyticsws.db;

import org.apache.log4j.*;


import java.sql.*;


public class Transaction {

  private Connection connect = null;


  /**
   * Constructor del objeto que modela una transaccion que oculta el uso
   * de una conexion gestionada dentro de un pool.
   * @exception DbConnectException Si se no se puede crear la transaccion.
   */
  public Transaction () throws DbConnectException {

    System.out.println("Creando la transaccion.");
    connect = ConnectionsManager.getConnectionsManager().getConnection(200);

    if (connect==null) {
    	System.out.println("No se puede obtener una conexion");
      throw new DbConnectException ("No se puede obtener una conexion");
    } else {
    	System.out.println("Creada una conexion");
    }
  }

  /**
   * Metodo que devuelve la conexion obtenida a traves del gestor
   * de conexiones.
   * @return Connection conexion dada por el gestor de conexiones
   */
  public Connection getConnection (){
    return connect;
  }

  /**
   * Finaliza la transaccion liberando los recursos ocupados.
   *
   * @exception DbConnectException Si se no se puede finalizar la transaccion.
   */
  public void commitTransaction () throws DbConnectException {

    try{
    	System.out.println("Realizando un commit");

      connect.commit();
    } catch(Exception e){
    	System.out.println("Error haciendo el commit");
      releaseTransaction();
      throw new DbConnectException("Error al finalizar transaccion");
    }
    releaseTransaction();
  }

  /**
   * Libera la transaccion
   * @exception DbConnectException Si se no se puede liberar la transaccion.
   */
  private void releaseTransaction() throws DbConnectException {
    try {
    	System.out.println("Liberando transaccion.");
      ConnectionsManager.getConnectionsManager().releaseConnection(connect);
    } catch (DbConnectException e) {
    	System.out.println("Error liberando la transaccion");
      throw new DbConnectException("Error al liberar la transacción");
    }
  }

  /**
   * Echa atras la transaccion liberando los recursos ocupados.
   *
   * @exception DbConnectException Si no se puede echar atras la transaccion.
   */
  public void rollbackTransaction() throws DbConnectException {

    try {
    	System.out.println("Realizando el rollback");

      connect.rollback();
    } catch(SQLException e) {
    	System.out.println("Error realizando rollback");
      releaseTransaction();
      throw new DbConnectException ("Error al hacer rollback");
    }
    releaseTransaction();
  }

  /**
   * Proporciona un Statement para realizar interacciones con la BBDD.
   *
   * @return Statement para interaccionar con la BBDD.
   * @exception DbConnectException Si se no se puede obtener una sentencia
   *  			  o la transaccion esta terminada.
   */
  public Statement getStatement() throws DbConnectException {
	  System.out.println("Obteniendo un statement.");
    try {
      return connect.createStatement();
    } catch(SQLException e) {
    	System.out.println("Error obteniendo un statement.");
      throw new DbConnectException("Error al realizar getStatement");
    }
  }

  /**
   * Proporciona un CallableStatement para realizar interacciones
   * con los procedimientos almacenados de una BBDD.
   *
   * @param llamada Llamada a los procedimientos/funciones.
   * @return Statement para interaccionar con la BBDD.
   * @exception DbConnectException Si se no se puede obtener una sentencia
   *  			  o la transaccion esta terminada.
   */
  public CallableStatement getCallableStatement(String llamada) throws DbConnectException {
	  System.out.println("Obteniendo un CallableStatement");
    try {
      return connect.prepareCall(llamada);
    } catch(SQLException e) {
    	System.out.println("Error obteniendo un CallableStatement");
      throw new DbConnectException ("Error al realizar getCallableStatement");
    }
  }

  /**
   * Proporciona un PreparedStatement para realizar interacciones
   * con los procedimientos almacenados de una BBDD.
   * @param sentencia La sentecia a ejecutar por el prepared statement.
   * @return PreparedStatement para interaccionar con la BBDD.
   * @exception DbConnectException Si se no se puede obtener una sentencia
   *  			  o la transaccion está terminada.
   */
  public PreparedStatement getPreparedStatement(String sentencia) throws DbConnectException {
	  System.out.println("Obteniendo un PreparedStatement");
    try {
      return connect.prepareStatement(sentencia);
    } catch(SQLException e) {
    	System.out.println("Error obteniendo un PreparedStatement");
      throw new DbConnectException ("Error al realizar getPreparedStatement");
    }
  }
}

