package me.chiefbeef.core.data;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DataRow {

	private UUID id;
	private DataTable table;
	
	private Map<String, DataCell> cells = new HashMap<>();
	
	private boolean vol = false;
	
	public DataRow(UUID id, Map<String, DataCell> columns) {
		this.id = id;
		this.cells.putAll(columns);
	}

	public Object getCell(String key) throws ClassNotFoundException, IOException {
		return cells.get(key);
	}
	
	/**
	public Set<Entry<String, DataCell>> getChanges() {
		InventoryClickEvent e = null;
		return changes.entrySet();
	}
	*/
	public UUID getRowId() {
		return id;
	}
	
	public DataTable getTable() {
		return table;
	}
	
	/**
	 * Revert all uncommitted changes made to the row.
	 */
	/**
	public synchronized void revertAll() {
		changes.clear();
	}
	*/
	/**
	 * Attempt to revert a value to its previous state.
	 * @param key The value to be reverted
	 */
	/**
	public synchronized boolean revert(String key) {
		return changes.remove(key) != null;
	}
	/*
	
	/**
	 * Resync this row with the database. This may need to be done in special cases where multiple servers
	 * are accessing the same data at the same.
	 */
	public synchronized void refresh() {
		
	}
	
	
	/**
	 * Set this row to a volatile state. The row will sync with the database before data is read or written.
	 * This should be set true if you intend to manipulate the same data on different servers at the same
	 * time to prevent issues with consistency.
	 */
	public synchronized void setVolatile(boolean bool) {
		this.vol = bool;
	}
	
	public synchronized boolean isVolatile() {
		return this.vol;
	}
	
	/**
	 * Commit changes made to the row and send them back to the database.
	 * Changes are automatically committed on auto save and server shutdown.
	 * @throws SQLException 
	 */
	/**
	public synchronized void commit() throws SQLException {
		for (Map.Entry<String, Object> entry: columns.entrySet()) {
			columns.put(entry.getKey(), entry.getValue());
		}
		table.setRow(this);
		return;
	}
	*/
	
}
