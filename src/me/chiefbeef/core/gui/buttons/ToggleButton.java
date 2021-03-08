package me.chiefbeef.core.gui.buttons;

import org.bukkit.inventory.ItemStack;

import me.chiefbeef.core.gui.GuiSession;
import me.chiefbeef.core.gui.abstraction.Page;
import me.chiefbeef.core.utility.ConsumerPair;
import me.chiefbeef.core.utility.PredicatePair;

/**
 * {@link ToggleButton} is a {@link GuiButton} type that allows conditional functions to be applied to a {@link Page} slot dynamically
 * without needing to create new {@link Page} children objects or hard coded buttons inside the {@link Page#onClick(org.bukkit.event.inventory.InventoryClickEvent)}
 * 
 * Registered functions are invoked on the objects within the supplied {@link ConsumerPair}(s) when a user
 * clicks on the gui slot. The chosen set of functions to invoke is dependent on the outcome of the given {@link PredicatePair}.
 * 
 * In addition the {@link ToggleButton} has the ability to automatically change the {@link GuiButton} display item in accordance with the
 * outcome of the {@link PredicatePair}.
 * 
 * Useful to create toggle on/off buttons
 * @author Kevin
 */
public class ToggleButton extends GuiButton {
	
	private ItemStack
		itemTrue,
		itemFalse;
	
	private PredicatePair<?> condition;
	
	private ConsumerPair<?>
		ifTrue,
		ifFalse;
	
	public ToggleButton(Page page, PredicatePair<?> condition, ConsumerPair<?> ifTrue, ItemStack itemTrue, ConsumerPair<?> ifFalse, ItemStack itemFalse) {
		super(page, null);
		this.condition = condition;
		this.ifTrue = ifTrue;
		this.ifFalse = ifFalse;
		this.itemTrue = itemTrue;
		this.itemFalse = itemFalse;
		initialCompare();
	}
	
	private void initialCompare() {
		super.setInitialItem(condition.test() ? itemTrue : itemFalse);
	}

	@Override
	public void invoke(GuiSession session) {
		if (condition.test()) {
			ifTrue.accept();
			setItem(itemTrue);
		} else {
			ifFalse.accept();
			setItem(itemFalse);
		}
	}
	
	

}
