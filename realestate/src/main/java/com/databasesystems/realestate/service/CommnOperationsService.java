package com.databasesystems.realestate.service;

import com.databasesystems.realestate.entity.Agent;
import com.databasesystems.realestate.models.request.SearchRequest;

public interface CommnOperationsService {

	public Agent createAgent(Agent agent) throws Exception;

	public Object searchInquiry(SearchRequest request) throws Exception;
}
