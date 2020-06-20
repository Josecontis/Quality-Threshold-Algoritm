package database;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mysql.jdbc.Statement;

import database.TableSchema.Column;

class TableDataTest {

	DbAccess db;
	private TableData t;
	private String table ="playtennis";
	Set<Object> valueSet;
	TableSchema tSchema;
	Connection con;
	ResultSet res;
	Example e;

	@BeforeEach
	void setUp() throws Exception {
		DbAccess.initConnection();		
		t=new TableData(db);
		tSchema=new TableSchema(db,table);
		e=new Example();
		valueSet = new TreeSet<Object>();
		DbAccess.initConnection();		
		con=DbAccess.getConnection();
		DatabaseMetaData meta = con.getMetaData();
		res = meta.getColumns(null, null, table, null);
	}

	@AfterEach
	void tearDown() throws Exception {
		db=null;
		t=null;
		table=null;
		valueSet=null;
		tSchema=null;
		con=null;
		res=null;
		e=null;
	}

	@Test
	void testTableData() {
		assertEquals(db,t.db);
	}

	@Test
	void testGetDistinctTransazioni() throws SQLException, EmptySetException {
		e.add("[sunny 30.3 high weak no , sunny 30.3 high strong no , overcast 30.0 high weak yes , rain 13.0 high weak yes , rain 0.0 normal weak yes , rain 0.0 normal strong no , overcast 0.1 normal strong yes , sunny 13.0 high weak no , sunny 0.1 normal weak yes , rain 12.0 normal weak yes , sunny 12.5 normal strong yes , overcast 12.5 high strong yes , overcast 29.21 normal weak yes , rain 12.5 high strong no ]");
		assertEquals(e.toString(),t.getDistinctTransazioni(table).toString()+" ");
	}

	@Test
	void testGetDistinctColumnValues() throws SQLException {
		Set<Object> valueSet = new TreeSet<Object>();
		valueSet.add("overcast, rain, sunny");	
		Column column=tSchema.new Column("outlook","String");
		Statement s = (Statement) con.createStatement();
		ResultSet rs = s.executeQuery("SELECT * FROM " + table);
		assertEquals(valueSet.toString(),t.getDistinctColumnValues(table, column).toString());
	}

	@Test
	void testGetAggregateColumnValue() throws SQLException, NoValueException {
		QUERY_TYPE q = null;

		Column column=tSchema.new Column("outlook","String");
		Object value=t.getAggregateColumnValue(table, column, q);
		Statement s = (Statement) con.createStatement();
		ResultSet rs = s.executeQuery("SELECT * FROM " + table);
		assertEquals(value.toString(),t.getAggregateColumnValue(table, column, q).toString());
	}

}
