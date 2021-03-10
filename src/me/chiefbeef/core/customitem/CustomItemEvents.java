package me.chiefbeef.core.customitem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import me.chiefbeef.core.StarryCore;
import me.chiefbeef.core.customitem.tracking.CursorItemTracker;
import me.chiefbeef.core.customitem.tracking.EntityItemTracker;
import me.chiefbeef.core.customitem.tracking.InventoryItemTracker;
import me.chiefbeef.core.event.channel.PacketPlayInEvent;
import me.chiefbeef.core.user.UserCore;
import me.chiefbeef.core.utility.Console;
import me.chiefbeef.core.utility.netserver.Reflect;

/**
 * 
 * --=-----------=- Listeners --=-----------=-
 * 
 */
// TODO
// Item entities continue to be tracked after despawning. There is no despawn
// event, what to do.

// Player puts custom items in crafting then closes inv, items are no longer
// tracked when they are auto placed back in inv.

// shift click into other inv not tracked.

// Inventory and cursor trackers create ghost blocks when updating really fast,
// no successful dupes yet.

// off hand and armor slots need hard coded slot numbers for drag event

// call destroy on entity death. TODO??
/**
 * Listener class responsible for passing interactions to CustomItems and
 * tracking where CustomItems are.
 * 
 * -NOTES-
 * 
 * - The creative inventory is all client side so if a creative player picks an
 * ItemStack up to their cursor it no longer exists on the server and we can't
 * track it anymore. The CustomItem will be unloaded at this point. It will be
 * reloaded if its ItemStack is either dropped on the ground or placed back in
 * the Inventory. Essentially its internal processes are paused/unloaded while
 * on the cursor. It will never return if the ItemStack is destroyed by the
 * client.
 * 
 * 
 * @author Kevin
 *
 */
public class CustomItemEvents implements Listener {

	private StarryCore starry;
	
	public CustomItemEvents(StarryCore starry) {
		this.starry = starry;
		starry.getServer().getPluginManager().registerEvents(this, starry);
	}
	
	public StarryCore getStarry() {
		return starry;
	}
	
	public static void loadInventory(final Inventory inv) {
		int slot = 0;
		for (ItemStack item : inv.getContents()) {
			if (CustomItem.isCustom(item)) {
				CustomItem custom = CustomItem.fromItemStack(item);
				InventoryItemTracker iit = custom.getTracker(InventoryItemTracker.class);
				iit.addSlot(inv, slot, true);
			}
			slot++;
		}
	}

