package me.chiefbeef.core.command.handling;

import java.util.List;

import org.bukkit.command.CommandSender;

public abstract class CoreCommand {

	/**
	 * @return The base label of this command.
	 */
	public abstract String getLabel();

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

}
