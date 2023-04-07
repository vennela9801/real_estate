package com.databasesystems.realestate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.databasesystems.realestate.entity.Agent;
import com.databasesystems.realestate.entity.HomeEntity;
import com.databasesystems.realestate.entity.HomeOwnerEntity;
import com.databasesystems.realestate.models.request.SearchRequest;
import com.databasesystems.realestate.models.response.ResponseStatus;
import com.databasesystems.realestate.models.response.SearchInquiryResponse;
import com.databasesystems.realestate.service.CommnOperationsService;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
public class BaseOperationsController {

	@Autowired CommnOperationsService commOpsServ;
	
	@PostMapping(value = "/realEstate/addNewAgent", consumes = MediaType.APPLICATION_JSON_VALUE, 
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseStatus> addNewUser(@RequestBody Agent agent) {
		 
		try {
			Agent newAgent = commOpsServ.createAgent(agent);
			if(newAgent.getId() != 0 ) {
				return new ResponseEntity<>(new ResponseStatus("Success",200,newAgent.getId()),HttpStatus.OK);
			}
			throw new Exception("Error occured while creating agent");
		}catch(Exception e) {
			return new ResponseEntity<>(new ResponseStatus("Failure",500,0),HttpStatus.OK);
		}
	}
	
	@PostMapping(value = "/realEstate/saveHomeDetails", consumes = MediaType.APPLICATION_JSON_VALUE, 
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseStatus> saveorUpdateHome(@RequestBody HomeEntity home) {
		 
		try {
			HomeEntity savedResp = commOpsServ.saveorUpdateHome(home);
			if(savedResp.getId() != 0 ) {
				return new ResponseEntity<>(new ResponseStatus("Success",200,savedResp.getId()),HttpStatus.OK);
			}
			throw new Exception("Error occured while creating agent");
		}catch(Exception e) {
			return new ResponseEntity<>(new ResponseStatus("Failure",500,0),HttpStatus.OK);
		}
	}
	
	@PostMapping(value = "/realEstate/deleteHomeDetails", consumes = MediaType.APPLICATION_JSON_VALUE, 
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseStatus> deleteHomeDetails(@RequestBody HomeEntity home) {
		 
		try {
			boolean savedResp = commOpsServ.deleteHomeDetails(home);
			if(savedResp) {
				return new ResponseEntity<>(new ResponseStatus("Success",200,0),HttpStatus.OK);
			}
			throw new Exception("Error occured while creating agent");
		}catch(Exception e) {
			return new ResponseEntity<>(new ResponseStatus("Failure",500,0),HttpStatus.OK);
		}
	}
	
	@PostMapping(value = "/realEstate/saveHomeOwners", consumes = MediaType.APPLICATION_JSON_VALUE, 
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseStatus> saveorUpdateHomeOwners(@RequestBody HomeOwnerEntity homeOwner) {
		 
		try {
			HomeOwnerEntity savedResp = commOpsServ.saveorUpdateHomeOwners(homeOwner);
			if(savedResp.getId() != 0 ) {
				return new ResponseEntity<>(new ResponseStatus("Success",200,savedResp.getId()),HttpStatus.OK);
			}
			throw new Exception("Error occured while creating agent");
		}catch(Exception e) {
			return new ResponseEntity<>(new ResponseStatus("Failure",500,0),HttpStatus.OK);
		}
	}
	
	@PostMapping(value = "/realEstate/searchRequest", consumes = MediaType.APPLICATION_JSON_VALUE, 
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseStatus> search(@RequestBody SearchRequest request) {
		 
		try {
			List<SearchInquiryResponse> obj = commOpsServ.searchInquiry(request);
			return new ResponseEntity<>(new ResponseStatus("Success",200,0,obj),HttpStatus.OK);
			
		}catch(Exception e) {
			return new ResponseEntity<>(new ResponseStatus("Failure",500,0),HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/realEstate/fetchHomeDetails")
	public ResponseEntity<ResponseStatus> fetchHomeDetailsbyId(@RequestParam(name = "homeId") int homeId) {
		try {
			HomeEntity homeresp = commOpsServ.fetchHomeDetails(homeId);
			return new ResponseEntity<>(new ResponseStatus("Success",200,0,homeresp),HttpStatus.OK);
			
		}catch(Exception e) {
			return new ResponseEntity<>(new ResponseStatus("Failure",500,0),HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/realEstate/fetchHomeOwnerDetails")
	public ResponseEntity<ResponseStatus> fetchHomeOwnerDetailsbyId(@RequestParam(name = "ownerId") int ownerId) {
		try {
			HomeOwnerEntity homeresp = commOpsServ.fetchHomeOwnerDetails(ownerId);
			return new ResponseEntity<>(new ResponseStatus("Success",200,0,homeresp),HttpStatus.OK);
			
		}catch(Exception e) {
			return new ResponseEntity<>(new ResponseStatus("Failure",500,0),HttpStatus.OK);
		}
	}
	
}

