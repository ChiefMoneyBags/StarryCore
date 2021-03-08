package me.chiefbeef.core.customitem.tracking;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.chiefbeef.core.customitem.CustomItem;
import me.chiefbeef.core.utility.Console;

public class CursorItemTracker extends ItemTracker {

	private List<Player> cursors = new CopyOnWriteArrayList<>();
	
	public CursorItemTracker(CustomItem custom) {
		super(custom);
	}

	public void addCursor(Player player) {
		Console.debug("add cursor: " + player.getName());
		if (cursors.contains(player)) {
			return;
		}
		if (player.getGameMode() == GameMode.CREATIVE) {
			return;
		}
		cursors.add(player);
	}
	
	public void removeCursor(Player player) {
		Console.debug("remove cursor: " + player.getName());
		cursors.remove(player);
		if (!holdsReference()) {
			getCustomItem().evaluateTrackers();
		}
	}
	
	public List<Player> getCursorPlayers() {
		return new ArrayList<>(cursors);
	}
	
	@Override
	public void removeItem() {
		for (Player p: cursors) {
			ItemStack it = p.getItemOnCursor();
			if (!CustomItem.isCustom(it) || CustomItem.fromItemStack(it) != getCustomItem()) {
				continue;
			}
			p.setItemOnCursor(null);
		}
		cursors.clear();
		if (!holdsReference()) {
			getCustomItem().evaluateTrackers();
		}
	}

	@Override
	public void updateItem() {
		
		for (Player p: cursors) {
			ItemStack it = p.getItemOnCursor();
			if (!CustomItem.isCustom(it) || CustomItem.fromItemStack(it) != getCustomItem()) {
				Console.debug("Cursor no longer contains CustomItem in continuity check, removing.");
				removeCursor(p);
				continue;
			}
			p.setItemOnCursor(getCustomItem().getItem());
		}
	}

	@Override
	public boolean holdsReference() {
		return cursors.size() > 0;
	}

}
