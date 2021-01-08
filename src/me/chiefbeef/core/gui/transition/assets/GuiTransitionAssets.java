package me.chiefbeef.core.gui.transition.assets;

import me.chiefbeef.core.gui.transition.GuiTransition;
import me.chiefbeef.core.utility.assets.TypeAssets;

public class GuiTransitionAssets extends TypeAssets<GuiTransition> {

	private Class<? extends GuiTransition> inverse;

	public GuiTransitionAssets(Class<? extends GuiTransition> clazz, String label) {
		super(clazz, label);
		this.inverse = clazz;
	}
	
	public GuiTransitionAssets(Class<? extends GuiTransition> clazz, Class<? extends GuiTransition> inverse, String label) {
		super(clazz, label);
		this.inverse = inverse;
	}
	
	public Class<? extends GuiTransition> getInverse() {
		return inverse;
	}

}
