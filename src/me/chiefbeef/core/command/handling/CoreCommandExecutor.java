package me.chiefbeef.core.command.handling;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.chiefbeef.core.command.CoreBaseCommand;
import me.chiefbeef.core.command.CoreSubCommand;
import me.chiefbeef.core.command.assets.CoreSubCommandAssets;

public class CoreCommandExecutor implements CommandExecutor {

	private CoreBaseCommand base;

	public CoreCommandExecutor(CoreBaseCommand base) {
		this.base = base;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length == 0) {
			base.executeAs(sender);
			return true;
		}
		CoreSubCommandAssets assets = base.getSubCommandWithAlias(args[0]);
		if (assets == null) {
			return false;
		}
		CoreSubCommand sub = assets.newInstance();
		sub.setArguments(args);
		sub.executeAs(sender);
		return true;
	}
	
	
	public CoreBaseCommand getBaseCommand() {
		return base;
	}

}
