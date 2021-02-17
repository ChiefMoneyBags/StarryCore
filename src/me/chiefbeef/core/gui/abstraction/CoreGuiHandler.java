package me.chiefbeef.core.gui.abstraction;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.chiefbeef.core.customitem.CustomItem;
import me.chiefbeef.core.customitem.tracking.InventoryItemTracker;
import me.chiefbeef.core.customitem.tracking.ItemTracker;
import me.chiefbeef.core.gui.GuiSession;
import me.chiefbeef.core.gui.GuiTheme.GuiElement;
import me.chiefbeef.core.gui.buttons.GuiButton;
public abstract class CoreGuiHandler {

	protected GuiSession session = null;
	protected Inventory inv;
	
	protected Map<Integer, GuiButton> buttons = new HashMap<>();
	
	
	/**
	 * refresh inventory for all viewers.
	 */
	public void refresh() {
		for (HumanEntity ent: inv.getViewers()) {
			if (ent instanceof Player) {
				//TODO
				//UserCore.get((Player)ent).getGui().refresh();
			}
		}
	}
	
	/**
	 * 
	 * @return The session associated with this page.
	 */
	public GuiSession getGuiSession() {
		return session;
	}
	
	/**
	 * Sets the {@link GuiSession} for this GuiHandler
	 * @param session
	 */
	public void setGuiSession(GuiSession session) {
		this.session = session;
	}
	
	/**
	 * Get the inventory associated with this gui handler
	 * @return the inventory
	 */
	public Inventory getInventory() {
		return inv;
	}
	
	/**
	 * Forcefully set the pages inventory.
	 * @param inv
	 */
	public void setInventory(Inventory inv) {
		this.inv = inv;
	}
	
	/**
	 * Apply a GuiElement to specific slots on top of the base page layout. build() should still be called first. 
	 * @param element The element to set.
	 * @param slots Slots to be affected
	 */
	public void applyElement(GuiElement element, int... slots) {
		ItemStack item = session.getTheme().get(element);
		for (int slot: slots) {
			setSlot(slot, item);
		}
	}
	
	/**
	 * Set an item in the inventory.
	 * Ease of access method.
	 * @param slot
	 * @param item
	 */
	public void setSlot(int slot, ItemStack item) {
		inv.setItem(slot, item);
	}
	
	
	/**
	 * This method should be used to set {@link CustomItem}s in the {@link Inventory} otherwise the {@link ItemTracker}s
	 * will not know the {@link CustomItem} is in the {@link Inventory}. This can cause issues if done improperly.
	 * @param slot
	 * @param custom
	 */
	public void setSlot(int slot, CustomItem custom) {
		inv.setItem(slot, custom.getItem());
		InventoryItemTracker iit = custom.getTracker(InventoryItemTracker.class);
		if (!iit.isTracking(inv, slot)) {
			iit.addSlot(inv, slot, true);
		}
	}
	
	/**
	 * Register a new button to the gui.
	 * @param slot the slot number
	 * @param button the new button.
	 */
	public void setSlot(int slot, GuiButton button) {
		buttons.put(slot, button);
		setSlot(slot, button.getItem());
	}
	
	/**
	 * i restructured stuff around now i need this
	 * @param slot
	 * @param item
	 */
	public void setSlotObject(int slot, Object item) {
		if (item instanceof CustomItem) {
			setSlot(slot, (CustomItem) item);	
		} else if (item instanceof ItemStack) {
			setSlot(slot, (ItemStack) item);	
		} else if (item instanceof GuiButton){
			setSlot(slot,((GuiButton)item));
		}
	}
}
