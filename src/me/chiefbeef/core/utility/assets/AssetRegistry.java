package me.chiefbeef.core.utility.assets;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.plugin.Plugin;

import me.chiefbeef.core.utility.Console;

public class AssetRegistry<T> {
	
	private final Map<Class<? extends T>, TypeAssets<T>> assetMap = new ConcurrentHashMap<>();
	
	/**
	 * See if a type is registered.
	 * @param type The type
	 * @return true if the type is registered in this api.
	 */
	public boolean isRegistered(Class<? extends T> type) {
		return assetMap.containsKey(type);
	}
	
	/**
	 * Get the assets for the given type in this api.
	 * @param type The type
	 * @return The assets for this type.
	 */
	public TypeAssets<T> getAssets(Class<? extends T> type) {
		return assetMap.get(type);
	}
	
	public TypeAssets<T> getAssets(String label) {
		Optional<TypeAssets<T>> optional = assetMap.values().stream().filter(assets -> assets.getLabel().equals(label)).findFirst();
		return optional.isPresent() ? optional.get() : null;
	}
	
	
	/**
	 * Get all the registered assets in this api.
	 * @return All the assets
	 */
	public Map<Class<? extends T>, TypeAssets<T>> getAssets() {
		return assetMap;
	}
	
	
	/**
	 * 
	 * Registration
	 * 
	 */
	
	
	/**
	 * Test a class to see if it is built to the specifications of the api it is being registered in.
	 * @return false if the class failed the test cases.
	 */
	public boolean testClass(Class<? extends T> type) {
		if (assetMap.containsKey(type)) {
			Console.generateException("A type cannot be registered more than once! (" + type.toString() + ")");
			return false;
		}
		if (!type.isAssignableFrom(AssetHolder.class)) {
			Console.generateException("Any type being registered in an AssetRegistry MUST implement AssetHolder!");
			return false;
		}
		try {
			type.getConstructor().newInstance();
		} catch (NoSuchMethodException e) {
			Console.generateException("All AssetHolder types MUST follow a builder pattern and be void of constructors or have a constructor with no parameters!");
			return false;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | SecurityException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Load the {@link TypeAssets} for a class.
	 * @param plugin
	 * @param type
	 * @return
	 */
	// I gave up trying to get rid of this unchecked cast the generics are confusing me rn.
	// I understand the problem i just dont know how to fix it yet.
	@SuppressWarnings("unchecked")
	public TypeAssets<T> loadAssets(Plugin plugin, Class<? extends T> type) {
		if (!testClass(type)) {
			return null;
		}
		try {
			TypeAssets<T> assets = ((AssetHolder<T>)type.getConstructor().newInstance()).createAssets();
			assets.setParentPlugin(plugin);
			return assets;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Register a {@link AssetHolder} type using its {@link TypeAssets}.
	 * This option allows you to register your {@link AssetHolder} types in
	 * a dynamically using information held within the assets if they may
	 * depend on eachother. This was a problem that arose in my plugin 
	 * ExoSuit with technology modules which is part of the reason for this API. 
	 * 
	 * To get the {@link TypeAssets} you must invoke {@link AssetRegistry#loadAssets(Plugin, Class)}
	 * @param assets The assets of the {@link Type} to register.
	 */
	public void register(TypeAssets<T> assets) {
		Class<? extends T> type = assets.getType();
		Console.debug("AssetManager<" + this.getClass().toString() + "> Registering: " + type.toString());
		assetMap.put(type, assets);
	}
	
	/**
	 * Register a {@link Type} in the {@link AssetRegistry}.
	 * One and done.
	 * @param plugin The plugin registering the {@link Type}
	 * @param type The {@link Type}
	 */
	public void register(Plugin plugin, Class<? extends T> type) {
		Console.debug("AssetManager<" + this.getClass().toString() + "> Registering: " + type.toString());
		TypeAssets<T> assets = loadAssets(plugin, type);
		if (assets == null) {
			return;
		}
		register(assets);
	}
	
	/**
	 * Unregister a type.
	 * @param type The type to be unregistered
	 */
	public void unregister(final Class<?> type) {
		assetMap.remove(type);
	}
	
	/**
	 * Unregister all types registered by a plugin.
	 * @param plugin the plugin to unregister.
	 */
	public void unregister(Plugin plugin) {
		assetMap.values().stream().filter(assets -> assets.getParentPlugin().equals(plugin)).forEach(assets -> unregister(assets.getType()));
	}
	
}
