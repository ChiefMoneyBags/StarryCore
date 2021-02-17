package me.chiefbeef.core.command.handling;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import me.chiefbeef.core.command.CoreBaseCommand;
import me.chiefbeef.core.command.CoreSubCommand;
import me.chiefbeef.core.command.assets.CoreSubCommandAssets;

public class CoreTabCompleter implements TabCompleter {

	private CommandManager manager;
	private CoreBaseCommand base;

	public CoreTabCompleter(CommandManager manager, CoreBaseCommand base) {
		this.manager = manager;
		this.base = base;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		if (args.length == 0) {
			return base.getPotentialArguments(sender);
		}
		CoreSubCommandAssets assets = base.getSubCommandWithAlias(args[0]);
		if (assets == null) {
			return null;
		}
		CoreSubCommand sub = assets.newInstance();
		sub.setArguments(args);
		return sub.getPotentialArguments(sender);
	}
	
	public CommandManager getCommandManager() {
		return manager;
	}
	
	public CoreBaseCommand getBaseCommand() {
		return base;
	}


}
