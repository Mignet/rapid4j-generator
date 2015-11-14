package com.v5ent.rapid4j.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTemplate {

	private Connection connection;

	/**
	 * DAO
	 * 
	 * @param connection
	 */
	public JdbcTemplate(Connection connection) {
		this.connection = connection;
	}

	/**
	 * executeUpdate
	 * 
	 * @param sql
	 * @return boolean
	 */
	public boolean createOrUpdate(String sql) {
		Statement stmt = null;
		try {
			stmt = this.connection.createStatement();
			stmt.executeUpdate(sql);
			return true;
		} catch (Exception e) {
			System.out.println("executeUpdate("+sql+"): " + e.getLocalizedMessage());
			connectionRollback(connection);
			return false;
		}
	}

	/**
	 * insert一条多个字段值的数据
	 * 
	 * @param table
	 *            表名
	 * @param params
	 *            多个字段值
	 * @return boolean
	 */
	public boolean insert(String table, String[] params) {
		Statement stmt = null;
		String sql = "insert into " + table + " values('";
		for (int i = 0; i < params.length; i++) {
			if (i == (params.length - 1)) {
				sql += (params[i] + "');");
			} else {
				sql += (params[i] + "', '");
			}
		}
		System.out.println(sql);
		try {
			stmt = this.connection.createStatement();
			stmt.executeUpdate(sql);
			if (!connection.isClosed()) {
				connection.close();
			}
			return true;
		} catch (Exception e) {
			System.out.println("insert " + table + " failed : " + e.getLocalizedMessage());
			connectionRollback(connection);
			return false;
		}
	}

	/**
	 * @param table
	 * @param keyParam
	 * @param keyField
	 * @param fields
	 * @param params
	 * @return boolean
	 */
	public boolean update(String table, String keyParam, String keyField, String[] fields, String[] params) {
		Statement stmt = null;
		String sql = "update " + table + " set ";
		for (int i = 0; i < fields.length; i++) {
			if (i == (fields.length - 1)) {
				sql += (fields[i] + "='" + params[i] + "' where " + keyField + "='" + keyParam + "';");
			} else {
				sql += (fields[i] + "='" + params[i] + "', ");
			}
		}
		System.out.println(sql);
		try {
			stmt = this.connection.createStatement();
			stmt.executeUpdate(sql);
			return true;
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			connectionRollback(connection);
			return false;
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
					stmt = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param table
	 * @param key
	 * @param keyValue
	 * @return boolean
	 */
	public boolean delete(String table, String key, String keyValue) {
		Statement stmt = null;
		String sql = "delete from " + table + " where " + key + "='" + keyValue + "';";
		System.out.println(sql);
		try {
			stmt = this.connection.createStatement();
			stmt.executeUpdate(sql);
			return true;
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			connectionRollback(connection);
			return false;
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
					stmt = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void connectionRollback(Connection connection) {
		try {
			connection.rollback();
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

	public void close() {
		try {
			if (connection != null) {
				connection.close();
				connection = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet query(String sql) throws SQLException {
		return this.connection.createStatement().executeQuery(sql);
	}
}
