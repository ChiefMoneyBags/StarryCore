package me.chiefbeef.core.gui.transition.variant.other;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import me.chiefbeef.core.aesthetic.sound.interfacing.GuiSoundPack.GuiSound;
import me.chiefbeef.core.gui.GuiSession;
import me.chiefbeef.core.gui.GuiTheme;
import me.chiefbeef.core.gui.GuiTheme.GuiElement;
import me.chiefbeef.core.gui.cookie.GuiCookie;
import me.chiefbeef.core.gui.cookie.GuiSessionCookies;
import me.chiefbeef.core.gui.cookie.variant.SelectedItem;
import me.chiefbeef.core.gui.page.Page;
import me.chiefbeef.core.gui.transition.GuiTransition;
import me.chiefbeef.core.gui.transition.variant.swipe.TransitionSwipeLeft;
import me.chiefbeef.core.utility.Console;
import me.chiefbeef.core.utility.gui.Pages;

/**
 * {@link TransitionItemSelect} is a {@link GuiTransition} that animates an item a user selects inside of
 * a {@link Page} and moves it to the top center of the {@link Page}, representing a selected item.
 * 
 * This {@link GuiTransition} requires at least one {@link GuiCookie} with the type of {@link SelectedItem} to
 * be registered in the {@link GuiSession}'s {@link GuiSessionCookies} prior to its invocation.
 * 
 * For as long as the {@link GuiSessionCookies} contains the {@link SelectedItem} {@link GuiCookie} the selected item
 * will remain in slot 5 of every subsequent {@link Page} in the gui chain and the top row of the inventory will
 * not be available for {@link Page} building as it will be used as the item display.
 * @author Kevin
 *
 */
public class TransitionItemSelect extends GuiTransition {
	
	private int
		start,
		step;
	
	private ItemStack selected;
	
	private GuiTheme theme;

	private Map<Integer, ItemStack> move = new ConcurrentHashMap<>();
	private int
		itemSlot,
		ministep = 0;
	
	private List<Integer> top = new ArrayList<>();

	@Override
	public void onStart() {
		GuiSessionCookies cookies = session.getCookies();
		if (!cookies.hasCookie(SelectedItem.class)) {
			Console.generateException("Missing data type in the GuiTransition (TransitionItemSelect)! This transition requires the data type: (SelectedItem)");
			cancel();
			return;
		}
		SelectedItem selectedItem = (SelectedItem) cookies.getDeepestInstanceOf(SelectedItem.class);
		start = selectedItem.getOriginSlot();
		selected = selectedItem.getItem();
		theme = session.getTheme();
		
		itemSlot = start;
		for (int i: Pages.getTopEdge()) {
			top.add(i);
		}
		for (int i = 0; i < inv.getSize(); i++) {
			ItemStack it = inv.getItem(i);
			if (it == null || Pages.isBaseTile(it) || i == start) {
				continue;
			}
			move.put(i, it);
		}
	}
	
	@Override
	public void nextFrame(int frame) {
		switch (step) {
		case 0:
			stepA();
			break;
		case 1:
			stepB();
			break;
		case 2:
			stepC();
			break;
		}
	}
	
	/**
	 * Step A is where all of the non selected items that are not gui base tiles are animated to move
	 * out of the inventory
	 */
	private void stepA() {
		for (Entry<Integer, ItemStack> entry: move.entrySet()) {
			//If this is the first iteration play some sweet sound effects
			if (ministep == 0) {
				theme.playSound(GuiSound.ANIM_SELECT_1);
				ministep++;
			}
			int slot = entry.getKey();
			int direction = getDirection(slot);
			if (canMove(slot, direction)) {
				int newSlot = slot + direction;
				
				setSlot(slot, theme.get(from, slot));
				move.remove(slot);
				
				if (Pages.isPresent(inv, newSlot)
						&& ( !Pages.isLeftEdge(slot) && !Pages.isRightEdge(slot) )) {
					setSlot(newSlot, entry.getValue());
					move.put(slot + direction, entry.getValue());
				}
			} else if (canMove(slot, 9)) {
				int newSlot = slot + 9;
				
				setSlot(slot, theme.get(from, slot));
				move.remove(slot);
				
				if (Pages.isPresent(inv, newSlot)
						&& ( !Pages.isLeftEdge(slot) && !Pages.isRightEdge(slot) )) {
					setSlot(newSlot, entry.getValue());
					move.put(newSlot, entry.getValue());
				}
			} else {
				setSlot(slot, theme.get(from, slot));
				move.remove(slot);
			}
		}
		if (move.size() == 0) {
			step++;
		}
	}
	
	/**
	 * In step B the selected item is moved to the center of the {@link Inventory} and subsequently to slot 5.
	 */
	private void stepB() {
		if (Pages.isRightSide(itemSlot)) {
			setSlot(itemSlot-1, selected);
			setSlot(itemSlot, theme.get(from, itemSlot));
			itemSlot--;
			
		} else if (Pages.isLeftSide(itemSlot)) {
			setSlot(itemSlot+1, selected);
			setSlot(itemSlot, theme.get(from, itemSlot));
			itemSlot++;
			
		} else {
			if (Pages.isPresent(inv, itemSlot-9)) {
				setSlot(itemSlot-9, selected);
				setSlot(itemSlot, theme.get(from, itemSlot));
				itemSlot -= 9;
			} else {
				ministep = 1;
				step++;
			}
		}
	}
	
	/**
	 * In step C the top row of the {@link Inventory} is turned into a colored bar surrounding the selected item
	 * starting from the center and growing outward until it reaches the edge of the {@link Inventory}.
	 */
	private void stepC() {
		if (ministep == 1) {
			theme.playSound(GuiSound.ANIM_SELECT_2);
		}
		setSlot(itemSlot+ministep, theme.get(GuiElement.SELECTED));
		setSlot(itemSlot-ministep, theme.get(GuiElement.SELECTED));
		ministep++;
		if (ministep == 5) {
			session.transitionTo(to, TransitionSwipeLeft.class);
		}
	}

	private boolean canMove(int slot, int direction) {
		return !move.containsKey(slot + direction) && itemSlot != slot + direction;
	}
	
	private int getDirection(int slot) {
		if (Pages.isLeftSide(slot)) {
			return -1;
		} else if (Pages.isRightSide(slot)) {
			return 1;
		} else {
			return 9;
		}
	}

	@Override
	public boolean hasNextFrame() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
