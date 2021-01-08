package me.chiefbeef.core.utility.netserver;

import org.bukkit.Bukkit;

import me.chiefbeef.core.user.UserCore;

public class Reflect {
	
	private static String version = null;
	
	public static Class<?> starryClass(String s) {
		try {
			return Class.forName(s);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Class<?> nmsClass(String s) {
		try {
			return Class.forName("net.minecraft.server." + nmsVersion() + "." + s);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Class<?> craftClass(String s) {
		try {
			return Class.forName("org.bukkit.craftbukkit." + nmsVersion() + "." + s);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Object craftPlayer(UserCore user) {
		try {
			return user.getPlayer().getClass().getMethod("getHandle").invoke(user.getPlayer());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String nmsVersion() {
		if (version == null) {
			version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
		} 
		return version;
	}
	
	public static Object field(Object instance, String field) {
		try {
			return instance.getClass().getField(field).get(instance);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
