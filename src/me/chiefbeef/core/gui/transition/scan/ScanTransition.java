package me.chiefbeef.core.gui.transition.scan;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.chiefbeef.core.aesthetic.sound.interfacing.GuiSoundPack.GuiSound;
import me.chiefbeef.core.gui.transition.GuiTransition;

public abstract class ScanTransition extends GuiTransition {

	private ItemStack scanLine = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
	
	public ItemStack getScanLine() {
		return scanLine;
	}
	
	@Override
	public void onStart() {
		session.getTheme().playSound(GuiSound.ANIM_SCAN);
	}
	
	@Override
	public boolean hasNextFrame() {
		return getCurrentFrame() <= 9;
	}

}
