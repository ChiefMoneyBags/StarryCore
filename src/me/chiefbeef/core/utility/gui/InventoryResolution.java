package me.chiefbeef.core.utility.gui;

import org.bukkit.inventory.Inventory;

public class InventoryResolution {

	int actualSlot;
	Inventory inv;
	
	public InventoryResolution(Inventory inv, int actualSlot) {
		this.actualSlot = actualSlot;
		this.inv = inv;
	}
	
	public int getActualSlot() {
		return actualSlot;
	}
	
	public Inventory getInventory() {
		return inv;
	}
}
