package me.chiefbeef.core.gui.transition;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import me.chiefbeef.core.gui.abstraction.CoreGuiHandler;
import me.chiefbeef.core.gui.abstraction.DynamicPage;
import me.chiefbeef.core.gui.abstraction.Page;
import me.chiefbeef.core.gui.abstraction.StaticPage;
import me.chiefbeef.core.gui.cookies.SelectedItem;
import me.chiefbeef.core.gui.transition.assets.GuiTransitionAssets;
import me.chiefbeef.core.utility.assets.AssetBuildPack;
import me.chiefbeef.core.utility.assets.AssetHolder;
import me.chiefbeef.core.utility.assets.AssetRegistry;
import me.chiefbeef.core.utility.gui.Pages;

public abstract class GuiTransition extends CoreGuiHandler implements AssetHolder<GuiTransition>, Runnable {
	
	protected Page from, to;
	// protected BukkitTask animation;
	private int size, frame, taskId;
	private boolean selectedItem, complete;
	
	private static AssetRegistry<GuiTransition> registry = new AssetRegistry<>();
	
	public static AssetRegistry<GuiTransition> getRegistry() {
		return registry;
	}
	
	public GuiTransition setPageTo(Page to) {
		this.to = to;
		return this;
	}
	
	public GuiTransition setPageFrom(Page from) {
		this.from = from;
		return this;
	}
	
	@Override
	public GuiTransition build() {
		setGuiSession(to.getGuiSession());
		setInventory(from == null ? Bukkit.createInventory(null, 9)
				: (from == to && (from instanceof DynamicPage) && ((DynamicPage) from).hasPreviousInventory())
				? ((DynamicPage) from).getPreviousInventory()
				: from.getInventory());
		this.size = to.getInventory().getSize();
		this.selectedItem = session.getCookies().hasCookie(SelectedItem.class);
		return this;
	}
	
	@Override
	public GuiTransition build(AssetBuildPack pack) {
		return null;
	}

	public final void start() {
		taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(getGuiSession().getUser().getManager().getStarry(), this, 0, 1);
		this.onStart();
	}

	@Override
	public void run() {
		// resize inventory to new size
		if (resizeNeeded()) {
			resize();
			return;
		}
		
		if (!hasNextFrame()) {
			complete();
			return;
		}
		
		nextFrame(getCurrentFrame());
		frame++;
	}

	@Override
	public GuiTransitionAssets getAssets() {
		return (GuiTransitionAssets) getRegistry().getAssets(this.getClass());
	}

	@Override
	public GuiTransitionAssets createAssets() {
		return new GuiTransitionAssets(this.getClass(), this.getLabel());
	}
	
	@Override
	public boolean hasConfig() {
		return false;
	}

	/**
	 * Get the {@link Page} of origin for this transition.
	 * 
	 * @return The {@link Page} being transitioned out of.
	 */
	public Page getPageFrom() {
		return from;
	}

	/**
	 * Get the destination {@link Page} of this transition.
	 * 
	 * @return The {@link Page} being transitioned into.
	 */
	public Page getPageTo() {
		return to;
	}

	/**
	 * Get whether this transition is complete, some transitions may take time if
	 * they are animated.
	 * 
	 * @return true if this transition is completely finished.
	 */
	public boolean isComplete() {
		return complete;
	}

	/**
	 * Simply cancel the tasks involved with creating the animation in the gui.
	 * Please note that this only stops the animation, as such you will need to
	 * either manually complete or cancel the transition, otherwise it will never
	 * finish.
	 */
	public void endAnimation() {
		Bukkit.getScheduler().cancelTask(taskId);
	}

	/**
	 * Mark this transition as complete. The animation will stop if it isn't already
	 * finished and the destination page will be fully opened to the user.
	 */
	public void complete() {
		complete = true;
		endAnimation();
		session.onTransitionComplete();
		session.setActivePage(to, this);
	}

	/**
	 * Cancel will stop the transition and create a virtual fake page where the
	 * animation stopped where the player cannot do anything. It will not take the
	 * player to the destination page.
	 * 
	 * Use this if you want to change the destination page fluidly while this
	 * transition is in progress. Because cancel leaves the player on a temporary
	 * {@link StaticPage}, the next transition invoked will make use of the static
	 * page where this animation left off, preventing the items in the inventory
	 * from flashing back to the origin page before the next animation can start.
	 * 
	 */
	public void cancel() {
		complete = true;
		endAnimation();
		session.onTransitionComplete();
		// I will create a locked static page that is not interactable if the transition
		// gets cancelled.
		// Static pages are not able to be added to history so when we transition away
		// from the static page it will go away.
		session.setActivePage(new StaticPage(session, inv) {
			@Override
			public void onClick(InventoryClickEvent e) {
				final Inventory clicked = e.getClickedInventory();
				if (clicked != inv)
					return;
				e.setCancelled(true);
				return;
			}
		}, this);
	}

	public boolean canAnimate(int slot) {
		if (Pages.isToolbar(to.getInventory(), slot) && !to.animateToolbar() && from == to) {
			return false;
		}
		return !(Pages.isTopEdge(slot) && selectedItem);
	}

	public boolean resizeNeeded() {
		return size != inv.getSize();
	}

	public void resize() {
		if (size > inv.getSize()) {
			inv = Bukkit.createInventory(null, inv.getSize() + 9);
			Pages.fillBackground(inv, session.getUser());
			session.refresh();
			return;
		} else if (size < inv.getSize()) {
			inv = Bukkit.createInventory(null, inv.getSize() - 9);
			Pages.fillBackground(inv, session.getUser());
			session.refresh();
			return;
		}
	}

	/**
	 * Called when the animation is initially starting before creating the first frame.
	 */
	protected void onStart() {
		return;
	}
	
	/**
	 * @return The current frame index.
	 */
	public int getCurrentFrame() {
		return frame;
	}

	/**
	 * Called each tick when the transition is ready to create the next frame.
	 */
	public abstract void nextFrame(int frame);

	/**
	 * @return true if this animation should be scheduled to invoke another frame.
	 */
	public abstract boolean hasNextFrame();

	public abstract String getLabel();

}
