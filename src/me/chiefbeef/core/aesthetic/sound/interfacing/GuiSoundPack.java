package me.chiefbeef.core.aesthetic.sound.interfacing;

import me.chiefbeef.core.user.UserCore;

public interface GuiSoundPack {

	public abstract void play(GuiSound sound, UserCore user);
	
	public abstract void stop(GuiSound sound, UserCore user);
	
	public static enum GuiSound {
		AMBIENT,
		ANIM_SCAN,
		ANIM_SELECT_1,
		ANIM_SELECT_2,
		ANIM_SWIPE,
	}
}
