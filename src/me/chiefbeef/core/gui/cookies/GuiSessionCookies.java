package me.chiefbeef.core.gui.cookies;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import me.chiefbeef.core.customitem.tracking.ItemTracker;
import me.chiefbeef.core.gui.GuiSession;
import me.chiefbeef.core.gui.abstraction.GuiCookie;

/**
 * GuiSessionCookies allows data to be applied to a users GuiSession that may impact / affect Page construction
 * among a number of other things. It is versatile and can be used in many ways, like a PersistentDataContainer on an item.
 * 
 * An example of usage:
 *   While in a gui page the user clicks on a CustomItem to view attributes / info about said CustomItem.
 *   When the click occcurs a GuiCookie is added to the users GuiSession that contains the CustomItem currently
 *   selected by the user.
 *   
 *   A CustomItem info Page can then use this cookie to build a page containing info about the CustomItem.
 *   
 *   Since the cookie is stored with the depth information about when it was added in the gui, The user can then
 *   click on another CustomItem deeper in the gui structure to see information about that CustomItem, since the
 *   CustomItem info page will get its data using the {@link GuiSessionCookies#getDeepestInstanceOf(Class)} method.
 *   
 *   Upon backtracking in the gui using the back button depth information and cookies are snipped meaning the selected
 *   CustomItem will be reverted to the origional item clicked once we backtrack enough pages.
 *   
 *   So the user selects CustomItem A at a gui depth of 3 pages. the 4th page is Item A's info panel.
 *   GuiCookie for CustomItem A is added at depth 3.
 *   Item A's info panel contains, for some reason, another CustomItem, CustomItem B.
 *   
 *   When CustomItem B is clicked, GuiCookie for CustomItem B is added at depth 4, page 5 is CustomItem B's info panel.
 *   When the back button is clicked and user goes back to page 4, all cookies added for depth 4 are reset,
 *   so the CustomItem info panel will again, get the cookie for depth 3, which is CustomItem A.
 * 
 * All in all it makes it much easier for Pages to communicate intent / data with eachother without needing to invoke
 * different explicit constructors for different purposes and needing to chain data with dependency injection. 
 * Escpecially when say, a page at depth 10 requires some data established at page depth 2.
 * 
 * @author Kevin
 *
 */
public class GuiSessionCookies {

	private GuiSession session;
	private Map<Class<? extends GuiCookie>, DepthTree> cookies = new ConcurrentHashMap<>();
	
	public GuiSessionCookies(GuiSession session) {
		this.session = session;
	}
	
	/**
	 * Check if this session contains a specific type of cookie.
	 * @param type The type of cookie.
	 * @return true if this gui session contains an instance of this cookie type.
	 */
	public boolean hasCookie(Class<? extends GuiCookie> type) {
		return cookies.containsKey(type);
	}
	
	/**
	 * Add a cookie at the current depth in the gui. going back one page will cause it to be removed.
	 * @param cookie
	 */
	public void addCookieCurrentDepth(GuiCookie cookie) {
		DepthTree tree = cookies.getOrDefault(cookie.getClass(), new DepthTree(cookie.getClass()));
		tree.addAtDepth(cookie, session.getHistory().getPageDepth());
	}
	
	/**
	 * Add a cookie at the specified depth in the gui.
	 * Keep in mind that adding a cookie at a depth greater than or equal to the current depth of the gui
	 * will cause the cookie to be lost if the user navigates using the back button as cookies for depths
	 * greater than or equal to the current depth are snipped while backtracking the gui, allowing pages to reset their functions.
	 * 
	 * Cookies added at depth 0 are usually only removed when the user completely closes the gui.
	 * @param cookie The GuiCookie to add
	 * @param depth The depth to add the cookie.
	 */
	public void addCookieAtDepth(GuiCookie cookie, int depth) {
		if (depth < 0) {
			depth = 0;
		}
		DepthTree tree = cookies.getOrDefault(cookie.getClass(), new DepthTree(cookie.getClass()));
		tree.addAtDepth(cookie, depth);
	}
	
