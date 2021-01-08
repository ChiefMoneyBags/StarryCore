package me.chiefbeef.core.command;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.chiefbeef.core.StarryCore;

public class CommandManager implements CommandExecutor {

	private StarryCore starry;
	
	private Map<String, CoreCommandExecutor> executors = new HashMap<>();
	
	public CommandManager(StarryCore starry) {
		this.starry = starry;
	}
	
	public StarryCore getStarry() {
		return starry;
	}
	

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		return false;
	}

}
