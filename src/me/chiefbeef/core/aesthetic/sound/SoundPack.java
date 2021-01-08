package me.chiefbeef.core.aesthetic.sound;

import java.util.HashMap;
import java.util.Map;

import me.chiefbeef.core.aesthetic.sound.packs.gui.BaseGuiPack;

public abstract class SoundPack {

	private static Map<String, SoundPack> packs = new HashMap<>();
	
	static {
		packs.put("BASE_GUI", new BaseGuiPack());
	}
	
	public static SoundPack get(String label) {
		return packs.get(label);
	}
	
	public abstract String getLabel();

}
