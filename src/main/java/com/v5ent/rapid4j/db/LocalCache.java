package com.v5ent.rapid4j.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.v5ent.rapid4j.db.model.DbInfo;
/**
 * 利用Sqlite存储数据<br>
 * 本地存储数据库连接信息
 * @author mignet
 *
 */
public class LocalCache {
	public static final String CREATE_TABLE_SONGINFO = "CREATE TABLE IF NOT EXISTS dbinfo ( "
			+ " id INTEGER PRIMARY KEY   AUTOINCREMENT ,"
			+ " dbtype     TEXT      NOT NULL,"
			+ " dbhost     TEXT      NOT NULL,"
			+ " dbport     TEXT      NOT NULL,"
			+ " dbname     TEXT      NOT NULL,"
			+ " username     TEXT      NOT NULL,"
			+ " password     TEXT      NOT NULL,"
			+ " isopened int(1) NOT NULL default 0);";  
	
	public static Connection initCache(){
		try {
			String url = "jdbc:sqlite:rapid4j.db";
			Class.forName("org.sqlite.JDBC",true,Thread.currentThread().getContextClassLoader()); 
			Connection conn = DriverManager.getConnection(url);
			Statement stat = conn.createStatement();
			ResultSet rs =  stat.executeQuery("select count(1) from sqlite_master where type='table' and name='dbinfo'");
			if(rs.next() && rs.getInt(1)>=1){
				return conn;
			}else{
				 stat.executeUpdate( CREATE_TABLE_SONGINFO );
				 return conn;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void putDbInfo(DbInfo dbinfo) throws SQLException{
		Connection conn =  initCache();
		conn.createStatement().execute("update dbinfo set isopened=0");
		String sql = "insert into dbinfo (dbtype, dbhost, dbport, dbname, username, password,isopened)values (?, ?, ?, ?, ?, ?, ?);";
		 PreparedStatement prep =conn.prepareStatement(sql);
		    prep.setString(1, dbinfo.getDbtype());
		    prep.setString(2, dbinfo.getDbhost());
		    prep.setString(3, dbinfo.getDbport());
		    prep.setString(4, dbinfo.getDbname());
		    prep.setString(5, dbinfo.getUsername());
		    prep.setString(6, dbinfo.getPassword());
		    prep.setString(7, "1");
		    prep.addBatch();
		    conn.setAutoCommit(false);
		    prep.executeBatch();
		    conn.setAutoCommit(true);
		 conn.close(); 
	}
	
	public static DbInfo getDbInfo() throws SQLException {
		DbInfo dbinfo = new DbInfo();
		Connection conn =  initCache();
		 Statement stat = conn.createStatement();
		ResultSet rs =  stat.executeQuery("select * from dbinfo where isopened=1;"); 
		if (rs.next()) {
			dbinfo.setDbtype(rs.getString(2));
			dbinfo.setDbhost(rs.getString(3));
			dbinfo.setDbport(rs.getString(4));
			dbinfo.setDbname(rs.getString(5));
			dbinfo.setUsername(rs.getString(6));
			dbinfo.setPassword(rs.getString(7));
		}
		 rs.close();
		 conn.close(); //结束数据库的连接 
		 return dbinfo;
	}

}

