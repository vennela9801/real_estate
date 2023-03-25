package com.databasesystems.realestate.models.response;

import lombok.Data;

@Data
public class ResponseStatus {
	
	public String message;
	public int statusCode;
	public int id;
	public Object obj;
	
	public ResponseStatus(String message, int statusCode, int id) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.id = id;
	}
	
	public ResponseStatus(String message, int statusCode, int id, Object obj) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.id = id;
		this.obj = obj;
	}
	

}
