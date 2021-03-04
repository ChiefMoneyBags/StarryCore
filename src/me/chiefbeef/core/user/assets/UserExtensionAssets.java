package me.chiefbeef.core.user.assets;

import me.chiefbeef.core.user.UserExtension;
import me.chiefbeef.core.utility.assets.TypeAssets;

public class UserExtensionAssets extends TypeAssets<UserExtension> {

	public UserExtensionAssets(Class<? extends UserExtension> type, String label) {
		super(type, label, label);
	}

}
