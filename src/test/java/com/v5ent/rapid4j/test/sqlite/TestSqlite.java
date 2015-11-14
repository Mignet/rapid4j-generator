package com.v5ent.rapid4j.test.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestSqlite {

	public static final String CREATE_TABLE_SONGINFO = "CREATE TABLE IF NOT EXISTS dbinfo ( "
			+ " id INTEGER PRIMARY KEY   AUTOINCREMENT ,"
			+ " dbtype     TEXT      NOT NULL,"
			+ " dbhost     TEXT      NOT NULL,"
			+ " dbport     TEXT      NOT NULL,"
			+ " dbname     TEXT      NOT NULL,"
			+ " username     TEXT      NOT NULL,"
			+ " password     TEXT      NOT NULL,"
			+ " isopend int(1) NOT NULL default '0');";  
	
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		try{
			String url = "jdbc:sqlite:test.db";
			System.out.println(url);

			 //2，连接SQLite的JDBC
			 Class.forName("org.sqlite.JDBC").newInstance(); 

			 //建立一个数据库，如果不存在就在当前目录下自动创建
			 Connection conn = DriverManager.getConnection(url);

			 //3,创建表
			 Statement stat = conn.createStatement();
			 stat.executeUpdate( CREATE_TABLE_SONGINFO );
			 
			 //4,插入一条数据
			 PreparedStatement prep = conn.prepareStatement(
		      "insert into dbinfo (dbtype, dbhost, dbport, dbname, username, password,isopend)values (?, ?, ?, ?, ?, ?, ?);");
			    prep.setString(1, "PostgreSQL");
			    prep.setString(2, "localhost");
			    prep.setString(3, "5432");
			    prep.setString(4, "postgres");
			    prep.setString(5, "postgres");
			    prep.setString(6, "postgres");
			    prep.setString(7, "1");
			    prep.addBatch();
			    
			    conn.setAutoCommit(false);
			    prep.executeBatch();
			    conn.setAutoCommit(true);

			 ResultSet rs =  stat.executeQuery("select * from dbinfo;"); 
			 while (rs.next()) { //将查询到的数据打印出来
				 System.out.print("id = " + rs.getString(1)); //列属性一
				 System.out.println("type = " + rs.getString(2)); //列属性二 
			 }

			 rs.close();
			 conn.close(); //结束数据库的连接 
		 }
		 catch( Exception e )
		 {
			 e.printStackTrace ( );
		 }
	}

}

