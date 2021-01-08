package me.chiefbeef.core.customitem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import me.chiefbeef.core.compatibility.CompatMaterial;
import me.chiefbeef.core.customitem.assets.CustomItemAssets;
import me.chiefbeef.core.customitem.assets.CustomItemBuildPack;
import me.chiefbeef.core.customitem.tracking.CursorItemTracker;
import me.chiefbeef.core.customitem.tracking.EntityItemTracker;
import me.chiefbeef.core.customitem.tracking.InventoryItemTracker;
import me.chiefbeef.core.customitem.tracking.ItemTracker;
import me.chiefbeef.core.user.UserCore;
import me.chiefbeef.core.utility.assets.AssetBuildPack;
import me.chiefbeef.core.utility.assets.AssetHolder;
import me.chiefbeef.core.utility.assets.AssetRegistry;
import me.chiefbeef.core.utility.assets.TypeAssets;
import me.chiefbeef.core.utility.persistence.Data;
import me.chiefbeef.core.utility.persistence.DataHolder;
import me.chiefbeef.core.utility.persistence.DataPack;
import me.chiefbeef.core.utility.persistence.gui.PersistentSlotHolder;

public abstract class CustomItem implements DataHolder, AssetHolder<CustomItem> {
	
	/**
	 * The Singleton object responsible for managing all the {@link CustomItem} types and their {@link TypeAssets}
	 * that are registered in this API. 
	 */
	private static final AssetRegistry<CustomItem> registry = new AssetRegistry<>();
	
	/**
	 * @return The {@link AssetRegistry} for the {@link CustomItem} API
	 */
	public static AssetRegistry<CustomItem> getRegistry() {
		return registry;
	}
	
	/**
	 * The cache of custom item instances that are currently being tracked.
	 */
	private static final Map<UUID, CustomItem> itemCache = new ConcurrentHashMap<>();
	
	/**
	 * plz no break
	 * @return The map you shouldn't break.
	 */
	public static Map<UUID, CustomItem> getCache() {
		return itemCache;
	}
	
	/**
	 * @param item the {@link ItemStack} to check
	 * @return true if the {@link ItemStack} is a {@link CustomItem}
	 */
	public static boolean isCustom(final ItemStack item) {
		if (item == null || !item.hasItemMeta()) {
			return false;
		}
		String label = getLabel(item);
		return label != null &&
				getRegistry().getAssets(label) != null;
	}

	/** 
	 * @param id The {@link UUID} of the {@link CustomItem}.
	 * @return The {@link CustomItem} instance in the cache that owns this {@link UUID} or null if there isnt one. 
	 */
	public static CustomItem fromId(final UUID id) {
		return itemCache.getOrDefault(id, null);
	}

	/**
	 * 
	 * @param item
	 * @return
	 */
	public static CustomItem fromItemStack(final ItemStack item) {
		if (!isCustom(item)) {
			return null;
		}
		String label = getLabel(item);
		CustomItem custom = getRegistry().getAssets(label).newInstance();
		return custom.build(new CustomItemBuildPack(item));
	}

	public static String getLabel(final ItemStack item) {
		return isCustom(item) ? Meta.get(item, "customItemLabel") : null;
	}
	
	public static UUID getUUID(final ItemStack item) {
		return isCustom(item) ? UUID.fromString(Meta.get(item, "customItemId")) : null;
	}

	public static void onApiShutdown() {
		for (CustomItem custom : itemCache.values()) {
			custom.onShutdown();
		}
		itemCache.clear();
	}
	
	
	
	

	/**
	 * 
	 * --=--------=- Object --=--------=-
	 * 
	 */

	protected DataPack pack;
	private ItemStack item;
	private UUID id;
	private boolean destroyed;

	/**
	 * Tracking all representations of this CustomItem This feature allows complete
	 * awareness of all (loaded) instances of this CustomItems physical
	 * representation. Unloaded instances are not currently tracked.
	 */
	private Map<TrackerType, ItemTracker> trackers = new HashMap<>();

	public static enum TrackerType {
		INVENTORY, ENTITY, CURSOR;
	}

	@Override
	public CustomItemAssets createAssets() {
		return new CustomItemAssets(this.getClass(), this.getLabel());
	}
	
	@Override
	public CustomItemAssets getAssets() {
		return (CustomItemAssets) getRegistry().getAssets(this.getClass());
	}
	
