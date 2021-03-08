package me.chiefbeef.core;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import me.chiefbeef.core.command.handling.CommandManager;
import me.chiefbeef.core.customitem.CustomItemEvents;
import me.chiefbeef.core.user.UserManager;

public class StarryCore extends JavaPlugin {
	
	private static StarryCore starry;
	
	public static StarryCore getInstance() {
		return starry;
	}
	
	private UserManager userManager;
	private CustomItemEvents customItem;
	private CommandManager commands;
	
	@Override
	public void onEnable() {
		StarryCore.starry = this;
		userManager = new UserManager(this);
		customItem = new CustomItemEvents(this);
		commands = new CommandManager(this);
	}
	
	@Override
	public void onDisable() {
		userManager.onDisable();
	}
	
	
	public UserManager getUserManager() {
		return userManager;
	}
	
	public CustomItemEvents getCustomItemEvents() {
		return customItem;
	}
	
	public CommandManager getCommandManager() {
		
		return commands;
	}
	
	/**
	protected void timeSpheres() {
		CustomTimingsHandler sphereAPI = new CustomTimingsHandler("Sphere Gen API");
		CustomTimingsHandler sphereRAW = new CustomTimingsHandler("Sphere Gen RAW");
		
		int radius = 5;
		//DataBridge.initCoreDataServers();
		Sphere.preGenerate(radius);
		final Location center = new Location(Bukkit.getWorlds().get(0), 100, 100, 100);
		Sphere sphere = new Sphere(center, radius);
		Bukkit.getScheduler().runTaskTimer(this, () -> {
			sphereAPI.startTiming();
			sphere.setCenter(center);
			List<Block> blocks = sphere.getBlocks();
			sphereAPI.stopTiming();
		}
		, 1, 1);
		
		Bukkit.getScheduler().runTaskTimer(this, () -> {
			sphereRAW.startTiming();
			List<Block> blocks = new ArrayList<>();
			for (int x = -radius; x <= radius; x++) {
				for (int y = -radius; y <= radius; y++) {
					for (int z = -radius; z <= radius; z++) {
						Location loc = center.clone().add(x, y, z);
						if (loc.distance(center) <= radius) {
							blocks.add(loc.getBlock());
						}
					}
				}
			}
			sphereRAW.stopTiming();
		}
		, 1, 1);
	}
	*/
	
}
