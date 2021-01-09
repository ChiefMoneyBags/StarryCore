package me.chiefbeef.core.utility.assets;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import me.chiefbeef.core.customitem.CustomItem;
import me.chiefbeef.core.customitem.assets.CustomItemBuildPack;
import me.chiefbeef.core.utility.Console;
import me.chiefbeef.core.utility.files.Files;

/**
 * An {@link AssetHolder} is a generic type that holds some form of {@link TypeAssets} belonging to
 * the {@link Type}, not to any individual instance.
 * 
 * The {@link TypeAssets} are initially generated by a throwaway instance of this type however.
 * the reasoning behind getting the {@link TypeAssets} from a throwaway instance and
 * not a static method are that it may be benificial to the flexability of plugins design
 * structures to allow types that extend each other to override and manipulate the assets
 * being created by their parents, It also forces implementation of only a few required methods
 * whereas if it were static, you would need to remember to implement the static methods in every
 * registered type. Many of the methods overriden are taken care of in higher level abstract classes.
 * 
 * Due to the fact we need to create an inctance to get the {@link TypeAssets} AssetHolders cannot have
 * constructors with parameters and must instead follow a builder pattern, this is because all the way
 * up in the {@link AssetHolder} API we dont know any of the information required to instantiate these
 * different types, which would cause errors. For the most part however the AssetHolders
 * will be built for you dynamically by the various API's so you only need to override the build method
 * in many cases if you need to set stuff up. All the required data will be present for you at this time.
 * 
 * These assets are needed by various api's in the core to store data about a {@link Type}
 * when it is registered, considering it should supercede all instances. 
 * this included but is not limited to,
 * 
 * - The {@link Plugin} that registered the {@link Type}
 *    
 * - The config file for this {@link Type}, if applicable.
 * 
 * - The type that was registered and a method to instantiate it.
 * 
 * Plugins implementing these assets may further extends the {@link TypeAssets} class
 * and add their own {@link Type} specific information to the assets.
 * 
 * @author Kevin
 *
 * @param <T> The common parent all children of this {@link AssetHolder} must be assignable from,
 * or, the target {@link Type}.
 * This will be the same type argument as the {@link TypeAssets} this {@link AssetHolder} will create.
 * It will also most likely just be the {@link Type} where this interface is implemented, Example
 * 
 * (public abstract class Example implements AssetHolder<Example>)
 * 
 * will create assets for the type example. TypeAssets<Example>
 * 
 * In most cases there will be a specific type of assets for each AssetHolder<T> you can cast to, example
 * (public class ExampleAssets implements TypeAssets<Example>)
 * 
 * These assets will hold more type specific information about (Example) than its parent assets TypeAssets<Example>.
 * 
 * Another note, if you need your plugin instance in the AssetHolder instance it can be located in your {@link TypeAssets}
 * or obtained using {@link AssetHolder#getParentPlugin()}. No need for static getters :)
 */
public interface AssetHolder<T> {
	
	
	public default void createConfig() throws IOException, InvalidConfigurationException {
		if (hasConfig()) {
			TypeAssets<T> assets = getAssets();
			Console.debug("--<[ Initializing AssetHolder<" + getClass().getName() + "> files...");
			File customConfigf = new File(getConfigDirectory(), getConfigName() + ".yml");
			if (!customConfigf.exists()) {
				Console.info("Config for AssetHolder<" + getClass().getName() + "> does not exist, creating one...");
				customConfigf.getParentFile().mkdirs();
				Files.createConfig(getParentPlugin().getResource(getEmbeddedConfigName() + ".yml"), customConfigf);
			}
			FileConfiguration customConfig = new YamlConfiguration();
			customConfig.load(customConfigf);
			
			assets.setYamlFile(customConfigf);
			assets.setYamlConfiguration(customConfig);
		}
	}
	
	
	/**
	 * 
	 * @return The directory where this types config will be located.
	 */
	public default File getConfigDirectory() {
		return getParentPlugin().getDataFolder();
	}
	
	/**
	 * 
	 * @return The plugin that registered this type.
	 */
	public default Plugin getParentPlugin() {
		return getAssets().getParentPlugin();
	}

	/**
	 * 
	 * @return The name of the config for this type.
	 */
	public default String getConfigName() {
		return getAssets().getLabel().toLowerCase();
	}

	/**
	 * 
	 * @return The embedded resource for this types config.
	 */
	public default String getEmbeddedConfigName() {
		return this.getConfigName();
	}
	
	/**
	 * Generate a new set of {@link TypeAssets} for the {@link Type}
	 * @param plugin The plugin instance that is responsible for the {@link Type}
	 * @return
	 */
	public abstract TypeAssets<T> createAssets();
	
	/**
	 * @return The assets belonging to the {@link Type}.
	 */
	public abstract TypeAssets<T> getAssets();

	/**
	 * 
	 * @return true if this custom type should have its own config file.
	 */
	public abstract boolean hasConfig();
	
	/**
	 * Due to the dynamic nature of AssetHolder instancing by the various API's, AssetHolders
	 * are required to have empty / no constructors. As such AssetHolders follow a build pattern
	 * where all information needed during the objects initialization is added via methods and
	 * the objects build method is invoked in place of a constructor.
	 * 
	 * This also allows the API's to construct throwaway instances of the AssetHolders to obtain {@link TypeAssets}
	 * without actually building a legitimate instance, which would inevitably cause a jumbled mess of errors
	 * and spaghetti code since the API wouldnt have any of the necessary information at this point during the
	 * runtime to construct a full instance.
	 * 
	 * for example, how do you construct an instance of UserExtension to invoke createAssets() when the UserExtension
	 * requires a UserCore instance in its constructor to load player data, then CustomItem requires an ItemStack in its constructor
	 * to load ItemMeta. the API cannot know these things and as such cannot obtain any {@link TypeAssets}.
	 */
	public abstract T build(AssetBuildPack pack);
	
	
	/**
	 * Build a new instance of this {@link AssetHolder} without any initial parameters.
	 * @throws UnsupportedOperationException if the {@link AssetHolder} type requires an {@link AssetBuildPack}
	 */
	public abstract T build();
	
	/**
	 * 
	 * @return The unique label of this type
	 */
	public abstract String getLabel();


}
