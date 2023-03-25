package com.databasesystems.realestate.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="home")
@Data
public class HomeEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2088165970403536920L;

	@Id 
	@Column(name="HomeID")
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id; 

	@Column(name = "owner")
	private String houseOwner;
	
	@Column(name = "FloorSpace")
	private String floorSpace;
	
	@Column(name = "rate")
	private String houseRate;
	
	@Column(name = "Floors")
	private int floors;
	
	@Column(name = "Bathrooms")
	private String totalbaths;
	
	@Column(name = "Bedrooms")
	private int bedRoomCount;
	
	@Column(name = "LandSize")
	private String areaCovered;
	
	@Column(name = "YearConstructed")
	private int builtYear;
	
	@Column(name = "HomeType")
	private String houseType;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "availabilityStatus")
	private String availabilityStatus;
	
	@Column(name = "AgentID")
	private int agentID;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "AddressID", nullable = false)
	private AddressEntity addressId;
	
}
