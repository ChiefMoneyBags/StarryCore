package me.chiefbeef.core.gui.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.chiefbeef.core.compatibility.CompatSound;
import me.chiefbeef.core.customitem.CustomItem;
import me.chiefbeef.core.customitem.tracking.CursorItemTracker;
import me.chiefbeef.core.customitem.tracking.InventoryItemTracker;
import me.chiefbeef.core.gui.CustomLayout;
import me.chiefbeef.core.gui.GuiSession;
import me.chiefbeef.core.gui.GuiTheme;
import me.chiefbeef.core.gui.GuiTheme.GuiElement;
import me.chiefbeef.core.gui.button.GuiButton;
import me.chiefbeef.core.gui.cookie.GuiSessionCookies;
import me.chiefbeef.core.gui.cookie.variant.SelectedItem;
import me.chiefbeef.core.utility.Console;
import me.chiefbeef.core.utility.gui.InventoryResolution;
import me.chiefbeef.core.utility.gui.Pages;
import me.chiefbeef.core.utility.persistence.gui.PersistentSlotHolder;

/**
 * {@link Page} is an object that represents an inventory that will be used as a gui page within a {@link GuiSession} 
 * @author Kevin
 *
 */
public abstract class Page extends CoreGuiHandler {
	
	//Slots in the gui registered as placeable, containing all objects types that may be placed in said slot.
	private Map<Integer, List<Class<?>>> placeable = new HashMap<>();
	
	//The layout of CustomItems in the gui.
	protected CustomLayout layout = new CustomLayout();
	
	
	public Page(GuiSession session, int size) {
		super.setGuiSession(session);
		super.setInventory(Bukkit.createInventory(null, size < 18 ? 18 : size));
	}
	
	public Page(GuiSession session, Inventory inv) {
		super.setGuiSession(session);
		super.setInventory(inv);
	}
	
	/**
	 * Invoked when the player drags in the inventory.
	 * @param e
	 */
	public void onDrag(final InventoryDragEvent e) {
		int rawSlot= -999;
		for (int slot: e.getRawSlots()) {
			rawSlot = slot;
		}
		InventoryResolution resolution = Pages.resolveInventory(e.getView(), rawSlot);
		Inventory clicked = resolution.getInventory();
		if (clicked != super.getInventory()) {
			Console.debug("", "drag event different inv");
			return;
		}
		Console.debug("", "canceling drag");
		e.setCancelled(true);
		Set<Integer> slots = e.getInventorySlots();
		if (slots.size() > 0) {
			onClick(new InventoryClickEvent(session.getUser().getPlayer().getOpenInventory(), SlotType.CONTAINER, slots.iterator().next(), ClickType.LEFT, InventoryAction.PLACE_ONE));
		}
	}
	

	/**
	 * Invoked when the player clicks the gui.
	 * @param e
	 */
	public void onClick(final InventoryClickEvent e) {
		final Inventory clicked = e.getClickedInventory();
		// get ouuttaa heere
		if (clicked != inv) {
			return;
		}
		
		e.setCancelled(true);
		final int slot = e.getSlot();
		
		// buttons
		Console.debug(slot);
		if (isButton(slot)) {
			Console.debug("is a button");
			GuiButton button = buttons.get(slot);
			if (!onButtonClick(button)) {
				button.invoke(session);
				return;
			}
		}
		
		// ToolBar
		if (slot == inv.getSize() - 5 && session.getHistory().hasPreviousPage()) {
			onBackButton();
			return;
		}
		
		//Placeable slot
		if (isPlaceable(slot) && e.getClick() == ClickType.LEFT) {
			Console.debug("--| Placeable slot clicked");
			ItemStack cursor = e.getCursor();
			ItemStack current = e.getCurrentItem();
			
			if (!canPlace(slot, cursor, current) || onPlaceable(slot, cursor, current)) {
				Console.debug("--|> Place cancelled... ");
				return;
			}
			
			Player p = session.getUser().getPlayer();
			setSlot(slot, cursor == null || cursor.getType() == Material.AIR ? session.getTheme().get(GuiElement.PLACEABLE) : cursor);
			if (CustomItem.isCustom(current)) {
				CustomItem custom = CustomItem.fromItemStack(current);
				Console.debug("--| Current item is custom. Removing inventory tracker and adding to cursor", "--| Current: " + custom.getLabel());
				CursorItemTracker cit = custom.getTracker(CursorItemTracker.class);
				cit.addCursor(session.getUser().getPlayer());
				InventoryItemTracker iit = custom.getTracker(InventoryItemTracker.class);
				iit.removeSlot(clicked, slot, true);
			}
			
			p.setItemOnCursor(Pages.isBaseTile(current) ? null : current);
		}
		return;
	}
	
	/**
	 * invoked when the user clicks the back button.
	 */
	public void onBackButton() {
		Console.debug("on back button");
		session.goBack();
	}
	
	
	/**
	 * Define a slot as "Placeable", Allow player to place items.
	 * This will flag the slot and the abstract Page will handle listeners and item management of the click event.
	 * @param slot The slot to be placeable
	 * @param allowed An array of declared classes that are allowed to be placed in the slot. ItemStack.class covers all items and Any class extending CustomItem is supported.
	 */
	public void setPlaceable(int slot, Class<?>... allowed) {
		List<Class<?>> classes = placeable.getOrDefault(slot, new ArrayList<>());
		classes.addAll(Arrays.asList(allowed));
		placeable.put(slot, classes);
		applyElement(GuiElement.PLACEABLE, slot);
	}
	
