package me.chiefbeef.core.gui.transition.scan;

import me.chiefbeef.core.utility.gui.Pages;


public class TransitionScanLeft extends ScanTransition {

	
	@Override
	public void nextFrame(int frame) {
		for (int slot: Pages.getRightEdge()) {
			if (slot > inv.getSize()-1) {
				break;
			}
			if (canAnimate((slot-frame)+1)) {
				setSlot((slot-frame)+1, to.getInventory().getItem((slot-frame)+1));	
			}
			if (frame < 9 && canAnimate(slot-frame)) {
				setSlot(slot-frame, getScanLine());
			}
		}
	}

	@Override
	public String getLabel() {
		return "SCAN_LEFT";
	}
	
}
