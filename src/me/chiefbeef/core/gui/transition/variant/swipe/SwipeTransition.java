package me.chiefbeef.core.gui.transition.variant.swipe;

import me.chiefbeef.core.compatibility.CompatSound;
import me.chiefbeef.core.gui.transition.GuiTransition;
import me.chiefbeef.core.user.UserCore;

public abstract class SwipeTransition extends GuiTransition {

	@Override
	protected void onStart() {
		UserCore user = session.getUser();
		CompatSound.ENTITY_WITCH_THROW.play(user, user.getLocation(), 1f, -2f);
	}
	
	@Override
	public boolean hasNextFrame() {
		return getCurrentFrame() <= 8;
	}


}
