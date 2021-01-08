package me.chiefbeef.core.utility.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.chiefbeef.core.customitem.Meta;
import me.chiefbeef.core.gui.GuiTheme.GuiElement;
import me.chiefbeef.core.user.UserCore;
import me.chiefbeef.core.utility.Console;

public class Pages {
	
	private static final List<Integer>
			left 			= Arrays.asList( 0,  9,  18, 27, 36, 45),
			right 			= Arrays.asList(  8,  17, 26, 35, 44, 53),
			top				= Arrays.asList(  0,  1,  2,  3,  4,  5,  6,  7,  8),
			
			leftSide 		= Arrays.asList(  0,  1,  2,  3,
								9,  10, 11, 12,
								18, 19, 20, 21,
								27, 28, 29, 30,
								36, 37, 38, 39,
								45, 46, 47, 48),
			
			center			= Arrays.asList(  4,  13, 22, 31, 40, 49),
			
			rightSide		= Arrays.asList(  5,  6,  7,  8,
								14, 15, 16, 17,
								23, 24, 25, 26,
								32, 33, 34, 35,
								41, 42, 43, 44,
								50, 51, 52, 53),
	
			leftSubtract	= Arrays.asList(  19, 10, 1 ),
			rightAdd 		= Arrays.asList(  1,  10, 19);
	
	/**
	 * This method will return the slots surrounding any given slot in a gui without screen wrapping.
	 * @param rows
	 * @param slot
	 * @return
	 */
	public static int[] surroundingSlots(int rows, int slot, boolean includeToolbar) {
		if (!includeToolbar) {
			rows--;
		}
		if (slot > (rows*9)-1) {
			Console.generateException("A slot was given that exceeds the bounds of the GUI: slot=" + slot + " gui-rows=" + rows);
			return new int[] {};
		}
		
		List<Integer> surround = new ArrayList<>();
		
		
		// 
		if (!isLeftEdge(slot)) {
			int base = slot+9;
			for (int sub: leftSubtract) {
				if ((base-sub > -1) && (base-sub < rows*9)) {
					surround.add(base-sub);
				}
			}
		}
		if (!isRightEdge(slot)) {
			int base = slot-9;
			for (int add: rightAdd) {
				if ((base+add < rows*9) && (base+add > -1)) {
					surround.add(base+add);
				}
			}
		}
		
		if (slot-9 > -1) surround.add(slot-9);
		if (slot+9 < rows*9) surround.add(slot+9);
		
		// convert to int array
		int size = surround.size();
		int[] a = new int[size];
		Integer[] b = surround.toArray(new Integer[size]);
		for (int i = 0; i < size; i++) {
			a[i] = b[i];
		}
		return a;
	}
	
	public static List<Integer> getTopEdge() {
		return top;
	}

	public static boolean isTopEdge(int slot) {
		return slot >= 0 && slot <= 8;
	}
	
	public static List<Integer> getLeftEdge() {
		return left;
	}
	
	public static boolean isLeftEdge(int slot) {
		for (int i: left) 
			if (i == slot) return true;
		return false;
	}
	
	public static boolean isLeftSide(int slot) {
		for (int i: leftSide) 
			if (i == slot) return true;
		return false;
	}
	
	public static List<Integer> getRightEdge() {
		return right;
	}
	
	public static boolean isRightEdge(int slot) {
		for (int i: right) 
			if (i == slot)
				return true;
		return false;
	}
	
	public static boolean isRightSide(int slot) {
		for (int i: rightSide)
			if (i == slot) 
				return true;
		return false;
	}
	
	public static List<Integer> getCenter() {
		return center;
	}
	
	public static boolean isCenter(int slot) {
		for (int i: center) 
			if (i == slot)
				return true;
		return false;
	}
	
	public static boolean isToolbar(Inventory inv, int slot) {
		return slot > inv.getSize() - 10;
	}
	
	public static void fillBackground(Inventory inv, UserCore user) {
		int size = inv.getSize();
		for (int slot = 0; slot < size; slot++) {
			if (slot >= size-9) {
				inv.setItem(slot, user.getGuiSession().getTheme().get(GuiElement.TOOLBAR));
			} else {
				inv.setItem(slot, user.getGuiSession().getTheme().get(GuiElement.BACKGROUND));
			}
		}
	}
	
	
	public static List<Integer> openSlotsFor(int rows) {
		List<Integer> slots = new ArrayList<>();
		if (rows > 0) 
			slots.addAll(Arrays.asList(10, 11, 12, 13, 14, 15, 16));
		if (rows > 1) 
			slots.addAll(Arrays.asList(19, 20, 21, 22, 23, 24, 25));
		if (rows > 2) 
			slots.addAll(Arrays.asList(28, 29, 30, 31, 32, 33, 34));
		return slots;
	}
	
	public static boolean isPresent(Inventory inv, int slot) {
		return slot < inv.getSize() && slot > -1;
	}
	
	public static boolean isBaseTile(ItemStack item) {
		return item != null && item.hasItemMeta() && Meta.getOrDefault(item.getItemMeta(), "guiBaseTile", false);
	}
	
	public static Inventory copy(Inventory inv) {
		Inventory copy = Bukkit.createInventory(inv.getHolder(), inv.getSize());
		copy.setContents(inv.getContents());
		return copy;
	}
	
}
