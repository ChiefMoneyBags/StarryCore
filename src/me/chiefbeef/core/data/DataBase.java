package me.chiefbeef.core.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Acts as a communicator with the database.
 * Note that this object does not store values in it from the database, Any queries to the database server
 * will still need to be sent over communication streams.
 * @author Kevin
 */
public class DataBase {

	private Connection connection;
	
	private Map<String, DataTable> cache = new HashMap<>();
	
	public DataBase(String ip, int port, String database, String user, String pass) throws SQLException {
		connection = DriverManager.getConnection(
				"jdbc:mysql://" + ip + ":" + port + "/" + database, user, pass);
	}
	
	/**
	 * Pull a table from the database.
	 * This method will block the current thread until a response is received.
	 * @param key
	 * @return
	 * @throws SQLException 
	 */
	public synchronized DataTable getTable(String name) throws SQLException {
		return cache.getOrDefault(name, new DataTable(this, name));
	}
	
	/**
	 * 
	 * @return The connection to the DataBase 
	 */
	public Connection getConnection() {
		return connection;
	}
	
}