	/**
	 * Construct the CustomItem using a build pack.
	 */
	@Override
	public CustomItem build(AssetBuildPack pack) {
		if (id != null) {return this;}
		
		if (!(pack instanceof CustomItemBuildPack)) {
			throw new IllegalArgumentException("CustomItems require a CustomItemBuildPack to be built!");
		}
		
		CustomItemBuildPack cpack = (CustomItemBuildPack) pack;
		
		if (cpack.getItemStack() == null || cpack.getItemStack().getType().isAir()) {
			throw new IllegalArgumentException("The ItemStack passed was invalid!");
		}
		
		this.item = cpack.getItemStack();
		this.id = isCustom(item) ? UUID.fromString(Meta.get(item, "customItemId")) : firstTimeSetup();
		loadPersistentData();
		loadTrackers();
		itemCache.put(id, this);
		debugTrackers(true);
		updateMeta();
		return this;
	}
	
	
	@Override
	public CustomItem build() {
		if (id != null) {return this;}
		this.item = new ItemStack(getMaterial().asMaterial());
		updateMeta();
		this.id = firstTimeSetup();
		loadTrackers();
		debugTrackers(true);
		return this;
	}
	

	/**
	 * Turn the {@link ItemStack} into a {@link CustomItem}.
	 * {@link CustomItem} children may override this to add data to the
	 * ItemStack during the build process but they must remember to call the super method!
	 * @return The UUID of the {@link CustomItem}.
	 */
	protected UUID firstTimeSetup() {
		UUID uuid = UUID.randomUUID();
		Meta.put(item, "customItemId", uuid.toString());
		Meta.put(item, "customItemLabel", getLabel().toUpperCase());
		return uuid;
	}

	/**
	 * loads {@link Data} objects from the item into memory.
	 */
	private void loadPersistentData() {
		this.pack = new DataPack();
		PersistentSlotHolder slots = new PersistentSlotHolder();
		// TODO Load slots
		pack.add(slots);
	}

	/**
	 * Load the trackers that store information about the custom items wherabouts.
	 */
	private void loadTrackers() {
		trackers.put(TrackerType.INVENTORY, new InventoryItemTracker(this));
		trackers.put(TrackerType.ENTITY, new EntityItemTracker(this));
		trackers.put(TrackerType.CURSOR, new CursorItemTracker(this));
	}

	/**
	 * Get the physical representation of this CustomItem.
	 * 
	 * @return A clone of the ItemStack representing this CustomItem.
	 */
	public ItemStack getItem() {
		return item.clone();
	}

	/**
	 * @return The UUID of this CustomItem.
	 */
	public UUID getUniqueId() {
		return id;
	}

	/**
	 * @return An array of all objects currently responsible for tracking the
	 *         physical whereabouts of this CustomItem.
	 */
	public ItemTracker[] getTrackers() {
		return trackers.values().toArray(new ItemTracker[trackers.size()]);
	}

	/**
	 * Evaluate the state of the CustomItem trackers. If none of the trackers
	 * currently have sense of the CustomItem's whereabouts the item is removed from
	 * the cache.
	 */
	public void evaluateTrackers() {
		if (destroyed) {
			return;
		}
		for (ItemTracker tracker : trackers.values()) {
			if (tracker.holdsReference()) {
				return;
			}
		}
		unload();
	}

	/**
	 * 
	 * @param type The type of tracker
	 * @return The tracker
	 */
	public ItemTracker getTracker(TrackerType type) {
		return trackers.get(type);
	}

	/**
	 * This will completely destroy the Custom item, removing it from its active
	 * inventories as well as killing it's entity. If there are unloaded
	 * representations of the item however they will not be removed so it is to only
	 * be used when you know where the item currently is and what state it is in.
	 * For instance destroy() is called internally when a customItem is dropped on
	 * the ground and burns in lava.
	 */
	public final void destroy() {
		destroyed = true;
		onDestroy();
		for (ItemTracker tracker : trackers.values()) {
			tracker.removeItem();
		}
		debugTrackers(false);
		itemCache.remove(this.id);
	}

	public final void unload() {
		debugTrackers(false);
		if (onUnload()) {
			itemCache.remove(this.id);
		}
	}

	public void setType(Material mat) {
		item.setType(mat);
	}

