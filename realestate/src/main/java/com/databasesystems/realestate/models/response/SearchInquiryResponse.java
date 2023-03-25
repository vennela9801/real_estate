package com.databasesystems.realestate.models.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class SearchInquiryResponse {

	private String houseOwner;
	private int houseId;
	private String houseType;
	private String address;
	private String city;
	private String state;
	private String zipCode;
	private String county;
	private String floors;
	private String bathrooms;
	private String landSize;
	private String yearConstructed;
	private String price;
	private String floorSpace;
	
	
	
}
