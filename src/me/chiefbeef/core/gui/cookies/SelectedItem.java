package me.chiefbeef.core.gui.cookies;

import org.bukkit.inventory.ItemStack;

import me.chiefbeef.core.gui.abstraction.GuiCookie;

public class SelectedItem extends GuiCookie {

	private int originSlot;
	private ItemStack item;
	
	public SelectedItem(int originSlot, ItemStack item) {
		this.originSlot = originSlot;
		this.item = item;
	}
	
	public int getOriginSlot() {
		return originSlot;
	}
	
	public ItemStack getItem() {
		return item;
	}
}
