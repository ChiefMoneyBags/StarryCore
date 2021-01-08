package me.chiefbeef.core.gui.abstraction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.bukkit.event.inventory.InventoryClickEvent;
import me.chiefbeef.core.customitem.CustomItem;
import me.chiefbeef.core.gui.GuiSession;
import me.chiefbeef.core.gui.GuiTheme.GuiElement;
import me.chiefbeef.core.gui.transition.swipe.TransitionSwipeLeft;
import me.chiefbeef.core.gui.transition.swipe.TransitionSwipeRight;
import me.chiefbeef.core.utility.Console;
import me.chiefbeef.core.utility.persistence.gui.PersistentSlotHolder;

/**
 * {@link BasicDynamicPage} is a {@link DynamicPage} type that formats elements in a {@link Page} using a simplistic algorithim.
 * It will fill full rows until the amount of remaining elements is no longer enough for a full row. It will then center
 * the remaining elements in the final row.
 * It is also capable of creating unlimited page indexes if so required by the amount of elements present.
 * @author Kevin
 *
 */
public abstract class BasicDynamicPage extends DynamicPage {
	
	// slots = number of dynamic slots IE: how many items are to be displayed in the gui
	private int
	indexSlots, maxSlots;
	
	private List<Integer> dynamicSlots = new ArrayList<>();
	
	
	/**
	 * 
	 * @param session The session of the player owning this gui.
	 * @param size The size of the gui.
	 * @param initialIndex The initial index to set the dynamic page to. 
	 * @param indexSlots The amount of slots allowed in this specific page index.
	 */
	public BasicDynamicPage(GuiSession session, int size, int initialIndex, int indexSlots) {
		// Force the dynamic page to be at least 3 rows.
		super(session, size < 36 ? 36 : size, initialIndex);
		this.indexSlots = indexSlots;
	}
	
	@Override
	public void build() {
		super.clearPlaceable();
		super.build();
		
		if (hasIndex(getCurrentIndex() + 1)) {
			applyElement(GuiElement.NEXT_PAGE, inv.getSize()-1);
		}
		
		if (hasIndex(getCurrentIndex() - 1)) {
			applyElement(GuiElement.PREV_PAGE, inv.getSize()-9);
		}
		
		calculateDynamicSlots();
	}
	
	@Override
	public void onClick(final InventoryClickEvent e) {
		super.onClick(e);
		int slot = e.getSlot();
		if (slot == (inv.getSize() - 9) && (hasIndex(getCurrentIndex() - 1))) {
			 setCurrentIndex(getCurrentIndex() - 1);
			 session.transitionTo(this, TransitionSwipeLeft.class);
			 return;
		 } else if (slot == (inv.getSize() - 1) && (hasIndex(getCurrentIndex() + 1))) {
			 setCurrentIndex(getCurrentIndex() + 1);
			 session.transitionTo(this, TransitionSwipeRight.class);
			 return;
		 }
		return;
	}
	
	/**
	 * Calculate the next set of dynamic slots based on the current index.
	 * @param maxSlots The total amount of slots allowed in the dynamic page.
	 * @param indexSlots The amount of slots allowed in this specific page index.
	 * @return The list of dynamic slots in the current page index.
	 */
	private static final List<Integer> genericRow = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
	public List<Integer> calculateDynamicSlots() {
		dynamicSlots.clear();
		int rows = (int) Math.floor(inv.getSize() / 9) - 3;
		int dynamicRemainder = (maxSlots - (getCurrentIndex()*indexSlots));
		
		int indexTotal = 0;
		for (int currentRow = 0; currentRow < rows; currentRow++) {
			for (int slot: indexTotal + 7 > dynamicRemainder ? getRowLayout(dynamicRemainder - indexTotal)
					: indexTotal + 7 > indexSlots ? getRowLayout(indexSlots - indexTotal) : genericRow) {
				indexTotal++;
				dynamicSlots.add(slot + (9 * (currentRow+1)));
			}
			if (indexTotal >= dynamicRemainder || indexTotal == indexSlots) {
				break;
			}
		}
		Console.debug(dynamicSlots);
		return dynamicSlots;
	}
	
	
	/**
	 * Place the dynamic items in the gui.
	 */
	public void buildDynamicItems() {
		if (items.size() == 0) {
			Console.debug("--| There are no dynamic items in the page.");
			return;
		}
		if (dynamicSlots.size() == 0) {
			Console.debug("--| There are no dynamic slots in this index.");
			return;
		}
		int skipdex = indexSlots * getCurrentIndex();
		if (skipdex > items.size()) {
			Console.debug("--| The skipdex exceeds the item count in this page index.");
			return;
		}
		
		Iterator<Object> itItems = items.iterator();
		for (int i = 0; i < skipdex; i++) {itItems.next();}
		Iterator<Integer> itSlots = dynamicSlots.iterator();
		while(itItems.hasNext() && itSlots.hasNext()) {
			Object item = itItems.next();
			int slot = itSlots.next();
			setSlotObject(slot, item);
		}
	}
	
	@Override
	public void loadPersistentItems(CustomItem[] customs, List<Integer> allowed) {
		List<CustomItem> newList = new ArrayList<>();
		for (CustomItem custom: customs) {
			PersistentSlotHolder slots = custom.getDataPack().getType(PersistentSlotHolder.class).getData();
			if (slots == null) {
				layout.addBroken(custom);
				continue;
			}
			if (slots.getIndex(this.getClass()) != this.getCurrentIndex()) {
				continue;
			}
			newList.add(custom);
		}
		super.loadPersistentItems(newList.toArray(new CustomItem[newList.size()]), allowed);
	}
	
	
	public void setMaxSlots(int maxSlots) {
		this.maxSlots = maxSlots;
	}
	
	public void setSlotsPerIndex(int indexSlots) {
		this.indexSlots = indexSlots;
	}
	
	public void setDynamicSlotsPlaceable(Class<?>... allowed) {
		for (int slot: dynamicSlots) {
			setPlaceable(slot, allowed);
		}
	}
	
	
	@Override
	public boolean hasIndex(int index) {
		return index*indexSlots <= maxSlots && index > -1;
	}
	
	
	/**
	 * @return The maximum number of dynamic slots that can fit in the current gui.
	 */
	public int getSlotsPerIndex() {
		return indexSlots;
	}
	
	/**
	 * 
	 * @return The number of available rows for item placement. Not including rows reserved for toolbar and gui frame.
	 */
	public int getAvailableRows() {
		return (inv.getSize() - 27) / 9;
	}
	
	/**
	 * 
	 * @param remainder The number of slots in the row.
	 * @return The centered layout for the items in the row.
	 */
	public static List<Integer> getRowLayout(int remainder) {
		switch (remainder) {
		case 1:
			return Arrays.asList(4);
		case 2:
			return Arrays.asList(3, 5);
		case 3:
			return Arrays.asList(3, 4, 5);
		case 4:
			return Arrays.asList(2, 3, 5, 6);
		case 5:
			return Arrays.asList(2, 3, 4, 5, 6);
		case 6:
			return Arrays.asList(1, 2, 3, 5, 6, 7);
		default:
			return Arrays.asList();
		}
	}
	
	public List<Integer> getDynamicSlots() {
		return dynamicSlots;
	}

}
