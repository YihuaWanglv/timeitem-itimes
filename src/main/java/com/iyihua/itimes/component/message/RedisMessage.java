package com.iyihua.itimes.component.message;

import java.io.Serializable;

public class RedisMessage implements Serializable {

	private static final long serialVersionUID = -8679950617238188971L;
	int id;
	String message;
	Object data;

	public RedisMessage(int id, String message, Object obj) {
		this.id = id;
		this.message = message;
		this.data = obj;
	}

	@Override
	public String toString() {
		return "RedisMessage [message=" + message + ", data=" + data.toString() + ", id=" + id + "]";
	}

	public int getId() {
		return id;
	}
	public String getMessage() {
		return message;
	}
	public Object getData() {
		return data;
	}

}