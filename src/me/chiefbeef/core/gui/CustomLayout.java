package me.chiefbeef.core.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import me.chiefbeef.core.customitem.CustomItem;

import java.util.Set;

public class CustomLayout {

	private Map<Integer, CustomItem> layout = new HashMap<>();
	private List<CustomItem> broken = new ArrayList<>();
	
	public void clear() {
		layout.clear();
		broken.clear();
	}
	
	public CustomItem get(int slot) {
		return layout.get(slot);
	}
	
	public void addBroken(CustomItem item) {
		broken.add(item);
	}
	
	public CustomItem[] getBroken() {
		return broken.toArray(new CustomItem[broken.size()]);
	}
	
	public void set(int slot, CustomItem item) {
		layout.put(slot, item);
	}
	
	public boolean contains(int slot) {
		return layout.containsKey(slot);
	}
	
	public Set<Entry<Integer, CustomItem>> getLayout() {
		return layout.entrySet();
	}

}
