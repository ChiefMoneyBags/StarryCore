package me.chiefbeef.core.customitem;

import org.bukkit.event.block.Action;

import me.chiefbeef.core.utility.persistence.DataPack;

public enum Interaction {
	
	CLICK,
		SHIFT_RIGHT_CLICK(CLICK),
			SHIFT_RIGHT_CLICK_AIR(SHIFT_RIGHT_CLICK),
			SHIFT_RIGHT_CLICK_BLOCK(SHIFT_RIGHT_CLICK),
			SHIFT_RIGHT_CLICK_ENTITY(SHIFT_RIGHT_CLICK),
		
		RIGHT_CLICK(CLICK),
			RIGHT_CLICK_AIR(RIGHT_CLICK),
			RIGHT_CLICK_BLOCK(RIGHT_CLICK),
			RIGHT_CLICK_ENTITY(RIGHT_CLICK),
		
		SHIFT_LEFT_CLICK(CLICK),
			SHIFT_LEFT_CLICK_AIR(SHIFT_LEFT_CLICK),
			SHIFT_LEFT_CLICK_BLOCK(SHIFT_LEFT_CLICK),
			SHIFT_LEFT_CLICK_ENTITY(SHIFT_LEFT_CLICK),
		
		LEFT_CLICK(CLICK),
			LEFT_CLICK_AIR(LEFT_CLICK),
			LEFT_CLICK_BLOCK(LEFT_CLICK),
			LEFT_CLICK_ENTITY(LEFT_CLICK),
		
	HOTBAR,
		SCROLL(HOTBAR),
			SCROLL_RIGHT(SCROLL),
			SCROLL_LEFT(SCROLL),
		
		SHIFT_SCROLL(HOTBAR),
			SHIFT_SCROLL_RIGHT(SHIFT_SCROLL),
			SHIFT_SCROLL_LEFT(SHIFT_SCROLL),
			
	GUI,
		GUI_CLICK(GUI),
			GUI_PLACE(GUI_CLICK),
			GUI_PICKUP(GUI_CLICK),
		
		GUI_DRAG(GUI),
		
	OTHER,
		PLACE_PHYSICAL(OTHER),
		PICKUP(OTHER),
		DROP(OTHER);
	
	private Interaction parent;
	private DataPack pack;

	private Interaction(Interaction parent) {
		this.parent = parent;
	}

	private Interaction() {
	
	}

	public boolean hasParent() {
		return parent != null;
	}

	public Interaction parent() {
		return parent;
	}

	public Interaction base() {
		Interaction action = this;
		while (action.hasParent()) {
			if (!action.parent().hasParent()) {
				break;
			}
			action = action.parent;
		}
		return action;
	}

	public Interaction category() {
		Interaction action = this;
		while (action.hasParent()) {
			if (!action.parent().hasParent()) {
				return action.parent;
			}
			action = action.parent;
		}
		return action;
	}
	
	public void setDataPack(DataPack pack) {
		this.pack = pack;
	}
	
	public DataPack getDataPack() {
		return pack;
	}
	
	public boolean isStoringData() {
		return pack != null;
	}

	public static Interaction convert(Action action) {
		switch (action) {
		case LEFT_CLICK_AIR:
			return Interaction.LEFT_CLICK_AIR;
		case LEFT_CLICK_BLOCK:
			return Interaction.LEFT_CLICK_BLOCK;
		case RIGHT_CLICK_AIR:
			return Interaction.RIGHT_CLICK_AIR;
		case RIGHT_CLICK_BLOCK:
			return Interaction.RIGHT_CLICK_BLOCK;
		default:
			return null;
		}
	}

	public static Interaction convert(String action) {
		switch (action) {
		case "INTERACT":
			return Interaction.RIGHT_CLICK_ENTITY;
		case "ATTACK":
			return Interaction.LEFT_CLICK_ENTITY;
		case "INTERACT_AT":
			return Interaction.RIGHT_CLICK_AIR;
		default:
			return null;
		}
	}
}
