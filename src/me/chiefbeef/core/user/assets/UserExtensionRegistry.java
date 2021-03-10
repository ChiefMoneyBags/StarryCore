package me.chiefbeef.core.user.assets;

import me.chiefbeef.core.StarryCore;
import me.chiefbeef.core.user.UserCore;
import me.chiefbeef.core.user.UserExtension;
import me.chiefbeef.core.utility.assets.AssetRegistry;
import me.chiefbeef.core.utility.assets.TypeAssets;

public class UserExtensionRegistry extends AssetRegistry<UserExtension> {
	
	@Override
	public void register(TypeAssets<UserExtension> assets) {
		super.register(assets);
		for (UserCore user: StarryCore.getInstance().getUserManager().getUsers()) {
			user.loadExtension(assets.getType());
		}
	}

}
