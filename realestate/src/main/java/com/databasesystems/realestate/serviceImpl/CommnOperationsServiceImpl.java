package com.databasesystems.realestate.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.databasesystems.realestate.entity.Agent;
import com.databasesystems.realestate.repository.AgentRepository;
import com.databasesystems.realestate.service.CommnOperationsService;

@Service
public class CommnOperationsServiceImpl implements CommnOperationsService{

	@Autowired 
	AgentRepository agentRepo;
	
	@Override
	public Agent createAgent(Agent agent) throws Exception {
		// TODO Auto-generated method stub
		try {
			return agentRepo.save(agent);
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception("error occured while creating new Agent");
		}
		
	}

	
}
