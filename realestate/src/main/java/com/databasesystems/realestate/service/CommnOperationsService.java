package com.databasesystems.realestate.service;

import java.util.List;

import com.databasesystems.realestate.entity.Agent;
import com.databasesystems.realestate.entity.HomeEntity;
import com.databasesystems.realestate.entity.HomeOwnerEntity;
import com.databasesystems.realestate.models.request.SearchRequest;
import com.databasesystems.realestate.models.response.SearchInquiryResponse;

public interface CommnOperationsService {

	public Agent createAgent(Agent agent) throws Exception;

	public List<SearchInquiryResponse> searchInquiry(SearchRequest request) throws Exception;

	public HomeEntity saveorUpdateHome(HomeEntity home) throws Exception;
	
	public HomeEntity fetchHomeDetails(int homeID) throws Exception;

	public HomeOwnerEntity saveorUpdateHomeOwners(HomeOwnerEntity homeOwner) throws Exception;

	public boolean deleteHomeDetails(HomeEntity home) throws Exception;

	public HomeOwnerEntity fetchHomeOwnerDetails(int ownerId) throws Exception;
}
