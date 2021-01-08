package me.chiefbeef.core.utility;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.chiefbeef.core.customitem.CustomItem;
import me.chiefbeef.core.customitem.CustomItem.TrackerType;
import me.chiefbeef.core.customitem.tracking.EntityItemTracker;
import me.chiefbeef.core.customitem.tracking.InventoryItemTracker;
import me.chiefbeef.core.user.UserCore;

public class Items {
	
	public static void giveItem(UserCore user, ItemStack it) {
		giveItem(user.getPlayer(), it);
	}
	
	public static void giveItem(Player p, ItemStack it) {
		Inventory inv = p.getInventory();
		int slot = inv.firstEmpty();
		HashMap<Integer, ItemStack> remaining = inv.addItem(it);
		
		if (remaining.size() == 0) {
			if (CustomItem.isCustom(it)) {
				CustomItem custom = CustomItem.fromItemStack(it);
				InventoryItemTracker eit = (InventoryItemTracker) custom.getTracker(TrackerType.INVENTORY);
				eit.addSlot(inv, slot, true);
			}
			return;
		}
		for (Map.Entry<Integer, ItemStack> entry: remaining.entrySet()) {
			Item item = p.getWorld().dropItemNaturally(p.getLocation(), it);
			if (CustomItem.isCustom(it)) {
				CustomItem custom = CustomItem.fromItemStack(it);
				EntityItemTracker eit = (EntityItemTracker) custom.getTracker(TrackerType.ENTITY);
				eit.addEntity(item);
			}
		}
	}
	
}
