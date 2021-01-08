package me.chiefbeef.core.utility.assets;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import me.chiefbeef.core.utility.Console;

/**
 * {@link TypeAssets} is a generic object that holds {@link Type} specific information about an {@link AssetHolder}. 
 * @author Kevin
 *
 * @param <T> The common parent {@link Type} that all {@link AssetHolder}s using these {@link TypeAssets} share.
 * i know thats a really bad explanation please see the docs for {@link AssetHolder}.  
 */
public abstract class TypeAssets<T> {

	private Plugin parentPlugin;
	private Class<? extends T> type;
	private File configf;
	private FileConfiguration config;
	private String label;
	
	public TypeAssets(Class<? extends T> type, String label) {
		this.type = type;
		this.label = label;
	}
	
	public void setParentPlugin(Plugin plugin) {
		this.parentPlugin = plugin;
	}
	
	public Plugin getParentPlugin() {
		return parentPlugin;
	}
	
	public void setYamlFile(File file) {
		this.configf = file;
	}
	
	public void setYamlConfiguration(FileConfiguration config) {
		this.config = config;
	}
	
	public File getFile() {
		return configf;
	}
	
	public FileConfiguration getYamlConfiguration() {
		return config;
	}
	
	public Class<? extends T> getType() {
		return type;
	}
	
	public String getLabel() {
		return label;
	}
	
	/**
	 * Create a new instance of the target {@link Type}. This method constructs the object
	 * using an empty constructor and as such the returned object will need any required fields
	 * defined and its {@link AssetHolder#build()} method invoked before it is safe to use.
	 * @return a new instance of the {@link Type}
	 */
	public T newInstance() {
		try {
			return type.getConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException e) {
			e.printStackTrace();
			return null;
		} catch (NoSuchMethodException e) {
			Console.generateException("For an AssetHolder<> type to be dynamically instantiated by the core it must contain an empty constructor and follow a builder pattern for its construction.");
			return null;
		}
	}

}
