package me.chiefbeef.core.customitem.assets;

import org.bukkit.inventory.ItemStack;

import me.chiefbeef.core.customitem.CustomItem;
import me.chiefbeef.core.utility.assets.AssetBuildPack;

public class CustomItemBuildPack extends AssetBuildPack<CustomItem> {

	private ItemStack item;
	
	public CustomItemBuildPack(ItemStack item) {
		this.item = item;
	}
	
	public ItemStack getItemStack() {
		return item;
	}

}
