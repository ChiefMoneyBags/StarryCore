package me.chiefbeef.core.customitem;

import java.lang.reflect.Field;
import java.util.UUID;

import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.libs.org.apache.commons.codec.binary.Base64;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import me.chiefbeef.core.StarryCore;
import me.chiefbeef.core.compatibility.CompatMaterial;

public class Meta {

	public static ItemStack getHead(String url) {
		ItemStack head = new ItemStack(CompatMaterial.PLAYER_HEAD.asMaterial());
		SkullMeta meta = (SkullMeta) head.getItemMeta();
		setHeadTexture(meta, url);
		head.setItemMeta(meta);
		return head;
	}
	
	public static void setHeadTexture(SkullMeta headMeta, String url) {
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try {
            profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
            e1.printStackTrace();
        }
	}
	
	public static String[] getOrDefault(Object obj, String key, String split, String[] value) {
		String got = get(obj, key);
		if (got != null) return got.split(split);
		else return value;
	}
	
	public static String getOrDefault(Object obj, String key, String value) {
		String got = get(obj, key);
		if (got != null) return got;
		else return value;
	}
	
	public static int getOrDefault(Object obj, String key, int value) {
		String got = get(obj, key);
		if (got != null) try {
							return Integer.parseInt(got);
						} catch (NumberFormatException e) {
							return value;
		}
		return value;
	}
	
	public static double getOrDefault(Object obj, String key, double value) {
		String got = get(obj, key);
		if (got != null) try {
							return Double.parseDouble(got);
						} catch (NumberFormatException e) {
							return value;
		}
		return value;
	}
	
	public static float getOrDefault(Object obj, String key, float value) {
		String got = get(obj, key);
		if (got != null) try {
							return Float.parseFloat(got);
						} catch (NumberFormatException e) {
							return value;
		}
		return value;
	}
	
	public static boolean getOrDefault(Object obj, String key, boolean value) {
		String got = get(obj, key);
		if (got != null && (got.equals("true") || got.equals("false"))) {
			return Boolean.parseBoolean(got);
		}
		return value;
	}
	
	/**
	 * Add a custom tag to an object. Any instance of PersistentDataHolder is acceptable.
	 * 
	 * @param object the PersistentDataHolder
	 * @param key The Key for the custom value
	 * @param value The value.
	 */
	public static void put(Object object, String key, Object value) {
		String val = String.valueOf(value);
		if ((value == null) || (object == null)) {
			return;
		}
		ItemStack item = null;
		if (!(object instanceof PersistentDataHolder)) {
			if (object instanceof ItemStack) {
				item = (ItemStack)object;
				object = ((ItemStack)object).getItemMeta();
			} else {
				return;	
			}
		}
		NamespacedKey nk = new NamespacedKey(StarryCore.getInstance(), key);
		PersistentDataContainer container = ((PersistentDataHolder) object).getPersistentDataContainer();
		container.set(nk, PersistentDataType.STRING, val);
		if (item != null) {
			item.setItemMeta((ItemMeta)object);
		}
	}
	
	
	/**
	 * Get a custom tag from an object. Any instance of PersistentDataHolder is acceptable.
	 * 
	 * @param object the PersistentDataHolder
	 * @param key The Key for the custom value
	 */
	public static String get(Object object, String key) {
		if (object == null) {
			return null;
		}
		if (!(object instanceof PersistentDataHolder)) {
			if (object instanceof ItemStack) {
				object = ((ItemStack)object).getItemMeta();
			} else {
				return null;
			}
		}
		NamespacedKey nk = new NamespacedKey(StarryCore.getInstance(), key);
		PersistentDataContainer container = ((PersistentDataHolder) object).getPersistentDataContainer();
		if (container.has(nk, PersistentDataType.STRING)) {
			return container.get(nk, PersistentDataType.STRING);
		}
		return null;
	}
	
	/**
	 * See if an object has a custom tag. Any instance of PersistentDataHolder is acceptable.
	 * 
	 * @param object the PersistentDataHolder
	 * @param key The Key for the custom value
	 * @param value The value.
	 */
	public static boolean has(Object object, String key) {
		if (object == null) {
			return false;
		}
		if (!(object instanceof PersistentDataHolder)) {
			if (object instanceof ItemStack) {
				object = ((ItemStack)object).getItemMeta();
			} else {
				return false;	
			}
		}
		NamespacedKey nk = new NamespacedKey(StarryCore.getInstance(), key);
		PersistentDataContainer container = ((PersistentDataHolder) object).getPersistentDataContainer();
		if (container.has(nk, PersistentDataType.STRING)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Remove a custom tag to an object. Any instance of PersistentDataHolder is acceptable.
	 * 
	 * @param object the PersistentDataHolder
	 * @param key The Key for the custom value
	 */
	public static void remove(Object object, String key) {
		if (object == null) {
			return;
		}
		if (!(object instanceof PersistentDataHolder)) {
			if (object instanceof ItemStack) {
				object = ((ItemStack)object).getItemMeta();
			} else {
				return;	
			}
		}
		NamespacedKey nk = new NamespacedKey(StarryCore.getInstance(), key);
		PersistentDataContainer container = ((PersistentDataHolder) object).getPersistentDataContainer();
		if (container.has(nk, PersistentDataType.STRING)) {
			container.remove(nk);
		}
	}
}
