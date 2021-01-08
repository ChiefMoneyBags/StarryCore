package me.chiefbeef.core.gui.buttons;

import org.bukkit.inventory.ItemStack;

import me.chiefbeef.core.gui.GuiSession;
import me.chiefbeef.core.gui.abstraction.Page;
import me.chiefbeef.core.gui.transition.GuiTransition;
import me.chiefbeef.core.utility.persistence.DataPack;

public final class Link extends GuiButton {

	private DataPack data;
	private Class<? extends Page> destination;
	private Class<? extends GuiTransition> type;
	
	public Link(Page page, ItemStack item, Class<? extends Page> destination, Class<? extends GuiTransition> type, DataPack data) {
		super(page, item);
		this.destination = destination;
		this.type = type;
		this.data = data;
	}
	
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
	
	public DataPack getData() {
		return data;
	}
	
	@Override
	public void invoke(GuiSession session) {
		Page to;
		try { to = destination.getConstructor(GuiSession.class).newInstance(session); } catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		session.transitionTo(to, getTransition());
	}


}
