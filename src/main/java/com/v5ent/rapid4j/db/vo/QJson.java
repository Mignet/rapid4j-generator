package com.v5ent.rapid4j.db.vo;

public class QJson{  
    private boolean success = true;
    private String msg;// 消息
    private String type;// 类型
    private Object object;// 对象
    
	public QJson(boolean success, String msg) {
		this.success = success;
		this.msg = msg;
	}
	
	public QJson(boolean success, String msg, Object obj) {
		this.success = success;
		this.msg = msg;
		this.object = obj;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
}
