package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
/**
 * class to model a transaction from table
 * @author Giuseppe
 */
import database.TableSchema.Column;

public class TableData {

	DbAccess db;

	public TableData(DbAccess db) {
		this.db=db;
	}

	/**
	 * Make query, and for the current tuple in resultset, extract the values of single fields (using getDouble() o getString()), 
	 * to add at object instance of class Example.
	 * @param table: scheme of table
	 * @return List: that contain an objects for each tuple of resultset
	 * @throws SQLException
	 * @throws EmptySetException
	 */
	public List<Example> getDistinctTransazioni(String table) throws SQLException, EmptySetException{
		LinkedList<Example> transSet = new LinkedList<Example>();
		Statement statement;
		TableSchema tSchema=new TableSchema(db,table);


		String query="select distinct ";

		for(int i=0;i<tSchema.getNumberOfAttributes();i++){
			Column c=tSchema.getColumn(i);
			if(i>0)
				query+=",";
			query += c.getColumnName();
		}
		if(tSchema.getNumberOfAttributes()==0)
			throw new SQLException();
		query += (" FROM "+table);

		statement = DbAccess.getConnection().createStatement();
		ResultSet rs = statement.executeQuery(query);
		boolean empty=true;
		while (rs.next()) {
			empty=false;
			Example currentTuple=new Example();
			for(int i=0;i<tSchema.getNumberOfAttributes();i++)
				if(tSchema.getColumn(i).isNumber())
					currentTuple.add(rs.getDouble(i+1));
				else
					currentTuple.add(rs.getString(i+1));
			transSet.add(currentTuple);
		}
		rs.close();
		statement.close();
		if(empty) throw new EmptySetException();


		return transSet;
	}

	/**
	 * @param table: table of database
	 * @param column: column of table
	 * @return TreeSet: ordered column name attributes in ascending 
	 * values in table formulated by query.
	 * @throws SQLException
	 */
	public Set<Object> getDistinctColumnValues(String table,Column column) throws SQLException{
		Set<Object> valueSet = new TreeSet<Object>();
		Statement statement;
		TableSchema tSchema=new TableSchema(db,table);

		String query="select distinct ";
		query+= column.getColumnName();
		query += (" FROM "+table);
		query += (" ORDER BY " +column.getColumnName());

		statement = DbAccess.getConnection().createStatement();
		ResultSet rs = statement.executeQuery(query);
		while (rs.next()) {
			if(column.isNumber())
				valueSet.add(rs.getDouble(1));
			else
				valueSet.add(rs.getString(1));

		}
		rs.close();
		statement.close();

		return valueSet;

	}

	/**
	 * 
	 * @param table: table of database
	 * @param column: column of table
	 * @param aggregate: object of type class enum QUERY_TYPE
	 * @return value: aggregated value (min value or max value)
	 * @throws SQLException
	 * @throws NoValueException
	 */
	public Object getAggregateColumnValue(String table,Column column,QUERY_TYPE aggregate) throws SQLException,NoValueException{
		Statement statement;
		TableSchema tSchema=new TableSchema(db,table);
		Object value=null;
		String aggregateOp="";

		String query="select "; 
		if(aggregate==QUERY_TYPE.MAX)
			aggregateOp+="max";
		else
			aggregateOp+="min";
		query+=aggregateOp+"("+column.getColumnName()+ ") FROM "+table;


		statement = DbAccess.getConnection().createStatement();
		ResultSet rs = statement.executeQuery(query);
		if (rs.next()) {
			if(column.isNumber())
				value=rs.getFloat(1);
			else
				value=rs.getString(1);

		}
		rs.close();
		statement.close();
		if(value==null)
			throw new NoValueException("No " + aggregateOp+ " on "+ column.getColumnName());

		return value;

	}





}
