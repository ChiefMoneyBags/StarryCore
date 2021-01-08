package me.chiefbeef.core.gui.buttons;

import org.bukkit.inventory.ItemStack;

import me.chiefbeef.core.gui.GuiSession;
import me.chiefbeef.core.gui.abstraction.Page;

public abstract class GuiButton {

	private Page page;
	private ItemStack item;
	
	public GuiButton(Page page, ItemStack item) {
		this.item = item;
	}
	
	public ItemStack getItem() {
		return item;
	}
	
	public void setItem(ItemStack item) {
		this.item = item;
		page.updateButton(this);
	}
	
	protected void setInitialItem(ItemStack item) {
		this.item = item;
	}

	public abstract void invoke(GuiSession session);
	
}
