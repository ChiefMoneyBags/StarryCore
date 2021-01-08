package me.chiefbeef.core.user;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import me.chiefbeef.core.gui.GuiSession;
import me.chiefbeef.core.user.assets.UserExtensionRegistry;

/**
 * The {@link UserCore} acts as a wrapper for the {@link Player} to extend its functionality
 * into the various API's available in StarryCore.
 * 
 * Each {@link Player} that joins the server is assigned their own {@link UserCore}.
 * 
 * The {@link UserCore} acts as a hub for hooks implemented by other plugins related to the {@link Player}.
 * When a {@link UserExtension} type is registered in the {@link UserExtensionRegistry} it will
 * be automatically instantiated for each player by the {@link UserCore} so you dont need to track
 * {@link PlayerJoinEvent} and {@link PlayerQuitEvent}.
 *  
 * @author Kevin
 *
 */
public class UserCore {
	
	private UserManager manager;
	private Map<Class<? extends UserExtension>, UserExtension> extensions = new HashMap<>();
	private Player p;
	private GuiSession gui;
	
	public UserCore(UserManager manager, Player p) {
		this.manager = manager;
		this.p = p;
		this.gui = new GuiSession(this);
	}
	
	/**
	 * @return The {@link UserManager} that created this {@link UserCore}
	 */
	public UserManager getManager() {
		return manager;
	}
	
	/**
	 * @return The {@link Player} this {@link UserCore} belongs to.
	 */
	public Player getPlayer() {
		return p;
	}
	
	/**
	 * @return The {@link Player} location
	 */
	public Location getLocation() {
		return p.getLocation();
	}
	
	/**
	 * 
	 * @return The {@link GuiSession} belonging to this {@link UserCore}
	 */
	public GuiSession getGuiSession() {
		return gui;
	}
	
	/**
	 * invoked when the {@link Player} leaves the server
	 */
	public void onQuit() {
		gui.end();
		save();
		
		for (UserExtension extension: extensions.values()) {
			extension.onQuit();
		}
	}
	
	/**
	 * This method will save all data about the user held in the core as well as
	 * invoke the save method in all extensions registered in this user.
	 */
	public void save() {
		for (UserExtension extension: extensions.values()) {
			extension.save();
		}
	}
	
	/**
	 * Check if the player has a permission.
	 * @param perm The permission
	 * @return true if they have the permission
	 */
	public boolean hasPermission(String perm) {
		return p.hasPermission(perm);
	}
	
	/**
	 * Gets the {@link UserExtension} of the specified type within this {@link UserCore}
	 * @param type The type of extension
	 * @return The {@link UserExtension} instance corresponding to type
	 */
	public UserExtension getExtension(Class<? extends UserExtension> type) {
		return extensions.get(type);
	}
	
	/**
	 * Load all available {@link UserExtension} types for this {@link UserCore}
	 */
	public void loadExtensions() {
		for (Class<? extends UserExtension> type: UserExtension.getRegistry().getAssets().keySet()) {
			loadExtension(type);
		}
	}
	
	/**
	 * Load a specific {@link UserExtension} type for this {@link UserCore}
	 * @param type The type of extension to load.
	 */
	public void loadExtension(Class<? extends UserExtension> type) {
		if (!extensions.containsKey(type)) {
			UserExtension extension = UserExtension.getRegistry().getAssets(type).newInstance();
			extension.setUser(this);
			extension.build();
			extensions.put(type, extension);	
		}
	}

	/**
	 * Calls a fake BlockBreakEvent and checks if the event got cancelled to see if the {@link Player}
	 * is allowed to mine a given {@link Block}.  
	 * @param block The block to check.
	 * @return true if they can mine it
	 */
	public boolean canMine(Block block) {
		BlockBreakEvent event = new BlockBreakEvent(block, p);
		Bukkit.getPluginManager().callEvent(event);
		return !event.isCancelled();
	}
	

}
