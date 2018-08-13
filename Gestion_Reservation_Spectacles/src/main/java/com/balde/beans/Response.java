package com.balde.beans;


import org.springframework.http.HttpStatus;

public class Response {
	private Object data;
	private HttpStatus status;
	private String massage;
	
	public Response(Object data, HttpStatus status, String massage) {
		super();
		this.data = data;
		this.status = status;
		this.massage = massage;
	}

	public Response(Object data, HttpStatus status) {
		super();
		this.data = data;
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMassage() {
		return massage;
	}

	public void setMassage(String massage) {
		this.massage = massage;
	}
}
