package com.v5ent.rapid4j.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.v5ent.rapid4j.db.domain.DbInfo;
import com.v5ent.rapid4j.db.vo.QTree;

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

	/**
	 * getTables ---------MySQL--------- 查询数据库中所有表名 select table_name from
	 * information_schema.tables where table_schema='csdb' and table_type='base
	 * table'; 查询指定数据库中指定表的所有字段名column_name 
	 * select column_name from information_schema.columns where table_schema='csdb' and table_name='users'
	 *  ---------PostgreSQL--------- 查询数据库中所有表名 SELECT
	 * tablename FROM pg_tables WHERE tablename NOT LIKE 'pg%' AND tablename NOT
	 * LIKE 'sql_%'; 
	 * ②数据库名 SELECT datname FROM pg_database; 
	 * ③列名 
	 * select * from information_schema.columns where table_name = 't_user'
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static List<QTree> getTables(DbInfo db) throws SQLException, ClassNotFoundException {
		List<QTree> list = new ArrayList<QTree>();
		SQLBaseDAO dao = new SQLBaseDAO(getConnection(db));
		String sql_table = "SELECT   tablename   FROM   pg_tables " + "WHERE   tablename   NOT   LIKE   'pg%' "
				+ "AND tablename NOT LIKE 'sql_%' ";
		if ("MySQL".equals(db.getDbtype())) {
			sql_table = "select table_name from information_schema.tables where table_schema='csdb' and table_type='base table'";
		}
		ResultSet rs = dao.query(sql_table);
		int i = 1;
		while (rs.next()) {
			QTree qt = new QTree();
			String tableName=rs.getString("tablename");
			qt.setId(i);
			qt.setText(tableName);
			qt.setUrl("table");
			//columns
			String sql_columns = "select * from information_schema.columns where table_name = '"+tableName+"'";
			if ("MySQL".equals(db.getDbtype())) {
				sql_columns = "select column_name from information_schema.columns where table_schema='csdb' and table_name='"+tableName+"'";
			}
			ResultSet rsc = dao.query(sql_columns);
			List<QTree> clist = new ArrayList<QTree>();
			int j=i*100;
			while(rsc.next()){
				QTree q = new QTree();
				String columnName=rsc.getString("column_name");
				String dataType=rsc.getString("udt_name");
				q.setId(j);
				q.setText(columnName);
				q.setUrl(dataType);
				clist.add(q);
				j++;
			}
			rsc.close();
			qt.setChildren(clist);
			list.add(qt);
			i++;
		}
		rs.close();
		dao.close();
		return list;
	}

	public static List quire(String sql) throws SQLException, ClassNotFoundException {
		DbInfo db = SqliteHelper.getDbInfo();
		SQLBaseDAO dao = new SQLBaseDAO(getConnection(db));
		ResultSet rs = dao.query(sql);
		dao.close();
		return convertResultSetToList(rs);
	}

	private static List convertResultSetToList(ResultSet rs) throws SQLException {
		List list = new ArrayList();
		ResultSetMetaData md = rs.getMetaData();
		int columnCount = md.getColumnCount(); // Map rowData;
		while (rs.next()) { // rowData = new HashMap(columnCount);
			Map rowData = new LinkedHashMap();//我们希望列表的字段是有序的
			for (int i = 1; i <= columnCount; i++) {
				int type = md.getColumnType(i);
				if(type==java.sql.Types.DATE||type==java.sql.Types.TIMESTAMP||type==java.sql.Types.TIME){
					rowData.put(md.getColumnName(i), rs.getTimestamp(i).toString());
				}else{
					rowData.put(md.getColumnName(i), rs.getObject(i));
				}
			}
			list.add(rowData);
		}
		return list;
	}

}
