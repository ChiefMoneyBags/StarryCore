package me.chiefbeef.core.gui.transition.swipe;

import java.util.Iterator;

import me.chiefbeef.core.utility.gui.Pages;

public class TransitionSwipeRight extends SwipeTransition {
	
	@Override
	public void nextFrame(int frame) {		
		//move the inventory 1 space right, ignore right most column.
		for (int slot = inv.getSize()-1; slot > 0; slot--) {
			if (Pages.isRightEdge(slot) || (!canAnimate(slot))) {
				continue;
			}
			setSlot(slot+1, inv.getItem(slot));
		}
		
		//move the next column of the animation into the inventory
		Iterator<Integer> itRight = Pages.getRightEdge().iterator();
		Iterator<Integer> itLeft = Pages.getLeftEdge().iterator();
		while (itLeft.hasNext()) {
			int nextR = itRight.next();
			int nextL = itLeft.next();
			if (!canAnimate(nextR)) {
				continue;
			}
			setSlot(nextL, to.getInventory().getItem(nextR-getCurrentFrame()));
		}
	}

	@Override
	public String getLabel() {
		return "SWIPE_RIGHT";
	}
}
