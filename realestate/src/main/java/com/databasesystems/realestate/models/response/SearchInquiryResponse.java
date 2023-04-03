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
	private String bedrooms;
	private String bathrooms;
	private String landSize;
	private String yearConstructed;
	private String price;
	private String floorSpace;
	public String getHouseOwner() {
		return houseOwner;
	}
	public void setHouseOwner(String houseOwner) {
		this.houseOwner = houseOwner;
	}
	public int getHouseId() {
		return houseId;
	}
	public void setHouseId(int houseId) {
		this.houseId = houseId;
	}
	public String getHouseType() {
		return houseType;
	}
	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getFloors() {
		return floors;
	}
	public void setFloors(String floors) {
		this.floors = floors;
	}
	public String getBathrooms() {
		return bathrooms;
	}
	public void setBathrooms(String bathrooms) {
		this.bathrooms = bathrooms;
	}
	public String getLandSize() {
		return landSize;
	}
	public void setLandSize(String landSize) {
		this.landSize = landSize;
	}
	public String getYearConstructed() {
		return yearConstructed;
	}
	public void setYearConstructed(String yearConstructed) {
		this.yearConstructed = yearConstructed;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getFloorSpace() {
		return floorSpace;
	}
	public void setFloorSpace(String floorSpace) {
		this.floorSpace = floorSpace;
	}
	
	
	
}
