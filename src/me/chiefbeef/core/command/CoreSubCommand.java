package me.chiefbeef.core.command;

import java.io.File;
import java.util.List;

import me.chiefbeef.core.command.assets.CoreSubCommandAssets;
import me.chiefbeef.core.command.handling.CoreCommand;
import me.chiefbeef.core.customitem.CustomItem;
import me.chiefbeef.core.utility.assets.AssetBuildPack;
import me.chiefbeef.core.utility.assets.AssetHolder;
import me.chiefbeef.core.utility.assets.AssetRegistry;
import me.chiefbeef.core.utility.assets.TypeAssets;

public abstract class CoreSubCommand extends CoreCommand implements AssetHolder<CoreSubCommand> {

	/**
	 * The arguments currently contained within this command.
	 */
	private String[] args;
	
	/**
	 * The Singleton object responsible for managing all the {@link CustomItem} types and their {@link TypeAssets}
	 * that are registered in this API. 
	 */
	private static final AssetRegistry<CoreSubCommand> registry = new AssetRegistry<>();
	
	/**
	 * @return The {@link AssetRegistry} for the {@link CustomItem} API
	 */
	public static AssetRegistry<CoreSubCommand> getRegistry() {
		return registry;
	}
	

	/**
	 * Commands dont really need their own config file, i prefer general config.yml files for most things. 
	 * If you want a specific command to have its own config file you can override and return true.
	 */
	@Override
	public boolean hasConfig() {
		return false;
	}
	
	@Override
	public File getConfigDirectory() {
		return new File(getParentPlugin().getDataFolder() + "Commands");
	}
	
	/**
	 * Get the arguments currently contained within the command.
	 * @return the arguments currently contained in the command.
	 */
	public String[] getArguments() {
		return args;
	}
	
	/**
	 * Set the arguments for this command.
	 * @param args The arguments for the command
	 */
	public void setArguments(String[] args) {
		this.args = args;
	}
	
	@Override
	public TypeAssets<CoreSubCommand> createAssets() {
		return new CoreSubCommandAssets(this.getClass(), this.getLabel(), this.getAliases());
	}

	@Override
	public TypeAssets<CoreSubCommand> getAssets() {
		return registry.getAssets(this.getClass());
	}
	
	@Override
	public void applyBuildPack(AssetBuildPack pack) {
		return;
	}

	@Override
	public CoreSubCommand build() {
		return this;
	}
	
	@Override
	public boolean isBuilt() {
		return true;
	}
	
	/**
	 * I dont think commands need a friendly name
	 */
	@Override
	public String getFriendlyName() {
		return getLabel();
	}
	
	public abstract List<String> getAliases();

}
