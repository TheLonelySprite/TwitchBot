package com.github.thelonedevil.TwitchBot;

import java.util.HashMap;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {

	Connection connect(String path) throws SQLException, ClassNotFoundException, NullPointerException {
		Class.forName("org.sqlite.JDBC");
		Connection connection = DriverManager.getConnection("jdbc:sqlite:" + path);
		return connection;
	}

	/**
	 * 
	 * @param connection
	 *            , the connection to a database, {@link #connect(String)}
	 * @param timeout
	 *            , the timeout for the query
	 *            {@link Statement#setQueryTimeout(int)}
	 * @return Returns a {@link Statement}
	 * @throws SQLException
	 */
	public Statement state(Connection connection, int timeout) throws SQLException {
		Statement statement = connection.createStatement();
		statement.setQueryTimeout(timeout); // set timeout to 30 sec.
		return statement;
	}

	/**
	 * 
	 * @param connection
	 *            , the connection to a database, {@link #connect(String)}
	 * @return Returns a {@link Statement}
	 * @throws SQLException
	 */
	public Statement state(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		statement.setQueryTimeout(30); // set timeout to 30 sec.
		return statement;
	}

	/**
	 * 
	 * @param statement
	 * @param table_name
	 * @param column_name1
	 * @param data_type1
	 * @throws SQLException
	 */
	void createTable(Statement statement, String table_name, String column_name1, String data_type1) throws SQLException {
		statement.executeUpdate("create table if not exists " + table_name + " (" + column_name1 + " " + data_type1 + ")");
	}

	/**
	 * 
	 * @param statement
	 * @param table_name
	 * @param column_name1
	 * @param data_type1
	 * @param column_name2
	 * @param data_type2
	 * @throws SQLException
	 */
	void createTable(Statement statement, String table_name, String column_name1, String data_type1, String column_name2, String data_type2) throws SQLException {
		statement.executeUpdate("create table if not exists " + table_name + " (" + column_name1 + " " + data_type1 + ", " + column_name2 + " " + data_type2 + ")");
	}

	/**
	 * 
	 * @param statement
	 * @param table_name
	 * @param column_name1
	 * @param data_type1
	 * @param column_name2
	 * @param data_type2
	 * @param column_name3
	 * @param data_type3
	 * @throws SQLException
	 */
	void createTable(Statement statement, String table_name, String column_name1, String data_type1, String column_name2, String data_type2, String column_name3, String data_type3)
			throws SQLException {
		statement.executeUpdate("create table if not exists " + table_name + " (" + column_name1 + " " + data_type1 + ", " + column_name2 + " " + data_type2 + ", " + column_name3 + " " + data_type3
				+ ")");
	}

	/**
	 * 
	 * @param statement
	 * @param table_name
	 * @param column_name1
	 * @param data_type1
	 * @param column_name2
	 * @param data_type2
	 * @param column_name3
	 * @param data_type3
	 * @param column_name4
	 * @param data_type4
	 * @throws SQLException
	 */
	void createTable(Statement statement, String table_name, String column_name1, String data_type1, String column_name2, String data_type2, String column_name3, String data_type3,
			String column_name4, String data_type4) throws SQLException {
		statement.executeUpdate("create table if not exists " + table_name + " (" + column_name1 + " " + data_type1 + ", " + column_name2 + " " + data_type2 + ", " + column_name3 + " " + data_type3
				+ ", " + column_name4 + " " + data_type4 + ")");
	}

	/**
	 * 
	 * @param statement
	 * @param table_name
	 * @param column_name1
	 * @param data_type1
	 * @param column_name2
	 * @param data_type2
	 * @param column_name3
	 * @param data_type3
	 * @param column_name4
	 * @param data_type4
	 * @param column_name5
	 * @param data_type5
	 * @throws SQLException
	 */
	void createTable(Statement statement, String table_name, String column_name1, String data_type1, String column_name2, String data_type2, String column_name3, String data_type3,
			String column_name4, String data_type4, String column_name5, String data_type5) throws SQLException {
		statement.executeUpdate("create table if not exists " + table_name + " (" + column_name1 + " " + data_type1 + ", " + column_name2 + " " + data_type2 + ", " + column_name3 + " " + data_type3
				+ ", " + column_name4 + " " + data_type4 + ", " + column_name5 + " " + data_type5 + ")");
	}

	/**
	 * 
	 * @param statement
	 * @param table_name
	 * @param column_name1
	 * @param data_type1
	 * @param column_name2
	 * @param data_type2
	 * @param column_name3
	 * @param data_type3
	 * @param column_name4
	 * @param data_type4
	 * @param column_name5
	 * @param data_type5
	 * @param column_name6
	 * @param data_type6
	 * @param column_name7
	 * @param data_type7
	 * @param column_name8
	 * @param data_type8
	 * @param column_name9
	 * @param data_type9
	 * @param column_name10
	 * @param data_type10
	 * @param column_name11
	 * @param data_type11
	 * @param column_name12
	 * @param data_type12
	 * @param column_name13
	 * @param data_type13
	 * @param column_name14
	 * @param data_type14
	 * @param column_name15
	 * @param data_type15
	 * @param column_name16
	 * @param data_type16
	 * @param column_name17
	 * @param data_type17
	 * @param column_name18
	 * @param data_type18
	 * @param column_name19
	 * @param data_type19
	 * @param column_name20
	 * @param data_type20
	 * @param column_name21
	 * @param data_type21
	 * @param column_name22
	 * @param data_type22
	 * @param column_name23
	 * @param data_type23
	 * @param column_name24
	 * @param data_type24
	 * @param column_name25
	 * @param data_type25
	 * @param column_name26
	 * @param data_type26
	 * @throws SQLException
	 */
	void createTable(Statement statement, String table_name, String column_name1, String data_type1, String column_name2, String data_type2, String column_name3, String data_type3,
			String column_name4, String data_type4, String column_name5, String data_type5, String column_name6, String data_type6, String column_name7, String data_type7, String column_name8,
			String data_type8, String column_name9, String data_type9, String column_name10, String data_type10, String column_name11, String data_type11, String column_name12, String data_type12,
			String column_name13, String data_type13, String column_name14, String data_type14, String column_name15, String data_type15, String column_name16, String data_type16,
			String column_name17, String data_type17, String column_name18, String data_type18, String column_name19, String data_type19, String column_name20, String data_type20,
			String column_name21, String data_type21, String column_name22, String data_type22, String column_name23, String data_type23, String column_name24, String data_type24,
			String column_name25, String data_type25, String column_name26, String data_type26) throws SQLException {
		statement.executeUpdate("create table if not exists " + table_name + " (" + column_name1 + " " + data_type1 + ", " + column_name2 + " " + data_type2 + ", " + column_name3 + " " + data_type3
				+ ", " + column_name4 + " " + data_type4 + ", " + column_name5 + " " + data_type5 + ", " + column_name6 + " " + data_type6 + ", " + column_name7 + " " + data_type7 + ", "
				+ column_name8 + " " + data_type8 + ", " + column_name9 + " " + data_type9 + ", " + column_name10 + " " + data_type10 + ", " + column_name11 + " " + data_type11 + ", " + column_name12
				+ " " + data_type12 + ", " + column_name13 + " " + data_type13 + ", " + column_name14 + " " + data_type14 + ", " + column_name15 + " " + data_type15 + ", " + column_name16 + " "
				+ data_type16 + ", " + column_name17 + " " + data_type17 + ", " + column_name18 + " " + data_type18 + ", " + column_name19 + " " + data_type19 + ", " + column_name20 + " "
				+ data_type20 + ", " + column_name21 + " " + data_type21 + ", " + column_name22 + " " + data_type22 + ", " + column_name23 + " " + data_type23 + ", " + column_name24 + " "
				+ data_type24 + ", " + column_name25 + " " + data_type25 + ", " + column_name26 + " " + data_type26 + ")");
	}

	/**
	 * 
	 * @param statement
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public static ResultSet rs(Statement statement, String query) throws SQLException {
		ResultSet rs = statement.executeQuery(query);
		return rs;
	}

	/**
	 * 
	 * @param statement
	 * @param table
	 * @param row_label
	 * @param column
	 * @return A HashMap<Column_label,Value> for the row_label if the row
	 *         exists, otherwise returns an empty HashMap<String,Object>
	 * @throws SQLException
	 */
	public HashMap<String, Object> getRow(Statement statement, String table, String row_label, String column) throws SQLException {
		HashMap<String, Object> row = new HashMap<String, Object>();
		String query = "SELECT * FROM " + table;
		ResultSet rs1 = rs(statement, query);
		while (rs1.next()) {
			if (rs1.getString(column).equalsIgnoreCase(row_label)) {
				ResultSetMetaData rsmd = rs1.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				int index = 1;
				while (index <= columnsNumber) {
					String Column_Label = rsmd.getColumnName(index);
					row.put(Column_Label, rs1.getObject(index));
					index++;
				}
			}
		}
		rs1.close();
		return row;
	}

	/**
	 * 
	 * @param statement
	 * @param table
	 * @param row_Label
	 * @param column
	 * @param map
	 * @throws SQLException
	 */
	/*public void updateRow(Statement statement, String table, String row_Label, String column, String map) throws SQLException {
		String update = "UPDATE " + table + " SET "+column+"= response WHERE ";
		statement.executeUpdate(update);
	}*/

	/**
	 * 
	 * @param statement
	 * @param table
	 * @param row_Label
	 * @param column
	 * @param map
	 * @throws SQLException
	 */
	public void insertRow(Statement statement, String table, String row_Label, String column, String map) throws SQLException {
		String update = "INSERT INTO " + table+" VALUES ('" + row_Label + "','"+map+"')";

		statement.executeUpdate(update);

	}

	/**
	 * 
	 * @param statement
	 * @param table
	 * @param column
	 * @return
	 * @throws SQLException
	 */
	public List<String> getStrings(Statement statement, String table, String column) throws SQLException {
		String query = "SELECT " + column + " FROM" + table;
		ResultSet rs1 = rs(statement, query);
		List<String> object = new ArrayList<String>();
		while (rs1.next()) {
			object.add(rs1.getString(column));
		}
		rs1.close();
		return object;

	}

	/**
	 * 
	 * @param statement
	 * @param table
	 * @param column
	 * @return
	 * @throws SQLException
	 */
	public List<String> getStrings(Statement statement, String table, String column, Boolean distinct) throws SQLException {
		String start;
		if (distinct) {
			start = "SELECT DISTINCT ";
		} else {
			start = "SELECT ";
		}
		String query = start + column + " FROM" + table;
		ResultSet rs1 = rs(statement, query);
		List<String> object = new ArrayList<String>();
		while (rs1.next()) {
			object.add(rs1.getString(column));
		}
		rs1.close();
		return object;

	}

	/**
	 * 
	 * @param statement
	 * @param table
	 * @param column1
	 * @param column2
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, List<String>> getStrings(Statement statement, String table, String column1, String column2) throws SQLException {
		HashMap<String, List<String>> map = new HashMap<String, List<String>>();
		String query = "SELECT " + column1 + ", " + column2 + " FROM " + table;
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		ResultSet rs1 = rs(statement, query);
		while (rs1.next()) {
			list1.add(rs1.getString(column1));
			list2.add(rs1.getString(column2));
		}
		rs1.close();
		map.put(column1, list1);
		map.put(column2, list2);
		return map;
	}

	/**
	 * 
	 * @param statement
	 * @param table
	 * @param column1
	 * @param column2
	 * @param column3
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, List<String>> getStrings(Statement statement, String table, String column1, String column2, String column3) throws SQLException {
		HashMap<String, List<String>> map = new HashMap<String, List<String>>();
		String query = "SELECT " + column1 + ", " + column2 + ", " + column3 + " FROM " + table;
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		List<String> list3 = new ArrayList<String>();
		ResultSet rs1 = rs(statement, query);
		while (rs1.next()) {
			list1.add(rs1.getString(column1));
			list2.add(rs1.getString(column2));
			list3.add(rs1.getString(column3));
		}
		rs1.close();
		map.put(column1, list1);
		map.put(column2, list2);
		map.put(column3, list3);
		return map;
	}

	/**
	 * 
	 * @param statement
	 * @param table
	 * @param column1
	 * @param column2
	 * @param column3
	 * @param column4
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, List<String>> getStrings(Statement statement, String table, String column1, String column2, String column3, String column4) throws SQLException {
		HashMap<String, List<String>> map = new HashMap<String, List<String>>();
		String query = "SELECT " + column1 + ", " + column2 + ", " + column3 + ", " + column4 + " FROM " + table;
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		List<String> list3 = new ArrayList<String>();
		List<String> list4 = new ArrayList<String>();
		ResultSet rs1 = rs(statement, query);
		while (rs1.next()) {
			list1.add(rs1.getString(column1));
			list2.add(rs1.getString(column2));
			list3.add(rs1.getString(column3));
			list4.add(rs1.getString(column4));
		}
		rs1.close();
		map.put(column1, list1);
		map.put(column2, list2);
		map.put(column3, list3);
		map.put(column4, list4);
		return map;
	}

	/**
	 * 
	 * @param statement
	 * @param table
	 * @param column1
	 * @param column2
	 * @param column3
	 * @param column4
	 * @param column5
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, List<String>> getStrings(Statement statement, String table, String column1, String column2, String column3, String column4, String column5) throws SQLException {
		HashMap<String, List<String>> map = new HashMap<String, List<String>>();
		String query = "SELECT " + column1 + ", " + column2 + ", " + column3 + ", " + column4 + ", " + column5 + " FROM " + table;
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		List<String> list3 = new ArrayList<String>();
		List<String> list4 = new ArrayList<String>();
		List<String> list5 = new ArrayList<String>();
		ResultSet rs1 = rs(statement, query);
		while (rs1.next()) {
			list1.add(rs1.getString(column1));
			list2.add(rs1.getString(column2));
			list3.add(rs1.getString(column3));
			list4.add(rs1.getString(column4));
			list5.add(rs1.getString(column5));
		}
		rs1.close();
		map.put(column1, list1);
		map.put(column2, list2);
		map.put(column3, list3);
		map.put(column4, list4);
		map.put(column5, list5);
		return map;
	}

	/**
	 * 
	 * @param statement
	 * @param table_name
	 * @param column1
	 * @param value1
	 * @throws SQLException
	 */
	public void insertString(Statement statement, String table_name, String column1, String value1) throws SQLException {
		String update = "INSERT INTO " + table_name + "( " + column1 + " VALUES ('" + value1 + "');";
		App.statement.executeUpdate(update);

	}

	/*
	 * statement.executeUpdate("drop table if exists person");
	 * statement.executeUpdate
	 * ("create table"+table+" (id integer, name string)");
	 * statement.executeUpdate("insert into person values(1, 'leo')");
	 * statement.executeUpdate("insert into person values(2, 'yui')"); ResultSet
	 * rs = statement.executeQuery("select * from person"); while(rs.next()) {
	 * // read the result set System.out.println("name = " +
	 * rs.getString("name")); System.out.println("id = " + rs.getInt("id")); } }
	 * 
	 * finally { try { if(connection != null) connection.close(); }
	 * catch(SQLException e) { // connection close failed.
	 * System.err.println(e); } } return null; }
	 */
}
