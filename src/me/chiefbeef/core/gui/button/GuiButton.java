package me.chiefbeef.core.gui.button;

import org.bukkit.inventory.ItemStack;

import me.chiefbeef.core.gui.GuiSession;
import me.chiefbeef.core.gui.page.Page;

public abstract class GuiButton {

	private Page page;
	private ItemStack item;
	
	public GuiButton(Page page, ItemStack item) {
		this.page = page;
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
