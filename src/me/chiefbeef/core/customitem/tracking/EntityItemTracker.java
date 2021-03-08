package me.chiefbeef.core.customitem.tracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Item;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import me.chiefbeef.core.StarryCore;
import me.chiefbeef.core.customitem.CustomItem;
import me.chiefbeef.core.utility.Console;

public class EntityItemTracker extends ItemTracker {

	private List<Item> entities = new ArrayList<>();
	private Map<Item, BukkitTask> flags = new HashMap<>();
	private BukkitTask watcher;
	
	public EntityItemTracker(CustomItem custom) {
		super(custom);
	}

	public void addEntity(Item item) {
		if (entities.contains(item)) {
			return;
		}
		entities.add(item);
	}
	
	public List<Item> getEntities() {
		return new ArrayList<>(entities);
	}
	
	public void removeEntity(Item item) {
		Console.debug("remove entity");
		entities.remove(item);
		if (flags.containsKey(item)) {
			flags.get(item).cancel();
			flags.remove(item);
		}
		if (!holdsReference()) {
			getCustomItem().evaluateTrackers();
		}
	}
	
	public void flagEntity(final Item item) {
		Console.debug("flagging entity...");
		if (flags.containsKey(item)) {
			flags.get(item).cancel();
		}
		flags.put(item, new BukkitRunnable() {
			@Override
			public void run() {
				flags.remove(item);
				if (item.isDead()) {
					removeEntity(item);
				}
			}
		}.runTask(StarryCore.getInstance()));
	}
	
	public void startWatcher() {
		if (watcher != null) {
			watcher.cancel();
		}
		watcher = new BukkitRunnable() {
			@Override
			public void run() {
				watcher = null;
				if (entities.size() == 0) {
					getCustomItem().evaluateTrackers();
				}
			}
		}.runTaskLater(StarryCore.getInstance(), 1);
	}
	
	@Override
	public void removeItem() {
		for (Item item: entities) {
			item.remove();
		}
		if (watcher != null) {
			watcher.cancel();
		}
	}

	@Override
	public void updateItem() {
		for (Item item: entities) {
			if (item.isDead()) {
				removeEntity(item);
				return;
			}
			item.setItemStack(getCustomItem().getItem());
		}
	}

	@Override
	public boolean holdsReference() {
		return entities.size() > 0 || watcher != null;
	}

}
