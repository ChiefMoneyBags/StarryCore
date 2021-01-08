package me.chiefbeef.core.gui.history;

import me.chiefbeef.core.gui.abstraction.Page;
import me.chiefbeef.core.gui.transition.assets.GuiTransitionAssets;

public class HistoryLevel {
	private Page page;
	private GuiTransitionAssets transitionAssets;
	
	public HistoryLevel(Page page, GuiTransitionAssets transitionAssets) {
		this.page = page;
		this.transitionAssets = transitionAssets;
	}
	
	/**
	 * @return The page
	 */
	public Page getPage() {
		return page;
	}
	
	/**
	 * @return The transition that got us to the page
	 */
	public GuiTransitionAssets getTransition() {
		return transitionAssets;
	}
}