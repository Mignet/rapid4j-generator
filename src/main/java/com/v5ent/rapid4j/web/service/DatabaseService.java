package com.v5ent.rapid4j.web.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.v5ent.rapid4j.cache.sqlite.SqliteCache;
import com.v5ent.rapid4j.db.DriverAdapter;
import com.v5ent.rapid4j.db.JdbcTemplate;
import com.v5ent.rapid4j.db.RowMapper;
import com.v5ent.rapid4j.db.vo.QTree;
import com.v5ent.rapid4j.model.DbInfo;

public class DatabaseService {

	/**
	 * 通用查询
	 * @param sql
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static List<Object> inquire(String sql) throws SQLException, ClassNotFoundException {
		DbInfo db = SqliteCache.getDbInfo();
		JdbcTemplate dao = new JdbcTemplate(DriverAdapter.getConnection(db));
		ResultSet rs = dao.query(sql);
		List<Object> result = RowMapper.convertResultSetToList(rs);
		rs.close();
		dao.close();
		return result;
	}

	/**
	 * 获取数据库所有的表和字段信息<br>
	 *  ---------MySQL--------- <br>
	 *  查询所有数据库名 <br>
	 *  show databases;<br>
	 *  查询数据库中所有表名<br>
	 *  select table_name from information_schema.tables where table_schema='数据库名' and table_type='base table'; <br>
	 *  查询指定数据库中指定表的所有字段名column_name <br>
	 *  select column_name from information_schema.columns where table_schema='数据库名' and table_name='表名'<br>
	 *  ---------PostgreSQL--------- <br>
	 *  查询所有数据库名 <br>
	 *  select datname from pg_database; <br>
	 *  查询数据库中所有表名 <br>
	 *  select tablename from pg_tables where tablename not like 'pg%' and tablename not like 'sql_%'; <br>
	 *  查询表的列名<br> 
	 *  select * from information_schema.columns where table_name = 't_user'<br>
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
			sql_table = "select table_name as tablename from information_schema.tables where table_schema='"+db.getDbname()+"' and table_type='base table'";
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
			String sql_columns = "select column_name,udt_name from information_schema.columns where table_name = '"+tableName+"'";
			if ("MySQL".equals(db.getDbtype())) {
				sql_columns = "select column_name,column_type as udt_name from information_schema.columns where table_schema='"+db.getDbname()+"' and table_name='"+tableName+"'";
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
