package me.chiefbeef.core.gui.page.variant.dynamic;

import org.bukkit.inventory.Inventory;

import me.chiefbeef.core.gui.GuiSession;
import me.chiefbeef.core.gui.page.Page;

public abstract class StaticPage extends Page {

	public StaticPage(GuiSession session, int size) {
		super(session, size);
	}
	
	public StaticPage(GuiSession session, Inventory inv) {
		super(session, inv);
	}

}
