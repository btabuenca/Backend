/**
 * <p> Web Service Application Programming Interface</p>
 * 
 *

 * 
 * @author Bernardo Tabuenca Archilla
 * @version 1.0
 */
package org.btb.analyticsws.db.dao;

import org.apache.log4j.*;
import org.btb.analyticsws.db.dao.DAOException;

import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;

public class GenericDAO {

 
  private Statement stmt = null;
  private ResultSet rset = null;
  private Connection conn = null;
  
  protected SimpleDateFormat fechaHoraFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
  protected SimpleDateFormat fechaFormat = new SimpleDateFormat("dd/MM/yyyy");

  protected void executeQuery(String query) throws DAOException {

	  System.out.println("Sentencia: " + query);


    try {
      stmt = conn.createStatement();
      rset = stmt.executeQuery(query);
    }
    catch (SQLException e) {
    	System.out.println(e.getMessage());
      try {
        rset.close();
      }
      catch (Exception ex) {};
      try {
        stmt.close();
      }
      catch (Exception ex) {};
      throw new DAOException("Error: " + e.getMessage());
    }
  } //executeQuery()


  protected void executePreparedQuery(String consulta, ArrayList parametros,
                                      PreparedStatement pstmt) throws
      DAOException {

    //introducimos los parametros al PreparedStament
    try {
      for (int i = 0, j = 1; i < parametros.size(); i += 2, j++) {
        if ( (parametros.get(i + 1)).getClass().getName().equals("java.lang.String") ||
             ((String)parametros.get(i+1)).equals("int")) {
          pstmt.setString(j, (String) parametros.get(i));
        }
      }
      //Ejecutamos la sentencia

      rset = pstmt.executeQuery();
    }
    catch (SQLException e) {
    	System.out.println(e.getMessage());
      try {
        rset.close();
      }
      catch (Exception ex) {}
      ;
      try {
        pstmt.close();
      }
      catch (Exception ex) {}
      ;
      throw new DAOException("Error: " + e.getMessage());
    }
  } //executeQuery()

  /**
   * Ejecuta una sentencia de update que recibe como parametro
   * @param update String con la setencia update a ejecutar
   * @return int El número de filas modificadas por la sentencia
   * @exception DAOException Si se produce algun error en la ejecucion de la consulta.
   */
  protected int executeUpdate(String update) throws DAOException {

	  System.out.println("Sentencia: " + update);

    //Ejecutamos la sentencia
    try {
      stmt = conn.createStatement();
      return stmt.executeUpdate(update);
    }
    catch (SQLException e) {
    	System.out.println(e.getMessage());
      try {
        stmt.close();
      }
      catch (Exception ex) {}
      ;
      throw new DAOException("Error: " + e.getMessage());
    }
  } //executeUpdate()

  /**
   * Ejecuta el update del objeto preparedStament que mantiene la instancia
   * de la clase con los parámetros que se reciben en el ArrayList.
   *
   * IMPORTANTE:
   * -----------------------------------------------------------------------
   * EL OBJETO PREPAREDSTAMENT DE LA INSTANCIA DEBE TENER ASIGNADA UNA QUERY
   * ANTES DE LA LLAMADA A ESTE MÉTODO O SE PRODUCIRÁ UN ERROR
   *
   * EL PARÁMETRO CONSULTA (STRING) SÓLO SE PASA A EFECTOS DE LOG.
   *-------------------------------------------------------------------------
   * @param update String con el update almacenado en el PreparedStatment
   * @param parametros ArrayList con los parametros del update, donde
   * cada dos posiciones en (i) tenemos un parametro y en (i+1) su tipo empezando
   * i en 0.
   * @param pstmt PreparedStatment inicializado con la sentecia update a ejecutar
   * @exception DAOException Si se produce algun error en la ejecucion del update.
   * @return int El número de filas modificadas por la sentencia
   */
  protected int executePreparedUpdate(String update, ArrayList parametros,
                                      PreparedStatement pstmt) throws
      DAOException {

    //introducimos los parametros al PreparedStament
    try {
      for (int i = 0, j = 1; i < parametros.size(); i += 2, j++) {
        if ( (parametros.get(i + 1)).getClass().getName().equals("java.lang.String") ||
             ((String)parametros.get(i+1)).equals("int")) {
          pstmt.setString(j, (String) parametros.get(i));
        }
      }

      //Ejecutamos la sentencia

      return pstmt.executeUpdate();
    }
    catch (SQLException e) {
    	System.out.println(e.getMessage());
      try {
        pstmt.close();
      }
      catch (Exception ex) {};
      throw new DAOException("Error: " + e.getMessage());
    }
  } //executeUpdate()

