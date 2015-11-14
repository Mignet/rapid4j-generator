package com.v5ent.rapid4j.db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RowMapper {
	
	public static List<Object> convertResultSetToList(ResultSet rs) throws SQLException {
		List<Object> list = new ArrayList<Object>();
		ResultSetMetaData md = rs.getMetaData();
		int columnCount = md.getColumnCount(); 
		while (rs.next()) { 
			Map<String,Object> rowData = new LinkedHashMap<String,Object>();//我们希望列表的字段是有序的,所以使用LinkedHashMap
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
