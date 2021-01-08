package me.chiefbeef.core.data;

import java.io.IOException;
import java.io.Serializable;

public class DataCell {

	private boolean vol;
	
	private DataTable table;
	private String column;
	
	private Object data;
	private byte[] image;
	private Object changed;
	
	public DataCell(DataTable table, String column, Object data) {
		this.table = table;
		this.column = column;
		this.data = data;
		image();
	}
	
	/**
	 * Set this cell to a volatile state. The cell will sync with the database before data is read or written.
	 * This should be set true if you intend to manipulate the same data in the cell on different servers at
	 * the same time to prevent issues with consistency.
	 */
	public synchronized void setVolatile(boolean bool) {
		this.vol = bool;
	}
	
	public synchronized boolean isVolatile() {
		return this.vol;
	}
	
	/**
	 * @param ob
	 */
	public synchronized void setData(Object ob) {
		this.changed = ob;
	}
	
	public synchronized Object getData() {
		return changed == null ? data : changed;
	}
	
	/**
	 * Attempt to revert the data to its previous state.
	 * If you pulled an object from the DataCell and proceeded to manipulate that object, its previous state will
	 * be restored from a binary image. 
	 * @param key The value to be reverted
	 */
	public synchronized boolean revert() {
		// If the changed value is novel, simply remove it.
		if (changed != null && changed != data) {
			changed = null;
			return true;
		// If the changed value is the initial value but manipulated, restore from binary image.
		} else if (changed != null && changed == data && image != null) {
			Serializable ser = null;
			try {
				ser = new Serializer(image).asObject();
				changed = null;
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// Cannot be reverted.
		return false;
	}
	
	/**
	 * Commit changes to the database.
	 */
	public synchronized void commit() {
		// There was no changes
		if (changed == null) {
			return;
		}
		
		// Set primary data as the new value and backup create a new restore image.
		data = changed;
		changed = null;
		image();
		
		// Send to the database.
		commitData();
	}
	
	/**
	 * Send data to the DataBase.
	 */
	private void commitData() {
		
	}
	
	private void image() {
		try {
			this.image = (data instanceof Serializable) ? new Serializer((Serializable) data).asBytes() : null;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
