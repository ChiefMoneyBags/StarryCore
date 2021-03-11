package me.chiefbeef.core.gui.button;

import org.bukkit.inventory.ItemStack;

import me.chiefbeef.core.gui.GuiSession;
import me.chiefbeef.core.gui.page.Page;
import me.chiefbeef.core.gui.transition.GuiTransition;
import me.chiefbeef.core.utility.Console;

public final class Link extends GuiButton {

	private Class<? extends Page> destination;
	private Class<? extends GuiTransition> type;
	
	public Link(Page page, ItemStack item, Class<? extends Page> destination, Class<? extends GuiTransition> type) {
		super(page, item);
		this.destination = destination;
		this.type = type;
	}
	
	public Class<? extends Page> getDestination() {
		return destination;
	}
	
	public Class<? extends GuiTransition> getTransition() {
		return type;
	}
	
	@Override
	public void invoke(GuiSession session) {
		Console.debug("link invoked");
		Page to;
		try { to = destination.getConstructor(GuiSession.class).newInstance(session); } catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		session.transitionTo(to, getTransition());
	}


}
