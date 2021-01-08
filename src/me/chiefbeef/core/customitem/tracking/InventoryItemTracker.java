package me.chiefbeef.core.customitem.tracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.chiefbeef.core.customitem.CustomItem;
import me.chiefbeef.core.utility.Console;

/**
 * Keeps track of where the CustomItem currently is inside of Inventories.
 * @author Kevin
 */
public class InventoryItemTracker extends ItemTracker {

	private static Map<Inventory, List<InventoryItemTracker>> lookup = new HashMap<>();
	
	/**
	 * 
	 * @param inv The inventory to check
	 * @return All CustomItem inventory trackers present in the inventory.
	 */
	public static InventoryItemTracker[] lookupByInventory(Inventory inv) {
		List<InventoryItemTracker> trackers = lookup.getOrDefault(inv, new ArrayList<>());
		return trackers.toArray(new InventoryItemTracker[trackers.size()]);
	}
	
	private Map<Inventory, List<Integer>> slots = new HashMap<>();
	
	public InventoryItemTracker(CustomItem custom) {
		super(custom);
	}

	/**
	 * Add a slot to the inventory tracker.
	 *
	 * The tracker will now see this slot as an instance of the CustomItem
	 * @param inv
	 * @param slot
	 */
	public void addSlot(Inventory inv, Integer slot, boolean update) {
		Console.debug("", "----InventoryItemTracker---", "--| Adding slot: " + slot);
		List<Integer> list = slots.getOrDefault(inv, new ArrayList<>());
		if (list.contains(slot)) {
			return;
		}
		
		duplicateSlotCheck(inv, slot, update);
		
		list.add(slot);
		slots.put(inv, list);
		
		Console.debug("--| New slots: " + list.toString());
		List<InventoryItemTracker> trackers = lookup.getOrDefault(inv, new ArrayList<>());
		trackers.add(this);
		lookup.put(inv, trackers);
	}
	
	/**
	 * Check that this slot is not already being tracked by another item, if it is, remove it.
	 * @param inv The inventory to check.
	 * @param slot The slot to check.
	 */
	private void duplicateSlotCheck(Inventory inv, int slot, boolean update) {
		for (InventoryItemTracker tracker: lookupByInventory(inv)) {
			if (tracker.isTracking(inv, slot)) {
				Console.debug("--|> Slot is already tracked in this inv!", "--| removing previous item tracker from this inv");
				tracker.removeSlot(inv, slot, update);
			}
		}
	}
	
	/**
	 * Remove a slot from the inventory tracker.
	 * 
	 * The tracker will no longer see this slot as an instance of the CustomItem
	 * @param inv
	 * @param slot
	 */
	public void removeSlot(Inventory inv, Integer slot, boolean update) {
		Console.debug("", "----InventoryItemTracker---", "--| Removing slot: " + slot);
		List<Integer> list = slots.getOrDefault(inv, new ArrayList<>());
		list.remove(slot);
		Console.debug("--| New slots: " + list.toString());
		if (list.size() < 1) {
			removeInventory(inv, update);
			return;
		}
		slots.put(inv, list);
	}
	
	/**
	 * Remove an entire inventory from the tracker
	 * 
	 * @param inv The inventory to remove from the tracker.
	 */
	public void removeInventory(Inventory inv, boolean update) {
		Console.debug("", "----InventoryItemTracker---", "--|> Removing tracker from inventory...");
		slots.remove(inv);
		
		//Remove this tracker from the global lookup for this inv.
		if (lookup.containsKey(inv)) {
			Console.debug("--| Removing inventory from static lookup table...");
			List<InventoryItemTracker> trackers = lookup.getOrDefault(inv, new ArrayList<>());
			trackers.remove(this);
			if (trackers.size() > 0) {
				lookup.put(inv, trackers);	
			} else {
				lookup.remove(inv);
			}
		}
		
		if (update && !holdsReference()) {
			Console.debug("--|> This tracker no longer holds reference to the custom item!", "--|> Invoking tracker re-evaluation...");
			getCustomItem().evaluateTrackers();
		}
	}
	
	/**
	 * Get the slots that are being tracked for the inventory in question.
	 * 
	 * @param inv The inventory being tracked.
	 * @return Never null
	 */
	public Integer[] getSlots(Inventory inv) {
		List<Integer> list = slots.getOrDefault(inv, new ArrayList<>());
		return list.toArray(new Integer[list.size()]);
	}
	
	/**
	 * Check if this tracker is currently tracking a specific slot in the inventory.
	 * 
	 * @param inv The inventory in question.
	 * @param slot The slot in question.
	 * @return True if the tracker is currently tracking the slot.
	 */
	public boolean isTracking(Inventory inv, int slot) {
		return slots.containsKey(inv) && slots.get(inv).contains(slot);
	}
	
	@Override
	public void removeItem() {
		for (Map.Entry<Inventory, List<Integer>> entry: slots.entrySet()) {
			for (int slot: entry.getValue()) {
				entry.getKey().setItem(slot, null);
			}	
			removeInventory(entry.getKey(), true);
		}
		
	}

	@Override
	public void updateItem() {
		//Console.debug("", "----InventoryItemTracker---", "--|> Updating all item references...");
		ItemStack item = getCustomItem().getItem();
		Iterator<Entry<Inventory, List<Integer>>> itInv = slots.entrySet().iterator();
		while (itInv.hasNext()) {
			Map.Entry<Inventory, List<Integer>> entry = itInv.next();
			Inventory inv = entry.getKey();
			
			//TODO Fix this ghetto shit
			List<Integer> list = new ArrayList<>();
			list.addAll(entry.getValue());
			Iterator<Integer> itInt = list.iterator();
			//TODO
			
			while (itInt.hasNext()) {
				int slot = itInt.next();
				ItemStack check = inv.getItem(slot);
				if (!CustomItem.isCustom(check) || CustomItem.fromItemStack(check) != getCustomItem()) {
					//Console.debug("--|> Slot (" + slot + ") no longer contains the CustomItem in a continuity check, removing.");
					removeSlot(inv, slot, true);
					continue;
				}
				inv.setItem(slot, item);
			}
		}

	}
	
	@Override
	public boolean holdsReference() {
		return slots.size() > 0;
	}
	
}
