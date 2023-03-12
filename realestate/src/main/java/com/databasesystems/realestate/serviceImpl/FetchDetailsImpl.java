package com.databasesystems.realestate.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.databasesystems.realestate.service.FetchDetails;

@Service
public class FetchDetailsImpl implements FetchDetails{

	@Autowired
	JdbcTemplate template;
	
	@Override
	public String getFirstName() {
		String firstName = "";
		try {
			firstName = template.queryForObject("select fname from databaseproject1.myfirsttable where lname = 'test'", String.class);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return firstName;
		
	}
}
