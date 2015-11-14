package com.v5ent.rapid4j.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.v5ent.rapid4j.model.DbInfo;

public class DriverAdapter {

	public static Connection getConnection(DbInfo db) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			if ("MySQL".equals(db.getDbtype())) {
				Class.forName("com.mysql.jdbc.Driver", true, Thread.currentThread().getContextClassLoader());
				conn = DriverManager.getConnection(
						"jdbc:mysql://" + db.getDbhost() + ":" + db.getDbport() + "/" + db.getDbname()
								+ "?useUnicode=true&characterEncoding=UTF-8&connectTimeout=10000&socketTimeout=180000",
						db.getUsername(), db.getPassword());
			} else if ("PostgreSQL".equals(db.getDbtype())) {
				Class.forName("org.postgresql.Driver", true, Thread.currentThread().getContextClassLoader());
				conn = DriverManager.getConnection(
						"jdbc:postgresql://" + db.getDbhost() + ":" + db.getDbport() + "/" + db.getDbname()
								+ "?useUnicode=true&characterEncoding=UTF-8&loginTimeout=10&socketTimeout=180",
						db.getUsername(), db.getPassword());
			} else if ("SQLite".equals(db.getDbtype())) {
				// jdbc:sqlite:dbPath/dbname.db
				Class.forName("org.sqlite.JDBC", true, Thread.currentThread().getContextClassLoader());
				conn = DriverManager.getConnection("jdbc:sqlite:db/" + db.getDbname() + ".db", db.getUsername(),
						db.getPassword());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

}
