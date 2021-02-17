package me.chiefbeef.core.utility;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.chiefbeef.core.customitem.CustomItem;
import me.chiefbeef.core.customitem.tracking.EntityItemTracker;
import me.chiefbeef.core.customitem.tracking.InventoryItemTracker;
import me.chiefbeef.core.customitem.tracking.ItemTracker;
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
				InventoryItemTracker eit = custom.getTracker(InventoryItemTracker.class);
				eit.addSlot(inv, slot, true);
			}
			return;
		}
		
		// I dont know why this is a map but to be safe im iterating all entries, i only ever expect
		// 1 ItemStack to be returned though.
		for (Map.Entry<Integer, ItemStack> entry: remaining.entrySet()) {
			Item item = p.getWorld().dropItemNaturally(p.getLocation(), entry.getValue());
			if (CustomItem.isCustom(entry.getValue())) {
				CustomItem custom = CustomItem.fromItemStack(entry.getValue());
				EntityItemTracker eit = custom.getTracker(EntityItemTracker.class);
				eit.addEntity(item);
			}
		}
	}
	
}
