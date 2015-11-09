package com.v5ent.rapid4j.db.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Result : 响应的结果对象
 *
 * @author Mignet
 * @since 2014-09-27 16:28
 */
public class Result implements Serializable {
    private static final long serialVersionUID = 6288374846131788743L;

    /**
     * 信息
     */
    private String message;

    /**
     * 状态码
     */
    private int statusCode;

    /**
     * 是否成功
     */
    private boolean success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Result() {
    }

    public Result(boolean success,String message) {
    	this.success = success;
    	this.message = message;
    }
    
    public Result(boolean success,int statusCode,String message) {
    	this.success = success;
    	this.statusCode = statusCode;
    	this.message = message;
    }

}
