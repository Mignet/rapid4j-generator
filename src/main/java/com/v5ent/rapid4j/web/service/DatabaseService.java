package com.v5ent.rapid4j.web.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.v5ent.rapid4j.db.DriverAdapter;
import com.v5ent.rapid4j.db.JdbcTemplate;
import com.v5ent.rapid4j.db.LocalCache;
import com.v5ent.rapid4j.db.RowMapper;
import com.v5ent.rapid4j.db.model.DbInfo;
import com.v5ent.rapid4j.db.vo.QTree;

public class DatabaseService {

	public static List inquire(String sql) throws SQLException, ClassNotFoundException {
		DbInfo db = LocalCache.getDbInfo();
		JdbcTemplate dao = new JdbcTemplate(DriverAdapter.getConnection(db));
		ResultSet rs = dao.query(sql);
		dao.close();
		return RowMapper.convertResultSetToList(rs);
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
		JdbcTemplate dao = new JdbcTemplate(DriverAdapter.getConnection(db));
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
}
