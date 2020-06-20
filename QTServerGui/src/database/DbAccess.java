package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * @author Giuseppe
 */
public class DbAccess {
	
	/**
	 * @param DRIVER_CLASS_NAME: Driver to connect with mysql 
	 * @param DBMS: mysql location
	 * @param SERVER: identifier of server of database
	 * @param DATABASE: name of DB (MapDB)
	 * @param PORT: port to DBMS MySQL accept connection (3306)
	 * @param USER_ID: Username for login (MapUser)
	 * @param PASSWORD: password of user USER_ID
	 * @param conn: handle connection to database
	 */
	private final static String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	private final static String DBMS = "jdbc:mysql";
	private final static String SERVER= "localhost";
	private final static String DATABASE = "MapDB";
	private final static String PORT= "3306";
	private final static String USER_ID = "MapUser";
	private final static String PASSWORD = "map";
	private static Connection conn;

	/**
	 * This method tell to class loader to load driver mysql 
	 * with Class.forName(), and initialize connection conn
	 * @throws DatabaseConnectionException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void initConnection() throws DatabaseConnectionException, ClassNotFoundException, SQLException
	{	
		Class.forName(DRIVER_CLASS_NAME); 
		conn = DriverManager.getConnection(DBMS+"://"+SERVER+":"+PORT+"/"+DATABASE,USER_ID,PASSWORD);
	}
	/**
	 * @return conn: To handle the connection
	 */
	public static Connection getConnection()
	{
		return conn;
	}

	/**
	 * close the connection
	 */
	public static void closeConnection()
	{
		try {
			conn.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

