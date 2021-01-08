package me.chiefbeef.core.command.assets;

import org.bukkit.plugin.Plugin;

import me.chiefbeef.core.command.CoreCommand;
import me.chiefbeef.core.utility.assets.AssetRegistry;
import me.chiefbeef.core.utility.assets.TypeAssets;

public class CoreCommandRegistry extends AssetRegistry<CoreCommand> {

	@Override
	public boolean testClass(Class<? extends CoreCommand> type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TypeAssets<CoreCommand> loadAssets(Plugin plugin, Class<? extends CoreCommand> type) {
		// TODO Auto-generated method stub
		return null;
	}

}
