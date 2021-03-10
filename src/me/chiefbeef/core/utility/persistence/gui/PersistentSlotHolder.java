package me.chiefbeef.core.utility.persistence.gui;

import java.util.HashMap;
import java.util.Map;

import me.chiefbeef.core.gui.page.Page;
import me.chiefbeef.core.gui.page.variant.dynamic.DynamicPage;
import me.chiefbeef.core.utility.persistence.Data;

public class PersistentSlotHolder extends Data {
	
	private Map<Class<? extends Page>, PageLocation> slots = new HashMap<>();
	
	
	
	public int getSlot(Class<? extends Page> page) {
		PageLocation pageLoc = slots.get(page);
		return pageLoc == null ? -999 : pageLoc.getSlot();
	}
	
	public int getIndex(Class<? extends Page> page) {
		PageLocation pageLoc = slots.get(page);
		return pageLoc == null ? -999 : pageLoc.getIndex();
	}
	
	public void removeSlot(Class<? extends Page> page) {
		slots.remove(page);
	}
	
	public PersistentSlotHolder setSlot(Page page, int slot) {
		slots.put(page.getClass(), new PageLocation(slot, 
				(page instanceof DynamicPage) ? ((DynamicPage)page).getCurrentIndex() : 0));
		return this;
	}
	
	public boolean contains(Class<? extends Page> page) {
		return slots.containsKey(page);
	}
	
	private static class PageLocation {
		private int slot;
		private int index;
		
		protected PageLocation(int slot, int index) {
			this.slot = slot;
			this.index = index;
		}
		
		protected int getSlot() {
			return slot;
		}
		
		protected int getIndex() {
			return index;
		}
	}
	
}
