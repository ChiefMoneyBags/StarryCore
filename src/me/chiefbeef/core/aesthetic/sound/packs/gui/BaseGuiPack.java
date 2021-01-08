package me.chiefbeef.core.aesthetic.sound.packs.gui;

import me.chiefbeef.core.aesthetic.sound.SoundPack;
import me.chiefbeef.core.aesthetic.sound.elements.Track;
import me.chiefbeef.core.aesthetic.sound.interfacing.GuiSoundPack;
import me.chiefbeef.core.compatibility.CompatSound;
import me.chiefbeef.core.user.UserCore;
import me.chiefbeef.core.utility.files.CoreFiles;

public class BaseGuiPack extends SoundPack implements GuiSoundPack {

	@Override
	public void play(GuiSound sound, UserCore user) {
		switch (sound) {
		case AMBIENT:
			new Track(CoreFiles.loadYaml("test_song"));
			break;
		case ANIM_SCAN:
			CompatSound.ENTITY_BEE_POLLINATE.play(user, 2f, -2f);
			break;
		case ANIM_SELECT_1:
			CompatSound.ENTITY_WITCH_THROW.play(user, 1, 0);
			break;
		case ANIM_SELECT_2:
			CompatSound.BLOCK_END_PORTAL_FRAME_FILL.play(user, 1, 0);
			break;
		case ANIM_SWIPE:
			break;
		default:
			break;

		
		}
		
	}

	@Override
	public void stop(GuiSound sound, UserCore user) {
		
	}

	@Override
	public String getLabel() {
		return "BASE_GUI_PACK";
	}

}
