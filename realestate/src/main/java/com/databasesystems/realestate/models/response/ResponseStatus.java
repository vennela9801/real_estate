package com.databasesystems.realestate.models.response;

import java.util.List;

import lombok.Data;

@Data
public class ResponseStatus {
	
	public String message;
	public int statusCode;
	public int id;
	public List<SearchInquiryResponse> obj;
	
	public ResponseStatus(String message, int statusCode, int id) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.id = id;
	}
	
	public ResponseStatus(String message, int statusCode, int id, List<SearchInquiryResponse> obj) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.id = id;
		this.obj = obj;
	}
	

}
