package database;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

import database.TableSchema.Column;

class TableSchemaTest {
	DbAccess db = new DbAccess();
	private TableSchema s;
	private TableSchema.Column sc;
	String table="playtennis";
	Connection con;
	List<Column> tableSchema;
	ResultSet res;	

	@BeforeEach
	void setUp() throws Exception {

		DbAccess.initConnection();		
		con=DbAccess.getConnection();
		DatabaseMetaData meta = con.getMetaData();
		res = meta.getColumns(null, null, table, null);

		s=new TableSchema(db, table); 
		sc = s.new Column("column","number");
		tableSchema=new ArrayList<Column>();
	}

	@AfterEach
	void tearDown() throws Exception {
		db=null;
		s=null;
		sc=null;
		table=null;
		con=null;
		tableSchema=null;
		res=null;
	}

	@Test
	void testGetColumnName() {
		assertEquals("column", sc.getColumnName());
	}

	@Test
	void testIsNumber() {
		assertTrue(sc.isNumber());
	}

	@Test
	void testToString() {
		assertEquals("column:number",sc.toString());
	}


	@Test
	void testTableSchema() throws SQLException {
		assertEquals(db,s.db);

		ResultSetMetaData metadata = (ResultSetMetaData) res.getMetaData();
		assertEquals(24, metadata.getColumnCount());
		Statement s = (Statement) con.createStatement();
		ResultSet rs = s.executeQuery("SELECT * FROM " + table);

		assertEquals(s.getConnection(),con);
		assertEquals(rs.getStatement(),s);
		rs.close();
		s.close();

	}

	@Test
	void testGetNumberOfAttributes() {
		Column c=null;
		tableSchema.add(c);
		assertNotNull(s.getNumberOfAttributes());
	}

	@Test
	void testGetColumn() {
		Column c=null;
		tableSchema.add(c);
		assertNotNull(s.getColumn(0));
	}

}