	public void setMeta(ItemMeta meta) {
		item.setItemMeta(meta);
	}
	
	/**
	 * Update the meta of the CustomItem. This method will not update the
	 * CustomItems' representations.
	 */
	public void updateMeta() {
		final ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(getDisplayName());
		meta.setLore(getLore());
		item.setItemMeta(meta);
	}
	
	/**
	 * Update the items material
	 */
	public void updateMaterial() {
		getMaterial().applyMaterial(item);
	}
	
	/**
	 * Update this CustomItems representations in real time using the internal
	 * ItemTrackers.
	 */
	public void updateItem() {
		for (ItemTracker tracker : trackers.values()) {
			tracker.updateItem();
		}
	}

	/**
	 * Called when the CustomItem is flagged for destruction. Cancel all tasks and
	 * cleanup. This will happen if the item is killed in its entity form, for
	 * instance if it is dropped in lava.
	 * 
	 * This however is not a sure fire way to tell if the entity is destroyed as
	 * there are ways of the item being lost forever that cannot be tracked.
	 * 
	 * For example, when a creative player picks an item up from their inventory,
	 * the item is handled solely by the client and is no longer tracked by the
	 * server. If they then destroy the item by placing it in the creative inventory
	 * the server can't know, so onDestroy will never be called. onUnload will
	 * however be called when the creative player picks up the item, If the player
	 * returns the item to their inventory it will again be tracked by the server.
	 * 
	 * Keep this in mind if you intend to store data about an item in a data file
	 * and remove it onDestroy as it will not be removed in cases like this.
	 */
	protected abstract void onDestroy();

	/**
	 * Called when the CustomItem is being unloaded and is to be removed from the
	 * cache. This occurs when the internal item trackers no longer have a reference
	 * to the item. IE it is in an unloaded inventory (such as a chest), unloaded
	 * chunk or just plain lost. The object will be rebuilt if it is found again.
	 * 
	 * If you expect this item to be loaded and unloaded many times you may return
	 * false to prevent the resource load of the item being constantly constructed,
	 * it will be left loaded in memory.
	 * 
	 * In addition if you intend to continue processes inside the CustomItem
	 * instance for some reason you may return false to keep it in the cache and
	 * remove it when you are done
	 * 
	 * If you are not careful returning false can cause ram leaks as the object will
	 * be left in the cache until it is again seen by the trackers, which may never
	 * happen.
	 * 
	 * @return false if you do not want the item removed from the cache.
	 */
	public abstract boolean onUnload();

	/**
	 * Called when the server is shutting down and it is time for you to finalize
	 * what you are doing. Very similar to onUnload except in this case you are not
	 * given a choice.
	 */
	protected abstract void onShutdown();

	/**
	 * @param u
	 *            The user who interacted with the item
	 * @param interaction
	 *            The interaction type
	 * @return True if you want the vanilla interaction to be cancelled
	 */
	public abstract boolean onInteract(final UserCore user, final Interaction action, final Event event);

	/**
	 * @return The display name for the CustomItem.
	 */
	public abstract String getDisplayName();

	/**
	 * @return The lore for the CustomItem.
	 */
	public abstract List<String> getLore();
	
	/**
	 * 
	 * @return The material for the CustomItem.
	 */
	public abstract CompatMaterial getMaterial();


	@Override
	public DataPack getDataPack() {
		return pack;
	}
	
	
	
	
	
	private BukkitTask debug;

	public void debugTrackers(boolean enable) {
		if (debug != null) {
			debug.cancel();
		}
		debug = null;
		if (enable) {
			debug = new BukkitRunnable() {
				private CompatMaterial[] mats = new CompatMaterial[] { CompatMaterial.BLACK_WOOL,
						CompatMaterial.CYAN_WOOL, CompatMaterial.MAGENTA_WOOL, CompatMaterial.GREEN_WOOL,
						CompatMaterial.ORANGE_WOOL, CompatMaterial.PINK_WOOL, CompatMaterial.RED_WOOL,
						CompatMaterial.WHITE_WOOL };

				@Override
				public void run() {
					setType(mats[ThreadLocalRandom.current().nextInt(mats.length)].asMaterial());
					updateItem();
				}
			}.runTaskTimer(getAssets().getParentPlugin(), 1, 2);
		}
	}
}
