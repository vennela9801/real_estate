package com.databasesystems.realestate.models.response;

import lombok.Data;

@Data
public class ResponseStatus {
	
	public String message;
	public int statusCode;
	public int id;
	
	public ResponseStatus(String message, int statusCode, int id) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.id = id;
	}
	
	

}