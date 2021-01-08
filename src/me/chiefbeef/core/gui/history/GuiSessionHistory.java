package me.chiefbeef.core.gui.history;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import me.chiefbeef.core.gui.GuiSession;
import me.chiefbeef.core.gui.abstraction.Page;
import me.chiefbeef.core.gui.transition.assets.GuiTransitionAssets;

/**
 * GuiSessionHistory keeps track of the page depth of the current gui GuiSession.
 * Previously visited Pages will be chained together and held in memory rather then being discarded.
 * This allows the GuiSession to traverse pages using intutive design structures without needing to hard code
 * every use case where pages may link together in the actual Page objects.
 * 
 * -- NOTES --
 * page history is not a 0 index feature. the first page of the gui is page depth 1! 0 depth means the inventory is closed.
 * 
 * @author Kevin
 *
 */
public class GuiSessionHistory {

	/**
	 * The maximum chain depth is hard coded until i find a way to intuitivly prevent infinite chaining of pages.
	 * 
	 * --notes--
	 * If a page type is already in the chain we could jump the chain back to the last known instance of that type.
	 * 
	 * The issue comes with pages that may be re-usable under different circumstances, especially when GuiCookies come into play.
	 * for instance a page that displays info about a CustomItem may contain other CustomItems that consequently, have their own info panels
	 * with a multiple layers of depth. we cannot jump the chain back in this circumstance otherwise we lose all layers of depth
	 * in the CustomItem info panel upon trying to use the back button.
	 * 
	 * Perhaps if the previous (X) pages in the chain are repeated we can safely jump the chain back (X) pages to keep it
	 * from growing too large without losing depth.
	 * 
	 * Keep in mind that infinite gui chaining would be a design flaw within the structure of the Pages themselves and doesn't
	 * necessarily need to hold back the potential of the GuiSessionHistory object.
	 *
	 */
	public static final int MAX_DEPTH = 30;
	
	private GuiSession session;
	private Map<Integer, HistoryLevel> history = new ConcurrentHashMap<>();

	public GuiSessionHistory(GuiSession session) {
		this.session = session;
	}
	
	/**
	 * Plz no modifies
	 * @return The map of gui page history.
	 */
	public Map<Integer, HistoryLevel> getPageHistory() {
		return history;
	}
	
	public boolean hasPreviousPage() {
		return history.size() > 0;
	}
	
	/**
	 * 
	 * @return The page previous to the currently open {@link Page} in the chain. 
	 */
	public HistoryLevel getPreviousPage() {
		return history.get(history.size()-2);
	}
	
	public void addPage(Page page, GuiTransitionAssets transition) {
		history.put(getPageDepth()+1, new HistoryLevel(page, transition));
	}
	
	/**
	 * 
	 * @return The current depth of the page chain.
	 */
	public int getPageDepth() {
		return history.size();
	}
	
	/**
	 * Check if any instance of this {@link Page} type exist in the page history
	 * @param type The type of page
	 * @return true if it is in the chain.
	 */
	public boolean hasVisted(Class<? extends Page> type) {
		return history.values().stream().anyMatch(historyPage -> historyPage.getPage().getClass().equals(type));
	}
	
	/**
	 * Gets the page of the given type that is deepest inside of the history chain.
	 * @return The page of this type at the deepest level in the history chain.
	 */
	public Page getDeepestInstanceOf(Class<? extends Page> type) {
		Page[] array = (Page[]) history.values().stream().filter(historyPage -> historyPage.getPage().getClass().equals(type)).toArray();
		return array != null && array.length > 0 ? array[array.length-1] : null;
	}
	
	/**
	 * Clear the sessions page history.
	 */
	public void wipeHistory() {
		history.clear();
	}
	
	/**
	 * Snip the history back to a specific depth.
	 * @param depth
	 */
	public void snip(int depth) {
		if (depth >= history.size()) {
			return;
		}
		for (Entry<Integer, HistoryLevel> entry: history.entrySet()) {
			if (entry.getKey() > depth) {
				history.remove(entry.getKey());
			}
		}
	}
	
	/**
	 * Get the page at a specific depth in the page history.
	 * @param depth The depth.
	 * @return The page at the depth.
	 */
	public HistoryLevel getPageAtDepth(int depth) {
		if (depth > history.size()) {
			return null;
		}
		return history.get(depth);
	}
}
