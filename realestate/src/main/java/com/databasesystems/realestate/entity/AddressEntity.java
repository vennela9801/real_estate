package com.databasesystems.realestate.entity;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="address")
@Data
public class AddressEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8515970463704162406L;

	@Id 
	@Column(name="AddressID")
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id; 

	@Column(name = "address")
	private String address;
	
	@Column(name = "zip")
	private int postalCode;
	
	@Column(name = "county")
	private String county;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "State")
	private String state;
	
//	@OneToOne(fetch = FetchType.LAZY,
//            cascade =  CascadeType.ALL,
//            mappedBy = "home")
//    private HomeEntity home;

}
