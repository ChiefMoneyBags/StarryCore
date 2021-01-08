package me.chiefbeef.core.gui.abstraction;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.chiefbeef.core.customitem.CustomItem;
import me.chiefbeef.core.gui.GuiSession;
import me.chiefbeef.core.gui.buttons.GuiButton;
import me.chiefbeef.core.utility.Console;
import me.chiefbeef.core.utility.gui.Pages;

/**
 * {@link DynamicPage} is an object whos purpose is to handle the creation, formatting and layout of
 * a {@link Page} that does not have a predetermined amount of elements and subsequently, an unknown
 * amount of possible indexes.
 * @author Kevin
 *
 */
public abstract class DynamicPage extends Page {

	private Inventory previousIndex;
	
	//Store items as object as they can be ItemStack, CustomItem or GuiButton 
	protected List<Object> items = new ArrayList<>();
	
	// size = size of inv,
	// slots = number of dynamic slots IE: how many items are to be displayed in the gui
	// index = the page number, like pages in a book.
	private int currentIndex;
	
	public DynamicPage(GuiSession session, int size, int initialIndex) {
		super(session, size);
		this.currentIndex = initialIndex;
	}
	
	/**
	 * Set the page index.
	 * @param Index new index
	 */
	public void setCurrentIndex(int index) {
		if (hasIndex(index)) {
			this.previousIndex = Pages.copy(inv);
			this.currentIndex = index;
			build();
		}
	}
	
	public Inventory getPreviousInventory() {
		return previousIndex;
	}
	
	public boolean hasPreviousInventory() {
		
		return previousIndex != null;
	}
	
	public int getCurrentIndex() {
		return currentIndex;
	}
	
	/**
	 * Set the list of items that will be placed in the gui
	 * Valid objects include ItemStack, CustomItem and GuiButton.
	 * @param items The items to set. Allowed Objects include CustomItem and ItemStack.
	 */
	public void setDynamicItems(List<Object> items) {
		//ay im walkin heere
		if (items == null) {
			items = new ArrayList<>();
		}
		this.items = items;
	}
	
	/**
	 * Add a dynamic item to the gui page.
	 * Valid objects include ItemStack, CustomItem and GuiButton.
	 * @param item
	 */
	public void addDynamicItem(Object item) {
		if (item instanceof CustomItem || item instanceof ItemStack || item instanceof GuiButton) {
			items.add(item);
			return;
		}
		Console.generateException("An object has been registered in a dynamic gui that is not of a valid type! (" + item.getClass().toGenericString() + ")");
	}
	
	/**
	 * Remove a dynamic item from the gui page.
	 * Valid objects include ItemStack, CustomItem and GuiButton.
	 * @param item
	 */
	public void removeDynamicItem(Object item) {
		items.remove(item);
	}
	
	/**
	 * Check if a dynamic item is registered in the gui page.
	 * Valid objects include ItemStack, CustomItem and GuiButton.
	 * @param item
	 */
	public boolean hasDynamicItem(Object object) {
		return items.contains(object);
	}
	
	@Override
	public boolean animateToolbar() {
		return false;
	}
	
	public abstract boolean hasIndex(int index);
	
}