	/**
	 * Invoked when the user goes backward in their gui history and the page depth is reduced.
	 * All cookies that were applied at a level >= their current depth are removed.
	 * @param depth the new depth
	 */
	public void applyNewDepth(int depth) {
		for (DepthTree tree: cookies.values()) {
			tree.snip(depth);
			checkEmpty(tree);
		}
	}
	
	/**
	 * Get the deepest / most recent known instance of this GuiCookie type. Deepest referring to the page depth
	 * of the gui when the cookie was added.
	 * @param type The type of cookie to get.
	 * @return 
	 * @return The deepest instance of this type.
	 */
	public <T extends GuiCookie> T getDeepestInstanceOf(Class<T> type) {
		DepthTree tree = getTree(type);
		if (tree == null) {
			return null;
		}
		GuiCookie cookie = tree.getDeepest();
		if (!cookie.getClass().isAssignableFrom(type)) {
			return null;
		}
		return type.cast(cookie);
	}
	
	/**
	 * Remove a GuiCookie for the current depth of the gui.
	 * @param type The type of cookie.
	 */
	public void removeCookieCurrentDepth(Class<? extends GuiCookie> type) {
		DepthTree tree = getTree(type);
		if (tree == null) {
			return;
		}
		tree.removeAtDepth(session.getHistory().getPageDepth());
		checkEmpty(tree);
	}
	
	/**
	 * Remove all cookies of this type from the gui.
	 * @param type The type of cookie.
	 */
	public void removeCookies(Class<? extends GuiCookie> type) {
		cookies.remove(type);
	}
	
	/**
	 * Remove a specific cookie from the gui regardless of its depth.
	 * @param cookie The cookie to remove.
	 */
	public void removeCookie(GuiCookie cookie) {
		DepthTree tree = getTree(cookie.getClass());
		if (tree == null) {
			return;
		}
		
		tree.remove(cookie);
		checkEmpty(tree);
	}
	
	/**
	 * Remove all cookies from the gui.
	 */
	public void wipeCookies() {
		cookies.clear();
	}
	
	/**
	 * 
	 * @param type The type of cookie.
	 * @return The cookie types depth tree.
	 */
	private DepthTree getTree(Class<? extends GuiCookie> type) {
		return cookies.getOrDefault(type, null);
	}
	
	/**
	 * Check if the depth tree is empty and remove it if so.
	 * @param tree the depth tree.
	 */
	private void checkEmpty(DepthTree tree) {
		if (tree.isEmpty()) {
			removeCookies(tree.getType());
		}
	}
	
	
	
	
	private class DepthTree {
		
		private Class<? extends GuiCookie> type;
		private Map<Integer, GuiCookie> depthCookies = new ConcurrentHashMap<>();
		
		public DepthTree(Class<? extends GuiCookie> type) {
			this.type = type;
		}
		
		public void addAtDepth(GuiCookie cookie, int depth) {
			depthCookies.put(depth, cookie);
		}
		
		public void removeAtDepth(int depth) {
			depthCookies.remove(depth);
		}
		
		public void remove(GuiCookie cookie) {
			for (Entry<Integer, GuiCookie> entry: depthCookies.entrySet()) {
				if (entry.getValue().equals(cookie)) {
					depthCookies.remove(entry.getKey());
				}
			}
		}
		
		public void snip(int depth) {
			depthCookies.entrySet().removeIf(entry -> entry.getKey() >= depth);
		}
		
		public boolean isEmpty() {
			return depthCookies.size() == 0;
		}
		
		//i couldn't figure out how to use a stream for this. :(
		public GuiCookie getDeepest() {
			int deepest = -999;
			GuiCookie found = null;
			for (Entry<Integer, GuiCookie> entry: depthCookies.entrySet()) {
				if (entry.getKey() > deepest) {
					found = entry.getValue();
				}
			}
			return found;
		}
		
		public Class<? extends GuiCookie> getType() {
			return type;
		}
	}

}
