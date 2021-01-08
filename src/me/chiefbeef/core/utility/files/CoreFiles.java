package me.chiefbeef.core.utility.files;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.chiefbeef.core.StarryCore;
import me.chiefbeef.core.user.UserCore;
import me.chiefbeef.core.utility.Console;

public final class CoreFiles {

	
	public static enum C {
		CONFIG,
		LANG,
		PAGE,
		CURRENCY,
		REGION_DATA,
		PERMISSIONS,
		DIMS,
		DATA,
		ZONE,
		SUIT,
		EXODATA;
	}
	
	public static File m; // Directory for plugin
	
	public static File 
	configf;
	
	public static FileConfiguration 
	config, data;
	
	public static void createFiles() { // Load all files here
		String s;
		m = StarryCore.getInstance().getDataFolder();
		/*
		final String[]
			s1 = {"skyblock"},
			s2 = {"eco"};
		final File
			skyblockFolder = getFolder(s1),
			ecoFolder = getFolder(s2);
		*/
		config = createConfig(configf = createFile("config.yml"));
		
		data = loadYaml("data", "flat_data");
	}
	
	public static File createFile(final String file) {
		final File f = new File(StarryCore.getInstance().getDataFolder(), file);
		if (!f.exists()) {
			Console.info("Creating new file, " + file);
			f.getParentFile().mkdirs();
			StarryCore.getInstance().saveResource(file, false);
		}
		return f;
	}
	
	
	/**
	 * Creates default pre generated FileConfigurations / loads them for initial use if necessary
	 * @param f
	 * @param path
	 * @return
	 */
	private static FileConfiguration createConfig(final File file) {
		FileConfiguration yaml = new YamlConfiguration();
		try {
			yaml.load(file);
		} catch (IOException | InvalidConfigurationException e) {
			Console.severe("Error loading " + yaml.getCurrentPath() + ". Check console for stack trace...");
			e.printStackTrace();
		}
		return yaml;
	}
	
	/**
	 * Load a folder.
	 * @param path
	 * @return The folder specified, or root if undefined.
	 */
	public static File getFolder(String... path) {
		File fin = StarryCore.getInstance().getDataFolder();
		for (int i = 0; i < path.length; i++) {
			fin = new File((fin != null ? fin : m), File.separator + path[i]);
		}
		return fin;
	}
	
	/**
	 * Load a file from a folder. If one does not exist create one.
	 * @param folders Path of sub folders to contain the file.
	 * @param file the file to retrieve.
	 * @return
	 */ 
	public static FileConfiguration loadYaml(String file, String... path) {
		File
			folder = path.length > 0 ? getFolder(path) : StarryCore.getInstance().getDataFolder(),
			yamlf = new File(folder, File.separator + file + ".yml");
		
	    if (!yamlf.exists()) {
	    	Console.info("Creating new file: " + file);
	        try {
	        	FileConfiguration yaml = YamlConfiguration.loadConfiguration(yamlf);
	        	yamlf.getParentFile().mkdirs();
	            yamlf.createNewFile();
				yaml.save(yamlf);
				yaml.load(yamlf);
				return yaml;
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    } else {
	    	FileConfiguration yaml = YamlConfiguration.loadConfiguration(yamlf);
	    	return yaml;
	    }
	}
	
	/**
	 * Save a file into any sub folder of the plugin. If one does not exist create one.
	 * @param folder the folder to contain the file.
	 * @param file the file to retrieve.
	 * @return
	 */
	public static void saveYaml(final String file, final FileConfiguration yaml, String... path) {
		File
			folder = path.length > 0 ? getFolder(path) : StarryCore.getInstance().getDataFolder(),
			yamlf = new File(folder, File.separator + file + ".yml");
        try {
			yaml.save(yamlf);
			yaml.load(yamlf);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static FileConfiguration getUser(UUID u){
		return loadYaml(String.valueOf(u));
	}
	
	public static FileConfiguration getUser(UserCore user){
		return loadYaml(String.valueOf(user.getPlayer().getUniqueId()));
	}
	
	public static void saveUser(UUID uuid, FileConfiguration data) {
		saveYaml(String.valueOf(uuid), data, "data", "users");
	}
	
	public static void saveUser(UserCore user, FileConfiguration data) {
		saveYaml(String.valueOf(user.getPlayer().getUniqueId()), data, "data", "users");
	}
	
}
