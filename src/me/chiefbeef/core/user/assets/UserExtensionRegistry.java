package me.chiefbeef.core.user.assets;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import me.chiefbeef.core.StarryCore;
import me.chiefbeef.core.user.UserCore;
import me.chiefbeef.core.user.UserExtension;
import me.chiefbeef.core.utility.Console;
import me.chiefbeef.core.utility.assets.AssetRegistry;
import me.chiefbeef.core.utility.assets.TypeAssets;

public class UserExtensionRegistry extends AssetRegistry<UserExtension> {

	@Override
	public boolean testClass(Class<? extends UserExtension> type) {
		try {type.getConstructor(Player.class);} catch (NoSuchMethodException e) {
			Console.generateException("Any object registering as a user extension requires a constructor have one parameter, (Player)");
			return false;
		}
		return true;
	}

	@Override
	public TypeAssets<UserExtension> loadAssets(Plugin plugin, Class<? extends UserExtension> type) {
		
		return null;
	}
	
	@Override
	public void register(TypeAssets<UserExtension> assets) {
		super.register(assets);
		for (UserCore user: StarryCore.getInstance().getUserManager().getUsers()) {
			user.loadExtension(assets.getType());
		}
	}

}
