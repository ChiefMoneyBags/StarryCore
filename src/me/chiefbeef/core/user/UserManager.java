package me.chiefbeef.core.user;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;

import me.chiefbeef.core.StarryCore;
import me.chiefbeef.core.gui.GuiSession;
import me.chiefbeef.core.utility.Console;

/**
 * The {@link UserManager} is an {@link Object} that stores
 * {@link Player} and their respective {@link UserCore}.
 * @author Kevin
 *
 */
public class UserManager {

	private final Map<Player, UserCore> users = new HashMap<>();
	
	private StarryCore starry;
	
	public UserManager(StarryCore starry) {
		this.starry = starry;
		for (Player p: Bukkit.getOnlinePlayers()) {
			addUser(p);
		}
		Bukkit.getServer().getPluginManager().registerEvents(new UserEvents(), starry);
	}
	
	public StarryCore getStarry() {
		return starry;
	}
	
	public void onDisable() {
		for (UserCore user: users.values()) {
			user.save();
		}
	}
	
	protected void addUser(Player p) {
		if (!users.containsKey(p)) {
			users.put(p, new UserCore(this, p));
		}
	}
	
	protected void removeUser(Player p) {
		if (!users.containsKey(p)) {
			return;
		}
		users.get(p).onQuit();
		users.remove(p);
	}
	
	public UserCore getUser(Player p) {
		return users.get(p);
	}
	
	public Collection<UserCore> getUsers() {
		return users.values();
	}
	
	/**
	 * private Listener class containing events needed for user management.
	 * belongs to the UserManager instance
	 * @author Kevin
	 *
	 */
	private class UserEvents implements Listener {
		/**
		 * We add the user first on lowest priority, so it is present for other plugins later.
		 * @param e
		 */
		@EventHandler (priority = EventPriority.LOWEST)
		public void onJoin(PlayerJoinEvent e) {
			addUser(e.getPlayer());
		}
		
		/**
		 * We remove the user last on monitor priority, so other plugins are done first. 
		 * @param e
		 */
		@EventHandler (priority = EventPriority.MONITOR)
		public void onQuit(PlayerQuitEvent e) {
			removeUser(e.getPlayer());
		}
		
		@EventHandler (priority = EventPriority.MONITOR)
		public void on(InventoryCloseEvent e) {
			if (!(e.getPlayer() instanceof Player)) {
				return;
			}
			UserCore user = getUser((Player) e.getPlayer());
			if (user == null) {
				return;
			}
			GuiSession session = user.getGuiSession();
			if (session.isActive() && !session.shouldSaveOnClose()) {
				session.end();
			}
		}
		
		@EventHandler (priority = EventPriority.HIGHEST)
		public void on(InventoryClickEvent e) {
			Console.debug("---- GuiSession Click Event ----");
			if (!(e.getWhoClicked() instanceof Player)) {
				return;
			}
			UserCore user = getUser((Player) e.getWhoClicked());
			if (user == null) {
				return;
			}
			
			GuiSession session = user.getGuiSession();
			Inventory clicked = e.getClickedInventory();
			Inventory playerInv = user.getPlayer().getInventory();
			
			if (!playerInv.equals(clicked) && session.isTransitioning()) {
				Console.debug("--| not player inv and session is transitioning, cancel...");
				e.setCancelled(true);
			} else if (session.isActive() && !session.isTransitioning()) {
				Console.debug("--| session is active, pass event to page...");
				session.getPage().onClick(e);
			}
			
		}
		
		@EventHandler (priority = EventPriority.HIGHEST)
		public void on(InventoryDragEvent e) {
			if (!(e.getWhoClicked() instanceof Player)) {
				return;
			}
			UserCore user = getUser((Player) e.getWhoClicked());
			if (user == null) {
				return;
			}
			
			GuiSession session = user.getGuiSession();
			if (session.isTransitioning()) {
				e.setCancelled(true);
			} else if (session.isActive() && session.getPage() != null) {
				session.getPage().onDrag(e);
			}
		}
	}
}
