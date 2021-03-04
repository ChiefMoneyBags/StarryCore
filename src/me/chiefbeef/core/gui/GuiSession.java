package me.chiefbeef.core.gui;

import java.lang.reflect.InvocationTargetException;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import me.chiefbeef.core.gui.abstraction.GuiCookie;
import me.chiefbeef.core.gui.abstraction.Page;
import me.chiefbeef.core.gui.abstraction.StaticPage;
import me.chiefbeef.core.gui.cookies.GuiSessionCookies;
import me.chiefbeef.core.gui.history.HistoryLevel;
import me.chiefbeef.core.gui.history.GuiSessionHistory;
import me.chiefbeef.core.gui.transition.GuiTransition;
import me.chiefbeef.core.gui.transition.assets.GuiTransitionAssets;
import me.chiefbeef.core.gui.transition.other.TransitionInstant;
import me.chiefbeef.core.user.UserCore;
import me.chiefbeef.core.utility.Console;

/**
 * {@link GuiSession} are objects whos purpose is to manage all gui functions and tracking for each {@link UserCore}.
 * Each {@link UserCore} has one persistent {@link GuiSession} for the duration of their time
 * on the server. If the user closes their gui the {@link GuiSession} will persist however
 * it will lay dormant until another gui {@link Page} is opened. Once the {@link UserCore} logs out the {@link GuiSession}
 * will be lost, and a new one created on join event.
 * 
 * Think of it like a web browser and the gui's are web pages.
 * @author Kevin
 *
 */
public class GuiSession {
	
	private UserCore user;
	private Page page;
	private GuiTheme theme;
	private GuiTransition transition;
	
	private boolean
	ending,
	saveOnClose,
	goingBack;
	
	private GuiSessionCookies cookies;
	private GuiSessionHistory history;
	
	public GuiSession(UserCore user) {
		this.user = user;
		this.history = new GuiSessionHistory(this);
		this.cookies = new GuiSessionCookies(this);
		//this.theme = new GuiTheme(user);
	}
	
	/**
	 * The GuiSessionHistory keeps track of the page depth and previously traversed pages in the current gui session. 
	 * @return The page history.
	 */
	public GuiSessionHistory getHistory() {
		return history;
	}
	
	/**
	 * The GuiSessionCookies keeps track of data added by pages or otherwise added manually to the gui session.
	 * Pages can use cookies to communicate with eachother and create intuitive / dynamic design structures. 
	 * @return The cookies for this gui session.
	 */
	public GuiSessionCookies getCookies() {
		return cookies;
	}
	
	public void onTransitionComplete() {
		Console.debug("--|> Transition completed!");
		transition = null;
	}
	
	public void cancelTransition() {
		if (isTransitioning()) {
			Console.debug("--|> Cancelling transition!");
			//page.setInventory(transition.getInventory());
			if (!transition.isComplete()) {
				transition.cancel();
			}
			transition = null;
		}
	}
	
	/**
	 * @return true if this session should keep its state on inventory close, false if it should end.
	 */
	public boolean shouldSaveOnClose() {
		return saveOnClose;
	}
	
	/**
	 * Warning, this method is meant to be used internally and may produce unintended effects.
	 * Set whether this session should keep its state when the player closes their inventory,
	 * If true the session will retain all its information and stay paused,
	 * If false the session will end on inventory close.
	 * @param save Do you want to save the session on close.
	 */
	public void setSaveOnClose(boolean save) {
		this.saveOnClose = save;
	}
	
	/**
	 * Set the active {@link Page} for this session.
	 * This method will also 
	 * @param page
	 * @param transition
	 */
	public void setActivePage(final Page page, GuiTransition transition) {
		Console.debug("----- Set active page -----");
		if (isEnding()) {
			Console.debug("--|> GuiSession is ending!");
			return;
		}
		if (!goingBack && !(getPage() instanceof StaticPage)) {
			getHistory().addPage(getPage(), transition.getAssets());	
		}
		this.page = page;
		Console.debug("--|> Opening page to...");
		openInventory(page.getInventory());
	}
	
	/**
	 * -- Unsafe method --
	 * Manually display an inventory to the user.
	 * This method will retain cursor location and prevent the cursor item from being dropped on the ground.
	 * Mainly used during GuiTransitions to display the animation frames without adding pages to the session history.
	 * 
	 * If used incorrectly can cause issues, please instead use {@link GuiSession#transitionTo(Page, String)} as this method
	 * will not register an active page to the session.
	 * @param inv The inventory to open.
	 */
	public void openInventory(Inventory inv) {
		// Save item in cursor from dropping
		Player p = user.getPlayer();
		ItemStack item = p.getItemOnCursor();
		p.setItemOnCursor(null);
		setSaveOnClose(true);
		p.openInventory(inv);
		setSaveOnClose(false);
		p.setItemOnCursor(item);
	}
	
	/**
	 * Transition the gui session to a new page.
	 * Keep in mind this method will increase the page depth of the gui session and add the previous page to the
	 * gui history, it is not meant to be used to go back.
	 * @param to
	 * @param type
	 */
	public void transitionTo(Page to, Class<? extends GuiTransition> transitionType) {
		GuiTransitionAssets assets = (GuiTransitionAssets) GuiTransition.getRegistry().getAssets(transitionType);
		if (assets == null) {
			Console.generateException("The specified GuiTransition was not found! (" + transitionType + ") | Defaulting to internal transition." );
			assets = (GuiTransitionAssets) GuiTransition.getRegistry().getAssets(TransitionInstant.class);
		}
		invokeTransition(to, assets);
	}
	
