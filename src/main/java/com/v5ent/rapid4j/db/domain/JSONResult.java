package com.v5ent.rapid4j.db.domain;


/**
 * JSONResult : Response JSONResult for RESTful,封装返回JSON格式的数据
 *
 * @author Mignet
 * @since 2014年5月26日 上午10:51:46
 */

public class JSONResult<T> extends Result {

    private static final long serialVersionUID = 7880907731807860636L;

    /**
     * 数据
     */
    private T data;


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public JSONResult() {
        super();
    }

    /**
     * 自定义返回的结果
     *
     * @param data
     * @param message
     * @param success
     */
    public JSONResult(boolean success, String message, T data) {
        this.data = data;
        super.setMessage(message);
        super.setSuccess(success);
    }

	public JSONResult(boolean success, String message) {
		super.setSuccess(success);
		super.setMessage(message);
	}

}