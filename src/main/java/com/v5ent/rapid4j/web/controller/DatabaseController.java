package com.v5ent.rapid4j.web.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.v5ent.rapid4j.db.DriverAdapter;
import com.v5ent.rapid4j.db.SqliteHelper;
import com.v5ent.rapid4j.db.domain.DbInfo;
import com.v5ent.rapid4j.db.domain.JSONResult;
import com.v5ent.rapid4j.db.domain.Result;
import com.v5ent.rapid4j.db.tree.QJson;
import com.v5ent.rapid4j.db.tree.QTree;

/**
 * Database
 * 
 * @author Mignet
 * @since 2014年4月13日 下午5:22:20
 **/
@Controller
@RequestMapping("/db")
public class DatabaseController {
	
	/**
     * Check DB
     */
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public Result check(DbInfo db) {
    	Connection conn = null;
		try {
			conn = DriverAdapter.getConnection(db);
			if(conn!=null){
				conn.close();
				conn = null;
	    		return new Result(true,"正确连接!");
	    	}else{
	    		return new Result(false,500,"连接失败");
	    	}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return new Result(false,500,"连接失败:"+e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			return new Result(false,500,"连接失败:"+e.getMessage());
		}
    }
    
    //Login in
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(DbInfo db) {
    	try {
			SqliteHelper.cacheDbInfo(db);
			return "redirect:/rest/dashboard";
		} catch (SQLException e) {
			e.printStackTrace();
			return "/page/500";
		}
    }
    
    //get tables
    //SQLBaseDAO
    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    @ResponseBody
    public QJson getTables() {
    	try {
    		DbInfo db = SqliteHelper.getDbInfo();
    		List<QTree> tables = DriverAdapter.getTables(db);
    		QTree qt = new QTree();
    		qt.setChildren(tables);
    		qt.setId(0);
    		qt.setText(db.getDbtype());
    		qt.setUrl(db.getDbname());
    		return new QJson(true,"查询成功",qt);
		} catch (SQLException e) {
			e.printStackTrace();
			return new QJson(false,"连接失败:"+e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return new QJson(false,"连接失败:"+e.getMessage());
		}
    }
    
    @RequestMapping(value = "/quire", method = RequestMethod.POST)
    @ResponseBody
    public JSONResult quire(String sql) {
    	try {
    		List list = DriverAdapter.quire(sql);
    		return new JSONResult(true,"查询成功",list);
    	} catch (SQLException e) {
    		e.printStackTrace();
    		return new JSONResult(false,"查询失败:"+e.getMessage());
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    		return new JSONResult(false,"查询失败:"+e.getMessage());
    	}
    }
}