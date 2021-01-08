package me.chiefbeef.core.data;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DataBridge {

	private static Map<String, DataBase> registry = new HashMap<>();
	
	public static void initCoreDataServers() {
		try {
			registry.put("testdb", new DataBase("localhost", 3306, "testdb", "kevin", "Chief"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static DataBase getDataBase(String label) {
		return registry.get(label);
	}
	
	public static boolean exists(String label) {
		return registry.containsKey(label);
	}
	

}
