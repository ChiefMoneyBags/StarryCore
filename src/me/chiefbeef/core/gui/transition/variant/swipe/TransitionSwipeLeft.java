package me.chiefbeef.core.gui.transition.variant.swipe;

import java.util.Iterator;

import me.chiefbeef.core.gui.transition.GuiTransition;
import me.chiefbeef.core.utility.Console;
import me.chiefbeef.core.utility.gui.Pages;

public class TransitionSwipeLeft extends SwipeTransition {
	
	@Override
	public void nextFrame(int frame) {		
		//move the inventory 1 space right, ignore right most column.
		for (int slot = inv.getSize() - 1; slot > 0; slot--) {
			if (Pages.isLeftEdge(slot) || (!canAnimate(slot))) {
				continue;
			}
			setSlot(slot, inv.getItem(slot - 1));
		}
		
		//move the next column of the animation into the inventory
		Iterator<Integer> itRight = Pages.getRightEdge().iterator();
		Iterator<Integer> itLeft = Pages.getLeftEdge().iterator();
		while (itLeft.hasNext()) {
			int nextR = itRight.next();
			int nextL = itLeft.next();
			if (!canAnimate(nextL)) {
				continue;
			}
			setSlot(nextL, to.getInventory().getItem(nextR - getCurrentFrame()));
		}
	}

	@Override
	public String getLabel() {
		return "SWIPE_LEFT";
	}

	@Override
	public Class<? extends GuiTransition> getInverse() {
		return TransitionSwipeRight.class;
	}
}
