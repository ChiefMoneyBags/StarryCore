package me.chiefbeef.core.utility.files;

import org.bukkit.configuration.file.FileConfiguration;

public final class Data {

	private static Data data;
	private static boolean
	useSql, useFlat;
	
	public static Data getInstance() {
		return data == null ? data = new Data() : data;
	}
	
	private FileConfiguration
	environment,
	general;
	

	
	public static String getString() {
		return null;
	}
	
	public static int getInt() {
		return 0;
	}
	
	public static double getDouble() {
		return 0;
	}
	
	public static enum DataFile {
		USER,
		ENVIRONMENT;
		
		private DataFile() {
			
		}
	}
	
}
