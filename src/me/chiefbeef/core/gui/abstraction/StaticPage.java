package me.chiefbeef.core.gui.abstraction;

import org.bukkit.inventory.Inventory;

import me.chiefbeef.core.gui.GuiSession;

public abstract class StaticPage extends Page {

	public StaticPage(GuiSession session, int size) {
		super(session, size);
	}
	
	public StaticPage(GuiSession session, Inventory inv) {
		super(session, inv);
	}

}