	/**
	 * Transition the gui session to a new page.
	 * Keep in mind this method will increase the page depth of the gui session and add the previous page to the
	 * gui history, it is not meant to be used to go back.
	 * @param to
	 * @param type
	 */
	public void transitionTo(Page to, String transitionType) {
		GuiTransitionAssets assets = (GuiTransitionAssets) GuiTransition.getRegistry().getAssets(transitionType);
		if (assets == null) {
			Console.generateException("The specified GuiTransition was not found! (" + transitionType + ") | Defaulting to internal transition." );
			assets = (GuiTransitionAssets) GuiTransition.getRegistry().getAssets(TransitionInstant.class);
		}
		invokeTransition(to, assets);
	}
	
	/**
	 * Invoke the actual gui transition.
	 */
	private void invokeTransition(Page to, GuiTransitionAssets assets) {
		if (isEnding()) {
			return;
		}
		if (isTransitioning()) {
			transition.cancel();
		}
		goingBack = false;
		transition = assets.newInstance().setPageFrom(page).setPageTo(to).build();
		transition.start();
	}
	
	/**
	 * Transition back to
	 * @param historyPage
	 */
	private void invokeTransitionBack(HistoryLevel historyLevel) {
		if (isEnding()) {
			return;
		}
		if (isTransitioning()) {
			transition.cancel();
		}
		goingBack = true;
		transition = historyLevel.getTransition().newInstance().setPageFrom(page).setPageTo(historyLevel.getPage()).build();
		transition.start();
	}
	
	/**
	 * Get whether this session is currently processing a {@link GuiTransition}
	 * @return true if the session is transitioning to a new {@link Page}
	 */
	public boolean isTransitioning() {
		return transition != null;
	}
	
	/**
	 * Get the active {@link GuiTransition} in this session.
	 * @return The {@link GuiTransition}.
	 */
	public GuiTransition getTransition() {
		return transition;
	}
	
	/**
	 * @return true if this session is actively in a gui {@link Page} or transitioning to a gui {@link Page}
	 */
	public boolean isActive() {
		return page != null || transition != null;
	}
	
	/**
	 * End this gui session.
	 * doing so will close any active {@link Page}, reset the {@link GuiSessionHistory} / {@link GuiSessionCookies}
	 * and cancel any {@link GuiTransition} currently in progress.
	 */
	public void end() {
		ending = true;
		if (isTransitioning()) {
			transition.cancel();
		}
		transition = null;
		page = null;
		cookies.wipeCookies();
		history.wipeHistory();
		user.getPlayer().closeInventory();
		ending = false;
	}
	
	/**
	 * @return true if this session is in the process of ending.
	 */
	public boolean isEnding() {
		return ending;
	}
	
	/**
	 * This method can be used to refresh the {@link Page} the user is in to make sure changes to the
	 * inventory contents are updated.
	 */
	public void refresh() {
		if (isEnding()) {
			return;
		}
		if (transition != null) {
			openInventory(transition.getInventory());
		} else if (page != null) {
			openInventory(page.getInventory());
		} 
	}
	
	/**
	 * Go back one {@link Page} in the {@link GuiSessionHistory}.
	 * Any {@link GuiCookie} or {@link HistoryPage} at a depth greater than or equal to the new depth will be cleared.
	 */
	public void goBack() {
		goBack(1);
	}
	
	/**
	 * Go back a number of {@link Page}s in the {@link GuiSessionHistory}.
	 * * Any {@link GuiCookie} or {@link HistoryPage} at a depth greater than or equal to the new depth will be cleared.
	 * @param number The number of pages to go back.
	 */
	public void goBack(int number) {
		int depth = history.getPageDepth();
		if (depth == 1 || number == 0) {
			return;
		}
		int pageNum = depth-number;
		if (pageNum <= 0) {
			pageNum = 1;
		}
		goBackTo(pageNum);
	}
	
	/**
	 * Go back to a specific {@link Page} depth in the {@link GuiSessionHistory}.
	 * Any {@link GuiCookie} or {@link HistoryPage} at a depth greater than or equal to the new depth will be cleared.
	 * @param pageNum The page depth to return to.
	 */
	public void goBackTo(int pageNum) {
		int depth = history.getPageDepth();
		if (pageNum > depth) {
			return;
		}
		HistoryLevel historyPage = history.getPageAtDepth(pageNum);
		history.snip(pageNum);
		cookies.applyNewDepth(pageNum);
		
		invokeTransitionBack(historyPage);
	}
	
	/**
	 * 
	 * @return The {@link Page} currently active in this session.
	 */
	public Page getPage() {
		return page;
	}
	
	/**
	 * 
	 * @return The {@link UserCore} this session belongs to.
	 */
	public UserCore getUser() {
		return user;
	}
	
	/**
	 * @return The {@link GuiTheme} of this session, containing gui specific aesthetic information for the player.
	 */
	public GuiTheme getTheme() {
		return theme;
	}

}
