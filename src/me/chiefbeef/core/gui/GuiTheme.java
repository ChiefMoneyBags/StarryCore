package me.chiefbeef.core.gui;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.chiefbeef.core.aesthetic.sound.SoundPack;
import me.chiefbeef.core.aesthetic.sound.interfacing.GuiSoundPack;
import me.chiefbeef.core.aesthetic.sound.interfacing.GuiSoundPack.GuiSound;
import me.chiefbeef.core.compatibility.CompatMaterial;
import me.chiefbeef.core.customitem.Meta;
import me.chiefbeef.core.gui.abstraction.Page;
import me.chiefbeef.core.user.UserCore;
public class GuiTheme {

	private final GuiSession session;
	private GuiColor
		background, toolbar;
	
	public GuiTheme(GuiSession session) {
		this.session = session;
		FileConfiguration data = null; //CoreFiles.getUser(user);
		this.background = GuiColor.valueOf(data.getString("settings.theme.gui.background", "GRAY"));
		this.toolbar = GuiColor.valueOf(data.getString("settings.theme.gui.toolbar", "BLACK"));
	}
	
	public ItemStack get(Page page, int slot) {
		Inventory inv = page.getInventory();
		int size = inv.getSize();
		
		if (slot > size-10) {
			return get(GuiElement.TOOLBAR);
		} else if (page.isPlaceable(slot)) {
			return get(GuiElement.PLACEABLE);
		} else {
			return get(GuiElement.BACKGROUND);
		}
	}
	 
	public ItemStack get(GuiElement element) {
		ItemStack item;
		switch (element) {
		case BACKGROUND:
			item = new ItemStack(background.getMaterial().asMaterial());
			break;
		case TOOLBAR:
			item = new ItemStack(toolbar.getMaterial().asMaterial());
			break;
		case BACK_BUTTON:
			item = new ItemStack(CompatMaterial.BOOK.asMaterial());
			break;
		case NEXT_PAGE:
			item = new ItemStack(CompatMaterial.REDSTONE_TORCH.asMaterial());
			break;
		case PREV_PAGE:
			item = new ItemStack(CompatMaterial.REDSTONE_TORCH.asMaterial());
			break;
		case SELECTED:
			item = new ItemStack(CompatMaterial.LIME_STAINED_GLASS_PANE.asMaterial());
			break;
		case NEUTRAL:
			item = new ItemStack(CompatMaterial.YELLOW_STAINED_GLASS_PANE.asMaterial());
			break;
		case PLACEABLE:
			item = new ItemStack(CompatMaterial.WHITE_STAINED_GLASS_PANE.asMaterial());
			break;
		default:
			item = new ItemStack(CompatMaterial.STONE.asMaterial());
			break;
		}
		Meta.put(item, "guiBaseTile", true);
		return item;
	}
	
	public GuiSoundPack getSounds() {
		return (GuiSoundPack) SoundPack.get("BASE_GUI");
	}
	
	public void playSound(GuiSound sound) {
		((GuiSoundPack) SoundPack.get("BASE_GUI")).play(sound, session.getUser());
	}
	
	public static enum GuiColor {
		BLACK(
				CompatMaterial.BLACK_STAINED_GLASS_PANE),
		BLUE(
				CompatMaterial.BLUE_STAINED_GLASS_PANE),
		BROWN(
				CompatMaterial.BROWN_STAINED_GLASS_PANE),
		CYAN(
				CompatMaterial.CYAN_STAINED_GLASS_PANE),
		GRAY(
				CompatMaterial.GRAY_STAINED_GLASS_PANE),
		GREEN(
				CompatMaterial.GREEN_STAINED_GLASS_PANE),
		LIGHT_BLUE(
				CompatMaterial.LIGHT_BLUE_STAINED_GLASS_PANE),
		LIGHT_GRAY(
				CompatMaterial.LIGHT_GRAY_STAINED_GLASS_PANE),
		LIME(
				CompatMaterial.LIME_STAINED_GLASS_PANE),
		MAGENTA(
				CompatMaterial.MAGENTA_STAINED_GLASS_PANE),
		ORANGE(
				CompatMaterial.ORANGE_STAINED_GLASS_PANE),
		PINK(
				CompatMaterial.PINK_STAINED_GLASS_PANE),
		PURPLE(
				CompatMaterial.PURPLE_STAINED_GLASS_PANE),
		RED(
				CompatMaterial.RED_STAINED_GLASS_PANE),
		WHITE(
				CompatMaterial.WHITE_STAINED_GLASS_PANE),
		YELLOW(
				CompatMaterial.YELLOW_STAINED_GLASS_PANE);
		
		private CompatMaterial mat;
		
		private GuiColor(CompatMaterial mat) {
			this.mat = mat;
		}
		
		public CompatMaterial getMaterial() {
			return mat;
		}
	}
	
	public static enum GuiElement {
		BACK_BUTTON,
		NEXT_PAGE,
		PREV_PAGE,
		BACKGROUND,
		TOOLBAR,
		SELECTED,
		NEUTRAL,
		PLACEABLE;
	}
}
