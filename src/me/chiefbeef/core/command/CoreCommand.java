package me.chiefbeef.core.command;

import java.util.List;

import org.bukkit.command.CommandSender;

import me.chiefbeef.core.command.assets.CoreCommandBuildPack;
import me.chiefbeef.core.customitem.CustomItem;
import me.chiefbeef.core.utility.assets.AssetHolder;
import me.chiefbeef.core.utility.assets.AssetRegistry;
import me.chiefbeef.core.utility.assets.TypeAssets;

public abstract class CoreCommand implements AssetHolder<CoreCommand> {

	/**
	 * The Singleton object responsible for managing all the {@link CustomItem} types and their {@link TypeAssets}
	 * that are registered in this API. 
	 */
	private static final AssetRegistry<CoreCommand> registry = new AssetRegistry<>();
	
	/**
	 * @return The {@link AssetRegistry} for the {@link CustomItem} API
	 */
	public static AssetRegistry<CoreCommand> getRegistry() {
		return registry;
	}
	
	/**
	 * Execute this command as the {@link CommandSender} given.
	 * @param sender The {@link CommandSender} that is executing the command.
	 */
	public abstract void executeAs(CommandSender sender);
	
	/**
	 * Used for tab completion, get a list of possible next arguments for the current command.
	 * @param sender The {@link CommandSender} tab comleting this command.
	 * @return The list of possible arguments.
	 */
	public abstract List<String> getPotentialArguments(CommandSender sender);

	@Override
	public TypeAssets<CoreCommand> createAssets() {
		return null;
	}

	@Override
	public TypeAssets<CoreCommand> getAssets() {
		return null;
	}

	@Override
	public boolean hasConfig() {
		return false;
	}

	@Override
	public CoreCommand build(CoreCommandBuildPack pack) {
		return this;
	}

	@Override
	public String getLabel() {
		return null;
	}

}
