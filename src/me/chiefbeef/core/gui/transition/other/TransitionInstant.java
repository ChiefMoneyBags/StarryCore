package me.chiefbeef.core.gui.transition.other;

import me.chiefbeef.core.gui.transition.GuiTransition;

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

}
