package database;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DbAccessTest {

	private String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	private String DBMS = "jdbc:mysql";
	private String SERVER= "localhost";
	private String DATABASE = "MapDB";
	private String PORT= "3306";
	private String USER_ID = "MapUser";
	private String PASSWORD = "map";
	private Connection conn;

	@BeforeEach
	void setUp() throws Exception {
		Class.forName(DRIVER_CLASS_NAME); 
		conn = DriverManager.getConnection(DBMS+"://"+SERVER+":"+PORT+"/"+DATABASE,USER_ID,PASSWORD);
	}

	@AfterEach
	void tearDown() throws Exception {
		conn=null;
		DRIVER_CLASS_NAME =null;
		DBMS= null;
		SERVER= null;
		DATABASE = null;
		PORT= null;
		USER_ID = null;
		PASSWORD = null;
	}

	@Test
	void testInitConnection() throws ClassNotFoundException, DatabaseConnectionException, SQLException {
		DbAccess.initConnection();
		assertNotNull(conn);
	}

	@Test
	void testGetConnection() {
		conn=DbAccess.getConnection();
		assertNotNull(conn);
	}


	@Test
	void testCloseConnection() throws SQLException {

		DbAccess.closeConnection();
		assertFalse("connection not closed",conn.isClosed());
	}

}