	/**
	 * Clears all placeable slots.
	 */
	public void clearPlaceable() {
		this.placeable.clear();
	}
	
	
	/**
	 * Invoked before the player places an item in a slot defined as being placeable. This call happens after
	 * safety checks have already occured and it is certain the item being placed in the gui is one of those that
	 * have been registered for the slot involved.
	 * @param slot The slot clicked.
	 * @param placed The item being placed.
	 * @param got The item the player got from the gui.
	 * @return True if you want the transaction to be cancelled.
	 */
	public boolean onPlaceable(int slot, ItemStack placed, ItemStack got) {
		return false;
	}
	
	/**
	 * Can the player place / pickup an item in the given gui slot?
	 * @param slot The slot clicked
	 * @param placed The item placed in gui
	 * @param got The item the player got from the gui.
	 * @return true if the placeable check passed. false if the transaction is not be allowed.
	 */
	public boolean canPlace(int slot, ItemStack placed, ItemStack got) {
		List<Class<?>> classes = placeable.get(slot);
		if (classes == null || classes.size() == 0) {
			return false;
		}
		if ((placed != null && placed.getType() != Material.AIR) && !classes.contains(ItemStack.class)) {
			if (!CustomItem.isCustom(placed)) {
				return false;
			}
			CustomItem custom = CustomItem.fromItemStack(placed);
			if (!classes.stream().anyMatch(c -> c.isAssignableFrom(custom.getClass()))) {
				Console.debug("no matches");
				return false;
			}
		}
		
		if (placed == null || placed.getType() == Material.AIR && Pages.isBaseTile(got)) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * @return true if the slot requested is placeable.
	 */
	public boolean isPlaceable(int slot) {
		return placeable.containsKey(slot);
	}
	
	
	
	
	/**
	 * This method will create the basic page layout including the background and toolbar as well as other complex things such as SelectedItem data
	 * Should be called first when building a gui as it overwrites every slot.
	 */
	public void build() {
		GuiTheme theme = session.getTheme();
		for (int i = 0; i < inv.getSize() - 9; i++) {
			this.setSlot(i, theme.get(GuiElement.BACKGROUND));
		}
		for (int i = inv.getSize() - 9; i < inv.getSize(); i++) {
			this.setSlot(i, theme.get(GuiElement.TOOLBAR));
		}
		if (session.getHistory().hasPreviousPage()) {
			this.setSlot(inv.getSize()-5, theme.get(GuiElement.BACK_BUTTON));
		}
		GuiSessionCookies cookies = session.getCookies();
		if (cookies.hasCookie(SelectedItem.class)) {
			SelectedItem selectedItem = (SelectedItem) cookies.getDeepestInstanceOf(SelectedItem.class);
			displaySelectedItem(selectedItem);
		}
	}
	
	/**
	 * Invoked when the gui is going to display the selected item, if you dont want this to happen just overide and return.
	 * @param selectedItem
	 */
	public void displaySelectedItem(SelectedItem selectedItem) {
		for (int i = 0; i <= 8; i++) {
			setSlot(i, i == 4 ? selectedItem.getItem() : session.getTheme().get(GuiElement.SELECTED));
		}
	}
	
	/**
	 * Load CustomItems into the slots they were previously saved, useful for persistence of item location
	 * in a gui that can be interacted with by players. The method returns void as the data loaded will be
	 * put into the field "layout" that you can then use to get the items placement.
	 * @param items Array of items expected to be in the gui.
	 * @param allowed Slots these items are allowed to be in.
	 */
	public void loadPersistentItems(CustomItem[] items, List<Integer> allowed) {
		layout.clear();
		
		Class<? extends Page> clazz = this.getClass();
		
		for (CustomItem custom: items) {
			
			//Load the custom items persistent slot data.
			PersistentSlotHolder slots = custom.getDataPack().getType(PersistentSlotHolder.class).getData();
			if (slots != null && slots.contains(clazz)) {
				int slot = slots.getSlot(clazz);
				
				//if 2 items are defined as having the same slot, or the slot defined isnt allowed something broke.
				if (layout.contains(slot) || !allowed.contains(slot)) {
					boolean fixed = false;
					
					//Remove the defined slot from the CustomItem as it cannot obtain this slot anymore.
					slots.removeSlot(clazz);
					
					//Iterate through open slots and try to find a new slot for the CustomItem
					for (int next: allowed) {
						
						//new slot found
						if (!layout.contains(next)) {
							layout.set(next, custom);
							slots.setSlot(this, slot);
							fixed = true;
							break;
						}
					}
					
					if (!fixed) {
						layout.addBroken(custom);
					}
				} else {
					layout.set(slot, custom);
				}
			} else {
				
			}
		}
	}
	
	public boolean animateToolbar() {
		return true;
	}
}
