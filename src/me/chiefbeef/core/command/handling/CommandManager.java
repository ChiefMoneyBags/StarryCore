package me.chiefbeef.core.command.handling;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import me.chiefbeef.core.StarryCore;
import me.chiefbeef.core.command.CoreBaseCommand;
import me.chiefbeef.core.utility.Console;

public class CommandManager {

	private StarryCore starry;
	
	private List<CoreBaseCommand> commands = new ArrayList<>();
	
	public CommandManager(StarryCore starry) {
		this.starry = starry;
	}
	
	public StarryCore getStarry() {
		return starry;
	}
	
	public void registerCommand(CoreBaseCommand base) {
		if (commands.stream().filter(registered -> registered.getLabel().equalsIgnoreCase(base.getLabel())).count() > 0) {
			Console.generateException("Commands can only be registered once and must have a unique label! (" + base.getLabel() +")");
			return;
		}
		commands.add(base);
	}
	
	public CoreBaseCommand getBaseCommand(String label) {
		Optional<CoreBaseCommand> optional = commands.stream().filter(command -> command.getLabel().equalsIgnoreCase(label)).findFirst();
		return optional.isPresent() ? optional.get() : null;
	}
	

}
