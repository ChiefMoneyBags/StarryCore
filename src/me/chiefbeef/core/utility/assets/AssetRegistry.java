package me.chiefbeef.core.utility.assets;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.configuration.InvalidConfigurationException;
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
	 * Test a class to see if it is built to the specifications of the {@link AssetHolder} API.
	 * @return false if the class failed the test cases.
	 */
	public boolean testClass(Class<? extends T> type) {
		if (assetMap.containsKey(type)) {
			Console.generateException("A type cannot be registered more than once! (" + type.getSimpleName() + ")");
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
	// TODO I know for a fact this type is an AssetHolder<T> otherwise it couldnt be registered, i just cannot
	// figure out how to explicitly declare it as such without casting, i haven't learned enough about generics yet, i'll come back to it later.
	@SuppressWarnings("unchecked")
	public TypeAssets<T> loadAssets(Plugin plugin, Class<? extends T> type) {
		Console.debug("--| Loading assets...");
		if (!testClass(type)) {
			Console.debug("--| Type failed test cases! returning null...", "");
			return null;
		}
		try {
			TypeAssets<T> assets = ((AssetHolder<T>) type.getConstructor().newInstance()).createAssets();
			assets.setParentPlugin(plugin);
			Console.debug("--| Assets were loaded: " + assets.getClass().getSimpleName(), "");
			return assets;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Register a {@link AssetHolder} type using its {@link TypeAssets}.
	 * This option allows you to register your {@link AssetHolder} types in
	 * a dynamic order using information held within the assets if they may depend on eachother.
	 * <p>
	 * This was a problem that arose in my plugin ExoSuit with technology modules and their upgrade modules.
	 * Before the assets were obtained i couldnt know which types were dependent on
	 * eachother without explicity stating it, which is not at all dynamic. i prefer the types themselves to
	 * declare their dependencies while being loaded.
	 * </p>
	 * To get the {@link TypeAssets} you must invoke {@link AssetRegistry#loadAssets(Plugin, Class)}
	 * @param assets The assets of the {@link Type} to register.
	 */
	// TODO I know for a fact this type is an AssetHolder<T> otherwise it couldnt be registered, i just cannot
	// figure out how to explicitly declare it as such without casting, i haven't learned enough about generics yet, i'll come back to it later.
	@SuppressWarnings("unchecked")
	public void register(TypeAssets<T> assets) {
		Class<? extends T> type = assets.getType();
		Console.debug("--| AssetRegistry Registering: " + type.getSimpleName());
		assetMap.put(type, assets);
		
		try {
			((AssetHolder<T>) assets.newInstance()).createConfig();
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	public void registerAll(Plugin plugin, List<Class<? extends T>> types) {
		for (Class<? extends T> type : types) {
			register(plugin, type);
		}
	}
	
	/**
	 * Register a {@link Type} in the {@link AssetRegistry}.
	 * One and done.
	 * @param plugin The plugin registering the {@link Type}
	 * @param type The {@link Type}
	 */
	public void register(Plugin plugin, Class<? extends T> type) {
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