  /**
   * libera los recursos utilizados en la ejecucion de una query (el resultset y el statement)
   * @exception DAOException Si se produce algun error en la ejecucion de la consulta.
   */
  protected void releaseResourcesQuery() throws DAOException {

    try {
      if (rset != null) rset.close();
      if (stmt != null) stmt.close();
    }
    catch (SQLException e) {
    	System.out.println(e.getMessage());
      try {
        if (rset != null) rset.close();
      }
      catch (Exception ex) {};
      try {
        if (stmt != null) stmt.close();
      }
      catch (Exception ex) {};
      throw new DAOException("Error liberando recursos en consulta a la BBDD");
    }
  } 

  /**
   * libera los recursos utilizados en la ejecucion de querys (el resultset y el statement)
   * mediante el objeto PreparedStatmen
   * @param pstmt PreparedStatment inicializado con la query a ejecutar
   * @exception DAOException Si se produce algun error en la ejecucion de la consulta.
   */
  public void releaseResourcesPreparedQuery(PreparedStatement pstmt) throws DAOException {

    try {
      if (rset != null) rset.close();
      if (pstmt != null) pstmt.close();
    }
    catch (SQLException e) {
    	System.out.println(e.getMessage());
      try {
        if (rset != null) rset.close();
      }
      catch (Exception ex) {};
      try {
        if (pstmt != null) pstmt.close();
      }
      catch (Exception ex) {};
      throw new DAOException(
          "Error liberando recursos en consulta 'prepared' a la BBDD");
    }
  }

  /**
   * libera los recursos utilizados en la ejecucion de un update (el statement)
   * @exception DAOException Si se produce algun error en la ejecucion del la update.
   */
  protected void releaseResourcesUpdate() throws DAOException {

    try {
      if(stmt!=null)
        stmt.close();
    }
    catch (SQLException e) {
    	System.out.println(e.getMessage());
      throw new DAOException("Error liberando recursos en consulta a la BBDD");
    }
  } 

  /**
   * libera los recursos utilizados en la ejecucion de un update (el propio
   * PreparedStatement) mediante el objeto PreparedStatement
   * @param pstmt PreparedStatment inicializado que se va a eliminar
   * @exception DAOException Si se produce algun error en la ejecucion del la update.
   */
  public void releaseResourcesPreparedUpdate(PreparedStatement pstmt) throws DAOException {

    try {
      if(pstmt!=null)
        pstmt.close();
    }
    catch (SQLException e) {
    	System.out.println(e.getMessage());
      throw new DAOException(
          "Error liberando recursos en update de tipo 'prepareda'");
    }
  } //freeResourcesUpdate()

  /**
   * Asigna una conexion al a variable de instancia conn
   * @param conn objeto Connection sobre el que ejecutar consultas
   */
  protected void setConexion(Connection conn) {
    this.conn = conn;
  }

  /**
   * devuelve el objeto resultset de la clase (utilizar tras la realizacion de una query)
   * @return ResultSet conteniendo los resultados de una consulta o null si no se ha ejecutado ninguna consulta
   */
  protected ResultSet getResultSet() {
    return rset;
  }

} //class GenericDAO
