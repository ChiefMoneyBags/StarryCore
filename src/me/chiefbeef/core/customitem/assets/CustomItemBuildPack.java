package me.chiefbeef.core.customitem.assets;

import org.bukkit.inventory.ItemStack;

import me.chiefbeef.core.utility.assets.AssetBuildPack;

public class CustomItemBuildPack implements AssetBuildPack {

	private ItemStack item;
	
	public CustomItemBuildPack(ItemStack item) {
		this.item = item;
	}
	
	public ItemStack getItemStack() {
		return item;
	}

}
