package me.chiefbeef.core.data;

import java.sql.JDBCType;

public class SqlDataWrapper {

	
	private Object data;
	private JDBCType type;
	private int index;
	
	
	public SqlDataWrapper(Object data, int index, JDBCType type) {
		this.index = index;
		this.data = data;
		this.type = type;
	}
	
	public Object getData() {
		return data;
	}
	
	public int getColumnIndex() {
		return index;
	}
	
	public JDBCType getType() {
		return type;
	}
}
