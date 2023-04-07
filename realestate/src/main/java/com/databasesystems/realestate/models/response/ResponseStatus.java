package com.databasesystems.realestate.models.response;

import java.util.List;

import com.databasesystems.realestate.entity.HomeEntity;
import com.databasesystems.realestate.entity.HomeOwnerEntity;

import lombok.Data;

@Data
public class ResponseStatus {
	
	public String message;
	public int statusCode;
	public int id;
	public List<SearchInquiryResponse> obj;
	public HomeEntity homeDetails;
	public HomeOwnerEntity homeOwnerDetails;
	
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
	
	public ResponseStatus(String message, int statusCode, int id, HomeEntity homeDetails) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.id = id;
		this.homeDetails = homeDetails;
	}
	
	public ResponseStatus(String message, int statusCode, int id, HomeOwnerEntity homeOwnerDetails) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.id = id;
		this.homeOwnerDetails = homeOwnerDetails;
	}

}
