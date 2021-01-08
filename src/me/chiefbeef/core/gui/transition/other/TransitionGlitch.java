package me.chiefbeef.core.gui.transition.other;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import io.netty.util.internal.ThreadLocalRandom;
import me.chiefbeef.core.compatibility.CompatSound;
import me.chiefbeef.core.gui.transition.GuiTransition;

public class TransitionGlitch extends GuiTransition {
	
	List<Integer>
	toAnimate = new ArrayList<>(), // slots that are not finished animating
	glitching = new ArrayList<>(), // slots that are glitching (turning green)
	toFinish = new ArrayList<>(); // slots that are in the final step (turn to actual inv)
	
	@Override
	public void onStart() {
		for (int i = 0; i < inv.getSize(); i++) {
			if (!canAnimate(i)) {
				continue;
			}
			toAnimate.add(i);
		}
		CompatSound.ENTITY_BEE_POLLINATE.play(session.getUser(), 2f, -2f);
	}
	
	@Override
	public boolean hasNextFrame() {
		return toAnimate.size() > 0;
	}
	
	@Override
	public void nextFrame(int frame) {
		// Choose 6 random slots from the list of animated slots and progress their animation progress.
		for (int i = 0; i < 6 && toAnimate.size() > 0; i++) {
			int slot = toAnimate.get(ThreadLocalRandom.current().nextInt(toAnimate.size()));
			
			if (glitching.contains(slot)) {
				setSlot(slot, new ItemStack(Material.GREEN_STAINED_GLASS_PANE));
				glitching.remove(slot);
				toFinish.add(slot);
				
			} else if (toFinish.contains(slot)) {
				setSlot(slot, to.getInventory().getItem(slot));
				toAnimate.remove(slot);
				toFinish.remove(slot);
				
			} else {
				setSlot(slot, new ItemStack(Material.LIME_STAINED_GLASS_PANE));
				glitching.add(slot);
			}
		}	
	}


	@Override
	public String getLabel() {
		return "GLITCH";
	}

}
