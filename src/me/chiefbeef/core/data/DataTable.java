package me.chiefbeef.core.data;

import java.io.IOException;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.DataFormatException;

public class DataTable {

	private DataBase base;
	
	private final String name;
	
	private Map<UUID, DataRow> cache = new ConcurrentHashMap<>();
	
	protected DataTable(DataBase base, String name) throws SQLException {
		this.base = base;
		this.name = name;
		
		if (!exists()) {
			create();
		}
	}
	
	/**
	 * @return False if the table does not exist in the database
	 * @throws SQLException
	 */
	public boolean exists() throws SQLException {
		return base.getConnection().getMetaData().getTables(null, null, name, null).next();
	}
	
	/**
	 * Create this table in the database.
	 * @throws SQLException
	 */
	public void create() throws SQLException {
		Statement state = base.getConnection().createStatement();
		state.execute("CREATE TABLE " + name + " (row_id VARCHAR(32))");
		state.close();
	}
	
	/**
	 * Add a new column to the table.
	 */
	public void addColumn(String name, JDBCType type) {
		
	}
	
	/**
	 * Drop a column from the table.
	 */
	public void dropColumn() {
		
	}
	
	/**
	 * SQL: SELECT * FROM table WHERE row_id = [row_id]
	 * @return The data row with the row id. Null if it doesn't exist.
	 * @throws SQLException 
	 * @throws DataFormatException 
	 */
	public synchronized DataRow getRow(UUID rowId) throws SQLException, DataFormatException {
		PreparedStatement state = base.getConnection().prepareStatement("SELECT * FROM ? WHERE ? = ?");
		state.setString(1, name);
		state.setString(2, "row_id");
		state.setString(3, rowId.toString());
		DataRow[] rows = parse(state.executeQuery());
		if (rows.length > 1) {
			throw new DataFormatException("There are 2 or more rows in a data table with the same row_id. These must be unique. table=(" + name + ") | row_id=(" + rowId.toString() + ")");
		}
		return parse(state.executeQuery())[0];	
	}
	
	/**
	 * SQL: SELECT * FROM table WHERE [queryColumn] = [data]
	 * Pull rows of data from the table. This method will return all rows of data that contain the "data"
	 * in the "columnName" specified. EX:
	 * Column=ISLAND_OWNER value=UUID returns the row of data for the island with owner specified
	 * Column=ISLAND_LEVEL value=10 returns the rows of data for all islands with island level 10 
	 * @return
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public synchronized DataRow[] getMatchingRows(String querycolumn, Object data) throws SQLException {
		PreparedStatement state = base.getConnection().prepareStatement("SELECT * FROM ? WHERE ? = ?");
		state.setString(1, name);
		state.setString(2, querycolumn);
		state.setObject(3, data);
		return parse(state.executeQuery());
	}
	
	/**
	 * SQL: SELECT * FROM table
	 * @return All rows present in the table.
	 * @throws SQLException
	 */
	public synchronized DataRow[] getAllRows() throws SQLException {
		return parse(base.getConnection().createStatement().executeQuery("SELECT * FROM " + name));
	}
	
	/**
	 * SQL: SELECT * FROM table WHERE row_id = [rowId]
	 * Get a single value from a specific row / column in the data table. 
	 * @param queryColumn
	 * @param rowId
	 * @return
	 * @throws SQLException
	 */
	public synchronized Object getValue(UUID rowId, String queryColumn) throws SQLException {
		PreparedStatement state = base.getConnection().prepareStatement("SELECT * FROM ? WHERE ? = ?");
		state.setString(1, name);
		state.setString(2, "row_id");
		state.setString(3, rowId.toString());
		ResultSet results = state.executeQuery();
		
		int count = results.getMetaData().getColumnCount();
		ResultSetMetaData meta = results.getMetaData();
		
		while (results.next()) {
			for (int column = 1; column <= count; column++) {
				if (meta.getColumnName(column).equals(queryColumn)) {
					return results.getObject(1);
				}
			}
		}
		return null;
	}
	
	private synchronized DataRow[] parse(ResultSet results) throws SQLException {
		List<DataRow> rows = new ArrayList<>();
		int count = results.getMetaData().getColumnCount();
		ResultSetMetaData meta = results.getMetaData();
		
		while (results.next()) {
			Map<String, DataCell> columns = new HashMap<>();
			for (int column = 1; column <= count; column++) {
				String name = meta.getColumnName(column);
				Object ob = results.getObject(column);
				columns.put(name, new DataCell(this, name, ob));
			}
			UUID id = UUID.fromString((String) columns.get("row_id").getData());
			DataRow row = cache.containsKey(id) ? cache.get(id) : new DataRow(id, columns);
			if (!cache.containsKey(id)) {
				cache.put(id, row);
			}
			rows.add(row);
		}
		return rows.toArray(new DataRow[rows.size()]);
	}
	
	
	public synchronized void setValue() {
		
	}
	
	/**
	 * Commit data in the table.
	 * @throws SQLException 
	 */
	public synchronized void setRow(DataRow row) throws SQLException {
		PreparedStatement state = base.getConnection().prepareStatement("INSERT INTO ? (?) VALUES (?)");
		state.setString(1, name);
		//TODO
		/**
		for (Entry<String, DataCell> column: row.getChanges()) {
			state.setString(2, column.getKey());
			Object object = column.getValue();
			state.setObject(3, column.getValue());
			state.executeUpdate();
		}
		*/
		state.close();
		closeRow(row);
	}
	
	/**
	 * Drop a row from the DataTable.
	 * @param row
	 * @throws SQLException
	 */
	public synchronized void dropRow(DataRow row) throws SQLException {
		PreparedStatement state = base.getConnection().prepareStatement("DELETE FROM ? WHERE row_id = ?");
		state.setString(1, name);
		state.setString(2, row.getRowId().toString());
		state.execute();
		state.close();
		closeRow(row);
	}
	
	/**
	 * Commit multiple rows to the database.
	 * @throws SQLException
	 */
	public synchronized void setRows(DataRow... rows) throws SQLException {
		for (DataRow row: rows) {
			setRow(row);
		}
	}
	
	public synchronized void closeRow(DataRow row) {
		cache.remove(row.getRowId());
	}
	
	/**
	 * Commit all the rows that are in local memory to the database.
	 * @throws SQLException
	 */
	public synchronized void commitLocalTable() throws SQLException {
		for (DataRow row: cache.values()) {
			setRow(row);
		}
	}
	
	/**
	 * @return The name of this table in the database.
	 */
	public String getName() {
		return name;
	}
	
}
