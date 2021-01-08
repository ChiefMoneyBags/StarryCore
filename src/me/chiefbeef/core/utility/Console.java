package me.chiefbeef.core.utility;

import org.bukkit.Bukkit;

public final class Console {

	public static void generateException(String message) {
		try {
			throw new Exception(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void info(String m){
		Bukkit.getLogger().info(m);
	}
	
	public static void warn(String m){
		Bukkit.getLogger().warning(m);
	}
	
	public static void severe(String m){
		Bukkit.getLogger().severe(m);
	}
	
	//lmao
	public static void extreme(String m) {
		for (int x = 0; x < 23; x++) severe((x > 10 && x < 13) ? m : (x == 11 || x == 13) ? "--------------" : "!!!");
	}

	private static boolean debug = true;
	public static void debug(Object... objects) {
		if (debug) {
			for (Object obj: objects) {
				Bukkit.getLogger().warning("[DEBUG] " + String.valueOf(obj));	
			}	
		}
	}
	
}
