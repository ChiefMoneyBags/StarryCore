package me.chiefbeef.core.customitem.tracking;

import me.chiefbeef.core.customitem.CustomItem;

/**
 * Abstract class representing an object who's purpose it is to track the whereabouts of a CustomItem
 * so that its meta may be updated in real time and it can be destroyed in its entirety.
 * @author Kevin
 *
 */
public abstract class ItemTracker {

	private CustomItem custom;
	
	public ItemTracker(CustomItem custom) {
		this.custom = custom;
	}
	
	/**
	 * 
	 * @return The custom item this tracker is responsible for.
	 */
	public CustomItem getCustomItem() {
		return custom;
	}
	
	/**
	 * Remove / destroy all items representations in this tracker.
	 */
	public abstract void removeItem();
	
	/**
	 * Update all item representations being tracked.
	 */
	public abstract void updateItem();

	/**
	 * 
	 * @return True if this tracker is currently tracking a representation of the CustomItem
	 */
	public abstract boolean holdsReference();

}
