package com.databasesystems.realestate.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.databasesystems.realestate.entity.AddressEntity;
import com.databasesystems.realestate.entity.Agent;
import com.databasesystems.realestate.entity.HomeEntity;
import com.databasesystems.realestate.models.request.SearchRequest;
import com.databasesystems.realestate.models.response.SearchInquiryResponse;
import com.databasesystems.realestate.repository.AddressRepository;
import com.databasesystems.realestate.repository.AgentRepository;
import com.databasesystems.realestate.repository.HomeRepository;
import com.databasesystems.realestate.service.CommnOperationsService;

@Service
public class CommnOperationsServiceImpl implements CommnOperationsService{

	@Autowired 
	AgentRepository agentRepo;
	
	@Autowired
	HomeRepository homeRepo;
	
	@Autowired
	AddressRepository addressRepo;
	
	@Autowired
	JdbcTemplate template;
	
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

	@SuppressWarnings("deprecation")
	@Override
	public List<SearchInquiryResponse> searchInquiry(SearchRequest request) throws Exception {
		// TODO Auto-generated method stub
		try {
		if(!StringUtils.isEmpty(request.getHouseOwner()) && !StringUtils.isEmpty(request.getCity())) {
			String queryStr = "Select home.HomeID as houseId, home.owner as houseOwner,home.FloorSpace as floorSpace, home.Floors as floors, home.Bathrooms as bathrooms, home.LandSize as landSize,\r\n"
					+ " home.YearConstructed, home.HomeType as houseType,\r\n"
					+ "  address.address as address, address.city as city, address.county as county, address.zip as zipCode,address.state, home.rate as price \r\n"
					+ "From home, address \r\n"
					+ "Where home.Address_id = address.Address_id and  \r\n"
					+ "home.owner = ? and \r\n"
					+ "address.city = ?;";
			List<String> params = new ArrayList<>();
			params.add(request.getHouseOwner());
			params.add(request.getCity());
			List<SearchInquiryResponse> resp = template.query(
				queryStr,
				new PreparedStatementSetter() {
					public void setValues(PreparedStatement preparedStatement) throws SQLException {
					   preparedStatement.setString(1, params.get(0));
					   preparedStatement.setString(2, params.get(1));
					}
				 },
				new BeanPropertyRowMapper<SearchInquiryResponse>(SearchInquiryResponse.class));
			return resp;
		}
		if(!StringUtils.isEmpty(request.getCity()) && !StringUtils.isEmpty(request.getBedRoomCount()) && !StringUtils.isEmpty(request.getTotalbaths()) && !StringUtils.isEmpty(request.getFloors())) {
			String queryStr = "Select home.HomeID as houseId, home.owner as houseOwner,home.FloorSpace as floorSpace, home.Floors as floors, home.Bedrooms as bedrooms, home.Bathrooms as bathrooms, home.LandSize as landSize,\r\n"
					+ "home.YearConstructed, home.HomeType as houseType,\r\n"
					+ "address.address as address, address.city as city, address.county as county, address.zip as zipCode,address.state, home.rate as price \r\n"
					+ "From home, address \r\n"
					+ "Where home.Address_id = address.Address_id and  \r\n"
					+ "home.Bedrooms = ? and \r\n"
					+ "home.Bathrooms = ? and \r\n"
					+ "home.Floors = ? and \r\n"
					+ "address.city = ?;";
			List<String> params = new ArrayList<>();
			params.add(String.valueOf(request.getBedRoomCount()));
			params.add(String.valueOf(request.getTotalbaths()));
			params.add(String.valueOf(request.getFloors()));
			params.add(request.getCity());
			List<SearchInquiryResponse> resp = template.query(
				queryStr,
				new PreparedStatementSetter() {
					public void setValues(PreparedStatement preparedStatement) throws SQLException {
					   preparedStatement.setString(1, params.get(0));
					   preparedStatement.setString(2, params.get(1));
					   preparedStatement.setString(3, params.get(2));
					   preparedStatement.setString(4, params.get(3));
					}
				 },
				new BeanPropertyRowMapper<SearchInquiryResponse>(SearchInquiryResponse.class));
			return resp;
		}

		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception(e.getLocalizedMessage());
		}
		return null;
	}

	@Override
	public HomeEntity saveorUpdateHome(HomeEntity home) throws Exception {
		// TODO Auto-generated method stub
		
		try {
			AddressEntity addressEnt = addressRepo.save(home.getAddress());
			home.setAddress(addressEnt);
			home.setAddressId(addressEnt.getId());
			return homeRepo.save(home);
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception("error occured while creating new Agent");
		}
	}
	
}
