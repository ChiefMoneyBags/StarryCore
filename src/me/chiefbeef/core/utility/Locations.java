package me.chiefbeef.core.utility;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.util.BoundingBox;

public class Locations {
	/**
	 * Saves a location to string for saving in data
	 * @param loc Location being serialized
	 * @param detailed Whether to include pitch and yaw in the save
	 * @return Serialized Location world~x~y~z
	 */
	public static String toString(Location loc, boolean detailed) {
		try {
			return loc.getWorld().getName() + "~" + (detailed ? loc.getX() : loc.getBlockX()) + "~" + (detailed ? loc.getY() : loc.getBlockY()) + "~" + (detailed ? loc.getZ() : loc.getBlockZ()) + (detailed ? ("~" + loc.getYaw() + "~" + loc.getPitch()) : "");
		} catch (Exception e) {
			Console.generateException("Bad locToString " + (loc != null ? loc : "UNKNOWN") + ", returning BAD_LOCATION!");
			return "BAD_LOCATION";
		}
	}
	
	/**
	 * Loads a serialized location from a string
	 * @param loc Location being loaded
	 * @param detailed Whether to attempt to load pitch and yaw from the location as well
	 * @return Deserialized Location
	 */
	public static Location fromString(String loc) {
		try {
			final String[] s = loc.split("~");
			return new Location(Bukkit.getWorld(s[0]), Double.valueOf(s[1]), Double.valueOf(s[2]), Double.valueOf(s[3]), (s.length > 4 ? Float.valueOf(s[4]) : 0F), (s.length > 5 ? Float.valueOf(s[5]) : 0F));
		} catch (Exception e) {
			Console.generateException("Bad locFromString for String " + (loc != null ? loc : "UNKNOWN") + ", returning null!");
			return null;
		}
	}
	
	public static boolean isWithin(Location check, Location pointA, Location pointB) {
		return BoundingBox.of(pointA, pointB).contains(check.toVector())
				&& check.getWorld().equals(pointA.getWorld())
				&& check.getWorld().equals(pointB.getWorld());
	}
}
