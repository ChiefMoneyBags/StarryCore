package me.chiefbeef.core.command.assets;

import java.util.List;

import me.chiefbeef.core.command.CoreSubCommand;
import me.chiefbeef.core.utility.assets.TypeAssets;

public class CoreSubCommandAssets extends TypeAssets<CoreSubCommand> {

	private List<String> alias;
	
	public CoreSubCommandAssets(Class<? extends CoreSubCommand> type, String label, List<String> alias) {
		super(type, label);
		this.alias = alias;
	}
	
	public List<String> getAliases() {
		return alias;
	}

}
