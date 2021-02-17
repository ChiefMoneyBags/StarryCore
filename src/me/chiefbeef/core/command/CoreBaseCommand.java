package me.chiefbeef.core.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.chiefbeef.core.command.assets.CoreSubCommandAssets;
import me.chiefbeef.core.command.handling.CoreCommand;
import me.chiefbeef.core.command.handling.CoreCommandExecutor;
import me.chiefbeef.core.command.handling.CoreTabCompleter;
import me.chiefbeef.core.utility.assets.TypeAssets;

public abstract class CoreBaseCommand extends CoreCommand {
	
	private Plugin plugin;
	private List<CoreSubCommandAssets> subCommands = new ArrayList<>();
	
	private CoreCommandExecutor executor;
	private CoreTabCompleter tab;

	/**
	 * The method getCommand is hidden in the plugin interface so i will ask for javaplugin here.
	 * @param plugin the plugin registereing the new command
	 */
	public CoreBaseCommand(JavaPlugin plugin) {
		this.plugin = plugin;
		this.executor = new CoreCommandExecutor(this);
		plugin.getCommand(getLabel()).setExecutor(executor);
		plugin.getCommand(getLabel()).setTabCompleter(tab);
	}
	
	public Plugin getParentPlugin() {
		return plugin;
	}
	
	public void registerSubCommand(Class<? extends CoreSubCommand> type) {
		TypeAssets<CoreSubCommand> assets = CoreSubCommand.getRegistry().getAssets(type);
		if (!(assets instanceof CoreSubCommandAssets)) {
			return;
		}
		subCommands.add((CoreSubCommandAssets) assets);
	}
	
	public CoreSubCommandAssets getSubCommand(String label) {
		Optional<CoreSubCommandAssets> optional = subCommands.stream().filter(sub -> sub.getLabel().equalsIgnoreCase(label)).findFirst();
		return optional.isPresent() ? optional.get() : null;
	}
	
	public CoreSubCommandAssets getSubCommandWithAlias(String label) {
		CoreSubCommandAssets assets = getSubCommand(label);
		if (assets != null) {
			return assets;
		}
		Optional<CoreSubCommandAssets> optional = subCommands.stream().filter(sub -> sub.getAliases().contains(label)).findFirst();
		return optional.isPresent() ? optional.get() : null;
	}
	

}