	public static void unloadInventory(Inventory inv) {
		for (ItemStack item : inv.getContents()) {
			if (CustomItem.isCustom(item)) {
				CustomItem custom = CustomItem.fromItemStack(item);
				InventoryItemTracker iit = custom.getTracker(InventoryItemTracker.class);
				iit.removeInventory(inv, true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void on(final ServerLoadEvent e) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			loadInventory(p.getInventory());
		}
		for (World world : Bukkit.getServer().getWorlds()) {
			for (Entity ent : world.getEntities()) {
				if (!(ent instanceof Item)) {
					continue;
				}
				Item item = (Item) ent;
				ItemStack it = item.getItemStack();
				if (!CustomItem.isCustom(it)) {
					continue;
				}
				CustomItem custom = CustomItem.fromItemStack(it);
				EntityItemTracker eit = custom.getTracker(EntityItemTracker.class);
				eit.addEntity(item);
			}
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void on(final PlayerJoinEvent e) {
		loadInventory(e.getPlayer().getInventory());

	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void on(final PlayerQuitEvent e) {
		unloadInventory(e.getPlayer().getInventory());
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void on(EntitySpawnEvent e) {
		if (!(e.getEntity() instanceof Item)) {
			return;
		}
		final Item item = (Item) e.getEntity();
		final ItemStack it = item.getItemStack();
		if (CustomItem.isCustom(it)) {
			CustomItem custom = CustomItem.fromItemStack(it);
			EntityItemTracker eit = custom.getTracker(EntityItemTracker.class);
			eit.addEntity(item);
		}
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void on(final EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Item)) {
			return;
		}
		final Item item = (Item) e.getEntity();

		final ItemStack it = item.getItemStack();
		if (CustomItem.isCustom(it)) {
			CustomItem custom = CustomItem.fromItemStack(it);
			EntityItemTracker eit = custom.getTracker(EntityItemTracker.class);
			if (item.isDead() || !item.isValid()) {
				eit.removeEntity(item);
			} else {
				eit.flagEntity(item);
			}
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void on(PlayerDropItemEvent e) {
		final Item item = e.getItemDrop();
		final ItemStack it = item.getItemStack();
		if (CustomItem.isCustom(it)) {
			CustomItem custom = CustomItem.fromItemStack(it);
			InventoryItemTracker iit = custom.getTracker(InventoryItemTracker.class);
			EntityItemTracker eit = custom.getTracker(EntityItemTracker.class);
			eit.addEntity(item);
			iit.removeInventory(e.getPlayer().getInventory(), true);
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void on(final EntityPickupItemEvent e) {
		final Item item = e.getItem();
		final ItemStack it = item.getItemStack();
		final LivingEntity ent = e.getEntity();
		CustomItem custom;

		if (CustomItem.isCustom(it)) {
			if ((custom = CustomItem.fromItemStack(it)).onInteract(
					(ent instanceof Player) ? starry.getUserManager().getUser((Player) ent) : null, Interaction.PICKUP, e)) {
				e.setCancelled(true);
				return;
			}
			if (e.getEntity() instanceof Player) {
				Inventory inv = ((Player) e.getEntity()).getInventory();
				int slot = inv.firstEmpty();
				InventoryItemTracker iit = custom.getTracker(InventoryItemTracker.class);
				iit.addSlot(inv, slot, true);
			}
			EntityItemTracker eit = custom.getTracker(EntityItemTracker.class);
			eit.removeEntity(item);
		}
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void on(InventoryCloseEvent e) {
		Console.debug("", "---CustomItemInventoryClose---", "--| Preparing to remove all trackers from this inventory...");
		Inventory inv = e.getInventory();
		
		for (InventoryItemTracker tracker : InventoryItemTracker.lookupByInventory(inv)) {
			Console.debug("--|> Found an inventory tracker, removing...");
			tracker.removeInventory(inv, true);
		}
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void on(InventoryOpenEvent e) {
		loadInventory(e.getInventory());
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void on(InventoryDragEvent e) {
		if (!(e.getWhoClicked() instanceof Player)) {
			return;
		}
		CustomItem custom = null;
		List<Integer> placed = new ArrayList<>();
		Map<Integer, ItemStack> items = e.getNewItems();
		InventoryView view = e.getView();
		for (Map.Entry<Integer, ItemStack> entry : items.entrySet()) {
			if (custom == null) {
				if (!CustomItem.isCustom(entry.getValue())) {
					return;
				}
				custom = CustomItem.fromItemStack(entry.getValue());
			}
			placed.add(entry.getKey());
		}
		if (custom.onInteract(starry.getUserManager().getUser((Player) e.getWhoClicked()), Interaction.GUI_DRAG, e)) {
			e.setCancelled(true);
			return;
		}
		InventoryItemTracker iit = custom.getTracker(InventoryItemTracker.class);
		for (int rawSlot : placed) {
			Inventory inv = view.getInventory(rawSlot);
			// actual slot number for the inventory clicked.
			int actualSlot = view.getSlotType(rawSlot) ==
			// if clicked is quickbar, slot = the raw slot - (viewtype=crafting? 36) else
			// top inv.size + 36. because technically the view has 3 inventories.
			SlotType.QUICKBAR ? // this tracks the off hand slot
					rawSlot - (view.getType() == InventoryType.CRAFTING ? (rawSlot == 45 ? 5 : 36)
							: view.getTopInventory().getSize() + 27)

					// if clicked is not the quickbar slot = (clicked inv is top inv? rawSlot) else
					// raw-(top.size-9)
					: inv == view.getTopInventory() ? rawSlot
							: rawSlot - (view.getTopInventory().getSize()
									- (view.getType() == InventoryType.CRAFTING ? 5 : 9));
			iit.addSlot(inv, actualSlot, true);
		}

		if (e.getCursor() == null || e.getCursor().getType() == Material.AIR) {
			CursorItemTracker cit = custom.getTracker(CursorItemTracker.class);
			cit.removeCursor((Player) e.getWhoClicked());
		}
	}

	private List<Event> ignore = new ArrayList<>();

	/**
	 * A monitor listener to try and improve compatibility with other plugins that
	 * set inventory items with .setItem() and cancel the event.
	 */
	/**
	@EventHandler(priority = EventPriority.MONITOR)
	public void onMonitor(InventoryClickEvent e) {
		if ((e.isCancelled() && ignore.remove(e)) || !e.isCancelled()) {
			return;
		}
		if (!(e.getWhoClicked() instanceof Player)) {
			return;
		}
		final Player p = (Player) e.getWhoClicked();
		final Inventory clicked = e.getClickedInventory();
		final int slot = e.getSlot();

		CustomItem got = CustomItem.fromItemStack(e.getCursor()), placed = CustomItem.fromItemStack(e.getCurrentItem());

		InventoryItemTracker iit;
		CursorItemTracker cit;
		// Did another plugin forcefully set the inventory slot?
		if (placed != null) {
			iit = (InventoryItemTracker) placed.getTracker(InventoryItemTracker.class);
			cit = (CursorItemTracker) placed.getTracker(CursorItemTracker.class);
			if (!iit.isTracking(clicked, slot) && clicked.getItem(slot).equals(placed.getItem())) {
				iit.addSlot(clicked, slot, true);
			}
			if (!p.getItemOnCursor().equals(placed.getItem())) {
				cit.removeCursor(p);
			}
		}
		// Did another plugin forcefully set the player cursor?
		if (got != null) {
			iit = (InventoryItemTracker) got.getTracker(InventoryItemTracker.class);
			cit = (CursorItemTracker) got.getTracker(CursorItemTracker.class);
			if (iit.isTracking(clicked, slot) && !clicked.getItem(slot).equals(placed.getItem())) {
				iit.removeSlot(clicked, slot, true);
			}
			if (p.getItemOnCursor().equals(got.getItem())) {
				cit.addCursor(p);
			}
		}
	}
	*/
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void on(InventoryClickEvent e) {
		if (!(e.getWhoClicked() instanceof Player)) {
			return;
		}
		final Player p = (Player) e.getWhoClicked();
		final Inventory clicked = e.getClickedInventory();

		boolean cancel = false;
		CustomItem got = CustomItem.fromItemStack(e.getCurrentItem()), placed = CustomItem.fromItemStack(e.getCursor());

		if (placed != null && (e.getView().getSlotType(e.getRawSlot()) == SlotType.ARMOR
				|| e.getView().getSlotType(e.getRawSlot()) == SlotType.RESULT)) {
			e.setCancelled(true);
			return;
		}

		if (placed != null && e.getClick() == ClickType.DOUBLE_CLICK) {
			e.setCancelled(true);
			return;
		}

		//TODO add 2 waves the first allows a cancel the second executes code.
		// How do i do this if one item cancels the event the other one wont know.
		if (got != null && got.onInteract(starry.getUserManager().getUser((Player) e.getWhoClicked()), Interaction.GUI_PICKUP, e)) {
			cancel = true;
		}

		// If this one cancels, the first one already went uncancelled.
		if (!cancel && placed != null
				&& placed.onInteract(starry.getUserManager().getUser((Player) e.getWhoClicked()), Interaction.GUI_PLACE, e)) {
			cancel = true;
		}

		if (!cancel) {
			InventoryItemTracker iit;
			CursorItemTracker cit;
			EntityItemTracker eit;
			if (got != null) {
				iit = got.getTracker(InventoryItemTracker.class);
				cit = got.getTracker(CursorItemTracker.class);
				cit.addCursor(p);
				iit.removeSlot(clicked, e.getSlot(), true);
			}
			if (placed != null) {
				int slot = e.getSlot();
				cit = placed.getTracker(CursorItemTracker.class);

				// if clicked is not a border of the inv
				if (clicked != null && slot > -1) {
					iit = placed.getTracker(InventoryItemTracker.class);
					iit.addSlot(clicked, e.getSlot(), true);
					cit.removeCursor(p);

					// If clicked is outside inventory bounds
				} else if (slot == -999) {
					eit = placed.getTracker(EntityItemTracker.class);
					eit.startWatcher();
					cit.removeCursor(p);
				}
			}
		} else {
			e.setCancelled(true);
			ignore.add(e);
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void on(PlayerSwapHandItemsEvent e) {
		if (CustomItem.isCustom(e.getMainHandItem())) {
			e.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void on(PlayerInteractEvent e) {
		ItemStack item = e.getItem();
		if (!CustomItem.isCustom(item)) {
			return;
		}
		Console.debug(e.getAction());
		CustomItem custom = CustomItem.fromItemStack(item);
		UserCore user = starry.getUserManager().getUser(e.getPlayer());
		if (custom.onInteract(user, Interaction.convert(e.getAction()), e))
			e.setCancelled(true);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void on(PacketPlayInEvent e) {
		Object packet = e.getPacket();
		if (!Reflect.nmsClass("PacketPlayInUseEntity").isInstance(packet)) {
			return;
		}

		UserCore user = e.getUser();
		Player p = user.getPlayer();
		ItemStack i = p.getInventory().getItemInMainHand();

		Interaction action;
		try {
			action = Interaction.convert(packet.getClass().getField("b").get(packet).toString());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e1) {
			e1.printStackTrace();
			return;
		}
		
		if (i != null && CustomItem.isCustom(i)) {
			CustomItem custom = CustomItem.fromItemStack(i);
			if (custom.onInteract(user, action, e))
				e.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void on(BlockPlaceEvent e) {
		ItemStack item = e.getItemInHand();
		if (!CustomItem.isCustom(item)) {
			return;
		}
		CustomItem custom = CustomItem.fromItemStack(item);
		UserCore user = starry.getUserManager().getUser(e.getPlayer());
		if (custom.onInteract(user, Interaction.PLACE_PHYSICAL, e))
			e.setCancelled(true);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onMonitor(ChunkLoadEvent e) {
		final Chunk chunk = e.getChunk();
		for (Entity ent : chunk.getEntities()) {
			if (!(ent instanceof Item)) {
				continue;
			}
			Item item = (Item) ent;
			ItemStack it = item.getItemStack();
			if (!CustomItem.isCustom(it)) {
				continue;
			}
			CustomItem custom = CustomItem.fromItemStack(it);
			EntityItemTracker eit = custom.getTracker(EntityItemTracker.class);
			eit.addEntity(item);
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onMonitor(ChunkUnloadEvent e) {
		final Chunk chunk = e.getChunk();
		for (Entity ent : chunk.getEntities()) {
			if (!(ent instanceof Item)) {
				continue;
			}
			Item item = (Item) ent;
			ItemStack it = item.getItemStack();
			if (!CustomItem.isCustom(it)) {
				continue;
			}
			CustomItem custom = CustomItem.fromItemStack(it);
			EntityItemTracker eit = custom.getTracker(EntityItemTracker.class);
			eit.removeEntity(item);
		}
	}
	
	@EventHandler
	public void on(PlayerItemHeldEvent e) {
		Player p = e.getPlayer();
		
		int next = e.getNewSlot(),
				start = e.getPreviousSlot(),
				leftPath = 0,
				rightPath = 0;
			
		
		ItemStack item = p.getInventory().getItem(start);
		if (!CustomItem.isCustom(item)) {
			return;
		}
		
		if (start == next) {
			return;
		}
		
		for (int slot = start; slot != next; slot++) {
			if (slot > 8) {
				slot = -1;
			}
			rightPath++;
		}
		
		for (int slot = start; slot != next; slot--) {
			if (slot < 0) {
				slot = 9;
			}
			leftPath++;
		}
		
		// Scroll up
		Interaction action = leftPath < rightPath ? (p.isSneaking() ? Interaction.SHIFT_SCROLL_LEFT : Interaction.SCROLL_LEFT) : (p.isSneaking() ? Interaction.SHIFT_SCROLL_RIGHT : Interaction.SCROLL_RIGHT);
		
		CustomItem custom = CustomItem.fromItemStack(item);
		if (custom.onInteract(starry.getUserManager().getUser(p), action, e)) {
			e.setCancelled(true);
		}

	}

}
