package me.chiefbeef.core.gui.transition.variant.scan;

import me.chiefbeef.core.utility.gui.Pages;


public class TransitionScanLeft extends ScanTransition {

	
	@Override
	public void nextFrame(int frame) {
		for (int slot: Pages.getRightEdge()) {			
			if (canAnimate(slot-frame)) {
				setSlot(slot-frame, getScanLine());
			}
			
			if (frame > 0 && canAnimate(slot - (frame - 1))) {
				setSlot((slot - (frame - 1)), to.getInventory().getItem((slot - (frame - 1))));	
			}
		}
	}

	@Override
	public String getLabel() {
		return "SCAN_LEFT";
	}
	
}
