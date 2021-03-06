package com.v5ent.rapid4j.web.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 公共视图控制器
 * 
 * @author Mignet
 * @since 2014年4月15日 下午4:16:34
 **/
@Controller
public class CommonController {
	
    /**
     * 连接入口
     * 
     * @param request
     * @return
     */
    @RequestMapping("index")
    public String index(HttpServletRequest request) {
        return "index";
    }
    
    /**
     * dashboard页
     */
    @RequestMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

}