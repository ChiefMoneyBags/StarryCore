package me.chiefbeef.core.gui.transition.variant.other;

import me.chiefbeef.core.gui.transition.GuiTransition;
import me.chiefbeef.core.gui.transition.variant.swipe.TransitionSwipeRight;

public class TransitionInstant extends GuiTransition {

	@Override
	public void nextFrame(int frame) {
		return;
	}

	@Override
	public boolean hasNextFrame() {
		return false;
	}

	@Override
	public String getLabel() {
		return "TRANSITION_INSTANT";
	}
	
	@Override
	public Class<? extends GuiTransition> getInverse() {
		return this.getClass();
	}

}
