package me.chiefbeef.core.gui.buttons;

import java.util.Arrays;
import java.util.List;
import org.bukkit.inventory.ItemStack;

import me.chiefbeef.core.gui.GuiSession;
import me.chiefbeef.core.gui.abstraction.Page;
import me.chiefbeef.core.utility.ConsumerPair;

/**
 * {@link FunctionButton} is a {@link GuiButton} type that allows functions to be applied to a {@link Page} slot dynamically
 * without needing to create new {@link Page} children objects or hard coded buttons inside {@link Page#onClick(org.bukkit.event.inventory.InventoryClickEvent)}.
 * 
 * Registered functions are invoked on the objects within the supplied {@link ConsumerPair}(s) when a user
 * clicks on the gui slot.  
 * @author Kevin
 */
public class FunctionButton extends GuiButton {

	private List<ConsumerPair<?>> functionPairs;
	
	public FunctionButton(Page page, ItemStack item, List<ConsumerPair<?>> functionPairs) {
		super(page, item);
		this.functionPairs = functionPairs;
	}
	
	public FunctionButton(Page page, ItemStack item, ConsumerPair<?> functionPair) {
		super(page, item);
		this.functionPairs = Arrays.asList(functionPair);
	}
	
	@Override
	public void invoke(GuiSession session) {
		for (ConsumerPair<?> functionPair: functionPairs) {
			functionPair.accept();
		}
	}
}
