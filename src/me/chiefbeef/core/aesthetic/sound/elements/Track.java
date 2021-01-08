package me.chiefbeef.core.aesthetic.sound.elements;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class Track {
	
	
	private Map<String, TrackTheme> themes = new HashMap<>();
	
	public Track(FileConfiguration config) {
		ConfigurationSection csThemes = config.getConfigurationSection("themes");
		if (csThemes != null) {
			for (String theme: csThemes.getKeys(false)) {
				
			}
		}
	}
	
	

}
