package me.chiefbeef.core.utility;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

public class Chat {

	public static String cc(String m) {
		return ChatColor.translateAlternateColorCodes('&', (m != null ? m : "&cThis message is broken! :("));
	}
	
	public static List<String> ccList(List<String> m) {
		List<String> s = new ArrayList<String>();
		if (m == null) {
			m = new ArrayList<>();
		}
		for (String line: m) {
			s.add(cc(line));
		}
		return s;
	}
	
	public static String format(double d) {
		return new DecimalFormat("##.##").format(d);
	}

}
