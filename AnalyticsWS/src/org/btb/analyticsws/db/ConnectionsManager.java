/**
 * <p> Web Service Application Programming Interface</p>
 * 
 *

 * 
 * @author Bernardo Tabuenca Archilla
 * @version 1.0
 */
package org.btb.analyticsws.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Logger;

public class ConnectionsManager {

	private Logger log = Logger.getLogger("ConnectionsManager");
	
	private static final String DB_URL = "DBURL";
	private static final String DB_USER = "DBUSER";
	private static final String DB_PASSWORD = "DBPASSWORD";
	private static final String DB_MAX_CONN = "DBMAXCONN";
	
	static ConnectionsManager instance = null;
	private DBConnectionPool pool = null;
	static int clients;
	Vector drivers = new Vector();

	/**
	 * Constructor del objeto que modela el gestor de los pools de conexiones.
	 *
	 * @exception DbConnectException
	 *                Si se no se puede crear el gestor.
	 */
	private ConnectionsManager() throws DbConnectException {
		init();
	}

	/**
	 * Constructor del objeto que modela el gestor de los pools de conexiones.
	 *
	 * @return Instancia sincronizada del objeto ConnectionsManager
	 * @exception DbConnectException
	 *                Si se no se puede crear el gestor.
	 */
	static synchronized public ConnectionsManager getConnectionsManager() throws DbConnectException {
		if (instance == null) {
			instance = new ConnectionsManager();

			System.out.println("Inicializando la instancia del pool.");
			clients++;
		}
		return instance;
	}

	/**
	 * Metodo que lee el fichero de configuracion. Inicializa los atributos que
	 * controlan las trazas, los errores de log, y llama a los metodos para
	 * cargar los drivers de conexion a las bases de datos, y para la creacion
	 * de pools.
	 *
	 * @exception DbConnectException
	 *                Si falla la carga de algun atributo.
	 */
	private void init() throws DbConnectException {
		loadDrivers();
		createPools();
	}

	/**
	 * Metodo que carga y registra los drivers de conexion a las bases de datos,
	 * leyendolos del fichero de configuracion.
	 *
	 * @exception DbConnectException
	 *                Si falla la carga de los drivers desde el fichero de
	 *                configuracion.
	 */
	private void loadDrivers() throws DbConnectException {
		String driverName = "oracle.jdbc.driver.OracleDriver";

		try {

			log.fine("loading drivers...");
			Driver driver = (Driver) Class.forName(driverName).newInstance();
			DriverManager.registerDriver(driver);
			drivers.addElement(driver);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in ConnectionsManager.loadDrivers():" + e.getMessage());
			throw new DbConnectException("No se puede registrar driver " + driverName);
		}
	}

	/**
	 * Metodo que crea los pools de conexiones leyendo para cada pool la url del
	 * servidor, el login, la password, y el numero maximo de conexiones de
	 * ficheros de configuracion. Introduce el pool creado en una tabla Hash de
	 * pools.
	 *
	 * @exception DbConnectException
	 *                Si falla la creacion del pool de conexiones.
	 */
	private void createPools() throws DbConnectException {


		String url = "";
		String user = "";
		String password = "";
		String maxConn = "";

		
		Properties prop = new Properties();
		String propFileName = "config.properties";
		
		InputStream in = getClass().getResourceAsStream(propFileName);
		try {
			prop.load(in);
						
			url = prop.getProperty(DB_URL);
			user = prop.getProperty(DB_USER);
			password = prop.getProperty(DB_PASSWORD);
			maxConn = prop.getProperty(DB_MAX_CONN);						
			
			in.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		
		
		

		// Desarrollo
		// String url = "jdbc:oracle:thin:@10.254.222.73:1521:OR1";
		// String user = "cbarreras";
		// String password = "cbarreras";
		// String maxConn = "300";

		// Produccion
//		String url = "jdbc:oracle:thin:@10.254.222.72:1521:OR1";
//		String user = "CBARRERAS";
//		String password = "CBARRERAS";
//		String maxConn = "300";

		int max;
		try {
			max = Integer.valueOf(maxConn).intValue();
		} catch (NumberFormatException e) {
			System.out.println("Exception in ConnectionsManager.createPools():" + e.getMessage());
			max = 0;
			throw new DbConnectException("Valor de maxconn invalido " + maxConn);
		}

		// Creacion del pool de conexiones
		pool = new DBConnectionPool(url, user, password, max);

	}

	/**
	 * Metodo que devuelve el pool correspondiente al nombre pasado como
	 * parametro. El pool es buscado en la tabla Hash de pools.
	 * 
	 * @param name
	 *            String Nombre del pool
	 * @return Connection Pool correspondiente al nombre pasado como parametro.
	 */
	public Connection getConnection(String name) {
		if (pool != null) {
			return pool.getConnection();
		} else {
			System.out.println("Connection pool is null!!");
		}
		return null;
	}

	/**
	 * Metodo que devuelve el pool correspondiente al nombre y al tiempo de
	 * conexion, que se pasan como parametros.
	 * 
	 * @param time
	 *            long Tiempo de la conexion
	 * @return Connection Pool con ese nombre y cuya conexion lleva establecida
	 *         el tiempo indicado en ms.
	 *
	 */
	public Connection getConnection(long time) {
		if (pool != null) {
			return pool.getConnection(time);
		} else {
			System.out.println("Connection pool is null!!");
		}
		return null;
	}

	/**
	 * Metodo que libera la conexion indicada del pool correspondiente a ese
	 * nombre.
	 * 
	 * @param con
	 *            Connection Conexion que se de sea liberar
	 */
	public void releaseConnection(Connection con) {
		if (pool != null && con != null) {
			pool.freeConnection(con);
			System.out.println("Liberando transacciones del pool");
		} else {
			System.out.println("La conexion no existe");
		}
	}

}
